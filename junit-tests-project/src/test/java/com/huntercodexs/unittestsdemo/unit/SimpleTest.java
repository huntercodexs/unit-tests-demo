package com.huntercodexs.unittestsdemo.unit;

import com.huntercodexs.unittestsdemo.abstractor.UnitAbstractTest;
import com.huntercodexs.unittestsdemo.model.UserModel;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleTest extends UnitAbstractTest {

    @Test
    public void testUserModel_0001() {
        UserModel userModel = new UserModel();
        userModel.setName(props.getProperty("unit.test.user-name"));
        userModel.setDocument(props.getProperty("unit.test.user-doc"));
        userModel.setMail(props.getProperty("unit.test.user-mail"));

        assertionExact("Username Tester", userModel.getName());
    }

    @Test
    public void testUserModel_0002() {
        UserModel userModel = new UserModel();
        userModel.setName(props.getProperty("unit.test.user-name"));
        userModel.setDocument(props.getProperty("unit.test.user-doc"));
        userModel.setMail(props.getProperty("unit.test.user-mail"));

        UserModel newUser = new UserModel();
        assertionBool(newUser.insertUser(userModel));

    }

}
