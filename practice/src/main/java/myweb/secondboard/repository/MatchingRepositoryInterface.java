package myweb.secondboard.repository;

import myweb.secondboard.domain.Matching;

import java.util.List;

public interface MatchingRepositoryInterface {

  List<Matching> findAll();

  Matching findOne(Long matchingId);
}
