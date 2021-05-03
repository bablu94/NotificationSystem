package com.target.notification.model;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Messages {

  private long id;
  private String subject;
  @Email
  private String from;
  @Email
  private String to;
  private String body;
  private LocalDateTime creationTime;
}
