package com.team11.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.team11.dao.CourseRepository;
import com.team11.dao.DepartmentRepository;
import com.team11.dao.FacultyRepository;
import com.team11.dao.StudentRepository;
import com.team11.entity.Course;
import com.team11.entity.Department;
import com.team11.entity.Faculty;
import com.team11.entity.Level;
import com.team11.entity.Semester;
import com.team11.entity.Student;
import com.team11.service.CourseService;
import com.team11.service.DepartmentService;
import com.team11.service.FacultyService;
import com.team11.service.StudentService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringmvcdemoApplicationTests {
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired 
	private CourseService courseService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private StudentService studentService;

	@MockBean
	private DepartmentRepository departmentRepository;
	@MockBean 
	private CourseRepository courseRepository;
	@MockBean
	private FacultyRepository facultyRepository;
	@MockBean
	private StudentRepository StudentRepository;
	
	// *** TEST DEPARTMENT SERVICE ***
		// Retrieve all departments
		@Test
		public void getDepartmentsTest() {
			when(departmentRepository.findAll()).thenReturn(
					Stream.of(new Department("Mathematics"), new Department("Physics")).collect(Collectors.toList()));
			assertEquals(2, departmentService.getDepartments().size());
		}
		
		// Test save function
		@Test
		public void saveDepartmentTest() {
			Department department = new Department("Economics");
			when(departmentRepository.save(department)).thenReturn(department);
			assertEquals(department, departmentService.saveDepartment(department));
		}
		
	// *** TEST COURSE SERVICE ***
		// Retrieve all courses
		@Test
		public void getCoursesTest() {
			when(courseRepository.findAll()).
			thenReturn(Stream.of(
					new Course("ABC", "Math201", 5, 1, new Date(), new Date()),
					new Course("DEF", "Math301", 5, 1, new Date(), new Date()))
					.collect(Collectors.toList()));
			assertEquals(2, courseService.getCourses().size());
		}
		
		// Test save function
		@Test
		public void saveCourseTest() {
			Course course = new Course("ABC", "Math201", 5, 1, new Date(), new Date());
			when(courseRepository.save(course)).thenReturn(course);
			assertEquals(course, courseService.saveCourse(course));
		}
		
	// *** TEST FACULTY SERVICE ***
		// Retrieve all faculty
		@Test
		public void getAllFacultyTest() {
			when(facultyRepository.findAll()).thenReturn(
					Stream.of(
							new Faculty("1", "Peter", "Parker", 2),
							new Faculty("2", "Mary", "Jane", 2))
					.collect(Collectors.toList()));
			assertEquals(2, facultyService.getFaculties().size());
		}
		
		// Test save faculty
		@Test
		public void saveFacultyTest() {
			Faculty faculty = new Faculty("1", "Peter", "Parker", 2);
			when(facultyRepository.save(faculty)).thenReturn(faculty);
			assertEquals(faculty, facultyService.saveFaculty(faculty));
		}
		
	// *** TEST FACULTY SERVICE ***	
		//Retrieve all students
		@Test
		public void getAllStudentTest() {
			when(StudentRepository.findAll()).thenReturn(
					Stream.of(
							new Student("Hein", "Htet", "Male", new Date(), "Chemistry", "", "", new Level(), new Semester()),
							new Student("Ming", "Xi", "Female", new Date(), "Physics", "", "", new Level(), new Semester()))
					.collect(Collectors.toList()));
			assertEquals(2, studentService.getStudents().size());
		}
		
		//Test save student
		@Test
		public void saveStudentTest() {
			Student student = new Student("Hein", "Htet", "Male", new Date(), "Chemistry", "", "", new Level(), new Semester());
			when(StudentRepository.save(student)).thenReturn(student);
			assertEquals(student, studentService.saveStudent(student));
		}
		
}
