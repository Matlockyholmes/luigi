package be.vdab.luigi.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.luigi.PizzaController;
import be.vdab.luigi.domain.Pizza;
import be.vdab.luigi.services.EuroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PizzaControllerTest {
    @Mock
    private EuroService euroService;
    private PizzaController controller;
    @Before
    public void before(){
        controller = new PizzaController(euroService);
    }
    @Test
    public void pizzasGebruiktDeThymeleafPaginaPizzas(){
        assertThat(controller.pizzas().getViewName()).isEqualTo("pizzas");
    }
    @Test
    public void pizzasGeeftPizzasDoorAanDeThymeleafPagina(){
        assertThat(controller.pizzas().getModel().get("pizzas") instanceof Pizza[]).isTrue();
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