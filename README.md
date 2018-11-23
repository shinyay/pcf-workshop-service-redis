# バックエンドサービスの利用 - Redis
バックエンドサービスとして Redis を使用します。

## 概要 / 説明
Pivotal Cloud Foundry には、アプリケーションを強化、保護、管理するためのプラットフォーム・アドオンサービスをお客様に提供する仕組みとして、
**[Pivotal Services Marketplace](https://pivotal.io/jp/platform/services-marketplace)** が提供されています。
カタログされているサービスには、データ永続性、キャッシュ、メッセージング、継続的インテグレーションなど、多様な分野にわたり、
Pivotal、Pivotalのパートナー、Cloud Foundryコミュニティが提供する、精選されたアドオンサービスが掲載されています。

ここでは、データをキャッシュするために、**Redis** を使用します。

### Redis
Redis とはインメモリベースの Key/Value ストア (KVS) です。

保存したいデータ(値：value)に対し、対応する一意の標識(キー：key)を設定し、これらをペアで保存して使用します。

## 前提 / 環境
- [事前作業](https://github.com/shinyay/pcf-workshop-prerequisite/blob/master/README.md) 実施済み

## 手順 / 解説
### プロジェクトの作成
GitHub 上に作成済みの Spring Boot プロジェクトをクローン(`git clone`)しプロジェクトを作成します。

- https://github.com/shinyay/pcf-workshop-service-redis-code.git

任意のディレクトリで、以下のコマンドを実行します。

```
$ mkdir pcf-workshop
$ cd pcf-workshop
$ git clone https://github.com/shinyay/pcf-workshop-service-redis-code.git hello-pcf-redis
$ cd hello-pcf-redis
```

### アプリケーションの修正
クローンしたプロジェクトに含まれている次のソースコードを編集します。

- src/main/java/com/example/hellopcf/HelloPcfRedisApplication.java

#### 編集内容
- キャッシュ機能の有効化
  - `EnableCaching` アノテーションの追加
- キャッシュ対象のデータを生成する処理の作成
  - `Cacheable` アノテーションを追加したメソッドの作成と呼び出し
  
<details><summary>編集済みソースコード</summary>

```java
package com.example.hellopcf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@SpringBootApplication
@RestController
@EnableCaching
public class HelloPcfRedisApplication {
    private final Greeter greeter;

    public HelloPcfRedisApplication(Greeter greeter) {
        this.greeter = greeter;
    }

    @GetMapping("/")
    String hello() {
        return greeter.hello();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloPcfRedisApplication.class, args);
    }
}

@Component
class Greeter {
    @Cacheable("hello")
    public String hello() {
        return "Hello, Redis. It's " + OffsetDateTime.now() + " now.";
    }
}
```
</details>


## まとめ / 振り返り

### 今回のソース
- []()
