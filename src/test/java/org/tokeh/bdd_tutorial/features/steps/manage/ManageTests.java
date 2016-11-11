package org.tokeh.bdd_tutorial.features.steps.manage;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/manage/manage_books.feature")
public class ManageTests { }
