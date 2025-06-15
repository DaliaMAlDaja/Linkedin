package linkedin.linkedin;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	WebDriver driver = new ChromeDriver();
	
	
	
	
	
	
	String URL = "https://www.linkedin.com/login/ar?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin";
	String SignInPage = "https://ww+w.linkedin.com/signup/cold-join?source=guest_homepage-basic_nav-header-signin";
	String Email = "test1htu@gmail.com";
	String Password = "P@ssword.test.1";

	String[] Jobs = { " manual and automation ", "Quality Assurance", "test engineer " };

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	String GmailPassword = "P@@ssword";
	String FirstName = "Tester";
	String LastName = "QA";
	String PhoneNumber = "788442808";

	static int nameCounter = 88;

	Random rand = new Random();

	@BeforeTest
	public void Setup() {

		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test(priority = 1, enabled = false)
	public void join_now() throws InterruptedException {
		WebElement joinNowBtn = driver.findElement(By.id("join_now"));
		joinNowBtn.click();

		WebElement email = driver.findElement(By.id("email-address"));
		email.sendKeys(Email);

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(Password);

//		WebElement rememberMeCheckbox = driver.findElement(By.id("remember-me-checkbox"));
//		rememberMeCheckbox.click(); 

		WebElement join = driver.findElement(By.id("join-form-submit"));
		join.click();

		WebElement firstName = driver.findElement(By.id("first-name"));
		firstName.sendKeys(FirstName);

		WebElement lastName = driver.findElement(By.id("last-name"));
		lastName.sendKeys(LastName + "2");

		WebElement submit = driver.findElement(By.id("join-form-submit"));
		submit.click();

//		WebElement mySelect = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.id("select-register-phone-country")));
//		Select mySelectedCountry = new Select(mySelect);
//		mySelectedCountry.selectByValue("jo");
//
//		WebElement phoneNumber = driver.findElement(By.id("register-verification-phone-number"));
//		phoneNumber.sendKeys(PhoneNumber);
//
//		Thread.sleep(1000);
//		WebElement sendBtn = driver.findElement(By.id("register-phone-submit-button"));
//		sendBtn.click();
//
//		Thread.sleep(1000);

		WebElement mySelect = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("select-register-phone-country")));
		Select mySelectedCountry = new Select(mySelect);

		mySelectedCountry.selectByValue("jo");
		Thread.sleep(2000);

		String mainWindowHandle = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(windowHandle);
			}
		}

		WebElement phoneNumber = driver.findElement(By.id("register-verification-phone-number"));
		phoneNumber.sendKeys(PhoneNumber);

		WebElement sendBtn = driver.findElement(By.id("register-phone-submit-button"));
		sendBtn.click();

		driver.switchTo().window(mainWindowHandle);

		WebElement closeButton = driver.findElement(By.cssSelector("button[aria-label='استبعاد']"));
		closeButton.click();

	}

	@Test(priority = 2, enabled = true)
	public void SigninwithGoogle() throws InterruptedException {

		driver.get("https://www.linkedin.com/login/ar?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");
		Thread.sleep(2000);

		wait.until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title='Sign in with Google Button']")));

		WebElement googleBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button']")));

		googleBtn.click();

		Thread.sleep(1500);

		Set<String> windowHandles = driver.getWindowHandles();
		String currentWindow = driver.getWindowHandle();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(currentWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		String newWindowTitle = driver.getTitle();
		System.out.println("New Window Title: " + newWindowTitle);

		WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
		emailField.sendKeys(Email);
		Thread.sleep(1500);

		WebElement nextButton = driver.findElement(By.id("identifierNext"));
		nextButton.click();

		WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("Passwd")));
		passwordField.sendKeys(GmailPassword);
		Thread.sleep(1500);

		WebElement signInButton = driver.findElement(By.id("passwordNext"));
		signInButton.click();
		Thread.sleep(1500);

		// ✅ هنا بنضغط على زر "Continue as Test"

		// WebElement continueButton =
		// wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm_yes")));

//		continueButton.click();
//		Thread.sleep(3000);
//
		WebElement iframe = driver.findElement(By.cssSelector("iframe.challenge-dialog__iframe"));
		driver.switchTo().frame(iframe);

		// ✅ هنا بنضغط على زر "Continue as Test"
		WebElement continueAsTestBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-as")));
		continueAsTestBtn.click();
		Thread.sleep(2000);

		WebElement submitButton = driver.findElement(By.id("join-form-submit"));
		submitButton.click();
		Thread.sleep(500);

//		WebElement student = wait.until(ExpectedConditions.elementToBeClickable(By.id("ember113")));
//		student.click();

		// تحديد العنصر عن طريق ID
