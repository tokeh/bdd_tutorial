package org.tokeh.bdd_tutorial.features;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    plugin = "pretty",
    tags = {"@path_special_case, @path_happy_path", "~@type_dummy"}
)
public class AcceptanceTests { }
