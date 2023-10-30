import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;
import pom.CurrenciesAndMetalsPage;
import pom.DemoModePage;
import pom.HomePageSberbank;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CurrencyTest {
    AndroidDriver<AndroidElement> driver = null;

    @Parameterized.Parameter(value = 0)
    public int numCurrency;

    @Parameterized.Parameter(value = 1)
    public double exchangeRates;

    @Parameterized.Parameter(value = 2)
    public double amountCurrency;

    @Before
    public void initialize() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Poco F2 Pro");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sberbankmobile");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.sberbank.mobile.auth.presentation.splash.SplashActivity");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> initParameters() {
        return Arrays.asList(new Object[][]{
                {0, ExchangeRates.USD, 500.00},
                {1, ExchangeRates.EUR, 500.00},
                {2, ExchangeRates.GBP, 500.00},
                {3, ExchangeRates.AED, 500.00},
                {4, ExchangeRates.CNY, 500.00},
                {5, ExchangeRates.JPY, 500.00},
                {6, ExchangeRates.HKD, 500.00},
                {7, ExchangeRates.SGD, 500.00},
        });
    }

    @Test
    public void checkCurrency() {
        HomePageSberbank homePageSberbank = new HomePageSberbank(driver);
        homePageSberbank.clickButtonMore();
        homePageSberbank.clickButtonDemoMode();
        DemoModePage demoModePage = new DemoModePage(driver);
        demoModePage.clickButtonContinue();
        demoModePage.clickButtonMore();
        CurrenciesAndMetalsPage currenciesAndMetalsPage = new CurrenciesAndMetalsPage(driver);
        currenciesAndMetalsPage.clickButtonBuyCurrency();
        currenciesAndMetalsPage.chooseCurrency(numCurrency);
        currenciesAndMetalsPage.sendAmountCurrency(Double.toString(amountCurrency));
        String amountValue = currenciesAndMetalsPage.getAmountCurrency();
        amountValue = amountValue.replace(",", ".");
        Double actualValue = Double.valueOf(amountValue);
        Double result = amountCurrency / exchangeRates;
        double scale = Math.pow(10, 2);
        double expectedValue = Math.floor(result * scale) / scale;
        assertEquals(actualValue, expectedValue, 0);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
