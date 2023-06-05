package plugin_test.TestPlugins;

import kd.bos.bill.AbstractBillPlugIn;
import kd.bos.bill.BillOperationStatus;
import kd.bos.bill.IBillView;
import kd.fi.er.business.invoicecloud.provider.param.req.BillOperationInfo;

public class BillTest extends AbstractBillPlugIn {
    IBillView billView = (IBillView) this.getView();
    private void BillViewTest(){
        IBillView billView = (IBillView) this.getView();
    }


}
