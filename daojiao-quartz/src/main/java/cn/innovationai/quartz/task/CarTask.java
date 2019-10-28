package cn.innovationai.quartz.task;

import cn.innovationai.common.utils.DateUtils;
import cn.innovationai.common.utils.GpsUtil;
import cn.innovationai.common.utils.http.HttpUtils;
import cn.innovationai.system.domain.CarItineraryInfo;
import cn.innovationai.system.domain.CarItineraryRecord;
import cn.innovationai.system.domain.CarPositionRecord;
import cn.innovationai.system.domain.model.ZhiyunHttpRes;
import cn.innovationai.system.service.ICarItineraryInfoService;
import cn.innovationai.system.service.ICarItineraryRecordService;
import cn.innovationai.system.service.ICarPositionRecordService;
import cn.innovationai.system.service.IDoHttpService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.openapi.sdk.service.DataExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度测试
 * 
 * @author innovationai
 */
@Component("carTask")
@Slf4j
public class CarTask
{
    @Autowired
    ICarItineraryRecordService carItineraryRecordService;
    @Autowired
    IDoHttpService doHttpService;
    @Autowired
    ICarPositionRecordService carPositionRecordService;
    @Autowired
    ICarItineraryInfoService carItineraryInfoService;
    public void getCarPositionTask() {
        //获取行程中的车辆
        List<CarItineraryRecord> carItineraryRecords = carItineraryRecordService.selectList(new EntityWrapper<CarItineraryRecord>().eq("status", 0).eq("id",4));
        carItineraryRecords.forEach(carItineraryRecord -> {
            String vclN = carItineraryRecord.getCarNo();
            //获取车辆当前位置信息
            Map<String, String> map = new HashMap<String, String>(3);
            map.put("token", "980c80d0-31ea-4754-8246-2608d896e328");
            map.put("cid", "72b52c2b-927d-47a4-9269-e2a8796c5715");
            map.put("srt", "f5ba4939-5fd8-41f0-8362-bd09ddfae18e");
            map.put("vclN", vclN);
            map.put("timeNearby", "30");
            String url = "https://testopen.95155.com/save/apis/vLastLocationV3";
            System.out.println("请求地址:" + url);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
            String zhiyunHttpRes = null;
            DecimalFormat df = new DecimalFormat("0.00000000");//设置保留位数
            Date date = new Date();
            try {
                DataExchangeService des = new DataExchangeService(5000, 8000);
                zhiyunHttpRes = des.postHttps(url, map);
                ZhiyunHttpRes<CarPositionRecord> carPositionRecordZhiyunHttpRes = JSON.parseObject(zhiyunHttpRes, new TypeReference<ZhiyunHttpRes<CarPositionRecord>>() {
                });
                CarPositionRecord carPositionRecord = carPositionRecordZhiyunHttpRes.getResult();
                carPositionRecord.setItineraryId(carItineraryRecord.getId());
                String lon = df.format(carPositionRecord.getLon() / 600000);
                String lat = df.format(carPositionRecord.getLat() / 600000);
                String sendGet = HttpUtils.sendGet("https://restapi.amap.com/v3/assistant/coordinate/convert", "locations=" + lon + "," + lat + "&coordsys=gps&output=json&key=" + "da4eb0eea6c054eab013741c3e75e0db");
                JSONObject parseObject = JSON.parseObject(sendGet);
                String locations = parseObject.getString("locations");
                String[] split = locations.split(",");
                Double carLongt = Double.valueOf(split[0]);
                carPositionRecord.setLon(carLongt);
                Double carLat = Double.valueOf(split[1]);
                carPositionRecord.setLat(carLat);
                carPositionRecord.setCarNo(vclN);
                carPositionRecord.setCreateTime(date);
                //判断是否偏离
                CarItineraryInfo carItineraryInfo = carItineraryInfoService.selectById(carItineraryRecord.getItInfoId());
                String itPoints = carItineraryInfo.getItPoints();
                List<Double[]> gpsList = JSON.parseArray(itPoints, Double[].class);
                double minDis = 500;
                carPositionRecord.setStatus(1);
                long l = System.currentTimeMillis();
                for (int i = 1; i < gpsList.size(); i++) {
                        Double[] gps1 = gpsList.get(i-1);
                        Double[] gps2 = gpsList.get(i);
                    double getNearestDistance = GpsUtil.GetNearestDistance(gps1[0], gps1[1], gps2[0], gps2[1], carLongt, carLat);
                    if (minDis > getNearestDistance){
                            break;
                        }
                        if (i == gpsList.size()-1){
                            carPositionRecord.setDeviateFlag(1);
                        }
                }
                long l2 = System.currentTimeMillis();
                log.info("--------->>>>>【{}】",l2-l);
                try {
                    carPositionRecordService.insert(carPositionRecord);
                } catch (DuplicateKeyException e) {
                    log.info(vclN + "已经保存" + carPositionRecord.getUtc() + "时间信息");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    public void getCarDeviateTask() {
        List<CarPositionRecord> carPositionRecords = carPositionRecordService.selectList(new EntityWrapper<CarPositionRecord>().eq("status", 0).ge("create_time", DateUtils.formatAddMin(new Date(), -1)));
        carPositionRecords.forEach(carPositionRecord -> {
            Long itineraryId = carPositionRecord.getItineraryId();
            Double lon = carPositionRecord.getLon();
            Double lat = carPositionRecord.getLat();
            CarItineraryRecord carItineraryRecord = carItineraryRecordService.selectById(itineraryId);
            CarItineraryInfo carItineraryInfo = carItineraryInfoService.selectById(carItineraryRecord.getItInfoId());
            String itPoints = carItineraryInfo.getItPoints();
            List<Double[]> gpsList = JSON.parseArray(itPoints, Double[].class);
            double minDis = 0;
            carPositionRecord.setStatus(1);
            for (int i = 0; i < gpsList.size(); i++) {
                if (i < gpsList.size() - 1){
                    Double[] gps1 = gpsList.get(i);
                    Double[] gps2 = gpsList.get(i + 1);
                    double getNearestDistance = GpsUtil.GetNearestDistance(gps1[0], gps1[1], gps2[0], gps2[1], lon, lat);
                    if (minDis < getNearestDistance){
                        minDis = getNearestDistance;
                    }
                    if (minDis > 500){
                        carPositionRecord.setDeviateFlag(1);
                        carPositionRecordService.updateById(carPositionRecord);
                    }
                }
            }


        });
    }

}
