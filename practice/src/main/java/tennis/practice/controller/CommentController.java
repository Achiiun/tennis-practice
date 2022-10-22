package tennis.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tennis.practice.domain.Board;
import tennis.practice.domain.Member;
import tennis.practice.service.BoardService;
import tennis.practice.service.CommentService;
import tennis.practice.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

  private final BoardService boardService;

  private final CommentService commentService;

  @PostMapping("/commentAdd/{boardId}")
  public int commentAdd(@PathVariable("boardId") Long boardId,
                           @RequestParam Map<String, Object> param, HttpServletRequest request) {
    String content = param.get("content").toString();

    if (content.length() < 1 || content.length() > 100) {
      return 2;
    }

    Member member = (Member) request.getSession(false)
      .getAttribute(SessionConst.LOGIN_MEMBER);
    Board board = boardService.findOne(boardId);

    commentService.save(param, board, member);
    return 1;
  }


}
