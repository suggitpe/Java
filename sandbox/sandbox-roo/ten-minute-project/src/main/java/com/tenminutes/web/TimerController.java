package com.tenminutes.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.tenminutes.Timer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "timers", formBackingObject = Timer.class)
@RequestMapping("/timers")
@Controller
public class TimerController {
}
