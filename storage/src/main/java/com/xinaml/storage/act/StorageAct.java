package com.xinaml.storage.act;

import com.xinaml.storage.entity.Storage;
import com.xinaml.storage.ser.StorageSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 上午8:56]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestController
public class StorageAct {
    @Autowired
    private StorageSer storageSer;

    @GetMapping("get")
    public Storage get(String name) {
        return storageSer.get(name);
    }

    @RequestMapping("timeout")
    public String timeout(){
        try{
            //睡2秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "timeout";
    }
}
