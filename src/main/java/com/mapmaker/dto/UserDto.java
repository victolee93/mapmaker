package com.mapmaker.dto;

import com.mapmaker.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(Long id, String nickname, String email, String password, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
