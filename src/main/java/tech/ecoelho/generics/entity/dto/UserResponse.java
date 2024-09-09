package tech.ecoelho.generics.entity.dto;

import tech.ecoelho.generics.entity.UserEntity;

public record UserResponse(String userName, String userEmail, String userCpf) {

    public static UserResponse fromEntity(UserEntity userEntity){
        return new UserResponse(userEntity.getUserName(), userEntity.getUserEmail(), userEntity.getUserCPF());
    }
}
