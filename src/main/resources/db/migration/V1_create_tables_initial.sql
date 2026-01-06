
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `adress_complement` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `logradouro` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `number` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `post_code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpo044ng5x4gynb291cv24vtea` (`city_id`),
  CONSTRAINT `FKpo044ng5x4gynb291cv24vtea` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `blood_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eg58fwasig7wub27c8tqtigf9` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uf_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7qs6ky1g6hp8hho02fd2qw0mi` (`uf_id`),
  CONSTRAINT `FK7qs6ky1g6hp8hho02fd2qw0mi` FOREIGN KEY (`uf_id`) REFERENCES `uf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_llidyp77h6xkeokpbmoy710d4` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `feed` (
  `id` int NOT NULL AUTO_INCREMENT,
  `feed_visibility` int NOT NULL,
  `post_date` date DEFAULT NULL,
  `reunion_date` date DEFAULT NULL,
  `text` text COLLATE utf8mb4_unicode_ci,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `head_quarter_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68qh2cttokq8bort1eh5tj2x0` (`head_quarter_id`),
  CONSTRAINT `FK68qh2cttokq8bort1eh5tj2x0` FOREIGN KEY (`head_quarter_id`) REFERENCES `head_quarter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `head_quarter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgs6co7uhk9rjb4w1yq8taa9o4` (`address_id`),
  CONSTRAINT `FKgs6co7uhk9rjb4w1yq8taa9o4` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `infraction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `infraction_date` date DEFAULT NULL,
  `infraction_type` int NOT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr1ila4006tt7fgnfbb2rm8hsl` (`member_id`),
  CONSTRAINT `FKr1ila4006tt7fgnfbb2rm8hsl` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admission_date` date DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `cel_phone` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cnh` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cpf` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `familiar_phone1` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `familiar_phone2` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `first_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nick_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rg` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `shutdow_date` date DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `blood_type_id` int DEFAULT NULL,
  `head_quarter_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_471a4j308osd46kos6rrgmcyt` (`cpf`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`),
  KEY `FKe949jkrgjkwq2hxgj3ow03bpm` (`address_id`),
  KEY `FK6e86pg78e4nkumcy0haem8gv5` (`blood_type_id`),
  KEY `FKfjme7m0idc6r4alolrb6ff1j2` (`head_quarter_id`),
  CONSTRAINT `FK6e86pg78e4nkumcy0haem8gv5` FOREIGN KEY (`blood_type_id`) REFERENCES `blood_type` (`id`),
  CONSTRAINT `FKe949jkrgjkwq2hxgj3ow03bpm` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKfjme7m0idc6r4alolrb6ff1j2` FOREIGN KEY (`head_quarter_id`) REFERENCES `head_quarter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `member_patch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admission_date` date NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  `patch_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK89yo7e75ug37r5stotj3goear` (`member_id`),
  KEY `FKacfgq04n83cavfnea0hgm4iuo` (`patch_id`),
  CONSTRAINT `FK89yo7e75ug37r5stotj3goear` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKacfgq04n83cavfnea0hgm4iuo` FOREIGN KEY (`patch_id`) REFERENCES `patch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `patch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mwpvil2gu3xg55fi23hl2qpa9` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `profiles` (
  `member_id` int NOT NULL,
  `profile` int DEFAULT NULL,
  KEY `FK3je4xlea0lern2dsaq2ofgyfd` (`member_id`),
  CONSTRAINT `FK3je4xlea0lern2dsaq2ofgyfd` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `role_duty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `role_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl5torsmx8gi7kl4ggt6l76btf` (`member_id`),
  CONSTRAINT `FKl5torsmx8gi7kl4ggt6l76btf` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `travel_control` (
  `id` int NOT NULL AUTO_INCREMENT,
  `arrival_location` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `departure_location` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `km` double NOT NULL,
  `km_control` int NOT NULL,
  `travel_date` date DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8urobcpjul6sp9wywn937p4du` (`member_id`),
  CONSTRAINT `FK8urobcpjul6sp9wywn937p4du` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `uf` (
  `id` int NOT NULL AUTO_INCREMENT,
  `acronym` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_88o6u6b303e42os0eoiiu54h8` (`acronym`),
  KEY `FKatnacokem7hya1wkfophesnko` (`country_id`),
  CONSTRAINT `FKatnacokem7hya1wkfophesnko` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;