package com.huntercodexs.unittestsdemo.local;

import com.huntercodexs.unittestsdemo.abstractor.LocalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUsersTest extends LocalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read Users
     */

    @Test
    public void whenCorrectRequestToGetAllUsersLocal_RetrieveUsersFound_200() throws Exception {
        okByHttpGet(props.getProperty("local.test.get-users-uri"), "");
    }

}
