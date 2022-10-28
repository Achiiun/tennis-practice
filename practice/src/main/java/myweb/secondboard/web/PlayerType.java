package myweb.secondboard.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerType implements PlayerMapperType{

  PARTICIPANTS("참여자"),
  CONSTRUCTOR("생성자");

  @Getter
  private final String title;

//  @Getter
//  private final String code;

}
