package thecafeshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import thecafeshop.entity.Atposition;

public interface AtpositionRepository extends JpaRepository<Atposition, String>, JpaSpecificationExecutor<Atposition> {

}