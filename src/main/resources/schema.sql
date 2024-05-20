CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(255)        NOT NULL
);

CREATE TABLE IF NOT EXISTS subscriptions
(
    user_id BIGINT NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS catalogs
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    user_id BIGINT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS books
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    author          VARCHAR(255) NOT NULL,
    subscription_id BIGINT,
    available       BOOLEAN DEFAULT true,
    FOREIGN KEY (subscription_id) REFERENCES subscriptions (user_id)
);

CREATE TABLE IF NOT EXISTS catalogs_books
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    catalog_id BIGINT NOT NULL,
    book_id    BIGINT NOT NULL,
    UNIQUE (catalog_id, book_id),
    FOREIGN KEY (catalog_id) REFERENCES catalogs (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
)
