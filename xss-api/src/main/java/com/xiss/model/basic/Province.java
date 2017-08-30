package com.xiss.model.basic;
/**
 * Created with Intellij IDEA.
 * User: wesley
 * Date: 2015/4/16
 * Time: 18:00
 */
public class Province {
    private Integer id;

    private String provinceid;

    private String province;
    
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}