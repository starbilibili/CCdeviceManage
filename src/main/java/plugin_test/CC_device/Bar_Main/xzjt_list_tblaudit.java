package plugin_test.CC_device.Bar_Main;

import plugin_test.CC_device.PublicVar;
import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.vitalchem.util.StringUtil;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.entity.datamodel.ListSelectedRow;
import kd.bos.entity.datamodel.ListSelectedRowCollection;
import kd.bos.form.control.events.ItemClickEvent;
import kd.bos.form.control.events.BeforeItemClickEvent;
import kd.bos.list.BillList;
import kd.bos.list.IListView;
import kd.bos.list.plugin.AbstractListPlugin;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 * 冷链设备管理
 * 服务对象：设备订单信息表与冷链设备信息表
 * 实现功能：在设备订单信息列表中，如果一个设备处于“空闲”状态，可以进行审核，审核后修改设备状态为“任务中”
 */

public class xzjt_list_tblaudit extends AbstractListPlugin {
    @Override
    public void beforeItemClick(BeforeItemClickEvent evt) {
        super.beforeItemClick(evt);
        //获取列表界面绑定的单据标识
        String FormId = ( (IListView) this.getView()).getBillFormId();
        //获取点击按钮的类型
        String key = evt.getItemKey();
        if(StringUtils.equals(key,"tblcheck")){
            //获取单据列表控件,对的于bos_list模板单据列表，它的标识是billlistp
            BillList billList = this.getControl("billlistap");
            //获取选中行记录，得到的结果是记录的主键ID的集合
            ListSelectedRowCollection listSelectedRows = billList.getSelectedRows();
            if(listSelectedRows != null && listSelectedRows.size()>0){
                //新建一个动态对象列表用于保存更新记录
                List<DynamicObject> listSelectedRowcol = new ArrayList<DynamicObject>();
                DynamicObject tempRowData = null;
                //遍历选中记录的id集合
                for(ListSelectedRow tempRowdataId : listSelectedRows){
                    //根据主键ID获取完整的记录
                    tempRowData = BusinessDataServiceHelper.loadSingle(tempRowdataId.getPrimaryKeyValue(),FormId);
                    //如果设备处于空闲状态，修改状态为任务中，并将修改后的记录加入更新列表中
                    if(StringUtils.equals(tempRowData.getString("xzjt_basedevice.xzjt_state"),"1")){
//                        tempRowData.set("xzjt_basedevice.xzjt_state","2");
//                        listSelectedRowcol.add(tempRowData);
                        //查出基础资料做更新
                        List<QFilter> qFilters = Lists.newArrayList();
                        //设置过滤规则
                        qFilters.add(new QFilter("number", QCP.equals,
                                tempRowData.getString("xzjt_basedevice.number")));

                        DynamicObject deviceinfo = BusinessDataServiceHelper.loadSingle(
                                "xzjt_deviceinfo", qFilters.toArray(new QFilter[] {}));
                        deviceinfo.set("xzjt_state","2");
                        listSelectedRowcol.add(deviceinfo);

                        //将当前订单的订单号以及设备id存入
                        PublicVar publicVar =new PublicVar();
                        publicVar.put_diviceId_BillNo((String)tempRowData.get("xzjt_basedevice.number"),
                                (String)tempRowData.get("billno"));
                    }else{
                        this.getView().showMessage("有设备处于任务中，审核不通过!");
                        evt.setCancel(true);
                    }
                }
                if(listSelectedRowcol.size()>0){
                    //更新数据
                    Object[] result = SaveServiceHelper.save(listSelectedRowcol.toArray(new DynamicObject[]{}));
                    //刷新列表
                    ((IListView) this.getView()).refresh();
                }
            }
        }
    }
}
