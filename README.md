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
###低端使用步骤
<pre>
1、启动Server、Client
2、访问测试方法
3、更改git分支上文件信息
4、访问Config Server(如http://localhost:8888/configClient/dev/master),查看更新后的内容信息
5、向Config Client发送Post请求（/refresh）,js脚本见下面
</pre>







<li>JS脚本sendPostRequest.js
<pre>
var http = require('http');

var querystring = require('querystring');

var options = {
	host:'127.0.0.1',
 	port:9999,
	path:'/refresh',
	method:'POST'   
};

var req = http.request(options,function(res){
	console.log('STATUS:'+res.statusCode);
	console.log('HEADERS:'+JSON.stringify(res.headers));
	res.setEncoding('utf8');
	res.on('data',function(chunck){
		console.log('BODY:'+chunck);
	});
});

req.end();

</pre>