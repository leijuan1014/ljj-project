package com.xiss.util.upload;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件元数据。上传时至少应指明文件名和类型。
 * 
 * @author zhy
 *
 */
public class Metadata implements Serializable {

	private static final long serialVersionUID = -5174398352958924921L;
	
	/** 文件名。上传时填原文件名，作为文件名后缀的参考。上传的文件会生成新文件名。 */
	private String filename;
	/** HTTP Cache-Control头 */
	private String cacheControl;
	/** HTTP Content-Disposition头 */
	private String contentDisposition;
	/** HTTP Content-Encoding头 */
	private String contentEncoding;
	/** 文件过期时间（HTTP Expires头） */
	private Date expires;
	/** 文件MIME type。上传时应指明，否则默认为application/octet-stream */
	private String contentType;
	/** 文件长度。上传时无需指明 */
	private long contentLength;
	/** 文件MD5校验和。 */
	private String contentMd5;
	/** 文件最后修改时间。上传时无需指明 */
	private Date lastModified;
	/** 文件的ETag。上传后生成，实际上是文件的MD5校验和 */
	private String etag;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCacheControl() {
		return cacheControl;
	}
	public void setCacheControl(String cacheControl) {
		this.cacheControl = cacheControl;
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	public String getContentEncoding() {
		return contentEncoding;
	}
	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public String getContentMd5() {
		return contentMd5;
	}
	public void setContentMd5(String contentMd5) {
		this.contentMd5 = contentMd5;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Metadata [filename=").append(filename)
				.append(", cacheControl=").append(cacheControl)
				.append(", contentDisposition=").append(contentDisposition)
				.append(", contentEncoding=").append(contentEncoding)
				.append(", expires=").append(expires).append(", contentType=")
				.append(contentType).append(", contentLength=")
				.append(contentLength).append(", contentMd5=")
				.append(contentMd5).append(", lastModified=")
				.append(lastModified).append(", etag=").append(etag)
				.append("]");
		return builder.toString();
	}

}
