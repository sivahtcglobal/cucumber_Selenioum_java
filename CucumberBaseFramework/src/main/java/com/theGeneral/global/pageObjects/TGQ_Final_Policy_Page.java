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
import com.theGeneral.utilities.Excel;
import com.theGeneral.utilities.MyOwnException;
import com.theGeneral.utilities.Report;
import com.theGeneral.wrapperClasses.MyWait;
import com.theGeneral.wrapperClasses.MyWebElement;

public class TGQ_Final_Policy_Page extends TheGeneralAllPages {

	private static final Logger log = LogManager.getLogger(TGQ_Final_Policy_Page.class.getName());

	// Page Factory

	
	@FindBy(how = How.XPATH, using = "//*[@id='quoteSummaryTable']/tbody/tr[3]/td[2]")
	public WebElement quote_num;
	@FindBy(how = How.XPATH, using = "//*[@id='body']/form/table[3]/tbody/tr[3]/td[2]")
	public WebElement policy_num;
	WebDriver ldriver;

	public TGQ_Final_Policy_Page(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void policy_page(String applicationType) throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {
			MyWait.until(ldriver, "ELEMENT_VISIBLE", 50, policy_num);
			System.out.println(quote_num.getText()+"quote_num");
			System.out.println(policy_num.getText()+"policy_num");
			Excel.writeToExcelSheet(policy_num.getText(), quote_num.getText());
			
			BaseClass.screenShot("./Results/Screenshots_" + testRunTimeStamp + "/" + "1_policy_page_" + applicationType + ".png");

			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS",
					"Successfully_Created_Policy_ '" + applicationType + "' application",
					"./Results/Screenshots_"+ testRunTimeStamp + "/"
							+ "1_policy_page_" + applicationType + ".png");

		} catch (Exception exp) {
			log.error(exp.getMessage());
			BaseClass.screenShot("./Results/Screenshots_" + testRunTimeStamp + "/" + "1_Error_in_policy_page_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
					"<font color=red><b>Error_in_policyPage '" + applicationType
							+ "' application: </b></font><br />" + exp.getMessage() + "<br />",
							"./Results/Screenshots_"+ testRunTimeStamp + "/"
							+ "1_Error_in_policy_page_" + applicationType + ".png");
			throwException("Unable To get Policy Number " + applicationType + "application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
