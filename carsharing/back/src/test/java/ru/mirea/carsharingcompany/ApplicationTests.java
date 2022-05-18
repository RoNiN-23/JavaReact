package ru.mirea.carsharingcompany;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mirea.carsharingcompany.controller.MainController;
import ru.mirea.carsharingcompany.controller.RegistrationController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private MainController mainController;

    @Autowired
    private RegistrationController registrationController;

    @Test
    void contextLoads() {
        assertThat(mainController).isNotNull();
        assertThat(registrationController).isNotNull();
    }

}
