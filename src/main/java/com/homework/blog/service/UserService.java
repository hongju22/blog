package com.homework.blog.service;

import com.homework.blog.dto.SignupRequestDto;
import com.homework.blog.model.User;
import com.homework.blog.model.UserRoleEnum;
import com.homework.blog.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String pattern = "^[a-zA-Z0-9]*$";
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        if(username.length()<3){
            throw new IllegalArgumentException("닉네임을 3자이상 입력하세요.");
        }else if(!Pattern.matches(pattern,username)){
            throw new IllegalArgumentException("알파벳 대소문자와 숫자로만 입력하세요ㅕ.");
        }else if(password.length()<4){
            throw new IllegalArgumentException("비밀번호는 4자이상 입력하세요");
        }else if(password.contains(username)){
            throw new IllegalArgumentException("비밀번호와 닉네임이 달라야 합니다.");
        }

        // 패스워드 암호화

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }
}
