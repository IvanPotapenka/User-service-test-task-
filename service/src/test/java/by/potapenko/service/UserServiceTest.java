package by.potapenko.service;

import by.potapenko.model.dto.UserDto;
import by.potapenko.model.entity.UserEntity;
import by.potapenko.model.repository.RoleRepository;
import by.potapenko.model.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService(userRepository,
                roleRepository, new ModelMapper());
    }

    @Test
    void whenGetByIdInvoked_thenExectUserIsReturned() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(ivanUser));
        Optional<UserDto> userDto = userService.getById(anyLong());
        Assertions.assertThat(userDto).isNotEmpty();
        Assertions.assertThat(userDto.get().getEmail()).isEqualTo(ivanUser.getEmail());
        verify(userRepository).findById(anyLong());
    }

    @Test
    void whenGetAllInvoked_thenAllTheUsersAreReturned() {
        when(userRepository.findAllByOrderByEmailAsc()).thenReturn(List.of(kiraUser, ivanUser));
        userService.getAll().forEach(UserDto::getEmail);
        String[] expected = new String[]{kiraUser.getEmail(), ivanUser.getEmail(),};
        Assertions.assertThat(expected).containsExactlyInAnyOrder(kiraUser.getEmail(), ivanUser.getEmail());
    }

    private UserEntity ivanUser = UserEntity.builder()
            .firstName("Ivan")
            .surName("Potapenko")
            .middleName("Uryevich")
            .email("ivan@mail.ru")
            .build();
    private UserEntity kiraUser = UserEntity.builder()
            .firstName("Kira")
            .surName("Potapenko")
            .middleName("Aleksandrovna")
            .email("kira@mail.ru")
            .build();
}