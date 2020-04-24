package ai.domrock.domrockwopireportservice.service;

import ai.domrock.domrockwopireportservice.model.Details;

import java.util.List;

public interface ReportDetailService {

    List<Details> getAllDetailsByIdReportSummary(String id);
}
