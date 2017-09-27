--liquibase formatted sql

--changeset FrankMisa:bootstrap_base_tables (dbms:mysql failOnError:true) endDelimiter:/ rollbackEndDelimiter:/
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema masterchild
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema masterchild
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `masterchild` DEFAULT CHARACTER SET utf8 ;
USE `masterchild` ;

-- -----------------------------------------------------
-- Table `masterchild`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `masterchild`.`Order` (
  `id` INT NOT NULL,
  `customerName` VARCHAR(45) NULL,
  `status` TINYINT(1) NULL DEFAULT 0,
  `orderDate` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masterchild`.`OrderDetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `masterchild`.`OrderDetail` (
  `id` INT NOT NULL,
  `orderId` INT NOT NULL,
  `details` VARCHAR(45) NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
/
--rollback drop masterchild.Order;
--rollback drop masterchild.OrderDetail;
--rollback /