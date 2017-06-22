package bz.sunlight.dto;

import java.util.Date;

public class AdminInfoDTO {

	private String id;

    
    private String userName;

    
    private String password;

    
    private Short status;

    
    private Date createTime;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Short getStatus() {
		return status;
	}


	public void setStatus(Short status) {
		this.status = status;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
