-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- 主機: 127.0.0.1
-- 產生時間： 2016-07-19 07:07:43
-- 伺服器版本: 10.1.10-MariaDB
-- PHP 版本： 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `db`
--

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_count`
--

CREATE TABLE `tbl_count` (
  `amount` int(20) NOT NULL,
  `_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `details_key` int(10) NOT NULL,
  `_key` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `tbl_count`
--

INSERT INTO `tbl_count` (`amount`, `_date`, `details_key`, `_key`) VALUES
(10, '2016-03-05 16:00:00', 44, 1),
(120, '2016-03-05 16:00:00', 45, 2),
(20, '2016-03-05 16:00:00', 46, 3),
(30, '2016-03-05 16:00:00', 47, 4),
(10, '2016-03-08 16:00:00', 48, 5),
(20, '2016-03-08 16:00:00', 49, 6),
(20, '2016-03-08 16:00:00', 50, 7),
(50, '2016-03-09 16:00:00', 51, 8),
(30, '2016-06-12 07:26:28', 48, 11);

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `tbl_count`
--
ALTER TABLE `tbl_count`
  ADD PRIMARY KEY (`_key`),
  ADD KEY `details_key` (`details_key`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `tbl_count`
--
ALTER TABLE `tbl_count`
  MODIFY `_key` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `tbl_count`
--
ALTER TABLE `tbl_count`
  ADD CONSTRAINT `tbl_count_ibfk_1` FOREIGN KEY (`details_key`) REFERENCES `tbl_details` (`details_key`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
