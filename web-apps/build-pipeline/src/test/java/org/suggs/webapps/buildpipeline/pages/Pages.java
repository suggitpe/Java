package org.suggs.webapps.buildpipeline.pages;

import org.suggs.webapps.buildpipeline.pages.pageobjects.HomePage;
import org.suggs.webapps.buildpipeline.pages.pageobjects.ReleaseManagementPage;
import org.suggs.webapps.buildpipeline.pages.pageobjects.ReleaseVersionForm;
import org.suggs.webapps.buildpipeline.pages.pageobjects.ReleaseVersionShow;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 09/09/11
 * Time: 18:28
 */
public interface Pages {

    HomePage homePage();

    ReleaseVersionShow releaseVersionShow();

    ReleaseVersionForm releaseVersionForm();

    ReleaseManagementPage releaseManagementPage();

}
