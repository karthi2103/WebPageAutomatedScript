package com.example.LoginPage.data;

import com.example.LoginPage.models.ElementName;
import com.example.LoginPage.models.ElementPage;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
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
public class InputDataAccesor {
  private static Splitter TAB_SPLITTER = Splitter.on('\t');
  private Map<InputDataKey,String> dataMapper = new HashMap<>();

  @Autowired
  public InputDataAccesor(@Value("classpath:input_data.txt")Resource resource) throws IOException {

    InputStream inputStream = resource.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    bufferedReader.lines()
            .forEach(value -> {
              List<String>inputData = TAB_SPLITTER.splitToList(value);
              ElementPage elementPage = ElementPage.valueOf(inputData.get(0));
              ElementName elementName = ElementName.valueOf(inputData.get(1));
              dataMapper.put(new InputDataKey(elementPage,elementName),inputData.get(2));
                    }
            );
  }

  public String getValue(ElementPage elementPage,ElementName elementName){
    InputDataKey inputDataKey = new InputDataKey(elementPage,elementName);
    return dataMapper.getOrDefault(inputDataKey,"");
  }
}
