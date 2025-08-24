package com.example.tution.dto;

import java.time.LocalDateTime;

/**
 * DTO for sending Teacher data to client
 *
 * Controls which fields are sent
 */
public class TeacherResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // All-args constructor for easy use in mapping
    public TeacherResponseDto(Long id, String name, String email, String phone, String subject, String bio, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.bio = bio;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters (no setters for immutability)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getSubject() { return subject; }
    public String getBio() { return bio; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
