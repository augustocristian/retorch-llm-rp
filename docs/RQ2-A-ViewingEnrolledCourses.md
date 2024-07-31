# RQ2 Test Cases for Scenario Viewing Enrolled Courses
## Results
| Model                  | Prompt Strategy | Changes (HAL) | Changes | Total | 
|------------------------|:---------------:|:-------------:|:-------:|:-----:|
| gpt-4o-2024-05-13      |    Few Shot     |       2       |    0    |   2   |
| gpt-4o-2024-05-13      | Few Shot + CoT  |       6       |    0    |   6   |
| gpt-4o-mini-2024-07-18 |    Few Shot     |       5       |    0    |   5   |
| gpt-4o-mini-2024-07-18 | Few Shot + CoT  |       6       |    2    |   8   |

## Closest Levenshtein Test Case

The closest Levenshtein test case is:

```java
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void forumLoadEntriesTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);
    try {
        //navigate to courses.
        NavigationUtilities.toCoursesHome(driver);
        List<String> courses = CourseNavigationUtilities.getCoursesList(driver);
        assertTrue(courses.size() > 0, "No courses in the list");
        //find course with forum activated
        boolean activated_forum_on_some_test = false;
        boolean has_comments = false;
        for (String course_name : courses) {
            //go to each of the courses
            WebElement course = CourseNavigationUtilities.getCourseByName(driver, course_name);
            course.findElement(COURSE_LIST_COURSE_TITLE).click();
            Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));
            log.info("Navigating to the forum and checking if its enabled");
            //go to forum tab to check if enabled:
            //load forum
            CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
            if (ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON))) {
                activated_forum_on_some_test = true;
                log.info("Loading the entries list");
                //Load list of entries
                List<String> entries_list = ForumNavigationUtilities.getFullEntryList(driver);
                if (entries_list.size() > 0) {
                    //Go into first entry
                    for (String entry_name : entries_list) {
                        log.info("Checking the entry with name: {}", entry_name);
                        WebElement entry = ForumNavigationUtilities.getEntry(driver, entry_name);
                        Click.element(driver, entry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));
                        //Load comments
                        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
                        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
                        log.info("Checking if the entry has comments");
                        if (comments.size() > 0) {
                            has_comments = true;
                            log.info("Comments found, saving them");

                            ForumNavigationUtilities.getUserComments(driver, userName);
                        }//else go to next entry
                        Click.element(driver, DOMManager.getParent(driver, driver.findElement(BACK_TO_ENTRIES_LIST_ICON)));
                    }
                }//(else) if no entries go to next course
            }//(else) if forum no active go to next course
            log.info("Returning to the main dashboard");
            driver = Click.element(driver, BACK_TO_DASHBOARD);
        }
        assertTrue((activated_forum_on_some_test && has_comments), "There isn't any forum that can be used to test this [Or not activated or no entry lists or not comments]");
    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to courses forum:: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}
```

## GPT-4o

The obtained test case with 4o:

### Few-Shot

```java

@ParameterizedTest
@MethodSource("data")
void viewEnrolledCoursesTest(String mail, String password, String role) {
    // Step 1: User logs into the application
    this.slowLogin(user, mail, password);

    // Step 2: User navigates to the dashboard
    try {
        if (NavigationUtilities.amINotHere(driver, COURSES_URL.replace("__HOST__", HOST))) {
            NavigationUtilities.toCoursesHome(driver);
        }

        // Step 3: System displays a list of courses the user is enrolled in
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("courses-list")));
        List<WebElement> courses = driver.findElements(By.className("course-item"));

        // Expected Output: The dashboard shows a list of courses the user is enrolled in
        assertTrue(courses.size() > 0, "No enrolled courses found for the user");

    } catch (Exception e) {
        fail("Failed to view enrolled courses: " + e.getClass() + ": " + e.getLocalizedMessage());
    }

    // Teardown: Navigate to the main page to logout
    user.getDriver().get(APP_URL);
}
```

#### Changes Performed

