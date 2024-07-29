# RQ2 Test cases for Scenario User Accessing Calendar

## Closest Levenshtein Test Case

The closest Levenshtein test case is:

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
void teachercourseMainTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);
    try {

        NavigationUtilities.toCoursesHome(driver); //4lines
        Wait.notTooMuch(driver).until(ExpectedConditions.presenceOfElementLocated(By.xpath(FIRST_COURSE_XPATH)));
        Click.element(driver, By.xpath(FIRST_COURSE_XPATH));
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));
    } catch (Exception e) {
        fail("Failed to load Courses Tabs" + e.getClass() + ": " + e.getLocalizedMessage());
    }
    //Check tabs
    //Home tab
    try {
        CourseNavigationUtilities.go2Tab(driver, HOME_ICON); //4lines
    } catch (Exception e) {
        fail("Failed to load home tab" + e.getClass() + ": " + e.getLocalizedMessage());
    }
    try {
        CourseNavigationUtilities.go2Tab(driver, SESSION_ICON); //4lines
    } catch (Exception e) {
        fail("Failed to load session tab" + e.getClass() + ": " + e.getLocalizedMessage());
    }
    try {
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON); //4lines
    } catch (Exception e) {
        fail("Failed to load forum tab" + e.getClass() + ": " + e.getLocalizedMessage());
    }
    try {
        CourseNavigationUtilities.go2Tab(driver, FILES_ICON); //4lines
    } catch (Exception e) {
        fail("Failed to load files tab" + e.getClass() + ": " + e.getLocalizedMessage());
    }
    try {
        CourseNavigationUtilities.go2Tab(driver, ATTENDERS_ICON); //4lines
    } catch (Exception e) {
        fail("Failed to load attenders tab" + e.getClass() + ": " + e.getLocalizedMessage());
    }
}
```

## GPT-4o

The obtained test case with 4o:

### Few-Shot

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "calendarservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void userAccessCalendarTest(String mail, String password, String role) {
    this.slowLogin(mail, password);
    try {
        // Navigate to the calendar page
        NavigationUtilities.toCalendarPage(driver);
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("calendar")));

        // Verify that the calendar displays all the user's classes
        List<WebElement> classes = driver.findElements(By.cssSelector(".calendar-class"));
        assertTrue(classes.size() > 0, "No classes found in the calendar");

    } catch (Exception e) {
        fail("Failed to access the calendar: " + e.getClass() + ": " + e.getLocalizedMessage());
    }
}
```

#### Changes Performed

```diff
 @AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
 @AccessMode(resID = "calendarservice", concurrency = 10, sharing = true, accessMode = "READONLY")
 @AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
 @AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
 @ParameterizedTest
 @MethodSource("data")
 void userAccessCalendarTest(String mail, String password, String role) {
    this.slowLogin(mail, password);

     try {
         // Navigate to the calendar page
-        NavigationUtilities.toCalendarPage(driver);
+        NavigationUtilities.toCoursesHome(driver);
-        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("calendar")));
+        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.className("calendar-div")));
 
         // Verify that the calendar displays all the user's classes
-        List<WebElement> classes = driver.findElements(By.cssSelector(".calendar-class"));
+        List<WebElement> classes = driver.findElements(By.className("cal-day-badge"));
         assertTrue(classes.size() > 0, "No classes found in the calendar");
 
     } catch (Exception e) {
         fail("Failed to access the calendar: " + e.getClass() + ": " + e.getLocalizedMessage());
     }
 }
```

### Few Shot + COT

The obtained test case with 4o-few-shot+CoT:

```java

@ParameterizedTest
@MethodSource("data")
void userCanAccessAndViewCalendarTest(String mail, String password, String role) {
    this.slowLogin(mail, password);

    try {
        // Navigate to the calendar page
        NavigationUtilities.toCalendarPage(driver);
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("calendar")));

        // Verify that the calendar is displayed
        WebElement calendar = driver.findElement(By.id("calendar"));
        assertTrue(calendar.isDisplayed(), "Calendar is not displayed");

        // Verify that the calendar displays all the user's classes
        List<WebElement> classes = calendar.findElements(By.className("calendar-class"));
        assertTrue(classes.size() > 0, "No classes are displayed on the calendar");

        for (WebElement calendarClass : classes) {
            assertTrue(calendarClass.isDisplayed(), "Class is not displayed correctly on the calendar");
        }

    } catch (Exception e) {
        fail("Failed to access and view the calendar: " + e.getMessage());
    } finally {
        // Teardown
        driver.get(APP_URL);
    }
}
```

#### Changes Performed

```diff
 @ParameterizedTest
 @MethodSource("data")
 void userCanAccessAndViewCalendarTest(String mail, String password, String role) {
    this.slowLogin(mail, password);

     try {
         // Navigate to the calendar page
-        NavigationUtilities.toCalendarPage(driver);
+        NavigationUtilities.toCoursesHome(driver);
-        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("calendar")));
+        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.className("calendar-div")));

         // Verify that the calendar is displayed
-        WebElement calendar = driver.findElement(By.id("calendar"));
+        WebElement calendar = driver.findElement(By.className("calendar-div"));
         assertTrue(calendar.isDisplayed(), "Calendar is not displayed");

         // Verify that the calendar displays all the user's classes
-        List<WebElement> classes = calendar.findElements(By.className("calendar-class"));
+        List<WebElement> classes = calendar.findElements(By.className("cal-day-badge"));
         assertTrue(classes.size() > 0, "No classes are displayed on the calendar");

         for (WebElement calendarClass : classes) {
             assertTrue(calendarClass.isDisplayed(), "Class is not displayed correctly on the calendar");
         }

     } catch (Exception e) {
         fail("Failed to access and view the calendar: " + e.getMessage());
     } finally {
         // Teardown
         driver.get(APP_URL);
     }
 }

```

