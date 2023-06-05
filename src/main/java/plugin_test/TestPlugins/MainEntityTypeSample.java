package plugin_test.TestPlugins;

import kd.bos.bill.AbstractBillPlugIn;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.dataentity.entity.LocaleString;
import kd.bos.dataentity.metadata.IDataEntityProperty;
import kd.bos.dataentity.metadata.IDataEntityType;
import kd.bos.dataentity.metadata.clr.CollectionProperty;
import kd.bos.dataentity.metadata.clr.ComplexProperty;
import kd.bos.dataentity.metadata.clr.DataEntityPropertyCollection;
import kd.bos.dataentity.metadata.clr.SimpleProperty;
import kd.bos.entity.MainEntityType;
import kd.bos.form.control.events.ItemClickEvent;
import kd.bos.form.plugin.AbstractFormPlugin;
import net.bytebuddy.dynamic.DynamicType;

import java.util.EventObject;
import java.util.Properties;

public class MainEntityTypeSample extends AbstractBillPlugIn {

    private void useMainEntityType(){
        //获取当前表单的主实体模型:getDataEntityType()
        //可通过主实体模型对象（mainEntityType）获取其各项属性值
        MainEntityType mainEntityType = this.getModel().getDataEntityType();

        //基于表单的主实体模型，创建空的数据包:createInstance()
        //数据包的数据类型为DynamicObject,实际是一个数据字典
        DynamicObject dataEntity1 = (DynamicObject) mainEntityType.createInstance();
        DynamicObject dataEntity2 = new DynamicObject(mainEntityType);

        //获取主实体部分属性值
        String entityNumber = mainEntityType.getName(); //实体标识(页面标识)
        LocaleString entityCaption = mainEntityType.getDisplayName();  //标题(页面名称)
        String PhysicalFormName = mainEntityType.getAlias();  //物理表格:动态表单此属性为空，单据、基础资料此属性为存储单据表格名
        boolean PhysicalFormFlag = mainEntityType.isDbIgnore();  //有没有关联物理表格
        String dbRouteKey = mainEntityType.getDBRouteKey();  // 分库标识
        String appId = mainEntityType.getAppId();  // 业务应用标识(页面所属应用的标识)

        //获取单据头上的字段属性
//        IDataEntityProperty billNoProp1 = mainEntityType.getProperties().get("billno");

//        if(PhysicalFormFlag){
//            this.getView().
//        }else{
//            this.getView().showErrorNotification("没有关联物理表格");
//        }
    }

    private void useDynamicObject(){
        //当前表单数据包
        DynamicObject dataEntity = this.getModel().getDataEntity(true);
        //判断数据包是从数据库加载还是全新创建的
        boolean isFormDB = dataEntity.getDataEntityState().getFromDatabase();
        //读取表单数据包中全部的字段值
        this.readPropValue(dataEntity);
    }

    private void readPropValue(DynamicObject dataEntity){
        //获取数据包对应实体模型
        IDataEntityType dType = dataEntity.getDataEntityType();

        //遍历实体模型的每一个属性
        //表单数据包主实体模型有三种属性类型：简单值（SimpleProperty）、复杂值（ComplexProperty）、集合值（CollectionProperty）
        for(IDataEntityProperty property:dType.getProperties()){
            //对于集合属性
            if(property instanceof CollectionProperty){
                //将属性关联数据包，值是数据包集合（DynamicObjectCollection）
                DynamicObjectCollection rows = dataEntity.getDynamicObjectCollection(property);
                //递归读取子实体各行属性值
                for(DynamicObject row : rows){
                    this.readPropValue(row);
                }
            }else if(property instanceof ComplexProperty){
                //对于复杂属性，关联引用的基础资料，值是另一个数据包
                DynamicObject refDataEntity = dataEntity.getDynamicObject(property);
                //递归引用基础资料属性值
                this.readPropValue(refDataEntity);
            }else{
                //对于简单属性，其本身是普通字段,直接获取属性值
                Object propValue = dataEntity.get(property);

                //输出：“属性名=属性值”
                String msg = String.format("%s=%s",property.getName(),propValue);
                System.out.println(msg);
            }
        }
    }

    public void registerListener(EventObject e) {
        super.registerListener(e);
        this.addItemClickListeners("tbmain");
    }

    @Override
    public void itemClick(ItemClickEvent evt) {
        super.itemClick(evt);
        //点击保存按钮触发
        if("bar_save".equals(evt.getItemKey())){
            useDynamicObject();
        }
    }
}
