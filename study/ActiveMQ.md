# ActiveMQ

[官方下载地址](http://activemq.apache.org/download.html)

### 安装启动
- 第一步：把ActiveMQ 的压缩包上传到Linux系统
- 第二步：解压缩
- 第三步：启动
```
使用bin目录下的activemq命令启动：
[root@localhost bin]# ./activemq start
关闭：
[root@localhost bin]# ./activemq stop
查看状态：
[root@localhost bin]# ./activemq status

进入管理后台：http://IP:8161/admin
用户名：admin
密码：admin
```

### 高可用  ActiveMQ集群搭建

高可用原理:使用Zookeeper来管理activeMQ集群,一台宕机,另一台顶上

1.创建3个 zookeeper实例

2.创建3个activeMQ实例

主要就是在每隔activeMQ的配置中加入zookeeper的管理

测试: master节点服务器宕机之后,子节点是否会顶替而上 ?
		
1.每隔两秒项主节点服务器发送消息,关闭主节点服务   activemq.stop

日志上报出异常:当前主机宕机了,自动的去连接别的节点

2.但是消息依旧继续发送 通过查看发现节点2可以启动作为主节点  而且中间没有数据丢失


