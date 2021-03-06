DROP TABLE IF EXISTS TBL_ADDRESSBOOK;

CREATE TABLE IF NOT EXISTS TBL_ADDRESSBOOK
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(250) NOT NULL,
    lastname  VARCHAR(250) NOT NULL,
    street    VARCHAR(250) DEFAULT NULL,
    zipcode   VARCHAR(250) DEFAULT NULL,
    city      VARCHAR(250) DEFAULT NULL,
    email     VARCHAR(250) DEFAULT NULL,
    phone     VARCHAR(250) DEFAULT NULL,
    image     longvarchar DEFAULT NULL
);