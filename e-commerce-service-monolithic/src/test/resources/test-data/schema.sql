-- =====================================================
-- Schema
-- =====================================================
CREATE SCHEMA IF NOT EXISTS ecommerce_monolithic;

-- =====================================================
-- customers 테이블
-- =====================================================
CREATE TABLE IF NOT EXISTS ecommerce_monolithic.customers
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    age          INT,
    phone_number VARCHAR(20),
    address      VARCHAR(500),
    grade        VARCHAR(20)  NOT NULL DEFAULT 'BASIC',
    is_deleted   BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at   TIMESTAMP,
    created_by   VARCHAR(100),
    updated_at   TIMESTAMP,
    updated_by   VARCHAR(100),
    PRIMARY KEY (id)
);

-- =====================================================
-- categories 테이블
-- =====================================================
CREATE TABLE IF NOT EXISTS ecommerce_monolithic.categories
(
    id   BIGINT       NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

-- =====================================================
-- vendors 테이블
-- =====================================================
CREATE TABLE IF NOT EXISTS ecommerce_monolithic.vendors
(
    id            BIGINT      NOT NULL AUTO_INCREMENT,
    name          VARCHAR(255) NOT NULL,
    vendor_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    is_deleted    BOOLEAN     NOT NULL DEFAULT FALSE,
    created_at    TIMESTAMP,
    created_by    VARCHAR(100),
    updated_at    TIMESTAMP,
    updated_by    VARCHAR(100),
    PRIMARY KEY (id)
);

-- =====================================================
-- products 테이블
-- =====================================================
CREATE TABLE IF NOT EXISTS ecommerce_monolithic.products
(
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    name           VARCHAR(255)   NOT NULL,
    price          DECIMAL(15, 2) NOT NULL,
    vendor_id      BIGINT         NOT NULL,
    category_id    BIGINT         NOT NULL,
    image_url      VARCHAR(500),
    stock_quantity INT            NOT NULL DEFAULT 0,
    is_exposed     BOOLEAN        NOT NULL DEFAULT TRUE,
    is_deleted     BOOLEAN        NOT NULL DEFAULT FALSE,
    created_at     TIMESTAMP,
    created_by     VARCHAR(100),
    updated_at     TIMESTAMP,
    updated_by     VARCHAR(100),
    PRIMARY KEY (id),
    CONSTRAINT fk_products_vendor FOREIGN KEY (vendor_id) REFERENCES ecommerce_monolithic.vendors (id),
    CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES ecommerce_monolithic.categories (id)
);