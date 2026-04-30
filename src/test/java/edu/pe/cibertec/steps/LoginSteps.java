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

        // VALIDACIÓN: Según tu XML, buscamos "Shopping Cart" o "Iniciar Sesión"
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

        // Verificamos que aparezca el catálogo
        boolean enHome = !driver.findElements(By.xpath("//*[@text='Productos' or @text='Inicio']")).isEmpty();
        Assertions.assertTrue(enHome, "Error: No se logró acceder a la pantalla principal");
    }

    @Then("deberia ver un mensaje de error")
    public void deberiaVerUnMensajeDeError() {
        // Pausa necesaria para que el mensaje aparezca
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // VALIDACIÓN: Según tu XML, el mensaje es "Contraseña incorrecta"
        // Usamos "incorrect" para capturar tanto masculino como femenino
        boolean errorVisible = !driver.findElements(By.xpath(
                "//*[contains(@text, 'incorrect')] | " +
                        "//*[contains(@text, 'inválid')] | " +
                        "//*[contains(@text, 'Error')]"
        )).isEmpty();

        Assertions.assertTrue(errorVisible, "Error: El mensaje de error no apareció en pantalla");
    }
}