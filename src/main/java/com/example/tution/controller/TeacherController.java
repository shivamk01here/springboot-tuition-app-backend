package com.example.tution.controller;

import com.example.tution.dto.TeacherRequestDto;
import com.example.tution.dto.TeacherResponseDto;
import com.example.tution.entity.Teacher;
import com.example.tution.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;        // Swagger annotation for endpoint docs
import io.swagger.v3.oas.annotations.Parameter;        // Swagger annotation to describe params
import io.swagger.v3.oas.annotations.tags.Tag;         // For grouping endpoints in UI
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST Controller for Teacher CRUD APIs
 *
 * @Tag - Groups all endpoints in Swagger UI under "Teacher"
 */
@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher", description = "Endpoints for managing teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * Helper to convert Teacher entity to DTO
     */
    private TeacherResponseDto toDto(Teacher teacher) {
        return new TeacherResponseDto(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getPhone(),
                teacher.getSubject(),
                teacher.getBio(),
                teacher.getCreatedAt(),
                teacher.getUpdatedAt()
        );
    }

    /**
     * GET /api/teachers
     * List all teachers
     */
    @Operation(
            summary = "Get all teachers",
            description = "Retrieves a list of all teachers"
    )
    @GetMapping
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherService.getAllTeachers()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * GET /api/teachers/{id}
     * Get teacher by id
     */
    @Operation(
            summary = "Get teacher by ID",
            description = "Fetch a teacher by their unique ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(
            @Parameter(description = "ID of the teacher to get") @PathVariable Long id
    ) {
        Optional<Teacher> teacherOpt = teacherService.getTeacherById(id);
        if (teacherOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(teacherOpt.get()));
    }

    /**
     * POST /api/teachers
     * Create a new teacher
     */
    @Operation(
            summary = "Create a new teacher",
            description = "Creates a teacher with name, email, phone, subject, and bio"
    )
    @PostMapping
    public ResponseEntity<TeacherResponseDto> createTeacher(
            @Valid @RequestBody TeacherRequestDto dto
    ) {
        Teacher teacher = new Teacher(
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getSubject(),
                dto.getBio()
        );
        Teacher saved = teacherService.createTeacher(teacher);
        return ResponseEntity.ok(toDto(saved));
    }

    /**
     * PUT /api/teachers/{id}
     * Update a teacher's info
     */
    @Operation(
            summary = "Update a teacher",
            description = "Update an existing teacher's details"
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(
            @Parameter(description = "ID of the teacher to update") @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDto dto
    ) {
        Teacher teacherDetails = new Teacher(
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getSubject(),
                dto.getBio()
        );
        teacherDetails.setId(id);

        try {
            Teacher updated = teacherService.updateTeacher(id, teacherDetails);
            return ResponseEntity.ok(toDto(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * DELETE /api/teachers/{id}
     * Delete a teacher
     */
    @Operation(
            summary = "Delete a teacher",
            description = "Removes a teacher by their ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(
            @Parameter(description = "ID of the teacher to delete") @PathVariable Long id
    ) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * GET /api/teachers/search
     * Search by name (partial match)
     */
    @Operation(
            summary = "Search teachers",
            description = "Searches teachers by name"
    )
    @GetMapping("/search")
    public List<TeacherResponseDto> searchTeachers(
            @Parameter(description = "Name to search (partial match)") @RequestParam String name
    ) {
        return teacherService.searchTeachersByName(name)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
