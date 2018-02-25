--
-- This file is part of spring-mvc-examples.
--
-- springmvc-examples is free software; you can redistribute it and/or modify it under the
-- terms of the GNU General Public License as published by the Free Software
-- Foundation; either version 2, or (at your option) any later version.
--
-- springmvc-examples is distributed in the hope that it will be useful, but WITHOUT ANY
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
-- Base de datos: `hibernate_hello_world_annotation`
--
CREATE DATABASE IF NOT EXISTS contact_manager DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `contact_manager`;

--
-- Objeto contacts
--
CREATE TABLE contacs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(30),
    lastname VARCHAR(30),
    telephone VARCHAR(15),
    email VARCHAR(30),
    created TIMESTAMP DEFAULT NOW()
);