#SHOW DATABASES;
#SELECT * FROM mysql.db;

#CREATE DATABASE myweb1;

#select * from mysql.user;
#select * from information_schema.SCHEMATA;
#select * from information_schema.tables; #Mysql DB object has no owner concept like Oracle.

CREATE USER zikaouser IDENTIFIED BY 'abcd';
#way 1: set password for zikaouser=password('abcd');
#way 2:
#update mysql.user set password=password('yyy') where user='zikaouser'; #Success
#update mysql.user set authentication_string=password('yyy') where user='zikaouser'; #Failed
#flush privileges; 
#You will see this error if forget to flush: 
#ERROR 1045 (28000): Access denied for user 'zikaouser'@'localhost' (using password: YES)
#Question: what is the difference between password and authentication_string?
#way 3: mysqladmin -u用户名 -p旧密码 password 新密码

#SELECT PASSWORD('abcd');
#CREATE USER zikaouser2 IDENTIFIED BY password '*A154C52565E9E7F94BFC08A1FE702624ED8EFFDA';
#drop user zikaouser2;

GRANT SELECT,INSERT,UPDATE,DELETE ON myweb1.* TO zikaouser;
#revoke SELECT,INSERT,UPDATE,DELETE ON myweb1.* from zikaouser;
#show grants;

#show variables like '%commit%';
#show variables where variable_name like 'log%' and value !='ABC';
#show global variables;
#show session variables;
#show local variables;
#show status like '%commit%';
#show processlist;

SET autocommit=0; #Can be 0/1/on/off. default is changing at session level.

#Extend number limit of connection
#show variables like '%connection%';
#set global max_connections=100; #Variable 'max_connections' is a GLOBAL variable and should be set with SET GLOBAL

#如何查看和修改系统参数？https://blog.51cto.com/u_15980129/6087466

#show create table zk_student\G;
#select * from zk_student\G;

#===================Below are application===========================
USE myweb1;

#drop table if exists sys_param;
CREATE TABLE sys_param(
param_code VARCHAR(30),
param_value VARCHAR(100),
param_desc VARCHAR(100), 
order_id INT,
PRIMARY KEY(param_code)
);

INSERT INTO sys_param(param_code, param_value, param_desc, order_id) VALUES('StartDateStr', '2023-03-20', 'class day range', 10);
INSERT INTO sys_param(param_code, param_value, param_desc, order_id) VALUES('EndDateStr', '2023-03-27', 'class day range', 11);
UPDATE sys_param SET param_value='2023-03-23' WHERE param_code='StartDateStr';
UPDATE sys_param SET param_value='2023-03-29' WHERE param_code='EndDateStr';

#drop table if exists sys_log;
CREATE TABLE sys_log(
remote_ip VARCHAR(16),
login_user_id INT,
url VARCHAR(100),
oper_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

#drop table if exists zk_sys_log;
CREATE TABLE zk_sys_log(
user_id INT,
oper_code VARCHAR(30),
detail VARCHAR(100),
oper_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

#select * from sys_log order by oper_time desc;
#select * from zk_sys_log order by oper_time desc;

#drop table if exists zk_student;
CREATE TABLE zk_student(
stu_id INT,
stu_no VARCHAR(30) NOT NULL,
pwd VARCHAR(30) NOT NULL,
stu_name VARCHAR(30) NOT NULL,
role_code VARCHAR(10),
photo_path VARCHAR(100),
PRIMARY KEY (stu_id)
);

#drop table if exists zk_course;
CREATE TABLE zk_course(
course_code VARCHAR(10),
course_name VARCHAR(30),
order_id INT,
PRIMARY KEY (course_code)
);

#select * from zk_course;

INSERT INTO zk_course(course_code, course_name, order_id) VALUES('closed', '未开放', 9);
INSERT INTO zk_course(course_code, course_name, order_id) VALUES('dbs', '数据库系统原理', 10);
INSERT INTO zk_course(course_code, course_name, order_id) VALUES('netdb', '互联网数据库', 11);
INSERT INTO zk_course(course_code, course_name, order_id) VALUES('or', '运筹学', 12);
UPDATE zk_course SET course_name='DB系统原理' WHERE course_code='dbs';

#drop table if exists zk_role_crs_map;
CREATE TABLE zk_role_crs_map(
role_code VARCHAR(10),
course_code VARCHAR(10),
PRIMARY KEY(role_code, course_code)
);

#select * from zk_role_crs_map;

INSERT INTO zk_role_crs_map(role_code, course_code) VALUES('sysAdmin', 'closed');
INSERT INTO zk_role_crs_map(role_code, course_code) VALUES('sysAdmin', 'dbs');
INSERT INTO zk_role_crs_map(role_code, course_code) VALUES('sysAdmin', 'netdb');

INSERT INTO zk_role_crs_map(role_code, course_code) VALUES('teacher', 'closed');
INSERT INTO zk_role_crs_map(role_code, course_code) VALUES('teacher', 'dbs');

INSERT INTO zk_role_crs_map(role_code, course_code) VALUES('student', 'dbs');

#drop table if exists zk_book_class;
CREATE TABLE zk_book_class(
book_id INT PRIMARY KEY AUTO_INCREMENT, 
stu_id INT, 
course_code VARCHAR(10),
class_time VARCHAR(16),
is_canceled VARCHAR(1) DEFAULT 'N',
canceled_time TIMESTAMP
);

#select * from zk_book_class order by class_time desc;

#select * from zk_student;

INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, role_code) VALUES(1, 'admin', 'admin123!', '陈工', 'sysAdmin');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, role_code) VALUES(2, 'teacher1', 'abc123!', '陈老师', 'teacher');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, role_code) VALUES(3, 'student1', 'abc123!', '学生1', 'student');

UPDATE zk_student SET photo_path='stevechen.jpg' WHERE stu_no='admin';

#Please remember to change DB account psw and student psw after DB initialized;

#=============================Operation===================================

INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, photo_path) VALUES(11, '15768024635', '24635', '狼', 'ln-dbs-15768024635.jpg');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, photo_path) VALUES(12, '13318623807', '23807', '申新旺-矢', 'ln-dbs-13318623807.jpg');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, photo_path) VALUES(13, '13544561245', '61245', '蔡云同-风露立中宵', 'ln-dbs-13544561245.jpg');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, photo_path) VALUES(14, '18178375064', '75064', '陶晔-阳光正好', 'ln-dbs-18178375064.jpg');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, photo_path) VALUES(15, '15992207233', '07233', '再睡五分钟', 'ln-dbs-15992207233.jpg');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, photo_path) VALUES(16, '19924910801', '10801', '胡生霞', 'ln-dbs-19924910801.jpg');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, photo_path) VALUES(17, '17329735941', '10801', '陈茜-信任是个好东西', 'ln-dbs-17329735941.jpg');
INSERT INTO zk_student(stu_id, stu_no, pwd, stu_name, photo_path) VALUES(17, '14739155550', '55550', '迷路', 'ln-dbs-14739155550.jpg');
UPDATE zk_student SET role_code='student' WHERE role_code IS NULL AND photo_path LIKE 'ln-dbs-%';
SELECT * FROM zk_student WHERE role_code IS NULL;

COMMIT;
SHOW VARIABLES LIKE '%connect%';
