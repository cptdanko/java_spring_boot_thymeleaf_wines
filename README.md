# Wine Details (Java app with JSF and Spring boot)

This repository showcases a simple Java entreprise app that aims to analyse Wine data with regards to wines manufactured in Australia. 

### Technology used
Java 1.8 
Java Server Faces (JSF 2.1.7)
Spring Boot (2.2.2)
Junit (4.2)
Vanilla Javascript to toggle Wine View

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
    </a>
    <div id="detail_#{w.lotCode}" class="hidden">
      <p>Description:</p>
      <h:inputText maxlength="60" value="#{w.description}" />
...
```
As you can see, it uses

- "<h:" from the JSF basic tags and
- "<ui:" from Facelets

### DOM Manipulation
Dom manipulation is being done with vanilla Javascript. Here's the showComponents  used to show and hide (toggle) wine details.
```
function showComponents(id) {
  var element = document.getElementById(id);
  if (element.style.display == "block") {
    element.style.display = "none";
  } else {
    element.style.display = "block";
  }
}
```

# REST API
This was not part of the requirements for this project, hence this is an added extra.

## Exposing resources 
The resource we are exposing are the WineAnalytics data resource and we do that using the Spring @RestController annotation, specigfy a @RequestMapping and return a response. Here's some code snippet for it
```
@RestController
@RequestMapping("analytics/breakdown")
public class WineAnalyticsApiImpl implements WineAnalyticsApi {
  private WineStatistics wineAnalytics = new WineAnalytics();
  private DataStore ds = new DataStore();
  
  @Override
  @GetMapping("/year")
  public Map<String, Double> getYearBreakdown(String wineId) {
    Wine w = ds.getWine(wineId);
    return wineAnalytics.getYearBreakdown(w);
  }
...
```
Notice, we don't need to convert the data we return to JSON because Spring handles this for us with it's HTTP response message converter support. Jackson 2 is already on Spring's classpath.

# Conclusion
If you need to know anything more about this? You can contact me directly through my email address that you see on my Github profile. If you would like to know more, you can also checkout my blog at https://mydaytodo.com/blog/