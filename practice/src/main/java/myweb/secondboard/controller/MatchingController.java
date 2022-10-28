package myweb.secondboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myweb.secondboard.domain.Matching;
import myweb.secondboard.domain.Member;
import myweb.secondboard.dto.MatchingSaveForm;
import myweb.secondboard.dto.MatchingUpdateForm;
import myweb.secondboard.service.MatchingService;
import myweb.secondboard.web.SessionConst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingController {

  private final MatchingService matchingService;

  @GetMapping("/home")
  public String home(@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC)
                     Pageable pageable, Model model) {
    Page<Matching> matchingList = matchingService.getMatchingList(pageable);
    int nowPage = matchingList.getPageable().getPageNumber() + 1;
    int startPage = Math.max(nowPage - 4, 1);
    int endPage = Math.min(nowPage + 9, matchingList.getTotalPages());

    model.addAttribute("matchingList", matchingList);
    model.addAttribute("nowPage", nowPage);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);

    return "/matching/matchingHome";
  }

  @GetMapping("/matchingAdd")
  public String matchingAddForm(Model model) {
    model.addAttribute("matching", new MatchingSaveForm());
    return "/matching/matchingAddForm";
  }

  @PostMapping("/new")
  public String matchingAdd(@Validated @ModelAttribute("matching") MatchingSaveForm form,
                            BindingResult bindingResult, HttpServletRequest request) {

    Member member = (Member) request.getSession(false)
      .getAttribute(SessionConst.LOGIN_MEMBER);

    if (bindingResult.hasErrors()) {
      log.info("errors = {}", bindingResult);
      return "/matching/matchingAddForm";
    }

    Long matchingId = matchingService.addMatching(form, member);
    return "redirect:/matching/detail/" + matchingId;
  }

  @GetMapping("/detail/{matchingId}")
  public String matchingDetail(@PathVariable("matchingId") Long matchingId, Model model) {

    Matching matching = matchingService.findOne(matchingId);
    matchingDetailView(matchingId, model, matching);
    return "/matching/matchingDetail";
  }

  private void matchingDetailView(Long matchingId, Model model, Matching matching) {
    model.addAttribute("matching", matching);
  }

  @GetMapping("/delete/{matchingId}")
  public String matchingDelete(@PathVariable("matchingId") Long matchingId) {
    matchingService.deleteById(matchingId);
    return "redirect:/matching/home";
  }

  @GetMapping("/update/{matchingId}")
  public String matchingUpdateForm(@PathVariable("matchingId") Long matchingId, Model model) {
    Matching matching = matchingService.findOne(matchingId);

    MatchingUpdateForm form = new MatchingUpdateForm();
    form.setId(matching.getId());
    form.setTitle(matching.getTitle());
    form.setPlace(matching.getPlace());
    form.setCourtType(matching.getCourtType());
    form.setMatchingDate(matching.getMatchingDate());
    form.setMatchingTime(matching.getMatchingTime());
    form.setMatchingType(matching.getMatchingType());
    model.addAttribute("form",form);

    return "/matching/matchingUpdateForm";
  }

  @PostMapping("/update/{matchingId}")
  public String matchingUpdate(@Validated @ModelAttribute("form")MatchingUpdateForm form,
                            BindingResult bindingResult, HttpServletRequest request,
                            @PathVariable("matchingId") Long matchingId) {

    Member member = (Member) request.getSession(false)
      .getAttribute(SessionConst.LOGIN_MEMBER);

    if (bindingResult.hasErrors()) {
      log.info("errors = {}", bindingResult);
      return "/matching/matchingUpdateForm";
    }

    matchingService.update(form, member);
    return "redirect:/matching/detail/" + matchingId;
  }

}
