package ch.fhnw.swa.repository;

import ch.fhnw.swa.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
