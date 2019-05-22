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

			if (!currentHash.get("PolicyType").equals("Bond - No Credit")) {
				Select ownership_type_veh = new Select(ownership_type);
				ownership_type_veh.selectByVisibleText("Owned");
				Select prior_damage_veh = new Select(prior_damage);
				prior_damage_veh.selectByVisibleText("No");
			}
			Select veh_reg_state_veh = new Select(veh_reg_state);
			veh_reg_state_veh.selectByVisibleText(currentHash.get("QuoteState"));
			//recalculate_button.click();
			BaseClass.screenShot("./Results/Screenshots_" + testRunTimeStamp + "/" + "1_vehiclesedit_" + applicationType + ".png");

			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS",
					"Successfully_vehiclesedit_ '" + applicationType + "' application",
					"./Results/Screenshots_"+ testRunTimeStamp + "/"
							+ "1_vehiclesedit_" + applicationType + ".png");
			next_button.click();
			 Thread.sleep(4000);
		} catch (Exception exp) {
			log.error(exp.getMessage());
			BaseClass.screenShot("./Results/Screenshots_" + testRunTimeStamp + "/" + "1_Error_in_vehiclesedit_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
					"<font color=red><b>Error_in_vehiclesedit '" + applicationType + "' application: </b></font><br />"
							+ exp.getMessage() + "<br />",
							"./Results/Screenshots_"+ testRunTimeStamp + "/"
							+ "1_Error_in_vehiclesedit_" + applicationType + ".png");
			throwException(
					"Unable To Edit in Vehicles tab " + applicationType + "application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
