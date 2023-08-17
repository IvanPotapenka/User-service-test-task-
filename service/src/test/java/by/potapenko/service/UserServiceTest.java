package by.potapenko.service;

import by.potapenko.model.dto.RoleDto;
import by.potapenko.model.dto.UserCreationDto;
import by.potapenko.model.dto.UserDto;
import by.potapenko.model.enam.Role;
import by.potapenko.model.entity.RoleEntity;
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

    private static final ModelMapper modelMapper = new ModelMapper();
    ;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService(userRepository,
                roleRepository, modelMapper);
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

    @Test
    void whenCreatedInvokedWithUser_ThenUserIsSaved() {
        when(userRepository.findAll()).thenReturn(List.of(modelMapper.map(userTest, UserEntity.class)));
        when(roleRepository.findAllByRole(Role.SALE)).thenReturn(Optional.of(sale));
        when(userRepository.save(modelMapper.map(userTest, UserEntity.class)))
                .thenReturn(modelMapper.map(userTest, UserEntity.class));

        userService.create(userTest);
        List<String> allUserEmail = userRepository.findAll().stream()
                .map(UserEntity::getEmail)
                .toList();
        Assertions.assertThat(allUserEmail).contains(userTest.getEmail());
    }

    @Test
    void whenUpdateById_ThenSavedUserUpdate() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(ivanUser));
        when(roleRepository.findAllByRole(Role.SALE)).thenReturn(Optional.of(sale));
        when(userRepository.save(ivanUser)).thenReturn(ivanUser);

        ivanUser.getRoles().add(sale);
        userService.update(anyLong(), modelMapper.map(ivanUser, UserCreationDto.class));
        Assertions.assertThat(ivanUser.getRoles()).contains(sale);
    }

    private UserCreationDto userTest = UserCreationDto.builder()
            .firstName("Ivan")
            .surName("Potapenko")
            .middleName("Uryevich")
            .email("kira@mail.ru")
            .roles(List.of(saleRole))
            .build();

    private static final RoleDto saleRole = RoleDto.builder()
            .role(Role.SALE)
            .build();
    private static final RoleEntity sale = RoleEntity.builder()
            .role(Role.SALE)
            .build();
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