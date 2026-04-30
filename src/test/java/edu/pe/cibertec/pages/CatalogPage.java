package edu.pe.cibertec.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CatalogPage extends PageObject {

    // Identificadores (Targets) requeridos para el patrón ScreenPlay
    public static final Target TXT_SEARCH = Target.the("campo de búsqueda")
            .located(By.xpath("//android.widget.EditText[.//android.widget.TextView[@text='Buscar productos...']]"));

    public static final Target BTN_FILTER_ELECTRONICS = Target.the("filtro Electrónica")
            .located(By.xpath("//android.widget.TextView[@text='Electrónica']"));

    // Selector con parámetro para validar cualquier producto
    public static final Target LBL_PRODUCT_NAME = Target.the("nombre del producto {0}")
            .locatedBy("//android.widget.TextView[@text='{0}']");

    // --- Métodos de operación solicitados en el punto (b) ---

    public void buscar(String producto) {
        $(TXT_SEARCH).type(producto);
    }

    public void filtrarPorElectronica() {
        $(BTN_FILTER_ELECTRONICS).click();
    }

    public boolean verificarProductoVisible(String nombreProducto) {
        return $(LBL_PRODUCT_NAME.of(nombreProducto)).isVisible();
    }
}