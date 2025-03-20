package organizacoesTabajara.crud_api_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import organizacoesTabajara.crud_api_spring.model.Student;
import organizacoesTabajara.crud_api_spring.service.StudentService;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping("/new")
    public String showForm(Model model) {
        // Create a new Student object and add it to the model
        model.addAttribute("student", new Student());
        return "studentForm"; // studentForm.html is assumed to be the template name
    }

    @GetMapping("/list")
    public String listStudents (Model model, Student student){
        model.addAttribute("students", studentService.listStudent());
        return "list";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            // Retorne uma mensagem de erro ou redirecione para o formulário com um erro
            return "redirect:/students/new?error=email_required";
        }
        studentService.saveStudent(student);
        return "redirect:/students/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Student> studentOptional = studentService.findStudentById(id);

        if (studentOptional.isEmpty()) {
            return "redirect:/students/list?error=student_not_found";
        }

        model.addAttribute("student", studentOptional.get());
        return "edit"; // O nome do arquivo Thymeleaf deve ser "edit.html"
    }


    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute Student student) {
        Optional<Student> existingStudentOptional = studentService.findStudentById(id);

        if (existingStudentOptional.isEmpty()) {
            return "redirect:/students/list?error=student_not_found";
        }

        Student existingStudent = existingStudentOptional.get();  // Acessa o aluno real do Optional

        // Atualiza os dados do aluno
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());


        // Salva o aluno atualizado
        studentService.saveStudent(existingStudent);

        return "redirect:/students/list"; // Corrigindo o redirecionamento para o caminho correto
    }

    }




