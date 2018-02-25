--
-- This file is part of hibernate-examples.
--
-- hibernate-examples is free software; you can redistribute it and/or modify it under the
-- terms of the GNU General Public License as published by the Free Software
-- Foundation; either version 2, or (at your option) any later version.
--
-- hibernate-examples is distributed in the hope that it will be useful, but WITHOUT ANY
-- WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
-- A PARTICULAR PURPOSE. See the GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License along with
-- this program; see the file COPYING. If not, see
-- <http://www.gnu.org/licenses/>.
--
-- @author Adrian Novegil Toledo
-- @mail adrian.novegil@gmail.com
-- @description Script de creación del modelo de aplicaicón para el presente 
-- ejemplo.
--

--
-- Base de datos: `hibernate_relationship_one_to_many_mapping_bag`
--
CREATE DATABASE IF NOT EXISTS hibernate_relationship_one_to_many_mapping_bag DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `hibernate_relationship_one_to_many_mapping_bag`;

--
-- Objeto department
--
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
    `department_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `dept_name` VARCHAR(50) NOT NULL DEFAULT '0',
    PRIMARY KEY (`department_id`)
) DEFAULT CHARSET=utf8;

--
-- Objeto employee
--
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
    `employee_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `firstname` VARCHAR(50) NULL DEFAULT NULL,
    `lastname` VARCHAR(50) NULL DEFAULT NULL,
    `birth_date` DATE NULL DEFAULT NULL,
    `cell_phone` VARCHAR(15) NULL DEFAULT NULL,
    `department_id` BIGINT(20) NULL DEFAULT NULL,
    PRIMARY KEY (`employee_id`),
    INDEX `FK_DEPT` (`department_id`),
    CONSTRAINT `FK_DEPT` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) DEFAULT CHARSET=utf8;

