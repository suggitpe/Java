package org.suggs.webapps.buildpipeline.pages;

import org.suggs.webapps.buildpipeline.pageobjects.HomePage;
import org.suggs.webapps.buildpipeline.pageobjects.ReleaseManagementPage;
import org.suggs.webapps.buildpipeline.pageobjects.ReleaseVersionForm;
import org.suggs.webapps.buildpipeline.pageobjects.ReleaseVersionShow;

/**
 * High level behaviour of the Pages objects.
 * <p/>
 * User: suggitpe Date: 09/09/11 Time: 18:28
 */

public interface Pages {

    HomePage homePage();

    ReleaseVersionShow releaseVersionShow();

    ReleaseVersionForm releaseVersionForm();

    ReleaseManagementPage releaseManagementPage();

}
