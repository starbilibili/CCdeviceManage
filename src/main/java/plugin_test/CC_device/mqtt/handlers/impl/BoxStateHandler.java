package plugin_test.CC_device.mqtt.handlers.impl;

import com.alibaba.druid.util.StringUtils;
import com.vitalchem.task.SysnEkpTask;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.dataentity.metadata.dynamicobject.DynamicObjectType;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;
import org.apache.commons.compress.utils.Lists;
import plugin_test.CC_device.mqtt.annotations.MqttCmd;
import plugin_test.CC_device.mqtt.handlers.IMqttMessageHandler;
import plugin_test.CC_device.mqtt.messages.ActionSuccessMsg;
import plugin_test.CC_device.mqtt.messages.BoxState;
import plugin_test.CC_device.mqtt.messages.BoxStateMsg;
import plugin_test.CC_device.mqtt.messages.IMqttMessage;
import plugin_test.CC_device.PublicVar;
import plugin_test.CC_device.SqlEntity;

import javax.swing.text.html.parser.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/4/12
 */
@MqttCmd("syncState")
public class BoxStateHandler implements IMqttMessageHandler<BoxStateMsg> {

    @Override
    public IMqttMessage onReceive0(String deviceId, BoxStateMsg message) throws Exception {
        //初始化设备号-订单号转换表：仅供测试用
        PublicVar m = new PublicVar();
        m.init_diviceId_BillNo();

        String billNo = m.get_BillNo(deviceId);
        BoxState boxstate = message.getState();
        int pos_x = boxstate.getPos_x();
        int pos_y = boxstate.getPos_y();
        int power = boxstate.getPower();
        int temperature = boxstate.getTemperature();
        String action = message.getAction();
        Date time = stringtoDate(message.getTime());
        //测试用输出
        System.out.println(billNo);System.out.println(pos_x);System.out.println(pos_y);
        System.out.println(power);System.out.println(temperature);System.out.println(action);System.out.println(time);

//        根据订单号检索单据列表
        List<QFilter> qFilters = Lists.newArrayList();
        qFilters.add(new QFilter("billno",QCP.equals, billNo));
        DynamicObject orderForm = BusinessDataServiceHelper.loadSingle(
                "xzjt_device_orderform",qFilters.toArray(new QFilter[]{})) ;
        System.out.println(orderForm.get("billno"));

        //BusinessDataServiceHelper类是提供查询、缓存查询的服务
        //BusinessDataServiceHelper.loadSingle用于加载单个实体
//        QFilter qFilter = new QFilter("billno",QCP.equals,billNo);
//        QFilter[] qFilters = new QFilter[]{qFilter};
//        DynamicObject orderForm = BusinessDataServiceHelper.loadSingle("xzjt_device_orderform",qFilters);
//        System.out.println(orderForm.get("billno"));

        //当订单存在时，在事件分录中增加一条记录
//        try{
//            //取单据体行的集合
//            DynamicObjectCollection entryEntity = orderForm.getDynamicObjectCollection("xzjt_evententryentity");
//            //获取单据体行的数据类型
//            DynamicObjectType entryEntityType = entryEntity.getDynamicObjectType();
//            //创建空的单据体行
//            DynamicObject row = new DynamicObject(entryEntityType);
//            row.set("xzjt_eventtype", action);
//            row.set("xzjt_eventlng", pos_x);
//            row.set("xzjt_eventlat", pos_y);
//            row.set("xzjt_eventtemperature", temperature);
//            row.set("xzjt_eventelectricity", power);
//            row.set("xzjt_eventtime", time);
//            entryEntity.add(row);
//            //保存修改
//            DynamicObject[] dyos = {orderForm};
//            SaveServiceHelper.save(dyos);
//        }catch (Exception e){
//        }
        ActionSuccessMsg response = new ActionSuccessMsg();
        return null;
    }

    public static Date stringtoDate(String time) throws Exception{
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        Date t;
        t=ft.parse(time);
        return t;
    }
}