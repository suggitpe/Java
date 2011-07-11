package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the Release Version Form.
 * <p/>
 * User: suggitpe
 * Date: 07/07/11
 * Time: 19:34
 */

public final class ReleaseVersionForm extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionForm.class );

    private static final String FORM_TITLE = "Release Version";
    private static final String DESC_FIELD = "descriptionField";
    private static final String NEW_BUTTON = "addButton";
    private static final String UPDATE_BUTTON = "updateButton";
    private static final String DELETE_BUTTON = "deleteButton";

    public ReleaseVersionForm( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
    }

    public void isShown() {
        //foundTitle( FORM_TITLE );
    }

    public void addDescription( String aDescription ) {
        findElement( By.id( DESC_FIELD ) ).sendKeys( aDescription );
    }

    public void completeNew() {
        findElement( By.id( NEW_BUTTON ) ).click();
    }

    public void completeUpdate() {
        findElement( By.id( UPDATE_BUTTON ) ).click();
    }

    public void completeDelete() {
        findElement( By.id( DELETE_BUTTON ) ).click();
    }
}
