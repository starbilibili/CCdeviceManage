package plugin_test.CC_device;

import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class SqlEntity {
    public static DynamicObject Sql(String billNo){
        List<QFilter> qFilters = Lists.newArrayList();
        qFilters.add(new QFilter("billno",QCP.equals, billNo));
//        DynamicObject[] orderForms = BusinessDataServiceHelper.load(
//                "xzjt_device_orderform","id,billno,name",new QFilter[]{new QFilter("billno", QCP.equals, billNo)}) ;
        DynamicObject orderForm = BusinessDataServiceHelper.loadSingle(
                "xzjt_device_orderform",qFilters.toArray(new QFilter[]{})) ;

        return orderForm;
    }

}
