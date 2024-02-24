package io.nozistance.dsme.aspect;

import io.nozistance.dsme.properties.WebhookProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
@RequiredArgsConstructor
public class WebhookControllerAspect {

    private static final String SECRET_TOKEN_HEADER = "X-Telegram-Bot-Api-Secret-Token";

    private final WebhookProperties properties;

    @Before("execution(* io.nozistance.dsme.controller.WebhookController.onWebhookUpdateReceived(..)))")
    public void validateCustomHeader(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String customHeader = request.getHeader(SECRET_TOKEN_HEADER);
        if (!isValid(customHeader)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Invalid or missing " + SECRET_TOKEN_HEADER);
        }
    }

    private boolean isValid(String token) {
        return properties.getSecretToken().equals(token);
    }
}
