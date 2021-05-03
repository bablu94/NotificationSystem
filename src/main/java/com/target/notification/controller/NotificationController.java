package com.target.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.target.notification.model.ChannelTypes;
import com.target.notification.model.Messages;
import com.target.notification.service.NotificationService;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

  private NotificationService service;

  public NotificationController(NotificationService service) {
    this.service = service;
  }

  @PostMapping("/notify/{channelType}")
  public ResponseEntity<String> notify(@PathVariable ChannelTypes channelType,
      @RequestBody Messages message) {

    return ResponseEntity.ok(service.notify(channelType, message));
  }

  @PostMapping("/notifyAll")
  public ResponseEntity<String> notifyAll(@RequestBody Messages messages) {
    return ResponseEntity.ok(service.notifyAll(messages));
  }

}
