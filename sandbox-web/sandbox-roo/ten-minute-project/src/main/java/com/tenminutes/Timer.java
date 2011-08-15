package com.tenminutes;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooEntity
public class Timer {

    @NotNull
    private String message;
}
