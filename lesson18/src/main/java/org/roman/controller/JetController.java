package org.roman.controller;

import org.roman.model.Jet;
import org.roman.service.JetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jets", produces = "application/json")
@ResponseBody
public class JetController {

    private final JetService service;

    @Autowired
    public JetController(JetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jet> getAllJets() {
        return service.getAllJets();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Jet create(@RequestBody Jet jet) {
        return service.add(jet);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Jet update(@Param("id") Long id, @RequestBody Jet jet) {
        return service.update(id, jet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@Param("id") Long id) {
        service.remove(id);
    }
}
