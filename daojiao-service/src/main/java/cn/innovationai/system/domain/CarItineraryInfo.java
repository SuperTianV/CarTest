package cn.innovationai.system.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * 车辆行程表 car_itinerary_info
 * 
 * @author TW
 * @date 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_itinerary_info")
public class CarItineraryInfo
{
	/** 行程信息ID */
    @TableId
	private Long id;
	/** 起始地坐标 */
	private String startCoor;
	/** 起始地坐标 */
	private String name;
	/** 目的地坐标 */
	private String destCoor;
	/** 起始地名称 */
	private String startAdr;
	/** 目的地名称 */
	private String destAdr;
	/** 路程经过点 */
	private String itPoints;
	/** 订阅预警类型
	/** 帐号状态（0正常 1停用） */
	private Integer status;
	/** 删除标志（0代表存在 2代表删除） */
	private Integer delFlag;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;
}
