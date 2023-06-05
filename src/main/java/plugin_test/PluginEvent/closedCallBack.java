package plugin_test.PluginEvent;

import com.alibaba.druid.util.StringUtils;
import com.grapecity.documents.excel.K;
import kd.bos.form.CloseCallBack;
import kd.bos.form.FormShowParameter;
import kd.bos.form.ShowType;
import kd.bos.form.control.Control;
import kd.bos.form.events.ClosedCallBackEvent;
import kd.bos.form.plugin.AbstractFormPlugin;

import java.util.EventObject;

/**
 * closedCallBack事件：交互弹窗关闭后触发
 * 案例：用户点击"文本摘要"后弹出文本编辑界面
 * 用户在文本编辑界面可修改文本内容
 * 文本编辑界面关闭后将修改后的内容保存
 */
public class closedCallBack extends AbstractFormPlugin {
    private final static String KEY_Text1 = "xzjt_largertext1";
    private final static String KEY_Text2 = "xzjt_largertext1_tag";
    private final static String ENTITYNUMBER_LARGETEXTEDIT = "ide_largertextedit";

    @Override
    public void registerListener(EventObject e) {
        super.registerListener(e);
        this.addClickListeners(KEY_Text1);
    }

    //click事件：捕获文本编辑按钮
    @Override
    public void click(EventObject evt) {
        super.click(evt);
        Control source = (Control) evt.getSource();
        if(StringUtils.equals(KEY_Text1,source.getKey())){
            //弹出文本编辑框
            this.showLargerTextForm();
        }
    }

    @Override
    public void closedCallBack(ClosedCallBackEvent closedCallBackEvent) {
        super.closedCallBack(closedCallBackEvent);
        if(StringUtils.equals(closedCallBackEvent.getActionId(),KEY_Text1)){
            this.receiveLargerText(closedCallBackEvent);
        }
    }

    //自定义文本编辑框的参数，然后显示文本编辑框
    private void showLargerTextForm(){
        //FormShowParameter：页面打开参数，是一个参数集合
        FormShowParameter showParameter = new FormShowParameter();
        //设置打开页面的标识
        showParameter.setFormId(ENTITYNUMBER_LARGETEXTEDIT);
        showParameter.setCustomParam("fieldKey",KEY_Text1);
        showParameter.setCustomParam("entryKey",KEY_Text2);
        showParameter.getOpenStyle().setShowType(ShowType.Modal);
        //处理子界面的回调
        CloseCallBack callBack = new CloseCallBack(this, KEY_Text1);
        showParameter.setCloseCallBack(callBack);
        //显示文本编辑框
        this.getView().showForm(showParameter);
    }
    //回传文本编辑框修改内容
    private void receiveLargerText(ClosedCallBackEvent closedCallBackEvent){
        //如果取消了文本编辑，则不对原本内容做处理
        if(closedCallBackEvent.getReturnData() == null){
            return;
        }
        //更新信息
        this.getModel().setValue(KEY_Text2,closedCallBackEvent.getReturnData());
    }

}
