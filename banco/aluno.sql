-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 09-Jan-2020 às 20:51
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
-- Estrutura da tabela `aluno`
--

CREATE TABLE `aluno` (
  `id_aluno` int(11) NOT NULL,
  `nome_aluno` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `serie` varchar(45) NOT NULL,
  `turma` varchar(45) NOT NULL,
  `turno` varchar(45) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`id_aluno`, `nome_aluno`, `senha`, `serie`, `turma`, `turno`, `status`) VALUES
(1, 'alexandre', 'asdf', '2', 'B', 'Matutino', 0),
(2, 'william', '123', '3', 'C', 'Matutino', 1),
(3, 'andre', '122', '2', 'B', 'Vespertino', 1),
(4, 'flavio', '123', '1', 'B', 'Matutino', 1),
(5, 'Antonio', '123', '3', 'B', 'Vespertino', 1),
(6, 'renata', '123', '2', 'A', 'Vespertino', 1),
(7, 'William Santos', '123', '1', 'B', 'Matutino', 1),
(8, 'w', 's', '1', 'B', 'Matutino', 1),
(9, 'bolinha', '123', '1', 'A', 'Vespertino', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id_aluno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aluno`
--
ALTER TABLE `aluno`
  MODIFY `id_aluno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
