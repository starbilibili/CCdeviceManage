package plugin_test.CC_device;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局变量
 */

/*
存放设备id与订单号的映射
 */
public class PublicVar {

    public Map<String,String> diviceId_BillNo = new HashMap<String,String>();
    public void init_diviceId_BillNo(){
        //测试用初始化
        diviceId_BillNo.put("devicetest","DO-202306-0006");
    }
    public void put_diviceId_BillNo(String a,String b){
        //添加元素
        diviceId_BillNo.put(a,b);
    }
    public String get_BillNo(String key){
        return diviceId_BillNo.get(key);
    }
    public void trav_diviceId_BillNo(Map<String,String> m){
        //遍历
        for(String key:m.keySet()){
            System.out.println(key + ":" + m.get(key));
        }
    }
}