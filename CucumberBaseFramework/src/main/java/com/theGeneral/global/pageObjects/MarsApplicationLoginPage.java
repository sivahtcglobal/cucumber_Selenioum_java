package com.theGeneral.global.pageObjects;

import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.theGeneral.utilities.BaseClass;
import com.theGeneral.utilities.MyOwnException;
import com.theGeneral.utilities.Report;
import com.theGeneral.wrapperClasses.MyWait;
import com.theGeneral.wrapperClasses.MyWebElement;

public class MarsApplicationLoginPage extends TheGeneralAllPages {

	private static final Logger log = LogManager.getLogger(MarsApplicationLoginPage.class.getName());

	// Page Factory

	@FindBy(how = How.CLASS_NAME, using = "OrangeGradientButton")
	public WebElement signin_button;

	@FindBy(how = How.NAME, using = "agencyid")
	public WebElement mars_agencyid;

	@FindBy(how = How.NAME, using = "pwd")
	public WebElement mars_password;

	WebDriver ldriver;

	public MarsApplicationLoginPage(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void login(String applicationType) throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {

			MyWebElement.enterText(mars_agencyid, currentHash.get("Username"));
			MyWebElement.enterText(mars_password, currentHash.get("Password"));
			BaseClass.screenShot("../ws/CucumberBaseFramework/Results/Screenshots_" + testRunTimeStamp + "/" + "1_Login_to_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS","Successfully_Logged into '" + applicationType + "' application","../ws/CucumberBaseFramework/Results/Screenshots_" + testRunTimeStamp + "/"	+ "1_Login_to_" + applicationType + ".png");
			MyWait.until(dr, "ELEMENT_CLICKABLE", 30, signin_button);
			MyWebElement.clickOnButton(signin_button);

		} catch (Exception exp) {
			log.error(exp.getMessage());
			BaseClass.screenShot(System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp
					+ "\\" + "1_Error_Logging_into_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
					"<font color=red><b>Error while Logging into '" + applicationType
							+ "' application: </b></font><br />" + exp.getMessage() + "<br />",
					System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp + "\\"
							+ "1_Error_Logging_into_" + applicationType + ".png");
			throwException("Unable To login to the " + applicationType + "application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
