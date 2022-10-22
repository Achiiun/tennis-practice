package tennis.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tennis.practice.domain.Comment;
import tennis.practice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryInterface {

    @Query("select c from Comment c where c.board.id = :boardId")
    List<Comment> findComments(@Param("boardId") Long boardId);
}
