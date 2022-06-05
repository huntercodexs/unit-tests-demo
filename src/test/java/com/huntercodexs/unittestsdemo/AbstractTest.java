package com.huntercodexs.unittestsdemo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UnitTestsDemoApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {

    private static final String propFile = "classpath:test.properties";
    protected final Properties props = loadPropsTest();
    protected final String authRequest = props.getProperty("test.basic-authorization-local");
    protected final String invalidAuthRequest = props.getProperty("test.basic-authorization-local-invalid");
    protected String uriBaseTest = props.getProperty("test.uri-base-test");
    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public static Properties loadPropsTest() {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile(AbstractTest.propFile);
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected void createUserBeforeTest(String user_data) throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(user_data)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                ).andReturn();
    }

    protected void rollbackTest(String md5Id) throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(uriBaseTest+"/"+md5Id)
                                .header("Authorization", authRequest)
                ).andReturn();
    }

    /**
     * Using Http GET
     */
    protected void unauthorizedByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uriBaseTest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", invalidAuthRequest)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void createdByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isCreated())
                .andReturn();
    }

    protected void acceptedByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(uriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http POST
     */
    protected void unauthorizedByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", invalidAuthRequest)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void createdByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isCreated())
                .andReturn();
    }

    protected void acceptedByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void foundByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isFound())
                .andReturn();
    }

    protected void conflictByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http PUT
     */
    protected void unauthorizedByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", invalidAuthRequest)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void createdByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isCreated())
                .andReturn();
    }

    protected void acceptedByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http DELETE
     */
    protected void unauthorizedByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(uriBaseTest)
                        .header("Authorization", invalidAuthRequest)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(uriBaseTest)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(uriBaseTest)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void conflictByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(uriBaseTest)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void okByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(uriBaseTest)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void acceptedByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(uriBaseTest)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(uriBaseTest)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void serverErrorByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(uriBaseTest)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http PATCH
     */
    protected void unauthorizedByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", invalidAuthRequest)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void acceptedByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http HEAD
     */
    protected void unauthorizedByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", invalidAuthRequest)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void notFoundByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http OPTIONS
     */
    protected void unauthorizedByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", invalidAuthRequest)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void notFoundByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) uriBaseTest = uri;
        if (!id.equals("")) uriBaseTest = uriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(uriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", authRequest)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

}