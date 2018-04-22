DROP TABLE IF EXISTS DEV_PROVINCE;
CREATE TABLE DEV_PROVINCE (
	ID varchar(32) NOT NULL PRIMARY KEY COMMENT 'ID',
	CODE varchar(50) NOT NULL COMMENT '编码',
	NAME varchar(50) NOT NULL COMMENT '名称',
	STATUS varchar(1) NOT NULL DEFAULT 'O' COMMENT '状态',
	DEL_FLAG tinyint(1) NULL DEFAULT '0' COMMENT '删除标志',
	CREATE_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
	CREATE_USER varchar(255) NULL COMMENT '创建人',
	MODIFY_DATE timestamp NOT NULL COMMENT '更新日期',
	MODIFY_USER varchar(255) NULL COMMENT '更新人'
) ENGINE = InnoDB COMMENT '省份信息表';