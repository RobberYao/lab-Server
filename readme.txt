# DEMO�����ο���ʵ�ʿ�������Ҫ��Ͼ���ҵ�񳡾��޸�ʹ��
#
# ���л���:JDK1.7
# ���ݿ⣺MySql5.7.16
# �������ߣ�STS3.7.2
# ʹ�ÿ�ܣ�Mybatis-3.2.3.jar
# ʹ�õ����������ࣺJRE1.7��mysql-connector-java-5.1.8
# ��Ŀ����Maven4.0

### ��Ƭ��������̨����ṹ Lab-server ###

src
������ main
    ������ java
    ��   ������ com
    ��       ������ labServer
    ��           ������ App					#������
    ��           ������ UdpServer			#Udp���������
    ��           ������ UdpServerThread		#Udp������̨�����ܳ�������������������������ʪ�ȹ���ֵ������UDP�����˿ںţ�
    ��           ��
    ��           ������ manager									#������������
    ��           ��	������ LabDisplayParamterManger.java		#��ʾ���ݱ������
    ��           ��	������	LabDisplayParamterMangerImpl.java	#��ʾ���ݱ������ʵ����
    ��           ��	������ LabDisprobeNumberManager.java		#̽ͷӳ��������
    ��           ��	������	LabDisprobeNumberManagerImpl.java	#̽ͷӳ��������ʵ����
    ��           ��	������	LabInputParamterManager.java		#ԭʼ���ݱ������
    ��           ��	������	LabInputParamterManagerImpl.java	#ԭʼ���ݱ������ʵ����
    ��           ��	������	LabModifyManager.java				#̽ͷУ׼���ù�����
    ��           ��	������	LabModifyManagerImpl.java			#̽ͷУ׼���ù�����ʵ����
    ��           ��	
    �� 			������ mapping									#�����ӿ�ӳ���
    ��           ��	������ LabDisplayParamterMapper.java		#��ʾ���ݱ�ӿ�ӳ���ࣨ�����ã�
    �� 			��	������	LabDisprobeNumberMapper.java		#̽ͷӳ���ӿ�ӳ����
    �� 			��	������	LabInputParamterMapper.java			#ԭʼ���ݱ�ӿ�ӳ����
    �� 			��	������	LabModifyMapper.java				#̽ͷУ׼���ýӿ�ӳ����
    �� 			��	������	LabDisplayParamterMapper.xml		#��ʾ���ݱ�ӿ�ӳ��xml�ļ���������ʵ�֣�
    �� 			��	������ LabInputParamterMapper.xml			#��ʾ���ݱ�ӿ�ӳ��xml�ļ���������ʵ�֣�
    �� 			��
	��			������ model								#ҵ��ģ�Ͳ�
    ��           ��	������ LabDisplayParamter.java			#��ʾ���ݱ�ģ��
    �� 			��	������ LabDisprobeNumber.java			#̽ͷӳ���ģ��	
    �� 			��	������ LabInputParamter.java			#ԭʼ���ݱ�ģ��
    �� 			��   ������ LabModify.java					#̽ͷУ׼��ģ��
    �� 			��
    �� 			������ Util
    ��     			������	DoubleUtil.java			#�������㴦������
    �� 				������	MyBatisUtil.java		#MyBatis���ô�������
    �� 				������	RegexUtil.java			#���������������
    �� 				������	SCMUtil.java			#��Ƭ�����ݴ�������
    ��     
    ������ resource
		������ conf.xml # MyBatis���ݿ����������ļ�&MyBatis��ܽӿ�ʵ��ӳ��
                  







