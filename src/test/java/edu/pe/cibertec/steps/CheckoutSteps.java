package edu.pe.cibertec.steps;

import edu.pe.cibertec.pages.CheckoutPage;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class CheckoutSteps {

    private CheckoutPage checkoutPage;

    @When("procede al checkout")
    public void procedeAlCheckout() {
        checkoutPage.find(CheckoutPage.BTN_IR_A_PAGAR).click();
    }

    @And("ingresa los datos de envio")
    public void ingresaLosDatosDeEnvio() {
        checkoutPage.find(CheckoutPage.TXT_DIRECCION).sendKeys("Av. Cibertec 123");
    }

    @And("confirma la compra")
    public void confirmaLaCompra() {
        checkoutPage.find(CheckoutPage.BTN_FINALIZAR_COMPRA).click();
    }

    @Then("deberia ver el mensaje de compra existosa")
    public void deberiaVerElMensajeDeCompraExistosa() {
        boolean exito = !checkoutPage.getDriver()
                .findElements(By.xpath("//*[contains(@text, 'Gracias') or contains(@text, 'exitosa')]")).isEmpty();
        Assertions.assertTrue(exito, "No se mostró el mensaje de éxito de compra");
    }

    @And("intenta confirmar la compra sin ingresar la direccion")
    public void intentaConfirmarSinDireccion() {
        checkoutPage.find(CheckoutPage.TXT_DIRECCION).clear();
        try {
            ((AndroidDriver) checkoutPage.getDriver()).hideKeyboard();
        } catch (Exception e) { }
        checkoutPage.find(CheckoutPage.BTN_FINALIZAR_COMPRA).click();
    }

    @Then("deberia ver un mensaje de error indicando {string}")
    public void deberiaVerMensajeDeError(String mensajeEsperado) {
        String mensajeObtenido = checkoutPage.find(CheckoutPage.LBL_ERROR_DIRECCION).getText();
        Assertions.assertEquals(mensajeEsperado, mensajeObtenido, "El mensaje de error no es el esperado");
    }
}