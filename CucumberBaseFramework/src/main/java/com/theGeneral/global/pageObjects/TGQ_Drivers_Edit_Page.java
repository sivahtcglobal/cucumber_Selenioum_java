package com.theGeneral.global.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.theGeneral.utilities.BaseClass;
import com.theGeneral.utilities.MyOwnException;
import com.theGeneral.utilities.Report;
import com.theGeneral.wrapperClasses.MyWait;
import com.theGeneral.wrapperClasses.MyWebElement;

public class TGQ_Drivers_Edit_Page extends TheGeneralAllPages {

	private static final Logger log = LogManager.getLogger(TGQ_Drivers_Edit_Page.class.getName());

	// Page Factory

	@FindBy(how = How.ID, using = "drivers[0].stateLicensed.writableValue")
	public WebElement state_lic;
	@FindBy(how = How.ID, using = "drivers[0].socialSecurityNumber.value")
	public WebElement ssn;
	@FindBy(how = How.ID, using = "drivers[0].driverLicenseNumber.value")
	public WebElement dl;
	@FindBy(how = How.LINK_TEXT, using = "Recalculate")
	public WebElement recalculate_button;
	@FindBy(how = How.LINK_TEXT, using = "Next")
	public WebElement next_button;

	WebDriver ldriver;

	public TGQ_Drivers_Edit_Page(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void driversedit(String applicationType) throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {

			Select state_lic_dr = new Select(state_lic);
			state_lic_dr.selectByVisibleText(currentHash.get("QuoteState"));
			MyWebElement.enterText(ssn, currentHash.get("SSN"));
			MyWebElement.enterText(dl, currentHash.get("DL"));
			recalculate_button.click();

			BaseClass.screenShot("./Results/Screenshots_" + testRunTimeStamp + "/"+ "1_Drivers_are_edited_" + applicationType + ".png");

			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS",
					"Successfully_Logged_into_ '" + applicationType + "' application",
					"./Results/Screenshots_"+ testRunTimeStamp + "/"
							+ "1_Drivers_are_edited_" + applicationType + ".png");
			next_button.click();
			 Thread.sleep(4000);
		} catch (Exception exp) {
			log.error(exp.getMessage());
			BaseClass.screenShot("./Results/Screenshots_" + testRunTimeStamp + "/" + "1_Error_in_Drivers_editing_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
					"<font color=red><b>Error_in_Drivers_editing '" + applicationType
							+ "' application: </b></font><br />" + exp.getMessage() + "<br />",
							"./Results/Screenshots_"+ testRunTimeStamp + "/"
							+ "1_Error_in_Drivers_editing_" + applicationType + ".png");
			throwException("Unable To Edit Driver/s " + applicationType + "application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
