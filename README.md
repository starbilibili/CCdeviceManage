# vitalchem-root 

## vitalchem-common-utils
```
src
  └─main
      └─java
          └─com
              └─vitalchem
                  └─common
                      │  GloConstKey.java
                      │
                      ├─biz
                      │      BizUtil.java
                      │
                      ├─net
                      │      HttpClientHelper.java
                      │
                      └─utils
                              BizOrgUtils.java
                              BOTPUtils.java
                              RegExpValidatorUtils.java
```

## vitalchem-sample

```
XXX
```

## vitalchem-ksapp

```
└─src
    └─main
        └─java
            └─com
                └─vitalchem
                    └─ksapp
                        ├─cardplugin
                        │      MyMsgCardPlugin.java
                        │      MyTaskCardPlugin.java
                        │
                        ├─common
                        │      CommonUtils.java
                        │      KsAppConstKey.java
                        │      KsAppSapSyncUtil.java
                        │      LevenshteinUtils.java
                        │      MessageUtils.java
                        │      PoiExcelUtils.java
                        │      SapConstKey.java
                        │      SyncResult.java
                        │
                        ├─excelplugin
                        │      CustDefaultExcelImportPlugin.java
                        │      CustFormExcelImportPlugin.java
                        │      SuppFormExcelImportPlugin.java
                        │
                        ├─formplugin
                        │      KsBdConsAreaFormPlugin.java
                        │      KsBdConsAreaListPlugin.java
                        │      KsBdConsAreaOptPlugin.java
                        │      KsBdCustDefaulFormPlugin.java
                        │      KsBdCustFormPlugin.java
                        │      KsBdCustListPlugin.java
                        │      KsBdCustOptPlugin.java
                        │      KsBdEntyFormPlugin.java
                        │      KsBdEntyInterLinkFormPlugin.java
                        │      KsBdEntyInterLinkListPlugin.java
                        │      KsBdEntyInterLinkOptPlugin.java
                        │      KsBdEntyListPlugin.java
                        │      KsBdEntyOptPlugin.java
                        │      KsBdInterCompFromPlugin.java
                        │      KsBdInterCompListPlugin.java
                        │      KsBdInterCompOptPlugin.java
                        │      KsBdOrgSapLinkFormPlugin.java
                        │      KsBdOrgSapLinkOptPlugin.java
                        │      KsBdSapClientFormPlugin.java
                        │      KsBdSapClientListPlugin.java
                        │      KsBdSapClientOptPlugin.java
                        │      KsBdSapSyncLogFormPlugin.java
                        │      KsBdSuppDefaultFormPlugin.java
                        │      KsBdSuppFormPlugin.java
                        │      KsBdSuppListPlugin.java
                        │      KsBdSuppOptPlugin.java
                        │      KsBizCreditQuotaFormPlugin.java
                        │      KsBizCustFormPlugin.java
                        │      KsBizCustListPlugin.java
                        │      KsBizCustOptPlugin.java
                        │      KsBizEntyFormPlugin.java
                        │      KsBizEntyOptPlugin.java
                        │      KsBizInterCompFormPlugin.java
                        │      KsBizInterCompListPlugin.java
                        │      KsBizSuppFormPlugin.java
                        │      KsBizSuppListPlugin.java
                        │      KsBizSuppOptPlugin.java
                        │
                        ├─mobileplugin
                        │      KsBdCustFormPlugin.java
                        │      KsBdCustListPlugin.java
                        │      KsBdEntyFormPlugin.java
                        │      KsBdEntyListPlugin.java
                        │      KsBdSuppListPlugin.java
                        │      MobileHomePlugin.java
                        │      MobileMyPagePlugin.java
                        │
                        ├─model
                        │      Customer.java
                        │      CustomerApply.java
                        │      ExcelMergeRegion.java
                        │      KsEntity.java
                        │      KsEntyValidate.java
                        │      Supplier.java
                        │      SupplierApply.java
                        │
                        ├─procplugin
                        │      KsBdCustsProcPlugin.java
                        │      KsBdEntyProcPlugin.java
                        │      KsBdSuppProcPlugin.java
                        │
                        └─task
                                KsDataToSapSyncTaskPlugin.java
```

## vitalchem-ehr

