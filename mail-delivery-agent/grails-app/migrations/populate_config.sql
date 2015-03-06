
drop table  if exists fogpanel_mail_manager.config;

CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into fogpanel_mail_manager.config values ('1', '1', 'Save type configuration setting holds either save all mails or save un delivered mails', 'SAVE_CONFIG', '1002');
-- insert into fogpanel_mail_manager.config values ('2', '0', 'The Url of this panel', 'MAIL_APPLICATIONURL', 'http://localhost:8080/fogpanel-mail-manager/');
insert into fogpanel_mail_manager.config values ('3', '0', 'Host of the email server', 'MAIL_HOST', 'smtp.gmail.com');
insert into fogpanel_mail_manager.config values ('4', '0', 'username of the email server', 'MAIL_USERNAME', 'balaji.assistanz.az@gmail.com');
insert into fogpanel_mail_manager.config values ('5', '0', 'Password of the email server', 'MAIL_PASSWORD', 'assistanz!@#');
insert into fogpanel_mail_manager.config values ('6', '0', 'port no of the email server', 'MAIL_PORT', '465');
insert into fogpanel_mail_manager.config values ('7', '0', 'Use Secure Connection', 'MAIL_SECURECONNECTION', 'SSL');
insert into fogpanel_mail_manager.config values ('8', '0', 'The from address of the email server', 'MAIL_FROM', 'demoFrom@gmail.com');
insert into fogpanel_mail_manager.config values ('9', '0', 'The sender name of the email', 'MAIL_SENDERNAME', 'BALAJI MANI');
