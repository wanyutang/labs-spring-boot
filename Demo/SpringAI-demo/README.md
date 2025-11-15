# Spring AI Demo

Spring Boot 3.5.5 示範專案，整合 Swagger/OpenAPI 文件。

## 技術棧

- **Java**: 21
- **Spring Boot**: 3.5.5
- **SpringDoc OpenAPI**: 2.6.0
- **Maven**: 3.x

## 功能特性

- ✅ RESTful API
- ✅ Swagger UI 互動式 API 文件
- ✅ OpenAPI 3.0 規範
- ✅ 示例 CRUD 端點

## 快速開始

### 前置需求

- JDK 21 或以上
- Maven 3.x

### 安裝與執行

```bash
# 編譯專案
./mvnw clean install

# 啟動應用程式
./mvnw spring-boot:run
```

應用程式將在 `http://localhost:8080` 啟動。

## API 文件

啟動應用程式後，可以通過以下 URL 訪問 API 文件：

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## API 端點

### Hello API

| 方法 | 端點 | 描述 |
|------|------|------|
| GET | `/api/hello` | 取得簡單問候訊息 |
| GET | `/api/hello/{name}` | 取得個人化問候訊息 |
| POST | `/api/greet` | 自訂問候訊息 |

### 範例請求

**GET /api/hello**
```bash
curl http://localhost:8080/api/hello
```

**GET /api/hello/{name}**
```bash
curl http://localhost:8080/api/hello/Winny
```

**POST /api/greet**
```bash
curl -X POST http://localhost:8080/api/greet \
  -H "Content-Type: application/json" \
  -d '{"name":"Winny","message":"歡迎使用 Spring AI!"}'
```

## 專案結構

```
src/
├── main/
│   ├── java/
│   │   └── com/example/demo/
│   │       ├── DemoApplication.java          # 主應用程式
│   │       ├── config/
│   │       │   └── OpenApiConfig.java        # OpenAPI 配置
│   │       └── controller/
│   │           └── HelloController.java      # REST Controller
│   └── resources/
│       └── application.properties            # 應用程式配置
└── test/
```

## 配置說明

主要配置位於 `src/main/resources/application.properties`：

```properties
# 應用程式名稱
spring.application.name=spring-ai-demo

# 伺服器端口
server.port=8080

# Swagger UI 路徑
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs
```

## 開發指南

### 添加新的 API 端點

1. 在 `controller` 包中創建新的 Controller
2. 使用 `@RestController` 和 `@RequestMapping` 註解
3. 使用 `@Operation` 和 `@Tag` 添加 API 文件
4. 重啟應用程式查看更新的 Swagger UI

### 自訂 OpenAPI 配置

修改 `OpenApiConfig.java` 來自訂 API 文件的標題、描述、聯絡資訊等。

## 相關連結

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [SpringDoc OpenAPI](https://springdoc.org/)
- [Swagger UI](https://swagger.io/tools/swagger-ui/)

## 授權

Apache 2.0
