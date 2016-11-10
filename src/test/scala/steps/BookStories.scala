package steps

import java.util.List

import org.jbehave.core.configuration.{Configuration, MostUsefulConfiguration}
import org.jbehave.core.io.{CodeLocations, LoadFromClasspath, StoryFinder}
import org.jbehave.core.junit.JUnitStories
import org.jbehave.core.reporters.{Format, StoryReporterBuilder}
import org.jbehave.core.steps.{InjectableStepsFactory, InstanceStepsFactory}

class BookStories extends JUnitStories {
  protected def storyPaths: List[String] = new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("src/test/resources"), "**/*.story", "**/exclude_*.story")

  override def configuration: Configuration = new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(this.getClass)).useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.XML, Format.IDE_CONSOLE, Format.CONSOLE, Format.HTML, Format.TXT))

  override def stepsFactory: InjectableStepsFactory = new InstanceStepsFactory(configuration, new BookSearchSteps)
}
