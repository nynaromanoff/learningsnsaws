package io.git.learningsnsaws.propostApp.Notification.config;

import org.springframework.beans.factory.annotation.Value;

public class AmazonSnsConfiguration {

    @Value("${aws.acessKey}")
    private String acessKey;

    @Value("${aws.secretKey}")
    private String secretKey;
}
