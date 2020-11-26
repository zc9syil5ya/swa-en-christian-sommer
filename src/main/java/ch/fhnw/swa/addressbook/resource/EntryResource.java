package ch.fhnw.swa.addressbook.resource;

import ch.fhnw.swa.addressbook.exception.EntryNotFoundException;
import ch.fhnw.swa.addressbook.model.Entry;
import ch.fhnw.swa.addressbook.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/entries/{id}")
    public Entry getEntry(@PathVariable long id) {
        try {
            return entryservice.getEntryById(id);
        } catch (EntryNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Function creates an entry when calling the url .../entry
    @PostMapping("/entries")
    public ResponseEntity<Entry> createEntry(@RequestBody Entry entry) {
        Entry createdEntry = entryservice.createOrUpdateEntry(entry);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEntry.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    //Function updates an entry when calling the url .../entry/{id}
    @PutMapping("/entries/{id}")
    public ResponseEntity<Entry> updateEntry( @PathVariable long id, @RequestBody Entry entry) {
        Entry updated = entryservice.createOrUpdateEntry(entry);
        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
    }

    //Function deletes an entry when calling the url .../entry/{id}
    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable long id) {
        Entry en = null;
        try {
            en = entryservice.deleteEntryById(id);
        } catch (EntryNotFoundException e) {
            e.printStackTrace();
        }
        if (en != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
