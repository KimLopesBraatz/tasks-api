package br.com.task.resource;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.task.domain.Task;
import br.com.task.service.TasksService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
@CrossOrigin("*")
public class TasksResource {

    private final Logger logger = LoggerFactory.getLogger(TasksResource.class);

    private TasksService service;

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        logger.info("REST request to get all Tasks");
        List<Task> taskList = service.findAll();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> findAllByStatus(@Valid @PathVariable String status) {
        logger.info("REST request to get all Tasks [status]: {}", status);
        List<Task> taskList = service.findAllByStatus(status);
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/active/{isActive}")
    public ResponseEntity<List<Task>> findAllByActiveFlag(@Valid @PathVariable Boolean isActive) {
        logger.info("REST request to get all Tasks [isActive]: {}", isActive);
        List<Task> taskList = service.findAllByIsActive(isActive);
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> find(@PathVariable Long id) {
        logger.info("REST request to get one Task [id]: {}", id);
        Task task = service.findById(id);
        return Objects.isNull(task) ? ResponseEntity.notFound().build() : ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> save(@Valid @RequestBody Task task) throws Exception {
        Task taskSaved = service.save(task);
        return Objects.isNull(taskSaved) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(taskSaved);
    }

}
