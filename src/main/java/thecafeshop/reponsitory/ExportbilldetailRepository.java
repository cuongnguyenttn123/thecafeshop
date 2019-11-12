package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Exportbilldetail;

public interface ExportbilldetailRepository extends JpaRepository<Exportbilldetail, Integer>, JpaSpecificationExecutor<Exportbilldetail> {

}