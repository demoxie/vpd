CREATE TABLE IF NOT EXISTS Transaction (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             description VARCHAR(255),
                             amount DECIMAL(19, 4),
                             currency VARCHAR(10),
                             status VARCHAR(50),
                             type VARCHAR(50),
                             category VARCHAR(50),
                             merchant VARCHAR(100),
                             reference VARCHAR(100),
                             userId BIGINT,
                             accountId BIGINT,
                             product VARCHAR(100),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
