package com.theGeneral.stepDefinations;

import com.theGeneral.global.pageObjects.MarsApplicationLoginPage;
import com.theGeneral.global.pageObjects.TGQ_Search_Quote_Page;
import com.theGeneral.utilities.BaseClass;
import com.theGeneral.utilities.MyOwnException;
import com.theGeneral.utilities.Report;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuoteSearchUsingQuoteNumber extends BaseClass {

	MarsApplicationLoginPage quoteSearch_page = new MarsApplicationLoginPage(dr);
	TGQ_Search_Quote_Page search_page = new TGQ_Search_Quote_Page(dr);
	Report getReports;

	public QuoteSearchUsingQuoteNumber() {
		super();

	}

	@Then("Provide the Quote number in \"([^\"]*)\"$")
	public void enterQuote(String applicationType) throws InterruptedException, MyOwnException {

		parentTestCase = Report.makeTestCaseEntry(report, "QuoteSearchUsingQuoteNumber");
		Report.assignCategoryToTestCase(parentTestCase, "QuoteSearchUsingQuoteNumber");

		search_page.enterquote(applicationType);

	}

}
