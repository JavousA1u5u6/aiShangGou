
SolrCloud(solr 云)是Solr提供的分布式搜索方案，当你需要大规模，容错，分布式索引和检索能力时使用 SolrCloud。当一个系统的索引数据量少的时候是不需要使用SolrCloud的，当索引量很大，搜索请求并发很高，这时需要使用SolrCloud来满足这些需求。
 SolrCloud是基于Solr和Zookeeper的分布式搜索方案，它的主要思想是使用Zookeeper作为集群的配置信息中心。
它有几个特色功能：
1）集中式的配置信息
2）自动容错
3）近实时搜索
4）查询时自动负载均衡



物理结构

	三个Solr实例（ 每个实例包括两个Core），组成一个SolrCloud。

逻辑结构
	
	索引集合包括两个Shard（shard1和shard2），shard1和shard2分别由三个Core组成，其中一个Leader两个Replication，Leader是由zookeeper选举产生，zookeeper控制每个shard上三个Core的索引数据一致，解决高可用问题。
用户发起索引请求分别从shard1和shard2上获取，解决高并发问题。


Core
每个Core是Solr中一个独立运行单位，提供 索引和搜索服务。一个shard需要由一个Core或多个Core组成。由于collection由多个shard组成所以collection一般由多个core组成。

Master或Slave
Master是master-slave结构中的主结点（通常说主服务器），Slave是master-slave结构中的从结点（通常说从服务器或备服务器）。同一个Shard下master和slave存储的数据是一致的，这是为了达到高可用目的。

Shard
Collection的逻辑分片。每个Shard被化成一个或者多个replication，通过选举确定哪个是Leader。

 ### solr集群架构 

Zookeeper作为集群的管理工具。
1、集群管理：容错、负载均衡。
2、配置文件的集中管理
3、集群的入口

需要实现zookeeper 高可用。需要搭建集群。建议是奇数节点。需要三个zookeeper服务器。
(因为zookeeper内部也需要实现高可用,依赖于投票选举制度,所以必须是奇数台)

搭建solr集群需要7台服务器。

搭建伪分布式：
需要三个zookeeper节点
需要四个tomcat节点。

建议虚拟机的内容1G以上。

环境准备
    CentOS-6.5-i386-bin-DVD1.iso
	jdk-7u72-linux-i586.tar.gz
    apache-tomcat-7.0.47.tar.gz
    zookeeper-3.4.6.tar.gz
    solr-4.10.3.tgz
修改配置信息
/usr/local/solr-cloud/zookeeper01/conf/zoo.cfg

server.1=192.168.149.133:2881:3881
server.2=192.168.149.133:2882:3882
server.3=192.168.149.133:2883:3883

dataDir=/usr/local/solr-cloud/zookeeper01/data


1)准备四台Tomcat
  A.修改port
  B.部署solr服务:拷贝单机版的solr服务

2)准备solrHome目录
  A.修改solr.xml中的slorcloud 配置所关联的tomcat的ip + port

3)配置
  A.配置web.xml,配置solrHome的目录
  B.上传配置文件到zookeeper(zookeeper是启动状态)
	需要上传的配置文件:solrHome/collection1/conf
	使用  :solr的目录下/example/cloud-scripts/zkcli.sh
	上传的指令:zkcli.sh   -zkhost  -cmd 
