package hello.inflearnspringcorebasic.beanfind;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.inflearnspringcorebasic.member.MemberRepository;
import hello.inflearnspringcorebasic.member.MemoryMemberRepository;

class ApplicationContextSameBeanFindTest {
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DuplicatedBeanConfig.class);

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류 발생")
	void findBeanByDuplicatedType(){
		// then - 아래 메서드를 호출했을때 예외가 발생해야 테스트 성공
		assertThrows(NoUniqueBeanDefinitionException.class,
			() -> applicationContext.getBean(MemberRepository.class));
	}

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정")
	void findBeanByName(){
		// when
		MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

		// then
		assertThat(memberRepository).isInstanceOf(MemberRepository.class);
	}

	@Test
	@DisplayName("특정 타입의 빈을 모두 조회하기")
	void findAllBeanByType(){
		// getBeansOfType() 메서드는 key - value 쌍을 반환
		Map<String, MemberRepository> beansOfType = applicationContext.getBeansOfType(MemberRepository.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + ", value = " + beansOfType.get(key));
		}

		System.out.println("beansOfType = " + beansOfType);

		// then
		assertThat(beansOfType.size()).isEqualTo(2);
	}

	@Configuration
	// 해당 클래스의 scope은 ApplicationContextSameBeanFindTest 클래스 내부로 제한
	static class DuplicatedBeanConfig {
		@Bean
		public MemberRepository memberRepository() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberRepository duplicatedMemberRepository() {
			return new MemoryMemberRepository();
		}
	}
}
