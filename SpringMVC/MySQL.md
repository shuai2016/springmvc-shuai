# MySQL配置

## my.ini

1. 路径：安装路径根目录

2. 服务端配置：[mysqld]

   ```ini
   #端口号
   port=3306
   #安装目录
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
   1. -h：-h 主机名
   2. -P：-P 端口号
   3. -u：-u 用户名
   4. -p：-p密码(-p与密码之间不能有空格，先运行再输入密码，密文)
   5. 本机3306端口访问可简写：`mysql -u root -proot`

# 基本命令

1. 建议：每条命令的结尾使用`;`或者`/g`
2. 查看数据库（管理系统）版本：
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

## 基础查询

1. 查询常量值：`SELECT 100;`，字符型和日期型的常量需要加单引号`'`
2. 查询表达式：`SELECT 100%98;`
3. 查询函数：`SELECT VERSION(); `
4. 起别名：`SELECT 100%98 AS 结果;`，别名有特殊字符则别名外加上双引号`"`
5. distinct：去重
   1. 多个字段不起作用：`distinct salary,commission_pct`
6. ”+“的作用：`SELECT '123'+90`，mysql中的”+“仅仅有运算符的作用
   1. ”+“号两边都是数字，直接做加法运算
   2. 存在字符串，则尝试转换为数字，转换失败，转换为0
   3. 存在null（不是字符串），结果为null（不是字符串）
7. 字符串拼接：`SELECT concat(123,null)`，存在null结果为null
8. ifnull：`IFNULL( commission_pct, 0 ) `，判断某字段或表达式是否为null，如果为null返回指定的值，否则返回原本的值
9. isnull：`ISNULL(commission_pct)`，判断某字段或表达式是否为null，如果是则返回1，否则返回0

## 条件查询

1. 不等于：建议`<>`，`!=`也不报错
2. not用法：`NOT ( salary >= 10000 AND salary <= 20000 )`
3. like通配符（可以判断字符型或数值型）：
   1. `%`：任意多个字符，包含0个字符
   2. `_`：任意单个字符
   3. 模糊查询第二个字符为`_`：需要转义
      1. 默认转义：`LIKE '_\_%'`
      2. 指定转义字符：`LIKE '_$_%' ESCAPE '$'`
   4. `like '%%'`：无法查出为null的数据
   5. like可以判断数值型：`LIKE '1__'`
4. `is null`：=或`<>`不能用于判断null值，`IS NULL`或`IS NOT NULL`用于判断null值
5. 安全等于：`<=>`，表示等于
   1. 可以判断null：`commission_pct <=> NULL`
   2. 可以判断普通数值：`salary <=> 12000`

## 排序查询

1. `order by`子句中可以支持单个字段、多个字段、表达式、函数、别名
2. `order by`子句一般是放在查询语句的最后面，limit子句除外

## 常见函数

```sql
SELECT 函数名(实参列表) 【from 表】;
```

### 单行函数

#### 字符函数

1. length：`SELECT LENGTH('张三丰hahaha');`获取参数值的字节个数，一个中文占多个字节（utf8字符集一个中文占3个字节）
2. concat：拼接字符串
3. upper、lower
4. substr、substring 
   1. 索引从1开始
   2. 截取从指定索引处后面所有字符：`SELECT SUBSTR('李莫愁爱上了陆展元',7) out_put;`
   3. 截取从指定索引处指定字符长度的字符：`SELECT SUBSTR('李莫愁爱上了陆展元',1,3) out_put;`
5. instr：`SELECT INSTR('杨不悔爱上了殷六侠','殷六侠') out_put;`，返回字串第一次出现的索引，如果找不到返回0
6. trim：去前后空格
   1. 默认去前后空格：`SELECT TRIM( '  张翠山  ' ) out_put;`
   2. 去前后指定字符：`SELECT TRIM( 'a' FROM 'aaaaa张aaaaa翠山aaaaa' ) out_put;`，中间不会去掉
7. lpad：`SELECT LPAD('殷素素',10,'*') out_put;`，用指定的字符左填充原字符使字符串至指定长度，若原字符串已经超出指定长度则从左截取指定长度返回。
8. rpad：若原字符串已经超出指定长度依然从左截取指定长度返回。
9. replace：`SELECT REPLACE('张无忌爱上了周芷若周芷若周芷若','周芷若','赵敏') out_put;`，替换全部指定字符

#### 数学函数

1. round：四舍五入
   1. `SELECT ROUND( - 1.5 )`：返回-2
   2. `SELECT ROUND( - 1.55,1 )`：返回-1.6，小数点后保留1位
