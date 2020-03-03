package com.young.edge.cloud.controller;

import com.young.edge.cloud.commen.constant.RSP;
import com.young.edge.cloud.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tornodo Young
 * @date 2020/3/3 11:18
 */
@RestController
public class BaseInfoController extends ParentContrller {

    @Autowired
    private BaseInfoService baseInfoService;

    @RequestMapping(value = "/basicCount",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RSP basicCount(){
        try {
            return ok(baseInfoService.basicCount());
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }
}
