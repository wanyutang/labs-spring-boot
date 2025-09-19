# Spring Boot 單元測試（Unit Test）練習指南

🎯 **目標：**
- 建立空的 Spring Boot 專案
- 建立簡單的 Component / Service
- 寫單元測試驗證 Service 功能

---

## Step 0. 前置準備

專案採用 [Maven](https://maven.apache.org/) 作為建構工具，請在 `pom.xml` 加入 Spring Boot Starter 與測試相關套件：

```xml
<dependencies>
    <!-- Spring Boot Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!-- Spring Boot 測試套件 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    
</dependencies>
```
> 測試套件統一採用 `spring-boot-starter-test`，所有依賴皆由 Maven 管理。

---

## Step 1. 建立簡單的 Request & Response DTO

```java
// Request DTO
public class UserRequest {
    private String name;
    private int birthYear;
    // getter/setter
}
```

```java
// Response DTO
public class UserResponse {
    private String name;
    private int age;
    // getter/setter
}
```

---

## Step 2. 建立 Service

```java
import org.springframework.stereotype.Service;
import java.time.Year;

@Service
public class UserService {

    public UserResponse calculateAge(UserRequest request) {
        int currentYear = Year.now().getValue();
        int age = currentYear - request.getBirthYear();

        UserResponse response = new UserResponse();
        response.setName(request.getName());
        response.setAge(age);

        return response;
    }
}
```

---

## Step 3. 撰寫單元測試

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testCalculateAge() {
        UserRequest req = new UserRequest();
        req.setName("Alice");
        req.setBirthYear(2000);

        UserResponse res = userService.calculateAge(req);

        assertEquals("Alice", res.getName());
        assertEquals(java.time.Year.now().getValue() - 2000, res.getAge());
    }
}
```
> 已掛載 `spring-boot-starter-test`，可直接啟動 Spring Context 並測試 Service 功能。

---

## Step 4. 驗收練習成果

- Service 可計算年齡並回傳 Response
- 單元測試可啟動 Spring Context 並驗證結果正確
- 此流程可直接應用至新建的 Component / Service

---

## 自我檢查清單 ✅

- [ ] 能在 Spring Boot 專案中建立 Service
- [ ] 能建立 Request / Response DTO
- [ ] 能撰寫單元測試驗證 Service 方法
- [ ] 能用 assert 驗證 Service 回傳結果
- [ ] 能成功啟動 Spring Context 並執行測試
- [ ] 能將此流程套用到新建 Component / Service