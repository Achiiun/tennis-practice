package tennis.practice.repository;

import tennis.practice.domain.Board;

public interface BoardRepositoryInterface {

  Board findOne(Long boardId);

}
