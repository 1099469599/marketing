package bz.sunlight.entity;

public class ADIDRequest {
	
	
	private String copId;
	
	private String sopId;

	private String timeStamp;

	private String correlateID;
	
	private String contentMngTXTURL;
	
	public String getCopId() {
		return copId;
	}
	public void setCopId(String copId) {
		this.copId = copId;
	}
	public String getSopId() {
		return sopId;
	}
	public void setSopId(String sopId) {
		this.sopId = sopId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCorrelateID() {
		return correlateID;
	}
	public void setCorrelateID(String correlateID) {
		this.correlateID = correlateID;
	}
	public String getContentMngTXTURL() {
		return contentMngTXTURL;
	}
	public void setContentMngTXTURL(String contentMngTXTURL) {
		this.contentMngTXTURL = contentMngTXTURL;
	}
	
	@Override
	public String toString() {
		return "copId:"+copId+", sopId:"+sopId+", timeStamp:"+timeStamp+", correlateID:"+correlateID+", contentMngTXTURL:"+contentMngTXTURL;
	}
}
