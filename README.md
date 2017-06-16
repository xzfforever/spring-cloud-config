# spring-cloud-config
###基本使用步骤：
<li>Config Server端（基于git）
>1、配置git仓库地址
<pre>
spring.cloud.config.server.git.uri=https://github.com/xzfforever/configRepo.git</pre>
>2、启动类上加上注解:@EnableConfigServer
<pre>
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

  public static void main(String[] args) {
	SpringApplication.run(ConfigServerApplication.class, args);
  }
}
</pre>
<li>Config Client端
>1、配置
<pre>
#指定Config Server地址
spring.cloud.config.uri=http://localhost:8888
#指定当前使用的profile变量
spring.cloud.config.profile=dev
#指定git上的分支版本
spring.cloud.config.label=master
spring.application.name=configClient
spring.profiles.active=dev
management.security.enabled=false
</pre>
>2、pom依赖
<pre>
spring-cloud-starter-config
spring-boot-starter-web
spring-boot-starter-actuator
</pre>
>3、要实现动态跟新，动态变量所在类要加上@RefreshScope注解
<pre>
@RestController
@RequestMapping(value = "/config")
@RefreshScope
public class ConfigController {

    @Value("${user.name}")
    private String userName;

    @GetMapping(value="/data")
    public String getUserName(){
        return this.userName;
    }
}
</pre>


