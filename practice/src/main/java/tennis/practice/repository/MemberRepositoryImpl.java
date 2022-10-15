package tennis.practice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl {

  @PersistenceContext
  private final EntityManager em; // jpa 엔티티에 쿼리를 날려주는 아이
}
