package tasks.tasksapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.tasksapp.persistence.entity.Comment;
import tasks.tasksapp.persistence.entity.Task;
import tasks.tasksapp.persistence.entity.TaskStatus;
import tasks.tasksapp.services.TaskService;
import tasks.tasksapp.services.dto.TaskInDTO;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO) {
        return this.taskService.createTask(taskInDTO);
    }

    @GetMapping
    public List<Task> findAll(){
        return this.taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllbyStatus(@PathVariable("status") TaskStatus status){
        return this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFiniched(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{taskId}/comments")
    public List<Comment> getCommentsByTaskId(@PathVariable("taskId") Long taskId) {
        return taskService.findCommentsByTaskId(taskId);
    }

    @PostMapping("/{taskId}/comments")
    public Comment addCommentToTask(@PathVariable("taskId") Long taskId, @RequestBody Comment comment) {
        return taskService.addCommentToTask(taskId, comment);
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentFromTask(@PathVariable("taskId") Long taskId, @PathVariable("commentId") Long commentId) {
        taskService.deleteCommentFromTask(taskId, commentId);
        return ResponseEntity.noContent().build();
    }
}
