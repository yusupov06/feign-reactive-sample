package sample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableReactiveFeignClients
@EnableFeignClients
@RequiredArgsConstructor
public class FeignApplication {


    private final GreetingReactive reactiveFeignClient;

    private final GreetingReactiveWithUrl reactiveFeignClientWithUrl;

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

    @GetMapping("/greetingReactive")
    public Mono<String> greetingReactive() {
        return reactiveFeignClient.greeting().map(s -> "reactive feign! : " + s);
    }

    @GetMapping("/greetingReactiveWithUrl")
    public Mono<String> greetingReactiveWithUrl() {
        return reactiveFeignClientWithUrl.greeting().map(s -> "reactive feign with url! : " + s);
    }

}
