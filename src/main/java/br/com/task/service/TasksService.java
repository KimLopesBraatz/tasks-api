package br.com.task.service;

import br.com.task.domain.enumtype.StatusType;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.task.domain.Task;
import br.com.task.repository.TasksRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TasksService {

    private final Logger logger = LoggerFactory.getLogger(TasksService.class);

    private TasksRepository repository;

    /**
     *
     * Busca todas as {@link Task}'s pelo status.
     *
     * @param status {@link String}.
     * @return {@link List} de {@link Task}.
     */
    public List<Task> findAllByStatus(String status) {
        Integer statusCode = StatusType.valueOf(status).ordinal();
        return repository.findAllByStatusType(statusCode.toString());
    }

    /**
     *
     * Busca todas as {@link Task}'s pela flag isActive.
     *
     * @param isActive {@link Boolean}.
     * @return {@link List} de {@link Task}.
     */
    public List<Task> findAllByIsActive(Boolean isActive) {
        return repository.findAllByIsActive(isActive);
    }

    /**
     *
     * Busca todas as {@link Task}.
     *
     * @return {@link List} de {@link Task}.
     */
    public List<Task> findAll() {
        return repository.findAll();
    }

    /**
     *
     * Busca {@link Task} pelo identificador.
     *
     * @param id {@link Long} com idendificador da {@link Task}.
     * @return {@link Task}.
     */
    public Task findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     *
     * Registra uma nova {@link Task} caso tenha id, senão atualiza.
     *
     * @param task {@link Task} a ser registrada.
     * @return {@link Task}.
     * @throws Exception
     */
    public Task save(Task task) throws Exception {
        logger.info("Save Task: {}", task);
        if(Objects.isNull(task.getId())) {
            task.setCreatedIn(new Date());
            task.setIsActive(Boolean.TRUE);
            task.setStatus(StatusType.TODO);

            return repository.save(task);
        }
        return update(task);
    }

    /**
     *
     * Atualiza {@link Task} de acordo com o status.
     *
     * @param task {@link Task} a ser atualizada.
     * @return {@link Task}.
     * @throws Exception
     */
    private Task update(Task task) throws Exception {
        logger.info("Update Task: {}", task);
        Task foundTask = repository.findById(task.getId()).orElseThrow(() -> new Exception("Task '"+ task.getTitle() +"' could not be updated"));

        task.setCreatedIn(foundTask.getCreatedIn());
        task.setUpdatedIn(new Date());

        switch (task.getStatus()) {
            case DONE:
                task.setFinishedIn(new Date());
                break;
            case REMOVED:
                task.setIsActive(Boolean.FALSE);
                task.setRemovedIn(new Date());
                break;
            default:
                break;
        }

        return repository.save(task);
    }
}
