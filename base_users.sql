-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 26 août 2024 à 14:05
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `base_users`
--

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_login` varchar(255) DEFAULT NULL,
  `worked_hours` float NOT NULL,
  `day` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`id`, `user_name`, `user_login`, `worked_hours`, `day`) VALUES
(1, 'fethi trimech', 'trefe100155675', 8.5, '2024-07-17'),
(3, 'sami haddad', 'samih1003', 5, '2024-07-17'),
(4, 'laila mansour', 'lailm1004', 4.5, '2024-07-17'),
(5, 'amine karoui', 'amink1005', 9, '2024-07-17'),
(6, 'sarah ferjani', 'saraf1006', 7.5, '2024-07-17'),
(7, 'walid bouzid', 'walib1007', 3.5, '2024-07-17'),
(8, 'mariem chermiti', 'maric1008', 6, '2024-07-17'),
(9, 'ahmed chatti', 'ahmec1009', 8, '2024-07-17'),
(10, 'rania mezghani', 'ranim1010', 9, '2024-07-17'),
(11, 'farah ghorbel', 'farag1011', 7.5, '2024-07-17'),
(12, 'nizar kallel', 'nizak1012', 4.5, '2024-07-17'),
(13, 'fethi trimech', 'trefe1001', 8.5, '2024-07-18'),
(14, 'nour ben ammar', 'nourb1002', 9, '2024-07-18'),
(15, 'sami haddad', 'samih1003', 8, '2024-07-18'),
(16, 'laila mansour', 'lailm1004', 7, '2024-07-18'),
(17, 'amine karoui', 'amink1005', 7.5, '2024-07-18'),
(18, 'sarah ferjani', 'saraf1006', 8.5, '2024-07-18'),
(19, 'walid bouzid', 'walib1007', 6, '2024-07-18'),
(20, 'mariem chermiti', 'maric1008', 5, '2024-07-18'),
(21, 'ahmed chatti', 'ahmec1009', 4.5, '2024-07-18'),
(22, 'rania mezghani', 'ranim1010', 9, '2024-07-18'),
(23, 'farah ghorbel', 'farag1011', 7.5, '2024-07-18'),
(24, 'nizar kallel', 'nizak1012', 3.5, '2024-07-18'),
(25, 'fethi trimech', 'trefe1001', 6, '2024-07-19'),
(27, 'sami haddad', 'samih1003', 9, '2024-07-19'),
(28, 'laila mansour', 'lailm1004', 7.5, '2024-07-19'),
(29, 'amine karoui', 'amink1005', 4.5, '2024-07-19'),
(30, 'sarah ferjani', 'saraf1006', 8.5, '2024-07-19'),
(31, 'walid bouzid', 'walib1007', 9, '2024-07-19'),
(32, 'mariem chermiti', 'maric1008', 8, '2024-07-19'),
(33, 'ahmed chatti', 'ahmec1009', 7, '2024-07-19'),
(34, 'rania mezghani', 'ranim1010', 7.5, '2024-07-19'),
(35, 'farah ghorbel', 'farag1011', 8.5, '2024-07-19'),
(36, 'nizar kallel', 'nizak1012', 6, '2024-07-19'),
(37, 'fethi trimech', 'trefe1001', 5, '2024-07-20'),
(38, 'nour ben ammar', 'nourb1002', 4.5, '2024-07-20'),
(39, 'sami haddad', 'samih1003', 9, '2024-07-20'),
(40, 'laila mansour', 'lailm1004', 7.5, '2024-07-20'),
(41, 'amine karoui', 'amink1005', 3.5, '2024-07-20'),
(42, 'sarah ferjani', 'saraf1006', 6, '2024-07-20'),
(43, 'walid bouzid', 'walib1007', 8, '2024-07-20'),
(44, 'mariem chermiti', 'maric1008', 9, '2024-07-20'),
(45, 'ahmed chatti', 'ahmec1009', 7.5, '2024-07-20'),
(46, 'rania mezghani', 'ranim1010', 4.5, '2024-07-20'),
(47, 'farah ghorbel', 'farag1011', 8.5, '2024-07-20'),
(49, 'noura saleh', 'noura123', 5, '2024-07-08'),
(51, 'salem ajroud', '2003', 5, '2024-07-08'),
(52, 'nourin', '5050', 5, '2024-07-08'),
(53, 'nourin', '5050', 6, '2024-07-09'),
(55, 'nourin amimi', '20035', 5, '2024-07-08'),
(56, 'nourin2003', '50506', 5, '2024-07-08');

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES
(1, 'lili', '$2a$10$5thMRLAt5XtB9MW2bQJDu.kF4PhG8ZszrhlO5gx.5tJRD/zEqAdSS', 'lili@gmail.com'),
(2, 'lilio', '$2a$10$umN1v0j14iIBhSHth65S7uSVboUkzJjnb.joMoXPtBNilOtzHeGoG', 'nouramimi22@gmail.com'),
(3, 'liliou', '$2a$10$6rMLDQ40spxqRkOy1AkaJ.Iqtam4CPIqlzwkBbiW0FYj7Ra/fJ27e', 'noura@gmail.com'),
(4, 'noura', '$2a$10$vV2vlduv9Y7mrsMWQVtuDuNoo0WwKcr.dHl7lvh4JUOIDrYdviIM2', 'nouramimi22@gmail.com'),
(5, 'nouri', '$2a$10$7QvlaA6nXtdUbQY5GE7CrebjFmvDNszqtM0pW1EuSrO9uUXK/JFXC', 'amiminourelhouda@issatso.u-sousse.tn'),
(6, 'nouri', '$2a$10$fLlpW2nzsMsDnkjDjHnWn.7YJ.oGjut0U.uOwgQt7vMh3yR.Brxwq', 'amiminourelhouda@issatso.u-sousse.tn'),
(7, 'nourin', '$2a$10$Ht55k4qidEJJo5VLxa811.TB2tWkncWb5S7AqP5bSLKWn1mGaArZa', 'nouramimi@gmail.com'),
(8, 'll', '$2a$10$/j4tlvR37iJNyrggKkLPguqme/ZS5Nv2vbMzgrUYmtqcm2W7Ob.pi', 'nouramimi22@gmail.com'),
(9, 'kk', '$2a$10$m3n.e1lQJ/uilzNVuqJ84.djh86KdapEEl.kf8f1nyUFpWvmkj0Am', 'amiminourelhouda@issatso.u-sousse.tn'),
(10, 'bb', '$2a$10$6xnp97ONE2AW9YpOug3U8OXuK9VBI.M5nSoZw8hhsEbm1QBO5mmIW', 'ela@gmail.com'),
(11, 'ff', '$2a$10$Q1/30alxJ.RycprVR.ryDOsldCv2mFbjY1W9CfRkIUeLJ.SUoHiDi', 'zz7015990@gmail.com'),
(12, 'ss', '$2a$10$DGazCOhK3sLHSJoFSN33pOlPE4mf1fXlvkz93IvUWL5oYczXXfmd2', 'amiminourelhouda@issatso.u-sousse.tn'),
(13, 'nourin2', '$2a$10$.EBnUFIggJqGheQ2HfiIu.N./WUCp4krk1rW.3Ye6/qK.wlxLStEO', 'nouramimi22@gmail.com'),
(14, 'nourin3', '$2a$10$kHT7RK7swqALu/god39BHe4s8v9M3yGq.lNUWqLaV8BddB5QDr1L2', 'amiminourelhouda@issatso.u-sousse.tn'),
(15, 'nourin5', '$2a$10$8GbLrnakL8IyN9AQj0wvcuQnMlt.UiN.7BeLJ9PDblUyF6EqaRlaK', 'nouramimi22@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `users_seq`
--

CREATE TABLE `users_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users_seq`
--

INSERT INTO `users_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2),
(14, 2),
(15, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK2uuqtodt6i8taxm6tswc7cccd` (`user_login`,`day`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
