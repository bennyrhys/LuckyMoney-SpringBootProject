[TOC]

# 文档说明

## 版本配置

Jdk1.8.111

meaven3.5.4

springboot2.1.3

# hello world

```java
package com.bennyrhys.luckymoney;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    @GetMapping("/hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public  String hello(){
        return "hello world";
    }
}
```

命令行启动

```luckymoney bennyrhys$ mvn spring-boot:run```

终止

ctrl+c

## 解决打包失败

```xml
<!--            为了打包后加的-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
```

查看jar包

ls target/

运行jar

java -jar target/luckymoney-0.0.1-SNAPSHOT.jar

访问：http://localhost:8080/hello

## 配置文件

###  application.properties

```xml
server.servlet.context-path=/luckymoney
server.port=8081
```

### application.yml(推荐)

```yml
server:
  port: 8081
  servlet:
    context-path: /luckymoney
```

访问：http://localhost:8081/luckymoney/hello

## git提交 ：helloworld+yml启动及简单配置

```
//初始化
springboot2小时浪漫红包 bennyrhys$ git init
//配置提交用户
git config user.name bennyrhys
git config user.email bennyrhys@163.com
//验证用户信息
cat .git/config
[core]
	repositoryformatversion = 0
	filemode = true
	bare = false
	logallrefupdates = true
	ignorecase = true
	precomposeunicode = true
[user]
	name = bennyrhys
	email = bennyrhys@163.com
//查看文件状态
git status
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)

	.DS_Store
	luckymoney/
//添加修改文件到暂存区
git add luckymoney/
//添加文件到数据库
git commit -m "hello world+yml 简单配置启动"
//建立远程连接
//列出已经存在的远程分支 -v详情
git remote -v
//创建远程分支
git remote add  origin_lm https://github.com/bennyrhys/LuckyMoney.git 
//检测分支
git remote -v
origin_lm	https://github.com/bennyrhys/LuckyMoney.git (fetch)
origin_lm	https://github.com/bennyrhys/LuckyMoney.git (push)
//此处切记更改上传人密钥信息
//push文件
git push origin_lm master
//远程仓库名冲突，修改远程分支
git remote remove origin_lm
//新建远程分支
git remote add origin_lm  https://github.com/bennyrhys/LuckyMoney-SpringBootProject.git
//因为github原有README.md文档需要解决冲突
git pull origin_lm master
//git push origin_lm master
```

# 配置红包

## 手动单个配置

1 .application.yml

```properties
mixMoney: 1
maxMoney: 99
description: 最少要发${mixMoney}元
```

2 .HelloController

```java
@RestController
public class HelloController {
//法1：手动引入配置信息
    @Value("${mixMoney}")
    private BigDecimal mixMoney;
    @Value("${description}")
    private String description;


    @GetMapping("/hello")
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public  String hello(){
        return "mixMoney"+mixMoney+"说明"+description;
    }
}
```

3. 访问http://localhost:8081/luckymoney/hello
4. 输出：mixMoney1说明最少要发1元
5. Git上传到分支confmoney

```
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git branch
* master
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git checkout -b confmoney
M	luckymoney/src/main/java/com/bennyrhys/luckymoney/HelloController.java
M	luckymoney/src/main/resources/application.yml
M	luckymoney_note.md
Switched to a new branch 'confmoney'
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git branch
* confmoney
  master
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git status
On branch confmoney
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   luckymoney/src/main/java/com/bennyrhys/luckymoney/HelloController.java
	modified:   luckymoney/src/main/resources/application.yml
	modified:   luckymoney_note.md

Untracked files:
  (use "git add <file>..." to include in what will be committed)

	.DS_Store
	luckymoney/.DS_Store

no changes added to commit (use "git add" and/or "git commit -a")
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git add .
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git commit -m "红包金额-手动配置"
[confmoney bde8c36] 红包金额-手动配置
 5 files changed, 59 insertions(+), 5 deletions(-)
 create mode 100644 .DS_Store
 create mode 100644 luckymoney/.DS_Store
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git remote -v
origin	https://github.com/bennyrhys/LuckyMoney-SpringBootProject.git (fetch)
origin	https://github.com/bennyrhys/LuckyMoney-SpringBootProject.git (push)
origin_lm	https://github.com/bennyrhys/LuckyMoney-SpringBootProject.git (fetch)
origin_lm	https://github.com/bennyrhys/LuckyMoney-SpringBootProject.git (push)
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git push origin_lm confmoney
Enumerating objects: 27, done.
Counting objects: 100% (27/27), done.
Delta compression using up to 4 threads
Compressing objects: 100% (11/11), done.
Writing objects: 100% (15/15), 2.37 KiB | 2.37 MiB/s, done.
Total 15 (delta 3), reused 0 (delta 0)
remote: Resolving deltas: 100% (3/3), completed with 2 local objects.
remote: 
remote: Create a pull request for 'confmoney' on GitHub by visiting:
remote:      https://github.com/bennyrhys/LuckyMoney-SpringBootProject/pull/new/confmoney
remote: 
To https://github.com/bennyrhys/LuckyMoney-SpringBootProject.git
 * [new branch]      confmoney -> confmoney
```

