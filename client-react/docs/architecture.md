# Architecture

## Entry Flow

`index.html` → `src/index.tsx` (ReactDOM.render + Bootstrap import) → `App.tsx` → `routes.tsx`

## Directory Conventions

- `src/pages/` — Route-level page components, each in their own subdirectory with `index.tsx`
- `src/components/` — Reusable UI components, organized by feature (e.g., `Navigation/Header/`, `Navigation/Footer/`)
- `src/routes.tsx` — Single source of truth for all route definitions using React Router v6 `<Routes>`/`<Route>`

## Adding Routes

Define new routes in `src/routes.tsx`. Create the corresponding page under `src/pages/<PageName>/index.tsx`. The router uses `BrowserRouter` with standard v6 `<Routes>` nesting.

## API Calls

No service layer exists yet. Axios is installed — when adding API calls, create a service module (e.g., `src/services/`) rather than calling Axios directly from components.

## Data Strategy

백엔드 구축 전까지 `/data/*.json` 파일을 더미 데이터로 사용한다.

- MCP를 통한 DB/외부 API 직접 연동은 백엔드 완성 후로 보류
- `src/services/` 레이어는 추후 실제 API로 교체를 염두에 두고 설계 (더미 데이터와 실제 API를 같은 인터페이스로 추상화)
- 더미 데이터 파일 위치: 프로젝트 루트 `/data/` 하위 (예: `/data/products.json`, `/data/orders.json`)
