package ai.domrock.domrockwopireportservice.service;

import ai.domrock.domrockwopireportservice.model.dto.ReportGeneratorResponse;

public interface FileGeneratorService {

    ReportGeneratorResponse generateWopiFile(String id);
}
