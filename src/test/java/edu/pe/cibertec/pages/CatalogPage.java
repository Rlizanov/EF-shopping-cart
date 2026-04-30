package edu.pe.cibertec.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CatalogPage extends PageObject {

    public static final Target TXT_SEARCH = Target.the("campo de búsqueda")
            .located(By.xpath("//android.widget.EditText"));

    // Cambiamos @text='' por contains(@text, '') para que sea flexible
    public static final Target LBL_PRODUCT_NAME = Target.the("nombre del producto {0}")
            .locatedBy("//android.widget.TextView[contains(@text, '{0}')]");

    public static final Target BTN_FILTER_ELECTRONICS = Target.the("filtro Electrónica")
            .located(By.xpath("//android.widget.TextView[@text='Electrónica']"));

    public void buscar(String producto) {
        // 1. type() limpia el campo y escribe el texto SIN enviar caracteres especiales al final
        $(TXT_SEARCH).type(producto);

        // 2. Ocultamos el teclado para que la app procese la búsqueda y no tape la pantalla
        try {
            ((io.appium.java_client.android.AndroidDriver) getDriver()).hideKeyboard();
        } catch (Exception e) {
            // Si el teclado no aparece, simplemente continuamos
        }
    }

    public void filtrarPorElectronica() {
        $(BTN_FILTER_ELECTRONICS).click();
    }

    public boolean verificarProductoVisible(String nombreProducto) {
        try {
            // El método $() convierte el Target en un elemento que ya sabe esperar
            return $(LBL_PRODUCT_NAME.of(nombreProducto))
                    .waitUntilVisible() // Espera automática de Serenity
                    .isVisible();       // Devuelve true o false
        } catch (Exception e) {
            return false;
        }
    }
}