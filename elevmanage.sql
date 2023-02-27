-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: elevmanage
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `em_accessory`
--

DROP TABLE IF EXISTS `em_accessory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_accessory` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '配件ID',
  `accessory_number` varchar(32) NOT NULL COMMENT '配件编号',
  `accessory_name` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配件名称',
  `specification` varchar(90) NOT NULL COMMENT '规格型号',
  `type` varchar(90) NOT NULL COMMENT '配件类型',
  `unit` varchar(90) NOT NULL COMMENT '单位',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='配件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_accessory`
--

LOCK TABLES `em_accessory` WRITE;
/*!40000 ALTER TABLE `em_accessory` DISABLE KEYS */;
INSERT INTO `em_accessory` VALUES (7,'YMP001','云母片','1mm','电梯保护装置','片',NULL,'2023-02-25 12:36:05',NULL,'0'),(8,'JXFS001','轿厢扶手','L-800mm','轿厢配件','个',NULL,'2023-02-25 12:36:20',NULL,'0'),(9,'JFWDKZQ001','机房温度控制器','AC220V','控制系统配件','个',NULL,'2023-02-25 12:36:28',NULL,'0'),(10,'DTL001','电梯轮','φ100mm','电梯传动部件','个',NULL,'2023-02-25 12:36:43',NULL,'0'),(11,'JXDB001','轿厢顶板','H-200mm','轿厢配件','片',NULL,'2023-02-25 12:37:01',NULL,'0'),(12,'JSJCL001','减速机齿轮','M1.5','电梯传动部件','个',NULL,'2023-02-25 12:37:06',NULL,'0'),(13,'DTMS001','电梯门扇','W-800mm','门系统配件','个',NULL,'2023-02-25 12:37:10',NULL,'0'),(14,'XSQDH001','限速器弹簧','L-50mm','电梯保护装置','个',NULL,'2023-02-25 12:37:14',NULL,'0'),(15,'DTMT001','电梯门套','W-2000mm','门系统配件','个',NULL,'2023-02-25 12:37:18',NULL,'0'),(16,'FHMK001','防火门框','W-900mm','门系统配件','个',NULL,'2023-02-25 12:37:24',NULL,'0'),(17,'DTMS002','电梯门锁','φ20mm','门系统配件','个',NULL,'2023-02-25 12:37:28',NULL,'0'),(18,'DTAN001','电梯按钮','5V','控制系统配件','个',NULL,'2023-02-25 12:37:32',NULL,'0'),(19,'JFTFS001','机房通风扇','AC380V','控制系统配件','个',NULL,'2023-02-25 12:37:36',NULL,'0'),(20,'DTJDD001','电梯井道灯','AC220V','电气系统配件','个',NULL,'2023-02-25 12:37:40',NULL,'0'),(21,'DTDKXSQ001','电梯底坑吸水器','L-500mm','电梯保护装置','个',NULL,'2023-02-25 12:37:44',NULL,'0'),(22,'YMP002','云母片','2mm','电梯保护装置','片',NULL,'2023-02-25 12:37:48',NULL,'0'),(23,'JXFS002','轿厢扶手','L-900mm','轿厢配件','个',NULL,'2023-02-25 12:38:00',NULL,'0'),(24,'JFWDKZQ002','机房温度控制器','AC110V','控制系统配件','个',NULL,'2023-02-25 12:38:04',NULL,'0'),(25,'DTL002','电梯轮','φ120mm','电梯传动部件','个',NULL,'2023-02-25 12:38:07',NULL,'0'),(26,'JXDB002','轿厢顶板','H-250mm','轿厢配件','片',NULL,'2023-02-25 12:38:11',NULL,'0'),(27,'JSJCL002','减速机齿轮','M2','电梯传动部件','个',NULL,'2023-02-25 12:38:14',NULL,'0'),(28,'DTMS003','电梯门扇','W-900mm','门系统配件','个',NULL,'2023-02-25 12:38:18',NULL,'0'),(29,'XSQDH002','限速器弹簧','L-60mm','电梯保护装置','个',NULL,'2023-02-25 12:38:21',NULL,'0'),(30,'DTMT002','电梯门套','W-2200mm','门系统配件','个',NULL,'2023-02-25 12:38:25',NULL,'0'),(31,'FHMK002','防火门框','W-1000mm','门系统配件','个',NULL,'2023-02-25 12:38:29',NULL,'0');
/*!40000 ALTER TABLE `em_accessory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_elevator`
--

DROP TABLE IF EXISTS `em_elevator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_elevator` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '电梯id',
  `elevator_number` varchar(32) NOT NULL COMMENT '电梯编号',
  `elevator_name` varchar(90) NOT NULL COMMENT '电梯名称',
  `location_name` varchar(90) NOT NULL COMMENT '场所名称',
  `manufacturer_name` varchar(90) NOT NULL COMMENT '生产厂家',
  `model` varchar(32) NOT NULL COMMENT '设备型号',
  `load_speed` int NOT NULL COMMENT '载重速度',
  `product_number` varchar(32) NOT NULL COMMENT '产品编号',
  `address` varchar(90) NOT NULL COMMENT '详细地址',
  `manufacturer_id` int NOT NULL COMMENT '电梯厂家ID',
  `location_id` int NOT NULL COMMENT '场所ID',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `contact_person` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人',
  `contact_number` varchar(90) NOT NULL COMMENT '联系电话',
  `status` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '正常' COMMENT '电梯状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电梯';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_elevator`
--

LOCK TABLES `em_elevator` WRITE;
/*!40000 ALTER TABLE `em_elevator` DISABLE KEYS */;
INSERT INTO `em_elevator` VALUES (27,'DS20230225001','天空之梯','北京科技馆','蒂森','XG-3000',1000,'1001-001','北京市海淀区中关村南大街5号',7,3,NULL,'2023-02-25 12:03:02','2023-02-25 12:27:30','0','李思琪','010-12345678','待维修'),(28,'SL20230225001','龙腾电梯','武汉长江大桥','三菱','SC-2000',800,'1001-002','湖北省武汉市江汉区长江大桥',4,8,NULL,'2023-02-25 12:04:12','2023-02-25 12:29:03','0','林嘉欣','027-66666666','正常'),(29,'RL20230225001','翔龙电梯','南京大学','日立','HG-1000',1000,'1001-003','江苏省南京市鼓楼区汉口路22号',5,9,NULL,'2023-02-25 12:05:03','2023-02-25 12:29:09','0','吴雪莲','025-55555555','正常'),(30,'TL20230225001','春天电梯','上海电视台','通力','CD-5000',1200,'1001-004','上海市徐汇区虹漕路253号',6,4,NULL,'2023-02-25 12:05:54','2023-02-25 12:27:49','0','刘春雨','021-87654321','待检查'),(31,'ADS20230225001','海豚电梯','杭州西湖','奥的斯','TD-800',800,'1001-005','浙江省杭州市西湖区西湖风景区',3,10,NULL,'2023-02-25 12:06:22','2023-02-25 12:29:14','0','郑宏伟','0571-99999999','正常'),(32,'FSD20230225001','金鹰电梯','深圳大剧院','富士达','JY-2000',1000,'1001-006','深圳市福田区红岭北路2013号',8,6,NULL,'2023-02-25 12:06:57','2023-02-25 12:28:06','0','王梓涵','0755-88888888','待维修'),(33,'DZ20230225001','海滨电梯','广州国际金融中心','东芝','HB-3000',1200,'1001-007','广州市天河区珠江新城华穗路5号',9,5,NULL,'2023-02-25 12:09:10','2023-02-25 12:28:00','0','张子轩','020-55555555','正常'),(34,'XD20230225001','天安电梯','北京科技馆','迅达','TA-1000',1000,'1001-008','北京市海淀区中关村南大街5号',10,3,NULL,'2023-02-25 12:09:46','2023-02-25 12:27:30','0','李思琪','010-12345678','正常'),(35,'FSD20230225002','盛世电梯','南京大学','富士达','SS-5000',1200,'1001-009','江苏省南京市鼓楼区汉口路22号',8,9,NULL,'2023-02-25 12:10:08','2023-02-25 12:29:09','0','吴雪莲','025-55555555','待维修'),(36,'TL20230225002','龙珠电梯','上海电视台','通力','LZ-888',800,'1001-010','上海市徐汇区虹漕路253号',6,4,NULL,'2023-02-25 12:10:32','2023-02-25 12:27:49','0','刘春雨','021-87654321','正常'),(37,'FSD20230225003','远航电梯','成都市第一人民医院','富士达','YH-5000',1200,'1001-021','四川省成都市青羊区人民北路1号',8,7,NULL,'2023-02-25 12:11:18','2023-02-25 12:28:14','0','陈俊博','028-77777777','待检查'),(38,'XD20230225002','荣耀电梯','深圳大剧院','迅达','RY-2000',1000,'1001-022','深圳市福田区红岭北路2013号',10,6,NULL,'2023-02-25 12:12:21','2023-02-25 12:28:06','0','王梓涵','0755-88888888','正常'),(39,'SL20230225002','钢铁巨人电梯','北京科技馆','三菱','GTJ-5000',1200,'1001-023','北京市海淀区中关村南大街5号',4,3,NULL,'2023-02-25 12:13:03','2023-02-25 12:27:30','0','李思琪','010-12345678','正常'),(40,'TL20230225003','梦想电梯','南京大学','通力','MX-2000',1000,'1001-024','江苏省南京市鼓楼区汉口路22号',6,9,NULL,'2023-02-25 12:13:53','2023-02-25 12:29:09','0','吴雪莲','025-55555555','正常'),(41,'ADS20230225002','阳光电梯','杭州西湖','奥的斯','YG-5000',1200,'1001-025','浙江省杭州市西湖区西湖风景区',3,10,NULL,'2023-02-25 12:14:18','2023-02-25 12:29:14','0','郑宏伟','0571-99999999','正常'),(42,'DS20230225002','星际穿越电梯','上海电视台','蒂森','XJCY-2000',1000,'1001-026','上海市徐汇区虹漕路253号',7,4,NULL,'2023-02-25 12:14:42','2023-02-25 12:27:49','0','刘春雨','021-87654321','正常'),(43,'DZ20230225002','闪电电梯','南京大学','东芝','SD-5000',1200,'1001-027','江苏省南京市鼓楼区汉口路22号',9,9,NULL,'2023-02-25 12:15:12','2023-02-25 12:29:09','0','吴雪莲','025-55555555','正常'),(44,'FSD20230225004','大都会电梯','深圳大剧院','富士达','DDH-2000',1000,'1001-028','深圳市福田区红岭北路2013号',8,6,NULL,'2023-02-25 12:15:37','2023-02-25 12:28:06','0','王梓涵','0755-88888888','正常'),(45,'RL20230225002','华灯初上电梯','北京科技馆','日立','HDCS-5000',1200,'1001-029','北京市海淀区中关村南大街5号',5,3,NULL,'2023-02-25 12:16:08','2023-02-25 12:27:30','0','李思琪','010-12345678','正常'),(46,'XD20230225003','云端电梯','成都市第一人民医院','迅达','YD-2000',1000,'1001-030','四川省成都市青羊区人民北路1号',10,7,NULL,'2023-02-25 12:16:26','2023-02-25 12:28:14','0','陈俊博','028-77777777','正常');
/*!40000 ALTER TABLE `em_elevator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_elevator_image`
--

DROP TABLE IF EXISTS `em_elevator_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_elevator_image` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '电梯图片ID',
  `elevator_id` int NOT NULL COMMENT '电梯ID',
  `image_name` varchar(255) NOT NULL COMMENT '图片名',
  `image_position` varchar(900) DEFAULT NULL COMMENT '图片位置',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电梯图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_elevator_image`
--

LOCK TABLES `em_elevator_image` WRITE;
/*!40000 ALTER TABLE `em_elevator_image` DISABLE KEYS */;
INSERT INTO `em_elevator_image` VALUES (2,27,'BCB0604E-2D0B-4EDB-82D3-E616F8CE8641.jpeg','/elevator/BCB0604E-2D0B-4EDB-82D3-E616F8CE8641.jpeg','2023-02-25 12:03:02',NULL,'0'),(3,27,'C97B5131-A195-4F31-88FD-63B441C29841.jpeg','/elevator/C97B5131-A195-4F31-88FD-63B441C29841.jpeg','2023-02-25 12:03:02',NULL,'0'),(4,28,'E7A02EC1-745D-409F-81C0-BD033BD06213.jpeg','/elevator/E7A02EC1-745D-409F-81C0-BD033BD06213.jpeg','2023-02-25 12:04:12',NULL,'0'),(5,28,'9F76D9F4-5D46-4E5C-B6D3-6FCEC50CE37C.jpeg','/elevator/9F76D9F4-5D46-4E5C-B6D3-6FCEC50CE37C.jpeg','2023-02-25 12:04:12',NULL,'0'),(6,29,'2D0D761C-A7AE-4F1B-8621-8FEF2F9BEB66.jpeg','/elevator/2D0D761C-A7AE-4F1B-8621-8FEF2F9BEB66.jpeg','2023-02-25 12:05:03',NULL,'0'),(7,29,'95A27E03-D6AE-4398-B73E-A69DC57AFBF3.jpeg','/elevator/95A27E03-D6AE-4398-B73E-A69DC57AFBF3.jpeg','2023-02-25 12:05:03',NULL,'0'),(8,30,'A29881EC-39B9-4B36-BCAE-60C7C0247151.jpeg','/elevator/A29881EC-39B9-4B36-BCAE-60C7C0247151.jpeg','2023-02-25 12:05:54',NULL,'0'),(9,30,'73021E9D-554D-4385-8FF3-637F957C31BB.jpeg','/elevator/73021E9D-554D-4385-8FF3-637F957C31BB.jpeg','2023-02-25 12:05:54',NULL,'0'),(10,31,'2FFFBFD9-17D9-4596-9C9A-B2841E1AE2D5.jpeg','/elevator/2FFFBFD9-17D9-4596-9C9A-B2841E1AE2D5.jpeg','2023-02-25 12:06:22',NULL,'0'),(11,31,'977005CF-054B-4402-A189-6957D16E071B.jpeg','/elevator/977005CF-054B-4402-A189-6957D16E071B.jpeg','2023-02-25 12:06:22',NULL,'0'),(12,32,'92C6BE24-F8E1-4EC5-990A-D1D91E0198B6.jpg','/elevator/92C6BE24-F8E1-4EC5-990A-D1D91E0198B6.jpg','2023-02-25 12:06:57',NULL,'0'),(13,32,'42B33B04-DA06-4BFF-9040-E9319C9281DD.jpeg','/elevator/42B33B04-DA06-4BFF-9040-E9319C9281DD.jpeg','2023-02-25 12:06:57',NULL,'0'),(14,33,'7EDF3675-0A08-4EAB-82D0-BDB3D953C289.jpeg','/elevator/7EDF3675-0A08-4EAB-82D0-BDB3D953C289.jpeg','2023-02-25 12:09:10',NULL,'0'),(15,33,'099B8284-70BB-4F59-9021-E34A55406C6B.jpg','/elevator/099B8284-70BB-4F59-9021-E34A55406C6B.jpg','2023-02-25 12:09:11',NULL,'0'),(16,34,'A4E6CABC-811D-430D-B224-6F1C25D7A88D.png','/elevator/A4E6CABC-811D-430D-B224-6F1C25D7A88D.png','2023-02-25 12:09:46',NULL,'0'),(17,35,'68CE48B7-611B-4A2E-AD9E-78472CF63884.jpg','/elevator/68CE48B7-611B-4A2E-AD9E-78472CF63884.jpg','2023-02-25 12:10:08',NULL,'0'),(18,36,'9723356A-5C7D-4DF2-B891-7B4936E26591.jpg','/elevator/9723356A-5C7D-4DF2-B891-7B4936E26591.jpg','2023-02-25 12:10:32',NULL,'0'),(19,36,'1F6C05F7-4F4E-4C7D-A7D3-0F9A0D2FB517.jpeg','/elevator/1F6C05F7-4F4E-4C7D-A7D3-0F9A0D2FB517.jpeg','2023-02-25 12:10:32',NULL,'0'),(20,37,'3DDF5123-5F69-4F45-867E-41A71F3FE508.jpg','/elevator/3DDF5123-5F69-4F45-867E-41A71F3FE508.jpg','2023-02-25 12:11:18',NULL,'0'),(21,38,'EC4ACF8A-00FE-4422-956B-7D3E54A442ED.jpeg','/elevator/EC4ACF8A-00FE-4422-956B-7D3E54A442ED.jpeg','2023-02-25 12:12:21',NULL,'0'),(22,39,'52574D07-AA27-4410-8C8F-92E5BAFEE6DF.jpeg','/elevator/52574D07-AA27-4410-8C8F-92E5BAFEE6DF.jpeg','2023-02-25 12:13:03',NULL,'0'),(23,39,'96972F8E-2545-4854-8B90-D9691B715577.png','/elevator/96972F8E-2545-4854-8B90-D9691B715577.png','2023-02-25 12:13:03',NULL,'0'),(24,40,'D6C657B8-E5D0-40CB-9244-8E0C1F60AD6E.jpg','/elevator/D6C657B8-E5D0-40CB-9244-8E0C1F60AD6E.jpg','2023-02-25 12:13:53',NULL,'0'),(25,41,'DA7F2987-B8B0-4197-9F7D-B28BEDE15B1E.jpg','/elevator/DA7F2987-B8B0-4197-9F7D-B28BEDE15B1E.jpg','2023-02-25 12:14:18',NULL,'0'),(26,41,'9319381C-274D-44B5-897F-3D387481AF1A.jpeg','/elevator/9319381C-274D-44B5-897F-3D387481AF1A.jpeg','2023-02-25 12:14:18',NULL,'0'),(27,42,'9E1FCA6B-9888-4924-94D1-F705EBE2560D.png','/elevator/9E1FCA6B-9888-4924-94D1-F705EBE2560D.png','2023-02-25 12:14:42',NULL,'0'),(28,42,'41596214-3572-420A-9A11-3E73C31390C7.jpeg','/elevator/41596214-3572-420A-9A11-3E73C31390C7.jpeg','2023-02-25 12:14:42',NULL,'0'),(29,43,'EEBF27AA-9F97-4FFF-BAC2-99967CD598D1.jpg','/elevator/EEBF27AA-9F97-4FFF-BAC2-99967CD598D1.jpg','2023-02-25 12:15:12',NULL,'0'),(30,44,'36A35603-F29C-4F46-B873-12DA30931CA4.jpeg','/elevator/36A35603-F29C-4F46-B873-12DA30931CA4.jpeg','2023-02-25 12:15:37',NULL,'0'),(31,44,'E29107C9-D7E0-4283-91ED-C685FF5EBEB8.jpg','/elevator/E29107C9-D7E0-4283-91ED-C685FF5EBEB8.jpg','2023-02-25 12:15:37',NULL,'0'),(32,45,'15B07A71-384F-4E7F-9752-210E297542D1.jpeg','/elevator/15B07A71-384F-4E7F-9752-210E297542D1.jpeg','2023-02-25 12:16:08',NULL,'0'),(33,46,'CC2DBA69-E0D9-4C23-98E0-A219954AC022.jpeg','/elevator/CC2DBA69-E0D9-4C23-98E0-A219954AC022.jpeg','2023-02-25 12:16:26',NULL,'0');
/*!40000 ALTER TABLE `em_elevator_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_inspection`
--

DROP TABLE IF EXISTS `em_inspection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_inspection` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '检查报告ID',
  `inspection_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '检查编号',
  `elevator_number` varchar(32) NOT NULL COMMENT '电梯编号',
  `location_name` varchar(90) NOT NULL COMMENT '电梯地点',
  `contact_person` varchar(90) NOT NULL COMMENT '联系人',
  `contact_number` varchar(90) NOT NULL COMMENT '联系电话',
  `check_description` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '检查描述',
  `inspection_data` datetime DEFAULT NULL COMMENT '检查日期',
  `inspection_person` varchar(90) DEFAULT NULL COMMENT '检查人',
  `is_finish` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否完成',
  `is_fault` varchar(1) DEFAULT NULL COMMENT '是否故障',
  `elevator_id` int NOT NULL COMMENT '电梯ID',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `address` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址',
  `elevator_name` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电梯名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='检查报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_inspection`
--

LOCK TABLES `em_inspection` WRITE;
/*!40000 ALTER TABLE `em_inspection` DISABLE KEYS */;
INSERT INTO `em_inspection` VALUES (5,'JC20230225001','TL20230225001','上海电视台','刘春雨','021-87654321','电梯检查没有问题','2023-02-26 13:56:25','曹雪芹','1','0',30,NULL,'2023-02-25 13:56:25','2023-02-25 14:29:36','0','上海市徐汇区虹漕路253号','春天电梯'),(6,'JC20230225002','TL20230225001','上海电视台','刘春雨','021-87654321',NULL,NULL,NULL,'0',NULL,30,NULL,'2023-02-25 14:05:23',NULL,'0','上海市徐汇区虹漕路253号','春天电梯'),(7,'JC20230225003','DS20230225001','北京科技馆','李思琪','010-12345678','电梯故障，开门刀与层门锁滚轮碰撞','2023-02-26 15:56:25','陈俊杰','1','1',27,NULL,'2023-02-25 14:05:23','2023-02-25 14:40:25','0','北京市海淀区中关村南大街5号','天空之梯'),(8,'JC20230225004','FSD20230225002','南京大学','吴雪莲','025-55555555','电梯故障，平层感应器与隔磁板位置尺寸发生变化','2023-02-26 15:56:25','罗美玲','1','1',35,NULL,'2023-02-25 14:05:23','2023-02-25 14:42:34','0','江苏省南京市鼓楼区汉口路22号','盛世电梯'),(9,'JC20230225005','FSD20230225003','成都市第一人民医院','陈俊博','028-77777777',NULL,NULL,NULL,'0',NULL,37,NULL,'2023-02-25 14:05:23',NULL,'0','四川省成都市青羊区人民北路1号','远航电梯'),(10,'JC20230225006','FSD20230225001','深圳大剧院','王梓涵','0755-88888888','电梯检查没有问题','2023-02-26 13:56:25','曹雪芹','1','0',32,NULL,'2023-02-25 14:05:23','2023-02-25 14:32:20','0','深圳市福田区红岭北路2013号','金鹰电梯');
/*!40000 ALTER TABLE `em_inspection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_inspection_image`
--

DROP TABLE IF EXISTS `em_inspection_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_inspection_image` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '检查报告图片ID',
  `inspection_id` int DEFAULT NULL COMMENT '检查报告ID',
  `maintenance_id` int DEFAULT NULL COMMENT '维修报告ID',
  `image_name` varchar(255) NOT NULL COMMENT '图片名',
  `image_position` varchar(900) DEFAULT NULL COMMENT '图片位置',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='检查报告图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_inspection_image`
--

LOCK TABLES `em_inspection_image` WRITE;
/*!40000 ALTER TABLE `em_inspection_image` DISABLE KEYS */;
INSERT INTO `em_inspection_image` VALUES (2,5,NULL,'AD9BE99B-CED3-4C60-A8AD-54F89AA10DB8.png','/inspection/AD9BE99B-CED3-4C60-A8AD-54F89AA10DB8.png','2023-02-25 14:29:36',NULL,'0'),(3,5,NULL,'806BEFD4-186E-42A4-B05D-30B2F028F13C.jpeg','/inspection/806BEFD4-186E-42A4-B05D-30B2F028F13C.jpeg','2023-02-25 14:29:36',NULL,'0'),(4,10,NULL,'17CBE2FF-45EB-4AEB-A4C0-1B00A747CC9B.jpeg','/inspection/17CBE2FF-45EB-4AEB-A4C0-1B00A747CC9B.jpeg','2023-02-25 14:32:20',NULL,'0'),(5,10,NULL,'D8CE1376-5967-4B0B-9ECC-CE3B6214EBCA.jpeg','/inspection/D8CE1376-5967-4B0B-9ECC-CE3B6214EBCA.jpeg','2023-02-25 14:32:21',NULL,'0'),(8,7,9,'03B491A1-860D-468D-8AE8-84501AAB3E9D.jpeg','/inspection/03B491A1-860D-468D-8AE8-84501AAB3E9D.jpeg','2023-02-25 14:40:25','2023-02-25 14:40:26','0'),(9,7,9,'978BA040-7B99-447D-A7E8-40E7B908E07F.jpeg','/inspection/978BA040-7B99-447D-A7E8-40E7B908E07F.jpeg','2023-02-25 14:40:25','2023-02-25 14:40:26','0'),(10,8,10,'1282085C-2F88-4633-A64D-A684E267901F.jpeg','/inspection/1282085C-2F88-4633-A64D-A684E267901F.jpeg','2023-02-25 14:42:35','2023-02-25 14:42:35','0'),(11,8,10,'29513F52-E201-4ED5-84DB-1BCAB6F01627.png','/inspection/29513F52-E201-4ED5-84DB-1BCAB6F01627.png','2023-02-25 14:42:35','2023-02-25 14:42:35','0'),(12,NULL,12,'55C4C5F6-C53D-4253-A3C0-A2930B4B8B50.jpeg','/inspection/55C4C5F6-C53D-4253-A3C0-A2930B4B8B50.jpeg','2023-02-25 14:58:50',NULL,'0'),(13,NULL,12,'D0A89728-FA9E-4162-99E2-86E625AF0FED.jpeg','/inspection/D0A89728-FA9E-4162-99E2-86E625AF0FED.jpeg','2023-02-25 14:58:50',NULL,'0'),(14,NULL,13,'252460CE-5CB7-4608-8EB8-C4E0E352A4BB.jpeg','/inspection/252460CE-5CB7-4608-8EB8-C4E0E352A4BB.jpeg','2023-02-25 15:00:34',NULL,'0'),(15,NULL,13,'F370B845-22D4-45AA-AC1A-23B2451122D7.jpeg','/inspection/F370B845-22D4-45AA-AC1A-23B2451122D7.jpeg','2023-02-25 15:00:34',NULL,'0');
/*!40000 ALTER TABLE `em_inspection_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_inventory`
--

DROP TABLE IF EXISTS `em_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_inventory` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '库存管理ID',
  `accessory_id` int NOT NULL COMMENT '配件ID',
  `quantity` int NOT NULL DEFAULT '0' COMMENT '库存数量',
  `warning_quantity` int NOT NULL DEFAULT '0' COMMENT '预警数量',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `accessory_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配件编号',
  `accessory_name` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配件名称',
  `specification` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格型号',
  `type` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配件类型',
  `unit` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位',
  `unit_price` decimal(24,6) NOT NULL DEFAULT '0.000000' COMMENT '单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_inventory`
--

LOCK TABLES `em_inventory` WRITE;
/*!40000 ALTER TABLE `em_inventory` DISABLE KEYS */;
INSERT INTO `em_inventory` VALUES (4,7,1,5,NULL,'2023-02-25 12:36:05','2023-02-25 13:35:18','0','YMP001','云母片','1mm','电梯保护装置','片',68.640000),(5,8,12,3,NULL,'2023-02-25 12:36:20','2023-02-25 13:29:38','0','JXFS001','轿厢扶手','L-800mm','轿厢配件','个',354.400000),(6,9,20,4,NULL,'2023-02-25 12:36:28','2023-02-25 13:28:35','0','JFWDKZQ001','机房温度控制器','AC220V','控制系统配件','个',111.540000),(7,10,17,5,NULL,'2023-02-25 12:36:43','2023-02-25 13:33:34','0','DTL001','电梯轮','φ100mm','电梯传动部件','个',95.550000),(8,11,2,3,NULL,'2023-02-25 12:37:01','2023-02-25 13:29:38','0','JXDB001','轿厢顶板','H-200mm','轿厢配件','片',562.500000),(9,12,16,5,NULL,'2023-02-25 12:37:06','2023-02-25 13:28:35','0','JSJCL001','减速机齿轮','M1.5','电梯传动部件','个',54.500000),(10,13,6,4,NULL,'2023-02-25 12:37:10','2023-02-25 13:36:20','0','DTMS001','电梯门扇','W-800mm','门系统配件','个',135.200000),(11,14,5,3,NULL,'2023-02-25 12:37:14','2023-02-25 13:32:23','0','XSQDH001','限速器弹簧','L-50mm','电梯保护装置','个',5433.200000),(12,15,13,5,NULL,'2023-02-25 12:37:18','2023-02-25 13:34:21','0','DTMT001','电梯门套','W-2000mm','门系统配件','个',5435.100000),(13,16,6,3,NULL,'2023-02-25 12:37:24','2023-02-25 13:33:34','0','FHMK001','防火门框','W-900mm','门系统配件','个',45.500000),(14,17,11,3,NULL,'2023-02-25 12:37:28','2023-02-25 13:29:38','0','DTMS002','电梯门锁','φ20mm','门系统配件','个',53.400000),(15,18,16,3,NULL,'2023-02-25 12:37:32','2023-02-25 13:32:23','0','DTAN001','电梯按钮','5V','控制系统配件','个',543.500000),(16,19,18,3,NULL,'2023-02-25 12:37:36','2023-02-25 13:28:35','0','JFTFS001','机房通风扇','AC380V','控制系统配件','个',354.200000),(17,20,10,3,NULL,'2023-02-25 12:37:40','2023-02-25 13:33:34','0','DTJDD001','电梯井道灯','AC220V','电气系统配件','个',549.700000),(18,21,4,3,NULL,'2023-02-25 12:37:44','2023-02-25 13:37:23','0','DTDKXSQ001','电梯底坑吸水器','L-500mm','电梯保护装置','个',768.900000),(19,22,7,4,NULL,'2023-02-25 12:37:48','2023-02-25 13:36:20','0','YMP002','云母片','2mm','电梯保护装置','片',45.800000),(20,23,4,5,NULL,'2023-02-25 12:38:00','2023-02-25 13:28:35','0','JXFS002','轿厢扶手','L-900mm','轿厢配件','个',486.640000),(21,24,10,4,NULL,'2023-02-25 12:38:04','2023-02-25 13:32:23','0','JFWDKZQ002','机房温度控制器','AC110V','控制系统配件','个',68.800000),(22,25,12,3,NULL,'2023-02-25 12:38:07','2023-02-25 13:29:38','0','DTL002','电梯轮','φ120mm','电梯传动部件','个',68.540000),(23,26,4,5,NULL,'2023-02-25 12:38:11','2023-02-25 13:34:21','0','JXDB002','轿厢顶板','H-250mm','轿厢配件','片',97.500000),(24,27,11,5,NULL,'2023-02-25 12:38:14','2023-02-25 13:32:23','0','JSJCL002','减速机齿轮','M2','电梯传动部件','个',679.500000),(25,28,6,5,NULL,'2023-02-25 12:38:18','2023-02-25 13:36:20','0','DTMS003','电梯门扇','W-900mm','门系统配件','个',687.680000),(26,29,7,3,NULL,'2023-02-25 12:38:21','2023-02-25 13:28:35','0','XSQDH002','限速器弹簧','L-60mm','电梯保护装置','个',369.500000),(27,30,6,3,NULL,'2023-02-25 12:38:25','2023-02-25 13:29:38','0','DTMT002','电梯门套','W-2200mm','门系统配件','个',678.500000),(28,31,10,4,NULL,'2023-02-25 12:38:29','2023-02-25 13:32:23','0','FHMK002','防火门框','W-1000mm','门系统配件','个',189.500000);
/*!40000 ALTER TABLE `em_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_location`
--

DROP TABLE IF EXISTS `em_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_location` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '场所ID',
  `location_name` varchar(90) NOT NULL COMMENT '场所名称',
  `contact_person` varchar(90) NOT NULL COMMENT '联系人',
  `contact_number` varchar(90) NOT NULL COMMENT '联系电话',
  `telephone` varchar(90) DEFAULT NULL COMMENT '手机',
  `fax` varchar(90) DEFAULT NULL COMMENT '传真',
  `address` varchar(90) NOT NULL COMMENT '场所地址',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='场所';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_location`
--

LOCK TABLES `em_location` WRITE;
/*!40000 ALTER TABLE `em_location` DISABLE KEYS */;
INSERT INTO `em_location` VALUES (3,'北京科技馆','李思琪','010-12345678','13811111111','010-12345679','北京市海淀区中关村南大街5号',NULL,'2023-02-25 00:38:42','2023-02-25 12:27:30','0'),(4,'上海电视台','刘春雨','021-87654321','13922222222','021-87654322','上海市徐汇区虹漕路253号',NULL,'2023-02-25 00:38:55','2023-02-25 12:27:49','0'),(5,'广州国际金融中心','张子轩','020-55555555','13633333333','020-55555556','广州市天河区珠江新城华穗路5号',NULL,'2023-02-25 00:39:02','2023-02-25 12:28:00','0'),(6,'深圳大剧院','王梓涵','0755-88888888','13744444444','0755-88888889','深圳市福田区红岭北路2013号',NULL,'2023-02-25 00:39:07','2023-02-25 12:28:06','0'),(7,'成都市第一人民医院','陈俊博','028-77777777','13666666666','028-77777778','四川省成都市青羊区人民北路1号',NULL,'2023-02-25 00:39:13','2023-02-25 12:28:28','0'),(8,'武汉长江大桥','林嘉欣','027-66666666','13555555555','027-66666667','湖北省武汉市江汉区长江大桥',NULL,'2023-02-25 00:39:19','2023-02-25 12:29:03','0'),(9,'南京大学','吴雪莲','025-55555555','13944444444','025-55555556','江苏省南京市鼓楼区汉口路22号',NULL,'2023-02-25 00:39:25','2023-02-25 12:29:09','0'),(10,'杭州西湖','郑宏伟','0571-99999999','13777777777','0571-99999990','浙江省杭州市西湖区西湖风景区',NULL,'2023-02-25 00:39:32','2023-02-25 12:29:14','0');
/*!40000 ALTER TABLE `em_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_maintenance`
--

DROP TABLE IF EXISTS `em_maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_maintenance` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '维修报告ID',
  `maintenance_number` varchar(32) NOT NULL COMMENT '维修编号',
  `elevator_number` varchar(32) NOT NULL COMMENT '电梯编号',
  `location_name` varchar(90) NOT NULL COMMENT '电梯地点',
  `contact_person` varchar(90) NOT NULL COMMENT '联系人',
  `contact_number` varchar(90) NOT NULL COMMENT '联系电话',
  `maintenance_price` decimal(24,6) DEFAULT NULL COMMENT '维修工费',
  `accessory_price` decimal(24,6) DEFAULT NULL COMMENT '配件费用',
  `total_price` decimal(24,6) DEFAULT '0.000000' COMMENT '总费用',
  `fault_description` varchar(900) DEFAULT NULL COMMENT '故障描述',
  `maintenance_data` datetime DEFAULT NULL COMMENT '维修日期',
  `maintenance_person` varchar(90) DEFAULT NULL COMMENT '维修人',
  `is_finish` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否完成',
  `elevator_id` int NOT NULL COMMENT '电梯ID',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `address` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址',
  `check_description` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '检查描述',
  `elevator_name` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电梯名称',
  `applicant` varchar(90) NOT NULL COMMENT '申请人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='维修报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_maintenance`
--

LOCK TABLES `em_maintenance` WRITE;
/*!40000 ALTER TABLE `em_maintenance` DISABLE KEYS */;
INSERT INTO `em_maintenance` VALUES (9,'WX20230225001','DS20230225001','北京科技馆','李思琪','010-12345678',NULL,NULL,0.000000,NULL,NULL,NULL,'0',27,NULL,'2023-02-25 14:40:26',NULL,'0','北京市海淀区中关村南大街5号','电梯故障，开门刀与层门锁滚轮碰撞','天空之梯','赵海龙'),(10,'WX20230225002','FSD20230225002','南京大学','吴雪莲','025-55555555',NULL,NULL,0.000000,NULL,NULL,NULL,'0',35,NULL,'2023-02-25 14:42:35',NULL,'0','江苏省南京市鼓楼区汉口路22号','电梯故障，平层感应器与隔磁板位置尺寸发生变化','盛世电梯','赵海龙'),(12,'WX20230225003','SL20230225001','武汉长江大桥','林嘉欣','027-66666666',370.000000,95.550000,465.550000,'控制电路保险丝1RD和2RD或门机电路保险丝11RD过松或可能已熔断，需要拧紧或更换新的','2023-02-27 13:56:25','陈俊杰','1',28,NULL,'2023-02-25 14:58:50','2023-02-25 15:27:46','0','湖北省武汉市江汉区长江大桥','电梯故障，轿厢平层准确度误差过大','龙腾电梯','葛洪文'),(13,'WX20230225004','FSD20230225001','深圳大剧院','王梓涵','0755-88888888',NULL,NULL,0.000000,NULL,NULL,NULL,'0',32,NULL,'2023-02-25 15:00:34',NULL,'0','深圳市福田区红岭北路2013号','电梯故障，制动力矩调整不当','金鹰电梯','葛洪文');
/*!40000 ALTER TABLE `em_maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_maintenance_image`
--

DROP TABLE IF EXISTS `em_maintenance_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_maintenance_image` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '维修报告图片ID',
  `maintenance_id` int NOT NULL COMMENT '维修报告ID',
  `image_name` varchar(255) NOT NULL COMMENT '图片名',
  `image_position` varchar(900) DEFAULT NULL COMMENT '图片位置',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='维修报告图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_maintenance_image`
--

LOCK TABLES `em_maintenance_image` WRITE;
/*!40000 ALTER TABLE `em_maintenance_image` DISABLE KEYS */;
INSERT INTO `em_maintenance_image` VALUES (8,12,'C095FECA-83C0-4843-A4D0-88298EEEDC99.jpeg','/maintenance/C095FECA-83C0-4843-A4D0-88298EEEDC99.jpeg','2023-02-25 15:27:47',NULL,'0'),(9,12,'E4847209-5E09-4339-B61B-6016EEE6E6A6.jpeg','/maintenance/E4847209-5E09-4339-B61B-6016EEE6E6A6.jpeg','2023-02-25 15:27:47',NULL,'0');
/*!40000 ALTER TABLE `em_maintenance_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_maintenance_item`
--

DROP TABLE IF EXISTS `em_maintenance_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_maintenance_item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '维修报告详情ID',
  `maintenance_id` int NOT NULL COMMENT '维修报告ID',
  `accessory_name` varchar(90) NOT NULL COMMENT '配件名称',
  `specification` varchar(90) NOT NULL COMMENT '规格型号',
  `type` varchar(90) NOT NULL COMMENT '配件类型',
  `unit` varchar(90) NOT NULL COMMENT '单位',
  `quantity` int NOT NULL COMMENT '数量',
  `purchase_price` decimal(24,6) NOT NULL COMMENT '进货价',
  `total_price` decimal(24,6) NOT NULL COMMENT '金额',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `accessory_id` int NOT NULL COMMENT '配件ID',
  `accessory_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配件编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='维修报告详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_maintenance_item`
--

LOCK TABLES `em_maintenance_item` WRITE;
/*!40000 ALTER TABLE `em_maintenance_item` DISABLE KEYS */;
INSERT INTO `em_maintenance_item` VALUES (5,12,'电梯轮','φ100mm','电梯传动部件','个',1,95.550000,95.550000,NULL,'2023-02-25 15:27:47',NULL,'0',10,'DTL001');
/*!40000 ALTER TABLE `em_maintenance_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_manufacturer`
--

DROP TABLE IF EXISTS `em_manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_manufacturer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '厂家ID',
  `manufacturer_name` varchar(90) NOT NULL COMMENT '厂家名称',
  `contact_person` varchar(90) NOT NULL COMMENT '联系人',
  `contact_number` varchar(90) NOT NULL COMMENT '联系电话',
  `telephone` varchar(90) DEFAULT NULL COMMENT '手机',
  `prefix` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号前缀',
  `fax` varchar(90) DEFAULT NULL COMMENT '传真',
  `address` varchar(90) NOT NULL COMMENT '公司地址',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电梯厂家';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_manufacturer`
--

LOCK TABLES `em_manufacturer` WRITE;
/*!40000 ALTER TABLE `em_manufacturer` DISABLE KEYS */;
INSERT INTO `em_manufacturer` VALUES (3,'奥的斯','伊莱沙·格雷夫斯·奥的斯','400-518-5588','400-818-5588','ADS','0571-81600696','美国康涅狄格州奥伯乌尔塞尔街15号',NULL,'2023-02-24 23:57:23',NULL,'0'),(4,'三菱','岩崎弥太郎','021-52082030','021-62363030','SL','021-52082230','日本东京都港区港南二丁目16番5号',NULL,'2023-02-25 00:01:30',NULL,'0'),(5,'日立','小平浪平','400-830-8333','400-690-2885','RL','010-65908110','日本东京都中央区广尾1丁目-6-6',NULL,'2023-02-25 00:05:05',NULL,'0'),(6,'通力','JOEJIAFENGBAO','400-601-1099','21-2201-2222','TL','21-2201-2111','美国俄亥俄州的费尔法克斯市',NULL,'2023-02-25 00:12:33',NULL,'0'),(7,'蒂森','鲍尔斯·贝哈特','6485-5666','0760-88890728','DS','086-21-34230226','法国thyssenkrupp Materials France S.A.S. 工业路18号 68700 Cernay',NULL,'2023-02-25 00:16:55',NULL,'0'),(8,'富士达','新井智弘','4006-810-718','022-24363923','FSD','022-84452397','北海道札幌市中央区北2条西2丁目3番地',NULL,'2023-02-25 00:21:03',NULL,'0'),(9,'东芝','内田修二','400-856-7888','020-26261282','DZ','400-818-0280','日本东京都港区芝浦1丁目1番1号',NULL,'2023-02-25 00:23:45',NULL,'0'),(10,'迅达','罗伯特·辛德勒','400-889-5369','86-0592-2141410','XD','021-67096677','31-33 Bond St, Bristol BS1 3LQ, United Kingdom',NULL,'2023-02-25 00:27:21',NULL,'0');
/*!40000 ALTER TABLE `em_manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_message`
--

DROP TABLE IF EXISTS `em_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `message` varchar(900) NOT NULL COMMENT '消息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_message`
--

LOCK TABLES `em_message` WRITE;
/*!40000 ALTER TABLE `em_message` DISABLE KEYS */;
INSERT INTO `em_message` VALUES (3,'添加新电梯厂家  奥的斯','2023-02-24 23:57:23',NULL,'0'),(4,'添加新电梯厂家  三菱','2023-02-25 00:01:30',NULL,'0'),(5,'添加新电梯厂家  日立','2023-02-25 00:05:05',NULL,'0'),(6,'添加新电梯厂家  通力','2023-02-25 00:12:33',NULL,'0'),(7,'添加新电梯厂家  蒂森','2023-02-25 00:16:55',NULL,'0'),(8,'添加新电梯厂家  富士达','2023-02-25 00:21:03',NULL,'0'),(9,'添加新电梯厂家  东芝','2023-02-25 00:23:45',NULL,'0'),(10,'添加新电梯厂家  迅达','2023-02-25 00:27:21',NULL,'0'),(11,'添加新场所  北京科技馆','2023-02-25 00:38:42',NULL,'0'),(12,'添加新场所  上海电视台','2023-02-25 00:38:55',NULL,'0'),(13,'添加新场所  广州国际金融中心','2023-02-25 00:39:02',NULL,'0'),(14,'添加新场所  深圳大剧院','2023-02-25 00:39:07',NULL,'0'),(15,'添加新场所  成都市第一人民医院','2023-02-25 00:39:14',NULL,'0'),(16,'添加新场所  武汉长江大桥','2023-02-25 00:39:19',NULL,'0'),(17,'添加新场所  南京大学','2023-02-25 00:39:25',NULL,'0'),(18,'添加新场所  杭州西湖','2023-02-25 00:39:32',NULL,'0'),(19,'添加新电梯  DS20230225001:天空之梯','2023-02-25 12:03:02',NULL,'0'),(20,'添加新电梯  SL20230225001:龙腾电梯','2023-02-25 12:04:12',NULL,'0'),(21,'添加新电梯  RL20230225001:翔龙电梯','2023-02-25 12:05:03',NULL,'0'),(22,'添加新电梯  TL20230225001:春天电梯','2023-02-25 12:05:54',NULL,'0'),(23,'添加新电梯  ADS20230225001:海豚电梯','2023-02-25 12:06:22',NULL,'0'),(24,'添加新电梯  FSD20230225001:金鹰电梯','2023-02-25 12:06:57',NULL,'0'),(25,'添加新电梯  DZ20230225001:海滨电梯','2023-02-25 12:09:11',NULL,'0'),(26,'添加新电梯  XD20230225001:天安电梯','2023-02-25 12:09:46',NULL,'0'),(27,'添加新电梯  FSD20230225002:盛世电梯','2023-02-25 12:10:08',NULL,'0'),(28,'添加新电梯  TL20230225002:龙珠电梯','2023-02-25 12:10:32',NULL,'0'),(29,'添加新电梯  FSD20230225003:远航电梯','2023-02-25 12:11:18',NULL,'0'),(30,'添加新电梯  XD20230225002:荣耀电梯','2023-02-25 12:12:21',NULL,'0'),(31,'添加新电梯  SL20230225002:钢铁巨人电梯','2023-02-25 12:13:03',NULL,'0'),(32,'添加新电梯  TL20230225003:梦想电梯','2023-02-25 12:13:53',NULL,'0'),(33,'添加新电梯  ADS20230225002:阳光电梯','2023-02-25 12:14:18',NULL,'0'),(34,'添加新电梯  DS20230225002:星际穿越电梯','2023-02-25 12:14:42',NULL,'0'),(35,'添加新电梯  DZ20230225002:闪电电梯','2023-02-25 12:15:12',NULL,'0'),(36,'添加新电梯  FSD20230225004:大都会电梯','2023-02-25 12:15:38',NULL,'0'),(37,'添加新电梯  RL20230225002:华灯初上电梯','2023-02-25 12:16:08',NULL,'0'),(38,'添加新电梯  XD20230225003:云端电梯','2023-02-25 12:16:26',NULL,'0'),(39,'更新场所内容  北京科技馆','2023-02-25 12:27:30',NULL,'0'),(40,'更新场所内容  上海电视台','2023-02-25 12:27:49',NULL,'0'),(41,'更新场所内容  广州国际金融中心','2023-02-25 12:28:00',NULL,'0'),(42,'更新场所内容  深圳大剧院','2023-02-25 12:28:06',NULL,'0'),(43,'更新场所内容  成都市第一人民医院','2023-02-25 12:28:14',NULL,'0'),(44,'更新场所内容  武汉长江大桥','2023-02-25 12:28:28',NULL,'0'),(45,'更新场所内容  南京大学','2023-02-25 12:29:03',NULL,'0'),(46,'更新场所内容  南京大学','2023-02-25 12:29:09',NULL,'0'),(47,'更新场所内容  杭州西湖','2023-02-25 12:29:14',NULL,'0'),(48,'添加新配件  YMP001:云母片','2023-02-25 12:36:05',NULL,'0'),(49,'添加新配件  JXFS001:轿厢扶手','2023-02-25 12:36:20',NULL,'0'),(50,'添加新配件  JFWDKZQ001:机房温度控制器','2023-02-25 12:36:28',NULL,'0'),(51,'添加新配件  DTL001:电梯轮','2023-02-25 12:36:43',NULL,'0'),(52,'添加新配件  JXDB001:轿厢顶板','2023-02-25 12:37:01',NULL,'0'),(53,'添加新配件  JSJCL001:减速机齿轮','2023-02-25 12:37:06',NULL,'0'),(54,'添加新配件  DTMS001:电梯门扇','2023-02-25 12:37:10',NULL,'0'),(55,'添加新配件  XSQDH001:限速器弹簧','2023-02-25 12:37:14',NULL,'0'),(56,'添加新配件  DTMT001:电梯门套','2023-02-25 12:37:18',NULL,'0'),(57,'添加新配件  FHMK001:防火门框','2023-02-25 12:37:24',NULL,'0'),(58,'添加新配件  DTMS002:电梯门锁','2023-02-25 12:37:28',NULL,'0'),(59,'添加新配件  DTAN001:电梯按钮','2023-02-25 12:37:32',NULL,'0'),(60,'添加新配件  JFTFS001:机房通风扇','2023-02-25 12:37:36',NULL,'0'),(61,'添加新配件  DTJDD001:电梯井道灯','2023-02-25 12:37:40',NULL,'0'),(62,'添加新配件  DTDKXSQ001:电梯底坑吸水器','2023-02-25 12:37:44',NULL,'0'),(63,'添加新配件  YMP002:云母片','2023-02-25 12:37:48',NULL,'0'),(64,'添加新配件  JXFS002:轿厢扶手','2023-02-25 12:38:00',NULL,'0'),(65,'添加新配件  JFWDKZQ002:机房温度控制器','2023-02-25 12:38:04',NULL,'0'),(66,'添加新配件  DTL002:电梯轮','2023-02-25 12:38:07',NULL,'0'),(67,'添加新配件  JXDB002:轿厢顶板','2023-02-25 12:38:11',NULL,'0'),(68,'添加新配件  JSJCL002:减速机齿轮','2023-02-25 12:38:14',NULL,'0'),(69,'添加新配件  DTMS003:电梯门扇','2023-02-25 12:38:18',NULL,'0'),(70,'添加新配件  XSQDH002:限速器弹簧','2023-02-25 12:38:21',NULL,'0'),(71,'添加新配件  DTMT002:电梯门套','2023-02-25 12:38:25',NULL,'0'),(72,'添加新配件  FHMK002:防火门框','2023-02-25 12:38:29',NULL,'0'),(73,'添加新供货商  华建科技有限公司','2023-02-25 12:43:34',NULL,'0'),(74,'添加新供货商  上海联创集团有限公司','2023-02-25 12:43:48',NULL,'0'),(75,'添加新供货商  深圳市鸿程科技有限公司','2023-02-25 12:43:53',NULL,'0'),(76,'添加新供货商  广东科技有限公司','2023-02-25 12:43:57',NULL,'0'),(77,'添加新供货商  湖北瑞达电子有限公司','2023-02-25 12:44:03',NULL,'0'),(78,'添加新供货商  四川科技有限公司','2023-02-25 12:44:07',NULL,'0'),(79,'添加新供货商  江苏万通科技有限公司','2023-02-25 12:44:12',NULL,'0'),(80,'添加新供货商  浙江联达科技有限公司','2023-02-25 12:44:18',NULL,'0'),(81,'添加新入库消息  RK20230225001--湖北瑞达电子有限公司','2023-02-25 13:28:35',NULL,'0'),(82,'添加新入库消息  RK20230225002--江苏万通科技有限公司','2023-02-25 13:29:38',NULL,'0'),(83,'添加新入库消息  RK20230225003--浙江联达科技有限公司','2023-02-25 13:32:23',NULL,'0'),(84,'添加新入库消息  RK20230225004--四川科技有限公司','2023-02-25 13:33:34',NULL,'0'),(85,'添加新入库消息  RK20230225005--上海联创集团有限公司','2023-02-25 13:34:21',NULL,'0'),(86,'添加新入库消息  RK20230225006--广东科技有限公司','2023-02-25 13:35:18',NULL,'0'),(87,'添加新入库消息  RK20230225007--深圳市鸿程科技有限公司','2023-02-25 13:36:20',NULL,'0'),(88,'添加新入库消息  RK20230225008--浙江联达科技有限公司','2023-02-25 13:37:23',NULL,'0'),(89,'添加新入库消息  RK20230225009--华建科技有限公司','2023-02-25 13:38:17',NULL,'0'),(90,'添加新入库消息  RK20230225010--深圳市鸿程科技有限公司','2023-02-25 13:38:57',NULL,'0'),(91,'添加新入库消息  RK20230225011--广东科技有限公司','2023-02-25 13:42:02',NULL,'0'),(92,'添加新检查计划  PL20230225001--TL20230225001:春天电梯','2023-02-25 13:55:54',NULL,'0'),(93,'添加新检查报告  JC20230225001--TL20230225001:春天电梯','2023-02-25 13:56:25',NULL,'0'),(94,'添加新检查计划  PL20230225002--DS20230225001:天空之梯','2023-02-25 13:57:28',NULL,'0'),(95,'添加新检查计划  PL20230225003--RL20230225002:华灯初上电梯','2023-02-25 13:59:14',NULL,'0'),(96,'添加新检查计划  PL20230225004--FSD20230225002:盛世电梯','2023-02-25 14:02:48',NULL,'0'),(97,'添加新检查计划  PL20230225005--TL20230225003:梦想电梯','2023-02-25 14:04:18',NULL,'0'),(98,'添加新检查计划  PL20230225006--FSD20230225003:远航电梯','2023-02-25 14:04:45',NULL,'0'),(99,'添加新检查计划  PL20230225007--FSD20230225001:金鹰电梯','2023-02-25 14:05:16',NULL,'0'),(100,'添加新检查报告  JC20230225002--TL20230225001:春天电梯','2023-02-25 14:05:23',NULL,'0'),(101,'添加新检查报告  JC20230225003--DS20230225001:天空之梯','2023-02-25 14:05:23',NULL,'0'),(102,'添加新检查报告  JC20230225004--FSD20230225002:盛世电梯','2023-02-25 14:05:23',NULL,'0'),(103,'添加新检查报告  JC20230225005--FSD20230225003:远航电梯','2023-02-25 14:05:23',NULL,'0'),(104,'添加新检查报告  JC20230225006--FSD20230225001:金鹰电梯','2023-02-25 14:05:23',NULL,'0'),(105,'曹雪芹  完成检查报告  JC20230225001--TL20230225001:春天电梯','2023-02-25 14:29:36',NULL,'0'),(106,'曹雪芹  完成检查报告  JC20230225006--FSD20230225001:金鹰电梯','2023-02-25 14:32:21',NULL,'0'),(107,'添加新检查报告  WX20230225001--DS20230225001:天空之梯','2023-02-25 14:34:06',NULL,'0'),(108,'陈俊杰  完成检查报告  JC20230225003--DS20230225001:天空之梯','2023-02-25 14:34:06',NULL,'0'),(109,'陈俊杰  完成检查报告  JC20230225003--DS20230225001:天空之梯','2023-02-25 14:40:25',NULL,'0'),(110,'添加新检查报告  WX20230225001--DS20230225001:天空之梯','2023-02-25 14:40:26',NULL,'0'),(111,'添加新检查报告  WX20230225002--DS20230225001:天空之梯','2023-02-25 14:40:26',NULL,'0'),(112,'罗美玲  完成检查报告  JC20230225004--FSD20230225002:盛世电梯','2023-02-25 14:42:35',NULL,'0'),(113,'添加新检查报告  WX20230225002--FSD20230225002:盛世电梯','2023-02-25 14:42:35',NULL,'0'),(114,'添加维修报告  WX20230225003--SL20230225001:龙腾电梯','2023-02-25 14:58:50',NULL,'0'),(115,'添加维修报告  WX20230225004--FSD20230225001:金鹰电梯','2023-02-25 15:00:34',NULL,'0'),(116,'陈俊杰  完成维修报告  WX20230225003--SL20230225001:龙腾电梯','2023-02-25 15:27:47',NULL,'0');
/*!40000 ALTER TABLE `em_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_permission`
--

DROP TABLE IF EXISTS `em_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_permission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permission_name` varchar(90) NOT NULL COMMENT '权限名字',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_permission`
--

LOCK TABLES `em_permission` WRITE;
/*!40000 ALTER TABLE `em_permission` DISABLE KEYS */;
INSERT INTO `em_permission` VALUES (1,'普通用户','2023-02-20 14:13:42',NULL,'0'),(2,'仓库管理员','2023-02-20 14:13:42',NULL,'0'),(3,'维修师傅','2023-02-20 14:13:42',NULL,'0'),(4,'全局管理员','2023-02-20 14:13:42',NULL,'0');
/*!40000 ALTER TABLE `em_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_plan`
--

DROP TABLE IF EXISTS `em_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_plan` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '检查计划ID',
  `plan_number` varchar(32) NOT NULL COMMENT '计划编号',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始日期',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束日期',
  `next_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下一次检查日期',
  `interval_time` int NOT NULL COMMENT '间隔时间',
  `Interval_unit` varchar(90) NOT NULL COMMENT '间隔时间单位',
  `is_finish` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否完成',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `elevator_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电梯编号',
  `elevator_id` int NOT NULL COMMENT '电梯ID',
  `elevator_name` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电梯名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='检查计划';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_plan`
--

LOCK TABLES `em_plan` WRITE;
/*!40000 ALTER TABLE `em_plan` DISABLE KEYS */;
INSERT INTO `em_plan` VALUES (2,'PL20230225001','2019-02-25 12:27:49','2023-05-25 12:27:49','2023-04-25 12:27:49',2,'月','0',NULL,'2023-02-25 13:55:53','2023-02-25 13:56:25','0','TL20230225001',30,'春天电梯'),(3,'PL20230225002','2019-02-25 12:27:49','2025-05-27 12:27:49','2023-02-25 12:27:49',2,'年','0',NULL,'2023-02-25 13:57:28','2023-02-25 14:05:23','0','DS20230225001',27,'天空之梯'),(4,'PL20230225003','2021-01-25 12:27:49','2025-03-27 12:27:49','2024-01-25 12:27:49',3,'周','0',NULL,'2023-02-25 13:59:14',NULL,'0','RL20230225002',45,'华灯初上电梯'),(5,'PL20230225004','2023-01-25 12:27:49','2023-03-27 12:27:49','2023-02-14 12:27:49',10,'天','0',NULL,'2023-02-25 14:02:48','2023-02-25 14:05:23','0','FSD20230225002',35,'盛世电梯'),(6,'PL20230225005','2023-01-25 12:27:49','2028-03-05 12:27:49','2023-06-25 12:27:49',5,'月','0',NULL,'2023-02-25 14:04:18',NULL,'0','TL20230225003',40,'梦想电梯'),(7,'PL20230225006','2018-11-25 12:27:49','2025-12-05 12:27:49','2020-05-25 12:27:49',9,'月','0',NULL,'2023-02-25 14:04:45','2023-02-25 14:05:23','0','FSD20230225003',37,'远航电梯'),(8,'PL20230225007','2011-11-25 12:27:49','2013-12-05 12:27:49','2012-09-25 12:27:49',5,'月','0',NULL,'2023-02-25 14:05:16','2023-02-25 14:05:23','0','FSD20230225001',32,'金鹰电梯');
/*!40000 ALTER TABLE `em_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_storage`
--

DROP TABLE IF EXISTS `em_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_storage` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '配件入库ID',
  `storage_number` varchar(32) NOT NULL COMMENT '入库编号',
  `storage_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库日期',
  `operator_person` varchar(90) NOT NULL COMMENT '经办人',
  `supplier_name` varchar(90) NOT NULL COMMENT '供货商名称',
  `total_price` decimal(24,6) NOT NULL DEFAULT '0.000000' COMMENT '总金额',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `contact_person` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人',
  `contact_number` varchar(90) NOT NULL COMMENT '联系电话',
  `address` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '场所地址',
  `supplier_id` int NOT NULL COMMENT '供货商ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='配件入库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_storage`
--

LOCK TABLES `em_storage` WRITE;
/*!40000 ALTER TABLE `em_storage` DISABLE KEYS */;
INSERT INTO `em_storage` VALUES (22,'RK20230225001','2023-02-25 13:28:34','冯云飞','湖北瑞达电子有限公司',718.250000,NULL,'2023-02-25 13:28:35',NULL,'0','黄家明','13812345678','武汉市洪山区珞瑜路1037号',7),(23,'RK20230225002','2023-02-25 13:29:38','刘娜娜','江苏万通科技有限公司',750.940000,NULL,'2023-02-25 13:29:38',NULL,'0','刘敬波','13987654321','南京市江宁区秣陵街道南京东路1号',9),(24,'RK20230225003','2023-02-25 13:32:22','王宇航','浙江联达科技有限公司',850.940000,NULL,'2023-02-25 13:32:23',NULL,'0','朱志远','13812345678','杭州市余杭区文一西路969号',10),(25,'RK20230225004','2023-02-25 13:33:34','冯云飞','四川科技有限公司',156.420000,NULL,'2023-02-25 13:33:34',NULL,'0','张敏','13698765432','成都市高新区科园南路2号',8),(26,'RK20230225005','2023-02-25 13:34:21','王宇航','上海联创集团有限公司',344.380000,NULL,'2023-02-25 13:34:21',NULL,'0','李明阳','13987654321','上海市浦东新区陆家嘴环路111号',4),(27,'RK20230225006','2023-02-25 13:35:17','刘娜娜','广东科技有限公司',501.820000,NULL,'2023-02-25 13:35:18',NULL,'0','林小华','13987654321','广州市天河区珠江新城华夏路15号',6),(28,'RK20230225007','2023-02-25 13:36:19','冯云飞 ','深圳市鸿程科技有限公司',596.670000,NULL,'2023-02-25 13:36:20',NULL,'0','陈志远','13698765432','深圳市南山区高新南区中区28栋3楼',5),(29,'RK20230225008','2023-02-25 13:37:22','冯云飞 ','浙江联达科技有限公司',391.910000,NULL,'2023-02-25 13:37:22',NULL,'0','朱志远','13812345678','杭州市余杭区文一西路969号',10),(30,'RK20230225009','2023-02-25 13:38:16','刘娜娜 ','华建科技有限公司',303.470000,NULL,'2023-02-25 13:38:17',NULL,'0','王小明','13812345678','北京市朝阳区建国路18号',3),(31,'RK20230225010','2023-02-25 13:38:57','王宇航 ','深圳市鸿程科技有限公司',380.400000,NULL,'2023-02-25 13:38:57',NULL,'0','陈志远','13698765432','深圳市南山区高新南区中区28栋3楼',5),(32,'RK20230225011','2023-02-25 13:42:01','王宇航 ','广东科技有限公司',303.470000,NULL,'2023-02-25 13:42:02',NULL,'0','林小华','13987654321','广州市天河区珠江新城华夏路15号',6);
/*!40000 ALTER TABLE `em_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_storage_item`
--

DROP TABLE IF EXISTS `em_storage_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_storage_item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '入库详情ID',
  `storage_id` int NOT NULL COMMENT '配件入库ID',
  `accessory_name` varchar(90) NOT NULL COMMENT '配件名称',
  `specification` varchar(90) NOT NULL COMMENT '规格型号',
  `type` varchar(90) NOT NULL COMMENT '配件类型',
  `unit` varchar(90) NOT NULL COMMENT '单位',
  `quantity` int NOT NULL COMMENT '数量',
  `purchase_price` decimal(24,6) NOT NULL COMMENT '进货价',
  `total_price` decimal(24,6) NOT NULL COMMENT '金额',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `accessory_id` int NOT NULL COMMENT '配件ID',
  `accessory_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配件编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_storage_item`
--

LOCK TABLES `em_storage_item` WRITE;
/*!40000 ALTER TABLE `em_storage_item` DISABLE KEYS */;
INSERT INTO `em_storage_item` VALUES (4,22,'减速机齿轮','M1.5','电梯传动部件','个',3,8.990000,26.970000,NULL,'2023-02-25 13:28:35',NULL,'0',12,'JSJCL001'),(5,22,'机房通风扇','AC380V','控制系统配件','个',8,55.500000,444.000000,NULL,'2023-02-25 13:28:35',NULL,'0',19,'JFTFS001'),(6,22,'限速器弹簧','L-60mm','电梯保护装置','个',2,12.340000,24.680000,NULL,'2023-02-25 13:28:35',NULL,'0',29,'XSQDH002'),(7,22,'机房温度控制器','AC220V','控制系统配件','个',6,25.000000,150.000000,NULL,'2023-02-25 13:28:35',NULL,'0',9,'JFWDKZQ001'),(8,22,'轿厢扶手','L-900mm','轿厢配件','个',1,72.600000,72.600000,NULL,'2023-02-25 13:28:35',NULL,'0',23,'JXFS002'),(9,23,'电梯门锁','φ20mm','门系统配件','个',4,38.990000,155.960000,NULL,'2023-02-25 13:29:38',NULL,'0',17,'DTMS002'),(10,23,'电梯轮','φ120mm','电梯传动部件','个',7,19.500000,136.500000,NULL,'2023-02-25 13:29:38',NULL,'0',25,'DTL002'),(11,23,'轿厢扶手','L-800mm','轿厢配件','个',9,7.200000,64.800000,NULL,'2023-02-25 13:29:38',NULL,'0',8,'JXFS001'),(12,23,'电梯门套','W-2200mm','门系统配件','个',3,14.990000,44.970000,NULL,'2023-02-25 13:29:38',NULL,'0',30,'DTMT002'),(13,23,'轿厢顶板','H-200mm','轿厢配件','片',1,99.990000,99.990000,NULL,'2023-02-25 13:29:38',NULL,'0',11,'JXDB001'),(14,24,'减速机齿轮','M2','电梯传动部件','个',2,31.500000,63.000000,NULL,'2023-02-25 13:32:23',NULL,'0',27,'JSJCL002'),(15,24,'电梯按钮','5V','控制系统配件','个',6,49.990000,299.940000,NULL,'2023-02-25 13:32:23',NULL,'0',18,'DTAN001'),(16,24,'机房温度控制器','AC110V','控制系统配件','个',5,10.000000,50.000000,NULL,'2023-02-25 13:32:23',NULL,'0',24,'JFWDKZQ002'),(17,24,'限速器弹簧','L-50mm','电梯保护装置','个',3,17.990000,53.970000,NULL,'2023-02-25 13:32:23',NULL,'0',14,'XSQDH001'),(18,24,'防火门框','W-1000mm','门系统配件','个',1,29.990000,29.990000,NULL,'2023-02-25 13:32:23',NULL,'0',31,'FHMK002'),(19,25,'电梯井道灯','AC220V','电气系统配件','个',5,12.490000,62.450000,NULL,'2023-02-25 13:33:34',NULL,'0',20,'DTJDD001'),(20,25,'电梯轮','φ100mm','电梯传动部件','个',1,45.990000,45.990000,NULL,'2023-02-25 13:33:34',NULL,'0',10,'DTL001'),(21,25,'防火门框','W-900mm','门系统配件','个',2,23.990000,47.980000,NULL,'2023-02-25 13:33:34',NULL,'0',16,'FHMK001'),(22,26,'轿厢顶板','H-250mm','轿厢配件','片',3,35.000000,105.000000,NULL,'2023-02-25 13:34:21',NULL,'0',26,'JXDB002'),(23,26,'电梯门套','W-2000mm','门系统配件','个',4,21.990000,87.960000,NULL,'2023-02-25 13:34:21',NULL,'0',15,'DTMT001'),(24,26,'机房通风扇','AC380V','控制系统配件','个',6,8.990000,53.940000,NULL,'2023-02-25 13:34:21',NULL,'0',19,'JFTFS001'),(25,26,'轿厢扶手','L-800mm','轿厢配件','个',2,19.990000,39.980000,NULL,'2023-02-25 13:34:21',NULL,'0',8,'JXFS001'),(26,26,'轿厢顶板','H-200mm','轿厢配件','片',1,15.000000,15.000000,NULL,'2023-02-25 13:34:21',NULL,'0',11,'JXDB001'),(27,26,'限速器弹簧','L-60mm','电梯保护装置','个',3,27.500000,82.500000,NULL,'2023-02-25 13:34:21',NULL,'0',29,'XSQDH002'),(28,27,'轿厢扶手','L-900mm','轿厢配件','个',3,17.990000,53.970000,NULL,'2023-02-25 13:35:18',NULL,'0',23,'JXFS002'),(29,27,'防火门框','W-1000mm','门系统配件','个',2,29.990000,59.980000,NULL,'2023-02-25 13:35:18',NULL,'0',31,'FHMK002'),(30,27,'轿厢扶手','L-800mm','轿厢配件','个',1,19.990000,19.990000,NULL,'2023-02-25 13:35:18',NULL,'0',8,'JXFS001'),(31,27,'电梯轮','φ100mm','电梯传动部件','个',3,45.990000,137.970000,NULL,'2023-02-25 13:35:18',NULL,'0',10,'DTL001'),(32,27,'电梯门锁','φ20mm','门系统配件','个',4,13.500000,54.000000,NULL,'2023-02-25 13:35:18',NULL,'0',17,'DTMS002'),(33,27,'减速机齿轮','M1.5','电梯传动部件','个',6,7.990000,47.940000,NULL,'2023-02-25 13:35:18',NULL,'0',12,'JSJCL001'),(34,27,'云母片','1mm','电梯保护装置','片',1,24.990000,24.990000,NULL,'2023-02-25 13:35:18',NULL,'0',7,'YMP001'),(35,27,'机房温度控制器','AC110V','控制系统配件','个',2,28.000000,56.000000,NULL,'2023-02-25 13:35:18',NULL,'0',24,'JFWDKZQ002'),(36,27,'减速机齿轮','M2','电梯传动部件','个',3,16.990000,50.970000,NULL,'2023-02-25 13:35:18',NULL,'0',27,'JSJCL002'),(37,28,'云母片','2mm','电梯保护装置','片',5,14.990000,74.950000,NULL,'2023-02-25 13:36:20',NULL,'0',22,'YMP002'),(38,28,'防火门框','W-900mm','门系统配件','个',4,19.990000,79.960000,NULL,'2023-02-25 13:36:20',NULL,'0',16,'FHMK001'),(39,28,'减速机齿轮','M1.5','电梯传动部件','个',7,7.990000,55.930000,NULL,'2023-02-25 13:36:20',NULL,'0',12,'JSJCL001'),(40,28,'防火门框','W-1000mm','门系统配件','个',3,29.990000,89.970000,NULL,'2023-02-25 13:36:20',NULL,'0',31,'FHMK002'),(41,28,'电梯门扇','W-900mm','门系统配件','个',6,11.000000,66.000000,NULL,'2023-02-25 13:36:20',NULL,'0',28,'DTMS003'),(42,28,'机房温度控制器','AC220V','控制系统配件','个',8,12.990000,103.920000,NULL,'2023-02-25 13:36:20',NULL,'0',9,'JFWDKZQ001'),(43,28,'电梯井道灯','AC220V','电气系统配件','个',5,25.000000,125.000000,NULL,'2023-02-25 13:36:20',NULL,'0',20,'DTJDD001'),(44,28,'电梯门扇','W-800mm','门系统配件','个',6,16.990000,101.940000,NULL,'2023-02-25 13:36:20',NULL,'0',13,'DTMS001'),(45,29,'电梯门锁','φ20mm','门系统配件','个',3,21.000000,63.000000,NULL,'2023-02-25 13:37:23',NULL,'0',17,'DTMS002'),(46,29,'限速器弹簧','L-60mm','电梯保护装置','个',2,13.500000,27.000000,NULL,'2023-02-25 13:37:23',NULL,'0',29,'XSQDH002'),(47,29,'电梯门套','W-2000mm','门系统配件','个',5,10.990000,54.950000,NULL,'2023-02-25 13:37:23',NULL,'0',15,'DTMT001'),(48,29,'电梯底坑吸水器','L-500mm','电梯保护装置','个',4,24.990000,99.960000,NULL,'2023-02-25 13:37:23',NULL,'0',21,'DTDKXSQ001'),(49,29,'电梯门套','W-2200mm','门系统配件','个',3,19.000000,57.000000,NULL,'2023-02-25 13:37:23',NULL,'0',30,'DTMT002'),(50,29,'电梯轮','φ100mm','电梯传动部件','个',6,15.000000,90.000000,NULL,'2023-02-25 13:37:23',NULL,'0',10,'DTL001'),(51,30,'限速器弹簧','L-50mm','电梯保护装置','个',2,18.500000,37.000000,NULL,'2023-02-25 13:38:17',NULL,'0',14,'XSQDH001'),(52,30,'机房通风扇','AC380V','控制系统配件','个',4,26.000000,104.000000,NULL,'2023-02-25 13:38:17',NULL,'0',19,'JFTFS001'),(53,30,'机房温度控制器','AC110V','控制系统配件','个',3,22.990000,68.970000,NULL,'2023-02-25 13:38:17',NULL,'0',24,'JFWDKZQ002'),(54,30,'轿厢顶板','H-250mm','轿厢配件','片',1,16.000000,16.000000,NULL,'2023-02-25 13:38:17',NULL,'0',26,'JXDB002'),(55,30,'电梯轮','φ120mm','电梯传动部件','个',5,15.500000,77.500000,NULL,'2023-02-25 13:38:17',NULL,'0',25,'DTL002'),(56,31,'机房温度控制器','AC220V','控制系统配件','个',3,14.990000,44.970000,NULL,'2023-02-25 13:38:57',NULL,'0',9,'JFWDKZQ001'),(57,31,'电梯轮','φ100mm','电梯传动部件','个',4,19.500000,78.000000,NULL,'2023-02-25 13:38:57',NULL,'0',10,'DTL001'),(58,31,'电梯门套','W-2000mm','门系统配件','个',2,22.000000,44.000000,NULL,'2023-02-25 13:38:57',NULL,'0',15,'DTMT001'),(59,31,'电梯按钮','5V','控制系统配件','个',5,16.990000,84.950000,NULL,'2023-02-25 13:38:57',NULL,'0',18,'DTAN001'),(60,31,'云母片','2mm','电梯保护装置','片',1,28.000000,28.000000,NULL,'2023-02-25 13:38:57',NULL,'0',22,'YMP002'),(61,31,'减速机齿轮','M2','电梯传动部件','个',3,17.500000,52.500000,NULL,'2023-02-25 13:38:57',NULL,'0',27,'JSJCL002'),(62,31,'防火门框','W-1000mm','门系统配件','个',2,23.990000,47.980000,NULL,'2023-02-25 13:38:57',NULL,'0',31,'FHMK002'),(63,32,'机房温度控制器','AC220V','控制系统配件','个',3,14.990000,44.970000,NULL,'2023-02-25 13:42:02',NULL,'0',9,'JFWDKZQ001'),(64,32,'电梯轮','φ100mm','电梯传动部件','个',4,19.500000,78.000000,NULL,'2023-02-25 13:42:02',NULL,'0',10,'DTL001'),(65,32,'电梯门套','W-2000mm','门系统配件','个',2,22.000000,44.000000,NULL,'2023-02-25 13:42:02',NULL,'0',15,'DTMT001'),(66,32,'电梯按钮','5V','控制系统配件','个',5,16.990000,84.950000,NULL,'2023-02-25 13:42:02',NULL,'0',18,'DTAN001'),(67,32,'云母片','2mm','电梯保护装置','片',1,28.000000,28.000000,NULL,'2023-02-25 13:42:02',NULL,'0',22,'YMP002'),(68,32,'减速机齿轮','M2','电梯传动部件','个',3,17.500000,52.500000,NULL,'2023-02-25 13:42:02',NULL,'0',27,'JSJCL002'),(69,32,'防火门框','W-1000mm','门系统配件','个',2,23.990000,47.980000,NULL,'2023-02-25 13:42:02',NULL,'0',31,'FHMK002');
/*!40000 ALTER TABLE `em_storage_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_supplier`
--

DROP TABLE IF EXISTS `em_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_supplier` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '供货商ID',
  `supplier_name` varchar(90) NOT NULL COMMENT '供货商名称',
  `contact_person` varchar(90) NOT NULL COMMENT '联系人',
  `contact_number` varchar(90) NOT NULL COMMENT '联系电话',
  `telephone` varchar(90) DEFAULT NULL COMMENT '手机',
  `fax` varchar(90) DEFAULT NULL COMMENT '传真',
  `address` varchar(90) NOT NULL COMMENT '场所地址',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='供货商';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_supplier`
--

LOCK TABLES `em_supplier` WRITE;
/*!40000 ALTER TABLE `em_supplier` DISABLE KEYS */;
INSERT INTO `em_supplier` VALUES (3,'华建科技有限公司','王小明','13812345678','010-87654321','010-12345678','北京市朝阳区建国路18号',NULL,'2023-02-25 12:43:34',NULL,'0'),(4,'上海联创集团有限公司','李明阳','13987654321','021-12345678','021-87654321','上海市浦东新区陆家嘴环路111号',NULL,'2023-02-25 12:43:48',NULL,'0'),(5,'深圳市鸿程科技有限公司','陈志远','13698765432','0755-87654321','0755-12345678','深圳市南山区高新南区中区28栋3楼',NULL,'2023-02-25 12:43:53',NULL,'0'),(6,'广东科技有限公司','林小华','13987654321','020-87654321','020-12345678','广州市天河区珠江新城华夏路15号',NULL,'2023-02-25 12:43:57',NULL,'0'),(7,'湖北瑞达电子有限公司','黄家明','13812345678','027-12345678','027-87654321','武汉市洪山区珞瑜路1037号',NULL,'2023-02-25 12:44:03',NULL,'0'),(8,'四川科技有限公司','张敏','13698765432','028-12345678','028-87654321','成都市高新区科园南路2号',NULL,'2023-02-25 12:44:07',NULL,'0'),(9,'江苏万通科技有限公司','刘敬波','13987654321','025-87654321','025-12345678','南京市江宁区秣陵街道南京东路1号',NULL,'2023-02-25 12:44:12',NULL,'0'),(10,'浙江联达科技有限公司','朱志远','13812345678','0571-12345678','0571-87654321','杭州市余杭区文一西路969号',NULL,'2023-02-25 12:44:18',NULL,'0');
/*!40000 ALTER TABLE `em_supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_user`
--

DROP TABLE IF EXISTS `em_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_number` varchar(32) NOT NULL COMMENT '用户编号',
  `user_name` varchar(90) NOT NULL COMMENT '用户名称',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `age` int DEFAULT NULL COMMENT '年龄',
  `remarks` varchar(900) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `is_enabled` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `permission_id` int NOT NULL COMMENT '权限ID',
  `permission_name` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_user`
--

LOCK TABLES `em_user` WRITE;
/*!40000 ALTER TABLE `em_user` DISABLE KEYS */;
INSERT INTO `em_user` VALUES (7,'US20230225001','葛洪文','123456789','男',28,NULL,'2023-02-25 12:24:59',NULL,'0','1',1,'普通用户'),(8,'US20230225002','罗美玲','123456789','女',32,NULL,'2023-02-25 12:25:14',NULL,'0','1',3,'维修师傅'),(9,'US20230225003','杨珊珊','123456789','女',25,NULL,'2023-02-25 12:25:19',NULL,'0','1',4,'全局管理员'),(10,'US20230225004','冯云飞','123456789','男',29,NULL,'2023-02-25 12:25:24',NULL,'0','1',2,'仓库管理员'),(11,'US20230225005','柳美华','123456789','女',27,NULL,'2023-02-25 12:25:28',NULL,'0','1',1,'普通用户'),(12,'US20230225006','陈俊杰','123456789','男',35,NULL,'2023-02-25 12:25:33',NULL,'0','1',3,'维修师傅'),(13,'US20230225007','张燕子','123456789','女',26,NULL,'2023-02-25 12:25:37',NULL,'0','1',4,'全局管理员'),(14,'US20230225008','王宇航','123456789','男',30,NULL,'2023-02-25 12:25:41',NULL,'0','1',2,'仓库管理员'),(15,'US20230225009','刘莉莉','123456789','女',28,NULL,'2023-02-25 12:25:45',NULL,'0','1',1,'普通用户'),(16,'US20230225010','孙宇豪','123456789','男',31,NULL,'2023-02-25 12:25:53',NULL,'0','1',4,'全局管理员'),(17,'US20230225011','赵海龙','123456789','男',27,NULL,'2023-02-25 12:25:57',NULL,'0','1',3,'维修师傅'),(18,'US20230225012','刘娜娜','123456789','女',26,NULL,'2023-02-25 12:26:01',NULL,'0','1',2,'仓库管理员'),(19,'US20230225013','周丹丹','123456789','女',29,NULL,'2023-02-25 12:26:06',NULL,'0','1',1,'普通用户'),(20,'US20230225014','李健健','123456789','男',34,NULL,'2023-02-25 12:26:10',NULL,'0','1',4,'全局管理员'),(21,'US20230225015','曹雪芹','123456789','男',33,NULL,'2023-02-25 12:26:13',NULL,'0','1',3,'维修师傅');
/*!40000 ALTER TABLE `em_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `em_user_permission`
--

DROP TABLE IF EXISTS `em_user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `em_user_permission` (
  `user_permission_id` int NOT NULL AUTO_INCREMENT COMMENT '用户权限ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `permission_id` int NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`user_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `em_user_permission`
--

LOCK TABLES `em_user_permission` WRITE;
/*!40000 ALTER TABLE `em_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `em_user_permission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-25 15:47:19
