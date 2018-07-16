# ************************************************************
# JPress SQL
# Version 0.1
#
# http://www.JPress.io
# ************************************************************


# Dump of table attachment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_attachment`;

CREATE TABLE `tjp_attachment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `title` text COMMENT '标题',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '上传附件的用户ID',
  `content_id` bigint(20) unsigned DEFAULT NULL COMMENT '附件所属的内容ID',
  `path` varchar(512) DEFAULT NULL COMMENT '路径',
  `mime_type` varchar(128) DEFAULT NULL COMMENT 'mime',
  `suffix` varchar(32) DEFAULT NULL COMMENT '附件的后缀',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `flag` varchar(256) DEFAULT NULL COMMENT '标示',
  `lat` decimal(20,16) DEFAULT NULL COMMENT '经度',
  `lng` decimal(20,16) DEFAULT NULL COMMENT '纬度',
  `order_number` int(11) DEFAULT NULL COMMENT '排序字段',
  `created` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `created` (`created`),
  KEY `suffix` (`suffix`),
  KEY `mime_type` (`mime_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件表，用于保存用户上传的附件内容。';



# Dump of table comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_comment`;

CREATE TABLE `tjp_comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '回复的评论ID',
  `content_id` bigint(20) unsigned DEFAULT NULL COMMENT '评论的内容ID',
  `content_module` varchar(32) DEFAULT NULL COMMENT '评论的内容模型',
  `comment_count` int(11) unsigned DEFAULT '0' COMMENT '评论的回复数量',
  `order_number` int(11) unsigned DEFAULT '0' COMMENT '排序编号，常用语置顶等',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '评论的用户ID',
  `ip` varchar(64) DEFAULT NULL COMMENT '评论的IP地址',
  `author` varchar(128) DEFAULT NULL COMMENT '评论的作者',
  `type` varchar(32) DEFAULT 'comment' COMMENT '评论的类型，默认是comment',
  `text` longtext COMMENT '评论的内容',
  `agent` text COMMENT '提交评论的浏览器信息',
  `created` datetime DEFAULT NULL COMMENT '评论的时间',
  `slug` varchar(128) DEFAULT NULL COMMENT '评论的slug',
  `email` varchar(64) DEFAULT NULL COMMENT '评论用户的email',
  `status` varchar(32) DEFAULT NULL COMMENT '评论的状态',
  `vote_up` int(11) unsigned DEFAULT '0' COMMENT '“顶”的数量',
  `vote_down` int(11) unsigned DEFAULT '0' COMMENT '“踩”的数量',
  `flag` varchar(256) DEFAULT NULL COMMENT '标识',
  `lat` decimal(20,16) DEFAULT NULL COMMENT '纬度',
  `lng` decimal(20,16) DEFAULT NULL COMMENT '经度',
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `content_id` (`content_id`),
  KEY `user_id` (`user_id`),
  KEY `email` (`email`),
  KEY `created` (`created`),
  KEY `parent_id` (`parent_id`),
  KEY `content_module` (`content_module`),
  KEY `comment_count` (`comment_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表，用于保存content内容的回复、分享、推荐等信息。';



