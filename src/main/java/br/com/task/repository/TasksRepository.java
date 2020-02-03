package br.com.task.repository;

import br.com.task.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Long> {

    @Query(value = "select * from task where status = ?1", nativeQuery = true)
    List<Task> findAllByStatusType(String statusCode);

    List<Task> findAllByIsActive(Boolean isActive);
}
