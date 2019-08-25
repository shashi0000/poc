package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalInvestorSite {
	ChromeDriver driver;
	WebDriverWait wait;
	By investordropdown = By.xpath("//span[text()='Investing']");;
	By vanguardMutualFundsLink = By.xpath("//a/span[text()='Vanguard mutual funds']");;

	public PersonalInvestorSite(ChromeDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void navigateToVanguardMutualFunds() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(investordropdown));
		driver.findElement(investordropdown).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(vanguardMutualFundsLink));
		driver.findElement(vanguardMutualFundsLink).click();
	}
}
