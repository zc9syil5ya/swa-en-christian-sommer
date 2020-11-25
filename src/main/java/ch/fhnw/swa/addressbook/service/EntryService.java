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
            throw new EntryNotFoundException("No entry record exist for given id");
        }
    }
    //Function delete a entry by ID
    public Entry deleteEntryById(Long id) throws EntryNotFoundException {
        Optional<Entry> entry = repo.findById(id);
        if(entry.isPresent()) {
            Entry en = entry.get();
            repo.deleteById(id);
            return en;
        } else {
            throw new EntryNotFoundException("No entry record exist for given id");
        }
    }
    //Function creates or update a Entry in the database
    public Entry createOrUpdateEntry(Entry entry){
        Optional<Entry> _entry= repo.findById(entry.getId());
        if(_entry.isPresent()) {
            Entry newEntry = _entry.get();
            newEntry.setFirstName(entry.getFirstName());
            newEntry.setLastName(entry.getLastName());
            newEntry.setStreet(entry.getStreet());
            newEntry.setZipcode(entry.getZipcode());
            newEntry.setCity(entry.getCity());
            newEntry.setEmail(entry.getEmail());
            newEntry.setPhone(entry.getPhone());
            newEntry = repo.save(newEntry);
            return newEntry;
        } else {
            entry = repo.save(entry);
            return entry;
        }
    }
}
