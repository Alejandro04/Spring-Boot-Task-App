package tasks.tasksapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tasks.tasksapp.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}