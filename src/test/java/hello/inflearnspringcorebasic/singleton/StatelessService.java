package hello.inflearnspringcorebasic.singleton;

public class StatelessService {
	public int order(String name, int price){
		System.out.println("name = " + name + " price = " + price);

		// 클라이언트가 주문을 하고 가격 정보를 반환
		return price;
	}
}
