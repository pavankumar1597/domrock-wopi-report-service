package ai.domrock.domrockwopireportservice.repository;

import ai.domrock.domrockwopireportservice.model.Details;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDetailRepository extends MongoRepository<Details, String> {

    List<Details> findAllByIdReportSummaryEquals(String idReportSummary);
}
