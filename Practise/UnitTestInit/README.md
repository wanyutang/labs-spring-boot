
# Spring Boot Unit Test 

🎯 **目標：**
- 建立空的 Spring Boot 專案
- 建立簡單 Component / Service
- 寫 Unit Test 測試 Service 功能

---

## Step 0. 前置準備

在 `pom.xml` 加上 Spring Boot Starter 與測試套件：

```xml
<dependencies>
    <!-- Spring Boot Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

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

## Step 3. 建立 Unit Test

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
        assertEquals(Year.now().getValue() - 2000, res.getAge());
    }
}
```

---

## Step 4. 驗收練習成果

- Service 可計算年齡並回傳 Response
- Unit Test 可啟動 Spring Context，並驗證結果正確
- 這套流程可以直接套用到新建 Component / Service

---

## 自我檢驗清單 ✅

- [ ] 能在 Spring Boot 專案中建立 Service
- [ ] 能建立 Request / Response DTO
- [ ] 能撰寫 Unit Test 測試 Service 方法
- [ ] 能用 assert 驗證 Service 回傳結果
- [ ] 能成功啟動 Spring Context 並執行測試
- [ ] 能將流程套用到新建 Component / Service 上
