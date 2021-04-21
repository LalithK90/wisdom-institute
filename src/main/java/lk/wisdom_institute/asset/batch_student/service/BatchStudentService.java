package lk.wisdom_institute.asset.batch_student.service;


import lk.wisdom_institute.asset.batch.entity.Batch;
import lk.wisdom_institute.asset.batch_student.dao.BatchStudentDao;
import lk.wisdom_institute.asset.batch_student.entity.BatchStudent;
import lk.wisdom_institute.asset.common_asset.model.enums.LiveDead;
import lk.wisdom_institute.asset.student.entity.Student;
import lk.wisdom_institute.util.interfaces.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchStudentService implements AbstractService< BatchStudent, Integer > {
  private final BatchStudentDao batchStudentDao;

  public BatchStudentService(BatchStudentDao batchStudentDao) {
    this.batchStudentDao = batchStudentDao;
  }

  public List< BatchStudent > findAll() {
    return batchStudentDao.findAll();
  }

  public BatchStudent findById(Integer id) {
    return batchStudentDao.getOne(id);
  }

  public BatchStudent persist(BatchStudent batch) {
    if ( batch.getId() == null ) {
      batch.setLiveDead(LiveDead.ACTIVE);
    }
    return batchStudentDao.save(batch);
  }

  public boolean delete(Integer id) {
    BatchStudent batch = batchStudentDao.getOne(id);
    batch.setLiveDead(LiveDead.STOP);
    batchStudentDao.save(batch);
    return false;
  }

  public List< BatchStudent > search(BatchStudent batch) {
    return null;
  }


  public int countByBatch(Batch batch) {
    return (int) batchStudentDao.findByBatch(batch).stream().filter(x -> x.getLiveDead().equals(LiveDead.ACTIVE)).count();
  }

  public List< BatchStudent > findByStudent(Student student) {
    return batchStudentDao.findByStudent(student);
  }

  public BatchStudent findByStudentAndBatch(Student student, Batch batch) {
    return batchStudentDao.findByStudentAndBatch(student, batch);
  }

  public List< BatchStudent > findByBatch(Batch batch) {
    return batchStudentDao.findByBatch(batch);
  }
}
