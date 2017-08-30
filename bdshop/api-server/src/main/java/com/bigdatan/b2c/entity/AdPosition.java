package com.bigdatan.b2c.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 广告位置实体类
 */
public class AdPosition implements Serializable {
    /**
     * 广告位置id，主键，自增1
     */
    private Integer adPositionId;
    /**
     * 广告名称
     */
    private String name;
    /**
     * 位置
     */
    private String position;
    /**
     * 尺寸
     */
    private String measure;
    /**
     * 状态，1开启 2关闭
     */
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
	public Integer getAdPositionId() {
		return adPositionId;
	}
	public void setAdPositionId(Integer adPositionId) {
		this.adPositionId = adPositionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdPosition [adPositionId=");
		builder.append(adPositionId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", position=");
		builder.append(position);
		builder.append(", measure=");
		builder.append(measure);
		builder.append(", state=");
		builder.append(state);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append("]");
		return builder.toString();
	}
}
