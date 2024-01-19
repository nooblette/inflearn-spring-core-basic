package hello.inflearnspringcorebasic.scope;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

	@Test
	@DisplayName("스프링 프로토타입 빈이 매번 새로운 인스턴스를 생성하여 반환하는지 count 필드 값으로 테스트 한다.")
	void protoTypeFind(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			PrototypeBean.class);

		PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;
		public void addCount(){
			count++;
		}

		public int getCount(){
			return count;
		}

		@PostConstruct
		public void init(){
			// 프로토타입 빈에서 PostConstruct 어노테이션이 붙은 메서드(초기화 콜백)는 호출 된다.
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void destroy(){
			// 프로토타입 빈에서 PreDestroy 어노테이션이 붙은 메서드(소멸 전 콜백)는 호출이 되지 않는다.
			System.out.println("PrototypeBean.destroy " + this);
		}
	}
}
