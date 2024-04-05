package tasks.tasksapp.services;

import org.springframework.stereotype.Service;
import tasks.tasksapp.persistence.entity.Comment;
import tasks.tasksapp.persistence.entity.Task;
import tasks.tasksapp.persistence.repository.CommentRepository;
import tasks.tasksapp.persistence.repository.TaskRepository;

import java.util.Optional;

@Service
public class CommentService {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository){
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    public Comment addCommentToTask(Long taskId, Comment comment) {
        Optional<Task> optionalTask = this.taskRepository.findById(taskId);

        System.out.println(comment);
        optionalTask.ifPresent(task -> {
            comment.setTask(task);
            Comment newComment = new Comment();
            newComment.setText(comment.getText());
            newComment.setCreatedDate(comment.getCreatedDate());
            this.commentRepository.save(newComment);
        });
        return comment;
    }

    public void deleteCommentFromTask(Long taskId, Long commentId) {
        Optional<Task> optionalTask = this.taskRepository.findById(taskId);
        optionalTask.ifPresent(task -> {
            task.getComments().removeIf(comment -> comment.getId().equals(commentId));
            this.taskRepository.save(task);
        });
    }
}
