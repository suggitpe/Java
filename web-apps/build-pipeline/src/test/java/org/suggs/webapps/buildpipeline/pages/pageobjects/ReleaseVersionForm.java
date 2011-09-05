package org.suggs.webapps.buildpipeline.pages.pageobjects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Class to encapsulate the Release ReleaseVersionImpl Form.
 * <p/>
 * User: suggitpe
 * Date: 07/07/11
 * Time: 19:34
 */

public final class ReleaseVersionForm extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionForm.class );

    private static final String FORM_TITLE = "Release ReleaseVersionImpl";
    private static final String DESC_FIELD = "descriptionField";
    private static final String NEW_BUTTON = "addButton";
    private static final String UPDATE_BUTTON = "updateButton";
    private static final String DELETE_BUTTON = "deleteButton";

    public ReleaseVersionForm( WebDriver aWebDriver ) {
        super( aWebDriver );
    }

    protected String expectedPageTitle() {
        return FORM_TITLE;
    }

    public void setDescription( String aDescription ) {
        WebElement descElem = getWebDriver().findElement( By.id( DESC_FIELD ) );
        descElem.clear();
        descElem.sendKeys( aDescription );
    }

    public void setComponentVersion( String aComponentName, String aVersionNumber ) {
        LOG.debug( "Looking for element [componentVersions" + aComponentName + "]" );
        WebElement elem = getWebDriver().findElement( By.id( "componentVersions" + aComponentName ) );


        throw new NotImplementedException();
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

    public void openForNew() {
        getWebDriver().navigate().to( BASE_URL + "/release-management/new" );
    }
}
