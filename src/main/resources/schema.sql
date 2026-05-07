CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    trader_id VARCHAR(50),
    stock VARCHAR(20),
    sector VARCHAR(20),
    quantity INT,
    side VARCHAR(10),
    status VARCHAR(20),
    created_at TIMESTAMP
);

CREATE TABLE portfolio (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    trader_id VARCHAR(50),
    stock VARCHAR(20),
    sector VARCHAR(20),
    quantity INT
);