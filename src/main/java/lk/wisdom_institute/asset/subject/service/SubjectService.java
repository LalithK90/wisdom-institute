package lk.wisdom_institute.asset.subject.service;



import lk.wisdom_institute.asset.common_asset.model.enums.LiveDead;
import lk.wisdom_institute.asset.subject.dao.SubjectDao;
import lk.wisdom_institute.asset.subject.entity.Subject;
import lk.wisdom_institute.util.interfaces.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService implements AbstractService< Subject, Integer > {
  private final SubjectDao subjectDao;

  public SubjectService(SubjectDao subjectDao) {
    this.subjectDao = subjectDao;
  }

  public List< Subject > findAll() {
    return subjectDao.findAll()
        .stream()
        .filter(x -> x.getLiveDead().equals(LiveDead.ACTIVE))
        .collect(Collectors.toList());
  }

    public List< Subject > findAllDeleted() {
        return subjectDao.findAll()
            .stream()
            .filter(x -> x.getLiveDead().equals(LiveDead.STOP))
            .collect(Collectors.toList());
    }


    public Subject findById(Integer id) {
    return subjectDao.getOne(id);
  }

  public Subject persist(Subject subject) {
    if ( subject.getId() == null ) {
      subject.setLiveDead(LiveDead.ACTIVE);
    }
    return subjectDao.save(subject);
  }

  public boolean delete(Integer id) {
    Subject subject = subjectDao.getOne(id);
    subject.setLiveDead(LiveDead.STOP);
    subjectDao.save(subject);
    return false;
  }

  public List< Subject > search(Subject subject) {
    return null;
  }


  public Subject lastSubject() {
    return subjectDao.findFirstByOrderByIdDesc();
  }
}
