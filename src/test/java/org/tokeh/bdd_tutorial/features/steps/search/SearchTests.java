package org.tokeh.bdd_tutorial.features.steps.search;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/search/book_search.feature",
    plugin = "pretty",
    tags = {"@test#1"}
)
public class SearchTests { }
