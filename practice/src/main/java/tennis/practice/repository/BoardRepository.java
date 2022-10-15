package tennis.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tennis.practice.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryInterface {

}
