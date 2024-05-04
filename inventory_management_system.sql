/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : inventory_management_system

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 04/05/2024 19:58:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment`  (
  `appoint` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment
-- ----------------------------

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` int(11) NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `admin` tinyint(1) NULL DEFAULT NULL,
  `salesperson` tinyint(1) NULL DEFAULT NULL,
  `storageperson` tinyint(1) NULL DEFAULT NULL,
  `inventoryperson` tinyint(1) NULL DEFAULT NULL,
  `stockmanager` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1001, 'e10adc3949ba59abbe56e057f20f883e', 1, 1, 1, 1, 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hiredate` date NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1001, 'zhangshan', '1', '123455678911', '2024-04-10', '2024-04-09');

-- ----------------------------
-- Table structure for list_inventory
-- ----------------------------
DROP TABLE IF EXISTS `list_inventory`;
CREATE TABLE `list_inventory`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `total_price` double(10, 2) NULL DEFAULT NULL,
  `employee_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of list_inventory
-- ----------------------------

-- ----------------------------
-- Table structure for list_sales
-- ----------------------------
DROP TABLE IF EXISTS `list_sales`;
CREATE TABLE `list_sales`  (
  `id` int(11) NOT NULL,
  `stock_id` int(11) NULL DEFAULT NULL,
  `sale_time` datetime(0) NULL DEFAULT NULL,
  `sale_price` double(10, 2) NULL DEFAULT NULL,
  `sale_volume` int(11) NULL DEFAULT NULL,
  `sale_employee_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of list_sales
-- ----------------------------

-- ----------------------------
-- Table structure for list_stock
-- ----------------------------
DROP TABLE IF EXISTS `list_stock`;
CREATE TABLE `list_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reserve_now` int(10) NULL DEFAULT NULL,
  `reserve_min` int(10) NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `vendor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Introduction` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100052 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of list_stock
-- ----------------------------
INSERT INTO `list_stock` VALUES (100001, '百事可乐', 200, 100, 3, '可口可乐', '可好喝');
INSERT INTO `list_stock` VALUES (100002, '可口可乐', 100, 50, 3, '百事可乐', '白好喝');
INSERT INTO `list_stock` VALUES (100003, '乐事薯片测试', 120, 30, 3.5, '乐视', '薯片薯片');
INSERT INTO `list_stock` VALUES (100048, '测试', 123, 123, 123, '123', '123');
INSERT INTO `list_stock` VALUES (100049, '测试', 123, 123, 1231, '123', '123');
INSERT INTO `list_stock` VALUES (100050, '测试123', 1, 1, 1, 'asa', '123');
INSERT INTO `list_stock` VALUES (100051, '测试啊', 123, 123, 123, '123', '123');

-- ----------------------------
-- Table structure for list_storage
-- ----------------------------
DROP TABLE IF EXISTS `list_storage`;
CREATE TABLE `list_storage`  (
  `id` int(11) NOT NULL,
  `stock_id` int(11) NULL DEFAULT NULL,
  `storage_time` datetime(0) NULL DEFAULT NULL,
  `storage_price` double(10, 2) NULL DEFAULT NULL,
  `storage_volume` int(11) NULL DEFAULT NULL,
  `storage_employee_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of list_storage
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
