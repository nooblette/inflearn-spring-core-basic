package hello.inflearnspringcorebasic.autowired;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.inflearnspringcorebasic.member.Member;

public class AutowiredTest {

	@Test
	@DisplayName("빈을 나중에(Option) 생성하고 등록하는 과정을 테스트 한다.")
	void AutowiredOption(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);

	}

	// 스프링 컨테이너에 빈을 생성하고 등록하기 위한 클래스
	static class TestBean{
		@Autowired(required = false)
		public void setNoBean1(Member noBean1){ // Member 클래스는 스프링 빈이 아니므로, 스프링 컨테이너에 존재하지 않는다.
			System.out.println("noBean1 = " + noBean1);
		}

		@Autowired
		public void setNoBean2(@Nullable Member noBean2){ // Member 클래스는 스프링 빈이 아니므로, 스프링 컨테이너에 존재하지 않는다.
			System.out.println("noBean2 = " + noBean2);
		}


		@Autowired
		public void setNoBean3(Optional<Member> noBean3){ // Member 클래스는 스프링 빈이 아니므로, 스프링 컨테이너에 존재하지 않는다.
			System.out.println("noBean3 = " + noBean3);
		}
	}
}
