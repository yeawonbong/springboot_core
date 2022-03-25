package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*AppConfig는 애플리케이션의 실제 동작에 필요한 구현객체를 생성한다.*/

@Configuration //애플리케이션의 구성정보, 설정정보
public class AppConfig {

    @Bean // 각 메서드에 Bean을 적어주면, Spring Container에 등록이 된다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); // 이 부분만 고치면 갈아낄 수 있다.
        return new RateDiscountPolicy();
    }
}

//역할과 구현 분리