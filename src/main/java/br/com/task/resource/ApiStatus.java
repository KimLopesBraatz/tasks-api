package br.com.task.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class ApiStatus {

    private final Logger logger = LoggerFactory.getLogger(ApiStatus.class);

    @GetMapping
    public ResponseEntity<String> getStatus() {
        logger.info("API status...");
        return ResponseEntity.ok("IS UP!");
    }
}
