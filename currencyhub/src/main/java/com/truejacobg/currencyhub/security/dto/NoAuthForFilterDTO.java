package com.truejacobg.currencyhub.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "blocked")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoAuthForFilterDTO {

   public Map<String, String> request;
}
