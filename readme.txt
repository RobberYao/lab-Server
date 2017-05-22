# DEMO仅供参考，实际开发中需要结合具体业务场景修改使用
#
# 运行环境:JDK1.7
# 数据库：MySql5.7.16
# 开发工具：STS3.7.2
# 使用框架：Mybatis-3.2.3.jar
# 使用第三方工具类：JRE1.7、mysql-connector-java-5.1.8
# 项目管理：Maven4.0

### 单片机解析后台代码结构 Lab-server ###

src
└── main
    ├── java
    │   └── com
    │       └── labServer
    │           ├── App					#测试类
    │           ├── UdpServer			#Udp服务主入口
    │           ├── UdpServerThread		#Udp解析后台代码总程序（配置批处理数量、配置温湿度过滤值、配置UDP监听端口号）
    │           │
    │           ├── manager									#解析程序管理层
    │           │	├── LabDisplayParamterManger.java		#显示数据表管理方法
    │           │	├──	LabDisplayParamterMangerImpl.java	#显示数据表管理方法实现类
    │           │	├── LabDisprobeNumberManager.java		#探头映射表管理方法
    │           │	├──	LabDisprobeNumberManagerImpl.java	#探头映射表管理方法实现类
    │           │	├──	LabInputParamterManager.java		#原始数据表管理方法
    │           │	├──	LabInputParamterManagerImpl.java	#原始数据表管理方法实现类
    │           │	├──	LabModifyManager.java				#探头校准配置管理方法
    │           │	└──	LabModifyManagerImpl.java			#探头校准配置管理方法实现类
    │           │	
    │ 			├── mapping									#解析接口映射层
    │           │	├── LabDisplayParamterMapper.java		#显示数据表接口映射类（暂弃用）
    │ 			│	├──	LabDisprobeNumberMapper.java		#探头映射表接口映射类
    │ 			│	├──	LabInputParamterMapper.java			#原始数据表接口映射类
    │ 			│	├──	LabModifyMapper.java				#探头校准配置接口映射类
    │ 			│	├──	LabDisplayParamterMapper.xml		#显示数据表接口映射xml文件（批处理实现）
    │ 			│	└── LabInputParamterMapper.xml			#显示数据表接口映射xml文件（批处理实现）
    │ 			│
	│			├── model								#业务模型层
    │           │	├── LabDisplayParamter.java			#显示数据表模型
    │ 			│	├── LabDisprobeNumber.java			#探头映射表模型	
    │ 			│	├── LabInputParamter.java			#原始数据表模型
    │ 			│   └── LabModify.java					#探头校准表模型
    │ 			│
    │ 			└── Util
    │     			├──	DoubleUtil.java			#参数计算处理工具类
    │ 				├──	MyBatisUtil.java		#MyBatis配置处理工具类
    │ 				├──	RegexUtil.java			#正则解析处理工具类
    │ 				└──	SCMUtil.java			#单片机数据处理工具类
    │     
    └── resource
		└── conf.xml # MyBatis数据库链接配置文件&MyBatis框架接口实现映射
                  