## 自动多个配置红包金额【limit类限制金额范围】

1. application.yml

```properties
limit:
  minMoney: 1
  maxMoney: 99
  description: 最少要发${limit.minMoney}元,最多发${limit.maxMoney}元
```

2. LimitConfig

```java
package com.bennyrhys.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HelloController {

//法2；自动注入
    @Autowired
    LimitConfig limitConfig;


    @GetMapping("/hello")
    public  String hello(){
        return "说明"+limitConfig.getDescription();
    }
}
```

3. HelloController

```java
package com.bennyrhys.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HelloController {

//法2；自动注入
    @Autowired
    LimitConfig limitConfig;


    @GetMapping("/hello")
    public  String hello(){
        return "说明"+limitConfig.getDescription();
    }
}
```

4. 输出

http://localhost:8081/luckymoney/hello

说明最少要发1元,最多发99元

5. git提交

```
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git branch
* confmoney
  master
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git status
On branch confmoney
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

	new file:   luckymoney/src/main/java/com/bennyrhys/luckymoney/LimitConfig.java

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   README.md
	modified:   luckymoney/src/main/java/com/bennyrhys/luckymoney/HelloController.java
	modified:   luckymoney/src/main/java/com/bennyrhys/luckymoney/LimitConfig.java
	modified:   luckymoney/src/main/resources/application.yml
	modified:   luckymoney_note.md

Untracked files:
  (use "git add <file>..." to include in what will be committed)

	README.assets/

bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git add .
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git commit -m "limit类限制金额自动配置"
```

# 生成环境开发环境划分

1. 思路

> dev开发模式
>
> prod生产模式
>
> 由yml控制选择模式
>
> 
>
> 场景
>
> dev：测试0.1 元
>
> prod：正式1元



2.  application-dev.yml

```properties
spring:
  profiles:
    active: dev #设定部署的测试配置开发、生产(如果正式上线，可以不修改此处，打包后命令切换环境)
```

3. application-dev.yml

```properties
server:
  port: 8081
  servlet:
    context-path: /luckymoney
limit:
  minMoney: 0.1
  maxMoney: 99
  description: 最少要发${limit.minMoney}元,最多发${limit.maxMoney}元
```

4. application-prod.yml

```properties
server:
  port: 8081
  servlet:
    context-path: /luckymoney
limit:
  minMoney: 1
  maxMoney: 99
  description: 最少要发${limit.minMoney}元,最多发${limit.maxMoney}元
```

5. 输出

> Dev:说明最少要发0.1元,最多发99元
>
> Prod:说明最少要发1元,最多发99元

6. 打包命令切换开发环境

```
meaven使用
//项目根目录打包
mvn clean package
//普通启动-开发环境
java -jar target/luckymoney-0.0.1-SNAPSHOT.jar
//启动时修改-生产环境
java -jar -Dspring.profiles.active=prod  target/luckymoney-0.0.1-SNAPSHOT.jar
```



6. git上传

```
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git branch
* confmoney
  master
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git add .
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git commit -m "多环境配置-生产、开发"
[confmoney b3f8623] 多环境配置-生产、开发
 4 files changed, 137 insertions(+), 10 deletions(-)
 create mode 100644 luckymoney/src/main/resources/application-dev.yml
 create mode 100644 luckymoney/src/main/resources/application-prod.yml
bennyrhysdeMacBook-Pro:LuckyMoney-SpringBootProject bennyrhys$ git push origin_lm confmoney

```



# 小结

@Value //单个配置

@Component 	@ConfigurationProperties//多个配置

多环境配置

# Controller的使用



<img src="luckymoney_note.assets/image-20191229164313530.png" alt="image-20191229164313530" style="zoom:50%;" />

@requestmapping旧版使用，新版@getmapping，但有个别场景还是会使用到它的

## controller+thymeleaf（不推荐，前后端不分离）

将restcontrller改成contrllre（报错），增添thymeleaf模版，返回指定templates-index页面

1. 改成旧版controller

```java
package com.bennyrhys.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Controller
public class HelloController {

//法2；自动注入
    @Autowired
    LimitConfig limitConfig;


    @GetMapping("/hello")
    public  String hello(){
//        return "说明"+limitConfig.getDescription();
        return "index";
    }
}
```

2. pom.xml,新增thymeleaf模版

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

3. 新建thymeleaf模版index页面

4.访问

http://localhost:8081/luckymoney/hello

hello world

5. git上传

```

```

