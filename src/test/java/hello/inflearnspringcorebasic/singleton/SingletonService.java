package hello.inflearnspringcorebasic.singleton;

public class SingletonService {
	// 자기 자신의 인스턴스를 내부에서 static(바이트코드 파일(.class)에 내용이 저장며, JVM method 영역의 static 영역에 생성된다)으로 갖는다.
	private static final SingletonService instance = new SingletonService();

	public static SingletonService getInstance() {
		return instance;
	}

	private SingletonService(){

	}

	public void logic(){
		System.out.println("싱글톤 객체 로직 호출");
	}
}
