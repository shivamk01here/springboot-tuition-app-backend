package com.example.tution.service;

import com.example.tution.entity.Teacher;
import com.example.tution.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Teacher Service - Business Logic Layer
 *
 * @Service marks this class as a service bean in Spring container
 * Spring will create and manage instance of this class
 *
 * Under the hood: Spring creates a singleton by default
 * One instance serves all requests (like Laravel's singleton binding)
 */
@Service
public class TeacherService {

    /**
     * Dependency Injection - Spring's IoC in action
     *
     * @Autowired tells Spring to inject TeacherRepository instance
     * Spring finds TeacherRepository bean and injects it here
     *
     * Under the hood: Spring uses reflection to inject dependencies
     * Like Laravel's constructor injection or method injection
     *
     * Alternative approaches:
     * 1. Constructor injection (recommended)
     * 2. Field injection (shown here, simpler but less testable)
     * 3. Setter injection
     */
    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Get all teachers
     *
     * @Transactional ensures database consistency
     * readOnly = true optimizes the transaction for read operations
     *
     * Under the hood: Spring creates a database transaction
     * Like Laravel's DB::transaction() but automatic
     */
    @Transactional(readOnly = true)
    public List<Teacher> getAllTeachers() {
        // findAll() is inherited from JpaRepository
        // Executes: SELECT * FROM teachers
        return teacherRepository.findAll();
    }

    /**
     * Get teacher by ID
     *
     * Optional<Teacher> handles null safety (Java 8+ feature)
     * Better than returning null (avoids NullPointerException)
     *
     * Like Laravel's findOrFail() but more explicit
     */
    @Transactional(readOnly = true)
    public Optional<Teacher> getTeacherById(Long id) {
        // findById() returns Optional<Teacher>
        // Executes: SELECT * FROM teachers WHERE id = ?
        return teacherRepository.findById(id);
    }

    /**
     * Create new teacher
     *
     * @Transactional without readOnly allows write operations
     * If exception occurs, transaction is rolled back automatically
     *
     * Under the hood: Hibernate manages the INSERT statement
     */
    @Transactional
    public Teacher createTeacher(Teacher teacher) {
        // Validate email uniqueness before saving
        if (teacherRepository.existsByEmail(teacher.getEmail())) {
            throw new RuntimeException("Email already exists: " + teacher.getEmail());
        }

        // save() method from JpaRepository
        // Executes: INSERT INTO teachers (...) VALUES (...)
        // Returns saved entity with generated ID
        return teacherRepository.save(teacher);
    }

    /**
     * Update existing teacher
     *
     * JPA's save() method works for both INSERT and UPDATE
     * If entity has ID, it performs UPDATE; otherwise INSERT
     */
    @Transactional
    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        // Find existing teacher
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        // Check email uniqueness if email is being changed
        if (!teacher.getEmail().equals(teacherDetails.getEmail()) &&
                teacherRepository.existsByEmail(teacherDetails.getEmail())) {
            throw new RuntimeException("Email already exists: " + teacherDetails.getEmail());
        }

        // Update fields - JPA will detect changes and generate UPDATE SQL
        teacher.setName(teacherDetails.getName());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setPhone(teacherDetails.getPhone());
        teacher.setSubject(teacherDetails.getSubject());
        teacher.setBio(teacherDetails.getBio());

        // save() triggers UPDATE because entity has ID
        return teacherRepository.save(teacher);
    }

    /**
     * Delete teacher by ID
     *
     * deleteById() method from JpaRepository
     * Throws exception if entity doesn't exist
     */
    @Transactional
    public void deleteTeacher(Long id) {
        // Check if teacher exists before deletion
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }

        // Executes: DELETE FROM teachers WHERE id = ?
        teacherRepository.deleteById(id);
    }

    /**
     * Search teachers by name
     * Custom business logic using repository methods
     */
    @Transactional(readOnly = true)
    public List<Teacher> searchTeachersByName(String name) {
        return teacherRepository.findByNameContaining(name);
    }

    /**
     * Get teachers by subject
     * Using custom repository method
     */
    @Transactional(readOnly = true)
    public List<Teacher> getTeachersBySubject(String subject) {
        return teacherRepository.findTeachersBySubjectOrdered(subject);
    }
}
