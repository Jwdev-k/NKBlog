# NKBlog
### Bootstrap 블로그 프로젝트

## 1. 개발환경
- JDK 17
- Spring FrameWork
- Spring Boot
- Tomcat 9
- Mybatis and Maria DB

## 2. 프로젝트 주요 기능
### 1. 로그인

- 사용자 회원가입 및 로그인, OAuth2.0 소셜 로그인(카카오 및 네이버 api를 활용),
- 정보수정, 중복검사
- 패스워드 찾기(Gmail SMTP 서버를 사용한 이메일전송 방식),
- 아이디 및 패스워드 정규표현식을 사용하여 유효성 검사
### 2. 게시판
- CRUD, 페이징 및 검색처리, 파일 업로드 
- 로그인한 사용자만 등록 및 수정가능
### 3. 댓글
- CRUD, 등록 및 수정 
- 로그인한 사용자만 등록 및 수정가능

## 3. 기본설정
- Clone 받은 소스를 실행하기 전에 `/sql/NKBlogDDL.sql` 파일을 Import 하여 테이블을 생성

## 4. REST API
### TEST 유저 정보 조회 JSON
유저 아이디 기반으로 해당 계정의 DB에 저장된 정보를 조회하는 기능입니다.

![image](https://user-images.githubusercontent.com/82058641/173864483-a4f75873-5218-4727-978e-582f47fbaa42.png)

![image](https://user-images.githubusercontent.com/82058641/173864583-de8e9f2c-6aae-46a7-81a2-ddf00d0b16e6.png)

### Active User
SessionListener 를 이용해 각각의 세션정보를 담아둔 Map 에서 현재 로그인중인 세션을 JSON 으로 가져옵니다.

![image](https://user-images.githubusercontent.com/82058641/173863996-9e79edbd-b6e4-45ae-9084-c152afe8644b.png)

![image](https://user-images.githubusercontent.com/82058641/173864108-26bc48e2-d973-4f6a-b0f0-f35d57602679.png)

## 5. Simple Security Guide
#### 5-1. 유저 정보 패스워드 저장방식

![1234](https://user-images.githubusercontent.com/82058641/144734289-cbbcec81-ef44-4236-ac3f-5b48153e460d.png)

- SHA256를 사용하여 암호화 후 DB에 저장합니다. 단방향 암호화이기 때문에 원래 패스워드를 다시 알수는 없습니다.
- 만약 암호를 잃어버렸을 경우에는 본인확인후 패스워드를 새로 설정할 필요가 있습니다.

5-X 추후 업데이트 예정

### [DataBase] SQL
* 데이터 베이스 관계도

![DB관계도](https://user-images.githubusercontent.com/82058641/163245617-99d440c4-1c04-4196-936a-196b66c7caa6.png)

### 게시판 화면

![image](https://user-images.githubusercontent.com/82058641/172414548-87822c6e-5a09-456d-993a-16fcc495cdd0.png)