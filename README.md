# 🧇 WaffleOverFlow Server

## Tech Stack
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=Kotlin&logoColor=white"/></a> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/></a> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat-square&logo=Spring Security&logoColor=white"/></a> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/></a> <img src="https://img.shields.io/badge/JWT-6DB33F?style=flat-square&logo=Json Web Tokens&logoColor=white"/></a> <img src="https://img.shields.io/badge/NGINX-009639?style=flat-square&logo=NGINX&logoColor=white"/></a> <img src="https://img.shields.io/badge/AWS-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white"/></a>

## Members
|Leader|Member|Member|
|:---:|:---:|:---:|
|[문보현](https://github.com/moonpiderman)|[문용균](https://github.com/yg-moon)|[최하늘](https://github.com/caelum02)|

## Create Database

```shell
bash /scripts/bash/init-db.sh
```

## API Docs
🔗 [Notion Link](https://eggplant-sumac-51e.notion.site/API-Docs-47105a4de54e4be6bf6027010afecf4d)


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

## Database Scheme
![image](https://user-images.githubusercontent.com/70942197/145508752-4f98c975-a627-4840-9ae5-9ea84dba9115.png)
