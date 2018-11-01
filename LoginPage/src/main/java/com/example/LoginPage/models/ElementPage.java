package com.example.LoginPage.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ElementPage {
  LOGIN_PAGE,
  CHAT_WINDOW,
  DEFAULT_PAGE,;

  public static List<String> getElementPage(){
    return Arrays.stream(ElementPage.values())
            .map(Enum::toString)
            .collect(Collectors.toList());
  }

}
