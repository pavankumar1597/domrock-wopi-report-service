package ai.domrock.domrockwopireportservice.service.impl;

import ai.domrock.domrockwopireportservice.model.Details;
import ai.domrock.domrockwopireportservice.model.dto.ReportGeneratorResponse;
import ai.domrock.domrockwopireportservice.service.FileGeneratorService;
import ai.domrock.domrockwopireportservice.service.FileUploaderService;
import ai.domrock.domrockwopireportservice.service.ReportDetailService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FileGeneratorServiceImpl implements FileGeneratorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileGeneratorServiceImpl.class);

    @Value("${file.name}")
    private String fileName;
    @Value("${file.extension}")
    private String fileExtension;
    private final FileUploaderService fileUploaderService;
    private final ReportDetailService reportDetailService;

    @Autowired
    public FileGeneratorServiceImpl(FileUploaderService fileUploaderService, ReportDetailService reportDetailService) {
        this.fileUploaderService = fileUploaderService;
        this.reportDetailService = reportDetailService;
    }


    @Override
    public ReportGeneratorResponse generateWopiFile(String id) {

        List<Details> reportDetails = reportDetailService.getAllDetailsByIdReportSummary(id);
        try {
            File fileGenerated = createXlsFile(reportDetails);
            return ReportGeneratorResponse.builder()
                    .fileName(fileGenerated.getName())
                    .fileSize(fileGenerated.length())
                    .status("Success")
                    .build();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return ReportGeneratorResponse.builder()
                    .status("Erro")
                    .build();
        }
    }

    private File createXlsFile(List<Details> reportDetails) throws IOException {

        File tempFile = Files.createTempFile(fileName, LocalDateTime.now().toString() + fileExtension).toFile();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report Details");

        Row headerRow = sheet.createRow(0);
        AtomicInteger headerIndex = new AtomicInteger();
        Arrays.stream(Details.class.getDeclaredFields()).forEach(field -> {
            headerRow.createCell(headerIndex.getAndIncrement()).setCellValue(field.getName());
        });

        AtomicInteger detailIndex = new AtomicInteger();
        reportDetails.forEach(detail -> {
            Row row = sheet.createRow(detailIndex.incrementAndGet());
            Field[] fields = detail.getClass().getDeclaredFields();
            AtomicInteger index = new AtomicInteger();

            Arrays.stream(fields)
                    .forEach(field -> {
                        field.setAccessible(true);
                        try {
                            row.createCell(index.getAndIncrement()).setCellValue(String.valueOf(field.get(detail)));
                        } catch (IllegalAccessException e) {
                            LOGGER.error(e.getMessage());
                        }
                    });
        });

        FileOutputStream out = new FileOutputStream(tempFile);
        workbook.write(out);
        out.close();

        fileUploaderService.uploadFileToAzure(tempFile);
        return tempFile;
    }
}
