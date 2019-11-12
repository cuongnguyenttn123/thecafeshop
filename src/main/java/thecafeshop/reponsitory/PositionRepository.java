package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Position;

public interface PositionRepository extends JpaRepository<Position, String>, JpaSpecificationExecutor<Position> {

}