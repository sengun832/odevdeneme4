import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage extends BaseTest{

    @Step("<Email sifresini gir> ile giris yap")
    public void passwordSend(String pass) {
        appiumDriver.findElementById(pass).sendKeys("asdfgh");
    }

    @Step("<int> saniye kadar bekle")
    public void waitForsecond(int s) throws InterruptedException {
        Thread.sleep(1000*s);
    }

    @Step("Xpath <xpath> li elementi bul ve tıkla")
    public void clickByxpath(String xpath){
        BaseTest.appiumDriver.findElement(By.xpath(xpath)).click();
    }


    @Step("<id> elemetin sayfada gorunur olduğunu kontrol et ve tıkla")
    public void findByelementEndclick(String id){
        MobileElement element = BaseTest.appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()){
            element.click();
        }else{
            System.out.println("Kontrol edilen element Görünür olmadı");
        }
    }
    @Step("Sayfayı aşağı doğru kaydır")
    public void swipeUp(){
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = BaseTest.appiumDriver.manage().window().getSize();
        System.out.println("Telefon Ekran Boyutu " + dims);
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("Başlangıç noktası " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 10, dims.height / 16);
        System.out.println("Bitiş noktası " + pointOptionEnd);
        try {
            new TouchAction(BaseTest.appiumDriver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
        }
    }

    @Step("Id <id> li elementi bul ve tıkla")
    public void clickByid(String id){
        BaseTest.appiumDriver.findElement(By.id(id)).click();
    }
    @Step("<random> rastgele bir ürün seçilir")
    public void randomProduct(String product){
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(" //*[@resource-id='com.ozdilek.ozdilekteyim:id/imageView']"));
        Random rnd = new Random();
        int rndInt = rnd.nextInt(elements.size());
        elements.get(rndInt).click();
    }
    @Step("<Email adresi> adres ile giris yap")
    public void emailSend(String email) {
        appiumDriver.findElementById(email).sendKeys("abc@hotmail.com");
    }
}

