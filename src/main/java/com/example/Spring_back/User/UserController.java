package com.example.Spring_back.User;

import com.example.Spring_back.User.model.AuthUserDetails;
import com.example.Spring_back.User.model.UserDto;
import com.example.Spring_back.Utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto.SignupReq dto) {

        userService.signup(dto);
        return ResponseEntity.ok("성공");
    }

    @GetMapping("/verify")
    public ResponseEntity verify(String uuid) {

        userService.verify(uuid);

        return ResponseEntity.ok("성공");
    }

    @GetMapping("/check-enable")
    public ResponseEntity email(String email) {

        boolean check = userService.email(email);

        if(check) {
            return ResponseEntity.ok("성공");
        }else {
            return ResponseEntity.ok("실패");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto.LoginReq dto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword(), null);

        Authentication authentication = authenticationManager.authenticate(token);
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        if(user != null) {
            String jwt = JwtUtil.createToken(user.getIdx(), user.getEmail(), user.getRole());
            return ResponseEntity.ok().header("Set-Cookie", "ATOKEN=" + jwt + "; Path=/").build();
        }

        return ResponseEntity.ok("로그인 실패");

    }
}
