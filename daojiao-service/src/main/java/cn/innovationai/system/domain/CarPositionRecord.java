package cn.innovationai.system.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * 车辆位置记录表 car_position_record
 * 
 * @author TW
 * @date 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_position_record")
public class CarPositionRecord
{
	/** ID */
    @TableId
	private Long id;
	/** 车牌号 */
	private String vclN;
	/** 行程 */
	private Long itineraryId;
	/** 车辆定位纬度 */
	private Double lat;
	/** 车辆定位经度 */
	private Double lon;
	/** 车辆地理位置名称 */
	private String adr;
	/** 车辆定位时间 */
	private Long utc;
	/** 速度 */
	private Double spd;
	/** 方向 */
	private Integer drc;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 县 */
	private String country;
	/** 里程(km) */
	private Double mil;
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
