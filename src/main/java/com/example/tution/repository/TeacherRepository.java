package com.example.tution.repository;

import com.example.tution.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmail(String email);

    List<Teacher> findByNameContaining(String name);


    List<Teacher> findBySubjectAndNameContaining(String subject, String name);

    @Query("SELECT t FROM Teacher t WHERE t.subject = :subject ORDER BY t.name ASC")
    List<Teacher> findTeachersBySubjectOrdered(@Param("subject") String subject);

    @Query(value = "SELECT * FROM teachers WHERE created_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)", nativeQuery = true)
    List<Teacher> findRecentTeachers();

    boolean existsByEmail(String email);

}
