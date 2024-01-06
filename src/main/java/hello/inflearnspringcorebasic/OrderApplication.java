package hello.inflearnspringcorebasic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.order.OrderService;
import hello.inflearnspringcorebasic.order.Order;

public class OrderApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

		Long memberId = 1L;
		Member member = new Member(memberId, "nooblette", Grade.VIP);
		memberService.join(member); // member 저장

		Order order = orderService.createOrder(memberId, "itemA", 10000); // 주문 생성
		System.out.println("order = " + order);
		System.out.println("order.calculatePrice = " + order.calculatePrice());
	}
}
