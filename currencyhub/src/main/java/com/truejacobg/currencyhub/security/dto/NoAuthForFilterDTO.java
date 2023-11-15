package com.truejacobg.currencyhub.security.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "blocked")
public class NoAuthForFilterDTO {
   public List<Map<Object,Object>> request;
}
