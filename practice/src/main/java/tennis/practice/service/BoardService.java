package tennis.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tennis.practice.domain.Board;
import tennis.practice.domain.Member;
import tennis.practice.dto.BoardSaveForm;
import tennis.practice.repository.BoardRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public Long addBoard(BoardSaveForm form, Member member) {
    Board board = Board.createBoard(form, member);
    boardRepository.save(board);
    return board.getId();
  }

  public List<Board> getBoardList() {
    return boardRepository.findAll();
  }

  public Board findOne(Long boardId) {
    return boardRepository.findOne(boardId);
  }
}
