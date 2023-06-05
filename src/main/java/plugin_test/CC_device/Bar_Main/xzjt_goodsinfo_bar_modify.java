package plugin_test.CC_device.Bar_Main;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import kd.bos.bill.AbstractBillPlugIn;
import kd.bos.bill.IBillView;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.form.control.events.BeforeItemClickEvent;
import kd.bos.list.IListView;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;

import javax.security.auth.message.config.RegistrationListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class xzjt_goodsinfo_bar_modify extends AbstractBillPlugIn {
    //监听菜单栏点击事件
    @Override
    public void registerListener(EventObject e) {
        super.registerListener(e);
        this.addItemClickListeners("tbmain");
    }

    @Override
    public void beforeItemClick(BeforeItemClickEvent evt) {
        super.beforeItemClick(evt);
        // 识别“提交”按钮
        if(StringUtils.equals("bar_submit",evt.getItemKey())){
            DynamicObject goodNew = this.getModel().getDataEntity();
            // 获取货物名称属性值
//            String goodId = (String)this.getModel().getValue("xzjt_goodsid");
            String goodId = (String) goodNew.get("xzjt_goodsid");
            // 设置过滤条件：查询货物编码是否已存在
            List<QFilter> qFilters = Lists.newArrayList();
            qFilters.add(new QFilter("xzjt_goodsid", QCP.equals,goodId));
            DynamicObject goodinfo = BusinessDataServiceHelper.loadSingle(
                    "xzjt_goodsinfo",qFilters.toArray(new QFilter[] {}));
            // 货物已存在，直接累积数量，不新建记录
            if(goodinfo != null){
                goodinfo.set("xzjt_goodsnum",goodinfo.getBigDecimal("xzjt_goodsnum")
                        .add(goodNew.getBigDecimal("xzjt_goodsnum")));
                //取消提交事件
                evt.setCancel(true);
                this.getView().showMessage("该货物已存在，已更新库存数量。");
                //更新数据
                SaveServiceHelper.update(goodinfo);
                //刷新页面
//                this.getView().invokeOperation("bar_new");
            }
        }

    }
}
