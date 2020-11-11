package org.roman.repository;

import org.roman.model.Jet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JetRepository extends JpaRepository<Jet, Long> {
}
