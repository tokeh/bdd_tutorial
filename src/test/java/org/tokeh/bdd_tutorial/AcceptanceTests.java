package org.tokeh.bdd_tutorial;

import net.serenitybdd.jbehave.SerenityStories;
import net.serenitybdd.jbehave.annotations.Metafilter;

// Custom filter:
// groovy: path == 'happy_path' && type == 'scenario'
// Standard filter:
// +path happy_path +type scenario -type dummy
@Metafilter("groovy: path == 'happy_path' && type == 'scenario'")
public class AcceptanceTests extends SerenityStories { }
