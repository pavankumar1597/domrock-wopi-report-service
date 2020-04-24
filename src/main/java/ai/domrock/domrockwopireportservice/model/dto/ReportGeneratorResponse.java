package ai.domrock.domrockwopireportservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportGeneratorResponse {

    private String status;
    private String fileName;
    private Long fileSize;
}
