package org.suggs.roo.pizzashop.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.suggs.roo.pizzashop.domain.Pizza;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "pizzas", formBackingObject = Pizza.class)
@RequestMapping("/pizzas")
@Controller
public class PizzaController {
}
