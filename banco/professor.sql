-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 09-Jan-2020 às 20:53
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
-- Estrutura da tabela `professor`
--

CREATE TABLE `professor` (
  `id_professor` int(11) NOT NULL,
  `nome_professor` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `disciplina` varchar(45) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `professor`
--

INSERT INTO `professor` (`id_professor`, `nome_professor`, `senha`, `disciplina`, `status`) VALUES
(1, 'Adalto', '123', 'Matematica', 1),
(2, 'Alexandre', '123', 'Matematica', 1),
(3, 'roger', 'sdf', 'Matematica', 1),
(4, 'Frutuoso', '123', 'Portugues', 1),
(5, 'Flaviana', '123', 'Portugues', 1),
(6, 'rogeria', 'asdf', 'Portugues', 1),
(7, 'professor', '123', 'Portugues', 1),
(8, 'andre', '123', 'Portugues', 0),
(9, 'Adalto Alexandre', '123', 'Matematica', 1),
(10, 'Rodolfo', '123', 'Matematica', 1),
(11, 'Antonio', '123', 'Matematica', 0),
(12, 'Frutuoso Alexandre', '123', 'Portugues', 1),
(13, 'juliano', '123', 'Matematica', 1),
(14, 'vitor', '123', 'Portugues', 1),
(15, 'stve', 'sdf', 'Quimica', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`id_professor`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `professor`
--
ALTER TABLE `professor`
  MODIFY `id_professor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
