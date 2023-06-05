package plugin_test.PluginEvent.ListEvent;

import kd.bos.filter.FilterContainer;
import kd.bos.form.events.FilterContainerInitArgs;
import kd.bos.form.field.events.BeforeF7SelectEvent;
import kd.bos.form.field.events.BeforeF7SelectListener;
import kd.bos.list.plugin.AbstractListPlugin;

import java.util.EventObject;

/**
 * filterContainerInit事件：初始化过滤面板时触发
 * filterContainerSearchClick事件：搜索按钮点击事件
 * setFilter事件：列表过滤条件生成后触发
 * filterContainerBeforeF7Select：
 * 案例：
 */


public class filterContainerInitTest extends AbstractListPlugin implements BeforeF7SelectListener {
    /** 缓存标识 */
    private final static String CACHEKEY_ORGCOMBOITEMS = "orgcomboitems";
    /** 组织主实体标识 */
    private final static String ORG_ENTITY = "bos_org";
    /** 用户是否点击了过滤面板搜索按钮 */
    private boolean isClickSearch = false;
    /** 筛选组织：优先按用户在过滤面板中，设置的组织筛选数据；列表初始化时，按当前组织筛选数据 */
    private long orgId = 0;

    @Override
    public void registerListener(EventObject e) {
        super.registerListener(e);
        // 侦听过滤面板上，过滤字段F7点击事件：设置自定义组织过滤字段，F7打开的基础资料列表
        FilterContainer filterContainer = this.getView().getControl(FILTERCONTAINERID);
//        filterContainer.addBeforeF7SelectListener(this);
    }

    /**
     * 1. 界面初始化时，触发此事件；
     * 2. 用户在过滤面板上，点击搜索时，也触发此事件
     * @param args
     * @remark
     * 1. 给常用过滤面板，增加组织过滤字段
     * 2. 取默认过滤组织
     */
    @Override
    public void filterContainerInit(FilterContainerInitArgs args) {
        super.filterContainerInit(args);

    }


    @Override
    public void beforeF7Select(BeforeF7SelectEvent beforeF7SelectEvent) {

    }
}
