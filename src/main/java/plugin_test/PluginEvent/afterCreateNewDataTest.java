package plugin_test.PluginEvent;

import kd.bos.form.plugin.AbstractFormPlugin;

import java.util.EventObject;

/**
 * afterCreateNewData
 * 测试页面：xzjt_lccmtest
 */
public class afterCreateNewDataTest extends AbstractFormPlugin {
    private final static String KEY_ENTRYENTITY = "xzjt_eet_aftercreatend";
    private final static String KEY_INTEGERFIELD1 = "xzjt_integerfieldap1";

    @Override
    public void afterCreateNewData(EventObject e) {
        super.afterCreateNewData(e);
        // 获取单据体行数
        int rowCount = this.getModel().getEntryRowCount(KEY_ENTRYENTITY);
        // 行数小于10时补全
        if(rowCount<10){
            this.getModel().batchCreateNewEntryRow(KEY_ENTRYENTITY,10-rowCount);
            rowCount = 10;
        }

        // 逐行为整数字段设置默认值
        for(int row=0;row<rowCount;row++){
            int fIdValue = row+1;
            this.getModel().setValue(KEY_INTEGERFIELD1,fIdValue,row);
        }
    }
}
