package by.potapenko.web.integretion;

import by.potapenko.model.dto.UserDto;
import by.potapenko.service.UserService;
import by.potapenko.web.UserServiceApplication;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;


@SpringBootTest(classes = UserServiceApplication.class)
@RequiredArgsConstructor
@TestConstructor(autowireMode = ALL)
@Sql(value = "classpath:db-test.sql")
class UserServiceIntegretionTest {

    private final UserService userService;

    @Test
    void whenGetAllInvoked_thenAllTheUsersAreReturned() {
        List<UserDto> users = userService.getAll();
        Assertions.assertThat(users).hasSize(14);
    }
}
