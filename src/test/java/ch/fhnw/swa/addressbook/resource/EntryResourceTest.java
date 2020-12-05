package ch.fhnw.swa.addressbook.resource;

import ch.fhnw.swa.addressbook.AddressbookApplication;
import ch.fhnw.swa.addressbook.model.Entry;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

/**
 * Test Class for EntryResource REST API
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressbookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EntryResourceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EntryResource entryresource;

    TestRestTemplate restTemplate = new TestRestTemplate();

    private static final Logger logger = Logger.getLogger(EntryResource.class.getName());

    @Test
    /**
     * Test get all entry
     */
    void getAllEntries() {
        List<Entry> entries = entryresource.getAllEntries();
        logger.info(entries.toString());
        Assert.assertTrue(entries.size() == 3);
        Entry entry = new Entry("Paul", "Panzer", "paul@panzer.net");
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
    /**
     * Test get an entry
     */
    void getEntry() {
        Entry entry = new Entry("Paul", "Panzer", "paul@panzer.net");
        entry.setEmail("FESTERBESTERTESTER@fhnw.ch");
        HttpEntity<Entry> httpentity = new HttpEntity<>(entry);
        ResponseEntity<Entry> response = restTemplate.exchange(
                createURLWithPort("/entries/" + entry.getId()),
                HttpMethod.PUT, httpentity, Entry.class);
        logger.info(response.toString());
    }

    @Test
    /**
     * Test create an new entry
     */
    void createEntry() {
        List<Entry> entries = entryresource.getAllEntries();
        logger.info(entries.toString());
        Assert.assertTrue(entries.size() == 3);
        HttpEntity<Long> httpentity = new HttpEntity<>(3L);
        ResponseEntity<Entry> response = restTemplate.exchange(
                createURLWithPort("/entries/3"),
                HttpMethod.GET, httpentity, Entry.class);
        logger.info(response.toString());
        Assert.assertTrue(response.getStatusCode().value() == 200);
        logger.info("Test ID 3 E-Mail: " + response.getBody().getEmail());
        Assert.assertTrue(response.getBody().getEmail().equals("Erlfried@gmail.com"));
    }

    @Test
    /**
     * Test update an existing entry
     */
    void updateEntry() {
        Entry entry = new Entry("Paul", "Panzer", "paul@panzer.net");
        entry.setEmail("FESTERBESTERTESTER@fhnw.ch");
        HttpEntity<Entry> httpentity = new HttpEntity<>(entry);
        ResponseEntity<Entry> response = restTemplate.exchange(
                createURLWithPort("/entries/" + entry.getId()),
                HttpMethod.PUT, httpentity, Entry.class);
        logger.info(response.toString());
        Assert.assertTrue(response.getStatusCode().value() == 200);
        List<Entry> entries = entryresource.getAllEntries();
        logger.info(String.valueOf(entries.size()));
        Assert.assertTrue(entries.size() == 4);
        Assert.assertTrue(entries.get(3).getEmail().equals("FESTERBESTERTESTER@fhnw.ch"));
    }

    @Test
    /**
     * Test delete an entry
     */
    void deleteEntry() {
        List<Entry> entries = entryresource.getAllEntries();
        logger.info(entries.toString());
        Assert.assertTrue(entries.size() == 3);
        HttpEntity<Long> httpentity = new HttpEntity<>(3L);
        ResponseEntity<Void> response = restTemplate.exchange(
                createURLWithPort("/entries/3"),
                HttpMethod.DELETE, httpentity, Void.class);
        entries = entryresource.getAllEntries();
        logger.info(entries.toString());
        //test DB entry size
        Assert.assertTrue(entries.size() == 2);
        logger.info(response.getStatusCode().toString());
        // test response
        Assert.assertTrue(response.getStatusCode().value() == 204);
        //try delete 3 again.
        response = restTemplate.exchange(
                createURLWithPort("/entries/3"),
                HttpMethod.DELETE, httpentity, Void.class);
        logger.info(response.getStatusCode().toString());
        //test NOT_FOUND
        Assert.assertTrue(response.getStatusCode().value() == 404);
    }

    @Test
    /**
     * Test upload
     */
    void uploadDataTest() throws IOException {
        List<Entry> entries = entryresource.getAllEntries();
        logger.info(entries.toString());
        Assert.assertTrue(entries.size() == 3);
        //test filetype png
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", geMockFile("png",false));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/upload/2"),
                HttpMethod.POST, requestEntity, String.class);
        logger.info(response.toString());
        Assert.assertTrue(response.getStatusCode().value() == 200);
        //test filetype txt
        bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", geMockFile("txt",false));
        headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        requestEntity = new HttpEntity<>(bodyMap, headers);
       response = restTemplate.exchange(createURLWithPort("/upload/2"),
                HttpMethod.POST, requestEntity, String.class);
        logger.info(response.toString());
        Assert.assertTrue(response.getStatusCode().value() == 422);
        //test if file to big
        bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", geMockFile("txt",true));
        headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        requestEntity = new HttpEntity<>(bodyMap, headers);
        response = restTemplate.exchange(createURLWithPort("/upload/2"),
                HttpMethod.POST, requestEntity, String.class);
        logger.info(response.toString());
        Assert.assertTrue(response.getStatusCode().value() == 413);
    }
    public static Resource geMockFile(String type, boolean oversize) throws IOException {
        //todo maybe replace tempFile with a real file
        Path tempFile = Files.createTempFile("test-file", "."+type);
        if(!oversize) {
            Files.write(tempFile, "some bytes".getBytes());
        }else {
            Files.write(tempFile, ("more bytes~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~").getBytes());
        }
        File file = tempFile.toFile();
        return new FileSystemResource(file);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}