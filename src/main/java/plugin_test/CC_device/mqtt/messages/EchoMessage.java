package plugin_test.CC_device.mqtt.messages;

/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/4/12
 */
//获取消息内容
public class EchoMessage implements IMqttMessage{
//ImqttMessage接口的具体实现
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
