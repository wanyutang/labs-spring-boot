# Spring Boot Hollow Word 開發指南

這是一個完整的Spring Boot Hollow Word專案開發指南，將帶您從零開始建立一個功能完整的Web應用程式。

## 專案簡介

Hollow Word 是一個基於Spring Boot框架的現代化Web應用專案，展示了企業級應用開發的最佳實踐。

## 開發環境準備

### 必要環境
- Java 21 或以上版本
- Maven 3.8+ 或 Gradle 7.5+
- IDE (建議使用 IntelliJ IDEA 或 Eclipse)
- Git 版本控制系統

### 推薦工具
- Postman (API測試)
- MySQL Workbench (資料庫管理)
- Docker (容器化部署)

## 第一步：建立Spring Boot專案

1. 前往 [Spring Initializr](https://start.spring.io)
2. 設定專案參數：
   - Project: Maven Project
   - Language: Java
   - Spring Boot: 3.2.x (最新穩定版)
   - Group: com.example
   - Artifact: hollow-word
   - Name: Hollow Word
   - Description: Spring Boot Hollow Word Application
   - Package name: com.example.hollowword
   - Packaging: Jar
   - Java: 21

3. 選擇依賴項目：
   - **Spring Web** (Web MVC框架)
   - **Spring Data JPA** (資料持久化)
   - **MySQL Driver** (資料庫驅動)
   - **H2 Database** (開發測試用資料庫)
   - **Spring Boot DevTools** (開發工具)
   - **Validation** (資料驗證)
   - **Spring Security** (安全性)
   - **Thymeleaf** (模板引擎)

4. 點擊 "Generate" 下載專案
5. 解壓縮並匯入IDE

## 第二步：專案結構規劃

建立標準的分層架構：
