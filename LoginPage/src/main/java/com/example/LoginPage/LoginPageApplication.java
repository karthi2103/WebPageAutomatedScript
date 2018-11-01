package com.example.LoginPage;

import com.example.LoginPage.data.InputDataAccesor;
import com.example.LoginPage.elementAccessor.ElementAccessor;
import com.example.LoginPage.models.ElementName;
import com.example.LoginPage.models.ElementPage;
import com.example.LoginPage.models.Elementfamily;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.CSS;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class LoginPageApplication implements CommandLineRunner {
  @Autowired
  private WebDriver webDriver;
  @Autowired
  private ElementAccessor elementAccessor;
  @Autowired
  private InputDataAccesor inputDataAccesor;
  @Autowired
  private HelperService helperService;

  private static final String EMAIL_PLACE_HOLDER = "Email";
  private static final String PASSWORD_PACE_HOLDER = "Password";



  public static void main(String[] args) {
    SpringApplication.run(LoginPageApplication.class, args);
  }


  @Override
  public void run(String... strings) throws Exception {
    webDriver.get("https://radiusagent.com/login");

    // verify Top Header
    List<WebElement>topHeaderElement = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.TOP_HEADER,Elementfamily.ID);
    String topHeader = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.TOP_HEADER);
    verifyElementText(topHeaderElement, topHeader,ElementName.TOP_HEADER);

    // verify Sub Header
    List<WebElement>subHeaderElement = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.SUB_HEADER,Elementfamily.ID);
    String subHeader = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.SUB_HEADER);
    verifyElementText(subHeaderElement,subHeader,ElementName.SUB_HEADER);

    // verify login page Url
    List<WebElement>loginPageImageElement = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.RIGHT_IMAGE,Elementfamily.CSS);
    String loginPageImageUrl = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.RIGHT_IMAGE);
    verifyElementUrl(loginPageImageElement,loginPageImageUrl,ElementName.RIGHT_IMAGE);

    // verify google sign in page connection
    List<WebElement>googleSignInElement = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.GOOGLE_SIGNIN,Elementfamily.CSS);
    String googleSignInUrl = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.GOOGLE_SIGNIN);
    if(helperService.isElementSeenOnPage(googleSignInElement)){
      verifyPopUp(googleSignInElement,webDriver,googleSignInUrl,ElementName.GOOGLE_SIGNIN);
    }

    //verify signin page connection
    List<WebElement>crsdesigneeElement = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.CRS_LOGIN,Elementfamily.CSS);
    String crsSignInUrl = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.CRS_LOGIN);
    verifyPageRedirection(webDriver,crsdesigneeElement,crsSignInUrl,ElementName.CRS_LOGIN);



    //verify Forgot password
    List<WebElement>forgotPasswordElement = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.FORGOT_PASSWORD,Elementfamily.CSS);
    String forgotPasswordUrl = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.FORGOT_PASSWORD);
    verifyPageRedirection(webDriver,forgotPasswordElement,forgotPasswordUrl,ElementName.FORGOT_PASSWORD);

    //verify signup
    List<WebElement>signupElement = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.SIGNUP,Elementfamily.CSS);
    String signUpUrl = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.SIGNUP);
    verifyPageRedirection(webDriver,signupElement,signUpUrl,ElementName.SIGNUP);


    //verify email input field
    List<WebElement>inputField = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.EMAIL,Elementfamily.CSS);
    String emailValue = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.EMAIL);
    verifyInputBox(inputField.get(0),emailValue,ElementName.EMAIL);

    //verify password field
    String passwordField = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.PASSWORD);
    verifyInputBox(inputField.get(1),passwordField,ElementName.PASSWORD);
    // verify Login button and Error Message
    List<WebElement>loginButton = elementAccessor.getWebElementsInAList(webDriver,ElementPage.LOGIN_PAGE,ElementName.LOGIN_BUTTON,Elementfamily.CSS);
    String loginUrl = inputDataAccesor.getValue(ElementPage.LOGIN_PAGE,ElementName.LOGIN_BUTTON);
    verifyPageRedirection(webDriver,loginButton,loginUrl,ElementName.LOGIN_BUTTON);
    webDriver.quit();



  }

  private void verifyElementText(List<WebElement> elements, String elementText, ElementName elementName) {
    if(helperService.isElementSeenOnPage(elements) && helperService.verifyContent(elements.get(0).getText(),elementText)){
      System.out.println(String.format("%s is seen on page and text matches with expected value of %s",elementName.toString().replaceAll("_"," ").toLowerCase(),elementText));
    }
    else{
      System.out.println(String.format("%s is either not seen on page or its text does not match with expected value of %s",elementName.toString().replaceAll("_"," ").toLowerCase(),elementText));
    }
  }
  private void verifyElementUrl(List<WebElement> elements, String elementUrl, ElementName elementName){
    if(helperService.isElementSeenOnPage(elements) && helperService.verifyContent(elements.get(0).getAttribute("src"),elementUrl)){
      System.out.println(String.format("%s is seen on page and url matches with expected value of %s",elementName.toString().replaceAll("_"," ").toLowerCase(),elementUrl));
    }
    else{
      System.out.println(String.format("%s is either not seen on page or it's url doe not matche with expected value of %s",elementName.toString().replaceAll("_"," ").toLowerCase(),elementUrl));
    }
  }

  private void verifyPopUp(List<WebElement> elements, WebDriver driver, String signinUrl, ElementName elementName) throws InterruptedException {
    elements.get(0).click();
    Thread.sleep(400);
    ArrayList<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(browserTabs.get(1));
    String url = driver.getCurrentUrl();
    if(url.contains(signinUrl)){
      System.out.println(String.format("successfully redirected to %s page",elementName.toString().replaceAll("_"," ").toLowerCase()));
    }
    else{
      System.out.println(String.format("failed to load %s page",elementName.toString().replaceAll("_"," ").toLowerCase()));
    }
    driver.close();
    driver.switchTo().window(browserTabs.get(0));

    }

  private void verifyPageRedirection(WebDriver driver, List<WebElement> elements, String url,ElementName elementName) throws InterruptedException {
    elements.get(0).click();
    Thread.sleep(4000);
    String crsUrl = driver.getCurrentUrl();
    if(helperService.verifyContent(crsUrl,url)){
      System.out.println(String.format("Successfully loaded %s page",elementName.toString().replaceAll("_"," ").toLowerCase()));
    }
    else{
      System.out.println(String.format("failed to load %s page",elementName.toString().replaceAll("_"," ").toLowerCase()));
    }
    driver.navigate().back();
  }

  private void verifyInputBox(WebElement element,String inputValue,ElementName elementName){
    if(helperService.verifyContent(element.getAttribute("placeholder"),EMAIL_PLACE_HOLDER)){
      System.out.println(String.format("Place holder match for %s",elementName.toString().replaceAll("_"," ").toLowerCase()));
    }
    else{
      System.out.println(String.format("Place holder do not match for %s",elementName.toString().replaceAll("_"," ").toLowerCase()));
    }
    element.sendKeys(inputValue);
  }


}
