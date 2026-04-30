package edu.pe.cibertec.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProfilePage extends PageObject {

    public static final Target BTN_ABRIR_PERFIL = Target.the("icono de perfil barra inferior")
            .located(By.xpath("//android.widget.TextView[@text='Perfil']"));

    public static final Target LBL_MI_PERFIL = Target.the("título Mi Perfil")
            .located(By.xpath("//android.widget.TextView[@text='Mi Perfil']"));

    public static final Target LBL_USER_EMAIL = Target.the("email del usuario")
            .located(By.xpath("//android.widget.TextView[@text='admin@test.com']"));

    public static final Target BTN_LOGOUT_INICIAL = Target.the("botón logout perfil")
            .located(By.xpath("//android.view.View[@content-desc='Cerrar sesión']"));

    // SEGÚN TU XML DEL DIÁLOGO: El botón de confirmación
    public static final Target BTN_CONFIRMAR_LOGOUT = Target.the("botón confirmar logout")
            .located(By.xpath("//android.widget.TextView[@text='Sí, cerrar sesión']"));

    public void abrirMenu() {
        $(BTN_ABRIR_PERFIL).waitUntilClickable().click();
    }

    public void verificarPantallaPerfil() {
        $(LBL_MI_PERFIL).waitUntilVisible();
    }

    public String obtenerEmailLogueado() {
        return $(LBL_USER_EMAIL).waitUntilVisible().getText();
    }

    public void ejecutarLogoutCompleto() {
        // 1. Clic en el botón del perfil para abrir el diálogo
        $(BTN_LOGOUT_INICIAL).waitUntilClickable().click();

        // 2. Clic en "Sí, cerrar sesión" en el cuadro de confirmación
        $(BTN_CONFIRMAR_LOGOUT).waitUntilClickable().click();
    }
}