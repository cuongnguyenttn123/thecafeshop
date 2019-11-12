package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Importbill;

public interface ImportbillRepository extends JpaRepository<Importbill, Integer>, JpaSpecificationExecutor<Importbill> {

}