package net.wanho;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.MD5;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class ApplicationTests {

    @Test
    public void test2(){
        String pass="$2a$10$ZLW5s40xHxsn.JcrQoknv.Js/mdG49TCxqbxa09aL2.mYx.FW94Ai";
        System.out.println(BCrypt.checkpw("admin",pass));
    }

    @Test
    void contextLoads() {
        // bcrypt 加密

        for(int i=0;i<5;i++){
            String admin = BCrypt.hashpw("admin");
            System.out.println( admin);
        }

        // md5
        for(int i=0;i<5;i++){
            String admin = MD5.create().digestHex("admin");
            System.out.println( admin);
        }


    }

}
