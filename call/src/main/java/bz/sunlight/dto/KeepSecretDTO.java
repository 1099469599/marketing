package bz.sunlight.dto;

import java.util.Date;

public class KeepSecretDTO {

	private String brandName;
	private String area;
	private String keepTime;
	private Short keepDay;
	private Date keepDate;
	private String customPropertiesId1;
	private String customPropertiesId2;
	private String customProperties3;
	private Integer currentPage;
	private Integer pageSize;
	private Integer pageItemStart;
	private String enterpriseId;
	private Short keepStatus;
	private Short status;
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public Short getKeepDay() {
		return keepDay;
	}
	public void setKeepDay(Short keepDay) {
		this.keepDay = keepDay;
	}
	public String getCustomPropertiesId1() {
		return customPropertiesId1;
	}
	public void setCustomPropertiesId1(String customPropertiesId1) {
		this.customPropertiesId1 = customPropertiesId1;
	}
	public String getCustomPropertiesId2() {
		return customPropertiesId2;
	}
	public void setCustomPropertiesId2(String customPropertiesId2) {
		this.customPropertiesId2 = customPropertiesId2;
	}
	public String getCustomProperties3() {
		return customProperties3;
	}
	public void setCustomProperties3(String customProperties3) {
		this.customProperties3 = customProperties3;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeepTime() {
		return keepTime;
	}
	public void setKeepTime(String keepTime) {
		this.keepTime = keepTime;
	}
	public Date getKeepDate() {
		return keepDate;
	}
	public void setKeepDate(Date keepDate) {
		this.keepDate = keepDate;
	}
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getPageItemStart() {
		return pageItemStart;
	}
	public void setPageItemStart(Integer pageItemStart) {
		this.pageItemStart = pageItemStart;
	}
	public Short getKeepStatus() {
		return keepStatus;
	}
	public void setKeepStatus(Short keepStatus) {
		this.keepStatus = keepStatus;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	
	
	
}
