package ai.domrock.domrockwopireportservice.service;

import ai.domrock.domrockwopireportservice.model.Summary;

import java.util.List;

public interface ReportSummaryService {

    List<Summary> getAllSummaryByFilter(String id, String dateInit, String dateEnd);
}
