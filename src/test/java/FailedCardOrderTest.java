import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FailedCardOrderTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldEnglishLetters() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ivan Petrov");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldEmptyNameField() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShorterPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Петров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7999888775");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldLongerPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Петров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+799988877555");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPhoneWithoutPlus() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Петров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPhoneEmptyField() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Петров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNameSymbols() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван#$* Петров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckboxEmptyField() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Петров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.className("button")).click();

        Boolean expected = true;
        Boolean actual = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).isDisplayed();

        Assertions.assertEquals(expected, actual);
    }

}
