package hello.inflearnspringcorebasic.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
	private String url;

	public NetworkClient() {
		System.out.println("생성자 호출, url = " + url);
		connect();
		call("초기화 연결 메시지");
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// 서비스 시작시 호출
	public void connect() {
		System.out.println("connect to " + url);
	}

	// 네트워크 연결이 된 상태에서 호출
	public void call(String message) {
		System.out.println("call to " + url + ", message = " + message);
	}

	// 서비스 종료시 호출
	public void disconnect() {
		System.out.println("close");
	}

	@PostConstruct
	public void init() throws Exception {
		System.out.println("\n빈 생성 및 의존관계 주입 후 NetworkClient.init() 호출");
		connect();
		call("초기화 연결 메시지");
	}

	@PreDestroy
	public void close() throws Exception {
		System.out.println("\n스프링 종료 및 빈 소멸 전 NetworkClient.close() 호출");
		disconnect();
	}
}
