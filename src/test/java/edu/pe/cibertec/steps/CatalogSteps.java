package edu.pe.cibertec.steps;

import edu.pe.cibertec.config.AppiumConfig;
import edu.pe.cibertec.pages.LoginPage;
import edu.pe.cibertec.pages.CatalogPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class CatalogSteps {

    private CatalogPage catalogPage;
    private LoginPage loginPage;

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

        var driver = AppiumConfig.getDriver();
        catalogPage.setDriver(driver);
        loginPage = new LoginPage(driver);


        theActorCalled("Omar").can(BrowseTheWeb.with(driver));
    }

    @Given("que el usuario esta logueado en la aplicacion")
    public void queElUsuarioEstaLogueado() {

        WebDriverWait wait = new WebDriverWait(catalogPage.getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
            loginPage.login("admin@test.com", "123456");
        } catch (Exception e) {

        }
    }



    @Given("que el usuario esta en el catalogo")
    public void que_el_usuario_esta_en_el_catalogo() {
        catalogPage.esperarQueCargueElCatalogo();
    }

    @When("busca el producto {string}")
    public void busca_el_producto(String producto) {
        catalogPage.buscar(producto);
    }

    @Then("deberia ver productos que contengan {string}")
    public void deberia_ver_productos_que_contengan(String productoEsperado) {
        Assertions.assertTrue(catalogPage.verificarProductoVisible(productoEsperado),
                "No se encontró el producto: " + productoEsperado);
    }



    @When("filtra los productos por la categoria {string}")
    public void filtra_los_productos_por_la_categoria(String categoria) {
        if(categoria.equalsIgnoreCase("Electrónica")) {
            catalogPage.filtrarPorElectronica();
        }
    }

    @Then("deberia ver solo productos pertenecientes a {string}")
    public void deberia_ver_solo_productos_pertenecientes_a(String categoria) {
        Assertions.assertTrue(catalogPage.verificarProductoVisible("Laptop"),
                "No se muestran productos de la categoría " + categoria);
    }

    @When("intenta proceder al checkout")
    public void intentaProcederAlCheckout() {
        catalogPage.find(CatalogPage.ICO_CARRITO).click();
    }

    @Then("deberia ver mensaje de carrito vacio")
    public void deberiaVerMensajeDeCarritoVacio() {
        boolean visible = catalogPage.getDriver()
                .findElement(By.xpath("//*[contains(@text, 'vacío')]")).isDisplayed();
        Assertions.assertTrue(visible, "No se mostró el mensaje de carrito vacío");
    }

    @When("navega al catalogo de productos")
    public void navega_al_catalogo_de_productos() {
        // Verificamos que el título 'Productos' sea visible
        Assertions.assertTrue(catalogPage.getDriver().findElement(By.xpath("//*[@text='Productos']")).isDisplayed());
    }

    @Then("deberia ver la lista de productos disponibles")
    public void deberia_ver_la_lista_de_productos_disponibles() {
        Assertions.assertTrue(catalogPage.verificarProductoVisible("Laptop HP Pavilion"),
                "La lista de productos no cargó.");
    }

    @After
    public void tearDown() {
        AppiumConfig.quitDriver();
    }


}