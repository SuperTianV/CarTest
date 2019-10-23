package cn.innovationai.system.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * 车辆行程记录表 car_itinerary_record
 * 
 * @author TW
 * @date 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_itinerary_record")
public class CarItineraryRecord
{
	/** 行程ID */
    @TableId
	private Long id;
	/** 车牌号 */
	private String vclN;
	/** 行程信息ID */
	private Long itInfoId;
	/** 任务开始时间 */
	private Date stime;
	/** 任务结束时间 */
	private Date etime;
	/** 第三方行程 id */
	private String rid;
	/** 订阅预警类型
type=1 异常线路预警
type=2 异常线路预警+停
车
type=3 异常线路预警+离
线
type=4 异常线路预警+停
车+离线 */
	private Integer type;
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
