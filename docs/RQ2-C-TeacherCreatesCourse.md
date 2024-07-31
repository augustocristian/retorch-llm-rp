# RQ2 Test cases for Scenario Teacher Creating a Course

## Results

| Model                  | Prompt Strategy | Changes (HAL) | Changes | Total Changes | Total Lines |
|------------------------|:---------------:|:-------------:|:-------:|:-------------:|:-----------:|
| gpt-4o-2024-05-13      |    Few Shot     |      14       |    0    |      14       |     29      |
| gpt-4o-2024-05-13      | Few Shot + CoT  |      14       |    0    |      14       |     28      |
| gpt-4o-mini-2024-07-18 |    Few Shot     |      14       |    4    |      18       |     36      |
| gpt-4o-mini-2024-07-18 | Few Shot + CoT  |       8       |    7    |      15       |     29      |

## Closest Levenshtein Test Case

The closest Levenshtein test case is:

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "course", concurrency = 15, sharing = true, accessMode = "DYNAMIC")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
void teacherCreateAndDeletecourseTest(String mail, String password, String role) throws ElementNotFoundException {
    // Setup
    this.slowLogin(user, mail, password);

    // Create a new course
    String courseTitle = "Test Course_" + System.currentTimeMillis();
    CourseNavigationUtilities.newCourse(user.getDriver(), courseTitle);
    //TO-DO the problem its here
    // Verify the course has been created
    assertTrue(checkIfCourseExists(driver, courseTitle));

    // Delete the course
    CourseNavigationUtilities.deleteCourse(user.getDriver(), courseTitle);

    // Verify the course has been deleted
    assertFalse(checkIfCourseExists(driver, courseTitle));

    // Teardown
    user.getDriver().get(APP_URL);
}
```

## GPT-4o Few-Shot

The obtained test case with 4o and FS:

```java

@ParameterizedTest
@MethodSource("data")
void teacherCreateCourseTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    try {
        // Navigate to the dashboard
        NavigationUtilities.toDashboard(driver);

        // Click on the "Create Course" button
        WebElement createCourseButton = driver.findElement(By.id("create-course-button"));
        Click.element(driver, createCourseButton);

        // Enter course title
        WebElement courseTitleInput = driver.findElement(By.id("course-title-input"));
        courseTitleInput.sendKeys("New Course Title");

        // Optionally upload an image
        WebElement courseImageInput = driver.findElement(By.id("course-image-input"));
        courseImageInput.sendKeys("/path/to/image.jpg");

        // Click on the "Create Course" button
        WebElement createCourseSubmitButton = driver.findElement(By.id("create-course-submit-button"));
        Click.element(driver, createCourseSubmitButton);

        // Wait for the course to appear in the dashboard
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-list")));

        // Verify the new course appears in the teacher's dashboard
        WebElement newCourse = driver.findElement(By.xpath("//div[contains(text(), 'New Course Title')]"));
        assertTrue(newCourse.isDisplayed(), "New course is not displayed in the dashboard");

    } catch (Exception e) {
        fail("Failed to create a new course: " + e.getClass() + ": " + e.getLocalizedMessage());
    }
}

