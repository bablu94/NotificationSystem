package com.target.notification.service;

import org.springframework.stereotype.Service;
import com.target.notification.model.ChannelTypes;
import com.target.notification.model.Messages;

@Service
public interface NotificationService {

  public String notifyAll(Messages message);

  public String notify(ChannelTypes channelType, Messages message);

}
