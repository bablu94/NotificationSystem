package com.target.notification.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.target.notification.model.ChannelTypes;
import com.target.notification.model.Messages;

@Service
class NotificationServiceImpl implements NotificationService {

  private EmailService emailService;
  private SlackService slackService;

  private AtomicInteger id = new AtomicInteger(1);
  private String uuid = UUID.randomUUID().toString();

  public NotificationServiceImpl(EmailService emailService, SlackService slackService) {

    this.emailService = emailService;
    this.slackService = slackService;
  }

  LocalDateTime time = LocalDateTime.now();

  @Override
  public String notifyAll(Messages message) {
    return emailAndSlack(message);

  }

  @Override
  public String notify(ChannelTypes channelType, Messages message) {

    if (channelType.equals(ChannelTypes.EMAIL)) {
      message.setId(id.getAndIncrement());
      message.setCreationTime(time);
      emailSend(message);
      return uuid;
    } else if (channelType.equals(ChannelTypes.SLACK)) {
      message.setId(id.getAndIncrement());
      message.setCreationTime(time);
      slackService.sendSlackNotif(message);
      return uuid;
    } else {
      return "ChannelType not found";
    }
  }


  private void emailSend(Messages messages) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(messages.getTo());
    mailMessage.setSubject(messages.getSubject());
    mailMessage.setFrom(messages.getFrom());
    mailMessage.setText(messages.getBody());

    emailService.sendEmail(mailMessage);
  }

  private String emailAndSlack(Messages message) {
    message.setId(id.getAndIncrement());
    message.setCreationTime(time);
    emailSend(message);
    slackService.sendSlackNotif(message);
    return uuid;
  }


}
