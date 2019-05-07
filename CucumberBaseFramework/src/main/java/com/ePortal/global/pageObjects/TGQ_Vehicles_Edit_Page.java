package com.ePortal.global.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ePortal.utilities.BaseClass;
import com.ePortal.utilities.MyOwnException;
import com.ePortal.utilities.Report;
import com.ePortal.wrapperClasses.MyWait;
import com.ePortal.wrapperClasses.MyWebElement;

public class TGQ_Vehicles_Edit_Page extends TheGeneralAllPages {

	private static final Logger log = LogManager.getLogger(TGQ_Vehicles_Edit_Page.class.getName());

	// Page Factory

	@FindBy(how = How.ID, using = "vehicles[0].ownershipType.writableValue")
	public WebElement ownership_type;
	@FindBy(how = How.ID, using = "vehicles[0].priorDamage.writableValue")
	public WebElement prior_damage;
	@FindBy(how = How.ID, using = "vehicles[0].registrationState.writableValue")
	public WebElement veh_reg_state;
	@FindBy(how = How.LINK_TEXT, using = "Recalculate")
	public WebElement recalculate_button;
	@FindBy(how = How.LINK_TEXT, using = "Next")
	public WebElement next_button;

	WebDriver ldriver;

	public TGQ_Vehicles_Edit_Page(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void vehiclesedit(String applicationType) throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {
			if (!currentHash.get("PolicyType").equals("Bond - No Credit") ) {
			Select ownership_type_veh = new Select(ownership_type);
			ownership_type_veh.selectByVisibleText("Owned");
			Select prior_damage_veh = new Select(prior_damage);
			prior_damage_veh.selectByVisibleText("No");
			}
			Select veh_reg_state_veh = new Select(veh_reg_state);
			veh_reg_state_veh.selectByVisibleText(currentHash.get("QuoteState"));
			recalculate_button.click();
			next_button.click();
			BaseClass.screenShot(System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp
					+ "\\" + "1_vehiclesedit_" + applicationType + ".png");

//			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS",
//					"Successfully_vehiclesedit_ '" + applicationType + "' application",
//					System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp + "\\"
//							+ "1_vehiclesedit_" + applicationType + ".png");

		} catch (Exception exp) {
			log.error(exp.getMessage());
			BaseClass.screenShot(System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp
					+ "\\" + "1_Error_in_vehiclesedit_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
					"<font color=red><b>Error_in_vehiclesedit '" + applicationType
							+ "' application: </b></font><br />" + exp.getMessage() + "<br />",
					System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp + "\\"
							+ "1_Error_in_vehiclesedit_" + applicationType + ".png");
			throwException("Unable To Edit in Vehicles tab " + applicationType + "application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
