package thecafeshop.DAOImp;

import thecafeshop.entity.Position;

import java.util.List;


public interface PositionDAOImp extends CommonDAOImp{

    public Boolean addPosition(Position position);
    
    public List<Position> findAll();

    public List<Position> findLimit(int startPosition);
    
	public Position getInfoById(String positionid);
	
	public Position getInfoByName(String name);
	
	public Boolean deletePosition(String positionid);

	public Boolean editPosition(Position position);
	
}
