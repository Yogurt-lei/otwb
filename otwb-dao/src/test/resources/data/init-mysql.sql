DROP TABLE IF EXISTS DEV_PROVINCE;
CREATE TABLE DEV_PROVINCE (
	ID VARCHAR(32) NOT NULL PRIMARY KEY COMMENT 'ID',
	CODE VARCHAR(50) NOT NULL COMMENT '编码',
	NAME VARCHAR(50) NOT NULL COMMENT '名称',
	STATUS VARCHAR(1) NOT NULL DEFAULT 'O' COMMENT '状态',
	DEL_FLAG TINYINT(1) NULL DEFAULT '0' COMMENT '删除标志',
	CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
	CREATE_USER VARCHAR(255) NULL COMMENT '创建人',
	MODIFY_DATE TIMESTAMP NOT NULL COMMENT '更新日期',
	MODIFY_USER VARCHAR(255) NULL COMMENT '更新人'
) ENGINE = INNODB COMMENT '省份信息表';


DROP TABLE IF EXISTS DEV_VERSION;
CREATE TABLE DEV_VERSION (
	ID VARCHAR(32) NOT NULL PRIMARY KEY COMMENT 'ID',
	VERSION VARCHAR(50) NOT NULL COMMENT '版本',
	STATUS VARCHAR(1) NOT NULL DEFAULT 'O' COMMENT '状态',
	DEL_FLAG TINYINT(1) NULL DEFAULT '0' COMMENT '删除标志',
	CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
	CREATE_USER VARCHAR(255) NULL COMMENT '创建人',
	MODIFY_DATE TIMESTAMP NOT NULL COMMENT '更新日期',
	MODIFY_USER VARCHAR(255) NULL COMMENT '更新人'
) ENGINE = INNODB COMMENT '省份信息表';


DROP TABLE IF EXISTS DEV_USER;
CREATE TABLE DEV_USER
(
  ID        VARCHAR(32) NOT NULL PRIMARY KEY COMMENT 'ID',
  USER_NAME VARCHAR(50) NOT NULL COMMENT '用户名',
  PASSWORD  VARCHAR(50) NOT NULL COMMENT '密码',
  STATUS VARCHAR(1) NOT NULL DEFAULT 'O' COMMENT '状态',
	DEL_FLAG TINYINT(1) NULL DEFAULT '0' COMMENT '删除标志',
	CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
	CREATE_USER VARCHAR(255) NULL COMMENT '创建人',
	MODIFY_DATE TIMESTAMP NOT NULL COMMENT '更新日期',
	MODIFY_USER VARCHAR(255) NULL COMMENT '更新人'
)
  ENGINE = INNODB;

