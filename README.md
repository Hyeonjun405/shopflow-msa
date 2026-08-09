# ShopFlow MSA

모놀리식 ShopFlow를 MSA로 전환한 프로젝트  
패턴 기록 및 학습 목적

---

## 전체 구조

```
shopflow-msa/
├── gateway/
├── user-service/
├── product-service/
├── cart-service/
├── order-service/
├── coupon-service/
├── payment-service/
└── docker-compose.yml
```

---

## 서비스 포트

| 서비스 | 포트 |
|---|---|
| Gateway | 8080 |
| User Service | 8081 |
| Product Service | 8082 |
| Cart Service | 8083 |
| Order Service | 8084 |
| Coupon Service | 8085 |
| Payment Service | 8086 |

---

## DB 구성

MySQL 하나에 스키마 분리

| 서비스 | 스키마 |
|---|---|
| User Service | shopflow_user |
| Product Service | shopflow_product |
| Cart Service | shopflow_cart |
| Order Service | shopflow_order |
| Coupon Service | shopflow_coupon |
| Payment Service | shopflow_payment |

---

## 기술 스택

| 항목 | 기술 |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Gateway | Spring Cloud Gateway |
| 메시지큐 | Kafka |
| ORM | Spring Data JPA + Hibernate 6 |
| DB | MySQL 8.0 |
| Migration | Flyway |
| 인증 | JWT (Gateway에서 검증) |
| Infra | Docker Compose |

---

## 서비스간 통신

| 상황 | 방식 |
|---|---|
| 조회 | REST |
| 상태 변경 | Kafka (비동기) |

### Kafka 이벤트 목록

| 이벤트 | 발행 서비스 | 구독 서비스 |
|---|---|---|
| OrderCreated | Order Service | Coupon Service, Product Service |
| OrderCancelled | Order Service | Product Service |
| PaymentCompleted | Payment Service | Order Service |
| PaymentCancelled | Payment Service | Order Service |

---

## 인증 흐름

```
클라이언트
  ↓ Bearer Token
Gateway       → JWT 검증
  ↓ X-User-Id, X-User-Role 헤더로 전달
각 서비스     → 헤더에서 유저 정보 추출
```

---

## 실행 방법

```bash
docker compose up -d
```

---

## 개발 순서

- [ ] Gateway 구성
- [ ] User Service
- [ ] Product Service (Category 포함)
- [ ] Coupon Service
- [ ] Cart Service
- [ ] Order Service
- [ ] Payment Service
- [ ] Kafka 이벤트 연동