package org.suggs.sandbox_webapps.springmvcpersistenttest.service;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.CounterpartyDao;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.CounterpartyContact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        Counterparty counterparty = counterpartyDao.get( aCounterpartyId );
        CounterpartyContact contact = new CounterpartyContact();
        counterparty.addCounterpartyContact( contact );
        aModel.addAttribute( "contact", contact );
        return "contacts/form";
    }

}
