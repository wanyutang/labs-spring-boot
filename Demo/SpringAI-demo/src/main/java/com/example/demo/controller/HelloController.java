package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Hello API", description = "簡單的問候 API")
public class HelloController {

    @GetMapping("/hello")
    @Operation(summary = "取得問候訊息", description = "返回簡單的問候訊息")
    public String hello() {
        return "Hello, Swagger!";
    }

    @GetMapping("/hello/{name}")
    @Operation(summary = "個人化問候", description = "根據提供的名字返回個人化問候訊息")
    public String helloName(
            @Parameter(description = "使用者名稱", required = true)
            @PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @PostMapping("/greet")
    @Operation(summary = "自訂問候", description = "使用請求體中的資訊創建自訂問候")
    public GreetingResponse greet(@RequestBody GreetingRequest request) {
        return new GreetingResponse("Hello, " + request.getName() + "! " + request.getMessage());
    }

    public record GreetingRequest(String name, String message) {}
    public record GreetingResponse(String greeting) {}
}