//		WebElement locationInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("typeahead-input-for-location")));
//
//		locationInput.click();
//
//		locationInput.sendKeys("Amman");
//
//		locationInput.sendKeys(Keys.ARROW_DOWN);
//		locationInput.sendKeys(Keys.ENTER);
//
//		locationInput.sendKeys("Amman");
//
//		locationInput.sendKeys(Keys.ARROW_DOWN);
//		locationInput.sendKeys(Keys.ENTER);
	}

	@Test(priority = 3, enabled = true)

	public void Login() throws InterruptedException {

		driver.get(URL);
		WebElement email = driver.findElement(By.id("username"));
		email.sendKeys(Email);
		Thread.sleep(3000);

		WebElement password = driver.findElement(By.id("password"));

		password.sendKeys(Password);
		Thread.sleep(1000);

		WebElement loginButton = driver.findElement(By.xpath("//button[@data-litms-control-urn='login-submit']"));
		Thread.sleep(3000);

		loginButton.click();

//		WebElement agree = driver.findElement(By.id("join-form-submit"));
//		agree.click();
	}

	@Test(priority = 4, enabled = true)
	public void Search() throws InterruptedException {
		Thread.sleep(3000);

		WebElement inputField = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.search-global-typeahead__input")));

		inputField.sendKeys(Jobs[rand.nextInt(Jobs.length)]);

		Thread.sleep(1000);

		inputField.sendKeys(Keys.ARROW_DOWN);
		inputField.sendKeys(Keys.ENTER);

	}

	@Test(priority = 5, enabled = true)
	public void JobsTab() throws InterruptedException {

		List<WebElement> filterButtons = driver.findElements(By.cssSelector(
				"li.search-reusables__primary-filter > button.artdeco-pill.search-reusables__filter-pill-button"));

		if (!filterButtons.isEmpty()) {
			WebElement firstFilter = filterButtons.get(0);
			if (!"true".equals(firstFilter.getDomAttribute("aria-pressed")))
				firstFilter.click();
			Thread.sleep(1000);

		}
	}

	@Test(priority = 6, enabled = true)

	public void filterByLast24Hours() {
	    // الانتظار حتى يظهر زر الفلتر
	    WebElement timePostedFilterButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchFilter_timePostedRange")));
	    timePostedFilterButton.click();

	    // الانتظار حتى يظهر عنصر التصفية "Last 24 Hours"
	    WebElement last24HoursLabel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='timePostedRange-r86400']")));
	    last24HoursLabel.click();

	    // الانتظار حتى يصبح الزر "تطبيق الفلتر" قابلًا للنقر
	    WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	            "//button[contains(@aria-label, 'تطبيق عامل التصفية') or contains(@aria-label, 'Apply current filter')]")));
	    applyButton.click();

	    // الانتظار بعض الوقت بعد التطبيق لتأكد من أن التصفية قد تم تطبيقها
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("searchFilter_timePostedRange")));  // تغيير مع عنصر مناسب
	}

	

	//
	//
	//
	//
	// لازم اشوف المهندس عشان هههاي
	//
	//
	//
	@Test(priority = 7, enabled = true)
	public void activateSearchAlert() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".jobs-search-results__list-item")));

		WebElement alertToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("adToggle_ember237")));

		String ariaChecked = alertToggle.getDomAttribute("aria-checked");
		if ("false".equals(ariaChecked)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", alertToggle);
		} else {
			System.out.println("Alert already activated.");
		}
	}

	@Test(priority = 8, enabled = true)
	public void editProfile() throws InterruptedException {

		// هاي ااذا ضغطت على صورتي من برا
//		WebElement profilePicLink = wait
//				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/in/']")));
//		profilePicLink.click();
//
////		
//
//		// النقر على "عرض الملف الشخصي"
//		WebElement svgButton = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//svg[@data-test-icon='caret-small']")));
//		svgButton.click();

		driver.get("https://www.linkedin.com/in/");

		// about
		WebElement editIntro = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='تحرير المقدمة']")));
		editIntro.click();

		WebElement additionalNameInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'maidenName')]")));
		// int testNumber = 1;

		String newLastName = "Test " + nameCounter;

		additionalNameInput.clear();
		additionalNameInput.sendKeys(newLastName);
		Thread.sleep(3000);

	}

	@Test(priority = 9, enabled = true)
	public void headlineField() throws InterruptedException {

		// العنوان الرئيسي
		WebElement headlineField = wait.until(ExpectedConditions.elementToBeClickable(By.id(
				"gai-text-form-component-profileEditFormElement-TOP-CARD-profile-ACoAAFus-BUB3X-rNj9qJFykiat5dMMp-TyVeJc-headline")));

		headlineField.clear();
		headlineField.sendKeys("This account was created for LinkedIn automation testing using Selenium.");
		headlineField.sendKeys(Keys.ENTER);
		Thread.sleep(1500);

	}

	@Test(priority = 10)
	public void Professionalfield() {

		WebElement industryInput = wait.until(ExpectedConditions.elementToBeClickable(By.id(
				"single-typeahead-entity-form-component-profileEditFormElement-TOP-CARD-profile-ACoAAFus-BUB3X-rNj9qJFykiat5dMMp-TyVeJc-industryId")));

		industryInput.click();

		industryInput.clear();
		industryInput.sendKeys("Testing");

		industryInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	@Test(priority = 11, enabled = true)
	public void SaveButton1() throws InterruptedException {
		Thread.sleep(2000);

		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("button.artdeco-button--primary[data-view-name='profile-form-save']")));
		saveButton.click();
	}

	@Test(priority = 12 , enabled = false)
	public void closeButton() {
		WebElement closeButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("button.artdeco-modal__dismiss[aria-label='استبعاد']")));

		closeButton.click();
	}

	@Test(priority = 13)
	public void editIntroButton() {
		WebElement editIntroButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='تحرير المقدمة']")));
		editIntroButton.click();
	}

	@Test(priority = 14, enabled = true)

	public void PositionButton() throws InterruptedException {

		WebElement PositionButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/add-edit/POSITION')]")));
		Thread.sleep(1500);

		PositionButton.click();

		WebElement positionTitleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
				"single-typeahead-entity-form-component-profileEditFormElement-POSITION-profilePosition-ACoAAFus-BUB3X-rNj9qJFykiat5dMMp-TyVeJc-1-title")));

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", positionTitleField);

		Thread.sleep(1000);
		positionTitleField.sendKeys("QA manual and automation software testing");
		Thread.sleep(1500);

	}

	@Test(priority = 15, enabled = true)
	public void employmentType() throws InterruptedException {

		WebElement employmentTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
				"text-entity-list-form-component-profileEditFormElement-POSITION-profilePosition-ACoAAFus-BUB3X-rNj9qJFykiat5dMMp-TyVeJc-1-employmentStatus")));

		Select employmentSelect = new Select(employmentTypeDropdown);
		employmentSelect.selectByVisibleText("تدريب");
		Thread.sleep(1500);

	}

	@Test(priority = 16, enabled = true)
	public void companyInput() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement companyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
				"single-typeahead-entity-form-component-profileEditFormElement-POSITION-profilePosition-ACoAAFus-BUB3X-rNj9qJFykiat5dMMp-TyVeJc-1-requiredCompany")));

		companyInput.sendKeys("Al Hussein Technical University (HTU)");
		Thread.sleep(2000);

		companyInput.sendKeys(Keys.ARROW_DOWN);
		companyInput.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

	}

	@Test(priority = 17, enabled = true)
	public void selectStartDate() throws InterruptedException {
		WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id(
				"date-range-form-component-profileEditFormElement-POSITION-profilePosition-ACoAAFus-BUB3X-rNj9qJFykiat5dMMp-TyVeJc-1-dateRange-start-date")));
		Select selectMonth = new Select(monthDropdown);
		selectMonth.selectByValue("1");
		Thread.sleep(500);

		WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id(
				"date-range-form-component-profileEditFormElement-POSITION-profilePosition-ACoAAFus-BUB3X-rNj9qJFykiat5dMMp-TyVeJc-1-dateRange-start-date-year-select")));
		Select selectYear = new Select(yearDropdown);
		selectYear.selectByValue("2025");
		Thread.sleep(500);

	}

	@Test(priority = 18, enabled = true)
	public void addSkillTest() throws InterruptedException {

		WebElement addSkillButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("form-component__typeahead-cta-ember195-button")));
		addSkillButton.click();

		WebElement skillInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("input.basic-input.typeahead-cta__input[placeholder*='المهارة']")));

		skillInput.sendKeys("Automation Testing");
		Thread.sleep(500);
		skillInput.sendKeys(Keys.ENTER);
		Thread.sleep(500);

	}

	@Test(priority = 19, enabled = true)
	public void SaveButton() {
		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("button.artdeco-button--primary[data-view-name='profile-form-save']")));
		saveButton.click();

	}

	@Test(priority = 20)
	public void searchForAccount() throws InterruptedException {

		WebElement searchInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.search-global-typeahead__input")));

		searchInput.sendKeys("‏Dalia Al Daja");
		Thread.sleep(2000);

		// searchInput.sendKeys(Keys.ARROW_DOWN);
		searchInput.sendKeys(Keys.ENTER);

	}

	@Test(priority = 21)
	public void connectButton() {

		WebElement connectButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[text()='تواصل']]")));
		connectButton.click();

		WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[span[@class='artdeco-button__text' and (text()='إرسال دون ملاحظة' or text()='Send without a note')]]")));
		sendButton.click();

	}

}
