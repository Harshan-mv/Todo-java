package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;
    
    // Get all todos (newest first)
    public List<Todo> getAllTodos() {
        return todoRepository.findAllByOrderByCreatedAtDesc();
    }
    
    // Get todo by ID
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    
    // Create a new todo
    public Todo createTodo(String title, String description) {
        // Validate input
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Todo title cannot be empty");
        }
        
        Todo todo = new Todo(title.trim(), description != null ? description.trim() : "");
        return todoRepository.save(todo);
    }
    
    // Update an existing todo
    public Todo updateTodo(Long id, String title, String description) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            
            // Validate title
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Todo title cannot be empty");
            }
            
            todo.setTitle(title.trim());
            todo.setDescription(description != null ? description.trim() : "");
            todo.setUpdatedAt(LocalDateTime.now());
            
            return todoRepository.save(todo);
        } else {
            throw new IllegalArgumentException("Todo not found with id: " + id);
        }
    }
    
    // Toggle todo completion status
    public Todo toggleTodoCompletion(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setCompleted(!todo.isCompleted());
            todo.setUpdatedAt(LocalDateTime.now());
            return todoRepository.save(todo);
        } else {
            throw new IllegalArgumentException("Todo not found with id: " + id);
        }
    }
    
    // Delete a todo
    public boolean deleteTodo(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    // Get completed todos only
    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompletedTrue();
    }
    
    // Get incomplete todos only
    public List<Todo> getIncompleteTodos() {
        return todoRepository.findByCompletedFalse();
    }
    
    // Search todos by title
    public List<Todo> searchTodos(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllTodos();
        }
        return todoRepository.findByTitleContainingIgnoreCase(keyword.trim());
    }
    
    // Get todo statistics
    public TodoStats getTodoStats() {
        long totalTodos = todoRepository.count();
        long completedTodos = todoRepository.countCompletedTodos();
        long incompleteTodos = todoRepository.countIncompleteTodos();
        
        return new TodoStats(totalTodos, completedTodos, incompleteTodos);
    }
    
    // Inner class for statistics
    public static class TodoStats {
        private long totalTodos;
        private long completedTodos;
        private long incompleteTodos;
        
        public TodoStats(long totalTodos, long completedTodos, long incompleteTodos) {
            this.totalTodos = totalTodos;
            this.completedTodos = completedTodos;
            this.incompleteTodos = incompleteTodos;
        }
        
        // Getters
        public long getTotalTodos() { return totalTodos; }
        public long getCompletedTodos() { return completedTodos; }
        public long getIncompleteTodos() { return incompleteTodos; }
        
        public double getCompletionPercentage() {
            if (totalTodos == 0) return 0.0;
            return (double) completedTodos / totalTodos * 100;
        }
    }
}