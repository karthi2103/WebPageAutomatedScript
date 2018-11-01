package com.example.LoginPage.elementAccessor;

import com.example.LoginPage.models.ElementName;
import com.example.LoginPage.models.ElementPage;
import com.example.LoginPage.models.Elementfamily;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementKey {
   ElementPage elementPage;
   ElementName elementName;
   Elementfamily elementFamily;
}
