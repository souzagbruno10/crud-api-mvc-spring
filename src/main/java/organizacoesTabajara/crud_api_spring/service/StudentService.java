package organizacoesTabajara.crud_api_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import organizacoesTabajara.crud_api_spring.model.Student;
import organizacoesTabajara.crud_api_spring.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student){
        studentRepository.save(student);

    }

    public List<Student> listStudent(){
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Long id){
        return studentRepository.findById(id);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }


}
