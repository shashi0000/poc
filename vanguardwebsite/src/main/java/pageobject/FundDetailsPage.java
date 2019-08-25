package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FundDetailsPage {
	ChromeDriver driver;
	WebDriverWait wait;

	By search = By.xpath("//input[@id='vgc-navBarSearchInput']");
	By feesAndMinimumTab = By.xpath("//a[text()='Fees & Minimums']");
	By verifyTextPurchaseFee = By.xpath("//td[text()='Purchase fee']//following-sibling::td//p");
	By verifyTextRedemptionFee = By.xpath(
			"(//td[text()='Redemption fee']//following-sibling::td)|(//td[text()='Redemption fee']//following-sibling::td//p)");
	By dummyElementForWait = By.xpath("//td[@id='dummy']");

	public FundDetailsPage(ChromeDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void searchFund(String fund) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(search));
		driver.findElement(search).sendKeys(fund);
	}

	public void clickOnFundDisplayed(String fund) {
		By enterTextOfFund = By.xpath("//span[contains(text(),'" + fund + "')]");
		wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(dummyElementForWait));
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterTextOfFund));
		driver.findElement(enterTextOfFund).click();
		wait = new WebDriverWait(driver, 10);
	}

	public void navigateToFeesAndMinimumTab() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(feesAndMinimumTab));
		driver.findElement(feesAndMinimumTab).click();
	}

	public boolean verifyPurchaseFee(String purchaseFee) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(verifyTextPurchaseFee));
		return driver.findElement(verifyTextPurchaseFee).getText().contains(purchaseFee);
	}

	public boolean verifyRedemptionFee(String redemptionFee) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(verifyTextRedemptionFee));
		return driver.findElement(verifyTextRedemptionFee).getText().contains(redemptionFee);
	}

	public void quit() {
		driver.quit();
	}
}