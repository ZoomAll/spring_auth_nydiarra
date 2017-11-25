USE auth_jwt;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `users`;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE `users` (
  `id`         VARCHAR(255)
               COLLATE utf8mb4_unicode_ci NOT NULL,
  `password`   VARCHAR(255)
               COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` VARCHAR(255)
               COLLATE utf8mb4_unicode_ci DEFAULT '',
  `last_name`  VARCHAR(255)
               COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_name_uindex` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `roles`;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE `roles` (
  `authority`   VARCHAR(255)
                COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` VARCHAR(255)
                COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`authority`),
  UNIQUE KEY `roles_name_uindex` (`authority`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user` VARCHAR(255)
         COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` VARCHAR(255)
         COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `user_role_users_name_fk` (`user`),
  KEY `user_role_roles_name_fk` (`role`),
  CONSTRAINT `user_role_users_name_fk` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `user_role_roles_name_fk` FOREIGN KEY (`role`) REFERENCES `roles` (`authority`)
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


LOCK TABLES `users` WRITE, `roles` WRITE, `user_role` WRITE;
INSERT INTO `users` VALUES
  ('alex', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', '', ''),
  ('gonzo', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', '', '');
INSERT INTO `roles` VALUES
  ('ADMIN', 'Administrator'),
  ('USER', 'Standard user');
INSERT INTO `user_role` VALUES
  ('alex', 'ADMIN'),
  ('alex', 'USER'),
  ('gonzo', 'USER');
UNLOCK TABLES;