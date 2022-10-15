package tennis.practice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tennis.practice.dto.MemberSaveForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


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


  public static Member createMember(MemberSaveForm form) {
    Member member = new Member();
    member.setLoginId(form.getLoginId());
    member.setPassword(form.getPassword());
    member.setNickname(form.getNickname());
    member.setEmail(form.getEmail());
    return member;
  }
}
