package org.suggs.roo.pizzashop.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.suggs.roo.pizzashop.domain.PizzaOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "pizzaorders", formBackingObject = PizzaOrder.class)
@RequestMapping("/pizzaorders")
@Controller
public class PizzaOrderController {
}
