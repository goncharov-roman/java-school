package org.roman.service;

import org.roman.model.Jet;

import java.util.List;

public interface JetService {

    List<Jet> getAllJets();

    Jet add(Jet jet);

    void remove(Long id);

    Jet update(Long id, Jet jet);
}
