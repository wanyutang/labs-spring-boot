# Spring Boot + Swagger + TDD Lab – 會員管理 API

## Lab 目標

- 學習以 TDD 方式開發基本 CRUD API。
- 練習 Spring Boot + Swagger（springdoc-openapi）整合。
- 建立「會員管理 API」：包含「新增會員」與「查詢會員」。
- 撰寫單元測試（JUnit5 + MockMvc）驗證 API 功能正確性。

---

## 操作步驟

### 步驟 1：初始化專案

> 主要片段

1. 前往 [Spring Initializr](https://start.spring.io/) 建立專案：
    - Dependencies: `Spring Web`, `Spring Data JPA`, `H2 Database`, `Springdoc OpenAPI UI`
2. 啟動後可瀏覽 Swagger UI：

```
http://localhost:8080/swagger-ui.html
```

驗證目標：專案能啟動並顯示 Swagger UI。

---

### 步驟 2：撰寫測試（紅）

> 主要片段 `UserControllerTest.java`

```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateUser() throws Exception {
        String json = "{\"name\":\"John\",\"email\":\"john@test.com\"}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"));
    }
}
```

驗證目標：測試 Fail

---

### 步驟 3：最小實作

> 撰寫最少程式碼讓測試通過

`User.java`

```java
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    // getters/setters
}
```

`UserRepository.java`

```java
public interface UserRepository extends JpaRepository<User, Long> {
}
```

`UserController.java`

```java
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
```

驗證目標：測試 Pass

---

### 步驟 4：查詢會員 API

> 新增查詢功能並通過測試

測試片段

```java
@Test
void testGetUser() throws Exception {
    String json = "{\"name\":\"Alice\",\"email\":\"alice@test.com\"}";
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json));

    mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Alice"));
}
```

實作片段

```java
@GetMapping("/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
    return userRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
```

驗證目標：測試通過 (Fail -> Pass)

---

### 步驟 5：Swagger 文件展示

> 主要片段：無需新增程式，只需確認 Swagger 顯示正確

1. 啟動專案後開啟：
   ```
   http://localhost:8080/swagger-ui.html
   ```
2. 展示 `/users` POST 與 GET API。

驗證目標：Swagger 顯示 API 文件，測試可成功執行。

---

### 步驟 6：單元測試擴充與驗證

> 需補上更多情境測試

建議測試：
- 新增會員時缺少欄位（應回傳 400）
- 查詢不存在 ID（應回傳 404）
- 查詢多筆資料（可自行擴充 Service 層）

進階挑戰：可加入 `@Valid` 驗證與 Exception Handler。

---

## Checklist

- [ ] Swagger UI 顯示正常
- [ ] POST `/users` 能成功新增會員
- [ ] GET `/users/{id}` 能正確查詢會員
- [ ] 單元測試涵蓋基本情境（紅 → 綠）
- [ ] 所有測試通過
- [ ] API 文件可展示並正確描述 Body / Response

---

## Lab 補充說明

- 本 Lab 目的為建立「可完整運作的 API 開發循環」。
- 遵循 TDD 流程：Red → Green → Refactor。
- 可延伸練習：新增「修改會員」「刪除會員」功能。
- 請參考下列資源補完細節：

---

## 參考資料

- [Spring Boot + Swagger 3 Example (OpenAPI 3)](https://www.bezkoder.com/spring-boot-swagger-3/)
- [Spring Boot Swagger 中文教學（Blog）](https://chikuwa-tech-study.blogspot.com/2021/07/spring-boot-swagger-openapi-documentation.html)
- [Spring Boot 測試 MockMvc 官方文件](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [TDD – Red Green Refactor Explained (Baeldung)](https://www.baeldung.com/java-test-driven-development)
