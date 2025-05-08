package com.webrise.testproject.service;

import com.webrise.testproject.mapper.UserMapper;
import com.webrise.testproject.model.dto.UserDTO;
import com.webrise.testproject.model.entity.User;
import com.webrise.testproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserInfo(Long id) {
        User user = getUserById(id);
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));

    }

    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = getUserById(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    //Главное не забыть, что когда такой метод вызывается из того же класса, аннотация не будет работать, так как она
    //работает через прокси. Но в нашем случае это не важно, так как все методы, которые его вызывают, выполняются в транзакции.
    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " was not found."));
    }
}
