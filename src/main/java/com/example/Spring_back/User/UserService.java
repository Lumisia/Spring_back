package com.example.Spring_back.User;

import com.example.Spring_back.User.model.EmailVerify;
import com.example.Spring_back.User.model.User;
import com.example.Spring_back.User.model.UserDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository ur;
    private final EmailVerifyRepository er;
    private final PasswordEncoder pe;
    private final JavaMailSender ms;

    public void signup(UserDto.SignupReq dto) {
        User user = dto.toEntity();
        user.setPassword(pe.encode(dto.getPassword()));
        ur.save(user);

        MimeMessage message = ms.createMimeMessage();

        try {
            String uuid = UUID.randomUUID().toString();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(dto.getEmail());

            String subject = "[안녕] 환영";
            String contents = "<a href='http://localhost:8080/user/verify?uuid="+uuid+"'>이메일 인증</a>";
            helper.setSubject(subject);
            helper.setText(contents, true);
            ms.send(message);

            EmailVerify emailVerify = EmailVerify.builder()
                    .email(dto.getEmail())
                    .uuid(uuid)
                    .build();
            er.save(emailVerify);

        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
