# Introduction 
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Installation process
2.	Software dependencies
3.	Latest releases
4.	API references

# Build and Test
TODO: Describe and show how to build your code and run the tests. 

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)

# 練習說明

## 可練習的項目

本工作區提供以下 Spring Boot 相關的練習項目：

### SpringBootLabs
- **MapStructCustom**: MapStruct 物件轉換練習
  - 學習使用 `@Mapping` 進行欄位對應
  - 學習使用 `@Named` 撰寫自訂轉換器
  - 實作 Entity 與 DTO 之間的轉換

### SpringBootInit
- Spring Boot 專案初始化與基本配置練習

### SpringBootHollowWord
- Spring Boot Hello World 基礎練習

## 工作區使用方法

- **目標**：在 Labs 分支上完成練習，並於任務板建立 Task 與 PR 的關聯
- **倉庫連結**：https://dev.azure.com/SystemwebRD/SWFramework/_
- **路徑資訊**：git/sw-framework-labs?path=/&version=GBpractise/fern&_a=contents

### 步驟

1. **開分支**
   - 從 main 建立個人練習分支

   ```bash
   git checkout main
   git pull
   git checkout -b practise/你的名稱或代號
   ```

2. **完成 Labs 練習**
   - 在 `/Practise` 目錄下對應的 Labs 完成練習
   - 完成後提交並推送

   ```bash
   git add .
   git commit -m "feat: 完成 <Lab 名稱> (TASK-XXXX)"
   git push -u origin practise/你的名稱或代號
   ```

3. **建立關聯**
   - Commit 訊息需包含 Task ID（例如 TASK-XXXX）
   - 到任務板的 Task 卡片回填此次 commit 的版本或哈希值，建立 Task 與 PR 的關聯
   - 之後從該分支發起 PR，指向 main 或對應整合分支，讓審查能追蹤到 Task

### 小提醒

- 分支命名格式：practise/XXXX（XXXX 可用你的英文名或縮寫）
- Commit 訊息務必含 Task ID，否則任務板無法自動或清楚關聯
- 每個 Lab 建議獨立多次小提交，便於 Review 與回滾