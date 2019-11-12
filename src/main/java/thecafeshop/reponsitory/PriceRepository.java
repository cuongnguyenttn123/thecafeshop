package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Integer>, JpaSpecificationExecutor<Price> {

}