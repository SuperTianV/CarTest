package cn.innovationai.daojiao.test;

import cn.innovationai.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	/**
	 * 修改摄像头
	 */
	@GetMapping("/index")
	public String edit(ModelMap mmap)
	{
		mmap.put("camera", "1111");
	    return prefix + "/index";
	}

}
