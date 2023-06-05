package plugin_test.PluginEvent;


import com.alibaba.druid.util.StringUtils;
import kd.bos.dataentity.OperateOption;
import kd.bos.dataentity.RefObject;
import kd.bos.form.ConfirmCallBackListener;
import kd.bos.form.ConfirmTypes;
import kd.bos.form.MessageBoxOptions;
import kd.bos.form.MessageBoxResult;
import kd.bos.form.control.events.BeforeClickEvent;
import kd.bos.form.events.BeforeDoOperationEventArgs;
import kd.bos.form.events.MessageBoxClosedEvent;
import kd.bos.form.operate.FormOperate;
import kd.bos.form.plugin.AbstractFormPlugin;

import java.util.EventObject;

/**
 * beforeDoOperation事件：是由表单执行某个操作前触发
 * 案例：用户点击“付款”按钮时，在付款这个操作执行之前，先触发beforeDoOperation事件，
 *      取消付款操作，并弹出提示框，当用户确认付款后再重新执行付款操作
 *
 *  confirmCallBack事件：用户确认交互信息后触发
 *  案例：当用户确认付款后再重新执行付款操作
 *
 *  实现过程：不能忘记监听；自定义操作的操作编码要与按钮标识一致
 */
public class beforeDoOperationTest extends AbstractFormPlugin {
    private final static String KEY_PAY = "xzjt_pay";  //付款按钮
    private final static String OPPARAM_AFTERCONFIRM = "afterconfirm";  //


    @Override
    public void registerListener(EventObject e) {
        super.registerListener(e);
        this.addClickListeners(KEY_PAY);
    }

//    @Override
////    public void click(EventObject evt) {
////        super.click(evt);
////        this.getView().showMessage("付款成功！");
////    }

    @Override
    public void beforeDoOperation(BeforeDoOperationEventArgs args) {

        super.beforeDoOperation(args);
        //捕获表单操作，表单操作的数据类型是FormOperate
        FormOperate operate = (FormOperate) args.getSource();
        //付款操作会触发相关操作：弹出确认提示框
        //需注意：operate.getOperateKey()得到的是操作编码而不是按钮标识
        if(StringUtils.equals(KEY_PAY,operate.getOperateKey())){

            RefObject<String> afterConfirm = new RefObject<>();
            //通过getOption读取自定义操作参数
            //tryGetVariableValue:尝试获取用户在“确认提示框”的交互变量值
            //在用户刚点击“付款”按钮，还未在提示框中确认时，获取的变量值afterConfirm为空，满足if条件,弹出提示框
            //用户确认之后，afterConfirm不为空，不会再次触发if语句
            if(!operate.getOption().tryGetVariableValue(OPPARAM_AFTERCONFIRM,afterConfirm)){
                //监听用户与提示框交互事件
                ConfirmCallBackListener confirmCallBackListener = new
                        ConfirmCallBackListener(KEY_PAY,this);
                //设计提示框
                String confirmTip = "您确认要付款给xxx?";
                this.getView().showConfirm(confirmTip, MessageBoxOptions.YesNo,
                        ConfirmTypes.Default,confirmCallBackListener);
                //未得到确认前先取消付款操作
                args.setCancel(true);
            }
        }
    }

    @Override
    public void confirmCallBack(MessageBoxClosedEvent messageBoxClosedEvent) {
        super.confirmCallBack(messageBoxClosedEvent);
        //如果交互事件由付款按钮触发
        if(StringUtils.equals(KEY_PAY,messageBoxClosedEvent.getCallBackId())){
            //如果用户确认付款
            if(messageBoxClosedEvent.getResult() == MessageBoxResult.Yes){
                //构建自定义操作参数（OperateOption）
                OperateOption operateOption = OperateOption.create();
                //将付款操作的afterconfirm参数提前设置为true，避免再次执行beforeDoOperation事件时再次弹出提示框
                operateOption.setVariableValue(OPPARAM_AFTERCONFIRM,"true");
                //执行付款操作：按照OperateOption规定的参数来执行KEY_PAY操作
                this.getView().invokeOperation(KEY_PAY,operateOption);
            }
        }
    }
}
