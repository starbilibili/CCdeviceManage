package plugin_test.CC_device.mqtt;

import com.alibaba.fastjson.JSON;
import org.apache.yetus.audience.InterfaceAudience;
import plugin_test.CC_device.mqtt.factory.MqttFactory;
import plugin_test.CC_device.mqtt.handlers.IMqttMessageHandler;
import plugin_test.CC_device.mqtt.messages.IMqttMessage;
import kd.bos.logging.Log;
import kd.bos.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/4/11
 */
public class MqttFacade {
    //LogFactory.getLog(Class)是一种日志管理方式
    private static Log logger = LogFactory.getLog(MqttFacade.class);

//    private static String subTopic = "mqtt/s/#";
    private static String subTopic = "mqtt/s/syncState/devicetest";
    private static int qos = 1;
    private static String broker = "tcp://10.1.200.61:1883";
//    private static String broker = "tcp://127.0.0.1:1883";
//    private static String clientId = "mqtt_test1";
    private static String clientId = "MQTT_FX_Client";
    private static String username = "admin";
    private static String password = "admin@123";

    private static MemoryPersistence persistence = new MemoryPersistence();

    private static MqttClient client;

    public static void launch(){

        try {
            //设置clientid的保存形式，persistence = new MemoryPersistence()说明是以内存形式保存
            client = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            // 清除会话：断开连接时会话自动销毁
            connOpts.setCleanSession(true);

            // 设置回调
            client.setCallback(new MsgCallback());

            // 建立连接
            client.connect(connOpts);

            // 订阅主题
            client.subscribe(subTopic);

        } catch (MqttException me) {
            logger.error("启动MQTT服务失败", me);
        }
    }

    public static void sendMsg(String topic, IMqttMessage message){
        sendMsg(topic, 1, message);
    }
    public static void sendMsg(String topic, int qos, IMqttMessage message){
        MqttMessage mm = new MqttMessage(JSON.toJSONBytes(message));
        //MQTT为保证消息准确传输，提供三种层次Qos（服务质量）
        //QoS0：至多一次，即发送方只发送一次消息，尽努力交付；
        //QoS1：至少一次，即发送方会持续重传消息直至接收方收到消息为止，由于重传接收方可能收到重复消息
        //QoS2：仅一次，保证接收方收到消息，并且过滤掉重复消息
        mm.setQos(qos);
        try{
            client.publish(topic, mm);
        }catch(Exception ex){
            logger.error("发送消息失败["+ topic + "|" + JSON.toJSONString(message) + "]", ex);
        }
    }

    private static class MsgCallback implements MqttCallback {
        public void connectionLost(Throwable cause) {
            // 连接丢失后，一般在这里面进行重连
            logger.error("连接断开，可以做重连", cause);
        }

        public void messageArrived(String topic, MqttMessage message) {
            String content = new String(message.getPayload());
            // subscribe后得到的消息会执行到这里面
            logger.info("接收消息，主题={}， 内容={}", topic, content);
            //将主题以“/”为界进行划分，正确的主题格式应当得到四段字符串
            String[] arr = topic.split("/");
            if(arr.length != 4){
                logger.warn("主题格式不对,topic={}", topic);
                return;
            }
            String cmd = arr[2]; // 动作类型
            String deviceId = arr[3];  // 设备id
            try {
                //根据动作类型来获取对象
                IMqttMessageHandler<? extends IMqttMessage> handler = MqttFactory.getHandler(cmd);
                //打印消息发送方的设备id与消息内容
                IMqttMessage response = handler.onReceive(deviceId, content);
                if(deviceId != null && response != null){
                    //若主题格式符合规定并且消息内容不为空，则将消息发布
                    MqttFacade.sendMsg("mqtt/c/" + cmd + "/" + deviceId, response);
                }
            } catch (Exception e) {
                logger.error("消息处理异常", e);
            }
        }

        public void deliveryComplete(IMqttDeliveryToken token) {
            logger.info("deliveryComplete---------" + token.isComplete());
        }
    }

}
