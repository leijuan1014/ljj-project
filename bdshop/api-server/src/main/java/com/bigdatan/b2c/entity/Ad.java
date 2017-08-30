package com.bigdatan.b2c.entity;

//import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 广告实体类
 */
public class Ad implements Serializable {
	private static final long serialVersionUID = -4255990149866676674L;
	/**
     * 广告id，主键，自增1
     */
    private Integer adId;
    /**
     * 广告位置id
     */
    ////@MultiLanguageField
    //@Column(name = "ad_position_id")
    private Integer adPositionId;
    /**
     * 广告图像地址
     */
    ////@MultiLanguageField
    //@Column(name = "image")
    private String image;
    /**
     * 广告描述
     */
    ////@MultiLanguageField
    //@Column(name = "description")
    private String description;
    /**
     * 广告链接
     */
    ////@MultiLanguageField
    //@Column(name = "url")
    private String url;
    /**
     * 状态，1开启 2关闭
     */
    ////@MultiLanguageField
    //@Column(name = "state")
    private Integer state;

    /**
     * 删除状态 1 已删除 2 未删除
     */
    ////@MultiLanguageField
    //@Column(name = "del_state")
    private Integer delState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Integer getAdPositionId() {
		return adPositionId;
	}
	public void setAdPositionId(Integer adPositionId) {
		this.adPositionId = adPositionId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getDelState() {
		return delState;
	}
	public void setDelState(Integer delState) {
		this.delState = delState;
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
		builder.append("Ad [adId=");
		builder.append(adId);
		builder.append(", adPositionId=");
		builder.append(adPositionId);
		builder.append(", image=");
		builder.append(image);
		builder.append(", description=");
		builder.append(description);
		builder.append(", url=");
		builder.append(url);
		builder.append(", state=");
		builder.append(state);
		builder.append(", delState=");
		builder.append(delState);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append("]");
		return builder.toString();
	}
}
