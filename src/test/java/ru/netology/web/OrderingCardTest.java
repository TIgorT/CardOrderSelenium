package ru.netology.web;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderingCardTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void orderOfTheCardIsTheTestFirst() {
        driver.findElements(By.tagName("input")).get(32).sendKeys("Велев Максим");
        driver.findElements(By.tagName("input")).get(1).sendKeys("+79645851953");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestSecond() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Велев Максим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79645851953");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestThird() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Велев Максим Владимирович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79645851953");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestFourth() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Велев-Максим-Владимирович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79645851953");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestFifth() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("велев-максим-владимирович");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79645851953");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestSixth() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Velev Maxim Vladimirovich");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79645851953");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestSeventh() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Велев Максим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("7+9645851953");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestEighth() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Велев Максим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79645851953");
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id=agreement]")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestNinth() {
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals(expected, actual);
    }

    @Test
    void orderOfTheCardIsTheTestTenth() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Велев Максим");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        assertEquals(expected, actual);
    }
}
