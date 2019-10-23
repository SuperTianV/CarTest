package cn.innovationai.system.service;

import cn.innovationai.system.domain.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * 参数配置 服务层
 * 
 * @author innovationai
 */

public interface IDoHttpService
{
    String doZhiyunHttp(Map<String, String> map,String url);
}