```

### Changes Performed

```diff
- @ParameterizedTest
- @MethodSource("data")
+ @Test
- void teacherCreateCourseTest(String mail, String password, String role) {
+ void teacherCreateCourseTest() {
+    String teacherEmail = "teacher@gmail.com";
+    String teacherPassword = "pass"; 
-    this.slowLogin(user, mail, password);
+    this.slowLogin(user, teacherEmail, teacherPassword);

     try {
         // Navigate to the dashboard
-        NavigationUtilities.toDashboard(driver);
+        NavigationUtilities.toCoursesHome(driver);

         // Click on the "Create Course" button
-        WebElement createCourseButton = driver.findElement(By.id("create-course-button"));
+        WebElement createCourseButton = driver.findElement(By.id("add-course-icon"));
         Click.element(driver, createCourseButton);

         // Enter course title
-        WebElement courseTitleInput = driver.findElement(By.id("course-title-input"));
+        WebElement courseTitleInput = driver.findElement(By.id("input-post-course-name"));
         courseTitleInput.sendKeys("New Course Title");

         // Optionally upload an image
-        WebElement courseImageInput = driver.findElement(By.id("course-image-input"));
-        courseImageInput.sendKeys("/path/to/image.jpg");
+        WebElement fileInput = driver.findElement(By.id("inputPostCourseImage"));
+        File uploadFile = new File("src/test/resources/inputs/image.png");
+        fileInput.sendKeys(uploadFile.getAbsolutePath());

         // Click on the "Create Course" button
-        WebElement createCourseSubmitButton = driver.findElement(By.id("create-course-submit-button"));
+        WebElement createCourseSubmitButton = driver.findElement(By.id("submit-post-course-btn"));
         Click.element(driver, createCourseSubmitButton);

         // Wait for the course to appear in the dashboard
         Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-list")));

         // Verify the new course appears in the teacher's dashboard
-        WebElement newCourse = driver.findElement(By.xpath("//div[contains(text(), 'New Course Title')]"));
+        WebElement newCourse = driver.findElement(By.xpath("//*[contains(text(), 'New Course Title')]"));
         assertTrue(newCourse.isDisplayed(), "New course is not displayed in the dashboard");

     } catch (Exception e) {
         fail("Failed to create a new course: " + e.getClass() + ": " + e.getLocalizedMessage());
     }
 }
```

## GPT-4o Few Shot + COT

The obtained test case with 4o FS+CoT:

```java

@ParameterizedTest
@MethodSource("data")
void teacherCreateCourseTest(String mail, String password, String role) {
    this.slowLogin(user, mail, password);

    try {
        // Navigate to the dashboard
        NavigationUtilities.toDashboard(driver);

        // Click on the "Create Course" button
        WebElement createCourseButton = driver.findElement(By.id("create-course-button"));
        Click.element(driver, createCourseButton);

        // Enter course title
        WebElement courseTitleInput = driver.findElement(By.id("course-title-input"));
        courseTitleInput.sendKeys("New Course Title");

        // Optionally upload an image
        WebElement courseImageInput = driver.findElement(By.id("course-image-input"));
        courseImageInput.sendKeys("/path/to/image.jpg");

        // Click on the "Create Course" button
        WebElement createCourseSubmitButton = driver.findElement(By.id("create-course-submit-button"));
        Click.element(driver, createCourseSubmitButton);

        // Wait for the course to appear in the dashboard
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-list")));

        // Verify the new course appears in the teacher's dashboard
        WebElement newCourse = driver.findElement(By.xpath("//div[contains(text(), 'New Course Title')]"));
        assertTrue(newCourse.isDisplayed(), "New course is not displayed in the dashboard");

    } catch (Exception e) {
        fail("Failed to create a new course: " + e.getClass() + ": " + e.getLocalizedMessage());
    }
}
```

### Changes Performed

```diff
- @ParameterizedTest
- @MethodSource("data")
+ @Test
- void teacherCreateCourseTest(String mail, String password, String role) {
+ void teacherCreateCourseTest() {
+    String teacherEmail = "teacher@gmail.com"; 
+    String teacherPassword = "pass"; 
-    this.slowLogin(user, mail, password);
+    this.slowLogin(user, teacherEmail, teacherPassword);

     try {
         // Navigate to the dashboard
-        NavigationUtilities.toDashboard(driver);
+        NavigationUtilities.toCoursesHome(driver);

         // Click on the "Create Course" button
-        WebElement createCourseButton = driver.findElement(By.id("create-course-button"));
+        WebElement createCourseButton = driver.findElement(By.id("add-course-icon"));
         Click.element(driver, createCourseButton);

         // Enter course title
-        WebElement courseTitleInput = driver.findElement(By.id("course-title-input"));
+        WebElement courseTitleInput = driver.findElement(By.id("input-post-course-name"));
         courseTitleInput.sendKeys("New Course Title");

         // Optionally upload an image
-        WebElement courseImageInput = driver.findElement(By.id("course-image-input"));
-        courseImageInput.sendKeys("/path/to/image.jpg");
+        WebElement fileInput = driver.findElement(By.id("inputPostCourseImage"));
+        File uploadFile = new File("src/test/resources/inputs/image.png");
+        fileInput.sendKeys(uploadFile.getAbsolutePath());

         // Click on the "Create Course" button
-        WebElement createCourseSubmitButton = driver.findElement(By.id("create-course-submit-button"));
+        WebElement createCourseSubmitButton = driver.findElement(By.id("submit-post-course-btn"));
         Click.element(driver, createCourseSubmitButton);

         // Wait for the course to appear in the dashboard
         Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("course-list")));

         // Verify the new course appears in the teacher's dashboard
-        WebElement newCourse = driver.findElement(By.xpath("//div[contains(text(), 'New Course Title')]"));
+        WebElement newCourse = driver.findElement(By.xpath("//*[contains(text(), 'New Course Title')]"));
         assertTrue(newCourse.isDisplayed(), "New course is not displayed in the dashboard");

     } catch (Exception e) {
         fail("Failed to create a new course: " + e.getClass() + ": " + e.getLocalizedMessage());
     }
 }
