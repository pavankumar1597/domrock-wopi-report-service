package ai.domrock.domrockwopireportservice.service.impl;

import ai.domrock.domrockwopireportservice.model.Details;
import ai.domrock.domrockwopireportservice.repository.ReportDetailRepository;
import ai.domrock.domrockwopireportservice.service.ReportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportDetailServiceImpl implements ReportDetailService {

    private final ReportDetailRepository repository;

    @Autowired
    public ReportDetailServiceImpl(ReportDetailRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Details> getAllDetailsByIdReportSummary(String id) {

        return repository.findAllByIdReportSummaryEquals(id);
    }
}
