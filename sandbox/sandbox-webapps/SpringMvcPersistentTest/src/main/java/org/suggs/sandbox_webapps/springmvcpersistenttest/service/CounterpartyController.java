package org.suggs.sandbox_webapps.springmvcpersistenttest.service;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.CounterpartyDao;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;
import org.suggs.sandbox_webapps.springmvcpersistenttest.validators.CounterpartyValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for Counterparties that will map the relevant URL to some core function and then back to a new URL.
 * <p/>
 * User: suggitpe Date: 15/02/11 Time: 14:34
 */
@Controller
@RequestMapping("/counterparties")
@SessionAttributes(types = Counterparty.class)
public class CounterpartyController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyController.class );

    @Autowired
    private CounterpartyDao counterpartyDao;

    @InitBinder
    public void setAllowedFields( WebDataBinder dataBinder ) {
        dataBinder.setDisallowedFields( "id" );
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView handleCounterparties() {
        LOG.debug( "fetching all counterparties" );
        ModelAndView mav = new ModelAndView();
        mav.addObject( "counterparties", counterpartyDao.getAll() );
        return mav;
    }

    @RequestMapping(value = "/{counterpartyId}", method = RequestMethod.GET)
    public ModelAndView findCounterparty( @PathVariable("counterpartyId") Long aCounterpartyId ) {
        LOG.debug( "fetching counterpatry " + aCounterpartyId );
        ModelAndView mav = new ModelAndView( "counterparties/show" );
        mav.addObject( counterpartyDao.get( aCounterpartyId ) );
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String setupNewForm( Model aModel ) {
        Counterparty counterparty = new Counterparty();
        aModel.addAttribute( counterparty );
        return "counterparties/form";
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processSubmitNew( @ModelAttribute Counterparty counterparty, BindingResult aResult, SessionStatus aStatus ) {
        new CounterpartyValidator().validate( counterparty, aResult );
        if ( aResult.hasErrors() ) {
            return "counterparties/form";
        }
        else {
            counterpartyDao.save( counterparty );
            aStatus.setComplete();
            return "redirect:/counterparties/" + counterparty.getId();
        }
    }

    @RequestMapping(value = "/{counterpartyId}/edit", method = RequestMethod.GET)
    public String setupEditForm( @PathVariable("counterpartyId") Long aCounterpartyId, Model aModel ) {
        Counterparty cp = counterpartyDao.get( aCounterpartyId );
        aModel.addAttribute( cp );
        return "counterparties/form";
    }

    @RequestMapping(value = "/{counterpartyId}/edit", method = RequestMethod.POST)
    public String processSubmitEdit( @ModelAttribute Counterparty counterparty, BindingResult aBindingResult, SessionStatus aStatus ) {
        new CounterpartyValidator().validate( counterparty, aBindingResult );
        if ( aBindingResult.hasErrors() ) {
            return "counterparties/form";
        }
        else {
            counterpartyDao.merge( counterparty );
            aStatus.setComplete();
            return "redirect:/counterparties/" + counterparty.getId();
        }
    }

    @RequestMapping(value = "/{counterpartyId}/edit", method = RequestMethod.DELETE)
    public String processDeleteCounterparty( @PathVariable("counterpartyId") Long aCounterpartyId ) {
        counterpartyDao.remove( aCounterpartyId );
        return "redirect:/counterparties";
    }

}
