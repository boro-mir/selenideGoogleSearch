import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SelenideGoogleSearchTest {

    @Test
    public void testSelenideGoogleSearch() {
        open("http://google.com/ncr");
        $(By.name("q")).setValue("selenide").pressEnter();
        results.shouldBe(size(10));
        assertFirstResult.shouldHave(text("Selenide: concise UI tests in Java."));
        $(By.linkText("Images")).click();
        images.first().hover().shouldHave(text("352 × 186 - selenide.org"));
        $(By.linkText("All")).click();
        assertFirstResult.shouldHave(text("Selenide: concise UI tests in Java."));
        firstLink.click();
        Selenide.Wait().until(titleIs("Selenide: concise UI tests in Java"));
    }

    ElementsCollection results = $$(".srg .g");

    SelenideElement assertFirstResult = $$(".srg .g").first();

    SelenideElement firstLink = $(".r>a");

    ElementsCollection images = $$("#rg_s .rg_l");
}