2. ceil：向上取整，返回>=该参数的最小整数
   1. `SELECT ceil(-1.2)`：返回-1
3. floor：向下取整，返回<=该参数的最大整数
4. truncate：截断
   1. `SELECT TRUNCATE(1.555,2 )`，返回1.55，小数点后保留2位
5. mod：取余，`MOD(a,b)`：表示`a-a/b*b`，所以符号和被除数相同
   1. `SELECT MOD(-10,-3)`：返回-1
   2. `SELECT MOD(-10,3)`：返回-1
6. rand：获取随机数，返回0-1之间的小数（取不到1），`SELECT RAND()`

#### 日期函数

1. now：返回当前系统日期+时间
   1. `SELECT NOW();`：返回 2019-01-25 11:27:29
2. curdate：返回当前系统日期，不包含时间
   1. `SELECT CURDATE();`：返回 2019-01-25
3. curtime：返回当前时间，不包含日期
   1. `SELECT CURTIME();`：返回 11:34:17
4. 获取时间的指定部分，年、月、日、时、分、秒
   1. `SELECT MONTH(NOW());`：返回 1
   2. `SELECT MONTHNAME(NOW());`：返回 January
5. str_to_date：字符转换为日期
   1. `SELECT STR_TO_DATE('2019/1/25 8-12-20','%Y/%c/%d %H-%i-%s');`：返回 2019-01-25 08:12:20
   2. 格式符
      1. `%Y`：四位年份
      2. `%y`：2位年份
      3. `%m`：月份（01,02，……，11,12）
      4. `%c`：月份（1,2，……，11,12）
      5. `%d`：日（01,02）
      6. `%H`：小时（24小时制）
      7. `%h`：小时（12小时制）
      8. `%i`：分钟（00,01，……，59）
      9. `%s`：秒（00,01，……，59）
6. date_format：将日期转换成字符
   1. `SELECT DATE_FORMAT(NOW(),'%Y/%c/%d %H-%i-%s');`：返回 2019/1/25 11-53-26
7. datediff：查询相差天数
   1. `SELECT DATEDIFF(NOW(),'1994-01-01');`，第一个参数为较大日期（较近日期）

#### 其它函数

1. 查看版本号：`SELECT VERSION()`
2. 查看当前数据库：`SELECT DATABASE()`
3. 查看当前的用户：`SELECT USER()`
4. 返回字符串的密码形式：`SELECT PASSWORD('字符');`
5. 返回字符串的md5加密形式：`SELECT MD5( '字符' );`

#### 流程控制函数

1. if函数：`SELECT IF(10<5,'大','小');`

2. case：

   1. 类似switch...case

      ```sql
      /*
      case 要判断的字段或表达式
      when 常量1 then 要显示的值1或语句1;（值不加;）
      when 常量2 then 要显示的值2或语句2;
      ...
      else 要显示的值n或语句n;
      end
      */
      SELECT
      	salary 原始工资,
      	department_id,
      CASE
      	department_id 
      	WHEN 30 THEN salary * 1.1 
      	WHEN 40 THEN salary * 1.2 
      	WHEN 50 THEN salary * 1.3 ELSE salary 
      	END AS 新工资 
      FROM
      	employees;
      ```

   2. 类似多重if

      ```sql
      /*
      case
      when 条件1 then 要显示的值1或语句1
      when 条件2 then 要显示的值2或语句2
      ...
      else 要显示的值n或语句n
      end
      */
      SELECT
      	salary,
      CASE
      	WHEN salary > 20000 THEN 'A' 
      	WHEN salary > 15000 THEN 'B' 
      	WHEN salary > 10000 THEN 'C'
      	ELSE 'D' 
      	END AS 工资级别 
      FROM
      	employees;
      ```

### 分组函数

1. sum、avg一般用于处理数值型，max、min、count可以处理任何类型
2. 以上分组函数都忽略null值
3. 可以和distinct搭配实现去重后计算
4. count函数的效率问题，一般使用count(*)用作统计行数
   1. MYISAM存储引擎下，count(*)的效率高
   2. INNODB存储引擎下，count(*)和count(1)的效率差不多，比count(字段)效率要高一些
5. 和分组函数一同查询的字段要求是group by后出现的字段

## 分组查询

1. 分组查询中的筛选条件分为两类

   |            | 数据源         | 位置                | 关键字 |
   | ---------- | -------------- | ------------------- | ------ |
   | 分组前筛选 | 原始表         | group by 子句的前面 | where  |
   | 分组后筛选 | 分组后的结果集 | group by 子句的后面 | having |

   1. 分组函数做条件肯定是放在having字句中
   2. 能用分组前筛选的，就优先考虑使用分组前筛选

