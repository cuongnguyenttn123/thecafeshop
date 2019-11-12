package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Billstatus;

public interface BillstatusRepository extends JpaRepository<Billstatus, String>, JpaSpecificationExecutor<Billstatus> {

}