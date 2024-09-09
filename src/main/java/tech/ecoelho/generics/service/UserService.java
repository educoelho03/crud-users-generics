package tech.ecoelho.generics.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import tech.ecoelho.generics.entity.UserEntity;
import tech.ecoelho.generics.entity.dto.UserResponse;
import tech.ecoelho.generics.exception.UserNotFoundException;
import tech.ecoelho.generics.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers(){
        List<UserEntity> list = userRepository.findAll();
        return list.stream().map(x -> new UserResponse(x.getUserName(), x.getUserEmail(), x.getUserCPF())).collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id){
        Optional<UserEntity> response = userRepository.findById(id);

        return response.map(UserResponse::fromEntity)
                .orElseThrow(() -> new UserNotFoundException("Usuario nao encontrado."));
    }

    public UserResponse createUser(UserEntity userEntity){

        // Salvando o novo usuário no repositório
        UserEntity newUser = userRepository.save(userEntity);

        // Criando e retornando uma resposta com os dados do novo usuário
        return new UserResponse(
                newUser.getUserName(),
                newUser.getUserEmail(),
                newUser.getUserCPF()
        );
    }

    public UserResponse updateUser(Long userId, UserEntity userEntity){
        var existingUser  = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuario nao encontrado"));
        existingUser.setUserName(userEntity.getUserName());
        existingUser.setUserEmail(userEntity.getUserEmail());
        existingUser.setUserCPF(userEntity.getUserCPF());

        UserEntity updatedUser = userRepository.save(existingUser);

        return new UserResponse(
                userEntity.getUserName(),
                userEntity.getUserEmail(),
                userEntity.getUserCPF()
        );
    }

    public void deleteUser(Long userId) {
        var existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuario nao encontrado"));

        userRepository.deleteById(existingUser.getId());
    }
}
