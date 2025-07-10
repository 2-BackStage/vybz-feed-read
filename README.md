# 🎵 Feed Read Service

> **VYBZ 플랫폼의 피드 읽기 전용 마이크로서비스**

피드 데이터의 조회 및 읽기 기능을 담당하는 Spring Boot 기반 마이크로서비스입니다. Notice, Reels, FanFeed, About 등 다양한 피드 타입을 지원하며, Kafka 이벤트 기반으로 실시간 데이터 동기화를 제공합니다.

---

## 🛠 Tech Stack

### **Backend**
- **Language**: Java 17
- **Framework**: Spring Boot 3.4.5
- **Database**: MongoDB
- **Message Queue**: Apache Kafka
- **Service Discovery**: Netflix Eureka Client
- **HTTP Client**: OpenFeign
- **Documentation**: Swagger/OpenAPI 3.0
- **Build Tool**: Gradle 8.4

### **Architecture Pattern**
- **Layered Architecture**
- **Domain-Driven Design (DDD)**
- **Event-Driven Architecture**

---

## 📋 서비스 목록

| 서비스명 | 설명 | 언어 | 상태 |
|---------|------|------|------|
| **Feed Read Service** | 피드 조회 전용 마이크로서비스 | Java 17 | ✅ Active |
| **Notice Feed** | 공지사항 피드 조회 | Java 17 | ✅ Active |
| **Reels Feed** | 릴스 피드 조회 (멤버십 체크) | Java 17 | ✅ Active |
| **FanFeed** | 팬 피드 조회 | Java 17 | ✅ Active |
| **About Feed** | 소개 피드 조회 | Java 17 | ✅ Active |

---

## 🚀 Quick Start

### **Prerequisites**
- Java 17+
- Gradle 8.4+
- MongoDB
- Apache Kafka
- Netflix Eureka Server

### **Local Development**

```bash
# 1. 프로젝트 클론
git clone <repository-url>
cd feed_reed_service

# 2. 의존성 설치
./gradlew build

# 3. 애플리케이션 실행
./gradlew bootRun
```
### **API 문서**
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI Spec**: `http://localhost:8080/v3/api-docs`

---

## 📁 프로젝트 구조 (Layered Architecture)

```
feed_reed_service/
├── src/
│   ├── main/
│   │   ├── java/back/vybz/feed_read_service/
│   │   │
│   │   │   ├── feed/
│   │   │   │   ├── presentation/      # 💡 Presentation Layer (컨트롤러)
│   │   │   │   │   ├── NoticeReadController.java
│   │   │   │   │   ├── ReelsReadController.java
│   │   │   │   │   ├── FanFeedReadController.java
│   │   │   │   │   └── AboutReadController.java
│   │   │   │   │
│   │   │   │   ├── application/       # ⚙️ Application Layer (서비스)
│   │   │   │   │   └── service/
│   │   │   │   │       ├── NoticeReadService.java / Impl
│   │   │   │   │       ├── ReelsReadService.java / Impl
│   │   │   │   │       ├── FanFeedReadService.java / Impl
│   │   │   │   │       └── AboutReadService.java / Impl
│   │   │   │   │
│   │   │   │   ├── domain/            # 🧩 Domain Layer (도메인 모델)
│   │   │   │   │   ├── FeedRead.java
│   │   │   │   │   ├── WriterType.java, FeedType.java 등
│   │   │   │   │
│   │   │   │   ├── vo/                # 🧾 VO (Value Object, 값 객체)
│   │   │   │   │   └── response/
│   │   │   │   │       ├── ResponseScrollNoticeVo.java
│   │   │   │   │       ├── ResponseScrollReelsVo.java
│   │   │   │   │       ├── ResponseScrollFanFeedVo.java
│   │   │   │   │       ├── ResponseNoticeVo.java 등
│   │   │   │   │
│   │   │   │   ├── infrastructure/    # 🗄 Infrastructure Layer (DB, 외부 연동)
│   │   │   │   │   ├── NoticeReadRepository.java / Custom
│   │   │   │   │   ├── ReelsReadRepository.java / Custom
│   │   │   │   │   ├── FanFeedReadRepository.java / Custom
│   │   │   │   │   ├── AboutReadRepository.java
│   │   │   │   │   └── feign/
│   │   │   │   │       └── MembershipFeignClient.java
│   │   │   │   │
│   │   │   │   ├── dto/               # 📦 DTO (Request/Response)
│   │   │   │   │   ├── request/
│   │   │   │   │   │   ├── RequestScrollNoticeDto.java
│   │   │   │   │   │   ├── RequestScrollReelsDto.java
│   │   │   │   │   │   └── RequestScrollFanFeedDto.java
│   │   │   │   │   └── response/
│   │   │   │   │       ├── ResponseNoticeDto.java
│   │   │   │   │       ├── ResponseScrollReelsDto.java
│   │   │   │   │       ├── ResponseScrollFanFeedDto.java
│   │   │   │   │       ├── ResponseAboutDto.java 등
│   │   │   │   │
│   │   │   │   └── (기타: kafka/ 등)
│   │   │   │
│   │   │   ├── common/                # 🛠 공통 유틸, 예외, 설정, 엔티티
│   │   │   │   ├── config/
│   │   │   │   │   ├── FeignConfig.java, SwaggerConfig.java 등
│   │   │   │   ├── util/
│   │   │   │   │   └── CursorPage.java
│   │   │   │   ├── exception/
│   │   │   │   │   ├── BaseException.java, BaseExceptionHandler.java 등
│   │   │   │   └── entity/
│   │   │   │       └── BaseResponseEntity.java 등
│   │   │   │
│   │   │   └── FeedReedServiceApplication.java
│   │   └── resources/                 # 설정 파일 (application.yml 등)
│   └── test/                          # 테스트 코드
```

