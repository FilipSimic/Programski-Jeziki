SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS INVOICE;
DROP TABLE IF EXISTS ARTICLE;
DROP TABLE IF EXISTS VAT;
DROP TABLE IF EXISTS PERSON;
DROP TABLE IF EXISTS INTERNAL_ARTICLE;
DROP TABLE IF EXISTS INVOICE_HAS_ARTICLE;
DROP TABLE IF EXISTS INVOICE_HAS_INTERNAL_ARTICLE;
DROP TABLE IF EXISTS COMPANY;
DROP TABLE IF EXISTS DISCOUNT;
DROP TABLE IF EXISTS SCHEMA_VERSION;
DROP TABLE IF EXISTS DELIVERY_TYPE;
DROP TABLE IF EXISTS PAYMENT_TYPE;
DROP TABLE IF EXISTS DISCOUNT_TYPE;
SET FOREIGN_KEY_CHECKS=1;

CREATE SCHEMA IF NOT EXISTS InvoiceManager_dev character set utf8mb4 collate utf8mb4_unicode_ci;
USE InvoiceManager_dev;

SET @schema_version_id = "00000000000001";
SET @change_number = 0;
SET @version = "1.0.0";
SET @applied = NOW();
SET @applied_by = "Filip Simic";
SET @description = "First creation";
SET @file = "dbChanges_1.sql";

