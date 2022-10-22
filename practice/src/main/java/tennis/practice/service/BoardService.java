package tennis.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

  @Transactional
  public Long addBoard(BoardSaveForm form, Member member) {
    Board board = Board.createBoard(form, member);
    boardRepository.save(board);
    return board.getId();
  }

  @Transactional
  public List<Board> getBoardList() {
    return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
  }

  public Board findOne(Long boardId) {
    return boardRepository.findOne(boardId);
  }
  @Transactional
  public void deleteById(Long boardId) {
    boardRepository.deleteById(boardId);
  }

  @Transactional
  public void update(Board boardForm, Long boardId) {
    Board board = boardRepository.findById(boardId).get();
    board.setTitle(boardForm.getTitle());
    board.setContent(boardForm.getContent());
    boardRepository.save(board);
  }
}
