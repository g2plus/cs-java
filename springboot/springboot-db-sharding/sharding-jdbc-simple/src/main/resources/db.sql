CREATE
DATABASE `order_db` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';


DROP TABLE IF EXISTS `t_order_1`;
CREATE TABLE `t_order_1`
(
    `order_id` bigint(20) NOT NULL COMMENT '订单id',
    `price`    decimal(10, 2)                                         NOT NULL COMMENT '订单价格',
    `user_id`  bigint(20) NOT NULL COMMENT '下单用户id',
    `status`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_order_2`;
CREATE TABLE `t_order_2`
(
    `order_id` bigint(20) NOT NULL COMMENT '订单id',
    `price`    decimal(10, 2)                                         NOT NULL COMMENT '订单价格',
    `user_id`  bigint(20) NOT NULL COMMENT '下单用户id',
    `status`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE DATABASE `user_db` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `user_id` bigint(20) NOT NULL COMMENT '用户id',
                          `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
                          `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
                          PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;