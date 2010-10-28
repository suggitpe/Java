// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.suggs.roo.pizzashop.web;

import java.io.UnsupportedEncodingException;
import java.lang.Long;
import java.lang.String;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.suggs.roo.pizzashop.domain.Base;

privileged aspect BaseController_Roo_Controller {
    
    @Autowired
    private GenericConversionService BaseController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String BaseController.create(@Valid Base base, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("base", base);
            return "bases/create";
        }
        base.persist();
        return "redirect:/bases/" + encodeUrlPathSegment(base.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String BaseController.createForm(Model model) {
        model.addAttribute("base", new Base());
        return "bases/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String BaseController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("base", Base.findBase(id));
        model.addAttribute("itemId", id);
        return "bases/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String BaseController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("bases", Base.findBaseEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Base.countBases() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("bases", Base.findAllBases());
        }
        return "bases/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String BaseController.update(@Valid Base base, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("base", base);
            return "bases/update";
        }
        base.merge();
        return "redirect:/bases/" + encodeUrlPathSegment(base.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String BaseController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("base", Base.findBase(id));
        return "bases/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String BaseController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Base.findBase(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/bases?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    Converter<Base, String> BaseController.getBaseConverter() {
        return new Converter<Base, String>() {
            public String convert(Base base) {
                return new StringBuilder().append(base.getName()).toString();
            }
        };
    }
    
    @PostConstruct
    void BaseController.registerConverters() {
        conversionService.addConverter(getBaseConverter());
    }
    
    private String BaseController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
