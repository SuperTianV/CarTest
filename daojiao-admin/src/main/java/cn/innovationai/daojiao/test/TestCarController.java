package cn.innovationai.daojiao.test;

import cn.innovationai.common.core.controller.BaseController;
import cn.innovationai.common.utils.ToolUtil;
import cn.innovationai.system.domain.CarItineraryInfo;
import cn.innovationai.system.domain.CarItineraryRecord;
import cn.innovationai.system.domain.CarPositionRecord;
import cn.innovationai.system.service.ICarItineraryInfoService;
import cn.innovationai.system.service.ICarItineraryRecordService;
import cn.innovationai.system.service.ICarPositionRecordService;
import cn.innovationai.system.service.IDoHttpService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 摄像头 信息操作处理
 * 
 * @author TW
 * @date 2019-01-10
 */
@Controller
@RequestMapping("/test/testCar")
public class TestCarController extends BaseController
{
    private String prefix = "test/testCar";
	@Autowired
	ICarItineraryRecordService carItineraryRecordService;
	@Autowired
	ICarPositionRecordService carPositionRecordService;
	@Autowired
	ICarItineraryInfoService carItineraryInfoService;

	@RequestMapping("/index/{carNo}/{itineraryId}")
	public String getCarItinerary(ModelMap mmap, @PathVariable("carNo") String carNo, @PathVariable("itineraryId") Integer itineraryId)
	{
		CarItineraryRecord carItineraryRecord = carItineraryRecordService.selectById(itineraryId);
		CarItineraryInfo carItineraryInfo = carItineraryInfoService.selectById(carItineraryRecord.getItInfoId());
		List<CarPositionRecord> carPositionRecords = carPositionRecordService.selectList(new EntityWrapper<CarPositionRecord>().eq("itinerary_id", itineraryId).eq("car_no", carNo));
		mmap.put("carPositionRecords", carPositionRecords);
		mmap.put("carItineraryInfo",carItineraryInfo);
		mmap.put("carNo",carNo);
		mmap.put("itineraryId",itineraryId);

		return prefix + "/index";
	}

	@RequestMapping("/getCarPosition")
	@ResponseBody
	public Object getCarPosition( String carNo,  Integer itineraryId,  Long utc)
	{
		List<CarPositionRecord> carPositionRecords = carPositionRecordService.selectList(new EntityWrapper<CarPositionRecord>().eq("itinerary_id", itineraryId).eq("car_no", carNo).ge(ToolUtil.isNotEmpty(utc),"utc",utc));
		return carPositionRecords;
	}

}
