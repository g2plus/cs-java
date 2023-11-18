CREATE DATABASE ds_0;
use ds_0;
DROP TABLE IF EXISTS `order0`;
CREATE TABLE `order0`  (
                           `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `sharding_db_id` int(0) NOT NULL COMMENT '分库用的ID',
                           `sharding_table_id` int(0) NOT NULL COMMENT '分表用的ID',
                           `key_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '全局id',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1`  (
                           `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `sharding_db_id` int(0) NOT NULL COMMENT '分库用的ID',
                           `sharding_table_id` int(0) NOT NULL COMMENT '分表用的ID',
                           `key_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '全局id',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


CREATE DATABASE ds_1;
use ds_1;
DROP TABLE IF EXISTS `order0`;
CREATE TABLE `order0`  (
                           `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `sharding_db_id` int(0) NOT NULL COMMENT '分库用的ID',
                           `sharding_table_id` int(0) NOT NULL COMMENT '分表用的ID',
                           `key_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '全局id',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1`  (
                           `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `sharding_db_id` int(0) NOT NULL COMMENT '分库用的ID',
                           `sharding_table_id` int(0) NOT NULL COMMENT '分表用的ID',
                           `key_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '全局id',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;