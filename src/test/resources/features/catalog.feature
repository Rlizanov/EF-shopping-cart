@smoke @catalogo
Feature: Catalogo de productos

  Background:
    Given que el usuario esta logueado en la aplicacion

  Scenario: Ver lista de productos
    When navega al catalogo de productos
    Then deberia ver la lista de productos disponibles

  Scenario: Buscar producto por nombre
    Given que el usuario esta en el catalogo
    When busca el producto "Laptop"
    Then deberia ver productos que contengan "Laptop"

  # --- Escenario solicitado (a) ---
  Scenario: Filtrar productos por categoria
    Given que el usuario esta en el catalogo
    When filtra los productos por la categoria "Electrónica"
    Then deberia ver solo productos pertenecientes a "Electrónica"