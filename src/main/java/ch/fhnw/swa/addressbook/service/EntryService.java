package ch.fhnw.swa.addressbook.service;

import ch.fhnw.swa.addressbook.exception.EntryNotFoundException;
import ch.fhnw.swa.addressbook.model.Entry;
import ch.fhnw.swa.addressbook.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntryService {
    @Autowired
    EntryRepository repo;
    //Function gets all address book Entries from the database
    public List<Entry> getAllEntries() {
        List<Entry> employeeList = repo.findAll();
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<Entry>();
        }
    }
    //Function gets gets a entry by ID
    public Entry getEntryById(Long id) throws EntryNotFoundException {
        Optional<Entry> entry = repo.findById(id);
        if(entry.isPresent()) {
            return entry.get();
        } else {
            throw new EntryNotFoundException("No employee record exist for given id");
        }
    }

    public void deleteEntryById(Long id) {
    }
    public Entry createOrUpdateEntry(Entry entry){
        return null;
    }
}
