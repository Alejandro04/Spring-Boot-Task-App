package tasks.tasksapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tasks.tasksapp.persistence.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
