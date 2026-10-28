package net.wanho;

import org.junit.jupiter.api.Test;



enum Gender {
    MALE("男"), FEMALE("女");

    private String gender;
    Gender(String gender){
        this.gender=gender;
    }

    public String gender() {
        return gender;
    }
}

//@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
        Gender gender = Gender.MALE;
        System.out.println(gender.gender());
    }

}
