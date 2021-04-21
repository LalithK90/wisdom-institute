package lk.wisdom_institute.asset.time_table.dao;


import lk.wisdom_institute.asset.batch.entity.Batch;
import lk.wisdom_institute.asset.time_table.entity.TimeTable;
import lk.wisdom_institute.asset.time_table.entity.enums.TimeTableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeTableDao extends JpaRepository< TimeTable, Integer > {

  TimeTable findFirstByOrderByIdDesc();

  List<TimeTable> findByBatchAndStartAtIsBetween(Batch batch, LocalDateTime form, LocalDateTime to);

  List< TimeTable> findByStartAtIsBetween(LocalDateTime from, LocalDateTime to);

  List< TimeTable> findByBatchAndStartAtIsBetweenAndTimeTableStatus(Batch batch, LocalDateTime from, LocalDateTime to, TimeTableStatus timeTableStatus);
}
