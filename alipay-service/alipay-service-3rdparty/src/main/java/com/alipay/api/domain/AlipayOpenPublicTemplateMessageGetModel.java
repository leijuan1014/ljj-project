package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * 模板消息领取接口
 *
 * @author auto create
 * @since 1.0, 2016-12-02 16:32:06
 */
public class AlipayOpenPublicTemplateMessageGetModel extends AlipayObject {

	private static final long serialVersionUID = 7573549178529845414L;

	/**
	 * 消息母板id，登陆生活号后台(fuwu.alipay.com)，点击菜单“模板消息”，点击“模板库”，即可看到相应模板的消息母板id
	 */
	@ApiField("template_id")
	private String templateId;

	public String getTemplateId() {
		return this.templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}
