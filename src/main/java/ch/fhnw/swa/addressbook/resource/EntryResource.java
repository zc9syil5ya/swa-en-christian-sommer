package ch.fhnw.swa.addressbook.resource;


import ch.fhnw.swa.addressbook.exception.EntryNotFoundException;
import ch.fhnw.swa.addressbook.model.Entry;
import ch.fhnw.swa.addressbook.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class EntryResource {
    @Autowired
    private EntryService entryservice;
    //Function gets all entries by calling the url .../entries
    @GetMapping("/entries")
    public List<Entry> getAllEntries() {
        return entryservice.getAllEntries();
    }

    //Function gets an entry by ID when calling the url .../entry/{id}
    @GetMapping("/entry/{id}")
    public Entry getEntry(@PathVariable long id) {
        try {
            return entryservice.getEntryById(id);
        } catch (EntryNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/entry")
    public ResponseEntity<Void> createEntry(@RequestBody Entry entry) {
        Entry createdEntry = entryservice.createOrUpdateEntry(entry);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEntry.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
