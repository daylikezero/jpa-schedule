## 🧑‍🏫 프로젝트 소개
[CH 3 일정 관리 앱 Develop](https://teamsparta.notion.site/Spring-5-CH-3-Develop-1912dc3ef51480d38f69ed0f0b7c5338)<br><br>
🌱 `Lv0` API 명세 및 ERD 작성<br>
🌿 `Lv1` 일정 CRUD<br>
🪴 `Lv2` 유저 CRUD<br>
🌳 `Lv3` 회원가입<br>
🌷 `Lv4` 로그인(인증)<br>
🌹 `Lv5` 다양한 예외처리 적용하기<br>
🌻 `Lv6` 비밀번호 암호화<br>
🪻 `Lv7` 댓글 CRUD<br>
💐 `Lv8` 일정 페이징 조회<br>

## 🗓️ 개발 기간
2025.02.06(목) ~ 2025.02.13(목)
<table>
  <tbody>
    <tr>
      <td align="center">02-06</th>
      <td align="center">02-07</td>
      <td align="center">02-10</td>
      <td align="center">02-11</td>
      <td align="center">02-12</td>
      <td align="center">02-13</td>
    </tr>
    <tr>
      <td align="center">Lv0<br>Lv1</td>
      <td align="center">Lv1<br>Lv2</td>
      <td align="center">Lv3<br>Lv4</td>
      <td align="center">Lv5<br>Lv6</td>
      <td align="center">Lv7<br>Lv8</td>
      <td align="center">통합테스트<br>최종검토</td>
    </tr>
  </tbody>
</table>


## 📋 [API 명세서]()

### 1️⃣ 일정 관리 Develop API - 일정
<div style="overflow-x: auto;">
  
| 기능 | Method | URI | Request | Response | Status |
| --- | --- | --- | --- | --- | --- |
| 일정 작성 | POST | /api/v1/schedules | {<br>"title": string,<br>"contents": string<br>} | {<br>"id": number,<br>"username": string,<br>"title": string,<br>"contents": string,<br>"replyCount": number,<br>"createdAt": string,<br>"updatedAt": string<br>} | 200, 400, 404 |
| 일정 페이지 조회 | GET | /api/v1/schedules?page={{number}}&size={{number}} |  | {<br> "content": [ <br>{ <br>"id": number,<br> "username": string,<br> "title": string,<br> "contents": string,<br> "replyCount": number,<br> "createdAt": string,<br> "updatedAt": string <br>}], <br>"pageable": { <br>"pageNumber": 3,<br> "pageSize": 5,<br> // ...<br> } | 200 |
| 일정 단건 조회 | GET | /api/v1/schedules/{{id}} |  | {<br>"id": number,<br>"username": string,<br>"title": string,<br>"contents": string,<br>"replyCount": number,<br>"createdAt": string,<br>"updatedAt": string<br>} | 200, 404 |
| 일정 수정 | PATCH | /api/v1/schedules/{{id}} | {<br>"title": string,<br>"contents": string<br>} | {<br>"id": number,<br>"username": string,<br>"title": string,<br>"contents": string,<br>"replyCount": number,<br>"createdAt": string,<br>"updatedAt": string<br>} | 200, 400, 404 |
| 일정 삭제 | POST | /api/v1/schedules/{{id}} |  |  | 200, 404 |


</div>

### 2️⃣ 일정 관리 Develop API - 유저
<div style="overflow-x: auto;">
  
| 기능 | Method | URI | Request | Response | Status |
| --- | --- | --- | --- | --- | --- |
| 회원가입 | POST | /api/v1/members/signup | {<br>"username": string,<br>"password": string,<br>"email": string<br>} | {<br>"id": number,<br>"username": string,<br>"email": string,<br>"createdAt": string,<br>"updatedAt": string<br>} | 200, 400 |
| 유저 목록 조회 | GET | /api/v1/members |  | [<br>{<br>"id": number,<br>"username": string,<br>"email": string,<br>"createdAt": string,<br>"updatedAt": string<br>},<br> //... <br>] | 200, 400 |
| 유저 아이디 조회 | GET | /api/v1/members/{{id}} |  | {<br>"id": number,<br>"username": string,<br>"email": string,<br>"createdAt": string,<br>"updatedAt": string<br>} | 200, 404 |
| 유저 수정 | PATCH | /api/v1/members/{{id}} | {<br>"password": string,<br>"username": string<br>"email": string<br>} | {<br>"id": number,<br>"username": string,<br>"email": string,<br>"createdAt": string,<br>"updatedAt": string<br>} | 200, 400, 404 |
| 유저 비밀번호변경 | PATCH | /api/v1/members/{{id}}/password | {<br>"oldPassword": string,<br>"newPassword": string<br>} |  | 200, 400, 404 |
| 유저 삭제 | POST | /api/v1/members/{{id}} | {<br>"password": string<br>} |  | 200, 404 |

</div>

### 3️⃣ 일정 관리 Develop API - 인증
<div style="overflow-x: auto;">
  
| 기능 | Method | URI | Request | Response | Status |
| --- | --- | --- | --- | --- | --- |
| 로그인 | POST | /api/v1/login | {<br>"email": string,<br>"password": string<br>} |  | 200, 401 |
| 로그아웃 | POST | /api/v1/logout |  |  | 200 |

</div>

### 4️⃣ 일정 관리 Develop API - 댓글
<div style="overflow-x: auto;">
  
| 기능 | Method | URI | Request | Response | Status |
| --- | --- | --- | --- | --- | --- |
| 댓글 작성 | POST | /api/v1/replies | {<br>"scheduleId": number,<br>"contents": string<br>} | {<br>"id": number,<br>"scheduleId": number,<br>"memberId": number,<br>"username": string,<br>"contents": string,<br>"createdAt": string,<br>"updatedAt": string<br>} | 200, 401 |
| 댓글 조회 | GET | /api/v1/replies |  | [<br> {<br>"id": number,<br>"scheduleId": number,<br>"memberId": number,<br>"username": string,<br>"contents": string,<br>"createdAt": string,<br>"updatedAt": string<br>},<br> // … <br>] | 200 |
| 댓글 수정 | PATCH | /api/v1/replies/{{id}} | {<br> "contents": string <br>} | {<br>"id": number,<br>"scheduleId": number,<br>"memberId": number,<br>"username": string,<br>"contents": string,<br>"createdAt": string,<br>"updatedAt": string<br>} | 200, 400, 404 |
| 댓글 삭제 | POST | /api/v1/replies/{{id}} |  |  | 200, 404 |

</div>

## [✔️ ERD](https://www.erdcloud.com/d/n8EPHTscd3WmKrffD)
![image](https://github.com/user-attachments/assets/10975dd8-def0-45c7-beaa-421ad8174e3c)





## ⚙ 개발 환경
- <img src="https://img.shields.io/badge/Java-007396?&style=for-the-badge&logo=java&logoColor=white" /><img src="https://img.shields.io/badge/gradle-%2302303A.svg?&style=for-the-badge&logo=gradle&logoColor=white" /><img src="https://img.shields.io/badge/spring-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white" /><img src="https://img.shields.io/badge/mysql-%234479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white" />
- JDK: `corretto-17 Amazon Corretto 17.0.13 - aarch64`
<!-- <img src="https://img.shields.io/badge/swagger-%2385EA2D.svg?&style=for-the-badge&logo=swagger&logoColor=black" /> -->

## 🔫 트러블 슈팅
[[Spring]_JPA_Entity](https://velog.io/@daylikezero/Spring-JPA-Entity)<br>
[[Spring]_JPA_Soft_Delete](https://velog.io/@daylikezero/Spring-JPA-Soft-Delete)<br>
[[Spring]_일정관리_앱_Develop_트러블슈팅](https://velog.io/@daylikezero/Spring-%EC%9D%BC%EC%A0%95%EA%B4%80%EB%A6%AC-%EC%95%B1-Develop-%ED%8A%B8%EB%9F%AC%EB%B8%94%EC%8A%88%ED%8C%85)<br>
