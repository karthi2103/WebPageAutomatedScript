package com.example.LoginPage.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ElementName {
  TOP_HEADER,
  SUB_HEADER,
  RIGHT_IMAGE,
  GOOGLE_SIGNIN,
  CRS_LOGIN,
  EMAIL,
  PASSWORD,
  SHOW_PASSWORD,
  FORGOT_PASSWORD,
  LOGIN_BUTTON,
  SIGNUP,
  RADIUS_ICON,
  CHAT_ICON,

  // GOOGLE SIGN IN
  GOOGLE_POPUP;




 public static List<String> getElementName(){
   return Arrays.stream(ElementName.values())
           .map(Enum::toString)
           .collect(Collectors.toList());
 }
}
