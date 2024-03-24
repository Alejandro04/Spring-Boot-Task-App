package tasks.tasksapp.services;

import org.springframework.stereotype.Service;
import tasks.tasksapp.mapper.TaskInDTOToTask;
import tasks.tasksapp.persistence.entity.Task;
import tasks.tasksapp.persistence.repository.TaskRepository;
import tasks.tasksapp.services.dto.TaskInDTO;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }
}
