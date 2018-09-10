import java.util.List;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testCase extends page{
    WebDriverWait wait= new WebDriverWait(driver, 500);
    WebDriver wDriver;

    //n11 açılış
    @Test
    public void WebSiteControl(){
        driver.get(webSiteMainUrl);
        waitForPageLoad();
        Assert.assertTrue(driver.getTitle().equals("n11.com - Alışverişin Uğurlu Adresi"));
        System.out.println("Web Sitesi Açıldı");
    }

    //Login sayfası açılışı
    @Test
    public void loginPageControl(){
        findByClassName("btnSignIn").click();
        wait.until(elementClickableById("loginButton"));
        Assert.assertTrue(findById("loginButton").getText().equals("Üye Girişi"));
        System.out.println("Sayfa kullanıcı girişi için hazır ...");
    }

    //Kullanıcı girişi
    @Test
    public void loginControl(){
        findById("email").sendKeys("vatanseverahmet78@hotmail.com");
        findById("password").sendKeys("asdfg123");
        findById("loginButton").click();
        waitForPageLoad();
        //Assert.assertTrue(!findByClassName("username").getText().equals(""));
        System.out.println("Kullanıcı Girişi Başarılı ...");
    }

    //Samsung arama
    @Test
    public void searchControl(){
        wait.until(elementClickableById("searchData"));
        findById("searchData").sendKeys("samsung");
        findByClassName("searchBtn").click();
        wait.until(elementClickableByClassName("pagination"));
        String resultOk=findByXpad("//section[2]/div/div").getText();
        Assert.assertTrue(resultOk.contains("sonuç bulundu."));
        System.out.println("Samsung için sonuç bulundu");
    }

    //ikinci sayfaya açılma ve 2. sayfanın açılma testi
    @Test
    public void searchPageOpenControl(){
        WebElement beforeList = element(By.className("pagination"));
        List<WebElement> sayfalarListesi = beforeList.findElements(By.tagName("a"));
        System.out.println("a listesi oluşturuldu");

        for (int x=0; x<sayfalarListesi.size(); x++){
            System.out.println("Listedeki : " + sayfalarListesi );
            WebElement elementA =  sayfalarListesi.get(x);
            if (elementA.getText().equals("2")){
                elementA.click();
                System.out.println("2.Sayfaya geçiliyor.");
            }
        }
    }

    // 3. ürünün favorilere eklenmesi
    @Test
    public void addFavorite(){
        //Listede 3. Ürün Oluşana Kadar Bekle
        wait.until(elementClickableByXpad("//li[3]/div/div[2]/span"));
        selectedFavoriteProduct=getElementTextByXpad("//li[3]/div/div/a/h3");
        findByXpad("//li[3]/div/div[2]/span").click();
        System.out.println("Favoriye Eklenen Ürün Adı : "+selectedFavoriteProduct);
    }

    //Favorilerim bölümüne tıklanma testi
    @Test
    public void favoritePage(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", findByXpad("//*[@class='myAccountMenu hOpenMenu']/div[1]/a[2]"));
        waitForPageLoad();
    }

    //Favorilerimde daha önce eklenmiş ürünün onaylanma testi
    @Test
    public void favoriteConfirmation(){
        List<WebElement> productTitles= findListByXpad("//*[@class='productTitle']/p/a");
        for (WebElement productTitle : productTitles) {
            favoriesCount+=1;
            String watchesProduct=productTitle.getText();
            if (watchesProduct.equals(selectedFavoriteProduct)) {
                System.out.println("Favoriye Eklenen Ürün Onaylandı.Ürünün Başlığı :"+watchesProduct+"\n");
                willDeleteFavorite=favoriesCount;
                Assert.assertTrue(watchesProduct.equals(selectedFavoriteProduct));
            }
        }
    }


    //Favorilerime eklenen ürünün silmesi
    @Test
    public void deleteFavorite() throws InterruptedException {
        favoritePage favouritePage =new favoritePage(driver);
        driver.findElement(By.className("listItemTitle")).click();
        waitForPageLoad();
        for (WebElement productTitle : favouritePage.myFavoriteList()) {
            String watchesProduct=productTitle.getText();
            if (watchesProduct.equals(favouritePage.getSelectedFavoriteProduct())) {
                favouritePage.clickLinkDeleteProduct();
                wait.until(elementClickableByXpad("//li[3]/div/div[2]/span"));
                waitForPageLoad();
                //Thread.sleep(3000);
                Assert.assertTrue(favouritePage.getMessage().equals("Ürününüz listeden silindi."));
                favouritePage.closeContent();
                System.out.println("Ürününüz listeden başarılı bir şekilde silindi....");
            }
        }
    }

    //Favorilerimden silinen ürünün kontrolü
    @Test
    public void deleteFavoriteControl(){
        boolean controlValue=true;
        favoritePage favouritePage =new favoritePage(driver);
        for (WebElement productTitle : favouritePage.myFavoriteList()) {
            String watchesProduct=productTitle.getText();
            if (watchesProduct.equals(favouritePage.getSelectedFavoriteProduct()))
                controlValue=false;
        }
        Assert.assertFalse(controlValue);
        System.out.println("Ürününüz favorilerde yer almıyor....");
    }

}