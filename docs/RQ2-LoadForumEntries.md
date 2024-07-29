# RQ2 TC Load Forum Entries

# Original Test Case
The expected (original) test case is:

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
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void loadForumEntriesTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    try {
        // Navigate to courses home
        NavigationUtilities.toCoursesHome(driver);

        // Select the course by name
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Navigate to the forum section
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Retrieve and verify forum entries
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        assertTrue(entriesList.size() > 0, "No forum entries found");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}

```
### Few Shot + COT
The obtained test case with 4o-few-shot+CoT:
```java
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void loadForumEntriesTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    try {
        // Navigate to courses home
        NavigationUtilities.toCoursesHome(driver);

        // Select the course by name
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Navigate to the forum section
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Retrieve and verify forum entries
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        assertTrue(entriesList.size() > 0, "No forum entries found");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}
```

## GPT-4o-mini
The obtained test case with 4o:
### Few-Shot
```java
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void forumViewEntriesTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    try {
        // Navigate to the courses home
        NavigationUtilities.toCoursesHome(driver);

        // Select the specific course
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();

        // Wait for the forum tab to be visible and check if the forum is enabled
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Retrieve the list of forum entries
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);

        // Verify that the entries are displayed
        assertFalse(entriesList.isEmpty(), "No forum entries found");

        // Optionally, check the details of the first entry
        WebElement firstEntry = ForumNavigationUtilities.getEntry(driver, entriesList.get(0));
        Click.element(driver, firstEntry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));

        // Wait for the comments section to be visible
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));

        // Verify that the comments are displayed
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        assertFalse(comments.isEmpty(), "No comments found for the entry");

        // Optionally, check the content of the first comment
        String firstCommentContent = comments.get(0).findElement(FORUM_COMMENT_LIST_COMMENT_CONTENT).getText();
        assertNotNull(firstCommentContent, "First comment content is null");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}

```
### Few Shot + COT
The obtained test case with 4o-few-shot+CoT:
```java
void loadForumEntriesTest() {
    // Step 1: Login as a User
    this.slowLogin(user, "testuser@example.com", "password123");

    try {
        // Step 2: Navigate to Courses
        NavigationUtilities.toCoursesHome(driver);
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        Click.element(driver, course.findElement(By.cssSelector("course-title-selector"))); // Adjust selector as needed

        // Step 3: Access the Forum
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("tabs-div-id")));
        CourseNavigationUtilities.go2Tab(driver, "forum-icon"); // Adjust the icon identifier as needed
        Assertions.assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, "forum-icon")), "Forum not activated");

        // Step 4: Load Forum Entries
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        WebElement entry;
        if (entriesList.isEmpty()) {
            // Create a new entry if none exists
            String newEntryTitle = "New Entry Test";
            String newEntryContent = "This is a test entry content.";
            ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);
            entry = ForumNavigationUtilities.getEntry(driver, newEntryTitle);
        } else {
            // Select the first existing entry
            entry = ForumNavigationUtilities.getEntry(driver, entriesList.get(0));
        }

        // Step 5: Verify Forum Entry Details
        Click.element(driver, entry.findElement(By.cssSelector("entry-title-selector"))); // Adjust selector as needed
        WebElement entryTitleRow = driver.findElement(By.id("forum-comment-list-entry-title"));
        Assertions.assertEquals(entryTitleRow.getText(), entry.getText(), "Incorrect Entry Title");

        // Verify comments
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        Assertions.assertFalse(comments.isEmpty(), "No comments on the entry");

    } catch (Exception e) {
        Assertions.fail("Failed to load forum entries: " + e.getMessage());
    } finally {
        // Step 6: Clean Up
        user.getDriver().get("logout-url"); // Adjust logout URL as needed
    }
}

```


