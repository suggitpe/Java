package org.suggs.sandbox_webapps.springmvcpersistenttest.service;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.CounterpartyDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 15/02/11
 * Time: 14:34
 */
@Controller
@RequestMapping("/counterparties")
public class CounterpartyController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyController.class );

    @Autowired
    private CounterpartyDao counterpartyDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView handleCounterparties() {
        ModelAndView mav = new ModelAndView();
        mav.addObject( "counterparties", counterpartyDao.getAll() );
        return mav;
    }
}
