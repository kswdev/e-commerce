
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
-- Product 더미 데이터 (category_id 추가)
-- =====================================================
INSERT INTO ecommerce_monolithic.products
(name, price, vendor_id, category_id, image_url, stock_quantity, is_exposed, is_deleted, created_at, created_by, updated_at, updated_by)
VALUES
    ('무선 블루투스 이어폰', 89000.00, 1, 1, 'https://example.com/images/earphone-bt.jpg', 150, true, false, NOW(), 'system', NOW(), 'system'),
    ('스마트워치 프로', 320000.00, 1, 2, 'https://example.com/images/smartwatch.jpg', 80, true, false, NOW(), 'system', NOW(), 'system'),
    ('노이즈 캔슬링 헤드폰', 250000.00, 2, 1, 'https://example.com/images/headphone-nc.jpg', 60, true, false, NOW(), 'system', NOW(), 'system'),
    ('USB-C 멀티포트 허브', 45000.00, 2, 3, 'https://example.com/images/usb-hub.jpg', 200, true, false, NOW(), 'system', NOW(), 'system'),
    ('기계식 키보드', 135000.00, 3, 3, 'https://example.com/images/keyboard-mech.jpg', 90, true, false, NOW(), 'system', NOW(), 'system'),
    ('무선 충전 패드', 29000.00, 3, 5, 'https://example.com/images/wireless-charger.jpg', 300, true, false, NOW(), 'system', NOW(), 'system'),
    ('4K 웹캠', 98000.00, 4, 3, 'https://example.com/images/webcam-4k.jpg', 45, true, false, NOW(), 'system', NOW(), 'system'),
    ('인체공학 마우스', 62000.00, 4, 3, 'https://example.com/images/mouse-ergo.jpg', 120, true, false, NOW(), 'system', NOW(), 'system'),
    ('27인치 모니터', 480000.00, 5, 4, 'https://example.com/images/monitor-27.jpg', 30, true, false, NOW(), 'system', NOW(), 'system'),
    ('휴대용 보조배터리 20000mAh', 55000.00, 5, 5, 'https://example.com/images/powerbank.jpg', 180, true, false, NOW(), 'system', NOW(), 'system'),
    ('블루투스 스피커', 75000.00, 1, 1, 'https://example.com/images/speaker-bt.jpg', 95, true, false, NOW(), 'system', NOW(), 'system'),
    ('스마트 홈 허브', 149000.00, 2, 6, 'https://example.com/images/smart-hub.jpg', 40, true, false, NOW(), 'system', NOW(), 'system'),
    ('노트북 스탠드', 38000.00, 3, 7, 'https://example.com/images/laptop-stand.jpg', 250, true, false, NOW(), 'system', NOW(), 'system'),
    ('게이밍 마우스패드 XL', 22000.00, 4, 7, 'https://example.com/images/mousepad-xl.jpg', 400, true, false, NOW(), 'system', NOW(), 'system'),
    ('LED 스탠드 조명', 48000.00, 5, 6, 'https://example.com/images/led-stand.jpg', 110, true, false, NOW(), 'system', NOW(), 'system'),
    ('포터블 SSD 1TB', 115000.00, 1, 8, 'https://example.com/images/ssd-1tb.jpg', 75, true, false, NOW(), 'system', NOW(), 'system'),
    ('태블릿 거치대', 32000.00, 2, 7, 'https://example.com/images/tablet-stand.jpg', 160, true, false, NOW(), 'system', NOW(), 'system'),
    ('USB 마이크', 88000.00, 3, 3, 'https://example.com/images/usb-mic.jpg', 55, true, false, NOW(), 'system', NOW(), 'system'),
    ('스마트 전구 4개입', 42000.00, 4, 6, 'https://example.com/images/smart-bulb.jpg', 220, false, false, NOW(), 'system', NOW(), 'system'),
    ('차량용 무선 충전 거치대', 67000.00, 5, 5, 'https://example.com/images/car-charger.jpg', 85, true, false, NOW(), 'system', NOW(), 'system');

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