```
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─vitalchem
    │  │          └─ehr
    │  │              ├─common
    │  │              │      EhrConstants.java
    │  │              │
    │  │              ├─formplugin
    │  │              │      UserMhgBillPlugIn.java
    │  │              │
    │  │              ├─list
    │  │              │      AdminOrgListPlugin.java
    │  │              │      AdminOrgViewFormPlugin.java
    │  │              │      AdminOrgViewListPlugin.java
    │  │              │
    │  │              ├─log
    │  │              │      LogInfo.java
    │  │              │      LogTypeEnum.java
    │  │              │
    │  │              ├─operation
    │  │              │      SyncAdminOrgOperationPlugin.java
    │  │              │
    │  │              ├─print
    │  │              │      JobProofPrintPlugin.java
    │  │              │
    │  │              ├─sapws
    │  │              │  │  SapAuthentication.java
    │  │              │  │
    │  │              │  ├─aperiodicPay
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR16SEND.java
    │  │              │  │      SIHR16SENDService.java
    │  │              │  │      ZHRFMOA0267.java
    │  │              │  │      ZHRFMOA0267Response.java
    │  │              │  │      ZHRSP0267.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─bank_account
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR13SEND.java
    │  │              │  │      SIHR13SENDService.java
    │  │              │  │      ZHRFMOA0009.java
    │  │              │  │      ZHRFMOA0009Response.java
    │  │              │  │      ZHRSP0009.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─base_income
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR17SEND.java
    │  │              │  │      SIHR17SENDService.java
    │  │              │  │      ZHRFMOA0008.java
    │  │              │  │      ZHRFMOA0008Response.java
    │  │              │  │      ZHRSP0008.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─commonInfo
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR18SEND.java
    │  │              │  │      SIHR18SENDService.java
    │  │              │  │      ZHRFMOACOMM.java
    │  │              │  │      ZHRFMOACOMMResponse.java
    │  │              │  │      ZHRSP0004.java
    │  │              │  │      ZHRSP0016.java
    │  │              │  │      ZHRSP0019.java
    │  │              │  │      ZHRSP9100.java
    │  │              │  │      ZHRSP9200.java
    │  │              │  │      ZHRSP9201.java
    │  │              │  │      ZHRSP9205.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─communication
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR08SEND.java
    │  │              │  │      SIHR08SENDService.java
    │  │              │  │      ZHRFMOA0105.java
    │  │              │  │      ZHRFMOA0105Response.java
    │  │              │  │      ZHRSP0105.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─contingentPay
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR15SEND.java
    │  │              │  │      SIHR15SENDService.java
    │  │              │  │      ZHRFMOA0015.java
    │  │              │  │      ZHRFMOA0015Response.java
    │  │              │  │      ZHRSP0015.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─currentPay
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR14SEND.java
    │  │              │  │      SIHR14SENDService.java
    │  │              │  │      ZHRFMOA0014.java
    │  │              │  │      ZHRFMOA0014Response.java
    │  │              │  │      ZHRSP0014.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─education
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR06SEND.java
    │  │              │  │      SIHR06SENDService.java
    │  │              │  │      ZHRFMOA0022.java
    │  │              │  │      ZHRFMOA0022Response.java
    │  │              │  │      ZHRSP0022.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─employee_addr
    │  │              │  │      DTHRPA001REC.java
    │  │              │  │      DTHRPA001SEN.java
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHRPA001SEND.java
    │  │              │  │      SIHRPA001SENDService.java
    │  │              │  │
    │  │              │  ├─employee_base
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR02SEND.java
    │  │              │  │      SIHR02SENDService.java
    │  │              │  │      ZHRFMOAJCSJ.java
    │  │              │  │      ZHRFMOAJCSJResponse.java
    │  │              │  │      ZHRSJCSJ.java
    │  │              │  │      ZHRSJCSJ1.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─family
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR05SEND.java
    │  │              │  │      SIHR05SENDService.java
    │  │              │  │      ZHRFMOA0021.java
    │  │              │  │      ZHRFMOA0021Response.java
    │  │              │  │      ZHRSP0021.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─job
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR04SEND.java
    │  │              │  │      SIHR04SENDService.java
    │  │              │  │      ZHRFMOAPOS.java
    │  │              │  │      ZHRFMOAPOSResponse.java
    │  │              │  │      ZHRSPOS.java
    │  │              │  │
    │  │              │  ├─org
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR03SEND.java
    │  │              │  │      SIHR03SENDService.java
    │  │              │  │      ZHRFMOAORG.java
    │  │              │  │      ZHRFMOAORGResponse.java
    │  │              │  │      ZHRSORG.java
    │  │              │  │
    │  │              │  ├─rewards_punish
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHR12SEND.java
    │  │              │  │      SIHR12SENDService.java
    │  │              │  │      ZHRFMOA9207.java
    │  │              │  │      ZHRFMOA9207Response.java
    │  │              │  │      ZHRSP9207.java
    │  │              │  │      ZHRSPERNR.java
    │  │              │  │
    │  │              │  ├─test
    │  │              │  │      DTHRPA001REC.java
    │  │              │  │      DTHRPA001SEN.java
    │  │              │  │      ObjectFactory.java
    │  │              │  │      package-info.java
    │  │              │  │      SIHRPA001SEND.java
    │  │              │  │      SIHRPA001SENDService.java
    │  │              │  │
    │  │              │  └─workexpe
    │  │              │          ObjectFactory.java
    │  │              │          package-info.java
    │  │              │          SIHR07SEND.java
    │  │              │          SIHR07SENDService.java
    │  │              │          ZHRFMOA0023.java
    │  │              │          ZHRFMOA0023Response.java
    │  │              │          ZHRSP0023.java
    │  │              │          ZHRSPERNR.java
    │  │              │
    │  │              ├─task
    │  │              │      SyncAdminOrgTask.java
    │  │              │      SyncAdminUserTask.java
    │  │              │
    │  │              └─util
    │  │                      GetSapDataUtil.java
    │  │
    │  └─resources
    │          spaws-auth.properties
    │
    └─test
        ├─java
        │  └─com
        │      └─vitalchem
        │          └─ehr
        │              └─sapws
        │                      UseDemo.java
        │
        └─resources
                spaws-auth.properties
```