2. 按表达式或函数分组

   ```sql
   SELECT
   	count( * ) c,
   	length( last_name ) len_name 
   FROM
   	employees 
   GROUP BY
   	len_name 
   HAVING
   	c > 5;
   ```

   1. mysql支持group by子句 和 having子句 使用 select子句 的别名

## 连接查询（多表查询）

### 笛卡尔乘积现象

### 分类

#### 按年代分类

##### sql92标准：mysql中仅仅支持内连接

1. 等值连接

   1. 多表等值连接的结果为多表的交集部分
   2. n表连接，至少需要n-1个连接条件
   3. 多表的顺序没有要求
   4. 一般需要为表起别名
   5. 可以搭配前面介绍的所有子句使用，比如排序，分组，筛选

2. 非等值连接

   ```sql
   SELECT
   	salary,
   	grade_level 
   FROM
   	employees e,
   	job_grades g 
   WHERE
   	salary BETWEEN g.lowest_sal 
   	AND g.highest_sal;
   ```

3. 自连接

   ```sql
   SELECT
   	e.employee_id,
   	e.last_name,
   	m.employee_id,
   	m.last_name 
   FROM
   	employees e,
   	employees m 
   WHERE
   	e.manager_id = m.employee_id
   ```

##### sql99标准：mysql支持内连接+外连接（左外和右外）+交叉连接

```sql
select 查询列表
from 表1 别名 【连接类型】
join 表2 别名
on 连接条件
【where 筛选条件】
【group by 分组】
【having 筛选条件】
【order by 排序列表】
```

1. 内连接：inner

   1. 等值连接：【inner】
   2. 非等值连接：【inner】
   3. 自连接：【inner】

2. 外连接：外连接的查询结果为主表中的所有记录，如果从表中有和它匹配的，则显示匹配的值，如果从表中没有和它匹配的，则显示null，外连接查询结果=内连接结果+主表中有而从表没有的记录

   1. 左外连接：left 【outer】：left join 左边的是主表

      ```sql
      SELECT
      	b.`name`
      FROM
      	beauty b
      	LEFT OUTER JOIN boys bo ON b.boyfriend_id = bo.id 
      WHERE
      	bo.id IS NULL;
      ```

   2. 右外连接：right 【outer】：right join 右边的是主表

   3. 全外连接：full 【outer】：（mysql不支持）

3. 交叉连接：cross（笛卡尔乘积）

   ```sql
   SELECT
   	b.*,
   	bo.* 
   FROM
   	beauty b
   	CROSS JOIN boys bo;
   ```

#### 按功能分类

##### 内连接

1. 等值连接
2. 非等值连接
3. 自连接

##### 外连接

1. 左外连接
2. 右外连接（mysql不支持）
3. 全外连接

##### 交叉连接（笛卡尔乘积）

### 注意

1. 如果为表起了别名，则查询的字段就不能使用原来的表名去限定

## 子查询

出现在其它语句内部的select语句称为子查询或内查询，外面的语句可以是insert、update、delete、select等，一般select作为外面语句较多，外面如果为select语句，则此语句称为主查询或外查询。

### 分类

#### 按子查询出现的位置

1. select后面

   1. 仅仅支持标量子查询

      ```sql
      SELECT
      	d.*,
      	( SELECT COUNT( * ) FROM employees e WHERE e.department_id = d.department_id ) 个数 
      FROM
      	departments d;
      ```

2. from后面

   1. 支持表子查询

      ```sql
      SELECT
      	ag_dep.*,
      	g.grade_level 
      FROM
      	( SELECT AVG( salary ) ag, department_id FROM employees GROUP BY department_id ) ag_dep
      	INNER JOIN job_grades g ON ag_dep.ag BETWEEN lowest_sal 
      	AND highest_sal
      ```

      1. 将子查询结果充当一张表，要求必须起别名

3. where或having后面*

   1. 标量子查询*

      ```sql
      SELECT
      	MIN( salary ),
      	department_id 
      FROM
      	employees 
      GROUP BY
      	department_id 
      HAVING
      	MIN( salary ) > ( SELECT MIN( salary ) FROM employees WHERE department_id = 50 );
      ```

   2. 列子查询（多行子查询）*

      ```sql
      SELECT
      	last_name,
      	employee_id,
      	job_id,
      	salary 
      FROM
      	employees 
      WHERE
      	salary < ALL ( SELECT DISTINCT salary FROM employees WHERE job_id = 'IT_PROG' );
      ```

   3. 行子查询（一行多列）

      ```sql
      SELECT
      	* 
      FROM
      	employees 
      WHERE
      	( employee_id, salary ) = ( SELECT MIN( employee_id ), MAX( salary ) FROM employees );
      ```

