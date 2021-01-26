# 前言

自己软件工程实训写的一个项目

模拟ATM自动取款机

包括存款、取款、转账(分为行内和跨行)、登录、修改密码、查询交易信息、打印交易凭条功能

涉及知识：设计模式、网络编程、JDBC、I/O流、SWING、Mysql等。

架构：C/S模式(客户端和服务端)

开发工具：idea、Navicat、IReport(用于打印凭条)

开发语言：Java (Jdk为java13)

数据库：Mysql(8.0.22)

操作系统：Win10

# ATM系统需求

ATM自动动取款机是银行在银行营业大厅、超市、商业机构、机场、车站、码头和闹市区设置的一种小型机器，利用一张信用卡大小的胶卡上的磁带〔或芯片卡上的芯片〕记录客户的基本户口资料，让客户可以透过机器进行提款、存款、转帐等银行柜台服务

1） 客户将银行卡插入读卡器，读卡器识别卡的真伪，并在显示器上提示输入密码。

2） 客户通过键盘输入密码，取款机验证密码是否有效。如果密码错误提示错误信息，如果正确，提示客户进行选择操作的业务。

3） 客户根据自己的需要可进行存款、取款、查询账户、转账、修改密码等操作。

4） 在客户选择后显示器进行交互提示和操作确认等信息。

5） 操作完毕之后，客户可自行选择打印或不打印凭条。

# ATM系统设计

系统流程和主要的功能

![](https://picture.hs-vae.com/ATM系统主要流程.png)

系统类图

![](https://picture.hs-vae.com/图片1.png)

登录时序图

![image-20210126231644395](https://picture.hs-vae.com/image-20210126231644395.png)

存款时序图

![image-20210126231607915](https://picture.hs-vae.com/image-20210126231607915.png)

取款时序图

![image-20210126231617684](https://picture.hs-vae.com/image-20210126231617684.png)

查询账户

![image-20210126231631561](https://picture.hs-vae.com/image-20210126231631561.png)

行内转账时序图

![image-20210126231702835](https://picture.hs-vae.com/image-20210126231702835.png)

跨行转账时序图

![image-20210126231714763](https://picture.hs-vae.com/image-20210126231714763.png)

# 数据库设计

先建立一个atm数据库

再建立三个表

用户信息表t_customer_info

字段包括：卡号、确认编号、用户名字、密码、余额、创建时间、开卡的银行、状态、保存类型

![image-20210127000107483](https://picture.hs-vae.com/用户信息表.png)

日志表t_log_info

字段包括：日志id、日志信息、日志类型、日志时间、操作人员、日志状态

![image-20210126223549711](https://picture.hs-vae.com/日志表.png)

交易记录表t_record_info

字段包括：交易id、交易时间、交易金额、用户名字、卡号、交易类型、余额

![image-20210126223607880](https://picture.hs-vae.com/交易记录表.png)

# 凭条设计

转账、存款、取款的凭条

![](https://picture.hs-vae.com/单挑记录.png)

交易信息的凭条

![](https://picture.hs-vae.com/交易凭条.png)

# ATM系统实现

登陆界面

![](https://picture.hs-vae.com/登录界面.png)

登陆成功后进入主界面

![](https://picture.hs-vae.com/主界面1.png)

存款界面(演示打印凭条功能，其他功能就不再演示打印凭条功能)

![image-20210126234714372](https://picture.hs-vae.com/存款界面.png)

存款成功后提示是否打印凭条

![](https://picture.hs-vae.com/是否打印凭条.png)

选择保存的路径

![](https://picture.hs-vae.com/选择保存路径.png)

凭条已生成到指定的位置(桌面)

![](https://picture.hs-vae.com/生成凭条成功.png)

查看生成的凭条(注意这里的银行的logo是我自己本地的，你们要使用的话要重新编译jrml文件，图片选择你自己本地的logo，最后生成jasper文件，放在项目的jasper文件夹里)

![](https://picture.hs-vae.com/存款凭条.png)

取款界面

![](https://picture.hs-vae.com/取款界面.png)

转账界面

![](https://picture.hs-vae.com/转账界面.png)

交易信息界面

![](https://picture.hs-vae.com/交易信息.png)

操作后的日志信息表和交易记录表

![](https://picture.hs-vae.com/交易表更新.png)

![](https://picture.hs-vae.com/日志表更新.png)

