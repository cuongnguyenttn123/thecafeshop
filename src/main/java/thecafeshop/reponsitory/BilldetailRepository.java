package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Billdetail;

public interface BilldetailRepository extends JpaRepository<Billdetail, Integer>, JpaSpecificationExecutor<Billdetail> {

}