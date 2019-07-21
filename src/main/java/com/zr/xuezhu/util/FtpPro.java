package com.zr.xuezhu.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="ftp")
@Data
public class FtpPro {

    private String ip;

    private Integer port;

    private String username;

    private String password;

    private String basePath;
}
