# 🚀 고가용성 백엔드 아키텍처 설계 및 성능 검증 프로젝트

본 프로젝트는 트래픽 증가에 유연하게 대응할 수 있는 **수평 확장(Scale-out) 가능한 고가용성 백엔드 시스템**을 설계하고, 실제 지표(TPS, Latency)를 통해 그 효용성을 검증하는 것을 목표로 합니다.

## 📌 핵심 목표
- **Stateless 아키텍처 구현**: Docker와 Spring Boot를 이용한 서버 무상태성 보장.
- **수평 확장(Scale-out)**: Nginx 로드밸런서를 통한 트래픽 분산 및 고가용성 확보.
- **성능 지표 검증**: k6를 활용한 부하 테스트로 개선 전/후 데이터 분석 (TPS, Response Time).

## 🛠 Tech Stack
- **Framework**: Spring Boot 3.x, Spring Data JPA
- **Database**: MySQL 8.x
- **Infrastructure**: Docker, Nginx
- **Testing**: k6 (Performance Testing)

## 📊 현재 진행 상황 (Baseline)
- [x] 단일 서버 CRUD 및 DB 연동 완료
- [x] PRG(Post-Redirect-Get) 패턴 적용
- [x] k6를 이용한 단일 노드 성능 측정 완료 (VUs 100 기준 평균 응답 2.24s)

## 🏃 실행 방법 (Docker)
```bash
# 추후 작성 예정
docker-compose up -d
```
