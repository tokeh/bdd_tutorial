import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(ExtendedCucumber.class)

@ExtendedCucumberOptions(
        jsonReport = "target/cucumber/cucumber.json",
        overviewReport = true,
        usageReport = true,
        detailedReport = true,
        detailedAggregatedReport = true,
        featureOverviewChart = true,
        retryCount = 0,
        coverageReport = true,
        excludeCoverageTags = {"@flaky" },
        includeCoverageTags = {"@passed" },
        overviewChartsReport = true,
        outputFolder = "target/cucumber")

@CucumberOptions(plugin = {
        "html:target/cucumber/cucumber-html-report",
        "json:target/cucumber/cucumber.json",
        "pretty:target/cucumber/cucumber-pretty.txt",
        "usage:target/cucumber/cucumber-usage.json",
        "junit:target/cucumber/cucumber-results.xml"})

public class BookSearchTest { }
