package com.xiss.model.basic;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 * User: wesley
 * Date: 2015/4/16
 * Time: 18:00
 */
public class City implements Serializable{
	private static final long serialVersionUID = 7188462040956204753L;

	private Integer id;

    private String cityid;

    private String city;

    private String father;
    
    private boolean active;
    
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father == null ? null : father.trim();
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("City [id=");
		builder.append(id);
		builder.append(", cityid=");
		builder.append(cityid);
		builder.append(", city=");
		builder.append(city);
		builder.append(", father=");
		builder.append(father);
		builder.append(", active=");
		builder.append(active);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}