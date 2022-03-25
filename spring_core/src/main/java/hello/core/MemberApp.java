package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        /*
        * 스프링은 모두 ApplicationContext에서 시작, 스프링 컨테이너라고 보면 된다.
        * 얘가 모든 객체들(Bean들)을 관리해준다.
        * 생성할 때 AnnotationConfigApplicationContext(AppConfig.class)는 AppConfig 안의 애노테이션 설정들을 기반으로 Config한 것을 가져온다는 뜻.
        * 환경설정 정보를 갖고 그 Bean들을 객체생성한 것들을 Spring Container에 다 집어넣어서 관리해준다.
        * 기존에는 직접 AppConfig에서 찾아왔다면, 이제는 스프링 컨테이너를 통해서 찾아와야 한다.
        *
        * 기본적으로 메서드 이름으로 Bean이 등록되기 때문에, 불러올 때 메서드 이름 문자열로 찾아온다. 두번째 인자는 타입을 적어준다.
        */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

        /*
        * 실행하면 기존과 다른 점은 로그가 쫙 찍힌다는 것. Creating shared instance of singleton bean -> @Bean 객체가 등록되는 과정
        * Key 는 저 문자열 (메서드 이름), Value는 객체 인스턴스로 해서 매핑되어 스프링 컨테이너에 등록되는 과정인 것이다.
        * 그래서 꺼낼 때 위에서처럼 Key값(문자열) + 타입으로 꺼내면 된다.
        */
    }
}
