package Runner;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(snippets = SnippetType.CAMELCASE, tags = { "@Test" }, glue = {
		"stepDefs" }, strict = true, dryRun = false, features = { "src/test/resources" }, plugin = { "pretty",
				"junit:target/cucumber-reports/Cucumber.xml", "json:target/cucumber-reports/Cucumber.json",
				"html:target/cucumber-reports" })

public class Junit {

}
