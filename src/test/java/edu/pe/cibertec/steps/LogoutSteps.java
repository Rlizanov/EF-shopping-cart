package edu.pe.cibertec.steps;

import edu.pe.cibertec.pages.ProfilePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import java.time.Duration;

public class LogoutSteps {

    private ProfilePage profilePage;

    @When("hace clic en el menu de usuario")
    public void haceClicEnElMenuDeUsuario() {
        profilePage.abrirMenu();
        profilePage.verificarPantallaPerfil();

        String emailEnPantalla = profilePage.obtenerEmailLogueado();

        org.junit.jupiter.api.Assertions.assertEquals("admin@test.com", emailEnPantalla,
                "ERROR: El email mostrado en el perfil no es el correcto.");
    }

    @And("hace clic en cerrar sesion")
    public void haceClicEnCerrarSesion() {
        // Ejecutamos la acción de los dos clics
        profilePage.ejecutarLogoutCompleto();
    }

    @Then("deberia regresar a la pantalla de login")
    public void deberiaRegresarALaPantallaDeLogin() {

        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        boolean enLogin = !profilePage.getDriver()
                .findElements(By.xpath("//*[@text='INGRESAR' or contains(@text, 'Login') or contains(@text, 'Email')]")).isEmpty();

        Assertions.assertTrue(enLogin, "Error: No se regresó a la pantalla de login tras confirmar el logout");
    }
}