package organizacoesTabajara.crud_api_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import organizacoesTabajara.crud_api_spring.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


}
