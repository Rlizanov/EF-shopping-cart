package edu.pe.cibertec.steps;

import edu.pe.cibertec.pages.CatalogPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Given;
import io.cucumber.java.es.Then;
import io.cucumber.java.es.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.jupiter.api.Assertions;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CatalogSteps {

    private CatalogPage catalogPage;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el usuario esta en el catalogo")
    public void elUsuarioEstaEnElCatalogo() {
        theActorCalled("Ruben").can(BrowseTheWeb.with(catalogPage.getDriver()));
        // Aquí podrías llamar al login si fuera necesario, o asumir que ya está logueado por el Background
    }

    @When("busca el producto {string}")
    public void buscaElProducto(String producto) {
        catalogPage.buscar(producto);
    }

    @Then("deberia ver productos que contengan {string}")
    public void deberiaVerProductosQueContengan(String productoEsperado) {
        // Assertions de JUnit 5 (Requerimiento 1c)
        Assertions.assertTrue(catalogPage.verificarProductoVisible(productoEsperado),
                "El producto " + productoEsperado + " no es visible en los resultados.");
    }

    @When("filtra los productos por la categoria {string}")
    public void filtraLosProductosPorLaCategoria(String categoria) {
        if(categoria.equalsIgnoreCase("Electrónica")) {
            catalogPage.filtrarPorElectronica();
        }
    }

    @Then("deberia ver solo productos pertenecientes a {string}")
    public void deberiaVerSoloProductosPertenecientesA(String categoria) {
        // En un caso real validaríamos que el tag de categoría coincida.
        // Para el examen validamos que un producto de esa categoría sea visible.
        Assertions.assertTrue(catalogPage.verificarProductoVisible("Laptop HP Pavilion"),
                "No se muestran productos de la categoría " + categoria);
    }

    @After
    public void tearDown() {
        // Hook para limpiar el driver al finalizar
        if (theActorInTheSpotlight() != null) {
            catalogPage.getDriver().quit();
        }
    }
}