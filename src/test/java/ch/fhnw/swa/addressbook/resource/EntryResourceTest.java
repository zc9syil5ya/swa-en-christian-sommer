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

    private static final String CONTACTS_END_POINT = "/entries";

    @LocalServerPort
    private int port;

    @Autowired
    private EntryService entryservice;

    @Autowired
    private EntryResource entryresource;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void getAllEntries() {
    }

    @Test
    void getEntry() {
    }

    @Test
    void createEntry() {

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