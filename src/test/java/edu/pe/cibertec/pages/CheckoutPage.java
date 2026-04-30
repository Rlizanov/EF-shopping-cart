package edu.pe.cibertec.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage extends PageObject {

    // El EditText que contiene el TextView "Dirección"
    public static final Target TXT_DIRECCION = Target.the("campo dirección de envío")
            .located(By.xpath("//android.widget.EditText[android.widget.TextView[@text='Dirección']]"));

    // El botón al final del ScrollView (es el View con índice 3)
    public static final Target BTN_FINALIZAR_COMPRA = Target.the("botón finalizar compra")
            .located(By.xpath("//android.widget.ScrollView/android.view.View[@index='3']"));

    // El mensaje de error que aparecerá debajo del campo
    public static final Target LBL_ERROR_DIRECCION = Target.the("mensaje de error de dirección")
            .located(By.xpath("//android.widget.TextView[@text='La dirección es requerida']"));

    // Botón del carrito para ir al checkout (Ajustado según flujo previo)
    public static final Target BTN_IR_A_PAGAR = Target.the("botón proceder al pago")
            .located(By.xpath("//android.widget.TextView[@text='Proceder al pago']"));
}