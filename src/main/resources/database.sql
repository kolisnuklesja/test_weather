DROP SCHEMA IF EXISTS `weather`;
CREATE SCHEMA IF NOT EXISTS `weather` DEFAULT CHARACTER SET utf8 ;
USE `weather` ;

-- -----------------------------------------------------
-- Table `weather`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `weather`.`city` (
  `idcity` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`idcity`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `weather`.`persistent_logins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `weather`.`persistent_logins` (
  `username` VARCHAR(255) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `weather`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `weather`.`role` (
  `idrole` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `weather`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `weather`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `weather`.`users_requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `weather`.`users_requests` (
  `idrequests` INT(11) NOT NULL AUTO_INCREMENT,
  `idcity` INT(11) NOT NULL,
  `iduser` INT(11) NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idrequests`, `idcity`, `iduser`),
  INDEX `fk_users_requests_city_idx` (`idcity` ASC),
  INDEX `fk_users_requests_user1_idx` (`iduser` ASC),
  CONSTRAINT `fk_users_requests_city`
    FOREIGN KEY (`idcity`)
    REFERENCES `weather`.`city` (`idcity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_requests_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `weather`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `weather`.`user_has_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `weather`.`user_has_role` (
  `iduser` INT(11) NOT NULL,
  `idrole` INT NOT NULL,
  PRIMARY KEY (`iduser`, `idrole`),
  INDEX `fk_user_has_role_role1_idx` (`idrole` ASC),
  INDEX `fk_user_has_role_user1_idx` (`iduser` ASC),
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `weather`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`idrole`)
    REFERENCES `weather`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;