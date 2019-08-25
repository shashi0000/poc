package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VanguardFundsFeesAndMinimumsPage {
	ChromeDriver driver;
	WebDriverWait wait;

	By funds = By.xpath("//span[text()='See which funds charge purchase & redemption fees']");
	By fundElementLocaters = By.xpath("//h4//span");
	By close = By.xpath("//div[@id='layerContent_layer4']//following-sibling::div//a[@title='Close']");

	public VanguardFundsFeesAndMinimumsPage(ChromeDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void navigateToPurchaseRedemptionFees() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(funds));
		driver.findElement(funds).click();
	}

	public List<WebElement> getAllFundWebElements() {

		return driver.findElements(fundElementLocaters);
	}

	public String getFullFundText(WebElement fund) {
		return fund.getText();
	}

	public String getPurchaseFee(String fullFund) {
		return driver.findElement(By.xpath("//span[contains(text(),'" + fullFund
				+ "')]//ancestor::div[@class='row ' or @class='row lastRow']//span[contains(text(),'PURCHASE FEE')]//following-sibling::p"))
				.getText();
	}

	public String getRedemptionFee(String fullFund) {
		return driver.findElement(By.xpath("//span[contains(text(),'" + fullFund
				+ "')]//ancestor::div[@class='row ' or @class='row lastRow']//span[contains(text(),'REDEMPTION FEE')]//following-sibling::p"))
				.getText();
	}

	public void close() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(close));
		driver.findElement(close).click();

	}
}
