/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : localhost:3306
 Source Schema         : inventory_management_system

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 14/05/2024 20:14:26
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1001, 'e10adc3949ba59abbe56e057f20f883e', 1, 1, 1, 1, 1);
INSERT INTO `authority` VALUES (1002, 'e10adc3949ba59abbe56e057f20f883e', 1, 1, 1, 1, 1);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1001, 'zhangshan', '1', '123455678911', '2024-04-10', '2024-04-09');
INSERT INTO `employee` VALUES (1002, 'wangwu', '1', '123455678911', '2024-04-10', '2024-04-09');

-- ----------------------------
-- Table structure for list_inventory
-- ----------------------------
DROP TABLE IF EXISTS `list_inventory`;
CREATE TABLE `list_inventory`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `storage_price` double(10, 2) NULL DEFAULT NULL,
  `sale_price` double(10, 2) NULL DEFAULT NULL,
  `profit_price` double(10, 2) NULL DEFAULT NULL,
  `remaining_count` int(11) NULL DEFAULT NULL,
  `Replenish_count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of list_inventory
-- ----------------------------
INSERT INTO `list_inventory` VALUES (1, 100001, '2024-05-09 00:00:00', 600.00, 300.00, -300.00, 100, 0);
INSERT INTO `list_inventory` VALUES (2, 100002, '2024-05-09 00:00:00', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (5, 100001, '2024-05-10 00:00:00', 600.00, 300.00, -300.00, 100, 0);
INSERT INTO `list_inventory` VALUES (6, 100002, '2024-05-10 00:00:00', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (7, 100001, '2024-05-11 00:00:00', 600.00, 300.00, -300.00, 100, 0);
INSERT INTO `list_inventory` VALUES (8, 100002, '2024-05-11 00:00:00', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (9, 100001, '2024-05-12 00:00:00', 600.00, 300.00, -300.00, 100, 0);
INSERT INTO `list_inventory` VALUES (10, 100002, '2024-05-12 00:00:00', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (11, 100001, '2024-05-12 00:00:00', 600.00, 300.00, -300.00, 100, 0);
INSERT INTO `list_inventory` VALUES (12, 100002, '2024-05-12 00:00:00', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (13, 100001, '2024-05-14 00:00:00', 600.00, 300.00, -300.00, 100, 0);
INSERT INTO `list_inventory` VALUES (14, 100002, '2024-05-14 00:00:00', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (15, 100003, '2024-05-14 00:00:00', 125.00, 250.00, 125.00, 0, 100);
INSERT INTO `list_inventory` VALUES (16, 100001, '2024-05-14 09:35:21', 600.00, 300.00, -300.00, 100, 0);
INSERT INTO `list_inventory` VALUES (17, 100002, '2024-05-14 09:35:21', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (18, 100003, '2024-05-14 09:35:21', 125.00, 250.00, 125.00, 0, 100);
INSERT INTO `list_inventory` VALUES (19, 100001, '2024-05-14 09:35:51', 600.00, 300.00, -300.00, 100, 0);
INSERT INTO `list_inventory` VALUES (20, 100002, '2024-05-14 09:35:51', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (21, 100003, '2024-05-14 09:35:51', 125.00, 250.00, 125.00, 0, 100);
INSERT INTO `list_inventory` VALUES (22, 100001, '2024-05-14 09:36:25', 600.00, 600.00, 0.00, 50, 50);
INSERT INTO `list_inventory` VALUES (23, 100002, '2024-05-14 09:36:25', 450.00, 450.00, 0.00, 0, 100);
INSERT INTO `list_inventory` VALUES (24, 100003, '2024-05-14 09:36:25', 125.00, 250.00, 125.00, 0, 100);

-- ----------------------------
-- Table structure for list_sales
-- ----------------------------
DROP TABLE IF EXISTS `list_sales`;
CREATE TABLE `list_sales`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) NULL DEFAULT NULL,
  `sale_time` datetime NULL DEFAULT NULL,
  `sale_price` double(10, 2) NULL DEFAULT NULL,
  `sale_volume` int(11) NULL DEFAULT NULL,
  `sale_employee_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of list_sales
-- ----------------------------
INSERT INTO `list_sales` VALUES (10001, 100001, '2024-05-01 01:01:01', 3.00, 50, 1001);
INSERT INTO `list_sales` VALUES (10002, 100001, '2024-05-03 01:01:01', 3.00, 50, 1001);
INSERT INTO `list_sales` VALUES (10003, 100002, '2024-05-09 01:01:01', 3.00, 50, 1001);
INSERT INTO `list_sales` VALUES (10004, 100002, '2024-05-09 01:01:01', 3.00, 50, 1001);
INSERT INTO `list_sales` VALUES (10005, 100002, '2024-05-09 01:01:01', 3.00, 50, 1001);
INSERT INTO `list_sales` VALUES (10006, 100003, '2024-05-13 01:01:01', 5.00, 50, 1001);
INSERT INTO `list_sales` VALUES (10007, 100001, '2024-05-13 12:12:12', 6.00, 50, 1001);

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
) ENGINE = InnoDB AUTO_INCREMENT = 100005 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of list_stock
-- ----------------------------
INSERT INTO `list_stock` VALUES (100001, '可口可乐', 50, 100, 3, '可口可乐', '可口可乐');
INSERT INTO `list_stock` VALUES (100002, '薯片', 0, 100, 3, '乐事', '乐事');
INSERT INTO `list_stock` VALUES (100003, '辣条', 0, 100, 2.5, '喂龙', '好吃');
INSERT INTO `list_stock` VALUES (100004, 'test', 25, 15, 1.5, '测试', '测试123test');

-- ----------------------------
-- Table structure for list_storage
-- ----------------------------
DROP TABLE IF EXISTS `list_storage`;
CREATE TABLE `list_storage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) NULL DEFAULT NULL,
  `storage_time` datetime NULL DEFAULT NULL,
  `storage_price` double(10, 2) NULL DEFAULT NULL,
  `storage_volume` int(11) NULL DEFAULT NULL,
  `storage_employee_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10006 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of list_storage
-- ----------------------------
INSERT INTO `list_storage` VALUES (10001, 100001, '2024-05-03 01:01:01', 3.00, 100, 1001);
INSERT INTO `list_storage` VALUES (10002, 100001, '2024-05-01 01:01:01', 3.00, 100, 1001);
INSERT INTO `list_storage` VALUES (10003, 100002, '2024-05-03 01:01:01', 3.00, 100, 1001);
INSERT INTO `list_storage` VALUES (10004, 100002, '2024-05-03 01:01:01', 3.00, 50, 1001);
INSERT INTO `list_storage` VALUES (10005, 100003, '2024-05-14 01:01:01', 2.50, 50, 1001);

SET FOREIGN_KEY_CHECKS = 1;
