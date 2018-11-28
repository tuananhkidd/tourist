package com.ptit.touristservice.event;

import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.constants.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailVerificationListener implements ApplicationListener<EmailVerificationEvent> {
    @Autowired
    private EmailVerificationTokenService emailVerificationTokenService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void onApplicationEvent(EmailVerificationEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(EmailVerificationEvent event) {
        User user = event.getUser();
        String token = emailVerificationTokenService.createEmailVerificationToken(user).getToken();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getUsername());
        email.setSubject("Xác nhận đăng ký tài khoản Thanh Troll Tourist Service");
        String url = "http://" + ApplicationConstant.LOCAL_HOST + "/api/auth/registration-confirm/" + token;
        email.setText("Xin chào,\n" +
                "Đây là email xác nhận tài khoản Thanh Troll Tourist Service. Vui lòng click vào đường dẫn bên dưới để tiến hành xác minh tài khoản của bạn. Email này có hiệu lực trong vòng 24 tiếng tính từ thời điểm được gửi đến\n" +
                url +
                "\n\nTrân trọng!\n" +
                "Kidd\n");
        if (email != null) {
            javaMailSender.send(email);
        }
    }
}
