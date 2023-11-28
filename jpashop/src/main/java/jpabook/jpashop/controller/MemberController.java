package jpabook.jpashop.controller;

import javax.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 정보 입력 창 호출
     * @param model
     * @return
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    /**
     * 회원 정보 저장
     * @param form
     * @return
     */
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form) {
        // 주소
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        // 회원
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        // 회원 저장
        memberService.join(member);

        return "redirect:/";
    }
}