```

## GPT-4o-mini Few-Shot

The obtained test case with 4o-mini FS:

```java

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "course", concurrency = 1, sharing = true, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@ParameterizedTest
@MethodSource("data")
void teacherCreatesCourseTest(String mail, String password, String role) {
    // Log in with provided credentials
    this.slowLogin(user, mail, password);
    String courseTitle = "New Course Title";
    String courseImagePath = "path/to/image.jpg"; // Optional image path

    try {
        // Navigate to course creation page
        NavigationUtilities.toCourseCreationPage(driver);

        // Enter course title
        WebElement titleInput = driver.findElement(By.id("courseTitleInput")); // Adjust the locator as necessary
        titleInput.sendKeys(courseTitle);

        // Optionally upload an image
        if (courseImagePath != null && !courseImagePath.isEmpty()) {
            WebElement imageUploadInput = driver.findElement(By.id("courseImageUpload")); // Adjust the locator as necessary
            imageUploadInput.sendKeys(courseImagePath);
        }

        // Click on "Create Course" button
        WebElement createCourseButton = driver.findElement(By.id("createCourseButton")); // Adjust the locator as necessary
        Click.element(driver, createCourseButton);

        // Wait for the course to be created and displayed in the dashboard
        Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), '" + courseTitle + "')]"))); // Adjust the locator as necessary

        // Verify that the new course appears in the teacher's dashboard
        List<WebElement> courses = driver.findElements(By.xpath("//div[contains(@class, 'course-title')]")); // Adjust the locator as necessary
        boolean courseCreated = courses.stream().anyMatch(course -> course.getText().equals(courseTitle));
        assertTrue(courseCreated, "The course was not created successfully and does not appear in the dashboard.");

    } catch (ElementNotFoundException notFoundException) {
        fail("Failed to create course:: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
    }
}
```

### Changes Performed

```diff
  @AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
  @AccessMode(resID = "course", concurrency = 1, sharing = true, accessMode = "READWRITE")
  @AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
  @AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
  @AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
- @ParameterizedTest
- @MethodSource("data")
+ @Test
- void teacherCreatesCourseTest(String mail, String password, String role) {
+ void teacherCreatesCourseTest() {
+     String teacherEmail = "teacher@gmail.com"; 
+     String teacherPassword = "pass"; 
      // Log in with provided credentials
-     this.slowLogin(user, mail, password);
+     this.slowLogin(user, teacherEmail, teacherPassword);
      String courseTitle = "New Course Title";
-     String courseImagePath = "path/to/image.jpg"; // Optional image path

      try {
         // Navigate to course creation page
-         NavigationUtilities.toCourseCreationPage(driver);
+         NavigationUtilities.toCoursesHome(driver);
+         WebElement createCourseButton = driver.findElement(By.id("add-course-icon"));
+         Click.element(driver, createCourseButton);
          // Enter course title
-         WebElement titleInput = driver.findElement(By.id("courseTitleInput")); // Adjust the locator as necessary
+         WebElement titleInput = driver.findElement(By.id("input-post-course-name"));// Adjust the locator as necessary
          titleInput.sendKeys(courseTitle);

          // Optionally upload an image
-         if (courseImagePath != null && !courseImagePath.isEmpty()) {
-             WebElement imageUploadInput = driver.findElement(By.id("courseImageUpload")); // Adjust the locator as necessary
-             imageUploadInput.sendKeys(courseImagePath);
-         }
+         WebElement fileInput = driver.findElement(By.id("inputPostCourseImage"));
+         File uploadFile= new File("src/test/resources/inputs/image.png");
+         fileInput.sendKeys(uploadFile.getAbsolutePath());

          // Click on "Create Course" button
-         WebElement createCourseButton = driver.findElement(By.id("createCourseButton")); // Adjust the locator as necessary
+         WebElement createCourseSubmitButton = driver.findElement(By.id("submit-post-course-btn"));
          createCourseSubmitButton.click();
          // Wait for the course to be created and displayed in the dashboard
-         Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), '" + courseTitle + "')]"))); // Adjust the locator as necessary
+         Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + courseTitle + "')]"))); // Adjust the locator as necessary

          // Verify that the new course appears in the teacher's dashboard
-         List<WebElement> courses = driver.findElements(By.xpath("//div[contains(@class, 'course-title')]")); // Adjust the locator as necessary
+         List<WebElement> courses = driver.findElements(By.xpath("//*[contains(@class, 'course-title')]")); // Adjust the locator as necessary
          boolean courseCreated = courses.stream().anyMatch(course -> course.getText().equals(courseTitle));
          assertTrue(courseCreated, "The course was not created successfully and does not appear in the dashboard.");

      } catch (ElementNotFoundException notFoundException) {
          fail("Failed to create course:: " + notFoundException.getClass() + ": " + notFoundException.getLocalizedMessage());
      }
  }