## GPT-4o-mini

The obtained test case with 4o:

### Few-Shot

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "calendar", concurrency = 1, sharing = true, accessMode = "READONLY")
@ParameterizedTest
@MethodSource("data")
@DisplayName("User Accessing Calendar Test")
void userAccessingCalendarTest(String mail, String password, String role) {
    // Step 1: User logs into the application
    this.slowLogin(user, mail, password);

    try {
        // Step 2: User navigates to the calendar page
        NavigationUtilities.toCalendarPage(driver);

        // Step 3: System displays a calendar with all the user's classes
        List<WebElement> calendarEntries = driver.findElements(By.className("calendar-entry")); // Assuming 'calendar-entry' is the class for calendar entries
        assertFalse(calendarEntries.isEmpty(), "The calendar should display at least one class entry.");

        // Additional assertions can be added here to verify the content of the calendar entries
        for (WebElement entry : calendarEntries) {
            assertNotNull(entry.findElement(By.className("class-title")), "Class title should be present in the calendar entry.");
            // You can add more checks for date, time, etc. based on your application structure
        }

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to the calendar page:: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    } finally {
        // Teardown: Logout or navigate back to the main page
        user.getDriver().get(APP_URL);
    }
}
```

#### Changes Performed

```diff
 @AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
 @AccessMode(resID = "calendar", concurrency = 1, sharing = true, accessMode = "READONLY")
 @ParameterizedTest
 @MethodSource("data")
 @DisplayName("User Accessing Calendar Test")
 void userAccessingCalendarTest(String mail, String password, String role) {
     // Step 1: User logs into the application
     this.slowLogin(user, mail, password);

     try {
         // Step 2: User navigates to the calendar page
-        NavigationUtilities.toCalendarPage(driver);
+        NavigationUtilities.toCoursesHome(driver);

         // Step 3: System displays a calendar with all the user's classes
-        List<WebElement> calendarEntries = driver.findElements(By.className("calendar-entry")); // Assuming 'calendar-entry' is the class for calendar entries
+        List<WebElement> calendarEntries = driver.findElements(By.className("cal-day-badge")); // Assuming 'calendar-entry' is the class for calendar entries
         assertFalse(calendarEntries.isEmpty(), "The calendar should display at least one class entry.");

         // Additional assertions can be added here to verify the content of the calendar entries
-        for (WebElement entry : calendarEntries) {
-            assertNotNull(entry.findElement(By.className("class-title")), "Class title should be present in the calendar entry.");
-            // You can add more checks for date, time, etc. based on your application structure
-        }
+        Click.element(user.getDriver(), calendarEntries.get(0));
+        String entrytitle = user.getDriver().findElement(By.className("cal-event-title")).getText();
+        assertEquals("Session 1: Introduction to Web | 12:41", entrytitle, "Class title should be present in the calendar entry.");

     } catch (ElementNotFoundException notFoundException) {
         fail("Failed to navigate to the calendar page:: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
     } finally {
         // Teardown: Logout or navigate back to the main page
         user.getDriver().get(APP_URL);
     }
 }
```

### Few Shot + COT

The obtained test case with 4o-few-shot+CoT:

```java

@ParameterizedTest
@MethodSource("data")
void userCanAccessAndViewCalendarTest(String mail, String password, String role) {
    // Step 1: User logs into the application
    this.slowLogin(user, mail, password);

    try {
        // Step 2: User navigates to the calendar page
        NavigationUtilities.toCalendarPage(driver);

        // Step 3: Verify calendar display
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(CALENDAR_ID)));
        WebElement calendar = driver.findElement(By.id(CALENDAR_ID));
        assertTrue(calendar.isDisplayed(), "Calendar is not displayed");

        // Step 4: Check that the calendar shows all the user's classes
        List<WebElement> classes = calendar.findElements(By.className(CLASS_ITEM_CLASS));
        assertTrue(classes.size() > 0, "No classes found in the calendar");

        // Optionally, you can validate the class details here
        // For example, check if the classes match expected classes for the user

    } catch (Exception e) {
        Assertions.fail("Failed to access and view the calendar: " + e.getMessage());
    } finally {
        // Step 5: Teardown if necessary
        user.getDriver().get(APP_URL); // Navigate back to the main page or logout
    }
}
```

#### Changes Performed

```diff
  @ParameterizedTest
 @MethodSource("data")
void userCanAccessAndViewCalendarTest(String mail, String password, String role) {
     // Step 1: User logs into the application
     this.slowLogin(user, mail, password);

     try {
         // Step 2: User navigates to the calendar page
-        NavigationUtilities.toCalendarPage(driver);
+        NavigationUtilities.toCoursesHome(driver);

         // Step 3: Verify calendar display
-        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(CALENDAR_ID)));
+        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.className("calendar-div")));
-        WebElement calendar = driver.findElement(By.id(CALENDAR_ID));
+        WebElement calendar = driver.findElement(By.className("calendar-div"));
         assertTrue(calendar.isDisplayed(), "Calendar is not displayed");

         // Step 4: Check that the calendar shows all the user's classes
-        List<WebElement> classes = calendar.findElements(By.className(CLASS_ITEM_CLASS));
+        List<WebElement> classes = calendar.findElements(By.className("cal-day-badge"));
         assertTrue(classes.size() > 0, "No classes found in the calendar");

         // Optionally, you can validate the class details here
         // For example, check if the classes match expected classes for the user

     } catch (Exception e) {
         Assertions.fail("Failed to access and view the calendar: " + e.getMessage());
     } finally {
         // Step 5: Teardown if necessary
         user.getDriver().get(APP_URL); // Navigate back to the main page or logout
     }
 }

```