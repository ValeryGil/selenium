import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardOrderTest {
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
    public void shouldRussianLetters() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Петров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDash() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Петров-Сидоров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTwoSpaces() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Васильевич Петров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTwoDashes() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Эмилия-Анна Петрова-Сидорова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTwoDashesAndTwoSpaces() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Эмилия-Анна Васильевна Петрова-Сидорова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldOneLetter() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Э");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTwoLettersAndSpace() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Э П");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTwoLettersAndDash() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Э-П");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
}
