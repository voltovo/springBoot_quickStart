package jpabook.jpashop.api;

import javax.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    /**
     * 회원 등록 v1
     * api 스팩과 엔티티가 같으면 엔티티를 수정하거나
     * api 스팩이 변경된 경우 사이드 이팩트가 발생할 수 있다
     * @param member
     * @return
     */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long joinId = memberService.join(member);
        return new CreateMemberResponse(joinId);
    }

    /**
     * 회원 등록 v2
     * api 스팩을 받는 객체를 하나 만들어서 전달하는 DTO 생성
     * api 스팩이나 엔티티가 변경되어도 대응이 간편하다
     * @param request
     * @return
     */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long joinId = memberService.join(member);

        return new CreateMemberResponse(joinId);
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
