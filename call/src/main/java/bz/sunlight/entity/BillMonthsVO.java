package bz.sunlight.entity;

import java.util.List;

public class BillMonthsVO {

	private String year;						// 年份
	private List<BillMonth> months;				// 相关月份

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<BillMonth> getMonths() {
		return months;
	}
	public void setMonths(List<BillMonth> months) {
		this.months = months;
	}

}
