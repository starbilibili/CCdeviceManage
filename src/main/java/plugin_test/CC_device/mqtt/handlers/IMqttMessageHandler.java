package plugin_test.CC_device.mqtt.handlers;

import com.alibaba.fastjson.JSON;
import plugin_test.CC_device.mqtt.messages.IMqttMessage;

import java.lang.reflect.ParameterizedType;

/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/1/9
 */
public interface IMqttMessageHandler<T extends IMqttMessage> {

    default IMqttMessage onReceive(String deviceId, String content) throws Exception {
//        T obj = JSON.parseObject(content, new TypeReference<T>(){});
        //getClass()可以获取类，getGenericInterfaces()方法获取的是一个表示类实现的接口所构成的数组
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericInterfaces()[0];

        Class<T> type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        T t = JSON.parseObject(content, type);
        return this.onReceive0(deviceId, t);
    }
    //定义一个抽象类，在EchoHandler中重写，用于打印消息发送方（设备id）与消息内容
    IMqttMessage onReceive0(String deviceId, T message) throws Exception;
}
