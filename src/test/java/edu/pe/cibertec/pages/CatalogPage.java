package edu.pe.cibertec.pages;

import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import java.time.Duration;

public class CatalogPage extends PageObject {



    public static final Target TXT_SEARCH = Target.the("campo de búsqueda")
            .located(By.className("android.widget.EditText"));

    public static final Target BTN_FILTER_ELECTRONICS = Target.the("filtro Electrónica")
            .located(By.xpath("//*[@text='Electrónica']"));

    public static final Target BTN_AGREGAR_CARRITO = Target.the("botón agregar al carrito")
            .located(By.xpath("(//*[@text='Agregar' or @content-desc='Agregar'])[1]"));

    public static final Target ICO_CARRITO = Target.the("icono del carrito")
            .located(By.xpath("//android.view.View[contains(@content-desc, 'Carrito')] | //*[@text='1']"));

    public static final Target LBL_PRODUCTO = Target.the("producto {0}")
            .locatedBy("//*[@text='{0}' or contains(@text, '{0}')]");


    // --- MÉTODOS DE ACCIÓN ---

    public void buscar(String producto) {
        $(TXT_SEARCH).type(producto);
        try {
            ((AndroidDriver) getDriver()).hideKeyboard();
        } catch (Exception e) {
            // Ignorar si el teclado ya está oculto
        }
    }

    public void filtrarPorElectronica() {
        $(BTN_FILTER_ELECTRONICS).click();
    }

    public boolean verificarProductoVisible(String nombreProducto) {
        try {
            return $(LBL_PRODUCTO.of(nombreProducto))
                    .waitUntilVisible()
                    .isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public void esperarQueCargueElCatalogo() {

        withTimeoutOf(Duration.ofSeconds(20))
                .waitFor(By.xpath("//*[@text='Productos' or @text='Inicio']"));
    }

    public void agregarPrimerProductoAlCarrito() {
        $(BTN_AGREGAR_CARRITO).waitUntilVisible().click();
    }
}