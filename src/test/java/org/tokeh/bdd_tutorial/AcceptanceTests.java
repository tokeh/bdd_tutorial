package org.tokeh.bdd_tutorial;

import net.serenitybdd.jbehave.SerenityStories;
import net.serenitybdd.jbehave.annotations.Metafilter;

@Metafilter("+path happy_path +type scenario -type dummy")
public class AcceptanceTests extends SerenityStories { }
