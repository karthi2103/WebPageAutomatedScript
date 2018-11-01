package com.example.LoginPage.data;

import com.example.LoginPage.models.ElementName;
import com.example.LoginPage.models.ElementPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDataKey {

  ElementPage elementPage;
  ElementName elementName;
}
