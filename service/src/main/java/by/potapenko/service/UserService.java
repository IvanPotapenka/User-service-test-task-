package by.potapenko.service;

import by.potapenko.model.dto.UserCreationDto;
import by.potapenko.model.dto.UserDto;
import by.potapenko.model.enam.Role;
import by.potapenko.model.entity.RoleEntity;
import by.potapenko.model.entity.UserEntity;
import by.potapenko.model.repository.RoleRepository;
import by.potapenko.model.repository.UserRepository;
import by.potapenko.service.util.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Optional<UserDto> create(UserCreationDto user) {
        UserEntity newUser = modelMapper.map(user, UserEntity.class);
        List<RoleEntity> roles = new ArrayList<>();
        user.getRoles().forEach(roleDto ->
                roleRepository.findAllByRole(Role.valueOf(roleDto.getRole().name()))
                        .ifPresent(roles::add));
        newUser.addRole(roles);
        return Optional.of(convertToUserDto(userRepository.save(newUser)));
    }

    public List<UserDto> getAll() {
        return userRepository.findAllByOrderByEmailAsc()
                .stream()
                .map(this::convertToUserDto)
                .toList();
    }

    public Optional<UserDto> getById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return Optional.of(user.map(this::convertToUserDto)
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public Optional<UserDto> update(Long id, UserCreationDto update) {
        Optional<UserEntity> existUser = userRepository.findById(id);
        if (existUser.isPresent()) {
            UserEntity user = existUser.get();
            List<RoleEntity> roles = new ArrayList<>();
            update.getRoles().forEach(roleDto ->
                    roleRepository.findAllByRole(Role.valueOf(roleDto.getRole().name()))
                            .ifPresent(roles::add));
            user.addRole(roles);
            return Optional.of(convertToUserDto(userRepository.save(user)));
        }
        return Optional.empty();
    }

    @Transactional
    public void delete(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        user.ifPresentOrElse(userRepository::delete,
                () -> user.orElseThrow(UserNotFoundException::new));
    }

    public UserDto convertToUserDto(UserEntity user) {
        modelMapper.typeMap(UserEntity.class, UserDto.class)
                .addMapping(UserEntity::getFullName, UserDto::setFullName);
        return modelMapper.map(user, UserDto.class);
    }
}