# Dump of table content
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_content`;

CREATE TABLE `tjp_content` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` text COMMENT '标题',
  `text` longtext COMMENT '内容',
  `summary` text COMMENT '摘要',
  `link_to` varchar(256) DEFAULT NULL COMMENT '连接到(常用于谋文章只是一个连接)',
  `markdown_enable` tinyint(1) DEFAULT '0' COMMENT '是否启用markdown',
  `thumbnail` varchar(128) DEFAULT NULL COMMENT '缩略图',
  `module` varchar(32) DEFAULT NULL COMMENT '模型',
  `style` varchar(32) DEFAULT NULL COMMENT '样式',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `author` varchar(128) DEFAULT NULL COMMENT '匿名稿的用户',
  `user_email` varchar(128) DEFAULT NULL COMMENT '匿名稿的邮箱',
  `user_ip` varchar(128) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` text COMMENT '发布浏览agent',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父级内容ID',
  `object_id` bigint(20) unsigned DEFAULT NULL COMMENT '关联的对象ID',
  `order_number` int(11) unsigned DEFAULT '0' COMMENT '排序编号',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `vote_up` int(11) unsigned DEFAULT '0' COMMENT '支持人数',
  `vote_down` int(11) unsigned DEFAULT '0' COMMENT '反对人数',
  `rate` int(11) DEFAULT NULL COMMENT '评分分数',
  `rate_count` int(10) unsigned DEFAULT '0' COMMENT '评分次数',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '价格',
  `comment_status` varchar(32) DEFAULT NULL COMMENT '评论状态',
  `comment_count` int(11) unsigned DEFAULT '0' COMMENT '评论总数',
  `comment_time` datetime DEFAULT NULL COMMENT '最后评论时间',
  `view_count` int(11) unsigned DEFAULT '0' COMMENT '访问量',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `modified` datetime DEFAULT NULL COMMENT '最后更新日期',
  `slug` varchar(128) DEFAULT NULL COMMENT 'slug',
  `flag` varchar(256) DEFAULT NULL COMMENT '标识',
  `lng` decimal(20,16) DEFAULT NULL COMMENT '经度',
  `lat` decimal(20,16) DEFAULT NULL COMMENT '纬度',
  `meta_keywords` varchar(256) DEFAULT NULL COMMENT 'SEO关键字',
  `meta_description` varchar(256) DEFAULT NULL COMMENT 'SEO描述信息',
  `remarks` text COMMENT '备注信息',
  `platform_id` int(64) DEFAULT NULL COMMENT '机构',
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `user_id` (`user_id`),
  KEY `parent_id` (`parent_id`),
  KEY `content_module` (`module`),
  KEY `created` (`created`),
  KEY `vote_down` (`vote_down`),
  KEY `vote_up` (`vote_up`),
  KEY `view_count` (`view_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容表，用于存放比如文章、帖子、商品、问答等用户自定义模型内容。也用来存放比如菜单、购物车、消费记录等系统模型。';



# Dump of table mapping
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_mapping`;

CREATE TABLE `tjp_mapping` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content_id` bigint(20) unsigned NOT NULL COMMENT '内容ID',
  `taxonomy_id` bigint(20) unsigned NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`),
  KEY `taxonomy_id` (`taxonomy_id`),
  KEY `content_id` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容和分类的多对多映射关系。';



# Dump of table metadata
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_metadata`;

CREATE TABLE `tjp_metadata` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `meta_key` varchar(255) DEFAULT NULL COMMENT '元数据key',
  `meta_value` text COMMENT '元数据value',
  `object_type` varchar(32) DEFAULT NULL COMMENT '元数据的对象类型',
  `object_id` bigint(20) unsigned DEFAULT NULL COMMENT '元数据的对象ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='元数据表，用来对其他表的字段扩充。';



# Dump of table option
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_option`;

CREATE TABLE `tjp_option` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `option_key` varchar(128) DEFAULT NULL COMMENT '配置KEY',
  `option_value` text COMMENT '配置内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配置信息表，用来保存网站的所有配置信息。';



# Dump of table taxonomy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_taxonomy`;

