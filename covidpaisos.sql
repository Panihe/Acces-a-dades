-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-01-2022 a las 12:03:11
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ae7_servicis_web`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `covidpaisos`
--

CREATE TABLE `covidpaisos` (
  `Id` int(11) NOT NULL,
  `Pais` varchar(50) NOT NULL,
  `Confirmats` varchar(50) NOT NULL,
  `Recuperats` varchar(50) NOT NULL,
  `Morts` varchar(50) NOT NULL,
  `Capital` varchar(50) NOT NULL,
  `Continent` varchar(50) NOT NULL,
  `Localitzacio` varchar(50) NOT NULL,
  `Poblacio` varchar(50) NOT NULL,
  `Area` varchar(50) NOT NULL,
  `Esperança_de_vida` varchar(50) NOT NULL,
  `Elevacio_en_metres` varchar(50) NOT NULL,
  `Bandera` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `covidpaisos`
--
ALTER TABLE `covidpaisos`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `covidpaisos`
--
ALTER TABLE `covidpaisos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
