# 创建人事工资管理系统数据库
create database if not exists `emp_ms` default character set utf8;
# drop database if exists `emp_ms`;
# 创建员工表
create table if not exists `emp_ms`.`employee`
(
    `id`      int(11)      NOT NULL AUTO_INCREMENT,
    `name`    varchar(255) NOT NULL,
    `passwd`  varchar(255) NOT NULL,
    `sex`     nchar(2) check ( sex = '男' or sex = '女' ),
    `rate`    int(11)      NOT NULL,
    `birth`   date         NOT NULL,
    `address` varchar(255) NOT NULL,
    `cardId`  varchar(20)  NOT NULL,
    `tel`     varchar(20)  NOT NULL,
    `dep_id`  int(11)      NOT NULL,
    `job_id`  int(11)      NOT NULL,
    PRIMARY KEY (`id`),
    foreign key (`dep_id`) references `emp_ms`.`department` (`id`),
    foreign key (`job_id`) references `emp_ms`.`job` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# 在员工表中姓名字段添加索引
CREATE INDEX idx_name ON emp_ms.employee (name);
# update `emp_ms`.`employee`
# set `id` = id + 8000
# where id < 7000;
# alter table `employee`
#     drop column location;
# alter table `emp_ms`.`employee`
#     change  salary rate float(11) default null;
# update `emp_ms`.`employee`
# set `rate` = 0.2 where job_id %11 = 0;
# alter table `employee`
#     drop column `dep_id`;

alter table emp_ms.`employee`
    add index idx_emp_id (`id`);

alter table emp_ms.`employee`
    modify sex nchar(2) not null;

alter table emp_ms.`employee`
    add FOREIGN KEY (`dep_id`)
        REFERENCES `department` (`id`);

alter table emp_ms.`employee`
    add FOREIGN KEY (`job_id`)
        REFERENCES `job` (`id`);


# 创建部门表
create table if not exists `emp_ms`.`department`
(
    # 部门编号
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    # 部门名称
    `dep_name`   varchar(255) NOT NULL,
    # 部门负责人编号
    `manager_id` int(11)      NOT NULL,
    primary key (`id`),
    foreign key (`manager_id`) references `employee` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# drop table if exists `department`;

# alter table `department`
#     change dap_name dep_name varchar(255) not null;
alter table emp_ms.`department`
    add index idx_mag_id (`manager_id`);

alter table emp_ms.`department`
    add foreign key (`manager_id`) references `employee` (`id`);


# 创建职位表
create table if not exists `emp_ms`.`job`
(
    # 职位编号
    `id`       int(11)      NOT NULL AUTO_INCREMENT,
    # 职位名称
    `job_name` varchar(255) NOT NULL,
    # 职位薪资
    `salary`   int(11)      NOT NULL check ( salary > 0 ),
    # 部门编号
    `dep_id`   int(11)      NOT NULL,
    primary key (`id`),
    foreign key (`dep_id`) references `department` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# alter table `job`
#     change name job_name varchar(255) not null;
# alter table job
#     add column salary int(11) not null;

alter table emp_ms.`job`
    add foreign key (`dep_id`) references `department` (`id`) on delete cascade on update cascade;
alter table emp_ms.`job`
    add index `name_idx` (`job_name`);

# 创建管理员表
create table if not exists `emp_ms`.`manager`
(
    # 管理员编号
    `id`     int(11)     NOT NULL primary key auto_increment,
    # 管理员账号
    `name`   varchar(20) NOT NULL,
    # 管理员密码
    `passwd` varchar(20) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


# 视图
create view `emp_ms`.`view_all_dep_info` as
select d.id 部门编号, d.dep_name 部门名称, e.id 部门主管编号, e.name 部门主管名称
from emp_ms.department d
         join emp_ms.employee e
              on d.manager_id = e.id;

# 创建管理员界面主要视图
create view emp_ms.view_all_dep_info as
select d.id, d.dep_name, d.manager_id, e.name
from emp_ms.department as d
         join emp_ms.employee as e on d.manager_id = e.id;

select *
from emp_ms.view_all_dep_info;

