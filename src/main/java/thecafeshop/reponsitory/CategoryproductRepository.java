package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Categoryproduct;

public interface CategoryproductRepository extends JpaRepository<Categoryproduct, String>, JpaSpecificationExecutor<Categoryproduct> {

}