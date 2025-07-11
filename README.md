# VYBZ Feed Read Service

VYBZ 플랫폼의 피드(About, FanFeed, Notice, Reels) 조회 및 이벤트 처리를 담당하는 마이크로서비스입니다.

## 📋 목차

- [개요](#개요)
- [기술 스택](#기술-스택)
- [주요 기능](#주요-기능)
- [프로젝트 구조](#프로젝트-구조)
- [API 문서](#api-문서)
- [설치 및 실행](#설치-및-실행)
- [환경 설정](#환경-설정)
- [피드 시스템](#피드-시스템)
- [이벤트 처리](#이벤트-처리)
- [아키텍처](#아키텍처)
- [개발 가이드](#개발-가이드)
- [트러블슈팅](#트러블슈팅)
- [라이선스](#라이선스)
- [팀](#팀)

---

## 🎯 개요

VYBZ Feed Read Service는 다음과 같은 기능을 제공합니다:

- **피드 조회**: About, FanFeed, Notice, Reels 조회 및 무한스크롤 지원
- **이벤트 처리**: Kafka를 통한 피드 관련 이벤트 수신 및 처리
- **데이터 저장**: MongoDB를 통한 피드 데이터 저장
- **Swagger**: API 문서 자동화
- **Eureka Client**: 서비스 디스커버리 지원

---

## 🛠 기술 스택

### Backend

![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

### Infra

![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Amazon EC2](https://img.shields.io/badge/Amazon_EC2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

### 협업

![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

---

## 🚀 주요 기능

- **피드 조회**: About, FanFeed, Notice, Reels 조회 및 상세 정보 제공
- **무한스크롤**: 커서 기반 페이징으로 효율적인 데이터 로딩
- **정렬 기능**: 최신순, 좋아요순, 댓글순 정렬 지원
- **Kafka 이벤트 처리**: 피드 생성/수정/삭제 이벤트 수신 및 처리
- **좋아요/댓글 카운트 동기화**: 실시간 카운트 업데이트
- **Swagger API 문서 제공**

---

## 📁 프로젝트 구조

```
src/main/java/back/vybz/feed_read_service/
├── common/                    # 공통 모듈
│   ├── config/               # 설정 클래스
│   │   ├── FeignConfig.java
│   │   ├── MongoConfig.java
│   │   ├── ObjectMapperConfig.java
│   │   └── SwaggerConfig.java
│   ├── entity/               # 공통 응답 엔티티
│   │   ├── BaseResponseEntity.java
│   │   └── BaseResponseStatus.java
│   ├── exception/            # 예외 처리
│   │   ├── AsyncExceptionHandler.java
│   │   ├── BaseException.java
│   │   ├── BaseExceptionHandler.java
│   │   ├── BaseExceptionHandlerFilter.java
│   │   └── BaseResponseStatus.java
│   └── util/                 # 유틸리티
│       └── CursorPage.java
├── feed/                      # 피드 도메인
│   ├── application/          # 서비스 계층
│   │   └── service/
│   │       ├── AboutReadService.java
│   │       ├── AboutReadServiceImpl.java
│   │       ├── FanFeedReadService.java
│   │       ├── FanFeedReadServiceImpl.java
│   │       ├── NoticeReadService.java
│   │       ├── NoticeReadServiceImpl.java
│   │       ├── ReelsReadService.java
│   │       └── ReelsReadServiceImpl.java
│   ├── domain/               # 도메인 모델
│   │   ├── FeedFile.java
│   │   ├── FeedRead.java
│   │   ├── FeedType.java
│   │   ├── FileType.java
│   │   ├── HumanType.java
│   │   ├── TaggedHuman.java
│   │   └── WriterType.java
│   ├── dto/                  # DTO 계층
│   │   ├── request/
│   │   │   ├── RequestScrollFanFeedDto.java
│   │   │   ├── RequestScrollNoticeDto.java
│   │   │   └── RequestScrollReelsDto.java
│   │   └── response/
│   │       ├── ResponseAboutDto.java
│   │       ├── ResponseFanFeedDto.java
│   │       ├── ResponseNoticeDto.java
│   │       ├── ResponseScrollFanFeedDto.java
│   │       ├── ResponseScrollNoticeDto.java
│   │       └── ResponseScrollReelsDto.java
│   ├── infrastructure/       # 리포지토리 계층
│   │   ├── AboutReadRepository.java
│   │   ├── FanFeedReadRepository.java
│   │   ├── FanFeedReadRepositoryCustom.java
│   │   ├── FanFeedReadRepositoryCustomImpl.java
│   │   ├── NoticeReadRepository.java
│   │   ├── NoticeReadRepositoryCustom.java
│   │   ├── NoticeReadRepositoryCustomImpl.java
│   │   ├── ReelsReadRepository.java
│   │   ├── ReelsReadRepositoryCustom.java
│   │   ├── ReelsReadRepositoryCustomImpl.java
│   │   └── feign/
│   │       └── MembershipFeignClient.java
│   ├── presentation/         # REST 컨트롤러
│   │   ├── AboutReadController.java
│   │   ├── FanFeedReadController.java
│   │   ├── NoticeReadController.java
│   │   └── ReelsReadController.java
│   └── vo/                   # VO 계층
│       └── response/
│           ├── ResponseAboutVo.java
│           ├── ResponseFanFeedVo.java
│           ├── ResponseNoticeVo.java
│           ├── ResponseScrollFanFeedVo.java
│           ├── ResponseScrollNoticeVo.java
│           └── ResponseScrollReelsVo.java
├── kafka/                    # Kafka 이벤트 처리
│   ├── config/               # Kafka 설정
│   │   ├── AboutEventConfig.java
│   │   ├── CommentCountEventConfig.java
│   │   ├── CommonKafkaConfig.java
│   │   ├── FanFeedEventConfig.java
│   │   ├── FeedDeleteEventConfig.java
│   │   ├── FeedLikeCountResultEventConfig.java
│   │   ├── NoticeEventConfig.java
│   │   └── ReelsEventConfig.java
│   ├── consumer/             # 이벤트 컨슈머
│   │   ├── AboutEventConsumer.java
│   │   ├── CommentCountEventConsumer.java
│   │   ├── FanFeedEventConsumer.java
│   │   ├── FeedDeleteEventConsumer.java
│   │   ├── FeedLikeCountResultEventConsumer.java
│   │   ├── NoticeEventConsumer.java
│   │   └── ReelsEventConsumer.java
│   └── event/                # 이벤트 모델
│       ├── AboutCreateEvent.java
│       ├── AboutUpdateEvent.java
│       ├── CommentCountEvent.java
│       ├── FanFeedCreateEvent.java
│       ├── FanFeedUpdateEvent.java
│       ├── FeedDeleteEvent.java
│       ├── FeedLikeCountResultEvent.java
│       ├── NoticeCreateEvent.java
│       ├── NoticeUpdateEvent.java
│       ├── ReelsCreateEvent.java
│       └── ReelsUpdateEvent.java
└── FeedReedServiceApplication.java # 메인 클래스
```

---

## 📚 API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다:

- **URL**: `http://localhost:8000/feed-read-service/swagger-ui/index.html`
- **API 그룹**: 
  - ABOUT-READ-SERVICE
  - FAN-FEED-READ-SERVICE
  - BUSKER-SERVICE
  - REELS-READ

### 주요 API 엔드포인트

#### About (자기소개)
- `GET /api/v1/read/feed/about/{aboutId}` - 자기소개 단건 조회

#### FanFeed (팬피드)
- `GET /api/v1/read/feed/fan` - 팬피드 목록 무한스크롤 조회
- `GET /api/v1/read/feed/fan/{writerUuid}` - 사용자 피드 무한스크롤 조회
- `GET /api/v1/read/feed/fan/{fanFeedId}` - 팬피드 상세 조회

#### Notice (공지)
- `GET /api/v1/read/feed/notice` - 공지 목록 무한스크롤 조회
- `GET /api/v1/read/feed/notice/{noticeId}` - 공지 상세 조회

#### Reels (릴스)
- `GET /api/v1/read/feed/reels` - 전체 릴스 무한스크롤 조회
- `GET /api/v1/read/feed/reels/{writerUuid}` - 버스커 릴스 무한스크롤 조회

---

## 🚀 설치 및 실행

### 1. 사전 요구사항

- Java 17
- Gradle 8.4+
- Docker (선택사항)
- MongoDB 6.0+
- Kafka 3.0+
- Eureka Server

### 2. 로컬 실행

```bash
# 프로젝트 클론
git clone <repository-url>
cd vybz-feed-read

# Gradle 빌드
./gradlew clean build

# 애플리케이션 실행
./gradlew bootRun
```

### 3. Docker 실행

```bash
# Docker 이미지 빌드
docker build -t vybz-feed-read .

# Docker 컨테이너 실행
docker run -p 8000:8000 vybz-feed-read
```

---

## ⚙️ 환경 설정

### 주요 설정 파일

- `application.yml`: 기본 설정

### 환경 변수 예시

```yaml
kafka:
    bootstrap-servers: ${KAFKA_SERVERS}

  data:
    mongodb:
      uri: mongodb://${MONGO_USERNAME}:${MONGO_PASSWORD}@${MONGO_HOST}:${MONGO_PORT}/${MONGO_DATABASE}?authSource=admin&replicaSet=myReplicaSet
```

---

## 📝 피드 시스템

### 피드 타입
- **About**: 버스커의 자기소개
- **FanFeed**: 팬들이 작성하는 피드
- **Notice**: 버스커가 작성하는 공지사항
- **Reels**: 짧은 동영상 콘텐츠

### 주요 기능
- **커서 기반 페이징**: CursorPage 유틸리티 활용
- **정렬 옵션**: LATEST, LIKES, COMMENTS
- **무한스크롤**: 효율적인 데이터 로딩
- **작성자 필터링**: 특정 사용자의 피드만 조회

### 도메인 모델
- **FeedFile**: 피드 첨부 파일 (이미지, 비디오)
- **TaggedHuman**: 태그된 사용자 정보
- **WriterType**: 작성자 타입 (BUSKER, FAN)
- **FeedType**: 피드 타입 구분

---

## 📡 이벤트 처리

### Kafka 토픽
- `about-create`, `about-update`
- `fanfeed-create`, `fanfeed-update`
- `notice-create`, `notice-update`
- `reels-create`, `reels-update`
- `feed-delete`
- `comment-count`
- `feed-like-count`

### 이벤트 처리 흐름
1. **피드 생성/수정 이벤트 수신**: MongoDB에 피드 데이터 저장/업데이트
2. **피드 삭제 이벤트 수신**: MongoDB에서 피드 데이터 삭제
3. **좋아요/댓글 카운트 이벤트 수신**: 실시간 카운트 업데이트

### 컨슈머 그룹
- `create-feed-read-{type}-group`
- `update-feed-read-{type}-group`
- `feed-read-delete-group`
- `comment-count-group`
- `feed-like-count-group`

---

## 🏗 아키텍처

### 도메인 주도 설계 (DDD)

- **Domain Layer**: 피드 도메인 모델과 비즈니스 로직
- **Application Layer**: 서비스 로직과 유스케이스
- **Infrastructure Layer**: MongoDB, Kafka 등 외부 시스템 연동
- **Presentation Layer**: REST API 컨트롤러

### 마이크로서비스 패턴

- **Event-Driven**: Kafka를 통한 비동기 이벤트 처리
- **CQRS**: 읽기 전용 서비스로 최적화
- **Service Discovery**: Eureka를 통한 서비스 등록/발견

### 데이터 흐름

```
Feed Service (Write) → Kafka → Feed Read Service → MongoDB (Read)
```

---

## 🔧 개발 가이드

### 패키지 구조
- **도메인별 계층 분리**: About, FanFeed, Notice, Reels
- **계층별 패키지**: presentation, application, domain, infrastructure
- **공통 모듈**: common 패키지로 공통 기능 분리

### 네이밍 규칙
- **Controller**: `{Type}ReadController`
- **Service**: `{Type}ReadService`, `{Type}ReadServiceImpl`
- **Repository**: `{Type}ReadRepository`
- **DTO**: `Request{Action}{Type}Dto`, `Response{Action}{Type}Dto`

### 예외 처리
- **BaseException**: 통일된 예외 처리
- **BaseExceptionHandler**: 글로벌 예외 핸들러
- **BaseResponseEntity**: 표준화된 응답 형식

### 로깅
- **Slf4j**: 구조화된 로깅
- **이벤트 로깅**: 이모지를 활용한 가독성 향상

---

## 🚨 트러블슈팅

### MongoDB 연결 오류

```bash
# MongoDB 연결 확인
mongo mongodb://vybz:<비밀번호>@<탄력적 IP>:27020/vybz?authSource=admin&replicaSet=myReplicaSet
```

### Kafka 연결 오류

```bash
# Kafka 브로커 상태 확인
kafka-topics.sh --bootstrap-server <탄력적 IP>:10000 --list
```

### 로그 확인

```bash
# 애플리케이션 로그 확인
tail -f logs/application.log

# 에러 로그 확인
grep "ERROR" logs/application.log

# Kafka 이벤트 로그 확인
grep "🟢\|🟡\|🗑️\|🔥" logs/application.log
```

### 일반적인 문제 해결

1. **포트 충돌**: `server.port: 0`으로 랜덤 포트 사용
2. **메모리 부족**: JVM 힙 메모리 설정 조정
3. **네트워크 타임아웃**: MongoDB/Kafka 연결 타임아웃 설정

---

## 📝 라이선스

이 프로젝트는 VYBZ 팀의 내부 프로젝트입니다.

---

## 👥 팀

- **개발팀**: VYBZ Backend Team

---

**VYBZ Feed Read Service** - 피드 조회 및 이벤트 처리 서비스
