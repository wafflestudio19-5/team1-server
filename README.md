# π§ WaffleOverFlow Server

## Tech Stack
![waffleoverflow](https://user-images.githubusercontent.com/70942197/151508565-28bc3365-7119-4c72-b0dc-0f7bfd18bd6a.png)

## Members
|Leader|Member|Member|
|:---:|:---:|:---:|
|[λ¬Έλ³΄ν](https://github.com/moonpiderman)|[λ¬Έμ©κ· ](https://github.com/yg-moon)|[μ΅νλ](https://github.com/caelum02)|

## 

## Create Database

```shell
bash /scripts/bash/init-db.sh
```

## API Docs
π [Notion Link](https://eggplant-sumac-51e.notion.site/API-Docs-47105a4de54e4be6bf6027010afecf4d)


## Project Structure
```
βββ build.gradle.kts
βββ gradlew
βββ scripts
β   βββ bash/init-db.sh
β   βββ sql/init-db.sql
βββ src
    βββ main
        βββ kotlin
        β    βββ com.wafflestudio.waffleoverflow
        β        βββ domain
        β        β   βββ USER - controller, repository, service, model, S3Utils
        β        β   βββ ANSWER - controller, repository, service, model
        β        β   βββ QUESTION - controller, repository, service, model
        β        β   βββ COMMENT - repository, service, model
        β        β   βββ VOTE - repository, service, model
        β        β   βββ TAG - repository, service, model
        β        βββ global
        β            βββ auth - JWT, SigninAuthenticationFilter
        β            β   βββ OAuth2 - Handler, Service
        β            βββ common.exception - ErrorType(ENUM), ErrorResponse
        β            βββ config
        β                βββ SecurityConfig
        β
        βββ resources
            βββ application.yml
```

## Database Scheme
![image](https://user-images.githubusercontent.com/70942197/149756651-8580a24b-f572-49c9-9bae-705b9cde04aa.png)

