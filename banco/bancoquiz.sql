-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 30-Jan-2020 às 21:55
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
(9, 'bolinha', '123', '1', 'A', 'Vespertino', 1),
(10, 'william cardoso', '123', '2', 'B', 'Matutino', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `desempenho`
--

CREATE TABLE `desempenho` (
  `aluno` varchar(45) NOT NULL,
  `professor` varchar(45) NOT NULL,
  `codigo_pacote` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `pontuacao` int(11) NOT NULL,
  `assunto` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `desempenho`
--

INSERT INTO `desempenho` (`aluno`, `professor`, `codigo_pacote`, `data`, `pontuacao`, `assunto`) VALUES
('william cardoso', 'william', 'Por86453', '2020-01-30', 300, 'assunto'),
('william cardoso', 'william', 'Por86453', '2020-01-30', 500, 'assunto'),
('william cardoso', 'william', 'Por44837', '2020-01-30', 300, 'assunto');

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
('renata', '192.168.1.105'),
('novo', '192.168.1.105'),
('william', '192.168.1.105'),
('william', '192.168.1.105'),
('william cardoso', '192.168.1.105'),
('william', '192.168.1.105');

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
('Por86453', 16, 500, 'A', 'Qual o melhor jogo de play 2?', 'god  of war', 'gta san andreas', 'shadow of the colossus', 'nenhum dos anteriores'),
('Qui93897', 17, 300, 'B', 'hhh', 'ff', 'ff', 'dd', 'ss');

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
(15, 'stve', 'sdf', 'Quimica', 0),
(16, 'novo', 'qwe', 'Portugues', 1),
(17, 'william', '123', 'Matematica', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id_aluno`);

--
-- Indexes for table `pergunta`
--
ALTER TABLE `pergunta`
  ADD PRIMARY KEY (`id_pergunta`);

--
-- Indexes for table `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`id_professor`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aluno`
--
ALTER TABLE `aluno`
  MODIFY `id_aluno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `pergunta`
--
ALTER TABLE `pergunta`
  MODIFY `id_pergunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `professor`
--
ALTER TABLE `professor`
  MODIFY `id_professor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
