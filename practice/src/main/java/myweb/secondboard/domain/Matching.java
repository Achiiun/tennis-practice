package myweb.secondboard.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myweb.secondboard.dto.BoardSaveForm;
import myweb.secondboard.dto.BoardUpdateForm;
import myweb.secondboard.dto.MatchingSaveForm;
import myweb.secondboard.dto.MatchingUpdateForm;
import myweb.secondboard.web.CourtType;
import myweb.secondboard.web.MatchingType;
import myweb.secondboard.web.PlayerType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Matching {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "matching_id")
  private Long id;

  @NotNull
  @Column(length = 11)
  private String author;   //member nickname

  @NotNull
  @Column(length = 31)
  private String title;

  @NotNull
  @Column(length = 145)
  private String place;

  @NotNull
  @Column(length = 40)
  private LocalDate matchingDate;

  @NotNull
  @Column(length = 40)
  private LocalTime matchingTime;


  @CreatedDate
  @Column(length = 40)
  private String createdDate;

  @LastModifiedDate
  @Column(length = 40)
  private String modifiedDate;

//  @NotNull
//  @Column(length = 40)
//  private PlayerType playerType;

  @Enumerated(EnumType.STRING)
  private CourtType courtType;

  @Enumerated(EnumType.STRING)
  private MatchingType matchingType;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  public static Matching createMatching(MatchingSaveForm form, Member member) {
    Matching matching = new Matching();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    matching.setTitle(form.getTitle());
    matching.setPlace(form.getPlace());
    matching.setAuthor(member.getNickname());
    matching.setMatchingDate(form.getMatchingDate());
    matching.setMatchingTime(form.getMatchingTime());
    matching.setCreatedDate(LocalDateTime.now().format(dtf));
    matching.setCourtType(form.getCourtType());
    matching.setMatchingType(form.getMatchingType());
    matching.setMember(member);
    return matching;
  }

  public void updateMatching(Matching matching, MatchingUpdateForm form, Member member) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    matching.setId(form.getId());
    matching.setTitle(form.getTitle());
    matching.setPlace(form.getPlace());
    matching.setAuthor(member.getNickname());
    matching.setMatchingDate(form.getMatchingDate());
    matching.setMatchingTime(form.getMatchingTime());
    matching.setCourtType(form.getCourtType());
    matching.setMatchingType(form.getMatchingType());
    matching.setModifiedDate(LocalDateTime.now().format(dtf));
    matching.setMember(member);

  }
}
