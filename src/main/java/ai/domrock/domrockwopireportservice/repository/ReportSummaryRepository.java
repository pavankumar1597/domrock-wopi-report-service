package ai.domrock.domrockwopireportservice.repository;

import ai.domrock.domrockwopireportservice.model.Summary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportSummaryRepository extends MongoRepository<Summary, String> {

    List<Summary> findSummariesByIdAndDateInitAndDateEnd(String id, String dateInit, String dateEnd);
}
