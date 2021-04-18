CREATE TABLE greeting
(
    id             INT         NOT NULL AUTO_INCREMENT,
    name           VARCHAR(50) NOT NULL,
    created_moment TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
