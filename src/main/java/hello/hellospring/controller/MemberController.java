package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//controller annotation 붙여주는 순간부터 얘는 spring container가 관리하게 되고 객체가 생성되게 된다. 이를 spring bean 이라고 부른다.
@Controller
public class MemberController {
    //의존성이 있기 때문에 얘는 기본적인 로직에 해당하는 memberservice class를 가져다 써야해
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {

        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        System.out.println("member = " + member.getName());

        //service에 있는 메소드 사용하려고 등록했지
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list (Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    //html파일로 갈 때 service에 등록되어 있던 member 정보를 list형태로 담아서 members란 이름으로 보내준다.
    return "members/memberList";
    }

}
