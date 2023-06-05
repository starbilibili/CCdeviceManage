package mservice;

import java.nio.file.Files;
import java.nio.file.Paths;

import kd.bos.config.client.util.ConfigUtils;
import kd.bos.service.bootstrap.Booter;
import kd.bos.db.DB;
import kd.bos.db.SqlLogger;
import plugin_test.CC_device.mqtt.MqttStarter;

/**
 * 开发环境
 * @author qiming.jiang
 * <p>
 * IP  服务地址
 * TENANT 租户编码
 * CLUSTER 集群编码
 * </p>
 */
public class DebugServerDev {

    
	private static final String ZK_ADDRESS = "10.1.100.234:2181";
	private static final String MC_ADDRESS = "http://10.1.100.234:8090/mc";
	private static final String MQ_ADDRESS = "http://10.1.100.234:8090/mq";
	private static final String TENANT = "ierp";
	private static final String CLUSTER = "ierp";
//	private static final String WEBAPP_PATH = "F:/projects/kd_cosmic-v4.0.009.0";
//	private static final String WEBRES_PATH = "F:/projects/kd_webapp-v4.0.009.0";
	private static final String WEBAPP_PATH = "E:/git_project/kd_mservice";
	private static final String WEBRES_PATH = "E:/git_project/kd_webapp";
	
//	private static final String WEBAPP_PATH = "F:/projects/vitalchem/mservice";
//	private static final String WEBRES_PATH = "F:/projects/vitalchem/webapp";

	
	
	public static void main(String[] args) throws Exception {
		
		//SpringBoot测试
//		System.setProperty("mservice.booter.type","springboot");
//		System.setProperty("springboot.enable","true");
//		System.setProperty("MONITOR_HTTP_HOST_IP", "10.1.100.234");
//		System.setProperty("MONITOR_HTTP_HOST_PORT", "8090");
//		System.setProperty("MONITOR_HTTP_PORT", "8090");
		
		
		
		System.setProperty(ConfigUtils.APP_NAME_KEY, "mservice-xdev1");// 设置集群环境名称和配置服务器地址
		System.setProperty(ConfigUtils.CLUSTER_NAME_KEY, CLUSTER);// 集群地址
		System.setProperty(ConfigUtils.CONFIG_URL_KEY, ZK_ADDRESS);// zk 地址	
		System.setProperty("mc.server.url", MC_ADDRESS);// mc地址
		System.setProperty("domain.contextUrl", "http://127.0.0.1:8080/ierp");// 启动本地苍穹地址
		System.setProperty("domain.tenantCode", TENANT);// 租户地址	
		System.setProperty("lightweightdeploy", "false");//环境以非轻量级启动，使用正式的redis和mq		
		System.setProperty("appSplit", "false");
		System.setProperty("trace.reporter.type", "");
		System.setProperty("tenant.code.type", "config");
		System.setProperty("configAppName", "mservice,web");
		System.setProperty("webmserviceinone", "true");
		System.setProperty("file.encoding", "utf-8");
		System.setProperty("script.debug.enable", "true");
		System.setProperty("db.error.throw.showExceptionMode", "debug");//DB异常错误信息输出模式    debug:输出sql详情
		System.setProperty("sys.productmodelsetting.enable", "false");
		
		// 本地资源地址
//		System.setProperty("JETTY_WEBAPP_PATH", "F:/projects/vitalchem/mservice/webapp");
//		System.setProperty("JETTY_WEBRES_PATH", "F:/projects/vitalchem/webapp");
//		System.setProperty("ActionConfigFile", "F:/projects/vitalchem/mservice/conf/actionconfig.xml");
		System.setProperty("JETTY_WEBAPP_PATH", WEBAPP_PATH+"/webapp");
		System.setProperty("JETTY_WEBRES_PATH", WEBRES_PATH);
		System.setProperty("ActionConfigFile", WEBAPP_PATH+"/conf/actionconfig.xml");

		//MQ配置
		System.setProperty("mqConfigFiles.config","/workflow_messagecenter_service.xml");
		System.setProperty("mq.consumer.register", "true");// MQ消费开关 该参数为false，本节点将不会消费mq消息
		System.setProperty("mq.debug.queue.tag", "lcy1");// mq 每个人的专属tag
		System.setProperty("JMX_HTTP_PORT", "9091");
		
		//监控
		System.setProperty("MONITOR_HTTP_PORT", "999DistributionOpService8");
		

		// 是否在dubbo注册
		//System.setProperty("dubbo.registry.address","localhost");
//		System.setProperty("dubbo.registry.register", "true");
//		System.setProperty("dubbo.service.lookup.local", "false");//远程调用
		System.setProperty("dubbo.registry.register", "false");
		System.setProperty("dubbo.service.lookup.local", "true");//本地调用
		System.setProperty("dubbo.protocol.port", "28888");
		System.setProperty("dubbo.consumer.url", "dubbo://localhost:28888");
		System.setProperty("dubbo.consumer.url.qing", "dubbo://localhost:30880");
		System.setProperty("dubbo.config-center.timeout","30000");
		//System.setProperty("dubbo.protocol.dispatche","30000");
		
		// 图片服务器,文件服务器
		System.setProperty("fileserver", "http://127.0.0.1:8100/fileserver/");//文件服务器
		System.setProperty("imageServer.url", "http://127.0.0.1:8100/fileserver/");//
		System.setProperty("attachmentServer.url", "http://127.0.0.1:8100/fileserver/");//
//		System.setProperty("fileserver", "http://10.1.100.234:8100/fileserver/");//文件服务器
//		System.setProperty("imageServer.url", "http://10.1.100.234:8100/fileserver/");//
//		System.setProperty("attachmentServer.url", "http://10.1.100.234:8100/fileserver/");//
//		System.setProperty("fileserver", "https://kdev.vitalchem.com/fileserver/");//文件服务器
//        System.setProperty("imageServer.url", "https://kdev.vitalchem.com/fileserver/");//
//        System.setProperty("attachmentServer.url", "https://kdev.vitalchem.com/fileserver/");//
		
		System.setProperty("xdb.enable", "false");
//		System.setProperty("db.sql.out", "true");
//		DB.setSqlLogger(new SqlLogger() {
////			@Override
////			public void log(String sql, Object... arg1) {
////				System.out.println("log输出:"+sql);
////			}
//
//			@Override
//			public void logMessage(String sql) {
//				// TODO Auto-generated method stub
//				System.out.println("logMessage输出:"+sql);
//			}
//
//			@Override
//			public void logSQL(String sql, Object... arg1) {
//				// TODO Auto-generated method stub
//				//System.out.println("logSQL输出:"+sql);
//			}
//		});
		
		//
		//kd.bos.mq.init.MQInit.init();
		//kd.bos.mq.config.UsageConfig.get().getRegions().forEach();

		new MqttStarter().start(); // 配置mqtt启动服务


		// 自定义日志
    StringBuffer log = new StringBuffer();
    Files.readAllLines(Paths.get(DebugServerDev.class.getResource("/logback.xml").toURI())).forEach(s -> log.append(s + "\n"));
    System.setProperty("log.config", log.toString());
		
		// 打印执行sql
		Booter.main(null);
		
	
    }

}