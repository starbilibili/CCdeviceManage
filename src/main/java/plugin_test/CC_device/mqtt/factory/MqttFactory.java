package plugin_test.CC_device.mqtt.factory;

import com.google.common.collect.Maps;
import plugin_test.CC_device.mqtt.annotations.MqttCmd;
import plugin_test.CC_device.mqtt.handlers.IMqttMessageHandler;
import plugin_test.CC_device.mqtt.messages.IMqttMessage;
import com.vitalchem.util.StringUtil;
import org.reflections.Reflections;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author kimyong
 * @since 2023/4/11
 */
public class MqttFactory {

    private static final Map<String, IMqttMessageHandler> handlers = Maps.newConcurrentMap();

    public static void register() {
        //扫描指定路径的包
        Reflections reflections = new Reflections("plugin_test.CC_device.mqtt.handlers");
        //根据父类IMqttMessageHandler查询所有子类
        Set<Class<? extends IMqttMessageHandler>> classes = reflections.getSubTypesOf(IMqttMessageHandler.class);
        //将子类设计成一个迭代器对象
        Iterator<Class<? extends IMqttMessageHandler>> iterator = classes.iterator();
        //当迭代器中指针所指元素的下一个元素不为空时
        while (iterator.hasNext()) {
            //令指针指向下一个元素
            Class<? extends IMqttMessageHandler> next = iterator.next();
            //获取next所指类的注解
            MqttCmd cmd = next.getDeclaredAnnotation(MqttCmd.class);
            if (cmd == null) {
                continue;
            }
            String cmdStr = cmd.value();
            if (StringUtil.isEmpty(cmdStr)) {
                continue;
            }

            try{
                //newInstance()方法是使用类的无参构造函数来创建实例，此处是创建next所指类的实例
                IMqttMessageHandler handler = next.newInstance();
                //创建一个map键值对，键是动作类型，值是一个IMqttMessageHandler类型的对象
                handlers.put(cmdStr, handler);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    //以cmd作为键来获取对应的值
    public static IMqttMessageHandler<? extends IMqttMessage> getHandler(String cmd){
        return handlers.get(cmd);
    }
}
