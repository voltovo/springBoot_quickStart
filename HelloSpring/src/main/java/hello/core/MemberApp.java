package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member suman = new Member(1L, "suman", Grade.VIP);
        memberService.join(suman);

        Member findMember = memberService.findMember(suman.getId());
        System.out.println("suman = " + suman);
        System.out.println("findMember = " + findMember);
    }
}
