package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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

    public ReleaseVersionForm( WebDriverProvider aWebDriver ) {
        super( aWebDriver );
    }

    protected String expectedPageTitle() {
        return FORM_TITLE;
    }

    public void setDescription( String aDescription ) {
        WebElement descElem = getWebDriver().findElement( By.id( DESC_FIELD ) );
        descElem.clear();
        descElem.sendKeys( aDescription );
        LOG.debug( "Updated description to [" + descElem.getText() + "]" );
    }

    public void completeNew() {
        getWebDriver().findElement( By.id( NEW_BUTTON ) ).click();
    }

    public void completeUpdate() {
        getWebDriver().findElement( By.id( UPDATE_BUTTON ) ).click();
    }

    public void completeDelete() {
        getWebDriver().findElement( By.id( DELETE_BUTTON ) ).click();
    }

    public void isShownInNewForm() {
        String pageTitle = getWebDriver().findElement( By.id( "title" ) ).getText();
        assertThat( pageTitle, equalTo( "New " + FORM_TITLE ) );
    }

    public void isShownInEditForm() {
        String pageTitle = getWebDriver().findElement( By.id( "title" ) ).getText();
        assertThat( pageTitle, equalTo( "Edit " + FORM_TITLE ) );
    }
}
