package tennis.practice.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tennis.practice.domain.Board;
import tennis.practice.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl {

  @PersistenceContext
  private final EntityManager em;

//  public List<Comment> findComments(Long boardId) {
//    return em.createQuery("select c from Comment c where c.board.id =" + boardId, Comment.class).getResultList();
//  }
}
