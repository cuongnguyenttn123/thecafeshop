package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Booktable;

public interface BooktableRepository extends JpaRepository<Booktable, String>, JpaSpecificationExecutor<Booktable> {

}