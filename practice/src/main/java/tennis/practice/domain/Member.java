package tennis.practice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tennis.practice.dto.MemberSaveForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;


@Entity
@Getter @Setter
@NoArgsConstructor
public class Member implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @NotNull
  @Column(unique = true, length = 20)
  private String loginId;

  @NotNull
  @Column(unique = true, length = 30)
  private String password;

  @NotNull
  @Column(unique = true, length = 10)
  private String nickname;

  @NotNull
  @Column(unique = true, length = 50)
  private String email;

  /*
   * mappedBy: 연관관계의 주인 필드를 선택
   * cascade: 영속성 전이 기능을 사용 (REMOVE: 부모 엔티티가 삭제하면 연관된 자식 엔티티도 함께 삭제된다.)
   */
//  @OneToMany(mappedBy = "board", cascade = REMOVE)
//  private List<Comment> comments = new ArrayList<>();


  public static Member createMember(MemberSaveForm form) {
    Member member = new Member();
    member.setLoginId(form.getLoginId());
    member.setPassword(form.getPassword());
    member.setNickname(form.getNickname());
    member.setEmail(form.getEmail());
    return member;
  }

}
