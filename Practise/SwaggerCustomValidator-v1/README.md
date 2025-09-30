# Spring Boot Enum Name 驗證 Lab

## Lab 目標

- 學習如何設計 API 以 Swagger 方式文件化，接收 JSON body，並驗證 JSON 中的 enum 欄位值是否正確（名稱）。
- 練習主要程式片段的撰寫，並透過查詢 Spring Boot 及 Swagger 官方或權威資源自行完成細節。
- 以單元測試驗證你設計的 enum 驗證機制是否正確。

---

## 操作步驟

### 步驟 1：宣告業務用 Enum 類別（如狀態）

> 主要片段
```java
public enum StatusEnum {
    ACTIVE, INACTIVE, SUSPENDED
}
```

---

### 步驟 2：設計驗證工具類

> 主要片段
```java
public static boolean isValidEnumName(Class<? extends Enum<?>> enumClass, String value) {
    if (value == null) return false;
    return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.name().equals(value));
}
```

---

### 步驟 3：建立 API Body 對應的請求物件

> 主要片段
```java
public class StatusReq {
    private String status;
    // getter, setter
}
```
> 需加上 Swagger 註解（參考下方資源）

---

### 步驟 4：Controller 驗證並回應

> 主要片段
```java
@PostMapping("/status")
public ResponseEntity<?> setStatus(@RequestBody StatusReq req) {
    if (!isValidEnumName(StatusEnum.class, req.getStatus())) {
        return ResponseEntity.badRequest().body(Map.of("success", false, "message", "status 欄位錯誤"));
    }
    return ResponseEntity.ok(Map.of("success", true, "status", req.getStatus()));
}
```
> 需加上 Swagger 的 API Operation、參數說明註解（參考下方資源）

---

### 步驟 5：Swagger UI 介接

- 依官方/社群教學設定，讓 Swagger UI 能正確顯示 API 文件與 Body 格式。
- 測試正確及錯誤情境。

---

### 步驟 6：單元測試驗證

> 主要測試片段

```java
    @Test
    void validStatus_shouldReturnSuccess() {
    //...
    } // status = "ACTIVE"
    @Test
    void invalidStatus_shouldReturnError() {
        //...
    }  // status = "INVALID"
    @Test
    void missingStatus_shouldReturnError() {
        //...
    } // status = null
```
> 需查詢如何使用 Spring Boot 測試 API（MockMvc）

---

## Checklist

- [ ] Enum 類別宣告正確
- [ ] 驗證工具能判斷 enum name 合法性
- [ ] Controller 可正確驗證並回傳格式化訊息
- [ ] Swagger UI 能顯示 API 並測試各情境
- [ ] 單元測試覆蓋有效、無效、缺少欄位情境
- [ ] 測試驗證機制成功

---

## 參考資料（請自行查詢並補完細節）

- [Spring Boot + Swagger 3 Example (OpenAPI 3)](https://www.bezkoder.com/spring-boot-swagger-3/)
- [Spring Boot Swagger 中文教學（Blog）](https://chikuwa-tech-study.blogspot.com/2021/07/spring-boot-swagger-openapi-documentation.html)
- [如何用 SpringDoc OpenAPI 處理 Enum 格式（StackOverflow）](https://stackoverflow.com/questions/68747036/how-can-have-springdoc-openapi-use-the-jsonvalue-enum-format-without-changing-t)
- [SpringBoot 集成 Swagger UI 顯示 Json 格式說明（CSDN）](https://blog.csdn.net/JingAi_jia917/article/details/136626438)

---

## Lab 補充

- 請只根據主要片段自行查詢並補完細節（如 Swagger 註解、單元測試、Spring Boot 專案設定等）。
- 建議將 enum 驗證工具抽成公用方法或元件，便於日後擴充。
- 完成 checklist 並通過所有單元測試即表示練習完成。
