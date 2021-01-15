package cn.com.djin.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * author:快乐风男
 * time:20:24
 */
@SpringBootApplication(scanBasePackages = "cn.com.djin.springboot.*")
@MapperScan(basePackages = "cn.com.djin.springboot.mapper")
public class StartApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        //关闭热部署
        // System.setProperty("spring.devtools.restart.enabled", "false");
        //启动此项目
        SpringApplication.run(StartApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(this.getClass());
    }
}
