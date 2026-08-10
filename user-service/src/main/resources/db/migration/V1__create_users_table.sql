CREATE TABLE users (
       id           BIGINT       NOT NULL AUTO_INCREMENT,
       email        VARCHAR(255) NOT NULL,
       password     VARCHAR(255) NOT NULL,
       name         VARCHAR(100) NOT NULL,
       phone_number VARCHAR(20)  NOT NULL DEFAULT '',
       address      VARCHAR(500) NOT NULL DEFAULT '',
       role         VARCHAR(20)  NOT NULL DEFAULT 'USER',
       status       VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
       created_at   DATETIME     NOT NULL,
       updated_at   DATETIME     NOT NULL,
       PRIMARY KEY (id),
       UNIQUE KEY uk_users_email (email)
);