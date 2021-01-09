-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2021 at 11:05 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tbbpl`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `sku` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `stock` int(50) NOT NULL,
  `harga_beli` int(20) NOT NULL,
  `harga_jual` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`sku`, `nama`, `stock`, `harga_beli`, `harga_jual`) VALUES
('BR1', 'Teh kotak', 63, 2500, 5000),
('BR10', 'Neocoffee tiramisu', 30, 1000, 1500),
('BR11', 'Teh sosro', 8, 5000, 7000),
('BR2', 'Indomilk cokelat', 68, 3000, 5000),
('BR3', 'Indomilk vanilla', 10, 2000, 4500),
('BR4', 'Milo cokelat', 19, 3500, 5000),
('BR5', 'Milo vanilla', 35, 4000, 5500),
('BR6', 'Roti manis', 32, 8000, 10000),
('BR7', 'Roti cokelat', 45, 8000, 10000),
('BR8', 'Energen jahe', 10, 2000, 3500),
('BR9', 'Energen cokelat', 32, 1500, 2500);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id` int(255) NOT NULL,
  `sku` varchar(50) NOT NULL,
  `noresi` varchar(50) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `harga` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id`, `sku`, `noresi`, `jumlah`, `harga`) VALUES
(107, 'BR6', 'T1', 1, 10000),
(108, 'BR11', 'T1', 3, 21000),
(109, 'BR4', 'T2', 5, 25000),
(110, 'BR1', 'T2', 5, 25000),
(111, 'BR9', 'T3', 3, 7500),
(112, 'BR2', 'T3', 2, 10000),
(113, 'BR6', 'T3', 1, 10000),
(114, 'BR9', 'T4', 5, 12500),
(115, 'BR6', 'T4', 2, 20000),
(116, 'BR9', 'T5', 3, 7500),
(117, 'BR8', 'T5', 6, 21000),
(118, 'BR9', 'T6', 3, 7500),
(119, 'BR9', 'T7', 2, 5000),
(120, 'BR8', 'T8', 5, 17500),
(121, 'BR5', 'T8', 7, 38500),
(122, 'BR7', 'T8', 3, 30000),
(123, 'BR6', 'T8', 1, 10000),
(124, 'BR5', 'T9', 3, 16500),
(125, 'BR7', 'T9', 3, 30000),
(126, 'BR6', 'T9', 1, 10000),
(127, 'BR8', 'T10', 5, 17500),
(128, 'BR4', 'T10', 3, 15000),
(129, 'BR5', 'T10', 2, 11000),
(130, 'BR6', 'T10', 1, 10000),
(131, 'BR1', 'T10', 7, 35000),
(132, 'BR6', 'T11', 1, 10000),
(133, 'BR11', 'T11', 2, 14000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `noresi` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`noresi`, `tanggal`, `username`) VALUES
('T1', '2021-01-09', 'fathia'),
('T10', '2021-01-10', 'fathia'),
('T11', '2021-01-10', 'debal'),
('T2', '2021-01-09', 'fathia'),
('T3', '2021-01-09', 'debal'),
('T4', '2021-01-09', 'fathia'),
('T5', '2021-01-09', 'fathia'),
('T6', '2021-01-09', 'fathia'),
('T7', '2021-01-10', 'debal'),
('T8', '2021-01-10', 'fathia'),
('T9', '2021-01-10', 'fathia');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `login_terakhir` date NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `login_terakhir`, `email`, `password`) VALUES
('debal', '2021-01-10', 'debal@unand.id', 'sandbox'),
('fathia', '2021-01-10', 'fathia@gmail.com', 'fathia123'),
('fauzan', '2020-12-22', 'fauzan@gmail.com', 'fauzan123'),
('gita', '2020-12-22', 'gita@gmail.com', 'gita123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`sku`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detail_transaksi_ibfk_2` (`sku`),
  ADD KEY `detail_transaksi_ibfk_1` (`noresi`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`noresi`),
  ADD KEY `transaksi_ibfk_1` (`username`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=134;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`noresi`) REFERENCES `transaksi` (`noresi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`sku`) REFERENCES `barang` (`sku`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
