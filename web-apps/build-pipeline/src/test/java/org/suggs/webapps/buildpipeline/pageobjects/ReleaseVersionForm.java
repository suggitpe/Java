package org.suggs.webapps.buildpipeline.pageobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.selenium.Selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class to encapsulate the Release ReleaseVersionImpl Form.
 * <p/>
 * User: suggitpe Date: 07/07/11 Time: 19:34
 */

public final class ReleaseVersionForm extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionForm.class );

    private static final String FORM_TITLE = "Release Version";
    private static final String DESC_FIELD = "descriptionField";
    private static final String NEW_BUTTON = "addButton";
    private static final String UPDATE_BUTTON = "updateButton";
    private static final String DELETE_BUTTON = "deleteButton";

    public ReleaseVersionForm( Selenium aSelenium ) {
        super( aSelenium );
    }

    protected String expectedPageTitle() {
        return FORM_TITLE;
    }

    public void setDescription( String aDescription ) {
        getSelenium().type( "id=" + DESC_FIELD, aDescription );
    }

    public void setComponentVersion( String aComponentName, String aVersionNumber ) {
        LOG.debug( "Setting version of component [" + aComponentName + "] to [" + aVersionNumber + "]" );
        getSelenium().select( "id=componentVersions" + aComponentName, aVersionNumber );
    }

    public void completeNew() {
        getSelenium().click( "id=" + NEW_BUTTON );
    }

    public void completeUpdate() {
        getSelenium().click( "id=" + UPDATE_BUTTON );
    }

    public void completeDelete() {
        getSelenium().click( "id=" + DELETE_BUTTON );
    }

    public boolean isShownInNewForm() {
        String pageTitle = getSelenium().getText( "id=title" );
        return pageTitle.equals( "New " + FORM_TITLE );
    }

    public boolean isShownInEditForm() {
        String pageTitle = getSelenium().getText( "id=title" );
        return pageTitle.equals( "Edit " + FORM_TITLE );
    }

    public void openForNew() {
        getSelenium().open( BASE_URL + "/release-management/new" );
        assertThat( isShownInNewForm(), is( true ) );
    }
}
