package plugin_test.CC_device.mqtt;

import plugin_test.CC_device.mqtt.MqttFacade;
import plugin_test.CC_device.mqtt.factory.MqttFactory;
import kd.bos.framework.lifecycle.appstart.AppStarter;

/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/4/12
 */
public class MqttStarter implements AppStarter {
    @Override
    public void start() {
        //注册处理器
        MqttFactory.register();;
        //启动MQTT消息监听
        MqttFacade.launch();
    }
}
