package hello.inflearnspringcorebasic.order;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.inflearnspringcorebasic.discount.FixDiscountPolicy;
import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberRepository;
import hello.inflearnspringcorebasic.member.MemoryMemberRepository;

class OrderServiceImplTest {

	// @Test
	// @DisplayName("순수 자바 코드로 테스트 코드를 작성할 때, 수정자 주입이 문제가 되는 경우(의존관계 설정 누락)를 확인한다.")
	// void createOrderBySetterInjection(){
	// 	OrderServiceImpl orderService = new OrderServiceImpl();
	// 	orderService.createOrder(1L, "itemA", 10000);
	// }

	@Test
	@DisplayName("순수 자바 코드로 테스트 코드를 작성할 때, 생성자 주입을 권장하는 이유(컴파일 시점에 의존관계 설정 누락을 체크)를 확인한다.")
	void createOrderByConstrutorInjection(){
		// given
		MemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "name", Grade.VIP));

		// 의존관계 주입을 누락하지 않고 테스트용 객체를 생성하여 주입
		OrderService orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());

		// when
		Order order = orderService.createOrder(1L, "itemA", 10000);

		// then
		assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

}