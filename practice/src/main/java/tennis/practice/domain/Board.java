package tennis.practice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import tennis.practice.dto.BoardSaveForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
  @Column(name = "board_id")
  private Long id;

  @NotNull
  @Column(length = 31)
  private String title;

  @NotNull
  @Column(length = 145)
  private String content;

  @NotNull
  @Column(length = 11)
  private String author;   //member nickname

  @Column(columnDefinition = "integer default 0")
  private Integer views;

  @CreatedDate
  @Column(length = 40)
  private String createdDate;

  @LastModifiedDate
  @Column(length = 40)
  private String modifiedDate;


  @ManyToOne(fetch = LAZY) // 속도보다 안전성
  @JoinColumn(name = "member_id")
  private Member member;

  /*
   * mappedBy: 연관관계의 주인 필드를 선택
   * cascade: 영속성 전이 기능을 사용 (REMOVE: 부모 엔티티가 삭제하면 연관된 자식 엔티티도 함께 삭제된다.)
   */
//  @OneToMany(mappedBy = "board", cascade = REMOVE)
//  private List<Comment> comments = new ArrayList<>();


  //==생성 메서드==//
  public static Board createBoard(BoardSaveForm form, Member member) {
    Board board = new Board();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    board.setTitle(form.getTitle());
    board.setContent(form.getContent());
    board.setAuthor(member.getNickname());
    board.setViews(0);
    board.setCreatedDate(LocalDateTime.now().format(dtf));
    board.setModifiedDate(LocalDateTime.now().format(dtf));
    board.setMember(member);

    return board;
  }

}
