-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 09-Jan-2020 às 20:52
-- Versão do servidor: 10.1.36-MariaDB
-- versão do PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bancoquiz`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `log`
--

CREATE TABLE `log` (
  `nome_aluno` varchar(45) NOT NULL,
  `ip_maquina` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `log`
--

INSERT INTO `log` (`nome_aluno`, `ip_maquina`) VALUES
('', '192.168.1.105'),
('andre', '192.168.1.105'),
('Adalto Alexandre', '192.168.1.105'),
('Rodolfo', '192.168.1.105'),
('William Santos', '192.168.1.105'),
('Antonio', '192.168.1.105'),
('w', '192.168.1.105'),
('Frutuoso Alexandre', '192.168.1.105'),
('bolinha', '192.168.1.105'),
('juliano', '192.168.1.105'),
('vitor', '192.168.1.105'),
('Antonio', '192.168.1.105'),
('stve', '192.168.1.105'),
('renata', '192.168.1.105');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
