package com.target.notification.model;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ChannelTypes implements LabeledEnum {

  SLACK("slack"), EMAIL("email"), UNKNOW("");

  @Getter
  private String label;

  private static final Map<String, ChannelTypes> BY_LABEL = new HashMap<>();

  static {
    for (ChannelTypes e : values()) {
      BY_LABEL.put(e.label, e);
    }
  }

  public static ChannelTypes valueByLabel(String label) {
    return BY_LABEL.get(label);
  }

}
