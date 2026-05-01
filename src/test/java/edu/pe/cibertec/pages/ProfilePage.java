package edu.pe.cibertec.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import java.time.Duration;

public class ProfilePage extends PageObject {

    public static final By BTN_ABRIR_PERFIL = By.xpath("//android.widget.TextView[contains(@text,'Perfil')]");
    public static final By LBL_MI_PERFIL = By.xpath("//android.widget.TextView[contains(@text,'Mi Perfil')]");
    public static final By LBL_USER_EMAIL = By.xpath("//android.widget.TextView[contains(@text,'@')]");

    // BOTONES DEL DIÁLOGO (Según tu XML)
    public static final By BTN_LOGOUT_INICIAL = By.xpath("//android.view.View[@content-desc='Cerrar sesión']");

    // CORRECCIÓN: Apuntamos al View padre que es el que tiene el 'clickable=true'
    public static final By BTN_CONFIRMAR_LOGOUT = By.xpath("//android.widget.TextView[@text='Sí, cerrar sesión']/parent::android.view.View");

    public void abrirMenu() {
        $(BTN_ABRIR_PERFIL).withTimeoutOf(Duration.ofSeconds(15)).waitUntilClickable().click();
    }

    public void verificarPantallaPerfil() {
        $(LBL_MI_PERFIL).withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
    }

    public String obtenerEmailLogueado() {
        return $(LBL_USER_EMAIL).withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().getText().trim();
    }

    public void ejecutarLogoutCompleto() {
        // 1. Clic en la opción del menú
        $(BTN_LOGOUT_INICIAL).withTimeoutOf(Duration.ofSeconds(10)).waitUntilClickable().click();

        // 2. Clic en el botón del diálogo (usando el nuevo XPath del padre clicable)
        $(BTN_CONFIRMAR_LOGOUT).withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().click();
    }
}