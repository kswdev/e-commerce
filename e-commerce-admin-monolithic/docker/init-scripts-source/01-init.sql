CREATE DATABASE ecommerce_monolithic
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

CREATE USER 'app'@'%' IDENTIFIED BY 'app123';

GRANT ALL PRIVILEGES ON ecommerce_monolithic.* TO 'app'@'%';

FLUSH PRIVILEGES;