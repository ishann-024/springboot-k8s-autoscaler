package com.example.instancePOC.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//import org.springframework.http.ResponseEntity;
@RestController
public class RequestController {
    private static final Logger log = LoggerFactory.getLogger(RequestController.class);
//    private final String podName = System.getenv("POD_NAME"); // Injected by Kubernetes
//    private static final AtomicInteger globalCounter = new AtomicInteger(0);
//    private final AtomicInteger instanceCounter = new AtomicInteger(0);
//    @GetMapping("/health")
//    public ResponseEntity<String> healthCheck() {
//        return ResponseEntity.ok("OK from " + podName);
//    }
@GetMapping("/stress")
public int generateStress() {
    log.info("Generating heavy CPU stress...");
    long start = System.currentTimeMillis();
    int result = 0;
    // Run for ~10 seconds
    while (System.currentTimeMillis() - start < 10000) {
        for (int i = 0; i < 50000; i++) {
            // Use CPU-intensive math operations
            result += Math.sqrt(i * i + 12345) * Math.cbrt(i + 6789);
        }
    }
    return result;
}
//    @GetMapping("/load")
//    public Map<String, Object> handleLoad() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("pod", podName);
//        response.put("globalCount", globalCounter.incrementAndGet());
//        response.put("instanceCount", instanceCounter.incrementAndGet());
//        // Kubernetes will auto-scale based on metrics
//        response.put("message", "Autoscaling handled by Kubernetes HPA");
//        return response;
//    }
}