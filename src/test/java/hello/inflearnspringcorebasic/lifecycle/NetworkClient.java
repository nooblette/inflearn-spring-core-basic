package hello.inflearnspringcorebasic.lifecycle;

import net.minidev.json.JSONUtil;

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
	public void connect(){
		System.out.println("connect to " + url);
	}

	// 네트워크 연결이 된 상태에서 호출
	public void call(String message){
		System.out.println("call to " + url + ", message = " + message);
	}

	// 서비스 종료시 호출
	public void disconnect(){
		System.out.println("close");
	}

}
