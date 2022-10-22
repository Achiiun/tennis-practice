package tennis.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tennis.practice.domain.Board;
import tennis.practice.domain.Comment;
import tennis.practice.domain.Member;
import tennis.practice.dto.BoardSaveForm;
import tennis.practice.service.BoardService;
import tennis.practice.service.CommentService;
import tennis.practice.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

  private final BoardService boardService;

  private final CommentService commentService;


  //==게시글 전체조회==//
  @GetMapping("/home")
  public String home(Model model) {
    List<Board> list = boardService.getBoardList();
    model.addAttribute("list", list);

    return "/boards/boardHome";
  }

  @GetMapping("/boardAdd") // 등록 폼
  public String boardAddForm(Model model) {
    model.addAttribute("form", new BoardSaveForm());
    return "/boards/boardAddForm";
  }

  @PostMapping("/new")
  public String boardAdd(@Validated @ModelAttribute("form") BoardSaveForm form,
                         BindingResult bindingResult, HttpServletRequest request) {

    Member member = (Member) request.getSession(false)
      .getAttribute(SessionConst.LOGIN_MEMBER);

    //==검증==//
    if (bindingResult.hasErrors()) {
      log.info("error = {}", bindingResult);
      return "/boards/boardAddForm";
    }

    //==게시글 등록==//
    Long boardId = boardService.addBoard(form, member);
    return "redirect:/boards/detail/" + boardId;
  }

  @GetMapping("/detail/{boardId}")
  public String boardDetail(@PathVariable("boardId") Long boardId, Model model) {

    Board board = boardService.findOne(boardId);
    boardDetailView(boardId, model, board);
    return "boards/boardDetailForm";
  }

  private void boardDetailView(Long boardId, Model model, Board board) {
    model.addAttribute("board", board);

    List<Comment> comments = commentService.findComments(boardId);
    if (comments != null) {
      model.addAttribute("comments", comments);
    }
  }

  @PostMapping("/delete/{boardId}")
  public String boardDelete(@PathVariable("boardId") Long boardId) {
    boardService.deleteById(boardId);
    return "redirect:/boards/home";
  }

  @GetMapping("/update/{boardId}")
  public String boardUpdateForm(@PathVariable("boardId") Long boardId, Model model) {
    Board board = boardService.findOne(boardId);
    model.addAttribute("board", board);
    return "/boards/boardUpdateForm";
  }

  @PostMapping("/update/{boardId}")
  public String boardUpdate(@PathVariable("boardId") Long boardId, Board board) {
    boardService.update(board, boardId);
    return "redirect:/boards/detail/" + boardId;
  }
}
