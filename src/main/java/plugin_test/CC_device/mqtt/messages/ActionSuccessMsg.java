package plugin_test.CC_device.mqtt.messages;

/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/4/12
 */
public class ActionSuccessMsg implements IMqttMessage{

    private String success = "TRUE";//TRUE OR FALSE

    private String errMsg;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
