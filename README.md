# 🧇 WaffleOverFlow Server

## Tech Stack
![image](https://user-images.githubusercontent.com/70942197/149767498-facb315b-2432-46e1-b63c-cc817cbc135a.png)

## Members
|Leader|Member|Member|
|:---:|:---:|:---:|
|[문보현](https://github.com/moonpiderman)|[문용균](https://github.com/yg-moon)|[최하늘](https://github.com/caelum02)|

## 

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
        │            │   └── OAuth2 - Handler, Service
        │            ├── common.exception - ErrorType(ENUM), ErrorResponse
        │            └── config
        │                └── SecurityConfig
        │
        └── resources
            └── application.yml
```

## Database Scheme
![image](https://user-images.githubusercontent.com/70942197/149756651-8580a24b-f572-49c9-9bae-705b9cde04aa.png)

