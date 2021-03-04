package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.jpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//@Service, @Repository 등의 어노테이션 방법과 @Configuration 방법의 차이는 전자는 어노테이션 되면 그 클래스 그대로 사용할 수 밖에 없지만
//이렇게 여기서 다시 명시해주면 return 값을 바꿀 수 있다.
//지금도 등록은 인터페이스인 memberRepository 이름으로 사용하지만 return은 직접 사용되는 repo class를 지정해서 사용한다.

@Configuration
public class SpringConfig {
    //기존에 jdbc쓸 때 사용한 게 datasource임 jpa는 entityManager 사용함.
    /*private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    //스프링 빈 등록을 return class로 지정해준다.
    //jdbc쓰려면 datasource를 무조건 등록을 해야하고

    EntityManager em;

    //의존성 주입
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository() {
       /* return new JdbcTemplateMemberRepository(dataSource);*/
        return new jpaMemberRepository(em);
    }
}
