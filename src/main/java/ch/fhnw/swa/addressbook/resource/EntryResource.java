package ch.fhnw.swa.addressbook.resource;

import ch.fhnw.swa.addressbook.exception.EntryNotFoundException;
import ch.fhnw.swa.addressbook.exception.ResponseMessage;
import ch.fhnw.swa.addressbook.model.Entry;
import ch.fhnw.swa.addressbook.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

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
    private static final Logger logger = Logger.getLogger(EntryResource.class.getName());
    @PostMapping("/upload/{id}")
    public ResponseEntity<ResponseMessage> uploadData(@PathVariable long id, @RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            throw new RuntimeException("You must select the a file for uploading");
        }
        String contentType = file.getContentType();
        if (contentType.equals("image/png") || contentType.equals("image/jpeg") || contentType.equals("image/gif")){
            entryservice.updateImage(id, file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("File successfully uploaded !"));
        }else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseMessage("Wrong file type. Upload only jpg,gif and png !"));
        }
    }
}
