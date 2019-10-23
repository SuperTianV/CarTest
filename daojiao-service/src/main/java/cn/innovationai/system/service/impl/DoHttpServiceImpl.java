package cn.innovationai.system.service.impl;

import cn.innovationai.system.domain.CarItineraryInfo;
import cn.innovationai.system.mapper.CarItineraryInfoMapper;
import cn.innovationai.system.service.ICarItineraryInfoService;
import cn.innovationai.system.service.IDoHttpService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.openapi.sdk.service.DataExchangeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 车辆行程 服务层实现
 * 
 * @author TW
 * @date 2019-10-23
 */
@Service
public class DoHttpServiceImpl  implements IDoHttpService
{

    @Override
    public String doZhiyunHttp(Map<String, String> map,String url) {
        String res = "";
        try {
            DataExchangeService des = new DataExchangeService(5000, 5000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
        return res;
    }
}
