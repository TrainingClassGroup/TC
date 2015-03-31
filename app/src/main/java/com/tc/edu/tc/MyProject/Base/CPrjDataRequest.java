package com.tc.edu.tc.MyProject.Base;

import com.tc.edu.tc.MyBase.CDataRequest;

/**
 * Created by Administrator on 15-3-31.
 */
public class CPrjDataRequest extends CDataRequest {

    public CPrjDataRequest( String function) {
        //super("http://115.28.76.20/tc/web/index.php?r=my/data");
        super("http://192.168.1.5/tc2/WebService/web/index.php?r=my/data");

        getParams().put("data", function);
    }

}