4. exists后面（相关子查询）

   1. 表子查询

      ```sql
      SELECT
      	department_name 
      FROM
      	departments d 
      WHERE
      	EXISTS ( SELECT * FROM employees e WHERE d.department_id = e.department_id );
      ```

#### 按结果集的行列数不同

1. 标量子查询（结果集只有一行一列）
2. 列子查询（结果集只有一列多行）
3. 行子查询（结果集有一行多列）
4. 表子查询（结果集一般为多行多列）

### 特点

1. 子查询放在小括号内
2. 子查询一般放在条件的右边
3. 标量子查询，一般搭配着单行操作符使用：<、>、=、<=、>=、<>
4. 列子查询，一般搭配着多行操作符使用：IN/NOT IN、ANY/SOME、ALL
5. 子查询的执行优先于主查询执行，主查询的条件用到了子查询的结果

## 分页查询

```sql
select 查询列表
from 表
【join type join 表2
on 连接条件
where 筛选条件
group by 分组字段
having 分组后的筛选
order by 排序的字段】
limit 【offset,】size;
```

1. offset：要显示条目的起始索引（起始索引从0开始，默认从0开始）
2. size：要显示的条目个数

### 特点

1. limit语句放在查询语句的最后

2. 公式

   ```sql
   要显示的页数 page，每页的条目数 size，
   select 查询列表
   from 表
   limit (page-1)*size,size;
   ```

## 联合查询（union）

```sql
SELECT * FROM employees WHERE email LIKE '%a%'
UNION
SELECT * FROM employees WHERE department_id > 90;
```

1. 将多条查询语句的结果合并成一个结果

### 特点

1. 要求多条查询语句的查询列数是一致的
2. 要求多条查询语句的查询的每一列的类型和顺序最好一致
3. union关键字默认去重，如果使用union all 可以包含重复项

# 数据操作语言DML

## 插入语句

### 方式一：经典插入

```sql
insert into 表名(列1,...) values(值1,...),(值1,...);
```

1. 插入的值的类型要与列的类型一致或兼容
2. 列数和值的个数必须一致
3. 可以省略列名，默认所有列，而且列的顺序和表中的列的顺序一致

### 方式二

```sql
insert into 表名
set 列名=值,列名=值,...
```

### 两种插入方式对比

1. 方式一支持插入多行，方式二不支持
2. 方式一支持子查询，方式二不支持

### 其它插入方式

```sql
INSERT INTO my_employees
SELECT 1,'Patel','Ralph','Rpatel',985 UNION 
SELECT 2,'Dancs','Betty','Bdancs',860;
```

## 修改语句

### 修改单表的记录

```sql
update 表名
set 列=新值,列=新值,...
where 筛选条件;
```

### 修改多表的记录

```sql
sql92语法
update 表1 别名,表2 别名
set 列=值,...
where 连接条件
and 筛选条件;

sql99语法
update 表1 别名
inner|left|right join 表2 别名
on 连接条件
set 列=值,...
where 筛选条件;
```

## 删除语句

### 方式一：delete

#### 单表的删除

```sql
delete from 表名 where 筛选条件
```

#### 多表的删除

```sql
sql92语法
delete 表1的别名,表2的别名（删除哪个表数据写哪个表）
from 表1 别名,表2 别名
where 连接条件
and 筛选条件;

sql99语法
delete 表1的别名,表2的别名
from 表1 别名
inner|left|right join 表2 别名 on 连接条件
where 筛选条件;
```

### 方式二：truncate

```sql
truncate table 表名;
```

### 两种删除方式的区别

1. delete 可以加 where 条件， truncate不能加
2. truncate删除，效率较高
3. 假如要删除的表中有自增长列，如果用delete删除后，再插入数据，自增长列的值从断点开始，而truncate删除后，在插入数据，自增长列的值从1开始
4. truncate删除没有返回值，delete删除有返回值
5. truncate删除不能回滚，delete删除可以回滚

# 数据定义语言DDL

库和表的管理创建（create）、修改（alter）、删除（drop）

## 库的管理

### 库的创建

```sql
CREATE DATABASE [if not exists] 库名;
```

### 库的修改

