package hello.inflearnspringcorebasic.scope;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PrototypeProviderTest {

	@Test
	@DisplayName("싱글톤 빈에서 프로토타입 빈을 사용하는 경우 새로운 프로토타입 빈을 생성하기 위해 ObjectProvider 클래스에  요청한다.")
	void providerTest(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			PrototypeBean.class, ClientBean.class);

		PrototypeBean prototypeBean1 = applicationContext.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = applicationContext.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Scope("singleton") // 생략 가능
	static class ClientBean {
		@Autowired
		private ObjectProvider<PrototypeBean> prototypeBeanProvider;

		public int logic(){
			// ObjectProvider Wrapper 클래스가 대신 프로토타입 스코프 빈에 대한 의존관계를 찾아(DL, Dependency Lookup) 준다.
			PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
			System.out.println("prototypeBean = " + prototypeBean); // logic() 메서드 호출마다 다른 인스턴스 참조값 츌력
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
