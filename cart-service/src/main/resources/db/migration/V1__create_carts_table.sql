CREATE TABLE carts (
   id         BIGINT   NOT NULL AUTO_INCREMENT,
   user_id    BIGINT   NOT NULL,
   created_at DATETIME NOT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY uk_carts_user (user_id)
);

CREATE TABLE cart_items (
    id         BIGINT NOT NULL AUTO_INCREMENT,
    cart_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_cart_items (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES carts (id)
);