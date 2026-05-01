@smoke @checkout
Feature: Proceso de compra


  Scenario: Completar compra exitosamente
    Given que el usuario tiene productos en el carrito
    When procede al checkout
    And ingresa los datos de envio
    And confirma la compra
    Then deberia ver el mensaje de compra exitosa

  @carrito-vacio
  Scenario: Carrito vacio no permite checkout
    Given que el usuario tiene el carrito vacio
    When intenta proceder al checkout
    Then deberia ver mensaje de carrito vacio

  @validacion-direccion
  Scenario: No permitir compra sin direccion de envio
    Given que el usuario tiene productos en el carrito
    When procede al checkout
    And intenta confirmar la compra sin ingresar la direccion
    Then deberia ver un mensaje de error indicando "La dirección es requerida"