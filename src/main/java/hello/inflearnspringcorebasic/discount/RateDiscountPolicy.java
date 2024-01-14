package hello.inflearnspringcorebasic.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{
	private int discountPercent = 10;
	@Override
	public int discount(Member member, int price) { // command + shift + t : 테스트 코드 생성
		if(member.getGrade() == Grade.VIP){
			return price * discountPercent / 100;
		} else {
			return 0;
		}
	}
}
