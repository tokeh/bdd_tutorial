package org.tokeh.bdd_tutorial;

import net.serenitybdd.jbehave.SerenityStories;
import net.serenitybdd.jbehave.annotations.Metafilter;

@Metafilter("+important *")
public class AcceptanceTests extends SerenityStories { }
