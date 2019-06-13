--
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


INSERT INTO lk_storage (id,create_date, count, name,price) VALUES (1,now() ,100,'手机', 2000);

INSERT INTO lk_user (id,create_date, username, password,account) VALUES (1,now() ,'test1','123456', 10000);
INSERT INTO lk_user (id,create_date, username, password,account) VALUES (2,now() ,'test2','123456', 20000);
