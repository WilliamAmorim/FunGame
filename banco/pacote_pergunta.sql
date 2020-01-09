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
-- Estrutura da tabela `pacote_pergunta`
--

CREATE TABLE `pacote_pergunta` (
  `codigo_pacote` varchar(45) NOT NULL,
  `disciplina` varchar(45) NOT NULL,
  `assunto` varchar(45) NOT NULL,
  `professor` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `pontuacao_maxima` int(11) NOT NULL,
  `numero_questoes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pacote_pergunta`
--

INSERT INTO `pacote_pergunta` (`codigo_pacote`, `disciplina`, `assunto`, `professor`, `data`, `pontuacao_maxima`, `numero_questoes`) VALUES
('Por44837', 'Portugues', 'Perguntas Aleatorias', 'adalto', '2019-12-30', 300, 1),
('Por86453', 'Portugues', 'Novas perguntas', 'alexandre', '2019-12-31', 1000, 3);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
