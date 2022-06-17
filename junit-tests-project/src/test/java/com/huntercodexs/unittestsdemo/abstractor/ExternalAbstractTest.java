package com.huntercodexs.unittestsdemo.abstractor;

import com.huntercodexs.unittestsdemo.UnitTestsDemoApplication;
import com.huntercodexs.unittestsdemo.external.ExternalUserResponseDto;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UnitTestsDemoApplication.class)
@WebAppConfiguration
public abstract class ExternalAbstractTest {

    protected MockMvc mockMvc;
    protected RestTemplate restTemplate;

    private static final String propFile = "classpath:external.test.properties";
    protected final Properties props = loadPropsTest();

    protected String externalUrlBaseTest = props.getProperty("external.test.base-url");
    protected String externalUriBaseTest = props.getProperty("external.test.base-uri");

    protected final String externalBasicAuthorization = props.getProperty("external.test.basic-authorization");
    protected final String externalInvalidBasicAuthorization = props.getProperty("external.test.basic-authorization-invalid");
    protected final String externalAppNameAuthorization = props.getProperty("external.test.api-key.app-name");
    protected final String externalTokenAuthorization = props.getProperty("external.test.api-key.token");
    protected final String externalSecretAuthorization = props.getProperty("external.test.api-key.secret");
    protected final String externalValueAuthorization = props.getProperty("external.test.api-key.value");
    protected final String externalGenericAuthorization = props.getProperty("external.test.api-key.generic");
    protected final String externalAdditionalAuthorization = props.getProperty("external.test.api-key.additional");

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        restTemplate = new RestTemplate();
    }

    public static Properties loadPropsTest() {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile(ExternalAbstractTest.propFile);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected void createBeforeTest(String user_data) throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(externalUrlBaseTest+externalUriBaseTest)
                                .content(user_data)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", externalBasicAuthorization)
                ).andReturn();
    }

    protected void rollbackTest(String id) throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(externalUrlBaseTest+externalUriBaseTest+"/"+id)
                                .header("Authorization", externalBasicAuthorization)
                ).andReturn();
    }

    protected void assertionTest(String ref, String text) {
        if (text.contains(ref)) {
            Assert.assertEquals(1, 1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    protected HttpHeaders httpRequestHeaders(boolean invalidAuth) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (invalidAuth) {
            httpHeaders.set("Authorization", externalInvalidBasicAuthorization);
        } else {
            httpHeaders.set("Authorization", externalBasicAuthorization);
        }
        return httpHeaders;
    }

    protected HttpComponentsClientHttpRequestFactory httpClientFactory() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    /**
     * Using Http GET together Rest Template
     */
    protected void assertRestByGet(String uri, String id, Object body, int expect) throws Exception {

        boolean basicAuth = false;

        if (!uri.equals("")) externalUriBaseTest = uri;
        if (!id.equals("")) externalUriBaseTest = externalUriBaseTest+"/"+id;
        if (expect == 401) basicAuth = true;

        String url = externalUrlBaseTest + externalUriBaseTest;
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpRequestHeaders(basicAuth));

        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
            Assert.assertEquals(response.getStatusCodeValue(), expect);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(ex.getRawStatusCode(), expect);
        }
    }

    /**
     * Using Http POST together Rest Template
     */
    protected void assertRestByPost(String uri, String id, Object body, int expect) throws Exception {

        boolean basicAuth = false;

        if (!uri.equals("")) externalUriBaseTest = uri;
        if (!id.equals("")) externalUriBaseTest = externalUriBaseTest+"/"+id;
        if (expect == 401) basicAuth = true;

        String url = externalUrlBaseTest + externalUriBaseTest;
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpRequestHeaders(basicAuth));

        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(url, httpEntity, Object.class);
            Assert.assertEquals(response.getStatusCodeValue(), expect);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(ex.getRawStatusCode(), expect);
        }
    }

    /**
     * Using Http DELETE together Rest Template
     */
    protected void assertRestByDelete(String uri, String id, Object body, int expect) throws Exception {

        boolean basicAuth = false;

        if (!uri.equals("")) externalUriBaseTest = uri;
        if (!id.equals("")) externalUriBaseTest = externalUriBaseTest+"/"+id;
        if (expect == 401) basicAuth = true;

        String url = externalUrlBaseTest + externalUriBaseTest;
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpRequestHeaders(basicAuth));

        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Object.class);
            Assert.assertEquals(response.getStatusCodeValue(), expect);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(ex.getRawStatusCode(), expect);
        }
    }

    /**
     * Using Http PUT together Rest Template
     */
    protected void assertRestByPut(String uri, String id, Object body, int expect) throws Exception {

        boolean basicAuth = false;

        if (!uri.equals("")) externalUriBaseTest = uri;
        if (!id.equals("")) externalUriBaseTest = externalUriBaseTest+"/"+id;
        if (expect == 401) basicAuth = true;

        String url = externalUrlBaseTest + externalUriBaseTest;
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpRequestHeaders(basicAuth));

        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Object.class);
            Assert.assertEquals(response.getStatusCodeValue(), expect);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(ex.getRawStatusCode(), expect);
        }
    }

    /**
     * Using Http PATCH together Rest Template
     */
    protected void assertRestByPatch(String uri, String id, Object body, int expect) throws Exception {

        boolean basicAuth = false;

        if (!uri.equals("")) externalUriBaseTest = uri;
        if (!id.equals("")) externalUriBaseTest = externalUriBaseTest+"/"+id;
        if (expect == 401) basicAuth = true;

        String url = externalUrlBaseTest + externalUriBaseTest;
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpRequestHeaders(basicAuth));

        try {
            restTemplate.setRequestFactory(httpClientFactory());
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.PATCH, httpEntity, Object.class);
            Assert.assertEquals(response.getStatusCodeValue(), expect);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(ex.getRawStatusCode(), expect);
        }
    }

    /**
     * Using Http HEAD together Rest Template
     */
    protected void assertRestByHead(String uri, String id, Object body, int expect) throws Exception {

        boolean basicAuth = false;

        if (!uri.equals("")) externalUriBaseTest = uri;
        if (!id.equals("")) externalUriBaseTest = externalUriBaseTest+"/"+id;
        if (expect == 401) basicAuth = true;

        String url = externalUrlBaseTest + externalUriBaseTest;
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpRequestHeaders(basicAuth));

        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.HEAD, httpEntity, Object.class);
            Assert.assertEquals(response.getStatusCodeValue(), expect);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(ex.getRawStatusCode(), expect);
        }
    }

    /**
     * Using Http OPTIONS together Rest Template
     */
    protected void assertRestByOptions(String uri, String id, Object body, int expect) throws Exception {

        boolean basicAuth = false;

        if (!uri.equals("")) externalUriBaseTest = uri;
        if (!id.equals("")) externalUriBaseTest = externalUriBaseTest+"/"+id;
        if (expect == 401) basicAuth = true;

        String url = externalUrlBaseTest + externalUriBaseTest;
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpRequestHeaders(basicAuth));

        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.OPTIONS, httpEntity, Object.class);
            Assert.assertEquals(response.getStatusCodeValue(), expect);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(ex.getRawStatusCode(), expect);
        }
    }

}