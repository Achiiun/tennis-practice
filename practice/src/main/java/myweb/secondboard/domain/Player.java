package myweb.secondboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myweb.secondboard.dto.MatchingSaveForm;
import myweb.secondboard.web.MatchingType;
import myweb.secondboard.web.PlayerType;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private PlayerType playerType;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "matching_id")
  private Matching matching;


  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

}
