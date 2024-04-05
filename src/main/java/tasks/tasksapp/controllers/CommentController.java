package tasks.tasksapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.tasksapp.persistence.entity.Comment;
import tasks.tasksapp.persistence.entity.Task;
import tasks.tasksapp.services.CommentService;
import tasks.tasksapp.services.TaskService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService, TaskService taskService){
        this.commentService = commentService;
    }

    @PostMapping()
    public Comment addCommentToTask(@RequestBody Comment comment, @RequestBody Long taskId) {
        return commentService.addCommentToTask(taskId, comment);
    }

    @DeleteMapping("/comments/{taskId}/{commentId}")
    public ResponseEntity<Void> deleteCommentFromTask(@PathVariable("taskId") Long taskId, @PathVariable("commentId") Long commentId) {
        commentService.deleteCommentFromTask(taskId, commentId);
        return ResponseEntity.noContent().build();
    }
}
