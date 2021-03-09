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
//기본편에서 배운 것 처럼 사실 이건 controller 기능이 추가적으로 들어간 component어노테이션과 같다.
//이 어노테이션을 해주면 componentscan돌면서 스프링 컨테이너에 빈 클래스 등록한다.
//순서를 굳이 따지면 bean에 먼저 등록되고 의존성 연결이 된다.
//얘는 지금 MemberService 클래스와 의존성 관계가 있고
//생성자 주입으로 의존성 연결이 되어있다.
@Controller
public class BoardController {
    //의존성이 있기 때문에 얘는 기본적인 로직에 해당하는 memberservice class를 가져다 써야해
    //자바 다형성 이용해서 일단 선언은 MemberService 인터페이스로 해두고
    //실제로 객체 생성할 때에야 진짜 값 넣어주는데 이건 스프링 빈으로 등록하면 스프링이 자동으로 해준다.

    @GetMapping("board/new")
    public String loginForm() {
        return "board/createBoardForm";
    }

    @GetMapping("board")
    public String broardList(MemberForm memberForm) {
        return "board/boardList";
    }
}
