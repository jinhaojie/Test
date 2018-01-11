#本demo是创建kafka的topic，并且产生何消费消息

# 在本地安装kafka
下载 kafka_2.11-1.0.0
1. 启动zookeeper
cd进入kafka解压目录，输入
bin/zookeeper-server-start.sh config/zookeeper.properties



2. 启动kafka
cd进入kafka解压目录，输入
bin/kafka-server-start.sh config/server.properties


# 创建topic
执行CreateTopic
# produce msg
执行 MessageProducer
# consumer msg
执行MessageConsumer

