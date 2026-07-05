# Project Roadmap

이커머스 UI를 만들면서 Claude Code의 핵심 기능을 단계별로 체득하는 로드맵.
각 Phase마다 "만들 것"과 "배울 것"이 1:1로 매핑된다.

---

## Phase 1 — 프로젝트 골격 × Context 분리 심화
**만들 것:** 전체 페이지 라우팅 구조 (상품목록, 상품상세, 장바구니, 주문, 로그인)

**배울 것:**
- CLAUDE.md / docs/ 계층 설계 및 관리 패턴
- Claude Memory 시스템 — 설계 결정을 세션 간 기억시키는 방법

---

## Phase 2 — 컴포넌트 개발 × Agent 병렬 활용
**만들 것:** ProductCard, Header, Footer, Layout 컴포넌트

**배울 것:**
- Explore Agent — 유사 패턴 탐색 위임
- Plan Agent — 컴포넌트 구조 설계 전 트레이드오프 검토
- 여러 컴포넌트를 동시에 만들 때 병렬 Agent 활용

---

## Phase 3 — 상태관리 도입 × Plan Mode 심화
**만들 것:** 장바구니 상태, 인증 상태 (Zustand 또는 Context API)

**배울 것:**
- 아키텍처 결정을 Plan Mode에서 함께 분석하는 방법
- 계획 → 승인 → 실행 분리가 중요한 이유
- `docs/decisions/` ADR(Architecture Decision Record) 패턴

---

## Phase 4 — API 연동 × Skills (커스텀 슬래시 명령)
**만들 것:** 상품 목록 / 장바구니 서비스 레이어 (`src/services/`)

**배울 것:**
- 반복 작업을 `/skill`로 추출하는 방법
- 팀 공유 skill vs 개인 skill 차이
- skill 추출 기준

---

## Phase 5 — 자동화 × Hooks
**만들 것:** 빌드 검증, 타입 체크 자동화

**배울 것:**
- PreToolUse / PostToolUse Hook 설정
- `/update-config` skill로 hook 관리

---

## Phase 6 — 고급 기능 × MCP 서버 _(보류)_
**만들 것:** 상품 검색, 필터, 주문 내역

**배울 것:**
- MCP(Model Context Protocol) — Claude가 브라우저/DB/외부 API에 직접 접근하는 방법
- Playwright MCP로 만든 화면을 Claude가 직접 테스트하는 워크플로우

> **보류 사유:** 백엔드 미완성. 그 전까지는 `/data/*.json` 더미 데이터로 대체.
> 백엔드 완성 후 이 Phase를 재개한다.

---

## 현재 상태

- [x] Phase 0: 프로젝트 초기화, CLAUDE.md / docs/ 구조 설정
- [x] Phase 1: 라우팅 골격 (ProductList, ProductDetail, Cart, Order, Login)
- [x] Phase 2: 컴포넌트 개발 (Layout, Header, Footer, ProductCard) + 병렬 Agent 실습
- [x] Phase 3: 상태관리 도입 (Zustand v4, cartStore/authStore) + ADR 작성
- [x] Phase 4: 서비스 레이어 (productService) + 커스텀 Skill (/new-page)
- [x] Phase 5: 자동화 × Hooks (PostToolUse → tsc --noEmit 자동 실행)
- [ ] Phase 6: MCP 서버 _(보류 — 백엔드 완성 후 재개)_
