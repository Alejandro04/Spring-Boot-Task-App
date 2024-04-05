package tasks.tasksapp.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tasks.tasksapp.mapper.TaskInDTOToTask;
import tasks.tasksapp.persistence.entity.Comment;
import tasks.tasksapp.persistence.entity.Task;
import tasks.tasksapp.persistence.entity.TaskStatus;
import tasks.tasksapp.persistence.repository.CommentRepository;
import tasks.tasksapp.persistence.repository.TaskRepository;
import tasks.tasksapp.services.dto.TaskInDTO;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;
    private final CommentRepository commentRepository;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper, CommentRepository commentRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.commentRepository = commentRepository;
    }

    public List<Task> findAll() {
        return this.repository.findAll();
    }

    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }
    public List<Task> findAllByTaskStatus(TaskStatus status) {
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new EntityNotFoundException("Task with id " + id + " not found");
        }

        this.repository.markTaskAsFinished(id);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new EntityNotFoundException("Task with id " + id + " not found");
        }

        this.repository.deleteById(id);
    }

    public List<Comment> findCommentsByTaskId(Long taskId) {
        Optional<Task> optionalTask = this.repository.findById(taskId);
        return optionalTask.map(Task::getComments).orElse(null);
    }
}
