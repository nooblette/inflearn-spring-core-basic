package hello.inflearnspringcorebasic.scope;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PrototypeTest {

	@Test
	@DisplayName("빈이 프로토타입 스코프를 따르는 경우를 테스트 한다.")
	void protoTypeBeanFind(){
		// given
		// AnnotationConfigApplicationContext 생성자에 매개변수로 스프링 구성 설정 정보 클래스(e.g. PrototypeBean.class)를 지정해주면
		// 해당 스프링 구성 설정 정보 클래스는 @Component 어노테이션이 없어도 컴포넌트 스캔 대상처럼 동작해서 스프링 컨테이너에 등록된다.
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			PrototypeBean.class);

		// when
		PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
		System.out.println("prototypeBean1 = " + prototypeBean1);

		PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);
		System.out.println("prototypeBean2 = " + prototypeBean2);

		// then
		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
		applicationContext.close(); //종료
	}

	@Scope("prototype")
	static class PrototypeBean {
		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy");
		}
	}
}
