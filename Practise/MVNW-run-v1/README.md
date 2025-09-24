# Spring Boot Init 與 Maven 環境配置 Lab

## 🎯 Lab 目標
本 Lab 的目的是讓 Java 工程師在不同開發情境下，能順利使用 Spring Boot 初始化專案並配置 Maven 環境，即使在 **無法連線外網** 的限制下，也能正常開發與除錯。

---

## 🔧 情境說明

1. **方案一：直接用 IDE 內建功能執行**
    - 適合已安裝 **IntelliJ IDEA** 或 **Eclipse** 的開發者。
    - 使用 IDE 直接建立 Spring Boot 專案並自動處理 Maven。

2. **方案二：專案內附 Maven，統一團隊開發環境**
    - 適合團隊協作，避免 Maven 版本不同導致的相依問題。
    - Maven 被放在專案資料夾中，所有人透過相同設定檔與路徑執行。

3. **方案三：內網環境 / 客戶端限制不能連外**
    - 適合在無法連網的環境下（如客戶端機房）。
    - 需預先下載好 Maven 所需的套件，並在本地配置 `.m2`。

---

## 🚀 案例一：IDE 直接執行

```bash
# 在 IntelliJ IDEA 中：
File -> New -> Project -> Spring Initializr

# 選擇 Spring Boot 版本與需要的 dependencies，例如：
Spring Web, Spring Data JPA, H2 Database

# IDE 自動處理 Maven
```

---

## 🚀 案例二：專案內附 Maven

### Step 1. 下載 Spring Boot 初始化專案
```bash
curl -s https://start.spring.io/starter.tgz     -d dependencies=web,jpa,h2     -d baseDir=my-springboot-app | tar -xzvf -
cd my-springboot-app
```

### Step 2. 下載 Maven 並放到專案目錄
```bash
wget https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip
unzip apache-maven-3.9.6-bin.zip -d ./tools
mv ./tools/apache-maven-3.9.6 ./maven
```

### Step 3. 設定環境變數 (專案內使用)
在專案根目錄建立 `mvnw.sh`：

```bash
#!/bin/bash
PROJECT_DIR="$(cd "$(dirname "$0")"; pwd)"
export MAVEN_HOME=$PROJECT_DIR/maven
export PATH=$MAVEN_HOME/bin:$PATH

mvn "$@"
```

賦予執行權限：
```bash
chmod +x mvnw.sh
```

### Step 4. 設定專案內的 `.m2`
```bash
mkdir -p .m2/repository
```

修改 `maven/conf/settings.xml`：
```xml
<settings>
  <localRepository>${project.basedir}/.m2/repository</localRepository>
</settings>
```

### Step 5. 建立並執行專案
```bash
./mvnw.sh clean install
./mvnw.sh spring-boot:run
```

---

## 🚀 案例三：無網路環境（內網客戶端）

### Step 1. 預先下載所有依賴
在可連網的電腦上：
```bash
mvn dependency:go-offline -Dmaven.repo.local=./offline-m2
```

### Step 2. 搬移到內網環境
將 `offline-m2` 資料夾壓縮後搬到客戶端電腦：
```bash
scp -r offline-m2 user@client:/project/.m2/repository
```

### Step 3. 修改 Maven 設定檔
在 `settings.xml` 中指定：
```xml
<settings>
  <localRepository>/project/.m2/repository</localRepository>
</settings>
```

### Step 4. 執行專案
```bash
./mvnw.sh clean install -o
./mvnw.sh spring-boot:run -o
```

(`-o` 表示 offline mode)

---

## ✅ Lab 成果驗收
- [ ] 可以在 IDE 直接跑 Spring Boot 專案
- [ ] 可以在專案資料夾內執行 `./mvnw.sh spring-boot:run`
- [ ] 可以在無外網環境正常 build 與啟動
