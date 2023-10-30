package pom;

import io.appium.java_client.android.AndroidDriver;

public class DemoModePage {
    private final String idButtonContinue = "ru.sberbankmobile:id/ds_rich_alert_positive_button";
    private final String idButtonMore = "ru.sberbankmobile:id/dhbf_action_button_view";
    private final AndroidDriver driver;

    public DemoModePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickButtonContinue() {
        driver.findElementById(idButtonContinue).click();
    }

    public void clickButtonMore() {
        driver.findElementById(idButtonMore).click();
    }
}
