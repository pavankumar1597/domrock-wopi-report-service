package ai.domrock.domrockwopireportservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "demo-dev-reports_summary")
public class Summary {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Field("Unit")
    private String unit;
    @Field("total_documentos")
    private Long totalDocumentos;
    private String algorithm;
    @Field("data_execucao")
    private LocalDateTime executionDate;
    @Field("publicar")
    private Boolean publish;
    @Field("DateInit")
    private String dateInit;
    @Field("DateEnd")
    private String dateEnd;
    private Double value;
}
