package ch.fhnw.swa.addressbook.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.fhnw.swa.addressbook.model.Entry;
import ch.fhnw.swa.addressbook.entryservice.Entryentryservice;
import ch.fhnw.swa.addressbook.exception.EntryNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {
    @Autowired
    Entryentryservice entryservice;

    @GetMapping
    public ResponseEntity<List<Entry>> getAllEntry() {
        List<Entry> list = entryservice.getAllEntrys();

        return new ResponseEntity<List<Entry>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getEmployeeById(@PathVariable("id") Long id)
            throws EntryNotFoundException {
        Entry entity = entryservice.getEntryById(id);

        return new ResponseEntity<Entry>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEntryById(@PathVariable("id") Long id)
            throws EntryNotFoundException {
        entryservice.deleteEntryById(id);
        return HttpStatus.FORBIDDEN;
    }

    @PostMapping
    public ResponseEntity<Entry> createOrUpdateEntry(Entry employee)
            throws EntryNotFoundException {
        Entry updated = entryservice.createOrUpdateEmployee(employee);
        return new ResponseEntity<Entry>(updated, new HttpHeaders(), HttpStatus.OK);
    }


}
