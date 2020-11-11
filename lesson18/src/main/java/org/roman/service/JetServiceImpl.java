package org.roman.service;

import org.roman.model.Jet;
import org.roman.repository.JetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JetServiceImpl implements JetService {

    private final JetRepository repository;

    @Autowired
    public JetServiceImpl(JetRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Jet> getAllJets() {
        return repository.findAll();
    }

    @Override
    public Jet add(Jet jet) {
        return repository.save(jet);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Jet update(Long id, Jet jet) {
        jet.setId(id);
        return repository.save(jet);
    }
}
