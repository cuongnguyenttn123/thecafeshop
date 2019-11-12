package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Materialdetail;

public interface MaterialdetailRepository extends JpaRepository<Materialdetail, Integer>, JpaSpecificationExecutor<Materialdetail> {

}