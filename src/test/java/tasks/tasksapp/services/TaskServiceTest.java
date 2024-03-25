package tasks.tasksapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tasks.tasksapp.mapper.TaskInDTOToTask;
import tasks.tasksapp.persistence.entity.Task;
import tasks.tasksapp.persistence.entity.TaskStatus;
import tasks.tasksapp.persistence.repository.TaskRepository;
import tasks.tasksapp.services.dto.TaskInDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskInDTOToTask mapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void findAll() {
        // Arrange
        List<Task> expectedTasks = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(expectedTasks);

        // Act
        List<Task> actualTasks = taskService.findAll();

        // Assert
        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void createTask() {
        // Arrange
        TaskInDTO taskInDTO = new TaskInDTO();
        Task mappedTask = new Task();
        when(mapper.map(taskInDTO)).thenReturn(mappedTask);
        when(taskRepository.save(mappedTask)).thenReturn(mappedTask);

        // Act
        Task createdTask = taskService.createTask(taskInDTO);

        // Assert
        assertNotNull(createdTask);
        assertEquals(mappedTask, createdTask);
        verify(mapper, times(1)).map(taskInDTO);
        verify(taskRepository, times(1)).save(mappedTask);
    }

    @Test
    void findAllByTaskStatus() {
        // Arrange
        TaskStatus status = TaskStatus.ON_TIME;
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findAllByTaskStatus(status)).thenReturn(tasks);

        // Act
        List<Task> foundTasks = taskService.findAllByTaskStatus(status);

        // Assert
        assertEquals(tasks, foundTasks);
        verify(taskRepository, times(1)).findAllByTaskStatus(status);
    }

    @Test
    void updateTaskAsFinished() {
        // Arrange
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        Optional<Task> optionalTask = Optional.of(task);
        when(taskRepository.findById(id)).thenReturn(optionalTask);

        // Act
        taskService.updateTaskAsFinished(id);

        // Obtener la tarea actualizada desde el repositorio
        Task updatedTask = optionalTask.get();

        // Assert
        assertTrue(updatedTask.isFinished());
        verify(taskRepository, times(1)).findById(id);
        verify(taskRepository, times(1)).markTaskAsFinished(id);
    }

    @Test
    void deleteById() {
        // Arrange
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        Optional<Task> optionalTask = Optional.of(task);
        when(taskRepository.findById(id)).thenReturn(optionalTask);

        // Act
        taskService.deleteById(id);

        // Assert
        verify(taskRepository, times(1)).findById(id);
        verify(taskRepository, times(1)).deleteById(id);
    }
}