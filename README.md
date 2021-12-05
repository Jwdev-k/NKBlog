## NKBlog
Bootstrap 개인 블로그 프로젝트

## 개발환경
- JDK 11
- Spring FrameWork
- Tomcat 9
- Mybatis and Maria DB
## 기본설정
Clone 받은 소스를 실행하기 전에 `/sql/NKBlogDDL.sql` 파일을 Import 하여 테이블을 생성해주셔야합니다.

## REST API
JSON 형태 반환
- 1. 유저정보 예시
input = `/NKBlog/user/getAccount?uid=1234`
output = {"id":2,"uid":"1234","password":"12345","gender":"남자"}
    

### 1. main screen

![main](https://user-images.githubusercontent.com/82058641/144664260-22c3a87b-9728-4937-940f-2d4c52b1cc61.png)

### 2. bbs

![bbs](https://user-images.githubusercontent.com/82058641/144664436-d9f7c420-931a-4096-8b8b-a09319997ecc.png)
