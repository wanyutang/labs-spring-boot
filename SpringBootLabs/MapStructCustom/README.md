# MapStruct 快速練習：簡單物件轉換

🎯 **目標：**
- 學會用 `@Mapping` 對應不同欄位名稱
- 學會用 `@Named` 撰寫簡單自訂轉換器

---

## Step 0. 前置準備

在 `pom.xml` 加上：

```xml
<dependencies>
    <!-- MapStruct -->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.5.5.Final</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.10.1</version>
            <configuration>
                <source>17</source>
                <target>17</target>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>1.5.5.Final</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

---

## Step 1. 建立簡單的 Entity & DTO

```java
// Entity
public class UserEntity {
    private Long id;
    private String fullName;
    private String birthday; // yyyy-MM-dd
    // getter/setter
}
```

```java
// DTO
public class UserDto {
    private Long userId;
    private String name;
    private Integer age;
    // getter/setter
}
```

---

## Step 2. 建立 Mapper 介面

```java
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "birthday", target = "age", qualifiedByName = "stringToAge")
    UserDto toDto(UserEntity entity);

    @Named("stringToAge")
    static Integer stringToAge(String birthday) {
        if (birthday == null) return null;
        LocalDate birthDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
```

---

## Step 3. 測試練習

```java
public class MapStructTest {
    public static void main(String[] args) {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setFullName("Alice Wonderland");
        entity.setBirthday("2000-01-01");

        UserDto dto = UserMapper.INSTANCE.toDto(entity);

        System.out.println("UserId: " + dto.getUserId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Age: " + dto.getAge());
    }
}
```

---

## Step 4. 驗收練習成果

- `id → userId` (不同名稱欄位)
- `fullName → name` (直接對應)
- `birthday(String) → age(Integer)` (自訂轉換器)

---

## 小結

完成以上練習，你已經學會了 MapStruct 最常用的兩大功能：
1. **欄位對應 `@Mapping`**
2. **自訂轉換器 `@Named`**
