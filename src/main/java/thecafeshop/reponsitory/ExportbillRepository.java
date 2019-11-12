package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Exportbill;

public interface ExportbillRepository extends JpaRepository<Exportbill, Integer>, JpaSpecificationExecutor<Exportbill> {

}