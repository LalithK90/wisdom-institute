package lk.wisdom_institute.asset.batch_exam.dao;
import lk.wisdom_institute.asset.batch.entity.Batch;
import lk.wisdom_institute.asset.batch_exam.entity.BatchExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BatchExamDao extends JpaRepository< BatchExam, Integer> {
  int countByBatch(Batch batch);

  BatchExam findFirstByOrderByIdDesc();

  List< BatchExam> findByBatch(Batch batch);

  List< BatchExam> findByStartAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

  List<BatchExam> findByStartAtBetweenAndBatch(LocalDateTime startDateTime, LocalDateTime endDateTime,Batch batch);
}
