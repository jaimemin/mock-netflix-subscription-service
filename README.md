mock-netflix-subscription-service
---
<h3>개요</h3>

---
* 해당 프로젝트는 구독형 멤버십 서비스를 구현하기 위해 Spring Boot 기반의 애플리케이션을 만들고 핵사고날 아키텍처를 적용했습니다.
* 보안에 초점을 맞추어 Spring Security를 활용하고, 인증 및 인가 기능을 구현했습니다.
* Spring Security에서 제공하는 기본적인 로그인/로그아웃 및 OAuth2.0을 활용했습니다.
* 영화 데이터를 불러오기 위해 TMDB API를 활용했습니다.

---
<h3>주요 기술 스택</h3>

---
* SpringBoot 3.3.3 버전
* Java 17
* Spring Security 6.x 버전
* Spring Batch 5.x 버전
* JWT
* OAuth 2.0 (Kakao Social Login)
* Gradle
* MySQL
* Flyway

---
<h3>프로젝트 모듈 구조</h3>

---
헥사고날 아키텍처로 구성하기 위해 멀티 모듈 프로젝트로 구성했습니다.

주요 모듈
* `netflix-apps`: 클라이언트가 호출할 수 있는 REST API와 Batch Job을 모아둔 모듈
* `netflix-adapters`: HTTP Client, DB, Redis 등 외부 인프라와 통신하기 위한 모듈
* `netflix-commons`: 모듈들이 공통으로 사용하는 유틸들을 모아둔 모듈
* `netflix-core`: 비즈니스 로직과 도메인 모델을 관리하는 모듈
* `netflix-frontend`: React 기반의 프론트엔드 코드를 모아둔 모듈

