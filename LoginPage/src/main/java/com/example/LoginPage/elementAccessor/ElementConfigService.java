package com.example.LoginPage.elementAccessor;

import com.example.LoginPage.models.ElementName;
import com.example.LoginPage.models.ElementPage;
import com.example.LoginPage.models.Elementfamily;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ElementConfigService {
 private Map<ElementKey, String> elementKeyMap = new HashMap<>();
 private static Splitter TAB_SPLITTER = Splitter.on('\t');

 @Autowired
  public ElementConfigService(@Value("classpath:element_value.txt") org.springframework.core.io.Resource resource) throws IOException {

   InputStream inputStream = resource.getInputStream();
   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
   bufferedReader.lines()
           .forEach(value -> {
             List<String> elementList = TAB_SPLITTER.splitToList(value);
             ElementName elementName = ElementName.valueOf(elementList.get(1));
             ElementPage elementPage = ElementPage.valueOf(elementList.get(0));
             Elementfamily elementfamily = Elementfamily.valueOf(elementList.get(2));

             elementKeyMap.put(new ElementKey(elementPage,elementName,elementfamily), elementList.get(3));
           });

 }

 String getValue(ElementPage elementPage, ElementName elementName, Elementfamily elementfamily){
   ElementKey elementKey = new ElementKey(elementPage,elementName,elementfamily);
   return elementKeyMap.getOrDefault(elementKey,"empty");
 }


}
