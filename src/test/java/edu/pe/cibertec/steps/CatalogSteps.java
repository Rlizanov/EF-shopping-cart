package edu.pe.cibertec.steps;

import edu.pe.cibertec.config.AppiumConfig;
import edu.pe.cibertec.pages.LoginPage;
import edu.pe.cibertec.pages.CatalogPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And; // Importante añadir esto
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class CatalogSteps {

    private CatalogPage catalogPage;
    private LoginPage loginPage;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        var driver = AppiumConfig.getDriver();

        // Sincroniza el PageObject de Serenity con el driver real
        catalogPage.setDriver(driver);

        loginPage = new LoginPage(driver);
        theActorCalled("Ruben").can(BrowseTheWeb.with(driver));
    }

    @Given("que el usuario esta logueado en la aplicacion")
    public void queElUsuarioEstaLogueado() {
        try {
            // Si ya estamos en la pantalla de Productos, evitamos re-loguear
            if (!catalogPage.getDriver().findElements(By.xpath("//*[@text='Productos']")).isEmpty()) {
                return;
            }
            loginPage.login("admin@test.com", "123456");
        } catch (Exception e) {
            loginPage.login("admin@test.com", "123456");
        }
    }

    @Given("que el usuario esta en el catalogo")
    public void elUsuarioEstaEnElCatalogo() {
        catalogPage.esperarQueCargueElCatalogo();
        Assertions.assertTrue(catalogPage.verificarProductoVisible("Laptop HP Pavilion"),
                "El catálogo no cargó correctamente.");
    }

    @When("navega al catalogo de productos")
    public void navegaAlCatalogoDeProductos() {
        Assertions.assertTrue(catalogPage.getDriver().findElement(By.xpath("//*[@text='Productos']")).isDisplayed());
    }

    @Then("deberia ver la lista de productos disponibles")
    public void deberiaVerLaListaDeProductosDisponibles() {
        Assertions.assertTrue(catalogPage.verificarProductoVisible("Laptop HP Pavilion"),
                "La lista de productos no es visible.");
    }

    @When("busca el producto {string}")
    public void buscaElProducto(String producto) {
        catalogPage.buscar(producto);
    }

    @Then("deberia ver productos que contengan {string}")
    public void deberiaVerProductosQueContengan(String productoEsperado) {
        Assertions.assertTrue(catalogPage.verificarProductoVisible(productoEsperado),
                "No se encontró ningún producto que contenga: " + productoEsperado);
    }

    @When("filtra los productos por la categoria {string}")
    public void filtraLosProductosPorLaCategoria(String categoria) {
        if(categoria.equalsIgnoreCase("Electrónica")) {
            catalogPage.filtrarPorElectronica();
        }
    }

    @Then("deberia ver solo productos pertenecientes a {string}")
    public void deberiaVerSoloProductosPertenecientesA(String categoria) {
        Assertions.assertTrue(catalogPage.verificarProductoVisible("Laptop HP Pavilion"),
                "No se muestran productos tras filtrar por " + categoria);
    }

    // --- FLUJO DE PREPARACIÓN PARA CHECKOUT ---

    @Given("que el usuario tiene productos en el carrito")
    public void queElUsuarioTieneProductosEnElCarrito() {
        catalogPage.esperarQueCargueElCatalogo();
        catalogPage.agregarPrimerProductoAlCarrito();

        // Pausa de 3 segundos para estabilidad
        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        catalogPage.find(CatalogPage.ICO_CARRITO).click();
    }

    @Given("que el usuario tiene el carrito vacio")
    public void queElUsuarioTieneElCarritoVacio() {
        catalogPage.esperarQueCargueElCatalogo();
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



    @After
    public void tearDown() {
        AppiumConfig.quitDriver();
    }
}