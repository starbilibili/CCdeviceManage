package plugin_test.CC_device.mqtt.messages;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/4/12
 */
public class BoxStateMsg implements IMqttMessage{

    //运送单号
    private String billNo;

    //状态
    private BoxState state;

    //时间
    private String time;

    //动作类型 开始装载、结束装载、结束运送
    private String action;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BoxState getState() {
        return state;
    }

    public void setState(BoxState state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) { this.time = time; }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
