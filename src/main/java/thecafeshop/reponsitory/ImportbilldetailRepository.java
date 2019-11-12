package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Importbilldetail;

public interface ImportbilldetailRepository extends JpaRepository<Importbilldetail, Integer>, JpaSpecificationExecutor<Importbilldetail> {

}