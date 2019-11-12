package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Register;

public interface RegisterRepository extends JpaRepository<Register, String>, JpaSpecificationExecutor<Register> {

}