package myweb.secondboard.repository;

import lombok.RequiredArgsConstructor;
import myweb.secondboard.domain.Matching;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchingRepositoryImpl {

  @PersistenceContext
  private final EntityManager em;

  public List<Matching> findAll() {
    return em.createQuery("select m from Matching m", Matching.class).getResultList();
  }

  public Matching findOne(Long matchingId) {
    return em.find(Matching.class, matchingId);
  }
}
