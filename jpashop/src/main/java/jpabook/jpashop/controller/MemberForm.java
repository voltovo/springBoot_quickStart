package jpabook.jpashop.controller;

import javax.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    private String street;
    private String city;
    private String zipcode;

    public Address createAddress() {
        return new Address(this.city, this.street, this.zipcode);
    }
}
