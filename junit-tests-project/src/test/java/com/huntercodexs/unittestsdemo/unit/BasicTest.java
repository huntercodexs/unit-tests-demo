package com.huntercodexs.unittestsdemo.unit;

import com.huntercodexs.unittestsdemo.abstractor.UnitAbstractTest;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicTest extends UnitAbstractTest {

    @Test
    public void whenDocIsValidCpf_RetrieveTrue() {
        isCpf(props.getProperty("unit.test.is-cpf"));
    }

    @Test
    public void whenMailIsValid_RetrieveTrue() {
        isMail(props.getProperty("unit.test.is-mail"));
    }

    @Test
    public void whenPhoneIsValid_RetrieveTrue() {
        isPhone(props.getProperty("unit.test.is-phone"));
    }

    @Test
    public void whenNumberIsValid_RetrieveTrue() {
        assertionNumber(props.getProperty("unit.test.is-number"));
    }

    @Test
    public void whenTextIsValid_RetrieveTrue() {
        assertionText("text", props.getProperty("unit.test.is-text"));
    }

    @Test
    public void whenCalculateSumCorrectly_RetrieveTrue() {
        assertionSum(10, 12, 22);
    }

}
