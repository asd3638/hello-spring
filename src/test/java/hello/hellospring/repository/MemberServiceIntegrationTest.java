package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//원래의 test는 스프링 구동 없이 하는 test였고 이 어노테이션이 있으면 실제로 spring이 동작하면서 테스트 하기 때문에 db가 정상적으로 연결되어서 작동하는지도 확인할 수 있다.
@SpringBootTest

//테스트인데 DB에 반영해버리면 안되잖아 commit만 하고 실제로 db에 반영은 안하는 채로 테스트 할 수 있게 하는게 transaction 어노테이션이다.
//aftereach 이런거 메소드 만들어서 db데이터 지울 필요가 없어지는 것이다.
@Transactional
class MemberServiceIntegrationTest {
    //MemoryMemberRepository 랑 다른 점은 얘는 스프링 구동을 함께 한다는 점이다.
    //스프링 bean에 미리 등록을 해두고
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    //서비스랑 리포를 둘 다 확인해야해
    @Test
    @Commit
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}