![image](https://github.com/user-attachments/assets/ea3ca505-1c98-4d63-bff8-d68d054650cf)



---

<h3>ERD</h3>

```mermaid
erDiagram
    USERS {
        VARCHAR USER_ID PK "사용자 ID (UUID)"
        VARCHAR USER_NAME "사용자 이름"
        VARCHAR PASSWORD "사용자 비밀번호 (암호화)"
        VARCHAR EMAIL "이메일"
        VARCHAR PHONE "전화번호"
        DATETIME CREATED_AT "생성일자"
        VARCHAR CREATED_BY "생성자"
        DATETIME MODIFIED_AT "수정일자"
        VARCHAR MODIFIED_BY "수정자"
    }

    SOCIAL_USERS {
        VARCHAR SOCIAL_USER_ID PK "소셜 사용자 ID (UUID)"
        VARCHAR USER_NAME "소셜 사용자 이름"
        VARCHAR PROVIDER "소셜 프로바이더 (구글, 카카오 등)"
        VARCHAR PROVIDER_ID "프로바이더 ID"
        DATETIME CREATED_AT "생성일자"
        VARCHAR CREATED_BY "생성자"
        DATETIME MODIFIED_AT "수정일자"
        VARCHAR MODIFIED_BY "수정자"
    }

    USER_HISTORIES {
        BIGINT USER_HISTORY_ID PK "사용자 이력 ID"
        VARCHAR USER_ID FK "사용자 ID"
        VARCHAR USER_ROLE "사용자 역할"
        VARCHAR REQ_IP "요청 IP"
        VARCHAR REQ_METHOD "요청 메소드"
        VARCHAR REQ_URL "요청 URL"
        TEXT REQ_HEADER "요청 헤더"
        TEXT REQ_PAYLOAD "요청 바디"
        DATETIME CREATED_AT "생성일자"
        VARCHAR CREATED_BY "생성자"
        DATETIME MODIFIED_AT "수정일자"
        VARCHAR MODIFIED_BY "수정자"
    }

    USER_SUBSCRIPTIONS {
        VARCHAR USER_SUBSCRIPTION_ID PK "사용자 구독 ID"
        VARCHAR USER_ID FK "사용자 ID"
        VARCHAR SUBSCRIPTION_NAME "구독권 이름"
        DATETIME START_AT "시작 일시"
        DATETIME END_AT "종료 일시"
        TINYINT VALID_YN "구독권 유효 여부"
        DATETIME CREATED_AT "생성일자"
        VARCHAR CREATED_BY "생성자"
        DATETIME MODIFIED_AT "수정일자"
        VARCHAR MODIFIED_BY "수정자"
    }

    TOKENS {
        VARCHAR TOKEN_ID PK "토큰 ID"
        VARCHAR USER_ID FK "사용자 ID"
        VARCHAR ACCESS_TOKEN "액세스 토큰"
        VARCHAR REFRESH_TOKEN "리프레시 토큰"
        DATETIME ACCESS_TOKEN_EXPIRES_AT "액세스 토큰 만료시간"
        DATETIME REFRESH_TOKEN_EXPIRES_AT "리프레시 토큰 만료시간"
        DATETIME CREATED_AT "생성일자"
        VARCHAR CREATED_BY "생성자"
        DATETIME MODIFIED_AT "수정일자"
        VARCHAR MODIFIED_BY "수정자"
    }

    MOVIES {
        VARCHAR MOVIE_ID PK "영화 ID"
        VARCHAR MOVIE_NAME "영화 명"
        TINYINT IS_ADULT "성인 영화 여부"
        VARCHAR GENRE "장르"
        VARCHAR OVERVIEW "설명"
        VARCHAR RELEASED_AT "출시일자"
        DATETIME CREATED_AT "생성일자"
        VARCHAR CREATED_BY "생성자"
        DATETIME MODIFIED_AT "수정일자"
        VARCHAR MODIFIED_BY "수정자"
    }

    USER_MOVIE_LIKES {
        VARCHAR USER_MOVIE_LIKE_ID PK "PK"
        VARCHAR USER_ID FK "사용자 ID"
        VARCHAR MOVIE_ID FK "영화 ID"
        TINYINT LIKE_YN "좋아요 여부"
        DATETIME CREATED_AT "생성일자"
        VARCHAR CREATED_BY "생성자"
        DATETIME MODIFIED_AT "수정일자"
        VARCHAR MODIFIED_BY "수정자"
    }

  USER_MOVIE_DOWNLOADS {
    VARCHAR USER_MOVIE_DOWNLOAD_ID PK "PK"
    VARCHAR USER_ID FK "사용자 ID"
    VARCHAR MOVIE_ID FK "영화 ID"
    DATETIME CREATED_AT "생성일자"
    VARCHAR CREATED_BY "생성자"
    DATETIME MODIFIED_AT "수정일자"
    VARCHAR MODIFIED_BY "수정자"
  }

  USERS ||--o{ USER_HISTORIES : "Has"
  USERS ||--o{ USER_SUBSCRIPTIONS : "Has"
  USERS ||--o{ TOKENS : "Has"
  USERS ||--o{ USER_MOVIE_LIKES : "Likes"
  USERS ||--o{ USER_MOVIE_DOWNLOADS : "Downloads"
  MOVIES ||--o{ USER_MOVIE_LIKES : "Liked by"
  MOVIES ||--o{ USER_MOVIE_DOWNLOADS : "Downloaded by"
```

---
<h3>프로젝트 설정</h3>

---
<h6>사전 준비</h6>

* SpringBoot 3.x 버전을 사용하므로 자바 17 또는 그 이상의 버전을 설치합니다.
* Node 20.9.0 버전을 설치합니다.

<br>
<h6>Docker-compose를 활용하여 MySQL 및 Redis 실행</h6>

```
docker-compose -f ./infra/docker-compose.yml up -d

docker ps -a
```

<br>
<h6>맥엔드 애플리케이션 실행</h6>

`NetflixApiApplication` 실행

<br>
<h6>프론트엔드 애플리케이션 실행</h6>

```
cd ./netplix-frontend

npm run start
```

---
<h3>참고</h3>

---
패스트 캠퍼스 Netplix 구독형 멤버십 프로젝트로 배우는 Spring Security
