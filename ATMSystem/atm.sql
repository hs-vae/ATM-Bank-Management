/*
 Navicat Premium Data Transfer

 Source Server         : hs
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : atm

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 23/01/2021 21:24:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `cardNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cusromerName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `remainMoney` decimal(20, 2) DEFAULT NULL,
  PRIMARY KEY (`cardNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for accountrecordinfo
-- ----------------------------
DROP TABLE IF EXISTS `accountrecordinfo`;
CREATE TABLE `accountrecordinfo`  (
  `cardNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cusromerName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `remainMoney` decimal(20, 2) DEFAULT NULL,
  `contactNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `validityPeriod` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`cardNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(0) NOT NULL,
  `cardNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `operationType` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `oldMoney` decimal(20, 2) DEFAULT NULL,
  `newMoney` decimal(20, 2) DEFAULT NULL,
  `operationTime` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_charge_record
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_record`;
CREATE TABLE `t_charge_record`  (
  `chargeId` int(0) NOT NULL AUTO_INCREMENT,
  `chargeTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `chargeMoney` decimal(20, 2) DEFAULT NULL,
  `customerName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cardNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `chargeType` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `remainMoney` decimal(20, 2) DEFAULT NULL,
  `profitMoney` decimal(20, 2) DEFAULT NULL,
  PRIMARY KEY (`chargeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer_info
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_info`;
CREATE TABLE `t_customer_info`  (
  `cardNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `certifyNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `customerName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `remainMoney` decimal(20, 2) DEFAULT NULL,
  `createDate` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `createCardBank` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `savetype` int(0) DEFAULT NULL,
  PRIMARY KEY (`cardNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_log_info
-- ----------------------------
DROP TABLE IF EXISTS `t_log_info`;
CREATE TABLE `t_log_info`  (
  `logid` int(0) NOT NULL AUTO_INCREMENT,
  `loginfo` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `logtype` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `logtime` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `operateUser` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`logid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_profit_record
-- ----------------------------
DROP TABLE IF EXISTS `t_profit_record`;
CREATE TABLE `t_profit_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cardNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `profit_value` decimal(20, 2) DEFAULT NULL,
  `remainMoney` decimal(20, 2) DEFAULT NULL,
  `computed_profit` decimal(20, 2) DEFAULT NULL,
  `computed_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `is_computed` int(0) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_profit_type
-- ----------------------------
DROP TABLE IF EXISTS `t_profit_type`;
CREATE TABLE `t_profit_type`  (
  `id` int(0) NOT NULL,
  `profitName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `profit` decimal(20, 2) DEFAULT NULL,
  `saveDays` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for profit_computed4stat_proc
-- ----------------------------
DROP PROCEDURE IF EXISTS `profit_computed4stat_proc`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `profit_computed4stat_proc`(in saveType INT)
BEGIN
		DECLARE vCardNumber varchar(30);
		DECLARE vChargeTime varchar(30);
		DECLARE dRemainMoney decimal(20,2);
		DECLARE dProfitValue decimal(20,2);
		DECLARE done INT DEFAULT 0;
		
		DECLARE cur CURSOR for
		SELECT
		 cardNumber,sum(profit_value)
		FROM 
			t_profit_record
		WHERE
			 DATE_FORMAT(computed_time,'%Y-%m-%d') <= DATE_FORMAT(now(),'%Y-%m-%d')
			AND is_computed = 0
		GROUP BY cardNumber;
		
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
			
			OPEN cur;
			REPEAT
			SET autocommit = 0;
				
			FETCH cur into vCardNumber,dProfitValue;
			IF NOT done THEN
				SET SQL_SAFE_UPDATES = 0;
				UPDATE t_customer_info tci
					SET tci.remainMoney = (tci.remainMoney + dProfitValue)
					where cardNumber = vCardNumber;
					update t_proft_record tcr
					SET tcr.is_computed = 1
					where cardNumber = vCardNumber;
					commit;
			END IF;
		UNTIL done END REPEAT;
		CLOSE cur;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for profit_computedperday_procs
-- ----------------------------
DROP PROCEDURE IF EXISTS `profit_computedperday_procs`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `profit_computedperday_procs`(in saveType INT)
BEGIN
		DECLARE iFitID int(4);
		DECLARE vCardNumber	varchar(30);
		DECLARE dRemainMoney decimal(20,2);
		DECLARE vChargeTime varchar(30);
		DECLARE dProfitValue decimal(20,2);
		DECLARE done INT DEFAULT 0;
		
		#声明查询账户信息的数据游标
		DECLARE cur CURSOR for
		SELECT
			tci.saveType,tci.cardNumber,tci.remainMoney,tpt.profit
				FROM
				t_customer_info tci,
				t_profit_type tpt
				WHERE
				tci.savetype = tpt.id
					AND tci.cardNumber > 0;
					
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
			
			OPEN cur;
			REPEAT
			SET autocommit = 0;
			
			FETCH cur into iFitID,vCardNumber,dRemainMoney,dProfitValue;
				 IF NOT done THEN
						 IF iFitID = 7 THEN
								 set @NProfitValue = dProfitValue / 360;
								 set @RemainMoney = dRemainMoney*@NProfitValue;
								 set SQL_SAFE_UPDATES = 0;
								 insert into t_profit_record(cardNumber,prodit_value,remainMoney,computed_profit,computed_time)
								 values(vCardNumber,@RemainMoney,dRemainMoney,dProfitValue,date_format(now(),'%Y-%m-%d %H:%i:%S'));
								 commit;
						 END IF;
				 END IF;
			UNTIL done END REPEAT;
		CLOSE cur;	
			
END
;;
delimiter ;

-- ----------------------------
-- Event structure for JOB_profitcompute_perseason
-- ----------------------------
DROP EVENT IF EXISTS `JOB_profitcompute_perseason`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` EVENT `JOB_profitcompute_perseason`
ON SCHEDULE
EVERY '1' MONTH STARTS '2020-12-17 22:00:00'
DO BEGIN
	if(date_format(now(),'%d') = 20 || date_format(now(),'%m') % 3 = 0) THEN
	CALL profit_computed4stat_proc(7);
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Event structure for JOB_profitcompute_preday
-- ----------------------------
DROP EVENT IF EXISTS `JOB_profitcompute_preday`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` EVENT `JOB_profitcompute_preday`
ON SCHEDULE
EVERY '1' MINUTE STARTS '2020-12-17 22:00:00'
DO BEGIN
  if(date_format(now(),'%H')>20) THEN
	CALL profit_computed4perday_proc(7);
	END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
