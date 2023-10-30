package pom;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomePageSberbank {
    private final AndroidDriver driver;
    private final String xpathButtonMore = "//android.widget.ImageView[@content-desc=\"Ещё\"]";
    private final String xpathButtonDemoMode = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout";

    public HomePageSberbank(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickButtonMore() {
        driver.findElement(By.xpath(xpathButtonMore)).click();
    }

    public void clickButtonDemoMode() {
        driver.findElement(By.xpath(xpathButtonDemoMode)).click();
    }
}
