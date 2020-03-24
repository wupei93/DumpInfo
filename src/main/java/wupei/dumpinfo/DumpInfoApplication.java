package wupei.dumpinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wup10
 */
@SpringBootApplication
@MapperScan("**.dao.**")
public class DumpInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DumpInfoApplication.class, args);
	}

}