---

### 🏗️ 레이어별 설명

- **Presentation Layer (프레젠테이션 레이어)**  
  💡 API 요청/응답을 담당하는 Controller 클래스  
  예: `NoticeReadController`, `ReelsReadController` 등

- **Application Layer (애플리케이션 레이어)**  
  ⚙️ 비즈니스 로직을 조합하고, 트랜잭션/흐름 제어  
  예: `NoticeReadServiceImpl`, `ReelsReadServiceImpl` 등

- **Domain Layer (도메인 레이어)**  
  🧩 핵심 도메인 모델(엔티티, 도메인 서비스 등)  
  예: `FeedRead`, `WriterType`, `FeedType` 등

- **VO (Value Object, 값 객체)**  
  🧾 도메인 내에서 불변의 값을 표현하는 객체  
  예: `ResponseScrollNoticeVo`, `ResponseScrollReelsVo` 등

- **Infrastructure Layer (인프라스트럭처 레이어)**  
  🗄 DB 접근, 외부 API, Kafka 등 인프라 연동  
  예: `NoticeReadRepository`, `MembershipFeignClient` 등

- **DTO**  
  📦 계층 간 데이터 전달을 위한 객체  
  예: `RequestScrollNoticeDto`, `ResponseNoticeDto` 등

- **Common**  
  🛠 공통 유틸리티, 예외, 설정, 엔티티  
  예: `CursorPage`, `BaseExceptionHandler`, `BaseResponseEntity` 등

---

이 구조는 **관심사의 분리**와 **유지보수성**을 높이기 위한 전형적인 레이어드 아키텍처 패턴을 따릅니다.

---

## 🔧 주요 기능

### **피드 타입별 조회**
- **Notice**: 공지사항 피드 조회
- **Reels**: 릴스 피드 조회 (멤버십 권한 체크)
- **FanFeed**: 팬 피드 조회
- **About**: 소개 피드 조회

### **무한 스크롤 지원**
- Cursor 기반 페이지네이션
- 성능 최적화된 조회

### **실시간 데이터 동기화**
- Kafka 이벤트 기반 동기화
- 피드 생성/수정/삭제 이벤트 처리

### **멤버십 통합**
- OpenFeign을 통한 멤버십 서비스 연동
- 실시간 멤버십 상태 확인

---

## 🎯 API 엔드포인트

### **Notice Feed**
```
GET /api/v1/notices                    # 공지사항 목록 조회
GET /api/v1/notices/{noticeId}         # 공지사항 상세 조회
GET /api/v1/buskers/{buskerUuid}/notices # 특정 버스커 공지사항 조회
```

### **Reels Feed**
```
GET /api/v1/reels                      # 릴스 목록 조회
GET /api/v1/reels/{reelsId}            # 릴스 상세 조회
GET /api/v1/buskers/{buskerUuid}/reels # 특정 버스커 릴스 조회
```

### **FanFeed**
```
GET /api/v1/fanfeeds                   # 팬피드 목록 조회
GET /api/v1/fanfeeds/{fanfeedId}       # 팬피드 상세 조회
GET /api/v1/buskers/{buskerUuid}/fanfeeds # 특정 버스커 팬피드 조회
```

### **About Feed**
```
GET /api/v1/abouts                     # 소개 목록 조회
GET /api/v1/abouts/{aboutId}           # 소개 상세 조회
GET /api/v1/buskers/{buskerUuid}/abouts # 특정 버스커 소개 조회
```

---

## 🔄 Kafka 이벤트

### **구독 이벤트**
- `about-create-event`
- `about-update-event`
- `fanfeed-create-event`
- `fanfeed-update-event`
- `notice-create-event`
- `notice-update-event`
- `reels-create-event`
- `reels-update-event`
- `feed-delete-event`
- `comment-count-event`
- `feed-like-count-result-event`


---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📞 Contact

- **Project Link**: [https://github.com/your-username/feed_reed_service](https://github.com/2-BackStage)
- **Team**: VYBZ Development Team

---

<div align="center">

**Made with ❤️ by VYBZ Team**

</div>
