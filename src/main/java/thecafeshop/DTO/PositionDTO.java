package thecafeshop.DTO;


import thecafeshop.entity.Position;

public class PositionDTO {

	private Position position;

	private Boolean canDelete;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

}
