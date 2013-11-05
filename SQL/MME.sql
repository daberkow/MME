-- phpMyAdmin SQL Dump
-- version 3.3.7deb7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 05, 2013 at 10:17 PM
-- Server version: 5.1.66
-- PHP Version: 5.3.3-7+squeeze14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `MME`
--

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE IF NOT EXISTS `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `artist` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`id`, `name`, `artist`) VALUES
(6, 'Red Disc 1', 3);

-- --------------------------------------------------------

--
-- Table structure for table `artists`
--

CREATE TABLE IF NOT EXISTS `artists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artist` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `artists`
--

INSERT INTO `artists` (`id`, `artist`) VALUES
(3, 'Taylor Swift');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE IF NOT EXISTS `jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL,
  `arguments` text NOT NULL,
  `status` tinyint(4) NOT NULL,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id`, `type`, `arguments`, `status`, `start`, `end`) VALUES
(1, 1, '', 1, '2012-12-12 16:35:19', '0000-00-00 00:00:00'),
(2, 1, '', 1, '2012-12-12 17:39:33', '0000-00-00 00:00:00'),
(3, 1, '', 1, '2012-12-12 17:39:33', '0000-00-00 00:00:00'),
(4, 1, '', 1, '2012-12-12 17:39:46', '0000-00-00 00:00:00'),
(5, 1, '', 1, '2012-12-12 17:40:13', '0000-00-00 00:00:00'),
(6, 1, '', 2, '2012-12-12 17:46:05', '0000-00-00 00:00:00'),
(7, 1, '', 2, '2012-12-12 17:47:57', '0000-00-00 00:00:00'),
(8, 1, '', 2, '2012-12-12 17:48:49', '2012-12-12 17:48:49');

-- --------------------------------------------------------

--
-- Table structure for table `radio`
--

CREATE TABLE IF NOT EXISTS `radio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `songID` int(11) NOT NULL,
  `channel` int(11) NOT NULL,
  `order` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `radio`
--


-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE IF NOT EXISTS `settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `setting` varchar(16) NOT NULL,
  `value` varchar(128) NOT NULL,
  `value-machine` tinyint(4) NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`id`, `setting`, `value`, `value-machine`) VALUES
(1, 'mode', 'radio', 0),
(2, 'storage', '/music/', 0);

-- --------------------------------------------------------

--
-- Table structure for table `songs`
--

CREATE TABLE IF NOT EXISTS `songs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file` text NOT NULL,
  `song` varchar(32) NOT NULL,
  `album` int(11) NOT NULL,
  `plays` int(11) NOT NULL,
  `length` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `songs`
--

INSERT INTO `songs` (`id`, `file`, `song`, `album`, `plays`, `length`, `status`) VALUES
(11, '/music/Taylor Swift/Red Disc 1/03 Treacherous.mp3', 'Treacherous', 6, 0, 243, 1),
(10, '/music/Taylor Swift/Red Disc 1/02 Red.mp3', 'Red', 6, 0, 223, 1),
(9, '/music/Taylor Swift/Red Disc 1/01 State of Grace.mp3', 'State of Grace', 6, 0, 295, 1),
(12, '/music/Taylor Swift/Red Disc 1/04 I Knew You Were Trouble.mp3', 'I Knew You Were Trouble', 6, 0, 220, 1),
(13, '/music/Taylor Swift/Red Disc 1/05 All Too Well.mp3', 'All Too Well', 6, 0, 329, 1);
