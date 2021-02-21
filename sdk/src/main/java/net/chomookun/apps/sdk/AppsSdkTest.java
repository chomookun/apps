package net.chomookun.apps.sdk;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppsSdkConfiguration.class })
@TestPropertySource("classpath:test.properties")
public class AppsSdkTest {

}
