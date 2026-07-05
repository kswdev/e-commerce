# ADR 001: 상태관리 라이브러리 선택

## 상태
채택됨

## 컨텍스트
이커머스 앱에서 장바구니(빈번한 상태 변경)와 인증(전역 상태) 두 가지 상태관리가 필요해졌다.
Context API와 Zustand 중 선택이 필요했다.

## 결정
**Zustand v4** 채택.

## 이유
- 장바구니 수량 변경이 빈번 → Context API는 구독 컴포넌트 전체를 리렌더링하지만, Zustand는 해당 값을 사용하는 컴포넌트만 리렌더링
- Provider 중첩 없이 훅 하나로 어디서든 접근 가능
- TypeScript 타입 자동 추론
- Zustand v5는 `@types/react >= 18` 요구 → 현재 프로젝트(React 16)와 호환되는 v4 설치

## 결과
- `src/store/cartStore.ts` — 장바구니 상태
- `src/store/authStore.ts` — 인증 상태
- 추후 백엔드 연동 시 스토어 내부 로직만 교체하면 됨 (컴포넌트 변경 불필요)
