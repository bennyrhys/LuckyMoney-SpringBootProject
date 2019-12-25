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
//


```