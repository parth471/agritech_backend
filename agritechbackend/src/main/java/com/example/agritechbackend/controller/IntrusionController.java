package com.example.agritechbackend.controller;

import com.example.agritechbackend.dto.IntrusionRequest;
import com.example.agritechbackend.service.IntrusionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class IntrusionController {

    private final IntrusionService service;

    public IntrusionController(IntrusionService service) {
        this.service = service;
    }

    @PostMapping("/intrusion")
    public String report(@Valid @RequestBody IntrusionRequest req) throws Exception {
        return service.handleIntrusion(req);
    }
}
