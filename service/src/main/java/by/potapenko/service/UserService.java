package by.potapenko.service;

import by.potapenko.model.dto.UserCreationDto;
import by.potapenko.model.dto.UserDto;
import by.potapenko.model.enam.UserRole;
import by.potapenko.model.entity.RoleEntity;
import by.potapenko.model.entity.UserEntity;
import by.potapenko.model.repository.RoleRepository;
import by.potapenko.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    public UserDto create(UserCreationDto user) {
        UserEntity newUser = convertToUserEntity(user);
        List<RoleEntity> roleEntities = user.getRoles().stream().map(roleDto -> roleRepository
                        .findAllByRole(UserRole.valueOf(roleDto.getRole().name())))
                .map(Optional::get).toList();
        newUser.addRole(roleEntities);
        return convertToUserDto(userRepository.save(newUser));
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "email"))
                .stream()
                .map(this::convertToUserDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> getById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToUserDto);
    }

    public Optional<UserDto> update(Long id, UserDto update) {
        Optional<UserEntity> existUser = userRepository.findById(id);
        if (existUser.isPresent()) {
            UserEntity user = existUser.get();
            modelMapper.map(update, user);
            return Optional.of(convertToUserDto(userRepository.save(user)));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }
    private UserDto convertToUserDto(UserEntity user) {
        modelMapper.typeMap(UserEntity.class, UserDto.class)
                .addMapping(UserEntity::getFirstName, UserDto::setFullName);
        return modelMapper.map(user, UserDto.class);
    }

    private UserEntity convertToUserEntity(UserCreationDto user) {
        return modelMapper.map(user, UserEntity.class);
    }
}
