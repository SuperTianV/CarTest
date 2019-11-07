package cn.innovationai.daojiao.test;

import java.util.Arrays;
import java.util.List;

import cn.innovationai.common.utils.ToolUtil;
import cn.innovationai.system.domain.CarItineraryInfo;
import cn.innovationai.system.domain.CarPositionRecord;
import cn.innovationai.system.service.ICarItineraryInfoService;
import cn.innovationai.system.service.ICarPositionRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.innovationai.common.annotation.Log;
import cn.innovationai.common.enums.BusinessType;
import cn.innovationai.system.domain.CarItineraryRecord;
import cn.innovationai.system.service.ICarItineraryRecordService;
import cn.innovationai.common.core.controller.BaseController;
import cn.innovationai.common.core.page.TableDataInfo;
import cn.innovationai.common.core.domain.AjaxResult;
import cn.innovationai.common.core.text.Convert;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * 车辆行程记录 信息操作处理
 * 
 * @author TW
 * @date 2019-10-25
 */
@Controller
@RequestMapping("/car/carItineraryRecord")
public class CarItineraryRecordController extends BaseController
{
    private String prefix = "car/carItineraryRecord";
	
	@Autowired
	private ICarItineraryRecordService carItineraryRecordService;
	@Autowired
	ICarPositionRecordService carPositionRecordService;
	@Autowired
	ICarItineraryInfoService carItineraryInfoService;
	
	@RequiresPermissions("car:carItineraryRecord:view")
	@GetMapping()
	public String carItineraryRecord()
	{
	    return prefix + "/carItineraryRecord";
	}
	
	/**
	 * 查询车辆行程记录列表
	 */
	@RequiresPermissions("car:carItineraryRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CarItineraryRecord carItineraryRecord)
	{
		startPage();
		return getDataTable(carItineraryRecordService.selectList(new EntityWrapper<>()));
	}
	
	/**
	 * 新增车辆行程记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	

	/**
	 * 修改车辆行程记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		CarItineraryRecord carItineraryRecord = carItineraryRecordService.selectById(id);
		CarItineraryInfo carItineraryInfo = carItineraryInfoService.selectById(carItineraryRecord.getItInfoId());
		List<CarPositionRecord> carPositionRecords = carPositionRecordService.selectList(new EntityWrapper<CarPositionRecord>().eq("itinerary_id", id).eq("car_no", carItineraryRecord.getCarNo()));
		mmap.put("carPositionRecords", carPositionRecords);
		mmap.put("carItineraryInfo",carItineraryInfo);
		mmap.put("carNo",carItineraryRecord.getCarNo());
		mmap.put("itineraryId",id);
	    return prefix + "/edit";
	}

	@RequestMapping("/getCarPosition")
	@ResponseBody
	public Object getCarPosition( String carNo,  Integer itineraryId,  Long utc)
	{
		List<CarPositionRecord> carPositionRecords = carPositionRecordService.selectList(new EntityWrapper<CarPositionRecord>().eq("itinerary_id", itineraryId).eq("car_no", carNo).ge(ToolUtil.isNotEmpty(utc),"utc",utc));
		return carPositionRecords;
	}
}
