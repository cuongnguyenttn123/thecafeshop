package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Dinnertable;

public interface DinnertableRepository extends JpaRepository<Dinnertable, Integer>, JpaSpecificationExecutor<Dinnertable> {

}