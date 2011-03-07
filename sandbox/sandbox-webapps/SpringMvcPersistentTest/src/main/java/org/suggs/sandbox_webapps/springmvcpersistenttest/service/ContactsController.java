package org.suggs.sandbox_webapps.springmvcpersistenttest.service;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.CounterpartyDao;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.CounterpartyContact;
import org.suggs.sandbox_webapps.springmvcpersistenttest.validators.CounterpartyContactValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Controller class for counterparty contacts.
 * <p/>
 * User: suggitpe
 * Date: 04/03/11
 * Time: 19:21
 */

@Controller
@RequestMapping("/counterparties/{counterpartyId}/contacts")
public class ContactsController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ContactsController.class );

    @Autowired
    private CounterpartyDao counterpartyDao;

    @InitBinder
    public void setAllowedFields( WebDataBinder dataBinder ) {
        dataBinder.setDisallowedFields( "id" );
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String setupNewForm( @PathVariable("counterpartyId") Long aCounterpartyId, Model aModel ) {
        CounterpartyContact contact = new CounterpartyContact();
        aModel.addAttribute( "contact", contact );
        return "contacts/form";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processSubmitNew( @PathVariable("counterpartyId") Long aCounterpartyId, @ModelAttribute("contact") CounterpartyContact contact, BindingResult aResult, SessionStatus aStatus ) {
        new CounterpartyContactValidator().validate( contact, aResult );
        if ( aResult.hasErrors() ) {
            return "contacts/form";
        } else {
            Counterparty counterparty = counterpartyDao.get( aCounterpartyId );
            counterparty.addCounterpartyContact( contact );
            counterpartyDao.merge( counterparty );
            aStatus.setComplete();
        }
        return "contacts/form";
    }

}
