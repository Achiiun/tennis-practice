package tennis.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tennis.practice.domain.Member;
import tennis.practice.dto.MemberSaveForm;
import tennis.practice.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public Long memberAdd(MemberSaveForm form) {
    Member member = Member.createMember(form);
    memberRepository.save(member);
    return member.getId();
  }
}
