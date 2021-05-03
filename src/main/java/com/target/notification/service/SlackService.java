package com.target.notification.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.target.notification.model.Messages;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

@Component
public class SlackService {

  @Value("${slack.access.token}")
  private String slackAccessToken;

  @Value("${slack.channel.name}")
  private String slackChannel;

  public void sendSlackNotif(Messages messages) {
    SlackSession session = null;
    try {
      session = SlackSessionFactory.createWebSocketSlackSession(slackAccessToken);
      session.connect();
      com.ullink.slack.simpleslackapi.SlackChannel channel =
          session.findChannelByName(slackChannel);
      if (channel == null) {
        throw new RuntimeException("Invalid Slack channel [" + slackChannel + "] is specified.");
      }
      session.sendMessage(channel, prepareMsg(messages));
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to send message using slack channel, exception : " + e.getMessage(), e);
    } finally {
      try {
        if (session != null)
          session.disconnect();
      } catch (IOException ignore) {
      }
    }
  }

  private String prepareMsg(Messages messages) {
    StringBuilder b = new StringBuilder();
    if (!messages.getFrom().isEmpty()) {
      b.append("`NotificationService` : (").append(messages.getFrom()).append(") - ");
    }
    if (!messages.getSubject().isEmpty()) {
      b.append("_").append(messages.getSubject()).append("_");
    }
    if (!messages.getBody().isEmpty()) {
      b.append("\n>");
      b.append(messages.getBody());
    }
    return b.toString();
  }

}
