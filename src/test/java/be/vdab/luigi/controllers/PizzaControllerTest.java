package be.vdab.luigi.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import be.vdab.luigi.domain.Pizza;
import be.vdab.luigi.services.EuroService;
import be.vdab.luigi.services.PizzaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PizzaControllerTest {
    @Mock
    private PizzaService pizzaService;
    @Mock
    private EuroService euroService;
    private PizzaController controller;
    @Before
    public void before(){
        when(pizzaService.findById(1)).thenReturn(Optional.of(new Pizza(1, "Test", BigDecimal.ONE, true)));
        controller = new PizzaController(euroService, pizzaService);
    }
    @Test
    public void pizzasGebruiktDeThymeleafPaginaPizzas(){
        assertThat(controller.pizzas().getViewName()).isEqualTo("pizzas");
    }
    @Test
    public void pizzasGeeftPizzasDoorAanDeThymeleafPagina(){
        assertThat(controller.pizzas().getModel().get("pizzas")).isInstanceOf(List.class);
    }
    @Test
    public void pizzaGebruiktDeThymeleafPaginaPizza() {
        assertThat(controller.pizza(1).getViewName()).isEqualTo("pizza");
    }
    @Test
    public void pizzaGeeftGevondenPizzaDoorAanDeThymeleafPagina() {
        Pizza pizza = (Pizza) controller.pizza(1).getModel().get("pizza");
        assertThat(pizza.getId()).isEqualTo(1);
    }
    @Test
    public void pizzaGeeftOnbestaandePizzaNietDoorAanDeThymeleafPagina() {
        assertThat(controller.pizza(-1).getModel()).doesNotContainKeys("pizza");
    }
}