package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    // Home page - show all todos
    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) String filter) {
        List<Todo> todos;
        String activeFilter = "all";
        
        // Apply filter
        if ("completed".equals(filter)) {
            todos = todoService.getCompletedTodos();
            activeFilter = "completed";
        } else if ("incomplete".equals(filter)) {
            todos = todoService.getIncompleteTodos();
            activeFilter = "incomplete";
        } else {
            todos = todoService.getAllTodos();
        }
        
        // Get statistics
        TodoService.TodoStats stats = todoService.getTodoStats();
        
        // Add data to model (this gets sent to the HTML template)
        model.addAttribute("todos", todos);
        model.addAttribute("activeFilter", activeFilter);
        model.addAttribute("stats", stats);
        model.addAttribute("newTodo", new Todo()); // For the form
        
        return "index"; // This refers to src/main/resources/templates/index.html
    }
    
    // Create a new todo
    @PostMapping("/todos")
    public String createTodo(@ModelAttribute Todo todo, RedirectAttributes redirectAttributes) {
        try {
            todoService.createTodo(todo.getTitle(), todo.getDescription());
            redirectAttributes.addFlashAttribute("successMessage", "Todo created successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }
    
    // Toggle todo completion
    @PostMapping("/todos/{id}/toggle")
    public String toggleTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Todo updatedTodo = todoService.toggleTodoCompletion(id);
            String status = updatedTodo.isCompleted() ? "completed" : "incomplete";
            redirectAttributes.addFlashAttribute("successMessage", 
                "Todo marked as " + status + "!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }
    
    // Delete a todo
    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = todoService.deleteTodo(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Todo deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Todo not found!");
        }
        return "redirect:/";
    }
    
    // Show edit form
    @GetMapping("/todos/{id}/edit")
    public String editTodoForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Todo> todoOptional = todoService.getTodoById(id);
        
        if (todoOptional.isPresent()) {
            model.addAttribute("todo", todoOptional.get());
            return "edit"; // This refers to src/main/resources/templates/edit.html
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Todo not found!");
            return "redirect:/";
        }
    }
    
    // Update an existing todo
    @PostMapping("/todos/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute Todo todo, 
                           RedirectAttributes redirectAttributes) {
        try {
            todoService.updateTodo(id, todo.getTitle(), todo.getDescription());
            redirectAttributes.addFlashAttribute("successMessage", "Todo updated successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }
    
    // Search todos
    @GetMapping("/search")
    public String searchTodos(@RequestParam String keyword, Model model) {
        List<Todo> todos = todoService.searchTodos(keyword);
        TodoService.TodoStats stats = todoService.getTodoStats();
        
        model.addAttribute("todos", todos);
        model.addAttribute("searchKeyword", keyword);
        model.addAttribute("stats", stats);
        model.addAttribute("newTodo", new Todo());
        
        return "index";
    }
    
    // API endpoint to get todo statistics (JSON response)
    @GetMapping("/api/stats")
    @ResponseBody
    public TodoService.TodoStats getStats() {
        return todoService.getTodoStats();
    }
}