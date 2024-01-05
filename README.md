## 🗨️ 프로젝트 소개
뮤지컬/연극 관람객의 더 나은 경험과 선택을 위해 개인 맞춤형 공연을 추천 해주는 서비스입니다.  
공연의 세부 정보 및 리뷰 분석으로 통해 더 직관적이고 세밀한 정보를 제공합니다.

### 주요 기능

- 약 7200개의 뮤지컬, 연극 데이터를 기반으로 시각화한 `공연 정보 제공`
    - Seleninm 라이브러리를 사용하여 PlayDB, Interpark 사이트 크롤링
- 사용자의 공연 평가, 리뷰를 기반으로 `사용자 맞춤 공연 추천`
    - 콘텐츠 기반 필터링 알고리즘을 사용하여 태그 및 배우 기반 유사한 공연 추출
- 사용자의 관람 기록을 토대로 `취향을 분석`해 선호 태그와 선호 배우 정보 제공
    - 하둡을 이용하여 리뷰 데이터를 분산 저장 및 특정 키워드 단어 카운팅 MapReduce 처리

![슬라이드1](https://user-images.githubusercontent.com/63090006/162746231-6d5f2ec1-a1ec-4c1f-9c8e-b94caa623c65.PNG)

![슬라이드2](https://user-images.githubusercontent.com/63090006/162746256-581f4f66-b5e9-4fe9-93b6-60c887fa9085.PNG)

![슬라이드3](https://user-images.githubusercontent.com/63090006/162746277-f9cd4052-448a-46cf-82f8-ed93c05bae71.PNG)

<br>

## 💻 주요 페이지
### 메인
![](https://user-images.githubusercontent.com/63090006/231166656-f5a6a4af-ec47-4caf-a740-71a120fe89dd.jpg)

로그인 전  
![로그인 전](https://user-images.githubusercontent.com/63090006/231166073-6d633111-2d76-41ad-ac98-9f9507f36ae4.gif)

로그인 후  
![로그인 후](https://user-images.githubusercontent.com/63090006/231164265-a40e5d9a-895e-473c-ab93-3a6cffb78949.gif)

### 공연 상세
![공연 상세1)](https://user-images.githubusercontent.com/63090006/231165699-3f66d80d-02d3-41da-b177-3f8452de1566.gif)

![공연 상세2](https://user-images.githubusercontent.com/63090006/231165439-040b04f7-7b80-4bec-b992-658abd333f5e.gif)

### 마이페이지
![마이페이지](https://user-images.githubusercontent.com/63090006/231164825-b46f5e8b-0fb2-440c-a973-32671243db96.gif)

<br>

## 🗓️ 진행 기간

2022.02.21~2022.04.08(총 7주)

<br>

## 🖊️ 팀원 소개
고주희, 김민, 김혜지, 송예진, 이명주, 최혜린  
  
### 역할   
- Backend : 김민준, 김혜지, 이명주  
- Frontend : 고주희, 송예진, 최혜린  
- Data : 고주희, 김민준, 송예진, 이명주  
- Hadoop : 김혜지, 최혜린  

<br>

## 🔨 기술스택

### Front-End

- Node.js - `v14.15.1`
- npm - `v8.1.2`
- HTML 5, CSS3, JavaScript (ES6)
- Vue.js 2

### Back-End

- Java - `openjdk v1.8.0_192`
- Spring Boot - `v2.5.7`
- Django
- JPA, QueryDSL
- MySQL - `v8.0.27`
- Swagger - `v3`

### Data

- Hadoop HDFS
- Hadoop mapreduce
- Python
- Selenium

### Infra

- AWS EC2 Ubuntu 20.04
- Jenkins - [ jenkins/jenkins:lts ] - `v2.332.1`
- Nginx - `nginx/1.18.0 (Ubuntu)`
- Certbot - `certbot 1.25.0`
- Docker - `v20.10.13`

<br>

## 🔨 파일구조

[프로젝트 구조](https://broadleaf-crabapple-56b.notion.site/dfae4f46a3ee40eba813d49425fa9f8e)

<br>

## ✨ 산출물

### 화면 설계

![Group_24](https://user-images.githubusercontent.com/63090006/162746596-b4feff01-8d99-4750-863a-1f8eae9d7230.png)

### ERD

![ourerd_ver2__2_](https://user-images.githubusercontent.com/63090006/162746722-ef3e6007-202d-43e9-8e34-1f46949b722b.png)

### API

[API 설계서](https://broadleaf-crabapple-56b.notion.site/API-9a5a758b6b074989ab66d53ca90184f6)
