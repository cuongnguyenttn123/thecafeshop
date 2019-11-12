package thecafeshop.DTO;

import java.util.Date;


public class ScheduleDTO {

	private String scheduleid;
	private String starttime;
	private String endtime;
	private Float payrate;
	private Date updateat;

	private Boolean canDelete;

	public String getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Float getPayrate() {
		return payrate;
	}

	public void setPayrate(Float payrate) {
		this.payrate = payrate;
	}

	public Date getUpdateat() {
		return updateat;
	}

	public void setUpdateat(Date updateat) {
		this.updateat = updateat;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

}
