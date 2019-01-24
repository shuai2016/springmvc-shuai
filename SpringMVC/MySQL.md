# MySQL配置

## my.ini

1. 路径：安装路径根目录

2. 服务端配置：[mysqld]

   ```ini
   #端口号
   port=3306
   #安装目标
   basedir="D:/work/MySQL/MySQL Server 5.5/"
   #文件目录
   datadir="D:/work/MySQL/data/Data/"
   #字符集
   character-set-server=utf8
   #存储引擎
   default-storage-engine=INNODB
   #语法模式
   sql-mode="STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"
   #最大连接数
   max_connections=100
   ```

   1. 注意：修改配置后，重启mysql服务

# 服务端登录

## 命令行登录

1. 命令行（管理员）：`mysql -h localhost -P 3306 -u root -proot`
   1. -h：主机名
   2. -P：端口号
   3. -u：用户名
   4. -p：密码(-p与密码之间不能有空格，先运行名再输入密码，密文)
   5. 本机3306端口访问可简写：`mysql -u root -proot`

# 基本命令

1. 建议：每条命令的结尾使用`;`或者`/g`
2. 查看数据库（管理软件）版本：
   1. sql命令：`select version();`
   2. dos命令：`mysql --version`（`mysql -V`）
3. 查找数据库相关
   1. 查看有哪些数据库：`show databases;`
   2. 进入某个数据库：`use test;`
   3. 查看当前数据库有哪些表：`show tables;`
   4. 查看某数据库的表：`show tables from mysql;`
   5. 查看当前所在数据库：`select database();`
4. 查找数据表相关
   1. 查看有哪些数据表：`show tables;`
   2. 查看表的结构：`desc stuinfo;`

# MySQL语法规范

1. 大小写：建议关键字大写，表名、列名小写
2. 注释：
   1. 单行注释：`#注释文字`
   2. 单行注释：`-- 注释文字`（注意空格）
   3. 多行注释：`/* 注释文字 */`

# 查询DQL

## 基本查询

1. 查询常量值：`SELECT 100;`，字符需要加单引号`'`
2. 查询表达式：`SELECT 100%98;`
3. 查询函数：`SELECT VERSION(); `
4. 起别名：`SELECT 100%98 AS 结果;`，别名有特殊字符则别名外加上双引号`"`
5. ”+“的作用：`SELECT '123'+90`，mysql中的”+“仅仅有运算符的作用
   1. ”+“号两边都是数字，直接做加法运算
   2. 存在字符串，则尝试转换为数字，转换失败，转换为0
   3. 存在null（不是字符串），结果为null（不是字符串）
6. 字符串拼接：`SELECT concat(123,null)`，存在null结果为null

## 条件查询

1. 不等于：建议`<>`，`!=`也不报错
2. not用法：`NOT ( salary >= 10000 AND salary <= 20000 )`
3. like通配符：
   1. `%`：任意多个字符，包含0个字符
   2. `_`：任意单个字符
   3. 模糊查询第二个字符为`_`：需要转义
      1. 默认转义：`LIKE '_\_%'`
      2. 指定转义字符：`LIKE '_$_%' ESCAPE '$'`