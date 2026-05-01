package edu.pe.cibertec.steps;

import edu.pe.cibertec.config.AppiumConfig;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import io.cucumber.java.Before;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

public class CheckoutSteps {

    // LOCALIZADORES
    private static final Target BTN_AGREGAR = Target.the("agregar").located(AppiumBy.xpath("(//android.widget.Button)[1]"));
    private static final Target ICO_CARRO = Target.the("carrito").located(AppiumBy.accessibilityId("Carrito"));
    private static final Target BTN_IR_PAGAR = Target.the("checkout").located(AppiumBy.xpath("//*[@text='Checkout' or @text='Ir a pagar']"));
    private static final Target TXT_DIR = Target.the("direccion").located(AppiumBy.xpath("//android.widget.EditText[1]"));
    private static final Target BTN_FIN = Target.the("finalizar").located(AppiumBy.xpath("//*[@text='Finalizar Compra' or @text='Place Order']"));
    private static final Target LBL_ERR = Target.the("error").located(AppiumBy.xpath("//*[contains(@text, 'requerida')]"));



    @Before
    public void prepareStage() {

        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el usuario tiene productos en el carrito")
    public void que_el_usuario_tiene_productos_en_el_carrito() {

        OnStage.theActorCalled("Omar")
                .can(BrowseTheWeb.with(AppiumConfig.getDriver()));

        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(BTN_AGREGAR, WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
                Click.on(BTN_AGREGAR),
                Click.on(ICO_CARRO)
        );
    }



    @Given("que el usuario tiene el carrito vacio")
    public void que_el_usuario_tiene_el_carrito_vacio() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ICO_CARRO, WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds(),
                Click.on(ICO_CARRO)
        );
    }

    @When("procede al checkout")
    public void procedeAlCheckout() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(BTN_IR_PAGAR, WebElementStateMatchers.isVisible()).forNoMoreThan(5).seconds(),
                Click.on(BTN_IR_PAGAR)
        );
    }

    @And("ingresa los datos de envio")
    public void ingresaLosDatosDeEnvio() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Enter.theValue("Av. Cibertec 123").into(TXT_DIR)
        );
    }

    @And("confirma la compra")
    public void confirmaLaCompra() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Click.on(BTN_FIN)
        );
    }

    @And("intenta confirmar la compra sin ingresar la direccion")
    public void intentaConfirmarSinDireccion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Enter.theValue("").into(TXT_DIR), // Asegura que esté vacío
                Click.on(BTN_FIN)
        );
    }

    @Then("deberia ver un mensaje de error indicando {string}")
    public void deberiaVerMensajeDeError(String mensaje) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(Text.of(LBL_ERR), containsString(mensaje))
        );
    }
}