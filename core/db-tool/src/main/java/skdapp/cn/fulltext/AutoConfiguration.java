package skdapp.cn.fulltext;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.**.jpa.**")
@ComponentScan(basePackages = {"skdapp.cn.fulltext.config",
        "com.sunny.boot.dbtool.demo"
})
public class AutoConfiguration {
}
