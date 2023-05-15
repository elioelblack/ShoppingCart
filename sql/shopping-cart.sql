create schema `shopping-cart` collate latin1_spanish_ci;
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK\n',
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'creation date',
  `description` varchar(250) DEFAULT NULL COMMENT 'description of the order',
  `print` datetime DEFAULT NULL COMMENT 'Date when the order was printed',
  `last_user` varchar(25) DEFAULT NULL COMMENT 'Audit control',
  `state` int DEFAULT '0' COMMENT '-1=Eliminado\n0=creado',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Orders ';

DROP TABLE IF EXISTS `order_product_details`;
CREATE TABLE `order_product_details` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `id_order` int DEFAULT NULL,
  `id_product` int NOT NULL,
  `quantity` int NOT NULL,
  `iva` double DEFAULT '0',
  `total` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `last_user` varchar(25) DEFAULT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_details_order_id_fk` (`id_product`),
  KEY `order_product_details_order_product_id_fk` (`id_order`),
  CONSTRAINT `order_product_details_order_product_id_fk` FOREIGN KEY (`id_order`) REFERENCES `order_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(250) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0=inactive,\n1=active',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Users table';

INSERT INTO `user` (`id`, `full_name`, `username`, `password`, `active`) VALUES (1,'Administrador del sistema','admin','admin',1);