CREATE TABLE `tjp_taxonomy` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(512) DEFAULT NULL COMMENT '标题',
  `text` text COMMENT '内容描述',
  `slug` varchar(128) DEFAULT NULL COMMENT 'slug',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `icon` varchar(128) DEFAULT NULL COMMENT '图标',
  `content_module` varchar(32) DEFAULT NULL COMMENT '对于的内容模型',
  `content_count` int(11) unsigned DEFAULT '0' COMMENT '该分类的内容数量',
  `order_number` int(11) DEFAULT NULL COMMENT '排序编码',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父级分类的ID',
  `object_id` bigint(20) unsigned DEFAULT NULL COMMENT '关联的对象ID',
  `flag` varchar(256) DEFAULT NULL COMMENT '标识',
  `lat` decimal(20,16) DEFAULT NULL COMMENT '经度',
  `lng` decimal(20,16) DEFAULT NULL COMMENT '纬度',
  `meta_keywords` varchar(256) DEFAULT NULL COMMENT 'SEO关键字',
  `meta_description` varchar(256) DEFAULT NULL COMMENT 'SEO描述内容',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `object_id` (`object_id`),
  KEY `content_module` (`content_module`),
  KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表。标签、专题、类别等都属于taxonomy。';



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_user`;

CREATE TABLE `tjp_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(128) DEFAULT NULL COMMENT '登录名',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `realname` varchar(128) DEFAULT NULL COMMENT '实名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `email_status` varchar(32) DEFAULT NULL COMMENT '邮箱状态（是否认证等）',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机电话',
  `mobile_status` varchar(32) DEFAULT NULL COMMENT '手机状态（是否认证等）',
  `telephone` varchar(32) DEFAULT NULL COMMENT '固定电话',
  `amount` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额（余额）',
  `gender` varchar(16) DEFAULT NULL COMMENT '性别',
  `role` varchar(32) DEFAULT 'visitor' COMMENT '权限',
  `signature` varchar(2048) DEFAULT NULL COMMENT '签名',
  `content_count` int(11) unsigned DEFAULT '0' COMMENT '内容数量',
  `comment_count` int(11) unsigned DEFAULT '0' COMMENT '评论数量',
  `qq` varchar(16) DEFAULT NULL COMMENT 'QQ号码',
  `wechat` varchar(32) DEFAULT NULL COMMENT '微信号',
  `weibo` varchar(256) DEFAULT NULL COMMENT '微博',
  `facebook` varchar(256) DEFAULT NULL,
  `linkedin` varchar(256) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `company` varchar(256) DEFAULT NULL COMMENT '公司',
  `occupation` varchar(256) DEFAULT NULL COMMENT '职位、职业',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `zipcode` varchar(128) DEFAULT NULL COMMENT '邮政编码',
  `site` varchar(256) DEFAULT NULL COMMENT '个人网址',
  `graduateschool` varchar(256) DEFAULT NULL COMMENT '毕业学校',
  `education` varchar(256) DEFAULT NULL COMMENT '学历',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `idcardtype` varchar(128) DEFAULT NULL COMMENT '证件类型：身份证 护照 军官证等',
  `idcard` varchar(128) DEFAULT NULL COMMENT '证件号码',
  `status` varchar(32) DEFAULT 'normal' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `create_source` varchar(128) DEFAULT NULL COMMENT '用户来源（可能来之oauth第三方）',
  `logged` datetime DEFAULT NULL COMMENT '最后的登录时间',
  `activated` datetime DEFAULT NULL COMMENT '激活时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `mobile` (`mobile`),
  KEY `status` (`status`),
  KEY `created` (`created`),
  KEY `content_count` (`content_count`),
  KEY `comment_count` (`comment_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表，保存用户信息。';


# Dump of table platform
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tjp_platform`;
CREATE TABLE `tjp_platform` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `summary` text COMMENT '摘要',
  `capital` varchar(20) DEFAULT NULL COMMENT '注册资本',
  `up_time` timestamp NULL DEFAULT NULL COMMENT '上线时间',
  `city` varchar(20) DEFAULT NULL COMMENT '所在城市',
  `context` varchar(30) DEFAULT NULL COMMENT '平台背景',
  `trusteeship` varchar(30) DEFAULT NULL COMMENT '资金托管',
  `security_model` varchar(100) DEFAULT NULL COMMENT '保障模式',
  `bid_security` varchar(100) DEFAULT NULL COMMENT '投标保障',
  `is_transfer` tinyint(1) DEFAULT '0' COMMENT '债券转让0不支持；1支持',
  `grade` int(2) DEFAULT NULL COMMENT '安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA',
  `min_profit` decimal(16,2) DEFAULT '0.00' COMMENT '最小年化收益',
  `max_profit` decimal(16,2) DEFAULT '0.00' COMMENT '最大年化收益',
  `min_dead_line` int(11) DEFAULT '0' COMMENT '最小产品期限',
  `max_dead_line` int(11) DEFAULT '0' COMMENT '最大产品期限',
  `dead_line_self_defined` varchar(100) DEFAULT NULL COMMENT '产品期限自定义显示',
  `platform_detail_img` varchar(128) DEFAULT NULL COMMENT '平台详情图片',
  `brief_introduction` longtext COMMENT '简介',
  `team_introduction` longtext COMMENT '团队介绍',
  `site_record` longtext COMMENT '网站备案',
  `contact_us` longtext COMMENT '联系我们',
  `pic_info` text COMMENT '图片资料',
  `style` varchar(32) DEFAULT NULL COMMENT '样式',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户',
  `order_number` int(11) unsigned DEFAULT '0' COMMENT '排序编号',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `modified` datetime DEFAULT NULL COMMENT '最后更新日期',
  `view_count` int(11) unsigned DEFAULT '0' COMMENT '访问量',
  `slug` varchar(128) DEFAULT NULL COMMENT 'slug',
  `meta_keywords` varchar(256) DEFAULT NULL COMMENT 'SEO关键字',
  `meta_description` varchar(256) DEFAULT NULL COMMENT 'SEO描述信息',
  `remarks` text COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='网贷平台信息表';

-- 模板配置
INSERT INTO `supermarket`.`tjp_option` (`option_key`, `option_value`) VALUES ('web_template_id', 'JBlog');
INSERT INTO `supermarket`.`tjp_option` (`option_key`, `option_value`) VALUES ( 'web_name', 'T呗资讯');

--  初始后台管理员
INSERT INTO `supermarket`.`tjp_user` (`username`, `nickname`, `realname`, `password`, `salt`, `email`, `email_status`, `mobile`, `mobile_status`, `telephone`, `amount`, `gender`, `role`, `signature`, `content_count`, `comment_count`, `qq`, `wechat`, `weibo`, `facebook`, `linkedin`, `birthday`, `company`, `occupation`, `address`, `zipcode`, `site`, `graduateschool`, `education`, `avatar`, `idcardtype`, `idcard`, `status`, `created`, `create_source`, `logged`, `activated`) VALUES ('admin', NULL, NULL, '64de9d041255398b959a9f8745a81336130b539dea8768fb231624749149f523', '774165e3c2b77', NULL, NULL, NULL, NULL, NULL, '0.00', NULL, 'administrator', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'activited', '2016-11-02 14:17:08', NULL, '2016-11-02 14:27:11', NULL);


--  网贷平台查询条件
DELETE from supermarket.tsys_config where config_name like '网贷平台筛选条件-%';
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-安全级别', 'platfrom_search_condit_grade', 'AAA', '=6', '安全级别AAA', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-安全级别', 'platfrom_search_condit_grade', 'AA以上', '>=5', '安全级别AA以上', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-安全级别', 'platfrom_search_condit_grade', 'A以上', '>=4', '安全级别A以上', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-安全级别', 'platfrom_search_condit_grade', 'BBB以上', '>=3', '安全级别BBB以上', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-安全级别', 'platfrom_search_condit_grade', 'BB以上', '>=2', '安全级别BB以上', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-安全级别', 'platfrom_search_condit_grade', 'B以上', '>=1', '安全级别B以上', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-年化收益', 'platfrom_search_condit_profit', '20%以上', '20,100', '年化收益20%以上', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-年化收益', 'platfrom_search_condit_profit', '16% - 20%', '16,20', '年化收益16%-20%', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-年化收益', 'platfrom_search_condit_profit', '12% - 16%', '12,16', '年化收益12%-16%', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-年化收益', 'platfrom_search_condit_profit', '8% - 12%', '8,12', '年化收益8%-12%', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-年化收益', 'platfrom_search_condit_profit', '8%以下', '1,8', '年化收益8%以下', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-产品期限', 'platfrom_search_condit_days', '30天以内', '1,30', '产品期限30天以内', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-产品期限', 'platfrom_search_condit_days', '1 - 3个月', '30,90', '产品期限1-3个月', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-产品期限', 'platfrom_search_condit_days', '3 - 6个月', '90,180', '产品期限3-6个月', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-产品期限', 'platfrom_search_condit_days', '6 - 12个月', '180,365', '产品期限6-12个月', NOW(), '0');
INSERT INTO `supermarket`.`tsys_config` (`config_name`, `config_type`, `config_key`, `config_value`, `remark`, `crt_time`, `app_type`) VALUES ('网贷平台筛选条件-产品期限', 'platfrom_search_condit_days', '12个月以上', '365,1825', '产品期限12个月以上', NOW(), '0');