#### 库名的修改（废弃）

```sql
RENAME DATABASE bookes TO 新库名;
```

#### 更改库的字符集

```sql
ALTER DATABASE books CHARACTER SET gbk;
```

### 库的删除

```sql
DROP DATABASE [IF EXISTS] books;
```

## 表的管理

### 表的创建

```sql
create table [IF NOT EXISTS] 表名(
	列名 列的类型[(长度) 约束],
	列名 列的类型[(长度) 约束],
	列名 列的类型[(长度) 约束],
	...
	列名 列的类型[(长度) 约束]
)
```

### 表的修改

```sql
alter table 表名 add|drop|modify|change column 列名 [列类型 约束]
```

#### 修改列名

```sql
ALTER TABLE book CHANGE COLUMN publishdate(旧名) pubDate(新名) DATETIME(类型);
```

#### 修改列的类型或约束

```sql
ALTER TABLE book MODIFY COLUMN pubdate TIMESTAMP;
```

#### 添加新列

```sql
ALTER TABLE author ADD COLUMN annual DOUBLE;
```

#### 删除列

```sql
ALTER TABLE author DROP COLUMN annual;
```

#### 修改表名

```sql
ALTER TABLE author RENAME TO book_author;
```

### 表的删除

```sql
DROP TABLE [IF EXISTS] book_author;
```

### 表的复制

#### 仅复制表结构

```sql
CREATE TABLE copy(新表) LIKE author(已经存在的表);
```

#### 复制表的结构及数据

```sql
CREATE TABLE copy2 SELECT * FROM author;
```

#### 仅复制某些字段

```sql
CREATE TABLE copy4
SELECT id,au_name
FROM author
WHERE 0;
```

# 常见的数据类型

## 数值型

### 整型

tinyint（1个字节）、smallint（2个字节）、mediumint（3个字节）、int（integer，4个字节）、bigint（8个字节）

#### 设置无符号和有符号

```sql
CREATE TABLE tab_int(
	t1 INT,
    t2 INT UNSIGNED
)
```

#### 特点

1. 如果不设置无符号还是有符号，默认是有符号，如果想设置无符号，需要添加unsigned关键字
2. 如果插入的数值超出了整型的范围，会报out of range异常，并且插入临界值
3. 如果不设置长度，会有默认的长度，长度代表了显示的最大宽度，如果不够会用0在左边填充，但必须搭配zerofill使用，如果使用zerofill，则变为无符号

### 小数

#### 浮点数

float(M,D)

double(M,D)

#### 定点数

dec(M,D)

decimal(M,D)

#### 特点

1. M表示整数部位+小数部位，D表示小数部位，如果超过范围，则插入临界值
2. M和D都可以省略，如果是decimal，则M默认为10，D默认为0，如果是float和double，则会根据插入的数值的精度来决定精度
3. 定点型的精确度较高，如果要求插入数值的精度较高如货币运算则考虑使用

## 字符型

### 较短的文本

1. char、varchar

   | 类型    | 写法       | M的意思                                             | 特点           | 空间的消耗 | 效率 |
   | ------- | ---------- | --------------------------------------------------- | -------------- | ---------- | ---- |
   | char    | char(M)    | 最大的字符数（一个汉字一个字符，可以省略，默认为1） | 固定长度的字符 | 比较耗费   | 高   |
   | varchar | varchar(M) | 最大的字符数（一个汉字一个字符）                    | 可变长度的字符 | 比较节省   | 低   |

2. binary和varbinary类型类似与char和varchar，不同的是它们包含二进制字符串而不包含非二进制字符串。

3. 枚举enum（不区分大小写）

   ```sql
   CREATE TABLE tab_char(
       c1 ENUM('a','b','c')
   )
   ```

4. 集合set（不区分大小写）

   ```sql
   CREATE TABLE tab_set(
       s1 SET('a','b','c','d')
   );
   INSERT INTO tab_set VALUES('a');
   INSERT INTO tab_set VALUES('B');
   INSERT INTO tab_set VALUES('a,b');
   ```

### 较长的文本

text、blob（较长的二进制数据）

## 日期型

| 类型      | 字节 | 范围      | 时区影响 |
| --------- | ---- | --------- | -------- |
| datetime  | 8    | 1000-9999 | 不受     |
| timestamp | 4    | 1970-2038 | 受       |

1. date：只保存日期
2. time：只保存时间
3. year：只保存年
4. datetime：保存日期+时间
5. timestamp：保存日期+时间

## 使用原则

1. 所选则的类型越简单越好，能保存数值的类型越小越好