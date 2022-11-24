-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 25, 2021 at 09:38 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lms`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `bookId` int(7) NOT NULL,
  `bookName` varchar(30) NOT NULL,
  `Author` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `fileSize` varchar(25) NOT NULL,
  `fileType` varchar(15) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `bookType` varchar(15) NOT NULL,
  `gread` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`bookId`, `bookName`, `Author`, `date`, `fileSize`, `fileType`, `subject`, `bookType`, `gread`) VALUES
(1, 'Chemistry ', 'Moe', '2021-05-12', '200', 'Pdf', 'Math', 'ref', '10'),
(2, 'Electronics System', 'Moe', '2021-05-11', '2', 'Doc', 'Biology', 'text', '11'),
(11, 'The Human System', 'Moe', '2021-05-11', '2', 'Doc', 'Biology', 'text', '11'),
(12, 'Java', 'Moe', '2021-05-11', '188', 'Doc', 'Biology', 'text', '11'),
(13, 'C++', 'Moe', '2021-05-11', '220', 'Doc', 'Biology', 'text', '11'),
(14, 'Python', 'Moe', '2021-05-11', '55', 'Doc', 'Biology', 'text', '11'),
(16, 'Economics', 'Moe', '2021-05-11', '2020', 'Doc', 'Biology', 'text', '11'),
(17, 'Data Stracture', 'moe', '2021-03-02', '20', 'Doc', 'physics', 'ref', '10'),
(18, 'Calcules', 'moe', '2021-02-04', '250', 'Pdf', 'biology', 'txt', '10'),
(19, 'OOP 1', 'Moe', '2021-02-04', '250', 'Doc', 'Physics', 'ref', '12'),
(20, 'OOP 2', 'Moe', '2021-03-01', '780', 'Pdf', 'IT', 'txt', '08'),
(21, 'book2', 'Moe', '2021-02-12', '21', 'ad', 'CS', 'doc', '8'),
(22, 'book3', 'Moe', '2021-02-01', '452', 'pdf', 'CE', 'pdf', '7'),
(30, 'sol', 'Moe', '2021-02-03', '21', 'da', 'amharic', 'text', '10'),
(31, 'book E', 'Moe', '2021-02-03', '21', 'Doc', 'amharic', 'text', '10'),
(32, 'Balageru Sami', 'Moe', '2021-02-03', '21Mb', 'pdf', 'Computer science', 'ref', 'Digree');

-- --------------------------------------------------------

--
-- Table structure for table `libraryst`
--

CREATE TABLE `libraryst` (
  `id` int(7) DEFAULT NULL,
  `libName` varchar(30) NOT NULL,
  `libPassword` varchar(30) NOT NULL,
  `libFullName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `libraryst`
--

INSERT INTO `libraryst` (`id`, `libName`, `libPassword`, `libFullName`) VALUES
(1, 'libraryst', 'libraryst', 'libraryst'),
(NULL, 'admin', 'admin', 'adminstrator');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `studId` int(7) NOT NULL,
  `IdNo` varchar(20) NOT NULL,
  `studUserName` varchar(30) NOT NULL,
  `studPass` varchar(30) NOT NULL,
  `fullName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`studId`, `IdNo`, `studUserName`, `studPass`, `fullName`) VALUES
(1, '14249/19', 'admin', 'admin', 'yesahek haile'),
(2, '14250/19', 'ukee', '1234', 'yesahek haile'),
(3, '14251/19', 'yesa', 'yesa', 'yesahek haile'),
(5, '14251/19', 'kkkk', 'kkkk', 'yesahek'),
(6, '001849', 'eyob', 'eyob', 'eyob Haile');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`bookId`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`studId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `bookId` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `studId` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
