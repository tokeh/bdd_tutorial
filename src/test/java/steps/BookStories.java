package steps;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import steps.BookSearchSteps;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.*;

public class BookStories extends JUnitStories {
  @Override
  protected List<String> storyPaths() {
    return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("src/test/resources"),
        "**/*.story", "**/exclude_*.story");
  }

  // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
  @Override
  public Configuration configuration() {
    return new MostUsefulConfiguration()
        .useStoryLoader(new LoadFromClasspath(this.getClass()))
        .useStoryReporterBuilder(new StoryReporterBuilder()
            .withFormats(Format.XML, Format.IDE_CONSOLE, Format.CONSOLE, Format.HTML, Format.TXT));
  }

  // Here we specify the steps classes
  @Override
  public InjectableStepsFactory stepsFactory() {
    // varargs, can have more that one steps classes
    return new InstanceStepsFactory(configuration(), new BookSearchSteps());
  }
}
