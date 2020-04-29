package ar.com.ada.sb.unittest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    //2 endpoint -> /test/one && /test/two => GET => OK => {"status":"ok"}

    @GetMapping({"/one", "/one/"})
    private ResponseEntity testOne() {
        Map<String, String> body = new HashMap<>();
        body.put("status", "ok");
        return ResponseEntity.ok(body);
    }

    @GetMapping({"/two", "/two/"})
    private ResponseEntity testTwo() {
        return ResponseEntity.ok(null);
    }
}
