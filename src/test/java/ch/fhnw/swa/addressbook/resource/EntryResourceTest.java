package ch.fhnw.swa.addressbook.resource;

import ch.fhnw.swa.addressbook.AddressbookApplication;
import ch.fhnw.swa.addressbook.exception.EntryNotFoundException;
import ch.fhnw.swa.addressbook.model.Entry;
import ch.fhnw.swa.addressbook.service.EntryService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressbookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EntryResourceTest {
    private static final String TEST_ADDRESS_BOOK = "address-book1";
    private static final String CONTACTS_END_POINT = "/entries";

    @LocalServerPort
    private int port;

    @Autowired
    private EntryService entryservice;


    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void getAllEntries() {
    }

    @Test
    void getEntry() {
    }

    @Test
    void createEntry() {
        Entry entry = new Entry();
        entry.setId((long) -1);
        entry.setEmail("marsupilami@gameboy.com");

        Entry en = entryservice.createOrUpdateEntry(entry);

        HttpEntity<Entry> entity = new HttpEntity<>(en);

        ResponseEntity<Entry> response = restTemplate.exchange(
                createURLWithPort(CONTACTS_END_POINT),
                HttpMethod.POST, entity, Entry.class);

        String dinimueter = response.toString();

        Assert.assertTrue(response.getStatusCode().value() == 201);

        try {
            entryservice.deleteEntryById(en.getId());
        } catch (EntryNotFoundException e) {
            e.printStackTrace();
        }
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