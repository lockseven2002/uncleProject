-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- 主機: 127.0.0.1
-- 產生時間： 2016-07-19 07:07:51
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
-- 資料表結構 `tbl_details`
--

CREATE TABLE `tbl_details` (
  `species_key` int(10) NOT NULL,
  `details_key` int(11) NOT NULL,
  `detailsName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `tbl_details`
--

INSERT INTO `tbl_details` (`species_key`, `details_key`, `detailsName`, `price`) VALUES
(2, 44, '鮭魚生魚片', 200),
(2, 45, '旗魚生魚片', 200),
(3, 46, '旗魚握壽司', 180),
(3, 47, '鮭魚握壽司', 200),
(4, 48, '海苔壽司', 80),
(4, 49, '豆皮壽司', 80),
(5, 50, '烤鯖魚', 300),
(5, 51, '烤秋刀魚', 200);

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `tbl_details`
--
ALTER TABLE `tbl_details`
  ADD PRIMARY KEY (`details_key`),
  ADD KEY `species_key` (`species_key`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `tbl_details`
--
ALTER TABLE `tbl_details`
  MODIFY `details_key` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `tbl_details`
--
ALTER TABLE `tbl_details`
  ADD CONSTRAINT `tbl_details_ibfk_1` FOREIGN KEY (`species_key`) REFERENCES `tbl_species` (`id`),
  ADD CONSTRAINT `tbl_details_ibfk_2` FOREIGN KEY (`species_key`) REFERENCES `tbl_species` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
