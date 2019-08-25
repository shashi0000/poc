package interfacee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pageobject.FundDetailsPage;
import pageobject.LaunchVanguardPage;
import pageobject.PersonalInvestorSite;
import pageobject.VanguardFundsFeesAndMinimumsPage;
import pageobject.VanguardMutualFundsPage;

public class VanguardInterface {

	static {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\shash\\chromedriver.exe");
	}

	ChromeDriver driver = new ChromeDriver();

	LaunchVanguardPage launchVanguardpage;
	PersonalInvestorSite personalInvestorSite;
	VanguardMutualFundsPage vanguardMutualFundsPage;
	VanguardFundsFeesAndMinimumsPage vanguardFundsFeesAndMinimumsPage;
	FundDetailsPage fundDetailsPage;

	public void launchesVanguardsite() {
		launchVanguardpage = new LaunchVanguardPage(driver);
		launchVanguardpage.launchesVanguard();
	}

	public void navigateToPersonalInvestor() {
		launchVanguardpage.navigateToPersonalInvestor();
	}

	public void navigateToVanguardMutualFunds() {
		personalInvestorSite = new PersonalInvestorSite(driver);
		personalInvestorSite.navigateToVanguardMutualFunds();
	}

	public void NavigateToFeesAndMiniumsPage() {
		vanguardMutualFundsPage = new VanguardMutualFundsPage(driver);
		vanguardMutualFundsPage.NavigateFeesAndMinimum();
	}

	public void openFeesAndMinimumsWindow() {
		vanguardFundsFeesAndMinimumsPage = new VanguardFundsFeesAndMinimumsPage(driver);
		vanguardFundsFeesAndMinimumsPage.navigateToPurchaseRedemptionFees();
	}

	public void getPurchaseRedemptionFeesForFunds(List<String> fundsList) throws InterruptedException {
		List<WebElement> fundElements = vanguardFundsFeesAndMinimumsPage.getAllFundWebElements();
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet Sheet = workBook.createSheet("fundDetails");

		int rowCount = 0;
		for (WebElement fund : fundElements) {
			String fullFund = vanguardFundsFeesAndMinimumsPage.getFullFundText(fund);
			String fundSymbol = fullFund.substring(fullFund.indexOf("(") + 1, fullFund.indexOf(")"));
			if (fundsList.contains(fundSymbol)) {
				HSSFRow row = Sheet.createRow(rowCount++);

				Cell cell;
				cell = row.createCell(0);
				cell.setCellValue(fundSymbol);

				fullFund = fullFund.split("™")[0];
				cell = row.createCell(1);
				cell.setCellValue(vanguardFundsFeesAndMinimumsPage.getPurchaseFee(fullFund));
				cell = row.createCell(2);
				cell.setCellValue(vanguardFundsFeesAndMinimumsPage.getRedemptionFee(fullFund));
			}
		}

		try {
			String home = System.getProperty("user.dir") + "/src/test/resources/";
			FileOutputStream outputStream = new FileOutputStream(new File(home + "fundswithFee.xls"));
			workBook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		vanguardFundsFeesAndMinimumsPage.close();
	}

	public void navigateToFeesAndMinimumTabInFundDetailsPageAndVerifyPurchaseAndRedemptionFee()
			throws InterruptedException {
		fundDetailsPage = new FundDetailsPage(driver);
		int rowCount = 0;

		HSSFWorkbook workbook = null;
		try {
			String home = System.getProperty("user.dir") + "/src/test/resources/";
			FileInputStream inputStream = new FileInputStream(new File(home + "fundswithFee.xls"));
			workbook = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HSSFSheet sheet = workbook.getSheet("fundDetails");
		rowCount = 0;
		HSSFRow row = sheet.getRow(rowCount);
		rowCount = rowCount + 1;
		Cell cell = row.getCell(0);

		while (row != null && cell.getStringCellValue() != "") {
			String fund = cell.getStringCellValue();
			cell = row.getCell(1);
			String purchaseFee = cell.getStringCellValue();
			cell = row.getCell(2);
			String redemptionFee = cell.getStringCellValue();

			fundDetailsPage.searchFund(fund);
			fundDetailsPage.clickOnFundDisplayed(fund);
			fundDetailsPage.navigateToFeesAndMinimumTab();
			Assert.assertTrue("Purchase fee not found", fundDetailsPage.verifyPurchaseFee(purchaseFee));
			Assert.assertTrue("Redemption fee not found", fundDetailsPage.verifyRedemptionFee(redemptionFee));

			row = sheet.getRow(rowCount);
			rowCount = rowCount + 1;
			if (row != null)
				cell = row.getCell(0);
		}
		fundDetailsPage.quit();

	}

}
