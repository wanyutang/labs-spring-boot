# Spring Boot - Swagger Labs

本 Lab 教你如何從零開始建置一個 Spring Boot 專案，整合 Swagger/OpenAPI，並示範 API 參數驗證、文件註解等實務技巧。

---

## Lab 流程

### 1. Spring Boot Init 專案

- 前往 [Spring Initializr](https://start.spring.io/)。
- 選擇 Java、Spring Boot 版本，填入 Group、Artifact 。
- 加入必要依賴：`Spring Web`。
- 點擊「Generate」下載並解壓，取得基本專案 layout。

---

### 2. 引入 Swagger/OpenAPI 套件

- 在 `pom.xml` 加入依賴（以下擇一）：

> Swagger 3 (springdoc-openapi)
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-ui</artifactId>
  <version>1.7.0</version>
</dependency>
```
> Swagger 2（非推薦，僅供參考）
```xml
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-boot-starter</artifactId>
  <version>3.0.0</version>
</dependency>
```

---

### 3. 設定 Swagger/OpenAPI Config

> 主要片段（Springdoc OpenAPI 3）
```java
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Demo API").version("1.0").description("Swagger Lab"));
    }
}
```
> 參考 [Spring Boot + Swagger 3 Example (OpenAPI 3)](https://www.bezkoder.com/spring-boot-swagger-3/)。

---

### 4. 建置 Controller / Req / Res 類別

> Controller 主要片段
```java
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Operation(summary = "查詢狀態", description = "根據前端傳入狀態進行驗證")
    @PostMapping("/status")
    public ResponseEntity<StatusRes> checkStatus(@Valid @RequestBody StatusReq req) {
        // 驗證邏輯略
        return ResponseEntity.ok(new StatusRes(true, req.getStatus()));
    }
}
```

> Req 類別（加上 Swagger 註解、驗證註解）
```java
public class StatusReq {
    @Schema(description = "狀態名稱（ACTIVE/INACTIVE/SUSPENDED）", required = true)
    @NotBlank(message = "status 不可為空")
    private String status;
    // getter, setter
}
```

> Res 類別
```java
public class StatusRes {
    private boolean success;
    private String status;
    // getter, setter, constructor
}
```

---

### 5. 加入參數驗證註解

- 使用 javax validation 註解（如 @NotBlank、@Pattern、@Size）。
- 如需自訂 enum name 驗證，可參考上一個 Lab 或設計自己的 ConstraintValidator。

---

### 6. Swagger 文件註解補充

- 用 `@Operation`, `@Parameter`, `@Schema` 註解描述 API、參數、回應。
- Controller 類別與方法加上 `@Tag`、`@Operation`。
- DTO 欄位加上 `@Schema`。

---

### 7. 驗證與練習

- 啟動 Spring Boot，瀏覽 `/swagger-ui/index.html` 或 `/swagger-ui.html`。
- 測試 API，嘗試傳送正確/錯誤參數（如 status 欄位為空、亂填）。
- 檢查 Swagger UI 是否正確顯示文件、參數格式、驗證訊息。

---

## Checklist

- [ ] 成功用 Spring Initializr 建立專案並引入 Swagger 套件
- [ ] 完成 Swagger/OpenAPI 設定
- [ ] Controller、Req、Res 實作並加上必要註解
- [ ] 參數驗證註解完善，能正確攔截格式錯誤
- [ ] Swagger 文件註解補齊，UI 顯示完整
- [ ] 驗證流程通過，API 能正確回應各種情境

---

## 參考資源

- [Spring Boot + Swagger 3 Example (OpenAPI 3)](https://www.bezkoder.com/spring-boot-swagger-3/)
- [SpringDoc OpenAPI 官方文件](https://springdoc.org/)
- [Spring Boot Swagger 中文教學（Blog）](https://chikuwa-tech-study.blogspot.com/2021/07/spring-boot-swagger-openapi-documentation.html)

---

> 本 Lab 僅列主要片段，請練習者查詢官方資源完成細節並動手實作！