```diff
@ParameterizedTest
@MethodSource("data")
void viewEnrolledCoursesTest(String mail, String password, String role) {
    // Step 1: User logs into the application
    this.slowLogin(user, mail, password);

    // Step 2: User navigates to the dashboard
    try {
        if (NavigationUtilities.amINotHere(driver, COURSES_URL.replace("__HOST__", HOST))) {
            NavigationUtilities.toCoursesHome(driver);
        }

        // Step 3: System displays a list of courses the user is enrolled in
-        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("courses-list")));
+        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-list")));
-        List<WebElement> courses = driver.findElements(By.className("course-item"));
+        List<WebElement> courses = driver.findElements(By.className("course-list-item"));

        // Expected Output: The dashboard shows a list of courses the user is enrolled in
        assertTrue(courses.size() > 0, "No enrolled courses found for the user");

    } catch (Exception e) {
        fail("Failed to view enrolled courses: " + e.getClass() + ": " + e.getLocalizedMessage());
    }

    // Teardown: Navigate to the main page to logout
    user.getDriver().get(APP_URL);
}
```

### Few Shot + COT

The obtained test case with 4o-few-shot+CoT:

```java

@Test
void viewEnrolledCoursesTest() {
    // Setup
    String email = "user@example.com";
    String password = "password";
    String[] expectedCourses = {"Course 1", "Course 2", "Course 3"};

    // Login
    this.slowLogin(user, email, password);

    // Verify Dashboard
    try {
        // Wait for the dashboard to load
        Wait.notTooMuch(driver).until(ExpectedConditions.presenceOfElementLocated(By.id("dashboard")));

        // Get the list of enrolled courses
        List<WebElement> courses = driver.findElements(By.className("course-title"));

        // Verify the number of courses
        assertEquals(expectedCourses.length, courses.size(), "Number of enrolled courses does not match");

        // Verify the course titles
        for (int i = 0; i < expectedCourses.length; i++) {
            assertEquals(expectedCourses[i], courses.get(i).getText(), "Course title does not match");
        }

    } catch (Exception e) {
        fail("Failed to verify enrolled courses: " + e.getClass() + ": " + e.getLocalizedMessage());
    }

    // Teardown
    user.getDriver().get(APP_URL);
}
```

#### Changes Performed

```diff
- @Test
+ @ParameterizedTest
+@MethodSource("data")
-void viewEnrolledCoursesTest() {
+void viewEnrolledCoursesTest(String mail, String password, String role) {
    // Setup
    -String email = "user@example.com";
    -String password = "password";
    -String[] expectedCourses = {"Course 1", "Course 2", "Course 3"};
    +String[] expectedCourses = {"Pseudoscientific course for treating the evil eye",  "Don't mind. This is a real course"};

    // Login
    this.slowLogin(user, mail, password);

    // Verify Dashboard
    try {
        // Wait for the dashboard to load
+        NavigationUtilities.toCoursesHome(driver);
-        Wait.notTooMuch(driver).until(ExpectedConditions.presenceOfElementLocated(By.id("dashboard")));
+        Wait.notTooMuch(driver).until(ExpectedConditions.presenceOfElementLocated(COURSES_DASHBOARD_TITLE));

        // Get the list of enrolled courses
-        List<WebElement> courses = driver.findElements(By.className("course-title"));
+        List<WebElement> courses = driver.findElements(By.className("title"));

        // Verify the number of courses
        assertEquals(expectedCourses.length, courses.size(), "Number of enrolled courses does not match");

        // Verify the course titles
        for (int i = 0; i < expectedCourses.length; i++) {
            assertEquals(expectedCourses[i], courses.get(i).getText(), "Course title does not match");
        }
    } catch (Exception e) {
        fail("Failed to verify enrolled courses: " + e.getClass() + ": " + e.getLocalizedMessage());
    }

    // Teardown
    user.getDriver().get(APP_URL);
}
```

## GPT-4o-mini

The obtained test case with 4o:

### Few-Shot

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void viewEnrolledCoursesTest(String mail, String password, String role) {
    // Setup: User logs into the application
    this.slowLogin(user, mail, password);

    // Navigate to the dashboard
    NavigationUtilities.toDashboard(driver);

    // Wait for the courses list to be visible
    Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("enrolledCoursesList")));

    // Verify that the dashboard shows a list of courses the user is enrolled in
    List<WebElement> enrolledCourses = driver.findElements(By.className("course-title"));
    assertFalse(enrolledCourses.isEmpty(), "The enrolled courses list should not be empty.");

    // Optionally, you can check for specific course titles if known
    // assertTrue(enrolledCourses.stream().anyMatch(course -> course.getText().equals("Expected Course Title")), "Expected course is not found in the enrolled courses list.");

    // Teardown: Navigate back to the main page or logout
    user.getDriver().get(APP_URL);
}
```

#### Changes Performed

```diff
 @AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
 @AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "READONLY")
 @AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
 @AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
 @AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
 @ParameterizedTest
 @MethodSource("data")
