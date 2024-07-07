### HHH

[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)

HHH组件名字含义为Healthy、Happiness、Handsome

[GitHub - 970263611/hhh: 中文：仿feign。English：like feign](https://github.com/970263611/hhh)

你一定听说过微服务吧，微服务之间的纽带就是服务间的调用组件。Dubbo体系用dubbo，spring cloud体系用feign。dubbo我仿写过源代码在

[GitHub - 970263611/rpc: 自己编写的rpc调用框架，配置简单，使用更简单，如果您感觉有用请点赞，纯个人手写](https://github.com/970263611/rpc)

如今我又对http协议的openfeign进行了仿写（组件亦可支持其他协议）。首先是支持了openfeign的主流功能，整合了spring mvc。其次我根据springmvc对于参数解析自动匹配到变量名这个能力，移植到了我写的hhh组件上了，但是由于是面向接口调用，所以需要jdk支持1.8+，且添加了编译参数-parameters参数。如果没有添加编译参数可以通过@RequestParam修饰来传值（这个就是feign目前的实现方案）。组件默认采用jackson为编码/解码器，okhttp为网络请求组件，注册中心默认实现为nacos。

### 使用须知

下载源码，打包引入

```
<dependency>
    <groupId>com.dahuaboke</groupId>
    <artifactId>hhh</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 使用配置

必须配置参数

```properties
# @Hhh注解标注的类路径，如果不配置默认和SpringbootApplication启动类路径及子路径
hhh.package.scanner=com.dahuaboke.hhh
# 服务间调用是否开启https,默认为false
hhh.enableHttps=true
# 一次调用整体超时时间（单位毫秒），默认为5000毫秒
hhh.requestTimeout=10000
# 网络请求连接超时时间（单位毫秒），默认为2000毫秒
hhh.socketConnectTimeout=3000
# 网络请求报文读取超时时间（单位毫秒），默认为2000毫秒
hhh.socketReadTimeout=5000
```

### 组件使用

在接口上添加@Hhh注解，并填写对应参数，填写name则默认为需要负载均衡，反之则直接调用

```java
@Hhh(name = "consumer")
public interface consumer {
    
    @RequestMapping("/count")
    void getCount(@RequestParam("id") String id)
        
    @PostMapping("/user")
    User getUser(@RequestParam("id") String id)
        
    @GetMapping("/users")
    List<User> getUserList(String group)
        
}
```

```java
@Hhh(url = "localhost:8080")
public interface consumer {
    
    @RequestMapping("/count")
    void getCount(@RequestParam("id") String id)
        
    @PostMapping("/user")
    User getUser(@RequestParam("id") String id)
        
    @GetMapping("/users")
    List<User> getUserList(String group)
        
}
```

添加拦截器，可以自定义调整header，具体内容请查看SocketContext类

```java
public class MyHook extends AbstractHook {
    
    @Override
    public void beforeSendRequest(SocketContext socketContext) {
        Map<String,String> headers = socketContext.getHeaders();
        headers.put("myHeader","自定义header值");
    }
}
```

#### 支持扩展点

自定义编码解码器

```java
@Bean
public CodecConverter encoderAndDecoder() {
    return new XXX();
}
```

自定义网络请求组件（可http，亦可其他协议）,亦需实现SocketAdapter 接口编写请求适配器

```java
@Bean
public SocketClient socketClient() {
    return new XXX();
}

@Bean
public RequestFactory requestFactory() {
    return new XXX();
}
```

使用其他注册中心

```java
@Bean
public LoadBalancer loadBalancer() {
	return new XXX();
}
```

大花出品，必属精品

[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)
