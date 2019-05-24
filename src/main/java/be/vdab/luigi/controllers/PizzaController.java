package be.vdab.luigi.controllers;

import be.vdab.luigi.forms.VanTotPrijsForm;
import be.vdab.luigi.services.EuroService;
import be.vdab.luigi.services.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("pizzas")
public class PizzaController {
    private final EuroService euroService;
    private final PizzaService pizzaService;

    public PizzaController(EuroService euroService, PizzaService pizzaService) {
        this.euroService = euroService;
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ModelAndView pizzas(){
        return new ModelAndView("pizzas","pizzas",pizzaService.findAll());
    }
    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("pizza");
        pizzaService.findById(id).ifPresent(pizza -> {
            modelAndView.addObject(pizza);
            modelAndView.addObject("inDollar",euroService.naarDollar(pizza.getPrijs()));
        });
        return modelAndView;
    }
    @GetMapping("prijzen")
    public ModelAndView prijzen(){
        return new ModelAndView("prijzen","prijzen",pizzaService.findUniekePrijzen());
    }
    @GetMapping("prijzen/{prijs}")
    public ModelAndView pizzasMetEenPrijs(@PathVariable BigDecimal prijs){
        return new ModelAndView("prijzen","pizzas",pizzaService.findByPrijs(prijs)).addObject("prijzen");
    }
    @GetMapping("vantotprijs/form")
    public ModelAndView vanTotPrijsForm(){
        return new ModelAndView("vantotprijs").addObject(new VanTotPrijsForm(null, null));
    }
    @GetMapping("vantotprijs")
    public ModelAndView vanTotPrijs(@Valid VanTotPrijsForm form, Errors errors){
        ModelAndView modelAndView = new ModelAndView("vantotprijs");
        if(errors.hasErrors()){
            return modelAndView;
        }
        return modelAndView.addObject("pizzas",pizzaService.findBijPrijsBetween(form.getVan(), form.getTot()));
    }
}
