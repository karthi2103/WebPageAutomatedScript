package com.example.LoginPage.models;

public enum Elementfamily {
  NAME("name"),
  XPATH("xpath"),
  ID("id"),
  CSS("css"),
  CLASS("class"),
  LINK_TEXT("linktext");

  private final String elementFamily;


  Elementfamily(String name) {
    this.elementFamily = name;
  }

  public static String getElementFamily(Elementfamily elementfamily){
    return elementfamily.elementFamily;
  }
}
