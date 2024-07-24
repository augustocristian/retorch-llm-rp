# RQ2 TC Load Forum Entries

# Original Test Case
The expected (original) test case is:

```java
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void forumNewCommentTest(String mail, String password, String role) {
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
        //check if one course have any entry for comment
        NavigationUtilities.toCoursesHome(driver);

        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");//6lines
        List<String> entries_list = ForumNavigationUtilities.getFullEntryList(driver);
        WebElement entry;
        if (entries_list.size() <= 0) {//if not new entry
            String newEntryTitle = "New Comment Test " + mDay + mMonth + mYear + mHour + mMinute + mSecond;
            String newEntryContent = "This is the content written on the " + mDay + " of " + months[mMonth - 1] + ", " + mHour + ":" + mMinute + "," + mSecond;
            ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);
            entry = ForumNavigationUtilities.getEntry(driver, newEntryTitle);
        } else {
            entry = ForumNavigationUtilities.getEntry(driver, entries_list.get(0));
        }
        //go to entry
        Click.element(driver, entry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));
        WebElement commentList = Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        //counting the number of numberComments
        int numberCommentsOld = ForumNavigationUtilities.getComments(driver).size();
        //new comment
        WebElement newCommentIcon = commentList.findElement(FORUM_COMMENT_LIST_NEW_COMMENT_ICON);
        Click.element(driver, newCommentIcon);
        Wait.aLittle(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_NEW_COMMENT_MODAL));
        String newCommentContent = "COMMENT TEST" + mDay + mMonth + mYear + mHour + mMinute + mSecond + ". This is the comment written on the " + mDay + " of " + months[mMonth] + ", " + mHour + ":" + mMinute + "," + mSecond;
        WebElement comment_field = driver.findElement(FORUM_NEW_COMMENT_MODAL_TEXT_FIELD);
        comment_field.sendKeys(newCommentContent);
        Click.element(driver, FORUM_NEW_COMMENT_MODAL_POST_BUTTON);

        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        //TO-DO think in other better way to solve this problem
        Wait.waitForPageLoaded(driver);
        user.waitUntil(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST_COMMENT), "The comment list are not visible");
        user.waitUntil((ExpectedCondition<Boolean>) driver -> {
            int elementCount = ForumNavigationUtilities.getComments(driver).size();
            return elementCount > numberCommentsOld;
        }, "Comment not attached");
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        //asserts
        assertTrue(comments.size() > numberCommentsOld, "Comment list empty or only original comment");

        user.waitUntil(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST_COMMENT), "The comment list are not visible");

        boolean commentFound = false;
        for (WebElement comment : comments) {
            //check if it is new comment
            try {
                String text = comment.findElement(FORUM_COMMENT_LIST_COMMENT_CONTENT).getText();
                if (text.equals(newCommentContent)) {
                    commentFound = true;
                    assertEquals(comment.findElement(FORUM_COMMENT_LIST_COMMENT_USER).getText(), userName, "Bad user in comment");
                }
            } catch (StaleElementReferenceException e) {
                log.info("Not Found");
            }
        }
        assertTrue(commentFound, "Comment not found");
    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum:: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }

}
```
## GPT-4o
The obtained test case with 4o:
### Few-Shot
```java
@ParameterizedTest
@MethodSource("data")
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
void forumCreateNewCommentTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mMinute = calendar.get(Calendar.MINUTE);
    int mSecond = calendar.get(Calendar.SECOND);
    String newCommentContent = "This is a new comment written on the " + mDay + " of " + months[mMonth] + ", " + mYear + " at " + mHour + ":" + mMinute + ":" + mSecond;

    try {
        // Navigate to courses
        NavigationUtilities.toCoursesHome(driver);
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Navigate to forum tab
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Load entries list
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        assertFalse(entriesList.isEmpty(), "No entries found in the forum");

        // Select the first entry
        WebElement entry = ForumNavigationUtilities.getEntry(driver, entriesList.get(0));
        Click.element(driver, entry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));

        // Add a new comment
        WebElement newCommentButton = driver.findElement(FORUM_NEW_COMMENT_BUTTON);
        Click.element(driver, newCommentButton);
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST_MODAL_NEW_COMMENT));
        WebElement textField = driver.findElement(FORUM_COMMENT_LIST_MODAL_NEW_COMMENT_TEXT_FIELD);
        textField.sendKeys(newCommentContent);
        Click.element(driver, FORUM_NEW_COMMENT_MODAL_POST_BUTTON);

        // Verify the new comment
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        WebElement newComment = comments.get(comments.size() - 1); // Assuming the new comment is the last one
        String commentContent = newComment.findElement(FORUM_COMMENT_LIST_COMMENT_CONTENT).getText();
        assertEquals(commentContent, newCommentContent, "Comment content mismatch");
        String commentUser = newComment.findElement(FORUM_COMMENT_LIST_COMMENT_USER).getText();
        assertEquals(commentUser, userName, "Comment user mismatch");
    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}
```
### Few Shot + COT
The obtained test case with 4o-few-shot+CoT:
```java
@ParameterizedTest
@MethodSource("data")
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
void forumCreateNewCommentTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mMinute = calendar.get(Calendar.MINUTE);
    int mSecond = calendar.get(Calendar.SECOND);
    String newCommentContent = "This is a new comment created on " + mDay + " " + months[mMonth] + " " + mYear + " at " + mHour + ":" + mMinute + ":" + mSecond;

    try {
        // Navigate to courses
        NavigationUtilities.toCoursesHome(driver);
        List<String> courses = CourseNavigationUtilities.getCoursesList(driver);
        assertTrue(courses.size() > 0, "No courses in the list");

        // Select a course with an active forum
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courses.get(0));
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Check for existing entries or create a new one
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        WebElement entry;
        if (entriesList.size() <= 0) {
            String newEntryTitle = "New Entry Test " + mDay + mMonth + mYear + mHour + mMinute + mSecond;
            String newEntryContent = "This is the content written on " + mDay + " " + months[mMonth] + " " + mYear + " at " + mHour + ":" + mMinute + ":" + mSecond;
            ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);
            entry = ForumNavigationUtilities.getEntry(driver, newEntryTitle);
        } else {
            entry = ForumNavigationUtilities.getEntry(driver, entriesList.get(0));
        }

        // Navigate to the entry and add a comment
        Click.element(driver, entry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        ForumNavigationUtilities.addComment(driver, newCommentContent);

        // Verify the comment
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        assertFalse(comments.isEmpty(), "No comments found");
        WebElement newComment = comments.get(comments.size() - 1);
        assertEquals(newComment.findElement(FORUM_COMMENT_LIST_COMMENT_CONTENT).getText(), newCommentContent, "Comment content mismatch");
        assertEquals(newComment.findElement(FORUM_COMMENT_LIST_COMMENT_USER).getText(), userName, "Comment user mismatch");

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
void userCreatesNewCommentInForumTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    // Generate a unique title and content for the new entry and comment
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);

    String newEntryTitle = "New Comment Test Entry " + day + month + year + hour + minute + second;
    String newEntryContent = "This is the content for the new entry created on " + day + " of " + months[month] + ", " + hour + ":" + minute + ":" + second;
    String newCommentContent = "This is a new comment added on " + day + " of " + months[month] + ", " + hour + ":" + minute + ":" + second;

    try {
        // Navigate to the courses section
        NavigationUtilities.toCoursesHome(driver);
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));

        // Access the forum tab
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum not activated");

        // Check for existing entries and create a new entry if needed
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        WebElement entry;
        if (entriesList.isEmpty()) {
            ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);
            entry = ForumNavigationUtilities.getEntry(driver, newEntryTitle);
        } else {
            entry = ForumNavigationUtilities.getEntry(driver, entriesList.get(0));
        }

        // Add a new comment
        Click.element(driver, entry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));

        WebElement commentTextField = driver.findElement(FORUM_COMMENT_LIST_MODAL_NEW_COMMENT_TEXT_FIELD);
        commentTextField.sendKeys(newCommentContent);
        Click.element(driver, FORUM_NEW_COMMENT_MODAL_POST_BUTTON);

        // Verify the new comment is visible
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        boolean commentFound = comments.stream()
                .anyMatch(comment -> comment.findElement(FORUM_COMMENT_LIST_COMMENT_CONTENT).getText().equals(newCommentContent));

        assertTrue(commentFound, "New comment was not found in the comment list");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to course forum: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
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
void forumCreateNewCommentTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    try {
        // Navigate to the courses section
        NavigationUtilities.toCoursesHome(driver);

        // Select a specific course
        WebElement course = CourseNavigationUtilities.getCourseByName(driver, courseName);
        course.findElement(COURSE_LIST_COURSE_TITLE).click();

        // Access the forum
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(TABS_DIV_ID)));
        CourseNavigationUtilities.go2Tab(driver, FORUM_ICON);
        assertTrue(ForumNavigationUtilities.isForumEnabled(CourseNavigationUtilities.getTabContent(driver, FORUM_ICON)), "Forum is not enabled");

        // Check for existing entries
        List<String> entriesList = ForumNavigationUtilities.getFullEntryList(driver);
        String newEntryTitle;
        if (entriesList.isEmpty()) {
            // Create a new entry if none exists
            newEntryTitle = "New Entry Test " + System.currentTimeMillis();
            String newEntryContent = "This is a test entry content.";
            ForumNavigationUtilities.newEntry(driver, newEntryTitle, newEntryContent);
        } else {
            newEntryTitle = entriesList.get(0); // Use the first existing entry
        }

        // Select the first entry
        WebElement entry = ForumNavigationUtilities.getEntry(driver, newEntryTitle);
        Click.element(driver, entry.findElement(FORUM_ENTRY_LIST_ENTRY_TITLE));

        // Add a comment
        String commentContent = "This is a test comment.";
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        WebElement commentTextField = driver.findElement(FORUM_COMMENT_LIST_MODAL_NEW_REPLY_TEXT_FIELD);
        commentTextField.sendKeys(commentContent);
        Click.element(driver, FORUM_NEW_COMMENT_MODAL_POST_BUTTON);

        // Verify the comment was added
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(FORUM_COMMENT_LIST));
        List<WebElement> comments = ForumNavigationUtilities.getComments(driver);
        boolean commentFound = comments.stream()
                .anyMatch(comment -> comment.getText().contains(commentContent));

        assertTrue(commentFound, "Comment was not found in the comment list");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to navigate to the course forum: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}


```


