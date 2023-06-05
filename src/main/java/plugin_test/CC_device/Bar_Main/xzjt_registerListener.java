package plugin_test.CC_device.Bar_Main;

import kd.bos.bill.AbstractBillPlugIn;

import java.util.EventObject;

/*
单据菜单栏按钮事件监听
 */
public class xzjt_registerListener extends AbstractBillPlugIn {
    @Override
    public void registerListener(EventObject e) {
        super.registerListener(e);
        this.addItemClickListeners("tbmain");
    }
}
