package ch.fhnw.swa.addressbook.resource;

import ch.fhnw.swa.addressbook.AddressbookApplication;
import ch.fhnw.swa.addressbook.exception.EntryNotFoundException;
import ch.fhnw.swa.addressbook.model.Entry;
import ch.fhnw.swa.addressbook.service.EntryService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressbookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EntryResourceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EntryResource entryresource;

    TestRestTemplate restTemplate = new TestRestTemplate();

    private static final Logger logger = Logger.getLogger(EntryResource.class.getName());

    @Test
    void getAllEntries() {
        List<Entry> entries = entryresource.getAllEntries();
        logger.info(entries.toString());
        Assert.assertTrue(entries.size() == 3);
        Entry entry = new Entry("Paul","Panzer","paul@panzer.net");
        HttpEntity<Entry> httpentity = new HttpEntity<>(entry);
        ResponseEntity<Entry> response = restTemplate.exchange(
                createURLWithPort("/entries"),
                HttpMethod.POST, httpentity, Entry.class);
        logger.info(response.toString());

        entries = entryresource.getAllEntries();
        logger.info(entries.toString());
        Assert.assertTrue(entries.size() == 4);
    }

    @Test
    void getEntry() { ;
    }

    @Test
    void createEntry() {
        Entry entry = new Entry("Paul","Panzer","paul@panzer.net");
        HttpEntity<Entry> httpentity = new HttpEntity<>(entry);
        ResponseEntity<Entry> response = restTemplate.exchange(
                createURLWithPort("/entries"),
                HttpMethod.POST, httpentity, Entry.class);
        logger.info(response.toString());
        Assert.assertTrue(response.getStatusCode().value() == 201);
    }

    @Test
    void updateEntry() {
    }

    @Test
    void deleteEntry() {
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}