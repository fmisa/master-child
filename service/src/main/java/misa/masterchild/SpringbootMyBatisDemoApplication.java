package misa.masterchild;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@MapperScan("misa.masterchild.dao.mybatis.mapper")
@ComponentScan("misa")
@ImportResource("/applicationContext.xml")
@PropertySource("/application.properties")
public class SpringbootMyBatisDemoApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootMyBatisDemoApplication.class, args);
    }
}
