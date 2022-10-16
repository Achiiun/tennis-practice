package tennis.practice.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MemberSaveForm {


  @NotNull @Size(min = 8, max = 20, message = "아이디는 8~20 자 이내여야 합니다.")
  private String loginId;

  @NotNull @Size(min = 8, max = 30, message = "비밀번호는 8~30 자 이내여야 합니다.")
  private String password;

  @NotNull @Size(min = 2, max = 10, message = "닉네임은 2~10 자 이내여야 합니다.")
  private String nickname;

  @NotNull
  @Email @Size(max = 50, message = "이메일은 최대 50자 입니다.")
  private String email;

}
