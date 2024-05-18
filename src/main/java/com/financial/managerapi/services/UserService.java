package com.financial.managerapi.services;
import com.financial.managerapi.dto.*;
import com.financial.managerapi.entities.Token;
import com.financial.managerapi.entities.User;
import com.financial.managerapi.enums.TokenType;
import com.financial.managerapi.repositories.TokenRepository;
import com.financial.managerapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    /**
     * register function for create new user and token for access
     */
    public AuthenticationResponse login(Login request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder().user(user).accessToken(jwtToken).build();

    }

    /**
     * login function for create token for user
     */
    public AuthenticationResponse register(Register request) {

        var user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(request.getRole())
                .image(request.getImage())
                .build();



        var userCheck =  userRepository.findByEmail(user.getEmail());

        if(userCheck.isPresent()){
            return null;
        }

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder().user(user).accessToken(jwtToken).build();

    }

    /**
     * saveUserToken is a function  for save token user into database
     */
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    /**
     * revokeAllUserTokens is a function  for revoke all user token
     */
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    /**
     * refreshToken function for create new token from refresh token
     */

    public User getConnectedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public User updateImage(ImageBase64 image, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if(Objects.isNull(user)){
            return null;
        }
        user.setImage(image.getImage());
        userRepository.save(user);
        return user;
    }

    public User updateUser(UserDto user) {
        User userExit = userRepository.findById(user.getUserId()).orElseThrow();
        if(Objects.isNull(userExit)){
            return null;
        }
        userExit.setFullName(user.getFullName());
        userExit.setPhone(user.getPhone());
        userRepository.save(userExit);
        return userExit;
    }

    public User updatePassword(PasswordChange change, Long id) {
        User userExit = userRepository.findById(id).orElseThrow();
        if(Objects.isNull(userExit)){
            return null;
        }
        if(!passwordEncoder.matches(change.getOldPassword(),userExit.getPassword())){
            return null;
        }
        userExit.setPassword(passwordEncoder.encode(change.getNewPassword()));
        userRepository.save(userExit);
        return userExit;
    }


}