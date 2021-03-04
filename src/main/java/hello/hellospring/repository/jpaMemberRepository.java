package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class jpaMemberRepository implements MemberRepository{

    //스프링 부트가 자동으로 만들어준다.
    //JPA 쓰면 이거 자동으로 주입받는다고 생각하면 된다.
    private final EntityManager em;

    @Autowired
    public jpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //persist는 영속적이라는 뜻으로 간단하게 저장해준다고 생각하면 된다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        //조회의 대상이 되는 class랑 식별자 넣어주면 된다.
        return Optional.ofNullable(member);
        //Optional은 null일때 특정한 값을 반환하도록 설정되어 있어서 처리를 따로 할 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;

    }
}
