package myweb.secondboard.service;

import lombok.RequiredArgsConstructor;
import myweb.secondboard.domain.Board;
import myweb.secondboard.domain.Matching;
import myweb.secondboard.domain.Member;
import myweb.secondboard.domain.Player;
import myweb.secondboard.dto.BoardSaveForm;
import myweb.secondboard.dto.BoardUpdateForm;
import myweb.secondboard.dto.MatchingSaveForm;
import myweb.secondboard.dto.MatchingUpdateForm;
import myweb.secondboard.repository.MatchingRepository;
import myweb.secondboard.repository.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchingService {

  private final MatchingRepository matchingRepository;

  private final PlayerRepository playerRepository;

  public Matching findOne(Long matchingId) {
    return matchingRepository.findOne(matchingId);
  }

  @Transactional
  public Page<Matching> getMatchingList(Pageable pageable) {
    return matchingRepository.findAll(pageable);
  }

  // 매칭 등록
  @Transactional
  public Long addMatching(MatchingSaveForm form, Member member) {
    Matching matching = Matching.createMatching(form, member);
    Player player = Player.createPlayer(matching, member);
    matchingRepository.save(matching);
    playerRepository.save(player);
    return matching.getId();
  }

  @Transactional
  public void deleteById(Long matchingId) {
    matchingRepository.deleteById(matchingId);
  }

  @Transactional
  public void update(MatchingUpdateForm form, Member member) {
    Matching matching = matchingRepository.findOne(form.getId());
    matching.updateMatching(matching, form, member);
  }

  public void increasePlayerNumber(Long matchingId) {
    matchingRepository.increasePlayerNumber(matchingId);
  }

  public void matchingCondtionCheck(Long matchingId) {
    matchingRepository.matchingCondtionCheck(matchingId);
  }
}
