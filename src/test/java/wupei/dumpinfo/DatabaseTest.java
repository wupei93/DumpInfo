package wupei.dumpinfo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void getPass() {
        String url = encryptor.encrypt("");
        String name = encryptor.encrypt("");
        String password = encryptor.encrypt("");
        System.out.println("数据库 url: " + url);
        System.out.println("数据库 name: " + name);
        System.out.println("数据库 password: " + password);
    }

}