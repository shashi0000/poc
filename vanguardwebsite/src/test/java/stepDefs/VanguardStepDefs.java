package stepDefs;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import interfacee.VanguardInterface;

public class VanguardStepDefs {
	VanguardInterface vanguardInterface = new VanguardInterface();

	@Given("^public user launches vanguard investor site$")
	public void publicUserLaunchesVanguardInvestorSite() throws Throwable {
		vanguardInterface.launchesVanguardsite();
	}

	@Given("^navigate to personal investor site$")
	public void navigateToPersonalInvestorSite() throws Throwable {
		vanguardInterface.navigateToPersonalInvestor();
	}

	@When("^the user navigate to purchase & redemption fees page$")
	public void theUserNavigateToVanguardFundsWithPurchaseRedemptionFeesPage() throws Throwable {
		vanguardInterface.navigateToVanguardMutualFunds();
		vanguardInterface.NavigateToFeesAndMiniumsPage();
	}

	@When("^get details of the funds with respective purchase & redemption fees$")
	public void getDetailsOfTheFundsWithRespectivePurchaseRedemptionFees(List<String> fundsList) throws Throwable {
		vanguardInterface.openFeesAndMinimumsWindow();
		vanguardInterface.getPurchaseRedemptionFeesForFunds(fundsList);
	}

	@Then("^they verify purchase fee & redemption fee in Fees & minimum tab matches with that of purchase & redemption fees page$")
	public void theyVerifyPurchaseFeeRedemptionFeeInFeesMinimumTabMatchesWithThatOfPurchaseRedemptionFeesPage()
			throws Throwable {
		vanguardInterface.navigateToFeesAndMinimumTabInFundDetailsPageAndVerifyPurchaseAndRedemptionFee();
	}

}
