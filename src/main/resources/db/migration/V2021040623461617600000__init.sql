DROP TABLE IF EXISTS `tv_shows`;
CREATE TABLE `tv_shows` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `show_id` VARCHAR(12) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `title` VARCHAR(200) NOT NULL,
  `director` VARCHAR(500) NULL,
  `cast` TEXT NULL,
  `country` VARCHAR(500) NOT NULL,
  `date_added` VARCHAR(45) NOT NULL,
  `release_year` INT NOT NULL,
  `rating` VARCHAR(10) NULL,
  `duration` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `show_id_UNIQUE` (`show_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
  ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;