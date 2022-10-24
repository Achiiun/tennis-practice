package tennis.practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tennis.practice.domain.Board;
import tennis.practice.domain.Comment;
import tennis.practice.domain.Member;
import tennis.practice.service.BoardService;
import tennis.practice.service.CommentService;
import tennis.practice.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentApiController {

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

  @PostMapping("/commentDelete/{commentId}")
  public int commentDelete(@PathVariable("commentId") Long commentId) {

    commentService.deleteById(commentId);

    return 1;
  }

  @PostMapping("/commentUpdate/{commentId}")
  public int commentUpdate(@PathVariable("commentId") Long commentId,
                           @RequestParam Map<String, Object> param) {
    String content = param.get("content").toString();

    if (content.length() < 1 || content.length() > 100) {
      return 2;
    }

    commentService.updateComment(commentId, param);
    return 1;
  }

  @PostMapping("/commentUpdateCancel/{commentId}")
  public int commentUpdateCancel(@PathVariable("commentId") Long commentId) {

    commentService.updateCommentCancel(commentId);

    return 1;
  }

}
