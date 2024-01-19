package hello.inflearnspringcorebasic.scope;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Test
	@DisplayName("싱글톤에서 프로토타입 빈을 사용하는 경우 프로토타입 스코프의 빈을 사용할때마다 새로 생성되는지 count 필드 값으로 테스트 한다.")
	void singleTonClientUsePrototype(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			ClientBean.class, PrototypeBean.class); // 두개의 스프링 구성설정 클래스를 AnnotationConfigApplicationContext 대상으로 등록
		ClientBean clientBean1 = applicationContext.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = applicationContext.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(2);

	}

	@Scope("singleton") // 생략 가능
	static class ClientBean {
		private final PrototypeBean prototypeBean;

		@Autowired // 생성자가 1개면 @Autowired 어노테이션은 생략 가능
		public ClientBean(PrototypeBean prototypeBean){
			this.prototypeBean = prototypeBean;
		}

		public int logic(){
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}
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