CREATE TABLE VAT (
	idVAT BINARY(16) PRIMARY KEY NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	vat_value INT NOT NULL,
    deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE ARTICLE (
	idARTICLE BINARY(16) PRIMARY KEY NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	name VARCHAR(45) NOT NULL,
	price_without_vat INT NOT NULL,
	price_with_vat INT NOT NULL,
	ean VARCHAR(45) NOT NULL,
	country VARCHAR(45) NOT NULL,
	VAT_idVAT BINARY(16) NOT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE INTERNAL_ARTICLE (
	idINTERNAL_ARTICLE BINARY(16) PRIMARY KEY NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	name VARCHAR(45) NOT NULL,
	price_without_vat INT NOT NULL,
	price_with_vat INT NOT NULL,
	ean VARCHAR(45) NOT NULL,
	country VARCHAR(45) NOT NULL,
	section VARCHAR(45) NOT NULL,
	weight INT NOT NULL,
	VAT_idVAT BINARY(16) NOT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE PERSON (
	idPERSON BINARY(16) PRIMARY KEY NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	name VARCHAR(45) NOT NULL,
	lastname VARCHAR(45) NOT NULL,
	address VARCHAR(45) NOT NULL,
	town VARCHAR(45) NOT NULL,
	country VARCHAR(45) NOT NULL,
	phone_number VARCHAR(45) NULL,
	postcode INT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE COMPANY (
	idCOMPANY BINARY(16) PRIMARY KEY NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	name VARCHAR(45) NOT NULL,
	address VARCHAR(45) NOT NULL,
	town VARCHAR(45) NOT NULL,
	country VARCHAR(45) NOT NULL,
	phone_number VARCHAR(45) NULL,
	postcode INT NULL,
	tax_number VARCHAR(45) NOT NULL,
	registration_number VARCHAR(45) NOT NULL,
	is_taxable TINYINT(1) NOT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE PAYMENT_TYPE (
	idPAYMENT_TYPE BINARY(16) PRIMARY KEY NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	type VARCHAR(45) NOT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE DELIVERY_TYPE (
	idDELIVERY_TYPE BINARY(16) PRIMARY KEY NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	type VARCHAR(45) NOT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE DISCOUNT_TYPE (
	idDISCOUNT_TYPE BINARY(16) PRIMARY KEY NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	type VARCHAR(45) NOT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE DISCOUNT (
	idDISCOUNT BINARY(16) PRIMARY KEY NOT NULL,
	validity TIMESTAMP NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	percentage INT NOT NULL,
	ean VARCHAR(45) NOT NULL,
	DISCOUNT_TYPE_idDISCOUNT_TYPE BINARY(16) NOT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE INVOICE (
	idINVOICE BINARY(16) PRIMARY KEY NOT NULL,
	date TIMESTAMP NOT NULL,
	created TIMESTAMP NOT NULL,
	last_modified TIMESTAMP NOT NULL,
	combined_total_price DOUBLE NOT NULL,
	combined_discount_price DOUBLE NOT NULL,
	tax_number VARCHAR(45) NULL,
	PERSON_idPERSON BINARY(16) NOT NULL,
	PAYMENT_TYPE_idPAYMENT_TYPE BINARY(16) NOT NULL,
	DELIVERY_TYPE_idDELIVERY_TYPE BINARY(16) NOT NULL,
	DISCOUNT_idDISCOUNT BINARY(16) NOT NULL,
	COMPANY_idCOMPANY BINARY(16) NOT NULL,
	deleted TINYINT(1) DEFAULT 0 NOT NULL
);

CREATE TABLE INVOICE_HAS_INTERNAL_ARTICLE (
	idINVOICE_HAS_INTERNAL_ARTICLE BINARY(16) PRIMARY KEY NOT NULL,
	quantity INT NOT NULL,
	INTERNAL_ARTICLE_idINTERNAL_ARTICLE BINARY(16) NOT NULL,
	INVOICE_idINVOICE BINARY(16) NOT NULL
);

CREATE TABLE INVOICE_HAS_ARTICLE (
	idINVOICE_HAS_ARTICLE BINARY(16) PRIMARY KEY NOT NULL,
	quantity INT NOT NULL,
	ARTICLE_idARTICLE BINARY(16) NOT NULL,
	INVOICE_idINVOICE BINARY(16) NOT NULL
);

CREATE TABLE SCHEMA_VERSION (
	idSCHEMA_VERSION binary(16) primary key not null,
    change_number int not null,
    version varchar(30) not null,
    applied datetime not null,
    applied_by varchar(45) not null,
    description varchar(500) not null,
    file varchar(45) not null
);

ALTER TABLE INTERNAL_ARTICLE ADD CONSTRAINT TK_IARTICLE_VAT FOREIGN KEY(VAT_idVAT) REFERENCES VAT(idVAT) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE ARTICLE ADD CONSTRAINT TK_ARTICLE_VAT FOREIGN KEY(VAT_idVAT) REFERENCES VAT(idVAT) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE_HAS_ARTICLE ADD CONSTRAINT TK_IHA_ARTICLE FOREIGN KEY(ARTICLE_idARTICLE) REFERENCES ARTICLE(idARTICLE) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE_HAS_ARTICLE ADD CONSTRAINT TK_IHA_INVOICE FOREIGN KEY(INVOICE_idINVOICE) REFERENCES INVOICE(idINVOICE) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE_HAS_INTERNAL_ARTICLE ADD CONSTRAINT TK_IHIA_ARTICLE FOREIGN KEY(INTERNAL_ARTICLE_idINTERNAL_ARTICLE) REFERENCES INTERNAL_ARTICLE(idINTERNAL_ARTICLE) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE_HAS_INTERNAL_ARTICLE ADD CONSTRAINT TK_IHIA_INVOICE FOREIGN KEY(INVOICE_idINVOICE) REFERENCES INVOICE(idINVOICE) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE ADD CONSTRAINT TK_INVOICE_PERSON FOREIGN KEY(PERSON_idPERSON) REFERENCES PERSON(idPERSON) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE ADD CONSTRAINT TK_INVOICE_TPAYMENT FOREIGN KEY(PAYMENT_TYPE_idPAYMENT_TYPE) REFERENCES PAYMENT_TYPE(idPAYMENT_TYPE) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE ADD CONSTRAINT TK_INVOICE_TDELIVERY FOREIGN KEY(DELIVERY_TYPE_idDELIVERY_TYPE) REFERENCES DELIVERY_TYPE(idDELIVERY_TYPE) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE ADD CONSTRAINT TK_INVOICE_DISCOUNT FOREIGN KEY(DISCOUNT_idDISCOUNT) REFERENCES DISCOUNT(idDISCOUNT) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE INVOICE ADD CONSTRAINT TK_INVOICE_COMPANY FOREIGN KEY(COMPANY_idCOMPANY) REFERENCES COMPANY(idCOMPANY) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE DISCOUNT ADD CONSTRAINT TK_DISCOUNT_TYPE FOREIGN KEY (DISCOUNT_TYPE_idDISCOUNT_TYPE) REFERENCES DISCOUNT_TYPE(idDISCOUNT_TYPE) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE INTERNAL_ARTICLE ENGINE = InnoDB;
ALTER TABLE ARTICLE ENGINE = InnoDB;
ALTER TABLE INVOICE_HAS_ARTICLE ENGINE = InnoDB;
ALTER TABLE INVOICE_HAS_INTERNAL_ARTICLE ENGINE = InnoDB;
ALTER TABLE INVOICE ENGINE = InnoDB;
ALTER TABLE DISCOUNT ENGINE = InnoDB;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_VAT BEFORE UPDATE ON VAT
	for each row begin
    insert into VAT
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_ARTICLE BEFORE UPDATE ON ARTICLE
	for each row begin
    insert into ARTICLE
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_INTERNAL_ARTICLE BEFORE UPDATE ON INTERNAL_ARTICLE
	for each row begin
    insert into INTERNAL_ARTICLE
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_PERSON BEFORE UPDATE ON PERSON
	for each row begin
    insert into PERSON
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_COMPANY BEFORE UPDATE ON COMPANY
	for each row begin
    insert into COMPANY
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_INVOICE BEFORE UPDATE ON INVOICE
	for each row begin
    insert into INVOICE
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_DISCOUNT BEFORE UPDATE ON DISCOUNT
	for each row begin
    insert into DISCOUNT
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_PAYMENT_TYPE BEFORE UPDATE ON PAYMENT_TYPE
	for each row begin
    insert into PAYMENT_TYPE
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_DELIVERY_TYPE BEFORE UPDATE ON DELIVERY_TYPE
	for each row begin
    insert into DELIVERY_TYPE
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

delimiter &&
CREATE TRIGGER BEFORE_UPDATE_DISCOUNT_TYPE BEFORE UPDATE ON DISCOUNT_TYPE
	for each row begin
    insert into DISCOUNT_TYPE
    set NEW.last_modified = CURRENT_TIMESTAMP;
END; &&
delimiter ;

INSERT INTO SCHEMA_VERSION(idSCHEMA_VERSION, change_number, version, applied, applied_by, description, file) VALUES (@schema_version_id, @change_number, @version, @applied, @applied_by, @description, @file);