package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//controller annotation 붙여주는 순간부터 얘는 spring container가 관리하게 되고 객체가 생성되게 된다. 이를 spring bean 이라고 부른다.
@Controller
@RequiredArgsConstructor
public class MemberController {
    //의존성이 있기 때문에 얘는 기본적인 로직에 해당하는 memberservice class를 가져다 써야해
    //근데 이런 식으로 생성자 주입으로 의존성 주입하는 과정이 너무 귀찮음
    //그래서 롬복 사용
    //롬복을 사용하면 final이라고 명시된 스프링 빈에 등록된 클래스에 한해서
    //생성자 생성을 자동으로 해준다.
    //여기서는 당연히 이 아래 메소드겠지.
    //final 을 생성자 주입에서 사용할 수 있는 이유 까먹지 말자
    //방법은 필수인자생성자라는 뜻의 RequiredArgsConstructor 써주면 된다.
    private final MemberService memberService;

//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("members/new")
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

    @GetMapping("members/login")
    public String loginForm() {
        return "members/loginMemberForm";
    }

    @GetMapping("members")
    public String list (Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    //html파일로 갈 때 service에 등록되어 있던 member 정보를 list형태로 담아서 members란 이름으로 보내준다.
    return "members/memberList";
    }

}
