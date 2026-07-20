-- =====================================================
-- Customer 더미 데이터 (20개)
-- =====================================================
INSERT INTO ecommerce_monolithic.customers (name, email, age, phone_number, address, grade, is_deleted, created_at, created_by, updated_at, updated_by)
VALUES
    ('김민준', 'minjun.kim@example.com', 28, '010-1234-5678', '서울시 강남구 테헤란로 123', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('이서연', 'seoyeon.lee@example.com', 34, '010-2345-6789', '서울시 서초구 반포대로 456', 'VIP', false, NOW(), 'system', NOW(), 'system'),
    ('박지훈', 'jihoon.park@example.com', 22, '010-3456-7890', '경기도 성남시 분당구 판교로 789', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('최수아', 'sua.choi@example.com', 41, '010-4567-8901', '인천시 남동구 구월로 321', 'VIP', false, NOW(), 'system', NOW(), 'system'),
    ('정도윤', 'doyoon.jung@example.com', 19, '010-5678-9012', '부산시 해운대구 해운대로 654', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('한예은', 'yeeun.han@example.com', 30, '010-6789-0123', '대구시 수성구 달구벌대로 987', 'BASIC', true, NOW(), 'system', NOW(), 'system'),
    ('오민서', 'minseo.oh@example.com', 26, '010-7890-1234', '광주시 서구 상무대로 111', 'VIP', true, NOW(), 'system', NOW(), 'system'),
    ('신준혁', 'junhyeok.shin@example.com', 38, '010-8901-2345', '대전시 유성구 대학로 222', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('임지아', 'jia.lim@example.com', 25, '010-9012-3456', '울산시 남구 삼산로 333', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('강현우', 'hyunwoo.kang@example.com', 45, '010-0123-4567', '서울시 마포구 홍익로 444', 'VIP', false, NOW(), 'system', NOW(), 'system'),
    ('윤채원', 'chaewon.yoon@example.com', 31, '010-1357-2468', '경기도 수원시 영통구 광교로 555', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('장태양', 'taeyang.jang@example.com', 27, '010-2468-3579', '서울시 은평구 통일로 666', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('배나은', 'naeun.bae@example.com', 33, '010-3579-4680', '경기도 고양시 일산동구 중앙로 777', 'VIP', false, NOW(), 'system', NOW(), 'system'),
    ('조현진', 'hyunjin.jo@example.com', 20, '010-4680-5791', '서울시 노원구 동일로 888', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('권지수', 'jisu.kwon@example.com', 36, '010-5791-6802', '인천시 부평구 부평대로 999', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('문하늘', 'haneul.moon@example.com', 23, '010-6802-7913', '경기도 화성시 동탄대로 101', 'VIP', false, NOW(), 'system', NOW(), 'system'),
    ('남승현', 'seunghyun.nam@example.com', 42, '010-7913-8024', '부산시 동래구 온천로 202', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('송다인', 'dain.song@example.com', 29, '010-8024-9135', '서울시 중구 을지로 303', 'BASIC', false, NOW(), 'system', NOW(), 'system'),
    ('홍준서', 'junseo.hong@example.com', 37, '010-9135-0246', '경기도 용인시 기흥구 동백중앙로 404', 'VIP', false, NOW(), 'system', NOW(), 'system'),
    ('류다영', 'dayoung.ryu@example.com', 24, '010-0246-1357', '서울시 동작구 사당로 505', 'BASIC', false, NOW(), 'system', NOW(), 'system');

-- =====================================================
-- Category 더미 데이터
-- =====================================================
INSERT INTO ecommerce_monolithic.categories (id, name)
VALUES
    (1, '오디오'),
    (2, '웨어러블'),
    (3, 'PC 주변기기'),
    (4, '모니터'),
    (5, '충전기기'),
    (6, '스마트홈'),
    (7, '액세서리'),
    (8, '저장장치');

-- =====================================================
-- vendors 더미 데이터 (20개)
-- =====================================================
INSERT INTO ecommerce_monolithic.vendors
(
    name,
    vendor_status,
    is_deleted,
    created_at,
    created_by,
    updated_at,
    updated_by
)
VALUES
    ('Vendor A',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor B',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor C',  'DISABLED', false, NOW(), 'system', NOW(), 'system'),
    ('Vendor D',  'PENDING',  false, NOW(), 'system', NOW(), 'system'),
    ('Vendor E',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor F',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor G',  'DISABLED', false, NOW(), 'system', NOW(), 'system'),
    ('Vendor H',  'PENDING',  false, NOW(), 'system', NOW(), 'system'),
    ('Vendor I',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor J',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor K',  'DISABLED', false, NOW(), 'system', NOW(), 'system'),
    ('Vendor L',  'PENDING',  false, NOW(), 'system', NOW(), 'system'),
    ('Vendor M',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor N',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor O',  'DISABLED', false, NOW(), 'system', NOW(), 'system'),
    ('Vendor P',  'PENDING',  false, NOW(), 'system', NOW(), 'system'),
    ('Vendor Q',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor R',  'ENABLED',   false, NOW(), 'system', NOW(), 'system'),
    ('Vendor S',  'DISABLED', false, NOW(), 'system', NOW(), 'system'),
    ('Vendor T',  'PENDING',  false, NOW(), 'system', NOW(), 'system');

-- =====================================================
-- Product 더미 데이터 (category_id 추가)
-- =====================================================
INSERT INTO ecommerce_monolithic.products
(name, description, price, vendor_id, category_id, image_url, stock_quantity, is_exposed, is_deleted, created_at, created_by, updated_at, updated_by)
VALUES
    ('무선 블루투스 이어폰', '고음질 블루투스 5.3 이어폰, 최대 30시간 재생', 89000.00, 1, 1, 'https://example.com/images/earphone-bt.jpg', 150, true, false, NOW(), 'system', NOW(), 'system'),
    ('스마트워치 프로', '심박수·혈중산소 측정, GPS 내장 스마트워치', 320000.00, 1, 2, 'https://example.com/images/smartwatch.jpg', 80, true, false, NOW(), 'system', NOW(), 'system'),
    ('노이즈 캔슬링 헤드폰', '업계 최고 수준의 능동형 노이즈 캔슬링 헤드폰', 250000.00, 2, 1, 'https://example.com/images/headphone-nc.jpg', 60, true, false, NOW(), 'system', NOW(), 'system'),
    ('USB-C 멀티포트 허브', 'HDMI·USB-A·SD카드 등 7-in-1 멀티포트 허브', 45000.00, 2, 3, 'https://example.com/images/usb-hub.jpg', 200, true, false, NOW(), 'system', NOW(), 'system'),
    ('기계식 키보드', '적축 스위치 채용, RGB 백라이트 풀배열 기계식 키보드', 135000.00, 3, 3, 'https://example.com/images/keyboard-mech.jpg', 90, true, false, NOW(), 'system', NOW(), 'system'),
    ('무선 충전 패드', 'Qi 규격 15W 고속 무선 충전 패드', 29000.00, 3, 5, 'https://example.com/images/wireless-charger.jpg', 300, true, false, NOW(), 'system', NOW(), 'system'),
    ('4K 웹캠', '4K UHD 해상도, 자동 조명 보정 내장 웹캠', 98000.00, 4, 3, 'https://example.com/images/webcam-4k.jpg', 45, true, false, NOW(), 'system', NOW(), 'system'),
    ('인체공학 마우스', '손목 피로 최소화 설계, 6개 커스텀 버튼 지원', 62000.00, 4, 3, 'https://example.com/images/mouse-ergo.jpg', 120, true, false, NOW(), 'system', NOW(), 'system'),
    ('27인치 모니터', '27인치 QHD IPS 패널, 144Hz 주사율 게이밍 모니터', 480000.00, 5, 4, 'https://example.com/images/monitor-27.jpg', 30, true, false, NOW(), 'system', NOW(), 'system'),
    ('휴대용 보조배터리 20000mAh', '20000mAh 대용량, PD 65W 고속충전 지원 보조배터리', 55000.00, 5, 5, 'https://example.com/images/powerbank.jpg', 180, true, false, NOW(), 'system', NOW(), 'system'),
    ('블루투스 스피커', '360도 서라운드 사운드, IPX7 방수 블루투스 스피커', 75000.00, 1, 1, 'https://example.com/images/speaker-bt.jpg', 95, true, false, NOW(), 'system', NOW(), 'system'),
    ('스마트 홈 허브', 'Wi-Fi·Zigbee·Z-Wave 통합 지원 스마트홈 허브', 149000.00, 2, 6, 'https://example.com/images/smart-hub.jpg', 40, true, false, NOW(), 'system', NOW(), 'system'),
    ('노트북 스탠드', '높이·각도 6단계 조절 가능한 알루미늄 노트북 스탠드', 38000.00, 3, 7, 'https://example.com/images/laptop-stand.jpg', 250, true, false, NOW(), 'system', NOW(), 'system'),
    ('게이밍 마우스패드 XL', '900x400mm 대형 천연 고무 게이밍 마우스패드', 22000.00, 4, 7, 'https://example.com/images/mousepad-xl.jpg', 400, true, false, NOW(), 'system', NOW(), 'system'),
    ('LED 스탠드 조명', '색온도·밝기 무단 조절, USB 전원 LED 스탠드', 48000.00, 5, 6, 'https://example.com/images/led-stand.jpg', 110, true, false, NOW(), 'system', NOW(), 'system'),
    ('포터블 SSD 1TB', '1TB 외장 SSD, USB 3.2 Gen2 최대 1050MB/s', 115000.00, 1, 8, 'https://example.com/images/ssd-1tb.jpg', 75, true, false, NOW(), 'system', NOW(), 'system'),
    ('태블릿 거치대', '11~13인치 호환 360도 회전 알루미늄 태블릿 거치대', 32000.00, 2, 7, 'https://example.com/images/tablet-stand.jpg', 160, true, false, NOW(), 'system', NOW(), 'system'),
    ('USB 마이크', '심장부 콘덴서 채용, 실시간 모니터링 지원 USB 마이크', 88000.00, 3, 3, 'https://example.com/images/usb-mic.jpg', 55, true, false, NOW(), 'system', NOW(), 'system'),
    ('스마트 전구 4개입', '1600만 색상 지원, 앱·음성 제어 스마트 LED 전구 4개입', 42000.00, 4, 6, 'https://example.com/images/smart-bulb.jpg', 220, false, false, NOW(), 'system', NOW(), 'system'),
    ('차량용 무선 충전 거치대', '15W 고속 무선충전, 자동 클램프 차량용 거치대', 67000.00, 5, 5, 'https://example.com/images/car-charger.jpg', 85, true, false, NOW(), 'system', NOW(), 'system');