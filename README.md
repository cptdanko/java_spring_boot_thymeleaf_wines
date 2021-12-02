# Best Wines (Java app with JSF, Thymeleaf and Spring boot)

The goal of this repository is to show how to migrate some legacy Java app with JSF to a more modern Java app using Spring Boot and Thymeleaf. The app in question is an enterprise Java app that analyses Wine data with regards to wines manufactured in Australia. 
  
### Technology used
- Java 8 
- Java Server Faces (JSF 2.1.7)
- Spring Boot (2.2.2)
- Thymeleaf 
- Junit (4.2)
- Vanilla Javascript to toggle Wine View in the UI in JSF

# Installation and setup (for Eclipse)
This project was built using Eclipse IDE, so the installation instructions here would be to set it up with Eclipse.
- Clone the repo on your machine 
- Open Eclipse IDE
- In the menu File -> Import 
- In that dialog that shows up, navigate to the Maven folder
- Select Existing Maven Projects and click browse
- Go to the folder where you have cloned the repo and select the pom.xml file

And you would have your project setup in Eclipse.

## This project has the following components 
- Analytics: Classes that summaries and compute a percentage breakdown with regards to wine components and deliver a s summary of the result. 
- Unit tests: to check if the data computed is correct
- Wine model UI: This is a user interface for the Wine object that uses a JSF
- Wine Analytics API: A set of REST endpoints that expose Wine Analytics data

## How to run the project
If you are here, then it means you already have Maven installed. To run it, navigate to the project directory on command line and execute
```
mvn spring-boot:run 
```
# Analytics and Tests component
Let's examine the various components of this repo starting with the Analytics and unit testing component.

## Class structure

```
public interface WineStatistics {
  /* distribution */
  public Map<String, Double> getYearBreakdown(Wine w);
  public Map<String, Double> getRegionBreakdown(Wine w);
  public Map<String, Double> getVarietyBreakdown(Wine w);
  public Map<String, Map<String,Double>> getYrnVBreakdown(Wine w);
}

public class WineAnalytics implements WineStatistics {

  @Override
  public Map<String, Double> getYearBreakdown(Wine w) {
    Map<String, Double> yearMap = new HashMap<>();
    for (GrapeComponent gc : w.getComponents()) {
      String yearStr = "" + gc.getYear();
      Double percentage = yearMap.get(yearStr);
      if (percentage != null) {
        yearMap.put(yearStr, percentage += gc.getPercentage());
      } else {
        yearMap.put(yearStr, gc.getPercentage());
      }
    }
    return yearMap;
  }
....
```

## Tests

You can test this repo, by either executing the Java Main class or by running maven test in Eclipse.

1. It prints the wine statistics using via WineTestMain.java
2. It tests and prints the wine statistics via JUnit test. The Junit tests validate whether the algorithm computes the right result

### JUnit test example

```
@Test
public void testYearBreakdown() {
  Map<String, Double> yearMap = wineStats.getYearBreakdown(vcharoo1);

  Double percent = yearMap.get("2011");
  Double actualPercent = 85D;
  Assert.assertEquals(actualPercent, percent);
  Assert.assertEquals(2, yearMap.size());

  Map<String, Double> yMap2 = wineStats.getYearBreakdown(ppnoo2vk);
  Double expected = 78D;
  Assert.assertEquals(expected, yMap2.get("2015"));
  System.out.println("     Year Breakdown    ");
  System.out.println("========================");
  printData(yearMap);
}
```

# Wine Model UI
This is a simple JSF that uses some basic JSF tags with Facelets and vanilla Javascript for DOM manipulation. 
### A snippet of the UI code from home.xhtml
```
...
<div class="home">
  <ui:repeat var="w" value="#{dataStore.wines}">
    <a class="button" onclick="showComponents('detail_#{w.lotCode}')">
      Code: #{w.lotCode}
    </a>...
```
As you can see, it uses

- "<h:" from the JSF basic tags and
- "<ui:" from Facelets

### Spring Controller (#Controller)
```
@Controller
public class BestWinesSpring {

	private DataStore ds = new DataStore();
	private Wine wine;
	
	@GetMapping("/")
	public String getBestWines(Model model) {
		List<Wine> wines = ds.getWines();
		model.addAttribute("wines", wines);
		return "bestWines";
	}
	@GetMapping("/wine")
	public String getWine(@RequestParam(value = "code", defaultValue = "15MPPN002-VK") String code, Model model) {
		this.wine = ds.getWine(code);
		model.addAttribute("wine", this.wine);
		return "wine";
	}
}
```
For the sake of this exercise, we are supplying it with a default value for code we know will return data for sure.

# REST API
This was not part of the requirements for this project, hence this is an added extra.

## Exposing resources 
The resource we are exposing are the WineAnalytics data resource and we do that using the Spring @RestController annotation, specigfy a @RequestMapping and return a response. Here's some code snippet for it
```
@RestController
public class AnalyticsAPI {

	@Autowired
	private WineAnalytics analytics;
	private DataStore dataStore = new DataStore();
	@GetMapping("/api/breakdown/year")
	public Map<String, Double> getYearBreakdown(@RequestParam(value = "code", defaultValue = "15MPPN002-VK") 
	String code, Model model) 
	....
```
Notice, we don't need to convert the data we return to JSON because Spring handles this for us with it's HTTP response message converter support. Jackson 2 is already on Spring's classpath.

# More great tutorials and code samples
Checkout [this blog] for more "how-to" code samples, tutorials and just info on how to solve problems.

If you like what I am doing, you can [buy me a coffee]

Click on the next link for more info on the 13+ year [software engineering career journey] of the author.

[this blog]: https://mydaytodo.com/blog/
[buy me a coffee]: https://www.buymeacoffee.com/bhumansoni
