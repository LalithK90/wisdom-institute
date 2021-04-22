package lk.wisdom_institute.asset.time_table.service;


import lk.wisdom_institute.asset.batch.entity.Batch;
import lk.wisdom_institute.asset.common_asset.model.enums.LiveDead;
import lk.wisdom_institute.asset.time_table.dao.TimeTableDao;
import lk.wisdom_institute.asset.time_table.entity.TimeTable;
import lk.wisdom_institute.asset.time_table.entity.enums.TimeTableStatus;
import lk.wisdom_institute.util.interfaces.AbstractService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeTableService implements AbstractService< TimeTable, Integer > {
  private final TimeTableDao timeTableDao;

  public TimeTableService(TimeTableDao timeTableDao) {
    this.timeTableDao = timeTableDao;
  }

  public List< TimeTable > findAll() {
    return timeTableDao.findAll().stream().filter(x -> x.getLiveDead().equals(LiveDead.ACTIVE)).collect(Collectors.toList());
  }

  public TimeTable findById(Integer id) {
    return timeTableDao.getOne(id);
  }

  public TimeTable persist(TimeTable timeTable) {
    if ( timeTable.getId() == null ) {
      timeTable.setLiveDead(LiveDead.ACTIVE);
      timeTable.setTimeTableStatus(TimeTableStatus.NOTMARKED);
    }
    return timeTableDao.save(timeTable);
  }

  public boolean delete(Integer id) {
    TimeTable timeTable = timeTableDao.getOne(id);
    timeTable.setLiveDead(LiveDead.STOP);
    timeTableDao.save(timeTable);
    return false;
  }

  public List< TimeTable > search(TimeTable timeTable) {
    return null;
  }


  public TimeTable lastTimeTable() {
    return timeTableDao.findFirstByOrderByIdDesc();
  }

  public boolean availableTimeTableCheck(LocalDateTime from, LocalDateTime to, Batch batch) {
    TimeTableStatus timeTableStatus = TimeTableStatus.NOTMARKED;
    List< TimeTable > timeTables = timeTableDao.findByBatchAndStartAtIsBetweenAndTimeTableStatus(batch, from, to,timeTableStatus);
    return timeTables.isEmpty();
  }

  public List< TimeTable> findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to) {
  return timeTableDao.findByStartAtIsBetween(from,to);
  }
}
