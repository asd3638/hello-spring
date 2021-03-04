package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    //createMemberForm에서 post로 name이 넘어오면 이 set method를 통해서 name이 저장되게 된다.
    public void setName(String name) {
        this.name = name;
    }
}
