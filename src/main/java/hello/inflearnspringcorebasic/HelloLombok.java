package hello.inflearnspringcorebasic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HelloLombok {
	
	private String name;
	private int age;

	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok();
		
		helloLombok.setName("name");
		System.out.println("helloLombok.getName() = " + helloLombok.getName());
		System.out.println("helloLombok = " + helloLombok);
	}
}
