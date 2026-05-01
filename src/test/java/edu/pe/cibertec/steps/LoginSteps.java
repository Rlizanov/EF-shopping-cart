package edu.pe.cibertec.steps;


import edu.pe.cibertec.config.AppiumConfig;
import edu.pe.cibertec.pages.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {

    private AndroidDriver driver;
    private LoginPage loginPage;

    @Before("@login")
    public void setUp(){
        driver = AppiumConfig.getDriver();
        loginPage = new LoginPage(driver);
    }

    @After("@login")
    public void tearDown(){
        AppiumConfig.quitDriver();
    }

    @Given("que el usuario esta en la pantalla de login")
    public void queElUsuarioEstaEnLaPantallaDeLogin(){
        // Espera para que cargue la app
        try { Thread.sleep(3000); } catch (InterruptedException e) {}


        boolean enLogin = !driver.findElements(By.xpath(
                "//*[contains(@text, 'Shopping Cart')] | //*[contains(@text, 'Iniciar Sesión')]"
        )).isEmpty();

        Assertions.assertTrue(enLogin, "El usuario no está en la pantalla de login");
    }

    @When("ingresa el email {string}")
    public void ingresaElEmail(String email){
        loginPage.enterEmail(email);
    }

    @And("ingresa el password {string}")
    public void ingresElPassword(String password){
        loginPage.enterPassword(password);
    }

    @And("hacer clic en el boton login")
    public void haceClicEnElBotonLogin(){
        loginPage.clickLoginButton();
    }

    @Then("deberia acceder a la pantalla principal")
    public void deberiaAccederALaPantallaPrincipal() {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}


        boolean enHome = !driver.findElements(By.xpath("//*[@text='Productos' or @text='Inicio']")).isEmpty();
        Assertions.assertTrue(enHome, "Error: No se logró acceder a la pantalla principal");
    }

    @Then("deberia ver un mensaje de error")
    public void deberiaVerUnMensajeDeError() {
        var driver = edu.pe.cibertec.config.AppiumConfig.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {

            boolean estaPresente = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text='Email no registrado' or @text='Contraseña incorrecta']")
            )).isDisplayed();

            Assertions.assertTrue(estaPresente, "El mensaje de error no es visible en pantalla");
        } catch (Exception e) {
            Assertions.fail("Error: No apareció el mensaje esperado tras 5 segundos de espera");
        }
    }
}