package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Tablestatus;

public interface TablestatusRepository extends JpaRepository<Tablestatus, Integer>, JpaSpecificationExecutor<Tablestatus> {

}