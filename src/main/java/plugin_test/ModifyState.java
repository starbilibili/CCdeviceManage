package plugin_test;

import kd.bos.bill.AbstractBillPlugIn;
import kd.bos.context.RequestContext;
import kd.bos.form.ClientProperties;
import kd.bos.servicehelper.org.OrgUnitServiceHelper;
import kd.bos.servicehelper.user.UserServiceHelper;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

public class ModifyState extends AbstractBillPlugIn {
    @Override
    public void afterCreateNewData(EventObject e) {
        String userId = RequestContext.get().getUserId();
        //查询部门&公司
        long UserMainOrgId = UserServiceHelper.getUserMainOrgId(Long.valueOf(userId));
        Map<String,Object> CompanyfromOrg = OrgUnitServiceHelper.getCompanyfromOrg(UserMainOrgId);

        this.getModel().setValue("xzjt_department","应用管理部");
        this.getModel().setValue("xzjt_company","广东先导稀材股份有限公司");
    }

    @Override
    public void afterBindData(EventObject e) {
        super.afterBindData(e);
        String billstatus = (String) this.getModel().getValue("status");
        Map<String, Object> parms = new HashMap<>();
        if ("A".equals(billstatus)) {
            parms.put(ClientProperties.ForeColor, "red");
        } else if ("B".equals(billstatus)) {
            parms.put(ClientProperties.ForeColor, "blue");
        } else {
            parms.put(ClientProperties.ForeColor, "green");
        }
        this.getView().updateControlMetadata("status",parms);
    }
}
