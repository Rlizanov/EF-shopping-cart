package edu.pe.cibertec.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumConfig {

    private static AndroidDriver driver;

    public static AndroidDriver getDriver(){
        if(driver == null){
            initilizeDriver();
        }
        return driver;
    }

    private static void initilizeDriver(){
        // Usamos la ruta exacta que confirmaste
        String apkPath = "C:\\appium-test\\apk\\shooping-cart-appium-demo.apk";

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setApp(apkPath)
                .setAutomationName("UiAutomator2")
                .setNoReset(false);
        try{
            // Appium Server por defecto usa el puerto 4723
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL de Appium inválida", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}