package ch.fhnw.swa.repository;

import ch.fhnw.swa.domain.Entry;
import ch.fhnw.swa.exception.EntryNotFoundException;
import ch.fhnw.swa.web.AddressBookApplication;
import ch.fhnw.swa.web.controll.EntryResourceController;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class for EntryService entity Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EntryServiceTest {
    @LocalServerPort
    private int port;

    @Autowired
    private EntryService entryservice;

    TestRestTemplate restTemplate = new TestRestTemplate();

    private static final Logger logger = Logger.getLogger(EntryResourceController.class.getName());

    @Test
    void getAllEntries() {
        List<Entry> entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 3);
        Entry entry = new Entry("Paul", "Panzer", "paul@panzer.net");
        entryservice.createOrUpdateEntry(entry);
        entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 4);
    }

    @Test
    void getEntryById() throws EntryNotFoundException {
        List<Entry> entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 3);
        Entry entry = new Entry("Paul", "Panzer", "paul@panzer.net");
        entryservice.createOrUpdateEntry(entry);
        Entry en = entryservice.getEntryById(4L);
        Assert.assertTrue(en.getEmail().equals("paul@panzer.net"));
        Assertions.assertThrows(EntryNotFoundException.class, () -> {
            entryservice.getEntryById(5L);
        });
    }

    @Test
    void deleteEntryById() throws EntryNotFoundException {
        List<Entry> entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 3);
        entryservice.deleteEntryById(1L);
        entryservice.deleteEntryById(2L);
        entryservice.deleteEntryById(3L);
        entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 0);
    }

    @Test
    void createOrUpdateEntry() throws EntryNotFoundException {
        List<Entry> entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 3);
        Entry entry = new Entry("Paul", "Panzer", "paul@panzer.net");
        // test create
        entryservice.createOrUpdateEntry(entry);
        entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 4);
        entryservice.createOrUpdateEntry(entry);
        entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 5);
        //test only update
        Entry entry1 = entryservice.getEntryById(5L);
        entry1.setEmail("hansli@nrd.de");
        entryservice.createOrUpdateEntry(entry1);
        entries = entryservice.getAllEntries();
        Assert.assertTrue(entries.size() == 5);
    }

    @Test
    void updateImage() throws IOException, EntryNotFoundException {
        String img = entryservice.getEntryById(1L).getImage();
        MockMultipartFile uploadFile = new MockMultipartFile("test.txt", "content".getBytes());
        entryservice.updateImage(1,uploadFile);
        String gmi = entryservice.getEntryById(1L).getImage();
        Assert.assertTrue(!img.equals(gmi));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}