package plugin_test.CC_device.Bar_Main;

import kd.bos.bill.AbstractBillPlugIn;
import kd.bos.form.control.Toolbar;
import kd.bos.form.control.events.BeforeItemClickEvent;

import java.util.EventObject;

/*
冷链设备信息单据页面
主菜单审核按钮
对于空闲设备，审核后修改设备状态为“任务中”
 */
public class xzjt_bill_bar_audit extends AbstractBillPlugIn {
    //监听菜单栏按钮事件
    public void registerListener(EventObject e) {
        super.registerListener(e);
        // 方案一
        this.addItemClickListeners("tbmain");

        // 方案二
//        Toolbar toolbar = this.getView().getControl(("tbmain"));
//        toolbar.addItemClickListener(this);

    }

    /*
    点击事件前判断事件类型
    对审核事件执行制定的click相关操作
     */
    @Override
    public void beforeItemClick(BeforeItemClickEvent evt) {
        super.beforeItemClick(evt);
        //判断是否是审核事件
        if("bar_audit".equals(evt.getItemKey())){
            //判断设备状态是否空闲
            String deviceState = (String)this.getModel().getValue("xzjt_state");
            if("1".equals(deviceState)){
                //设备空闲时审核成功，并修改设备状态为“任务中”
                this.getModel().setValue("xzjt_state","2");
            }else{
                //设备不空闲时审核失败
                this.getView().showErrorNotification("审核失败，当前设备不可用。");
                evt.setCancel(true);
            }
        }
    }
}
