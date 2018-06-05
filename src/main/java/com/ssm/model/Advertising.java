package com.ssm.model;

import com.mysql.jdbc.Blob;



public class Advertising{
	int id;
	String photoName;  //照片名称
	String photoSize;//照片尺寸
	String title;//图片标题
	String photoContent;//照片附带内容
	byte[] photoByte;    //照片详细
	String createTime;  // 创建时间
	String createBy;  //创建人
	public String getPhotoSize() {
		return photoSize;
	}
	public void setPhotoSize(String photoSize) {
		this.photoSize = photoSize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhotoContent() {
		return photoContent;
	}
	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	
	public byte[] getPhotoByte() {
		return photoByte;
	}
	public void setPhotoByte(byte[] photoByte) {
		this.photoByte = photoByte;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	
}
