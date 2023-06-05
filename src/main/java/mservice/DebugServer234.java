package mservice;

import kd.bos.config.client.util.ConfigUtils;
import kd.bos.service.bootstrap.Booter;

public class DebugServer234 {

    /**
     	* 测试环境
     * IP  服务地址
     * TENANT 租户编码
     * CLUSTER 集群编码
     */
	private static final String IP = "10.1.100.234";
	private static final String TENANT = "ierp";
	private static final String CLUSTER = "ierp";

	public static void main(String[] args) throws Exception {
		System.setProperty(ConfigUtils.APP_NAME_KEY, "mservice-biz1.5-cosmic");

		// 设置集群环境名称和配置服务器地址
		// 集群地址
		System.setProperty(ConfigUtils.CLUSTER_NAME_KEY, CLUSTER);
		// 租户地址
		System.setProperty("domain.tenantCode", TENANT);
		// zk 地址
		System.setProperty(ConfigUtils.CONFIG_URL_KEY, IP + ":2181");

		System.setProperty("SSO_URL", "https://kdev.vitalchem.com/sso");

		// 图片服务器,文件服务器
		System.setProperty("fileserver", "http://" + IP + ":8100/fileserver/");
//		System.setProperty("fileserver", "http://127.0.0.1:8100/fileserver/");
//		System.setProperty("attachmentServer.url", "http://127.0.0.1:8100/fileserver/");
		System.setProperty("imageServer.url", "http://" + IP + ":8100/fileserver/");
		// mc地址
		System.setProperty("mc.server.url", "http://10.1.100.234:8090/mc");
		// 本地资源地址
		System.setProperty("JETTY_WEBAPP_PATH", "E:/git_project/mservice/webapp");
		System.setProperty("JETTY_WEBRES_PATH", "E:/git_project/static-files/cosmic/webapp");
		System.setProperty("ActionConfigFile", "E:/git_project/mservice/conf/actionconfig.xml");

		// 启动本地苍穹地址
		System.setProperty("domain.contextUrl", "http://127.0.0.1:8080/ierp");

		// 默认
		System.setProperty("tenant.code.type", "config");
		System.setProperty("configAppName", "mservice,web");
		System.setProperty("webmserviceinone", "true");
		System.setProperty("file.encoding", "utf-8");
		System.setProperty("xdb.enable", "false");
		// MQ消费开关 该参数为false，本节点将不会消费mq消息
		System.setProperty("mq.consumer.register", "true");
		// mq 每个人的专属tag
		System.setProperty("mq.debug.queue.tag", "chengwen.pan");
		System.setProperty("MONITOR_HTTP_PORT", "999DistributionOpService8");
		System.setProperty("JMX_HTTP_PORT", "9091");

		// 是否在dubbo注册
		System.setProperty("dubbo.registry.register", "false");
		System.setProperty("dubbo.protocol.port", "28888");
		System.setProperty("dubbo.consumer.url", "dubbo://localhost:28888");
		System.setProperty("dubbo.consumer.url.qing", "dubbo://localhost:30880");
		System.setProperty("dubbo.service.lookup.local", "false");

		System.setProperty("appSplit", "false");
		System.setProperty("lightweightdeploy", "false");
		System.setProperty("trace.reporter.type", "");

		// 打印执行sql
		Booter.main(null);
    }

}