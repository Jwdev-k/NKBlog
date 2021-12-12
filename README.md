# NKBlog
### Bootstrap 개인 블로그 프로젝트
#
## 1. 개발환경
- JDK 11
- Spring FrameWork
- Tomcat 9
- Mybatis and Maria DB
#
## 2. 기본설정
Clone 받은 소스를 실행하기 전에 `/sql/NKBlogDDL.sql` 파일을 Import 하여 테이블을 생성해주셔야합니다.
#
## 3. REST API
### 유저 정보 조회 JSON 반환 예시 
#### input = `/NKBlog/api/user/{uid}`
#### output = `{"id":2,"uid":"1234","password":"12345","gender":"null"}`
#
## 4. Simple Security Guide
### 4-1. 유저 정보 패스워드 저장방식
![1234](https://user-images.githubusercontent.com/82058641/144734289-cbbcec81-ef44-4236-ac3f-5b48153e460d.png)
### SHA256를 사용하여 암호화 후 DB에 저장.
#
