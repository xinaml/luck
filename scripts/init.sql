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


/**
  oauth2
  https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 */
create table oauth_client_details (
                                      client_id VARCHAR(255) PRIMARY KEY,
                                      resource_ids VARCHAR(255),
                                      client_secret VARCHAR(255),
                                      scope VARCHAR(255),
                                      authorized_grant_types VARCHAR(255),
                                      web_server_redirect_uri VARCHAR(255),
                                      authorities VARCHAR(255),
                                      access_token_validity varchar(255),
                                      refresh_token_validity varchar(255),
                                      additional_information varchar(255),
                                      autoapprove VARCHAR(255)
);

create table oauth_client_token (
                                    token_id VARCHAR(255),
                                    token varchar(255),
                                    authentication_id VARCHAR(255) PRIMARY KEY,
                                    user_name VARCHAR(255),
                                    client_id VARCHAR(255)
);

create table oauth_access_token (
                                    token_id VARCHAR(255),
                                    token varchar(255),
                                    authentication_id VARCHAR(255) PRIMARY KEY,
                                    user_name VARCHAR(255),
                                    client_id VARCHAR(255),
                                    authentication varchar(255),
                                    refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
                                     token_id VARCHAR(255),
                                     token varchar(255),
                                     authentication varchar(255)
);

create table oauth_code (
                            code VARCHAR(255), authentication varchar(255)
);

create table oauth_approvals (
                                 userId VARCHAR(255),
                                 clientId VARCHAR(255),
                                 scope VARCHAR(255),
                                 status VARCHAR(10),
                                 expiresAt TIMESTAMP,
                                 lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
                               appId VARCHAR(255) PRIMARY KEY,
                               resourceIds VARCHAR(255),
                               appSecret VARCHAR(255),
                               scope VARCHAR(255),
                               grantTypes VARCHAR(255),
                               redirectUrl VARCHAR(255),
                               authorities VARCHAR(255),
                               access_token_validity INTEGER,
                               refresh_token_validity INTEGER,
                               additionalInformation VARCHAR(255),
                               autoApproveScopes VARCHAR(255)
);

INSERT INTO luck.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('webApp', null, '$2a$10$UvDO6V7AqxixPT94qW8m6edlG/IA3FqtXnWHpZRPlpiNDF.TfhdGW', 'webApp', 'authorization_code,password,refresh_token,client_credentials', null, null, null, null, null, null);