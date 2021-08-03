import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DDTestUsingJSON {

    WebDriver driver;
    @BeforeClass
   public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test(dataProvider="dp")

    public void login(String data){

       String[]  users = data.split(",");

        driver.get("https://demo.nopcommerce.com/login");

        driver.findElement(By.id("Email")).sendKeys(users[0]);

        driver.findElement(By.id("Password")).sendKeys(users[1]);

        driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
        String act_title= driver.getTitle();

        String exp_title= "nopCommerce demo store. Login";

        Assert.assertEquals(act_title,exp_title);



    }

    @DataProvider(name="dp")

    public Object[] readJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("./jsonfiles/testdata.json");
     Object obj=   jsonParser.parse(reader);

    JSONObject userloginsJsonobj=    (JSONObject)obj;

     JSONArray   userloginsArray= (JSONArray)userloginsJsonobj.get("userlogins");

     String[] arr = new String[userloginsArray.size()];

        for (int i = 0; i < userloginsArray.size() ; i++) {

            JSONObject users = (JSONObject)userloginsArray.get(i);

           String user = (String) users.get("username");

           String pwd = (String)users.get("password");

           arr[i] = user + " ," + pwd;

        }

        return arr;
        
        //commit




    }
}
