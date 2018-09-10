import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class utility {
    static String webSiteMainUrl="http://www.n11.com";
    static WebDriver driver;
    static String selectedFavoriteProduct;
    static boolean deletedFavorite=false;
    static int favoriesCount=0;
    static int willDeleteFavorite=0;
    public String eMail = "vatanseverahmet78@hotmail.com";
    public String eMailPass = "asdfg123";


    public WebElement element(By Locator){
        return driver.findElement(Locator);
    }

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","E://chromedriver_win32-2.41//chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }

    public String getSelectedFavoriteProduct(){
        return selectedFavoriteProduct;
    }

}