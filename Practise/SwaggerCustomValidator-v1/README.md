# Spring Boot 自訂複合條件 Validator Lab

## Lab 目標

- 學習如何在 Spring Boot 中設計複合條件的自訂 Validator，驗證多個欄位（如生日、年齡、Email domain）是否符合規則。
- 練習將驗證邏輯放在 Request 物件中，並透過 Swagger UI 測試表單提交及回傳狀態。
- 使用單元測試驗證自訂 Validator 的正確性。

---

## 操作步驟

### 步驟 1：建立 Request 物件

```java
@Data
public class UserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @ValidBirthday
    private String birthday; // yyyy-MM-dd
}
```

> 這裡將驗證註解放在 Request class，各參數自動套用。

---

### 步驟 2：建立 Controller

```java
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @ModelAttribute UserRequest request) {
        return ResponseEntity.ok("User registered successfully");
    }
}
```

> 使用 @ModelAttribute 從 UI 或 Swagger 表單綁定參數，驗證自動套用。

---

### 步驟 3：建立自訂 Validator

#### Annotation

```java
@Constraint(validatedBy = BirthdayValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBirthday {
    String message() default "Birthday must be before today and age >= 18";
}
```

#### Validator Class 主要邏輯

```java
public class BirthdayValidator implements ConstraintValidator<ValidBirthday, String> {

    @Override
    public boolean isValid(String birthdayStr, ConstraintValidatorContext context) {
        if (birthdayStr == null || birthdayStr.isEmpty()) return true;
        LocalDate birthday = LocalDate.parse(birthdayStr);
        if (!birthday.isBefore(LocalDate.now()) || Period.between(birthday, LocalDate.now()).getYears() < 18) {
            return false;
        }
        return true;
    }
}
```

> 可以擴展檢查其他複合條件，例如 Email domain。

---

### 步驟 4：配置 Swagger UI

```properties
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

- 啟動專案 → 打開 http://localhost:8080/swagger-ui.html 測試 /user/register API。

---

### 步驟 5：測試案例

**Case 1: 符合條件 → 200 OK**

```
name=Alice
email=alice@example.com
birthday=2000-01-01
```

**Case 2: 違反條件 → 400 Bad Request**

```
name=Bob
email=bob@example.com
birthday=2999-01-01
```

- 回傳訊息示例: `Birthday must be before today and age >= 18`

---

### 步驟 6：單元測試驗證

主要測試片段：

```java
@Test
void validUser_shouldReturnSuccess() {
    // 使用 MockMvc 或 WebTestClient 測試合法資料
}

@Test
void invalidBirthday_shouldReturnError() {
    // 測試生日違規，期望 400
}

@Test
void missingName_shouldReturnError() {
    // 測試 Name 為空，期望 400
}
```

> 查詢如何使用 Spring Boot 測試 API（MockMvc / WebTestClient）

---

## Checklist

- Request 物件各參數驗證註解正確
- 自訂 Validator 能檢查複合條件（生日、年齡、可擴展其他條件）
- Controller 能正確接收 Request 並回傳格式化訊息
- Swagger UI 能顯示 API 並測試各情境
- 單元測試覆蓋合法、違規、缺少欄位情境
- 測試驗證機制成功

---

## 參考資料（請自行查詢並補完細節）

- [Spring Boot + Swagger 3 Example (OpenAPI 3)](https://www.bezkoder.com/spring-boot-swagger-3/)
- [Spring Boot Swagger 中文教學（Blog）](https://chikuwa-tech-study.blogspot.com/2021/07/spring-boot-swagger-openapi-documentation.html)
- [Spring Boot Validation 官方文件](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-validation)
- [SpringDoc OpenAPI 教學](https://springdoc.org/)

---

## Lab 補充

- 請根據主要片段自行查詢並補完細節（如 Swagger 註解、單元測試、專案設定等）。
- 可擴展更多複合條件或共用驗證工具，方便日後套用。
- 完成 checklist 並通過單元測試即表示練習完成。  
