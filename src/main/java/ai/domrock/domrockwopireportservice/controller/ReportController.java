package ai.domrock.domrockwopireportservice.controller;

import ai.domrock.domrockwopireportservice.model.Details;
import ai.domrock.domrockwopireportservice.model.Summary;
import ai.domrock.domrockwopireportservice.model.dto.ReportGeneratorResponse;
import ai.domrock.domrockwopireportservice.service.FileGeneratorService;
import ai.domrock.domrockwopireportservice.service.ReportDetailService;
import ai.domrock.domrockwopireportservice.service.ReportSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportDetailService detailService;
    private final ReportSummaryService summaryService;
    private final FileGeneratorService fileGeneratorService;


    @Autowired
    public ReportController(ReportDetailService detailService,
                            ReportSummaryService summaryService,
                            FileGeneratorService fileGeneratorService) {
        this.detailService = detailService;
        this.summaryService = summaryService;
        this.fileGeneratorService = fileGeneratorService;
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<List<Details>> getDetailsByIdReportSummary(@PathVariable("id") String id) {

        return ResponseEntity.ok(detailService.getAllDetailsByIdReportSummary(id));
    }

    @GetMapping("/summary/{id}/{dateInit}/{dateEnd}")
    public ResponseEntity<List<Summary>> getSummariesByFilter(@PathVariable("id") String id,
                                                              @PathVariable("dateInit") String dateInit,
                                                              @PathVariable("dateEnd") String dateEnd) {

        return ResponseEntity.ok(summaryService.getAllSummaryByFilter(id, dateInit, dateEnd));
    }

    @GetMapping("/wopi/{id}")
    public ResponseEntity<ReportGeneratorResponse> generateWopiFile(@PathVariable("id") String id) {

        return ResponseEntity.ok(fileGeneratorService.generateWopiFile(id));
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}
