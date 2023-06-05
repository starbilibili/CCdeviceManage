package plugin_test.CC_device.mqtt.handlers.impl;

import plugin_test.CC_device.mqtt.annotations.MqttCmd;
import plugin_test.CC_device.mqtt.handlers.IMqttMessageHandler;
import plugin_test.CC_device.mqtt.messages.EchoMessage;
import plugin_test.CC_device.mqtt.messages.IMqttMessage;

/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/4/12
 */
@MqttCmd("echo")
public class EchoHandler implements IMqttMessageHandler<EchoMessage> {
    //打印消息发送方与消息内容
    @Override
    public IMqttMessage onReceive0(String deviceId, EchoMessage message) {
        System.out.println("收到消息，设备=" + deviceId);
        System.out.println(message.getContent());
        return null;
    }
}
