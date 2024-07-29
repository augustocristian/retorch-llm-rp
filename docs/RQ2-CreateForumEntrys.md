# RQ2 TC Load Forum Entries

# Original Test Case
The expected (original) test case is:

```java
@AccessMode(resID = "LoginService", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "OpenVidu", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "Course", concurrency = 1, sharing = false, accessMode = "READWRITE")
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
void forumNewEntryTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mMinute = calendar.get(Calendar.MINUTE);
    int mSecond = calendar.get(Calendar.SECOND);

    String newEntryTitle = "New Entry Test " + mDay + mMonth + mYear + mHour + mMinute + mSecond;
    String newEntryContent = "This is the content written on the " + mDay + " of " + months[mMonth] + ", " + mYear + " at " + mHour + ":" + mMinute + ":" + mSecond;

    try {
        // Navigate to courses
        NavigationUtilities.toCoursesHome(driver);

        // Select the course
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Navigate to the forum tab
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Create a new forum entry
        ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);

        // Verify the new entry
        WebElement newEntry = ForumNavigationUtilities.getEntry(driver, newEntryTitle);
        assertEquals(newEntry.findElement(FORUM_ENTRY_LIST_ENTRY_USER).getText(), userName, "Incorrect user");
        Click.element(driver, newEntry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));

        WebElement entryTitleRow = driver.findElement(FORUM_COMMENT_LIST_ENTRY_TITLE);
        assertEquals(entryTitleRow.getText().split("\\n")[0], newEntryTitle, "Incorrect Entry Title");
        assertEquals(entryTitleRow.findElement(FORUM_COMMENT_LIST_ENTRY_USER).getText(), userName, "Incorrect User for Entry");

        // Verify the first comment
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        assertFalse(comments.isEmpty(), "No comments on the entry");
        WebElement newComment = comments.get(0);
        assertEquals(newComment.findElement(FORUM_COMMENT_LIST_COMMENT_CONTENT).getText(), newEntryContent, "Bad content of comment");
        assertEquals(newComment.findElement(FORUM_COMMENT_LIST_COMMENT_USER).getText(), userName, "Bad user in comment");

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
void forumCreateEntryTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mMinute = calendar.get(Calendar.MINUTE);
    int mSecond = calendar.get(Calendar.SECOND);

    try {
        // Navigate to courses home
        NavigationUtilities.toCoursesHome(driver);

        // Select the course
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Navigate to the forum tab
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Create a new forum entry
        String newEntryTitle = "New Forum Entry " + mDay + "/" + (mMonth + 1) + "/" + mYear + " " + mHour + ":" + mMinute + ":" + mSecond;
        String newEntryContent = "This is the content of the new forum entry created on " + mDay + "/" + (mMonth + 1) + "/" + mYear + " at " + mHour + ":" + mMinute + ":" + mSecond;
        ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);

        // Verify the new entry is displayed in the forum list
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        assertTrue(entriesList.contains(newEntryTitle), "New forum entry not found in the list");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum or create entry: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}
```

## GPT-4o-mini
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
void forumCreateEntryTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mMinute = calendar.get(Calendar.MINUTE);
    int mSecond = calendar.get(Calendar.SECOND);

    String newEntryTitle = "New Forum Entry Test " + mDay + "/" + (mMonth + 1) + "/" + mYear + " " + mHour + ":" + mMinute + ":" + mSecond;
    String newEntryContent = "This is a test entry created on " + mDay + " of " + months[mMonth] + ", " + mYear + " at " + mHour + ":" + mMinute + ":" + mSecond;

    try {
        // Navigate to courses home
        NavigationUtilities.toCoursesHome(driver);

        // Select the course to create an entry in
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Navigate to the forum tab
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Create a new forum entry
        ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);

        // Verify that the new entry appears in the forum
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        assertTrue(entriesList.contains(newEntryTitle), "The new entry was not found in the forum entries list.");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum:: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}

```
### Few Shot + COT
The obtained test case with 4o-few-shot+CoT:
```java
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void forumCreateEntryTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    String newEntryTitle = "Test Entry " + calendar.getTimeInMillis(); // Unique title
    String newEntryContent = "This is a test entry created on " + calendar.getTime();

    try {
        // Navigate to courses
        NavigationUtilities.toCoursesHome(driver);

        // Select a course with forum enabled
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Access the forum
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Create a new forum entry
        ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);

        // Verify the entry is created
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        assertTrue(entriesList.contains(newEntryTitle), "New entry was not found in the forum list");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}
```