```

## GPT-4o-mini Few Shot + COT

The obtained test case with 4o-mini FS+CoT:

```java

@Test
void teacherCreatesCourse() {
    // Test data
    String teacherEmail = "teacher@example.com"; // Replace with actual test data
    String teacherPassword = "password"; // Replace with actual test data
    String courseTitle = "Test Course Title"; // Replace with desired course title
    String courseImagePath = "path/to/image.jpg"; // Replace with actual image path if needed

    // Step 1: Login as Teacher
    this.slowLogin(user, teacherEmail, teacherPassword);

    // Step 2: Navigate to Course Creation Page
    NavigationUtilities.toDashboard(driver);
    Click.element(driver, By.id("create-course-button")); // Replace with actual button ID

    // Step 3: Fill in Course Details
    WebElement titleInput = driver.findElement(By.id("course-title")); // Replace with actual input ID
    titleInput.sendKeys(courseTitle);

    // Optional: Upload an image if applicable
    if (courseImagePath != null && !courseImagePath.isEmpty()) {
        WebElement imageUpload = driver.findElement(By.id("course-image-upload")); // Replace with actual upload ID
        imageUpload.sendKeys(courseImagePath);
    }

    Click.element(driver, By.id("create-course-submit")); // Replace with actual submit button ID

    // Step 4: Verify Course Creation
    Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard"))); // Wait for dashboard to load
    List<WebElement> courses = driver.findElements(By.className("course-title")); // Replace with actual class name
    boolean courseCreated = courses.stream().anyMatch(course -> course.getText().equals(courseTitle));

    Assertions.assertTrue(courseCreated, "The course was not created successfully.");

    // Step 5: Teardown
    Click.element(driver, By.id("logout-button")); // Replace with actual logout button ID
}
```

### Changes Performed

```diff
+ @Test
- void teacherCreatesCourse() {
+ void teacherCreatesCourse() throws ElementNotFoundException {
      // Test data
-     String teacherEmail = "teacher@example.com"; // Replace with actual test data
+     String teacherEmail = "teacher@gmail.com"; // Replace with actual test data
-     String teacherPassword = "password"; // Replace with actual test data
+     String teacherPassword = "pass"; // Replace with actual test data
      String courseTitle = "Test Course Title"; // Replace with desired course title
-     String courseImagePath = "path/to/image.jpg"; // Replace with actual image path if needed

      // Step 1: Login as Teacher
      this.slowLogin(user, teacherEmail, teacherPassword);

      // Step 2: Navigate to Course Creation Page
-     NavigationUtilities.toDashboard(driver);
+     NavigationUtilities.toCoursesHome(driver);
-     Click.element(driver, By.id("create-course-button")); // Replace with actual button ID
+     Click.element(driver, By.id("add-course-icon")); // Replace with actual button ID

      // Step 3: Fill in Course Details
-     WebElement titleInput = driver.findElement(By.id("course-title")); // Replace with actual input ID
+     WebElement titleInput = driver.findElement(By.id("input-post-course-name")); // Replace with actual input ID
      titleInput.sendKeys(courseTitle);

      // Optional: Upload an image if applicable
-     if (courseImagePath != null && !courseImagePath.isEmpty()) {
-         WebElement imageUpload = driver.findElement(By.id("course-image-upload")); // Replace with actual upload ID
-         imageUpload.sendKeys(courseImagePath);
-     }
+     WebElement fileInput = driver.findElement(By.id("inputPostCourseImage"));
+     File uploadFile = new File("src/test/resources/inputs/image.png");
+     fileInput.sendKeys(uploadFile.getAbsolutePath());

      Click.element(driver, By.id("create-course-submit")); // Replace with actual submit button ID
+     Click.element(driver, By.id("submit-post-course-btn")); // Replace with actual submit button ID

      // Step 4: Verify Course Creation
-     Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard"))); // Wait for dashboard to load
+     Wait.notTooMuch(driver).until(ExpectedConditions.visibilityOfElementLocated(By.className("dashboard-title"))); // Wait for dashboard to load
-     List<WebElement> courses = driver.findElements(By.className("course-title")); // Replace with actual class name
+     List<WebElement> courses = driver.findElements(By.xpath("//*[contains(text(), '" + courseTitle + "')]")); // Replace with actual class name
      boolean courseCreated = courses.stream().anyMatch(course -> course.getText().equals(courseTitle));

      Assertions.assertTrue(courseCreated, "The course was not created successfully.");

      // Step 5: Teardown
-     Click.element(driver, By.id("logout-button")); // Replace with actual logout button ID
+     //Click.element(driver, By.id("logout-button")); // Replace with actual logout button ID Its done by the base Test Class (n
  }

```