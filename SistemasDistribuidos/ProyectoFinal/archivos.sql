-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2022 at 02:55 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `archivos`
--
CREATE DATABASE IF NOT EXISTS `archivos`;
USE `archivos`;

-- --------------------------------------------------------

--
-- Table structure for table `archivos`
--

CREATE TABLE `archivos` (
  `archivoId` varchar(36) NOT NULL,
  `espacioTrabajoId` varchar(36) NOT NULL,
  `borrado` tinyint(1) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaBorrado` varchar(32) DEFAULT NULL,
  `usuarioIdBorrado` varchar(36) DEFAULT NULL,
  `archivoPadreId` varchar(36) DEFAULT NULL,
  `esCarpeta` tinyint(1) NOT NULL,
  `nombreCarpeta` varchar(128) DEFAULT NULL
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `blobs`
--

CREATE TABLE `blobs` (
  `blobId` varchar(36) NOT NULL,
  `archivoId` varchar(36) NOT NULL,
  `binario` mediumblob NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `usuarioIdCreacion` varchar(36) NOT NULL,
  `formato` varchar(128) NOT NULL,
  `nombre` varchar(128) NOT NULL
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `espaciotrabajos`
--

CREATE TABLE `espaciotrabajos` (
  `espacioTrabajoId` varchar(36) NOT NULL,
  `usuarioId` varchar(36) NOT NULL,
  `nombre` varchar(36) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `borrado` tinyint(1) NOT NULL,
  `fechaBorrado` datetime DEFAULT NULL
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `usuarioId` varchar(36) NOT NULL,
  `nombre` varchar(32) NOT NULL,
  `claves` varchar(32) NOT NULL
) ENGINE=InnoDB;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `archivos`
--
ALTER TABLE `archivos`
  ADD PRIMARY KEY (`archivoId`),
  ADD KEY `espacioTrabajoId` (`espacioTrabajoId`),
  ADD KEY `archivoPadreId` (`archivoPadreId`);

--
-- Indexes for table `blobs`
--
ALTER TABLE `blobs`
  ADD PRIMARY KEY (`blobId`),
  ADD KEY `fechaCreacion` (`fechaCreacion`),
  ADD KEY `archivoId` (`archivoId`);

--
-- Indexes for table `espaciotrabajos`
--
ALTER TABLE `espaciotrabajos`
  ADD PRIMARY KEY (`espacioTrabajoId`),
  ADD KEY `usuarioId` (`usuarioId`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuarioId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
