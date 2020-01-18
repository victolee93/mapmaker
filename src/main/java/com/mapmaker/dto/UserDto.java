package com.mapmaker.dto;

import com.mapmaker.domain.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(Long id, String nickname, String email, String password,
                   LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
