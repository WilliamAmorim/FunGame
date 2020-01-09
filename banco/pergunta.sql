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
-- Estrutura da tabela `pergunta`
--

CREATE TABLE `pergunta` (
  `codigo_pacote` varchar(45) NOT NULL,
  `id_pergunta` int(11) NOT NULL,
  `pontos` int(11) NOT NULL,
  `resposta` varchar(45) NOT NULL,
  `enunciado` varchar(45) NOT NULL,
  `a` varchar(45) NOT NULL,
  `b` varchar(45) NOT NULL,
  `c` varchar(45) NOT NULL,
  `d` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pergunta`
--

INSERT INTO `pergunta` (`codigo_pacote`, `id_pergunta`, `pontos`, `resposta`, `enunciado`, `a`, `b`, `c`, `d`) VALUES
('Por44837', 1, 300, 'B', 'primeira pergunta', 'a', 'b', 'c', 'd'),
('Por68007', 2, 300, 'C', 'Qual destes animais é mais pesado?', 'tamandoa', 'grilo', 'elefante', 'andorinha'),
('Por68007', 3, 300, 'A', 'Quem é cj?', 'Personagem de video game', 'Atleta famoso', 'Fisico nuclear', 'Um atoa ai'),
('Por68007', 4, 500, 'D', 'Quem é kratos?', 'Um cara chato', 'Um maronba', 'Um asassino', 'Personagem de video game'),
('Por54926', 5, 300, 'C', 'Qual destes animais é mais pesado?', 'tamandoa', 'grilo', 'elefante', 'andorinha'),
('Por54926', 6, 300, 'A', 'Quem é cj?', 'Personagem de video game', 'Atleta famoso', 'Fisico nuclear', 'Um atoa ai'),
('Por54926', 7, 500, 'D', 'Quem é kratos?', 'Um cara chato', 'Um maronba', 'Um asassino', 'Personagem de video game'),
('Por36426', 8, 300, 'C', 'Qual destes animais é mais pesado?', 'tamandoa', 'grilo', 'elefante', 'andorinha'),
('Por36426', 9, 300, 'A', 'Quem é cj?', 'Personagem de video game', 'Atleta famoso', 'Fisico nuclear', 'Um atoa ai'),
('Por36426', 10, 500, 'D', 'Quem é kratos?', 'Um cara chato', 'Um maronba', 'Um asassino', 'Personagem de video game'),
('Por70273', 11, 200, 'C', 'Qual destes animais é mais pesado?', 'tamandoa', 'grilo', 'elefante', 'andorinha'),
('Por70273', 12, 300, 'A', 'Quem é cj?', 'Personagem de video game', 'Atleta famoso', 'Fisico nuclear', 'Um atoa ai'),
('Por70273', 13, 200, 'D', 'Quem é kratos?', 'Um cara chato', 'Um maronba', 'Um asassino', 'Personagem de video game'),
('Por86453', 14, 200, 'C', 'Quem é cj?', 'um personagem de desenho animado?', 'um  ator famoso de cinema?', 'um personagem de video game?', 'ninguém importante'),
('Por86453', 15, 300, 'D', 'Quem é kratos?', 'Um fisiculturista', 'ninguem importane', 'marinheiro', 'Personagem de  video game'),
('Por86453', 16, 500, 'A', 'Qual o melhor jogo de play 2?', 'god  of war', 'gta san andreas', 'shadow of the colossus', 'nenhum dos anteriores');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pergunta`
--
ALTER TABLE `pergunta`
  ADD PRIMARY KEY (`id_pergunta`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pergunta`
--
ALTER TABLE `pergunta`
  MODIFY `id_pergunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
