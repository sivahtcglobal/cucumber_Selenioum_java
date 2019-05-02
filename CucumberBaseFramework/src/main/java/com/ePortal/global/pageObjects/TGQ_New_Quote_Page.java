package com.ePortal.global.pageObjects;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
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

public class TGQ_New_Quote_Page extends EportalAllPages {

	private static final Logger log = LogManager.getLogger(TGQ_New_Quote_Page.class.getName());

	// Page Factory
	@FindBy(xpath= "//*[@name='newQuoteBean.allowableStates.realValue']")
	public WebElement tgq_state;
	@FindBy(how = How.ID, using = "newQuoteBean.policyTypeHelper.value")
	public WebElement tgq_policy_type;
	@FindBy(how = How.ID, using = "referralLocationCodeInputField")
	public WebElement tgq_zip_code;
	@FindBy(how = How.LINK_TEXT, using = "Next")
	public WebElement tgq_next;
	@FindBy(how = How.NAME, using = "newQuoteBean.effectiveDate.mo")
	public WebElement tgq_eff_mnth;
	@FindBy(how = How.NAME, using = "newQuoteBean.effectiveDate.dy")
	public WebElement tgq_eff_date;
	@FindBy(how = How.NAME, using = "newQuoteBean.effectiveDate.yr")
	public WebElement tgq_eff_year;
	
	WebDriver ldriver;

	public TGQ_New_Quote_Page(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void newquote(String applicationType) throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {
		         for (String handle1 : ldriver.getWindowHandles()) {
		          ldriver.switchTo().window(handle1);
		          }
		         ldriver.manage().window().maximize() ;
				Select state = new Select(tgq_state);
				state.selectByVisibleText( prop.getProperty("tgq_state"));
				//MyWebElement.enterText(tgq_state, prop.getProperty("tgq_state"));
				Select policy_type = new Select(tgq_policy_type);
				policy_type.selectByVisibleText( prop.getProperty("tgq_policy_type"));
				//MyWebElement.enterText(tgq_policy_type, prop.getProperty("tgq_policy_type"));
				MyWebElement.enterText(tgq_zip_code, prop.getProperty("tgq_zip_code"));
				//MyWebElement.enterText(tgq_eff_mnth, prop.getProperty("tgq_eff_mnth"));
				//MyWebElement.enterText(tgq_eff_date, prop.getProperty("TGQUserName"));
				//MyWebElement.enterText(tgq_eff_year, prop.getProperty("TGQPassword"));

				MyWait.until(dr, "ELEMENT_CLICKABLE", 30, tgq_next);
				MyWebElement.clickOnButton(tgq_next);


			BaseClass.screenShot(System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp
					+ "\\" + "1_Login_to_" + applicationType + ".png");

			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS",
					"Successfully_Logged into '" + applicationType + "' application",
					System.getProperty("user.dir") + "\\Results\\Screenshots" + "_" + testRunTimeStamp + "\\"
							+ "1_Login_to_" + applicationType + ".png");

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
