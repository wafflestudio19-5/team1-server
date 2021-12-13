# 🧇 WaffleOverFlow Server
## Create Database

```shell
bash /scripts/bash/init-db.sh
```

## Project Structure
```
├── build.gradle.kts
├── gradlew
├── scripts
│   ├── bash/init-db.sh
│   └── sql/init-db.sql
└── src
    └── main
        ├── kotlin
        │    └── com.wafflestudio.waffleoverflow
        │        ├── domain
        │        │   ├── USER - controller, repository, service, model
        │        │   ├── ANSWER - controller, repository, service, model
        │        │   ├── QUESTION - controller, repository, service, model
        │        │   ├── COMMENT - repository, service, model
        │        │   ├── VOTE - repository, service, model
        │        │   └── TAG - repository, service, model
        │        └── global
        │            ├── auth - JWT, SigninAuthenticationFilter
        │            ├── common.exception - ErrorType(ENUM), ErrorResponse
        │            └── config
        │                └── SecurityConfig
        │
        └── resources
            └── application.yml
```

## Databse Scheme
![image](https://user-images.githubusercontent.com/70942197/145508752-4f98c975-a627-4840-9ae5-9ea84dba9115.png)
