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

public class MarsHomePage extends TheGeneralAllPages {

	private static final Logger log = LogManager.getLogger(MarsHomePage.class.getName());

	// Page Factory

	@FindBy(how = How.XPATH, using = "//*[@id='maincontent']/table/tbody/tr[2]/td/table/tbody/tr/td[1]/div[1]/div/div/div/div/div/div/div/div/div/table/tbody/tr[3]/td/input")
	public WebElement new_Quote;
	@FindBy(how = How.XPATH, using = "//*[@id='maincontent']/table/tbody/tr[2]/td/table/tbody/tr/td[1]/div[1]/div/div/div/div/div/div/div/div/div/table/tbody/tr[5]/td/input")
	public WebElement quote_Search;
	WebDriver ldriver;

	public MarsHomePage(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void homePage(String applicationType) throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {

			BaseClass.screenShot("./Results/Screenshots_" + testRunTimeStamp + "/" + "1_HomePage_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "PASS",
					"Successfully '" + applicationType + "' application",
					"./Results/Screenshots_"+ testRunTimeStamp + "/"
							+ "1_HomePage_" + applicationType + ".png");
			if (applicationType.equals("New Quote")) {
				
				MyWebElement.clickOnButton(new_Quote);
			} else if (applicationType.equals("QuoteSearch")) {
				
				MyWebElement.clickOnButton(quote_Search);

			}

		} catch (Exception exp) {
			log.error(exp.getMessage());
			BaseClass.screenShot("./Results/Screenshots_" + testRunTimeStamp + "/"+ "1_Error_" + applicationType + ".png");
			Report.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
					"<font color=red><b>Error while  '" + applicationType
							+ "' application: </b></font><br />" + exp.getMessage() + "<br />",
							"./Results/Screenshots_"+ testRunTimeStamp + "/"
							+ "1_Error_" + applicationType + ".png");
			throwException("Unable To  " + applicationType + "application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
