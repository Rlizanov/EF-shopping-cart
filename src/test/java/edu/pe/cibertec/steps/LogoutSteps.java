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

        String email = profilePage.obtenerEmailLogueado();
        Assertions.assertEquals("admin@test.com", email, "El email del perfil no coincide");
    }

    @And("hace clic en cerrar sesion")
    public void haceClicEnCerrarSesion() {
        // Ejecutamos la acción de los dos clics
        profilePage.ejecutarLogoutCompleto();
    }

    @Then("deberia regresar a la pantalla de login")
    public void deberiaRegresarALaPantallaDeLogin() {
        // Esperamos un momento a que el diálogo desaparezca y cargue el login
        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        // Verificamos elementos típicos del Login (botón Ingresar o campo Email)
        boolean enLogin = !profilePage.getDriver()
                .findElements(By.xpath("//*[@text='INGRESAR' or contains(@text, 'Login') or contains(@text, 'Email')]")).isEmpty();

        Assertions.assertTrue(enLogin, "Error: No se regresó a la pantalla de login tras confirmar el logout");
    }
}