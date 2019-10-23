package cn.innovationai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author innovationai
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("cn.innovationai.*.mapper")
public class DaojiaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DaojiaoApplication.class, args);
    }
}