# RQ2 Test cases for Scenario Accessing Courses and Viewing Classes

## Results

| Model                  | Prompt Strategy | Changes (HAL) | Changes | Total Changes | Total Lines |
|------------------------|:---------------:|:-------------:|:-------:|:-------------:|:-----------:|
| gpt-4o-2024-05-13      |    Few Shot     |       5       |    0    |       5       |     36      |
| gpt-4o-2024-05-13      | Few Shot + CoT  |       9       |    0    |       9       |     27      |
| gpt-4o-mini-2024-07-18 |    Few Shot     |       7       |    0    |       7       |     31      |
| gpt-4o-mini-2024-07-18 | Few Shot + CoT  |      10       |    0    |      10       |     33      |

## Closest Levenshtein Test Case

The closest Levenshtein test case is:

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void forumNewEntryTest(String mail, String password, String role) {// 48+ 104 +   28 set up +13 lines teardown =193
    this.slowLogin(user, mail, password); //24 lines
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mMinute = calendar.get(Calendar.MINUTE);
    int mSecond = calendar.get(Calendar.SECOND);
    log.info("Setting new entry title and content");
    String newEntryTitle = "New Entry Test " + mDay + mMonth + mYear + mHour + mMinute + mSecond;
    String newEntryContent = "This is the content written on the " + mDay + " of " + months[mMonth] + ", " + mHour + ":" + mMinute + "," + mSecond;
    log.info("Navigating to courses tab");
    try {
        log.info("Navigating to courses tab");
        //navigate to courses.
        if (NavigationUtilities.amINotHere(driver, COURSES_URL.replace("__HOST__", HOST))) {
            NavigationUtilities.toCoursesHome(driver);//3lines
        }
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);//14lines
        log.info("Entering the course List");
        course.findElement(COURSE_LIST_COURSE_TITLE).click();

        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));
        log.info("Entering the Forum");
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON); //4lines
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated"); //6lines
        Wait.waitForPageLoaded(driver);
        ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent); //16lines
        //Retorch Modification, this test fails due to the speed of the browser
        Wait.waitForPageLoaded(driver);
        //Check entry... Flake Here
        WebElement newEntry = ForumNavigationUtilities.getEntry(driver, newEntryTitle);//16lines
        Wait.waitForPageLoaded(driver);
        assertEquals(newEntry.findElement(FORUM_ENTRY_LIST_ENTRY_USER).getText(), userName, "Incorrect user");
        Click.element(driver, newEntry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        WebElement entryTitleRow = driver.findElement(FORUM_COMMENT_LIST_ENTRY_TITLE);
        assertEquals(entryTitleRow.getText().split("\n")[0], newEntryTitle, "Incorrect Entry Title");
        assertEquals(entryTitleRow.findElement(FORUM_COMMENT_LIST_ENTRY_USER).getText(), userName, "Incorrect User for Entry");
        //first comment should be the inserted while creating the entry
        Wait.waitForPageLoaded(driver);
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        assertFalse(comments.size() < 1, "No comments on the entry");
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        Wait.waitForPageLoaded(driver);
        WebElement newComment = comments.get(0);
        String commentContent = newComment.findElement(FORUM_COMMENT_LIST_COMMENT_CONTENT).getText();
        assertEquals(commentContent, newEntryContent, "Bad content of comment");
        Wait.waitForPageLoaded(driver);
        String comment = newComment.findElement(FORUM_COMMENT_LIST_COMMENT_USER).getText();
        assertEquals(comment, userName, "Bad user in comment");
    } catch (ElementNotFoundException notFoundException) {
        Assertions.fail("Failed to navigate to course forum:: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
    //Fix Flaky test Navigating to the mainpage to logout...
    user.getDriver().get(APP_URL);
}
```

## GPT-4o Few-Shot

The obtained test case with 4o FS:

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "DYNAMIC")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void accessCoursesAndViewClassesTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);
    try {
        // Navigate to courses
        NavigationUtilities.toCoursesHome(driver);
        List<String> courses = CourseNavigationUtilities.getCoursesList(driver);
        assertTrue(courses.size() > 0, "No courses in the list");

        // Access the first course
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courses.get(0));
        course.findElement(By.cssSelector(".course-title")).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-classes")));

        // Verify classes are ordered by date
        List<WebElement> classes = driver.findElements(By.cssSelector(".class-item"));
        assertTrue(classes.size() > 0, "No classes found in the course");

        // Check if classes are ordered by date
        boolean isOrderedByDate = true;
        for (int i = 1; i < classes.size(); i++) {
            String previousDate = classes.get(i - 1).findElement(By.cssSelector(".class-date")).getText();
            String currentDate = classes.get(i).findElement(By.cssSelector(".class-date")).getText();
            if (previousDate.compareTo(currentDate) > 0) {
                isOrderedByDate = false;
                break;
            }
        }
        assertTrue(isOrderedByDate, "Classes are not ordered by date");

    } catch (Exception e) {
        fail("Failed to access courses and view classes: " + e.getClass() + ": " + e.getLocalizedMessage());
    }
}
```

### Changes Performed

```diff
  @AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
  @AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "DYNAMIC")
  @AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
  @AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
  @AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")

 @ParameterizedTest
 @MethodSource("data")
 void accessCoursesAndViewClassesTest(String mail, String password, String role) {
      this.slowLogin(user, mail, password);
      try {
          // Navigate to courses
          NavigationUtilities.toCoursesHome(driver);
          List<String> courses = CourseNavigationUtilities.getCoursesList(driver);
          assertTrue(courses.size() > 0, "No courses in the list");

          // Access the first course
          WebElement course = CourseNavigationUtilities.getCourseByName(driver, courses.get(0));
          course.findElement(By.cssSelector(".course-title")).click();
-         Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-classes")));
+         Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.className("session-data")));
+         CourseNavigationUtilities.go2Tab(driver, SESSION_ICON);

          // Verify classes are ordered by date
-         List<WebElement> classes = driver.findElements(By.cssSelector(".class-item"));
+         List<WebElement> classes = driver.findElements(By.className("session-data"));
          assertTrue(classes.size() > 0, "No classes found in the course");

          // Check if classes are ordered by date
          boolean isOrderedByDate = true;
          for (int i = 1; i < classes.size(); i++) {
-             String previousDate = classes.get(i - 1).findElement(By.cssSelector(".class-date")).getText();
+             String currentDate = classes.get(i).findElement(By.className("session-datetime")).getText();
-             String currentDate = classes.get(i).findElement(By.cssSelector(".class-date")).getText();
+             String previousDate = classes.get(i - 1).findElement(By.className("session-datetime")).getText();
              if (previousDate.compareTo(currentDate) > 0) {
                  isOrderedByDate = false;
                  break;
              }
          }
          assertTrue(isOrderedByDate, "Classes are not ordered by date");

      } catch (Exception e) {
          fail("Failed to access courses and view classes: " + e.getClass() + ": " + e.getLocalizedMessage());
      }
  }

```

## GPT-4o Few Shot + CoT

The obtained test case with 4o FS+CoT:

```java

@Test
void userAccessCoursesAndViewClassesTest() {
    // Step 1: User logs into the application
    this.slowLogin(user, "user@example.com", "password");

    try {
        // Step 2: User navigates to the dashboard
        NavigationUtilities.toCoursesHome(driver);
        List<String> courses = CourseNavigationUtilities.getCoursesList(driver);
        assertTrue(courses.size() > 0, "No courses in the list");

        // Step 3: User clicks on a course
        String courseName = courses.get(0); // Select the first course for simplicity
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(By.cssSelector(".course-title")).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-page")));

        // Step 4: System displays the classes within the course, ordered by date
        List<WebElement> classes = driver.findElements(By.cssSelector(".class-item"));
        assertTrue(classes.size() > 0, "No classes found in the course");

        // Verify that classes are ordered by date
        for (int i = 1; i < classes.size(); i++) {
            String previousDate = classes.get(i - 1).findElement(By.cssSelector(".class-date")).getText();
            String currentDate = classes.get(i).findElement(By.cssSelector(".class-date")).getText();
            assertTrue(previousDate.compareTo(currentDate) <= 0, "Classes are not ordered by date");
        }

    } catch (Exception e) {
        fail("Test failed: " + e.getMessage());
    }
}
```

### Changes Performed

```diff
- @Test
+ @ParameterizedTest
+ @MethodSource("data")
- void userAccessCoursesAndViewClassesTest() {
+ void userAccessCoursesAndViewClassesTest(String mail, String password, String role) {
      // Step 1: User logs into the application
-     this.slowLogin(user, "user@example.com", "password");
+     this.slowLogin(user, mail, password);

      try {
          // Step 2: User navigates to the dashboard
          NavigationUtilities.toCoursesHome(driver);
          List<String> courses = CourseNavigationUtilities.getCoursesList(driver);
          assertTrue(courses.size() > 0, "No courses in the list");

          // Step 3: User clicks on a course
          String courseName = courses.get(0); // Select the first course for simplicity
          WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
          course.findElement(By.cssSelector(".course-title")).click();
-         Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-page")));
+         Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.className("session-data")));
+         CourseNavigationUtilities.go2Tab(driver, SESSION_ICON);

          // Step 4: System displays the classes within the course, ordered by date
-         List<WebElement> classes = driver.findElements(By.cssSelector(".class-item"));
+         List<WebElement> classes = driver.findElements(By.className("session-data"));
          assertTrue(classes.size() > 0, "No classes found in the course");

          // Verify that classes are ordered by date
          for (int i = 1; i < classes.size(); i++) {
-             String previousDate = classes.get(i - 1).findElement(By.cssSelector(".class-date")).getText();
+             String previousDate = classes.get(i - 1).findElement(By.className("session-datetime")).getText();
-             String currentDate = classes.get(i).findElement(By.cssSelector(".class-date")).getText();
+             String currentDate = classes.get(i).findElement(By.className("session-datetime")).getText();
              assertTrue(previousDate.compareTo(currentDate) <= 0, "Classes are not ordered by date");

      } catch (Exception e) {
          fail("Test failed: " + e.getMessage());
      }
  }
```

## GPT-4o-mini Few-Shot

The obtained test case with 4o-mini FS:

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void userAccessCoursesAndViewClassesTest(String mail, String password, String role) {
    // Step 1: User logs into the application
    this.slowLogin(user, mail, password);

    try {
        // Step 2: User navigates to the dashboard
        NavigationUtilities.toCoursesHome(driver);

        // Step 3: User clicks on a course
        List<String> courses = CourseNavigationUtilities.getCoursesList(driver);
        assertFalse(courses.isEmpty(), "No courses available for the user.");

        // Click on the first course in the list
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courses.get(0));
        Click.element(driver, course.findElement(COURSE_LIST_COURSE_TITLE));

        // Step 4: System displays the classes within the course, ordered by date
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Verify that classes are displayed
        List<WebElement> classes = CourseNavigationUtilities.getClassesList(driver);
        assertFalse(classes.isEmpty(), "No classes found for the selected course.");

        // Optionally, check if classes are ordered by date
        // (Assuming there is a method to verify the order of classes)
        assertTrue(CourseNavigationUtilities.areClassesOrderedByDate(classes), "Classes are not ordered by date.");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to courses or view classes: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}
```

### Changes Performed

```diff
  @AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
  @AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "READONLY")
  @AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
  @AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
  @AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
 @ParameterizedTest
 @MethodSource("data")
 void userAccessCoursesAndViewClassesTest(String mail, String password, String role) {
      // Step 1: User logs into the application
      this.slowLogin(user, mail, password);

      try {
          // Step 2: User navigates to the dashboard
          NavigationUtilities.toCoursesHome(driver);

          // Step 3: User clicks on a course
          List<String> courses = CourseNavigationUtilities.getCoursesList(driver);
          assertFalse(courses.isEmpty(), "No courses available for the user.");

          // Click on the first course in the list
          WebElement course = CourseNavigationUtilities.getCourseByName(driver, courses.get(0));
          Click.element(driver, course.findElement(COURSE_LIST_COURSE_TITLE));

          // Step 4: System displays the classes within the course, ordered by date
          Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

+         CourseNavigationUtilities.go2Tab(driver, SESSION_ICON);
-         List<WebElement> classes = CourseNavigationUtilities.getClassesList(driver);
+         List<WebElement> classes = driver.findElements(By.className("session-data"));
          assertFalse(classes.isEmpty(), "No classes found for the selected course.");

         // Optionally, check if classes are ordered by date
         // (Assuming there is a method to verify the order of classes)
-         assertTrue(CourseNavigationUtilities.areClassesOrderedByDate(classes), "Classes are not ordered by date.");
+         for (int i = 1; i < classes.size(); i++) {
+             String previousDate = classes.get(i - 1).findElement(By.className("session-datetime")).getText();
+             String currentDate = classes.get(i).findElement(By.className("session-datetime")).getText();
+             assertTrue(previousDate.compareTo(currentDate) <= 0, "Classes are not ordered by date");
+         }

      } catch (ElementNotFoundException notFoundException) {
          fail("Failed to navigate to courses or view classes: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
      }
  }
```

### GPT-4o-mini Few Shot + CoT

The obtained test case with 4o-min FS+CoT:

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "READONLY")
@ParameterizedTest
@MethodSource("data")
    // Assuming data() provides test users
void userAccessCoursesAndViewClassesTest(String mail, String password, String role) {
    // Step 1: Login to the application
    this.slowLogin(user, mail, password);

    try {
        // Step 2: Navigate to the dashboard
        NavigationUtilities.toDashboard(driver);
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(DASHBOARD_ID)));

        // Step 3: View enrolled courses
        List<String> enrolledCourses = CourseNavigationUtilities.getCoursesList(driver);
        assertFalse(enrolledCourses.isEmpty(), "The user should have enrolled courses displayed.");

        // Step 4: Access a course
        String courseName = enrolledCourses.get(0); // Access the first course
        WebElement courseElement = CourseNavigationUtilities.getCourseByName(driver, courseName);
        Click.element(driver, courseElement);
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(COURSE_PAGE_ID)));

        // Step 5: View classes within the course
        List<WebElement> classes = CourseNavigationUtilities.getClassesList(driver);
        assertFalse(classes.isEmpty(), "The course should have classes displayed.");
        // Optionally, check if classes are ordered by date
        // (Assuming there's a method to verify order)
        assertTrue(CourseNavigationUtilities.areClassesOrderedByDate(classes), "Classes should be ordered by date.");

    } catch (Exception e) {
        fail("Test failed due to an exception: " + e.getMessage());
    } finally {
        // Step 6: Teardown
        user.getDriver().get(APP_URL); // Navigate back to the home page
    }
}
```

#### Changes Performed

```diff
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "READONLY")
@ParameterizedTest
@MethodSource("data") // Assuming data() provides test users
void userAccessCoursesAndViewClassesTest(String mail, String password, String role) {
    // Step 1: Login to the application
    this.slowLogin(user, mail, password);

    try {
        // Step 2: Navigate to the dashboard
-        NavigationUtilities.toDashboard(driver);
+        NavigationUtilities.toCoursesHome(driver);
-        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(DASHBOARD_ID)));
+        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(COURSES_DASHBOARD_TITLE));

        // Step 3: View enrolled courses
        List<String> enrolledCourses = CourseNavigationUtilities.getCoursesList(driver);
        assertFalse(enrolledCourses.isEmpty(), "The user should have enrolled courses displayed.");

        // Step 4: Access a course
        String courseName = enrolledCourses.get(0); // Access the first course
        WebElement courseElement = CourseNavigationUtilities.getCourseByName(driver, courseName);
        Click.element(driver, courseElement);

-        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(COURSE_PAGE_ID)));

        // Step 5: View classes within the course
+        CourseNavigationUtilities.go2Tab(driver, SESSION_ICON);
-        List<WebElement> classes = CourseNavigationUtilities.getClassesList(driver);
+        List<WebElement> classes = driver.findElements(By.className("session-data"));
         assertFalse(classes.isEmpty(), "The course should have classes displayed.");
         
         // Optionally, check if classes are ordered by date
         // (Assuming there's a method to verify order)
-        assertTrue(CourseNavigationUtilities.areClassesOrderedByDate(classes), "Classes should be ordered by date.");
+        for (int i = 1; i < classes.size(); i++) {
+            String previousDate = classes.get(i - 1).findElement(By.className("session-datetime")).getText();
+            String currentDate = classes.get(i).findElement(By.className("session-datetime")).getText();
+            assertTrue(previousDate.compareTo(currentDate) <= 0, "Classes are not ordered by date");
+        }
    } catch (Exception e) {
        fail("Test failed due to an exception: " + e.getMessage());
    } finally {
        // Step 6: Teardown
        user.getDriver().get(APP_URL); // Navigate back to the home page
    }
}
```