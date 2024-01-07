package hello.inflearnspringcorebasic.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.inflearnspringcorebasic.AppConfig;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName(){
		// when
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

		// then
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("빈 타입으로만 조회")
	void findBeanByType(){
		// when
		MemberService memberService = applicationContext.getBean(MemberService.class);

		// then
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}


	@Test
	@DisplayName("빈 이름과 구체 타입으로 조회")
	void findBeanBysNameAndImplementClas(){
		// when - 구현에 의존하기 때문에 별로 좋은 코드는 아니다. (DIP 위반)
		MemberService memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);

		// then
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("빈 이름으로 조회 실패")
	void findBeanByNameX(){
		// then - assertThrows() 메서드에 전달한 예외가 발생해야 테스트 성공
		assertThrows(NoSuchBeanDefinitionException.class,
			() -> applicationContext.getBean("XXXXX", MemberService.class));
	}
}
