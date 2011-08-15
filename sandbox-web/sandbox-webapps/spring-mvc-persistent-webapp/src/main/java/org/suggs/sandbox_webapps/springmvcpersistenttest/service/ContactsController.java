package org.suggs.sandbox_webapps.springmvcpersistenttest.service;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.CounterpartyContactDao;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Controller class for counterparty contacts.
 * <p/>
 * User: suggitpe Date: 04/03/11 Time: 19:21
 */

@Controller
@RequestMapping("/counterparties/{counterpartyId}/contacts")
@SessionAttributes(types = CounterpartyContact.class)
public class ContactsController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ContactsController.class );

    @Autowired
    private CounterpartyDao counterpartyDao;

    @Autowired
    private CounterpartyContactDao counterpartyContactDao;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String setupNewForm( Model aModel ) {
        CounterpartyContact contact = new CounterpartyContact();
        aModel.addAttribute( "counterpartyContact", contact );
        return "contacts/form";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processSubmitNew( @PathVariable("counterpartyId") Long aCounterpartyId, @ModelAttribute("counterpartyContact") CounterpartyContact contact, BindingResult aResult, SessionStatus aStatus ) {
        new CounterpartyContactValidator().validate( contact, aResult );
        if ( aResult.hasErrors() ) {
            return "contacts/form";
        }
        else {
            Counterparty counterparty = counterpartyDao.get( aCounterpartyId );
            counterparty.addCounterpartyContact( contact );
            counterpartyDao.merge( counterparty );
            aStatus.setComplete();
            return "redirect:/counterparties/" + counterparty.getId();
        }
    }

    @RequestMapping(value = "/{contactId}/edit", method = RequestMethod.GET)
    public String setupEditForm( @PathVariable("counterpartyId") Long aCounterpartyId, @PathVariable("contactId") Long aContactId, Model aModel ) {
        Counterparty counterparty = counterpartyDao.get( aCounterpartyId );
        aModel.addAttribute( "counterparty", counterparty );

        CounterpartyContact counterpartyContact = counterpartyContactDao.get( aContactId );
        aModel.addAttribute( "counterpartyContact", counterpartyContact );
        return "contacts/form";
    }

    @RequestMapping(value = "/{contactId}/edit", method = RequestMethod.POST)
    public String processSubmitEdit( @PathVariable("counterpartyId") Long aCounterpartyId, @ModelAttribute CounterpartyContact contact, BindingResult aBindingResult, SessionStatus aStatus ) {
        new CounterpartyContactValidator().validate( contact, aBindingResult );
        if ( aBindingResult.hasErrors() ) {
            return "contacts/form";
        }
        else {
            counterpartyContactDao.merge( contact );
            aStatus.setComplete();
            return "redirect:/counterparties/" + aCounterpartyId;
        }
    }

    @RequestMapping(value = "/{contactId}/edit", method = RequestMethod.DELETE)
    public String processDeleteContact( @PathVariable("counterpartyId") Long aCounterpartyId, @PathVariable("contactId") Long aContactId ) {
        counterpartyContactDao.remove( aContactId );
        return "redirect:/counterparties/" + aCounterpartyId;
    }

}
