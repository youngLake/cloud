package com.young.edge.cloud.controller;

import com.young.edge.cloud.commen.constant.RSP;
import com.young.edge.cloud.commen.constant.SystemConstant;

/**
 * @author Tornodo Young
 * @date 2020/3/3 11:18
 */
public class ParentContrller {
    public RSP ok(Object data){
        return RSP.ok(null,data);
    }
    public RSP err(){
        return RSP.error(RSP.FAIL_CODE,RSP.FAIL, SystemConstant.ERROR_MSG_COMMEN);
    }
}
