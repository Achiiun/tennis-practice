package tennis.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tennis.practice.domain.Board;
import tennis.practice.domain.Comment;
import tennis.practice.domain.Member;
import tennis.practice.repository.CommentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  public List<Comment> findComments(Long boardId) {
    return commentRepository.findComments(boardId);
  }
  @Transactional
  public void save(Map<String, Object> param, Board board, Member member) {
    Comment comment = new Comment();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    comment.setAuthor(member.getNickname());
    comment.setContent(param.get("content").toString());
    comment.setBoard(board);
    comment.setMember(member);
    comment.setCreatedDate(LocalDateTime.now().format(dtf));
    comment.setModifiedDate(LocalDateTime.now().format(dtf));
    commentRepository.save(comment);
  }



}
