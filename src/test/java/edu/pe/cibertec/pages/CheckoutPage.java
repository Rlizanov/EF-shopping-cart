package edu.pe.cibertec.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage extends PageObject {


    public static final Target TXT_DIRECCION = Target.the("campo dirección de envío")
            .located(By.xpath("//android.widget.EditText[android.widget.TextView[@text='Dirección']]"));


    public static final Target BTN_FINALIZAR_COMPRA = Target.the("botón finalizar compra")
            .located(By.xpath("//android.widget.ScrollView/android.view.View[@index='3']"));


    public static final Target LBL_ERROR_DIRECCION = Target.the("mensaje de error de dirección")
            .located(By.xpath("//android.widget.TextView[@text='La dirección es requerida']"));


    public static final Target BTN_IR_A_PAGAR = Target.the("botón proceder al pago")
            .located(By.xpath("//android.widget.TextView[@text='Proceder al pago']"));
}