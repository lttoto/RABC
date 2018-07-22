CREATE TABLE permission (
  pid int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL DEFAULT '',
  url VARCHAR(255) DEFAULT '',
  PRIMARY KEY(pid)
)ENGINE = InnoDB DEFAULT CHARSET = utf-8;

CREATE TABLE user (
  uid int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL DEFAULT '',
  password VARCHAR(255) DEFAULT '',
  PRIMARY KEY(uid)
)ENGINE = InnoDB DEFAULT CHARSET = utf-8;

CREATE TABLE role (
  rid int(11) NOT NULL AUTO_INCREMENT,
  rname VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY(rid)
)ENGINE = InnoDB DEFAULT CHARSET = utf-8;


CREATE TABLE permission_role (
  rid int(11) NOT NULL,
  pid int(11) NOT NULL,
  KEY 'idx_rid' (rid),
  KEY 'idx_pid' (pid)
)ENGINE = InnoDB DEFAULT CHARSET = utf-8;

CREATE TABLE permission_user (
  uid int(11) NOT NULL,
  pid int(11) NOT NULL,
  KEY 'idx_uid' (uid),
  KEY 'idx_pid' (pid)
)ENGINE = InnoDB DEFAULT CHARSET = utf-8;