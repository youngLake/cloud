package com.young.edge.cloud.commen.constant;

import java.util.HashMap;

/**
 * @author Tornado Young
 * @date time 2020/3/2 21:30
 */
public class RSP extends HashMap<String,Object> {
    public final static String SUCCESS_CODE="1";
    public final static String FAIL_CODE="2";
    public final static String SUCCESS="SUCCESS";
    public final static String FAIL="FAIL";
    /**
     *  失败返回
     * @param returnCode
     * @param returnStatus
     * @param returnMsg
     * @return
     */
    public static RSP error(String returnCode,String returnStatus,String returnMsg) {
        RSP r = new RSP();
        r.put("returnCode", FAIL_CODE);
        r.put("returnStatus", FAIL);
        r.put("returnMsg", returnMsg);
        return r;
    }


    /**
     *  成功返回
     * @param data
     * @return
     */
    public static RSP ok(String returnMsg,Object data) {
        RSP r = new RSP();
        r.put("returnCode", SUCCESS_CODE);
        r.put("returnStatus", SUCCESS);
        r.put("returnMsg", SUCCESS);
        r.put("data", data);
        return r;
    }
}
