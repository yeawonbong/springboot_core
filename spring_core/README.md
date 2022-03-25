# 스프링을 이용한 DI
## 스프링으로 전환하기
1. AppConfig에 설정을 구성한다는 뜻의  `@Configuration` 을 붙여준다.  
2. 각 메서드에  @Bean 을 붙여준다. (이렇게 하면 스프링 컨테이너에 스프링 빈으로 등록된다.)
## 스프링 컨테이너 적용
스프링은 모두 `ApplicationContext`에서 시작, 스프링 컨테이너라고 보면 된다.  
얘가 모든 객체들(Bean들)을 관리해준다.  
생성할 때 `AnnotationConfigApplicationContext(AppConfig.class)`는 AppConfig 안의 애노테이션 설정들을 기반으로 Config한 것을 가져온다는 뜻.  
환경설정 정보를 갖고 그 Bean들을 객체생성한 것들을 Spring Container에 다 집어넣어서 관리해준다.  
기존에는 직접 AppConfig에서 찾아왔다면, 이제는 스프링 컨테이너를 통해서 찾아와야 한다.  
```java
MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
```
기본적으로 메서드 이름으로 Bean이 등록되기 때문에, 불러올 때 메서드 이름 문자열로 찾아온다. 두번째 인자는 타입을 적어준다.  
실행하면 기존과 다른 점은 로그가 쫙 찍힌다는 것. `Creating shared instance of singleton bean` -> @Bean 객체가 등록되는 과정  
Key 는 저 문자열 (메서드 이름), Value는 객체 인스턴스로 해서 매핑되어 스프링 컨테이너에 등록되는 과정인 것이다.  
그래서 꺼낼 때 위에서처럼 Key값(문자열) + 타입으로 꺼내면 된다.
## 스프링 컨테이너
- 기존에는 개발자가 `AppConfig` 를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈 객체를 사용한다.
- 스프링 컨테이너는 `@Configuration`이 붙은 `AppConfig`를 설정(구성)정보로 사용한다. 여기서 `@Bean`이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 스프링 컨테이너에 등록된 객체를 **스프링빈**이라고 한다.
- 스프링 빈은 `@Bean`이 붙은 메서드명을 스프링 빈의 이름으로 사용한다.
  - 예제에서 `memberService`, `orderService`
- 이전에는 개발자가 필요한 객체를 `AppConfig`를 사용하여 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해 필요한 스프릴 빈(객체)를 찾아야 한다.
  - 스프링빈은 `applicationContext.getBean()` 메서드를 사용해서 찾을 수 있다.
- 기존에는 개발자가 직접 자바 코드로 모든 것을 했다면, 이제부터는 **스프링 컨테이너**에 객체를 **스프링 빈**으로 등록하고, **스프링 컨테이너**에서 스프링 빈을 찾아서 사용하도록 변경되었다.