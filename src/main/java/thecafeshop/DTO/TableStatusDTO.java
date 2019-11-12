package thecafeshop.DTO;

import java.util.Date;
import java.util.List;



public class TableStatusDTO {

	private int tablestatusid;
	
	private String name;
	
	private Date updateat;

	private Boolean canDelete;

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public int getTablestatusid() {
		return tablestatusid;
	}

	public void setTablestatusid(int tablestatusid) {
		this.tablestatusid = tablestatusid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdateat() {
		return updateat;
	}

	public void setUpdateat(Date updateat) {
		this.updateat = updateat;
	} 
}
