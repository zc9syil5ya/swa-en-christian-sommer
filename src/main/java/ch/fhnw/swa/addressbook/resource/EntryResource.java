package ch.fhnw.swa.addressbook.resource;


import ch.fhnw.swa.addressbook.model.Entry;
import ch.fhnw.swa.addressbook.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
