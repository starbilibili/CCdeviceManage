package plugin_test.PluginEvent;

import kd.bos.dataentity.utils.StringUtils;
import kd.bos.bill.AbstractBillPlugIn;
import kd.bos.form.control.*;
import kd.bos.form.control.events.*;

import java.util.EventObject;

/**
 * registerListener用于监听各个控件事件
 */
public class registerListenerTest extends AbstractBillPlugIn implements ItemClickListener,
        ClickListener, RowClickEventListener, TreeNodeClickListener {
    private final static String KEY_MBAR = "tbmain";  //主菜单栏
    private final static String KEY_BARITEM1 = "baritem1";  //主菜单按钮
    private final static String KEY_BUTTON1 = "buttonap1";  //自定义按钮
    private final static String KEY_ENTRYENTITY = "entryentity";  //单据体行
    private final static String KEY_TREEVIEW1 = "treeviewap1";  //树形控件



    @Override
    public void registerListener(EventObject e) {
        super.registerListener(e);
        //监听主菜单栏的点击事件
        Toolbar toolbar = this.getView().getControl(KEY_MBAR);
        toolbar.addItemClickListener(this);

        //监听自定义按钮的点击事件
        Button button = this.getView().getControl(KEY_BUTTON1);
        button.addClickListener(this);

        //监听单据体行的点击事件
        EntryGrid entryGrid = this.getView().getControl(KEY_ENTRYENTITY);
        entryGrid.addRowClickListener(this);

        //监听树形控件节点点击事件
        TreeView treeView = this.getView().getControl(KEY_TREEVIEW1);
        treeView.addTreeNodeClickListener(this);
    }

    @Override
    public void itemClick(ItemClickEvent evt) {
        super.itemClick(evt);
        if(StringUtils.equals(KEY_BARITEM1,evt.getItemKey())){

        }
    }

    @Override
    public void click(EventObject evt) {
        super.click(evt);
        Control source = (Control) evt.getSource();
        if(StringUtils.equals(KEY_BUTTON1,source.getKey())){

        }
    }

    @Override
    public void entryRowClick(RowClickEvent evt) {
        RowClickEventListener.super.entryRowClick(evt);
        Control source = (Control) evt.getSource();
        if(StringUtils.equals(KEY_ENTRYENTITY,source.getKey()));
    }

    @Override
    public void treeNodeClick(TreeNodeEvent evt) {
        // 事件处理代码略过
        TreeView treeView = (TreeView)evt.getSource();
        if (StringUtils.equals(KEY_TREEVIEW1, treeView.getKey())){
            // 事件处理代码略过
        }
    }
}
