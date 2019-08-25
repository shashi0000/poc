package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VanguardMutualFundsPage {
	ChromeDriver driver;
	WebDriverWait wait;
	By fees = By.xpath("//span[text()='Get details on fees & minimums for Vanguard mutual funds']");

	public VanguardMutualFundsPage(ChromeDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void NavigateFeesAndMinimum() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(fees));
		driver.findElement(fees).click();
	}
}