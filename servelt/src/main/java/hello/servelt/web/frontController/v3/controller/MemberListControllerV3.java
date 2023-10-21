package hello.servelt.web.frontController.v3.controller;

import hello.servelt.domain.member.Member;
import hello.servelt.domain.member.MemberRepository;
import hello.servelt.web.frontController.ModelView;
import hello.servelt.web.frontController.v3.ControllerV3;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findByAll();
        //Model에 데이터를 보관한다.
        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
