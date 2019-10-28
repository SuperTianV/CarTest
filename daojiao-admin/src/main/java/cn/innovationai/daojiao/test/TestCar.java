package cn.innovationai.daojiao.test;

import com.openapi.sdk.service.DataExchangeService;

import java.util.HashMap;
import java.util.Map;

public class TestCar {

  /** 用户登陆
     * 用户首次调用接口，须先登录，认证通过后生成令牌。
     令牌有效期默认为 3 天，登录后之前的令牌将立即失效，多服务调用业务接口时，建议由统一服
     务调用登录接口将令牌缓存起来，多个服务统一从共享缓存中获取令牌。
     令牌失效后再调用登录接口获取令牌，避免频繁调用登录接口，建议一天内登录次数不超过 10
     次，超过 10 次将触发安全系统报警。
     * */
    public void login() {
        try {
            Map<String, String> map = new HashMap<String, String>(4);
            map.put("user", "ed6cb3e3-135b-4b94-a42f-23b6982f39c9");
            map.put("pwd", "myl7u00144023s2hd5h2i50BY5e624");
            map.put("srt", "f5ba4939-5fd8-41f0-8362-bd09ddfae18e");
            map.put("cid", "72b52c2b-927d-47a4-9269-e2a8796c5715");
            String url = "https://testopen.95155.com/save/apis/login";
            // 设置 http 读写超时
            DataExchangeService des = new DataExchangeService(5000, 8000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
    }

    /** 一、 车辆最新位置查询（车牌号）接口
     * 本接口提供指定车牌号的车辆最新位置查询。
     * */
    public static void vLastLocationV3() {
        try {
            Map<String, String> map = new HashMap<String, String>(3);
            map.put("token", "980c80d0-31ea-4754-8246-2608d896e328");
            map.put("cid", "72b52c2b-927d-47a4-9269-e2a8796c5715");
            map.put("srt", "f5ba4939-5fd8-41f0-8362-bd09ddfae18e");
            map.put("vclN", "陕YH0009");
            map.put("timeNearby", "30");
            String url = "https://testopen.95155.com/save/apis/vLastLocationV3";
            DataExchangeService des = new DataExchangeService(5000, 5000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
    }

    /** 一、 车辆最新位置查询（车牌号）接口
     * 本接口提供指定车牌号的车辆最新位置查询。
     * */
    public static void vHisTrack24() {
        try {
            Map<String, String> map = new HashMap<String, String>(3);
            map.put("token", "6212c649-c3ad-4b26-b186-e829a3ec0b53");
            map.put("cid", "72b52c2b-927d-47a4-9269-e2a8796c5715");
            map.put("srt", "f5ba4939-5fd8-41f0-8362-bd09ddfae18e");
            map.put("vclN", "陕YH0009");
            map.put("qryBtm", "2019-10-20");
            map.put("qryEtm", "2019-10-20");
            String url = "https://testopen.95155.com/save/apis/vHisTrack24";
            DataExchangeService des = new DataExchangeService(5000, 5000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
    }

    /** 二、 行驶证识别信息查询接口
     * 本接口提供行驶证识别信息查询。
     * */
    public void vehicleLicense() {
        try {
            Map<String, String> map = new HashMap<String, String>(4);
            map.put("token", "您的令牌");
            map.put("cid", "您的客户端 ID");
            map.put("srt", "您的私钥");
            // 文件路径，包含文件的扩展名
            map.put("path", "E:\\图片\\行驶证\\20190509140734.jpg");
            String url = "https://testopen.95155.com/save/apis/vehicleLicense";
            DataExchangeService des = new DataExchangeService(5000, 5000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
    }

    /** 三、 身份证识别信息查询（正、反面）
     * 本接口提供身份证识别信息查询。
     * */
    public void idCardLicense() {
        try {
            Map<String, String> map = new HashMap<String, String>(4);
            map.put("token", "您的令牌");
            map.put("cid", "您的客户端 ID");
            map.put("srt", "您的私钥");
            // 文件路径，包含文件的扩展名
            map.put("path", "E:\\图片\\身份证\\正面.jpg");
            map.put("path2", "E:\\图片\\身份证\\背面.jpg");

            String url = "https://testopen.95155.com/save/apis/idCardLicense";
            DataExchangeService des = new DataExchangeService(5000, 5000);
            System.out.println("请求地址:"+url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String res = des.postHttps(url, map);
            System.out.println("返回:"+ res);
        } catch (Exception e) {
            System.out.println("e:" + e.getMessage());
        }
    }

    public static void main(String[] args)throws Exception {
        TestCar testCar = new TestCar();
//        testCar.login();
        testCar.vLastLocationV3();
//        testCar.vHisTrack24();
// testCar.vehicleLicense();
// testCar.idCardLicense();
    }

}
