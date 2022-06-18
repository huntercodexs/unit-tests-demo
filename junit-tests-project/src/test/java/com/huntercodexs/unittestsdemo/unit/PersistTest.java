package com.huntercodexs.unittestsdemo.unit;

import com.huntercodexs.unittestsdemo.abstractor.UnitAbstractTest;
import com.huntercodexs.unittestsdemo.entity.UserEntity;
import com.huntercodexs.unittestsdemo.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class PersistTest extends UnitAbstractTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback
    @Transactional
    public void testCreateUser() {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(props.getProperty("unit.test.user-name"));
        userEntity.setDocument(props.getProperty("unit.test.user-doc"));
        userEntity.setMail(props.getProperty("unit.test.user-mail"));

        userRepository.save(userEntity);
        UserEntity userEntity1 = userRepository.findByName(props.getProperty("unit.test.user-name"));

        assertionNullable(userEntity1);
        assertionObject(userEntity, userEntity1);

    }

}