-void viewEnrolledCoursesTest(String mail, String password, String role) {
+void viewEnrolledCoursesTest(String mail, String password, String role) throws ElementNotFoundException {
     // Setup: User logs into the application
     this.slowLogin(user, mail, password);
 
     // Navigate to the dashboard
-    NavigationUtilities.toDashboard(driver);
+    NavigationUtilities.toCoursesHome(driver);
 
     // Wait for the courses list to be visible
-    Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("enrolledCoursesList")));
+    Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-list")));
 
     // Verify that the dashboard shows a list of courses the user is enrolled in
-    List<WebElement> enrolledCourses = driver.findElements(By.className("course-title"));
+    List<WebElement> enrolledCourses = driver.findElements(By.className("course-list-item"));
-    assertFalse(enrolledCourses.isEmpty(), "The enrolled courses list should not be empty.");
+    assertFalse(enrolledCourses.size()!=2, "The enrolled courses list should not be empty.");
 
     // Optionally, you can check for specific course titles if known
     // assertTrue(enrolledCourses.stream().anyMatch(course -> course.getText().equals("Expected Course Title")), "Expected course is not found in the enrolled courses list.");
 
     // Teardown: Navigate back to the main page or logout
     user.getDriver().get(APP_URL);
}
```

### Few Shot + COT

The obtained test case with 4o-few-shot+CoT:

```java

@Test
void viewEnrolledCourses() {
    // Setup: Log in to the application
    String email = "testuser@example.com"; // Replace with actual test user email
    String password = "password"; // Replace with actual test user password
    this.slowLogin(user, email, password);

    // Navigation: Navigate to the dashboard
    NavigationUtilities.toDashboard(driver);

    // Verification: Check that the system displays a list of enrolled courses
    List<WebElement> enrolledCourses = driver.findElements(By.className("enrolled-course")); // Replace with actual class name for enrolled courses
    Assertions.assertFalse(enrolledCourses.isEmpty(), "The enrolled courses list should not be empty.");

    // Optionally, validate the content of the enrolled courses
    for (WebElement course : enrolledCourses) {
        String courseTitle = course.getText(); // Assuming the course title is displayed as text
        Assertions.assertNotNull(courseTitle, "Course title should not be null.");
        // Add more assertions as needed to validate course details
    }

    // Teardown: Log out of the application
    NavigationUtilities.logout(driver); // Replace with actual logout method
}
```

#### Changes Performed

```diff
-@Test
+@ParameterizedTest
+@MethodSource("data")
-void viewEnrolledCourses() {
+void viewEnrolledCourses(String mail, String password, String role) throws ElementNotFoundException {

     // Setup: Log in to the application
-    String email = "testuser@example.com"; // Replace with actual test user email
-    String password = "password"; // Replace with actual test user password
     this.slowLogin(user, mail, password);

     // Navigation: Navigate to the dashboard
-    NavigationUtilities.toDashboard(driver);
+    NavigationUtilities.toCoursesHome(driver);

     // Verification: Check that the system displays a list of enrolled courses
-    List<WebElement> enrolledCourses = driver.findElements(By.className("enrolled-course")); // Replace with actual class name for enrolled courses
+    List<WebElement> enrolledCourses = driver.findElements(By.className("course-list-item")); // Replace with actual class name for enrolled courses
     Assertions.assertFalse(enrolledCourses.isEmpty(), "The enrolled courses list should not be empty.");

     // Optionally, validate the content of the enrolled courses
     for (WebElement course : enrolledCourses) {
         String courseTitle = course.getText(); // Assuming the course title is displayed as text
         Assertions.assertNotNull(courseTitle, "Course title should not be null.");
         // Add more assertions as needed to validate course details
     }

     // Teardown: Log out of the application
-    NavigationUtilities.logout(driver); // Replace with actual logout method
+    //NavigationUtilities.logout(driver); // Replace with actual logout method (NOT NECESSARY DONE IN THE TEAR-DOWN)
 }
```