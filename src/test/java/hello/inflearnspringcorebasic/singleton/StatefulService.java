package hello.inflearnspringcorebasic.singleton;

public class StatefulService {
	private int price; // 상태를 유지하는 필드

	public void order(String name, int price){
		System.out.println("name = " + name + " price = " + price);

		// 클라이언트가 주문을 하고 가격 정보를 싱글톤 빈에 저장
		this.price = price; // 문제가 되는 지점
	}

	public int getPrice(){
		return price;
	}
}
