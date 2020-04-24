package ai.domrock.domrockwopireportservice.service.impl;

import ai.domrock.domrockwopireportservice.model.Summary;
import ai.domrock.domrockwopireportservice.repository.ReportSummaryRepository;
import ai.domrock.domrockwopireportservice.service.ReportSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportSummaryServiceImpl implements ReportSummaryService {

    private final ReportSummaryRepository repository;


    @Autowired
    public ReportSummaryServiceImpl(ReportSummaryRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Summary> getAllSummaryByFilter(String id, String dateInit, String dateEnd) {

        return repository.findSummariesByIdAndDateInitAndDateEnd(id, dateInit, dateEnd);
    }
}
