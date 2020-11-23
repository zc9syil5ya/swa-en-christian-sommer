package ch.fhnw.swa.addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  ch.fhnw.swa.addressbook.model.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry , Long> {
}
