# NKBlog
### Bootstrap 블로그 프로젝트

### 1. 개발환경
- JDK 11
- Spring FrameWork
- Tomcat 9
- Mybatis and Maria DB
#
### 2. 프로젝트 주요 기능
- 사용자 - 회원가입 및 로그인, OAuth2.0 네이버 로그인, 정보수정, 중복검사
- 게시판 - CRUD, 페이징 및 검색처리
- 댓글 - CRUD
#
### 3. 기본설정
Clone 받은 소스를 실행하기 전에 `/sql/NKBlogDDL.sql` 파일을 Import 하여 테이블을 생성해주셔야합니다.
#
### 4. REST API
 유저 정보 조회 JSON 
#
### 5. Simple Security Guide
5-1. 유저 정보 패스워드 저장방식
![1234](https://user-images.githubusercontent.com/82058641/144734289-cbbcec81-ef44-4236-ac3f-5b48153e460d.png)

SHA256를 사용하여 암호화 후 DB에 저장.

5-X 추후 업데이트 예정
#
### [DataBase] SQL
* 데이터 베이스 관계도

![db 관계도](https://user-images.githubusercontent.com/82058641/161379847-0cf41510-96f9-4b14-959f-f59859af5504.png)
