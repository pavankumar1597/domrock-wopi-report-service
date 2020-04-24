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
@Document(collection = "demo-dev-reports_details")
public class Details {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Field("cod_ems")
    private String codEms;
    @Field("nome_dasa")
    private String nomeDasa;
    private String recepcao;
    @Field("reg_hospital")
    private String regHospital;
    @Field("atend_hosp")
    private String atendHospital;
    @Field("ped_prescricao")
    private String pedPrescricao;
    @Field("data_atendimento")
    private String dataAtendimento;
    @Field("data_admissao")
    private String dataAdmissao;
    @Field("hora_atend")
    private String horaAtendimento;
    @Field("convenio_ems")
    private String convenioEms;
    private String convenio;
    private String exame;
    @Field("cod_amb")
    private String codAmb;
    @Field("descricao_dasa")
    private String descricaoDasa;
    @Field("cod_exame_origem")
    private String codExameOrigem;
    @Field("seq_exame_origem")
    private String seqExameOrigin;
    @Field("sequencia_exame")
    private String sequenciaExame;
    private String amost;
    @Field("ac_pd")
    private String acPd;
    private String crm;
    @Field("nome_medico_1")
    private String nomeMedico;
    @Field("desc_origem")
    private String descOrigem;
    @Field("data_laudo")
    private LocalDateTime dataLaudo;
    private String url;
    @Field("valor_total_exame")
    private Double valorTotalExame;
    @Field("valor_total_exame_rec")
    private Double valotTotalExameRec;
    @Field("valor_material")
    private Double valorMaterial;
    @Field("valor_medicamento")
    private Double valorMedicamente;
    @Field("valor_filme")
    private Double valorFilme;
    @Field("valor_taxas")
    private Double valorTaxas;
    @Field("valor_exame")
    private Double valorExame;
    @Field("perc_desc")
    private String percDesc;
    private Double valor;
    private String ch;
    @Field("status_laudo")
    private String statusLaudo;
    @Field("status_faturado")
    private String statusFaturado;
    private String unit;
    @Field("id_report_summary")
    private String idReportSummary;
}
