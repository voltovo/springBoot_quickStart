package hello.servelt.web.frontController.v3.controller;

import hello.servelt.domain.member.Member;
import hello.servelt.domain.member.MemberRepository;
import hello.servelt.web.frontController.ModelView;
import hello.servelt.web.frontController.v3.ControllerV3;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다.
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;
    }
}
