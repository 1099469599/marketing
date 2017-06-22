package bz.sunlight.entity;

public class BillVO {
	
	private Double callPrice;			// 通话单价
	private Integer callQuantity;		// 通话次数
	private Double callSubtotal;		// 通话小计
	private Double keepPrice;			// 保留单价
	private Integer keepDay;			// 保留人天
	private Double keepSubtotal;		// 保留小计
	private Double seatPrice;			// 坐席单价
	private Integer seatQuantity;		// 坐席次数
	private Double seatSubtotal;		// 坐席小计
	private Double baseCost;			// 基础费用
	private Double totalAmount;			// 费用合计
	private Integer baseCallQuantity;	// 免费通话次数
	private Integer baseSeatQuantity;	// 免费坐席数

	public Double getCallPrice() {
		return callPrice;
	}
	public void setCallPrice(Double callPrice) {
		this.callPrice = callPrice;
	}
	public Integer getCallQuantity() {
		return callQuantity;
	}
	public void setCallQuantity(Integer callQuantity) {
		this.callQuantity = callQuantity;
	}
	public Double getCallSubtotal() {
		return callSubtotal;
	}
	public void setCallSubtotal(Double callSubtotal) {
		this.callSubtotal = callSubtotal;
	}
	public Double getKeepPrice() {
		return keepPrice;
	}
	public void setKeepPrice(Double keepPrice) {
		this.keepPrice = keepPrice;
	}
	public Integer getKeepDay() {
		return keepDay;
	}
	public void setKeepDay(Integer keepDay) {
		this.keepDay = keepDay;
	}
	public Double getKeepSubtotal() {
		return keepSubtotal;
	}
	public void setKeepSubtotal(Double keepSubtotal) {
		this.keepSubtotal = keepSubtotal;
	}
	public Double getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(Double seatPrice) {
		this.seatPrice = seatPrice;
	}
	public Integer getSeatQuantity() {
		return seatQuantity;
	}
	public void setSeatQuantity(Integer seatQuantity) {
		this.seatQuantity = seatQuantity;
	}
	public Double getSeatSubtotal() {
		return seatSubtotal;
	}
	public void setSeatSubtotal(Double seatSubtotal) {
		this.seatSubtotal = seatSubtotal;
	}
	public Double getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getBaseCallQuantity() {
		return baseCallQuantity;
	}
	public void setBaseCallQuantity(Integer baseCallQuantity) {
		this.baseCallQuantity = baseCallQuantity;
	}
	public Integer getBaseSeatQuantity() {
		return baseSeatQuantity;
	}
	public void setBaseSeatQuantity(Integer baseSeatQuantity) {
		this.baseSeatQuantity = baseSeatQuantity;
	}

}
