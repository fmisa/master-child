--liquibase formatted sql

--changeset FrankMisa:bootstrap_base_tables (dbms:mysql failOnError:true) endDelimiter:/ rollbackEndDelimiter:/
CREATE SCHEMA IF NOT EXISTS masterchild DEFAULT CHARACTER SET utf8;
/
CREATE TABLE IF NOT EXISTS masterchild.Order (
  id INT NOT NULL,
  customerName VARCHAR(45) NULL,
  status TINYINT(1) NULL DEFAULT 0,
  orderDate DATE NOT NULL,
  PRIMARY KEY (id));
/
CREATE TABLE IF NOT EXISTS masterchild.OrderDetail (
  id INT NOT NULL,
  orderId INT NOT NULL,
  details VARCHAR(45) NULL,
  quantity INT NULL,
  PRIMARY KEY (id));
/
--rollback drop masterchild.Order;
--rollback drop masterchild.OrderDetail;
--rollback /