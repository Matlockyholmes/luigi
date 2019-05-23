package be.vdab.luigi.services;

import be.vdab.luigi.domain.Pizza;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PizzaService {
    long create(Pizza pizza);
    void update(Pizza pizza);
    void delete(long id);
    List<Pizza> findAll();
    Optional<Pizza> findById(long id);
    List<Pizza> findBijPrijsBetween(BigDecimal van, BigDecimal tot);
    long findAantalPizza();
    List<BigDecimal> findUniekePrijzen();
    List<Pizza> findByPrijs(BigDecimal prijs);
}