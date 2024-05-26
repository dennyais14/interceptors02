package co.develhope.Interceptor02.controllers;

import co.develhope.Interceptor02.entities.Month;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/months")
public class MonthController {
    @GetMapping
    public ResponseEntity<Month> getMonth(@RequestAttribute("month") Month month) {
        return ResponseEntity.ok(month);
    }
}
