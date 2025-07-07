package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
    // Find all todos ordered by creation date (newest first)
    List<Todo> findAllByOrderByCreatedAtDesc();
    
    // Find only completed todos
    List<Todo> findByCompletedTrue();
    
    // Find only incomplete todos
    List<Todo> findByCompletedFalse();
    
    // Find todos by title containing a keyword (case-insensitive)
    List<Todo> findByTitleContainingIgnoreCase(String keyword);
    
    // Custom query to count completed todos
    @Query("SELECT COUNT(t) FROM Todo t WHERE t.completed = true")
    long countCompletedTodos();
    
    // Custom query to count incomplete todos
    @Query("SELECT COUNT(t) FROM Todo t WHERE t.completed = false")
    long countIncompleteTodos();
}