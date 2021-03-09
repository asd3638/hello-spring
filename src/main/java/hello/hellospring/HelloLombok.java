package hello.hellospring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        //자바에서 기본인게 메소드는 public static 으로 선언하고 그 안에 private으로 선언한 class내부 보호 변수들을 맘대로 가져다 사용할 수 없어.
        //자바 캡슐화
        //그래서 사용하는게 getter setter메소드
        //근데 스프링에서는 롬복 사용해서 getter setter 자동으로 생성할 수 있다.
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("안녕하세요");
        helloLombok.setAge(24);
        System.out.println("helloLombok = " + helloLombok);
    }
}
