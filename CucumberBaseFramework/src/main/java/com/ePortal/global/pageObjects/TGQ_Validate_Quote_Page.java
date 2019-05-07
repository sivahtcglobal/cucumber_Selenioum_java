package com.ePortal.global.pageObjects;



import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ePortal.utilities.BaseClass;
import com.ePortal.utilities.MyOwnException;
import com.ePortal.utilities.Report;
import com.ePortal.wrapperClasses.MyWait;
import com.ePortal.wrapperClasses.MyWebElement;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.sym.Name;

public class TGQ_Validate_Quote_Page extends TheGeneralAllPages {

	private static final Logger log = LogManager.getLogger(TGQ_Validate_Quote_Page.class.getName());

	// Page Factory

	@FindBy(how = How.LINK_TEXT, using = "Validate")
	public WebElement validate_button;
	@FindBy(how = How.ID, using = "quoteBean.paperless.writableValue")
	public WebElement signup_paperless;
	@FindBy(how = How.ID, using = "quoteBean.homePhone.area")
	public WebElement prim_num1;
	@FindBy(how= How.ID,using= "quoteBean.homePhone.exchange")
	public WebElement prim_num2;
	@FindBy(how = How.ID, using = "quoteBean.homePhone.station")
	public WebElement prim_num3;
	@FindBy(how = How.ID, using = "quoteBean.primaryAccountAlertsOptInPreference.writableValue")
	public WebElement account_alerts;
	@FindBy(how = How.ID, using = "quoteBean.workPhone.area")
	public WebElement alt_num1;
	@FindBy(how = How.ID, using = "quoteBean.workPhone.exchange")
	public WebElement alt_num2;
	@FindBy(how = How.ID, using = "quoteBean.workPhone.station")
	public WebElement alt_num3;
	@FindBy(how = How.ID, using = "quoteBean.alternateAccountAlertsOptInPreference.writableValue")
	public WebElement alt_account_alert;
	@FindBy(how = How.ID, using = "quoteBean.closingMethod.writableValue")
	public WebElement closing_method;
	@FindBy(how = How.ID, using = "quoteBean.accessEmail.value")
	public WebElement confirm_email;
	@FindBy(how = How.LINK_TEXT, using = "Next")
	public WebElement tgq_next_btn;


	WebDriver ldriver;

	public TGQ_Validate_Quote_Page(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void validatequote(String applicationType) throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {

			Select signup_paperless_val = new Select(signup_paperless);
			signup_paperless_val.selectByVisibleText("No");
			//MyWebElement.enterText(prim_num1, prop.getProperty("TGQUserName"));
			//MyWebElement.enterText(prim_num2, prop.getProperty("TGQUserName"));
			//MyWebElement.enterText(prim_num3, prop.getProperty("TGQUserName"));
			Select account_alerts_val = new Select(account_alerts);
			account_alerts_val.selectByVisibleText("Call");
			//MyWebElement.enterText(alt_num1, prop.getProperty("TGQUserName"));
			//MyWebElement.enterText(alt_num2, prop.getProperty("TGQUserName"));
			//MyWebElement.enterText(alt_num3, prop.getProperty("TGQUserName"));
			Select closing_method_val = new Select(closing_method);
			closing_method_val.selectByVisibleText("Fax");
			tgq_next_btn.click();;
			
			BaseClass.screenShot(System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp
					+ "\\" + "1_validatequote_" + applicationType + ".png");

//			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS",
//					"Successfully_validated_quote '" + applicationType + "' application",
//					System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp + "\\"
//							+ "1_Login_to_" + applicationType + ".png");

		} catch (Exception exp) {
			log.error(exp.getMessage());
			BaseClass.screenShot(System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp
					+ "\\" + "1_Error_in_validating_quote_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
					"<font color=red><b>Error Validating Quote '" + applicationType
							+ "' application: </b></font><br />" + exp.getMessage() + "<br />",
					System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp + "\\"
							+ "1_Error_in_validating_quote_" + applicationType + ".png");
			throwException("Unable To validate Quote " + applicationType + "application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
