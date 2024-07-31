# RQ1 Test Scenarios Generation GPT 4o Few Shot

### Most consistent Execution

### Summary

| Number of Scenarios | % Coverage |
|---------------------|------------|
| 16                  | 100 %      |

### Scenarios Generated:

```markdown
### Scenario 1: Viewing Enrolled Courses
**Objective:** Ensure all users can see the courses they are enrolled in.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User views the list of enrolled courses.

**Expected Output:**
- The dashboard displays a list of all courses the user is enrolled in.

---

### Scenario 2: Accessing Course Details
**Objective:** Ensure all users can access any of the courses and see the classes ordered by date.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User clicks on a specific course.
4. User views the list of classes ordered by date.

**Expected Output:**
- The course page displays a list of classes ordered by date.

---

### Scenario 3: Teacher Creating a Course
**Objective:** Ensure the teacher can create courses with a title and optional image.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the course creation page.
3. Teacher enters a course title.
4. Teacher optionally uploads an image.
5. Teacher clicks the "Create Course" button.

**Expected Output:**
- The system creates the course and displays it in the teacher's dashboard.

---

### Scenario 4: Teacher Creating a Class
**Objective:** Ensure the teacher can create a new class within a course with all required attributes.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks on "Create New Class."
4. Teacher enters the class title, description, date, and time.
5. Teacher invites students.
6. Teacher uploads related files.
7. Teacher enables the forum for the class.
8. Teacher clicks the "Create Class" button.

**Expected Output:**
- The system creates the class and displays it in the course's class list.

---

### Scenario 5: Student Interacting with Courses
**Objective:** Ensure students can interact with courses on their dashboard.

**Steps:**
1. Student logs into the application.
2. Student navigates to the dashboard.
3. Student views associated course information.
4. Student clicks on a course with an enabled forum.
5. Student adds a comment to the forum.
6. Student downloads attached files.

**Expected Output:**
- The dashboard displays course information.
- The forum accepts and displays the student's comment.
- The student successfully downloads attached files.

---

### Scenario 6: Teacher Conducting a Class
**Objective:** Ensure the teacher can conduct classes with all specified functionalities.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific class.
3. Teacher clicks "Start Class."
4. Teacher shares/blocks audio and webcam as needed.
5. Teacher views class information.
6. Teacher communicates via chat.
7. Teacher passes and revokes intervention turns.
8. Teacher ends the class.

**Expected Output:**
- The class starts and all functionalities work as expected.

---

### Scenario 7: Student Attending a Class
**Objective:** Ensure students can attend classes with all specified functionalities.

**Steps:**
1. Student logs into the application.
2. Student navigates to a specific class.
3. Student enters the classroom when the teacher starts it.
4. Student receives the audio and video feed.
5. Student mutes the audio and video feed.
6. Student views class information.
7. Student communicates via chat.
8. Student requests and cancels intervention turns.
9. Student shares audio and webcam when granted speaking request.

**Expected Output:**
- The student successfully attends and interacts in the class.

---

### Scenario 8: User Registration
**Objective:** Ensure users can register using their email and pass a CAPTCHA.

**Steps:**
1. User navigates to the registration page.
2. User enters email and other required details.
3. User completes the CAPTCHA.
4. User clicks "Register."

**Expected Output:**
- The system registers the user and sends a confirmation email.

---

### Scenario 9: User Login
**Objective:** Ensure users can log in to the application.

**Steps:**
1. User navigates to the login page.
2. User enters email and password.
3. User clicks "Login."

**Expected Output:**
- The system logs the user in and redirects to the dashboard.

---

### Scenario 10: Teacher Editing Course Attributes
**Objective:** Ensure the teacher can edit the attributes of a course at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Edit Course."
4. Teacher updates course attributes.
5. Teacher clicks "Save."

**Expected Output:**
- The system updates the course attributes and displays the updated information.

---

### Scenario 11: Teacher Editing Class Attributes
**Objective:** Ensure the teacher can edit the attributes of a class at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific class.
3. Teacher clicks "Edit Class."
4. Teacher updates class attributes.
5. Teacher clicks "Save."

**Expected Output:**
- The system updates the class attributes and displays the updated information.

---

### Scenario 12: User Profile Modification
**Objective:** Ensure users can modify their profiles.

**Steps:**
1. User logs into the application.
2. User navigates to the profile settings page.
3. User updates username, email, password, and profile image.
4. User clicks "Save."

**Expected Output:**
- The system updates the user's profile with the new information.

---

### Scenario 13: Teacher Adding Course Description
**Objective:** Ensure the teacher can add a description to the courses.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Edit Course."
4. Teacher adds a description with text, images, or links.
5. Teacher clicks "Save."

**Expected Output:**
- The system updates the course with the new description.

---

### Scenario 14: CAPTCHA During Registration
**Objective:** Ensure all users pass a CAPTCHA during registration.

**Steps:**
1. User navigates to the registration page.
2. User enters registration details.
3. User completes the CAPTCHA.
4. User clicks "Register."

**Expected Output:**
- The system registers the user only if the CAPTCHA is successfully completed.

---

### Scenario 15: Accessing Calendar
**Objective:** Ensure all users have access to a calendar displaying all their classes.

**Steps:**
1. User logs into the application.
2. User navigates to the calendar page.

**Expected Output:**
- The calendar displays all classes the user is enrolled in.

---

### Scenario 16: Teacher Adding Students to a Course
**Objective:** Ensure the teacher can add students to a course in different ways.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Add Students."
4. Teacher adds students using their unique email address.
5. Teacher adds students using multiple email addresses.
6. Teacher uploads a file with student details.
7. Teacher clicks "Save."

**Expected Output:**
- The system adds the students to the course using the provided methods.

```

### Coverage matrix

| User Requirement | Scenario 1 | Scenario 2 | Scenario 3 | Scenario 4 | Scenario 5 | Scenario 6 | Scenario 7 | Scenario 8 | Scenario 9 | Scenario 10 | Scenario 11 | Scenario 12 | Scenario 13 | Scenario 14 | Scenario 15 | Scenario 16 |
|------------------|:----------:|:----------:|:----------:|:----------:|:----------:|:----------:|:----------:|:----------:|:----------:|:-----------:|:-----------:|:-----------:|-------------|:-----------:|:-----------:|:-----------:|
| 1                |     X      |            |            |            |            |            |            |            |            |             |             |             |             |             |             |             |
| 2                |            |     X      |            |            |            |            |            |            |            |             |             |             |             |             |             |             |
| 3                |            |            |     X      |            |            |            |            |            |            |             |             |             |             |             |             |             |
| 4.1              |            |            |            |     X      |            |            |            |            |            |             |             |             |             |             |             |             |
| 4.2              |            |            |            |     X      |            |            |            |            |            |             |             |             |             |             |             |             |
| 4.3              |            |            |            |     X      |            |            |            |            |            |             |             |             |             |             |             |             |
| 4.4              |            |            |            |     X      |            |            |            |            |            |             |             |             |             |             |             |             |
| 4.5              |            |            |            |     X      |            |            |            |            |            |             |             |             |             |             |             |             |
| 4.6              |            |            |            |     X      |            |            |            |            |            |             |             |             |             |             |             |             |
| 5.1              |            |            |            |            |     X      |            |            |            |            |             |             |             |             |             |             |             |
| 5.2              |            |            |            |            |     X      |            |            |            |            |             |             |             |             |             |             |             |
| 5.3              |            |            |            |            |     X      |            |            |            |            |      X      |             |             |             |             |             |             |
| 6.1              |            |            |            |            |            |     X      |            |            |            |             |             |             |             |             |             |             |
| 6.2              |            |            |            |            |            |     X      |            |            |            |             |             |             |             |             |             |             |
| 6.3              |            |            |            |            |            |     X      |            |            |            |             |             |             |             |             |             |             |
| 6.4              |            |            |            |            |            |     X      |            |            |            |             |             |             |             |             |             |             |
| 6.5              |            |            |            |            |            |     X      |            |            |            |             |             |             |             |             |             |             |
| 6.6              |            |            |            |            |            |     X      |            |            |            |             |             |             |             |             |             |             |
| 6.7              |            |            |            |            |            |     X      |            |            |            |             |             |             |             |             |             |             |
| 6.8              |            |            |            |            |            |     X      |            |            |            |             |             |             |             |             |             |             |
| 7.1              |            |            |            |            |            |            |     X      |            |            |             |             |             |             |             |             |             |
| 7.2              |            |            |            |            |            |            |     X      |            |            |             |             |             |             |             |             |             |
| 7.3              |            |            |            |            |            |            |     X      |            |            |             |             |             |             |             |             |             |
| 7.4              |            |            |            |            |            |            |     X      |            |            |             |             |             |             |             |             |             |
| 7.5              |            |            |            |            |            |            |     X      |            |            |             |             |             |             |             |             |             |
| 7.6              |            |            |            |            |            |            |     X      |            |            |             |             |             |             |             |             |             |
| 7.7              |            |            |            |            |            |            |     X      |            |            |             |             |             |             |             |             |             |
| 7.8              |            |            |            |            |            |            |     X      |            |            |             |             |             |             |             |             |             |
| 8                |            |            |            |            |            |            |            |     X      |            |             |             |             |             |             |             |             |
| 9                |     X      |     X      |     X      |     X      |     X      |     X      |            |            |     X      |             |             |             |             |             |             |             |
| 10               |            |            |            |            |            |            |            |            |            |      X      |             |             |             |             |             |             |
| 11               |            |            |            |            |            |            |            |            |            |             |      X      |             |             |             |             |             |
| 12               |            |            |            |            |            |            |            |            |            |             |             |      X      |             |             |             |             |
| 13               |            |            |            |            |            |            |            |            |            |             |             |             | X           |             |             |             |
| 14               |            |            |            |            |            |            |            |     X      |            |             |             |             |             |             |      X      |             |
| 15               |            |            |            |            |            |            |            |            |            |             |             |             |             |             |             |      X      |
| 16.1             |            |            |            |            |            |            |            |            |            |             |             |             |             |             |             |      X      |
| 16.2             |            |            |            |            |            |            |            |            |            |             |             |             |             |             |             |      X      |
| 16.3             |            |            |            |            |            |            |            |            |            |             |             |             |             |             |             |      X      |

### Execution 1

```markdown
### Scenario 1: Viewing Enrolled Courses
**Objective:** Ensure all users can see the courses they are enrolled in.

**Steps:**
1. User logs into the application.
2. User navigates to the "My Courses" section.
3. System displays a list of courses the user is enrolled in.

**Expected Output:**
- The user sees a list of all courses they are enrolled in.

---

### Scenario 2: Accessing Course Details
**Objective:** Ensure all users can access any of the courses and see the classes ordered by date.

**Steps:**
1. User logs into the application.
2. User navigates to the "My Courses" section.
3. User selects a specific course.
4. System displays the course details, including a list of classes ordered by date.

**Expected Output:**
- The user sees the course details and a list of classes ordered by date.

---

### Scenario 3: Teacher Creating a Course
**Objective:** Ensure the teacher can create a course with a title and an optional image.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the "Create Course" section.
3. Teacher enters a title for the course.
4. Teacher optionally uploads an image for the course.
5. Teacher submits the form to create the course.
6. System saves the course and displays a confirmation message.

**Expected Output:**
- The course is created with the provided title and image, and a confirmation message is displayed.

---

### Scenario 4: Teacher Creating a Class
**Objective:** Ensure the teacher can create a new class within a course with all required attributes.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the "My Courses" section.
3. Teacher selects a specific course.
4. Teacher navigates to the "Create Class" section.
5. Teacher enters the title, description, date and time, invited students, related files, and enables the forum if desired.
6. Teacher submits the form to create the class.
7. System saves the class and displays a confirmation message.

**Expected Output:**
- The class is created with all provided attributes, and a confirmation message is displayed.

---

### Scenario 5: Student Interacting with Courses on Dashboard
**Objective:** Ensure students can interact with courses on their dashboard.

**Steps:**
1. Student logs into the application.
2. Student navigates to the "Dashboard" section.
3. System displays the courses the student is enrolled in with associated information.
4. Student selects a course with an enabled forum.
5. Student adds a comment to the forum.
6. Student downloads attached files if they exist.

**Expected Output:**
- The student sees the courses with associated information, can add comments to the forum, and can download attached files.

---

### Scenario 6: Teacher Conducting a Class
**Objective:** Ensure the teacher can conduct classes with all specified functionalities.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the "My Courses" section.
3. Teacher selects a specific course.
4. Teacher starts a class.
5. Teacher shares/blocks audio and webcam as needed.
6. Teacher views associated class information.
7. Teacher communicates via chat.
8. Teacher passes and revokes intervention turns to/from students.
9. Teacher ends the class.

**Expected Output:**
- The teacher can start the class, manage audio and webcam, view class information, communicate via chat, manage intervention turns, and end the class.

---

### Scenario 7: Student Attending a Class
**Objective:** Ensure students can attend classes with all specified functionalities.

**Steps:**
1. Student logs into the application.
2. Student navigates to the "My Courses" section.
3. Student selects a specific course.
4. Student enters the classroom when the teacher has started it.
5. Student receives the audio and video feed of the teacher.
6. Student mutes/unmutes the audio and video feed of the teacher.
7. Student views class information.
8. Student communicates via chat.
9. Student requests and cancels intervention turns.
10. Student shares their audio and webcam if their speaking request is granted.

**Expected Output:**
- The student can enter the classroom, receive and manage audio/video feed, view class information, communicate via chat, manage intervention turns, and share audio/webcam if allowed.

---

### Scenario 8: User Registration
**Objective:** Ensure users can register using their email and pass a CAPTCHA.

**Steps:**
1. User navigates to the "Register" section.
2. User enters their email and other required details.
3. User completes the CAPTCHA.
4. User submits the registration form.
5. System verifies the CAPTCHA and saves the user details.
6. System sends a confirmation email to the user.

**Expected Output:**
- The user is registered successfully, and a confirmation email is sent.

---

### Scenario 9: User Login
**Objective:** Ensure users can log in to the application.

**Steps:**
1. User navigates to the "Login" section.
2. User enters their email and password.
3. User submits the login form.
4. System verifies the credentials and logs the user in.

**Expected Output:**
- The user is logged in successfully and redirected to the dashboard.

---

### Scenario 10: Teacher Editing Course Attributes
**Objective:** Ensure the teacher can edit the attributes of a course at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the "My Courses" section.
3. Teacher selects a specific course.
4. Teacher navigates to the "Edit Course" section.
5. Teacher updates the course attributes.
6. Teacher submits the form to save changes.
7. System saves the updated course attributes and displays a confirmation message.

**Expected Output:**
- The course attributes are updated successfully, and a confirmation message is displayed.

---

### Scenario 11: Teacher Editing Class Attributes
**Objective:** Ensure the teacher can edit the attributes of a class at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the "My Courses" section.
3. Teacher selects a specific course.
4. Teacher navigates to the "Edit Class" section.
5. Teacher updates the class attributes.
6. Teacher submits the form to save changes.
7. System saves the updated class attributes and displays a confirmation message.

**Expected Output:**
- The class attributes are updated successfully, and a confirmation message is displayed.

---

### Scenario 12: User Modifying Profile
**Objective:** Ensure users can modify their profiles.

**Steps:**
1. User logs into the application.
2. User navigates to the "Profile" section.
3. User updates their username, email, password, and profile image.
4. User submits the form to save changes.
5. System saves the updated profile details and displays a confirmation message.

**Expected Output:**
- The profile details are updated successfully, and a confirmation message is displayed.

---

### Scenario 13: Teacher Adding Course Description
**Objective:** Ensure the teacher can add a description to the courses.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the "My Courses" section.
3. Teacher selects a specific course.
4. Teacher navigates to the "Edit Course" section.
5. Teacher adds a description with text, images, and links.
6. Teacher submits the form to save changes.
7. System saves the course description and displays a confirmation message.

**Expected Output:**
- The course description is added successfully, and a confirmation message is displayed.

---

### Scenario 14: User Accessing Calendar
**Objective:** Ensure all users have access to a calendar displaying all their classes.

**Steps:**
1. User logs into the application.
2. User navigates to the "Calendar" section.
3. System displays a calendar with all the user's classes.

**Expected Output:**
- The user sees a calendar displaying all their classes.

---

### Scenario 15: Teacher Adding Students to a Course
**Objective:** Ensure the teacher can add students to a course using different methods.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the "My Courses" section.
3. Teacher selects a specific course.
4. Teacher navigates to the "Add Students" section.
5. Teacher adds students using their unique email address, multiple email addresses, or a file.
6. Teacher submits the form to add students.
7. System adds the students to the course and displays a confirmation message.

**Expected Output:**
- The students are added to the course successfully, and a confirmation message is displayed.
```

### Execution 2

```markdown
### Scenario 1: Viewing Enrolled Courses
**Objective:** Ensure all users can see the courses they are enrolled in.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User views the list of enrolled courses.

**Expected Output:**
- The dashboard displays a list of courses the user is enrolled in.

---

### Scenario 2: Accessing Courses and Viewing Classes
**Objective:** Ensure all users can access any of the courses and see the classes ordered by date.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User clicks on a course.
4. User views the list of classes ordered by date.

**Expected Output:**
- The course page displays a list of classes ordered by date.

---

### Scenario 3: Teacher Creating a Course
**Objective:** Ensure the teacher can create courses with a title and optional image.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the course creation page.
3. Teacher enters a course title.
4. Teacher optionally uploads an image.
5. Teacher submits the form to create the course.

**Expected Output:**
- The new course is created and displayed on the teacher's dashboard.

---

### Scenario 4: Teacher Creating a Class within a Course
**Objective:** Ensure the teacher can create a new class within a course with all required attributes.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the course page.
3. Teacher clicks on "Create New Class."
4. Teacher enters the class title, description, date, and time.
5. Teacher selects invited students.
6. Teacher uploads related files.
7. Teacher enables the forum for the class.
8. Teacher submits the form to create the class.

**Expected Output:**
- The new class is created and displayed within the course.

---

### Scenario 5: Student Interacting with Courses on Dashboard
**Objective:** Ensure students can interact with courses on their dashboard.

**Steps:**
1. Student logs into the application.
2. Student navigates to the dashboard.
3. Student views the associated information for a course.
4. Student clicks on the forum (if enabled) and adds a comment.
5. Student downloads attached files (if any).

**Expected Output:**
- The student can see course information, add comments to the forum, and download files.

---

### Scenario 6: Teacher Conducting a Class
**Objective:** Ensure the teacher can conduct classes with all specified functionalities.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the class page.
3. Teacher starts the class.
4. Teacher shares or blocks audio and webcam.
5. Teacher views class information (title, duration, attendees, intervention times).
6. Teacher communicates via chat.
7. Teacher passes intervention turn to students.
8. Teacher revokes control of intervention turn from students.
9. Teacher ends the class.

**Expected Output:**
- The teacher can conduct the class with all functionalities working as expected.

---

### Scenario 7: Student Attending a Class
**Objective:** Ensure students can attend classes with all specified functionalities.

**Steps:**
1. Student logs into the application.
2. Student navigates to the class page.
3. Student enters the classroom when the teacher has started it.
4. Student receives the audio and video feed of the teacher.
5. Student mutes the audio and video feed of the teacher.
6. Student views class information (title, duration, attendees).
7. Student communicates via chat.
8. Student asks for an intervention turn.
9. Student cancels a previously requested turn.
10. Student shares their audio and webcam when granted speaking request.

**Expected Output:**
- The student can attend and interact in the class with all functionalities working as expected.

---

### Scenario 8: User Registration
**Objective:** Ensure users can register using their email and pass a CAPTCHA.

**Steps:**
1. User navigates to the registration page.
2. User enters their email, username, and password.
3. User completes the CAPTCHA.
4. User submits the registration form.

**Expected Output:**
- The user is registered and redirected to the login page.

---

### Scenario 9: User Login
**Objective:** Ensure users can log in to the application.

**Steps:**
1. User navigates to the login page.
2. User enters their email and password.
3. User submits the login form.

**Expected Output:**
- The user is logged in and redirected to the dashboard.

---

### Scenario 10: Teacher Editing Course Attributes
**Objective:** Ensure the teacher can edit the attributes of a course at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the course page.
3. Teacher clicks on "Edit Course."
4. Teacher updates the course attributes (title, description, image).
5. Teacher submits the form to save changes.

**Expected Output:**
- The course attributes are updated and displayed on the course page.

---

### Scenario 11: Teacher Editing Class Attributes
**Objective:** Ensure the teacher can edit the attributes of a class at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the class page.
3. Teacher clicks on "Edit Class."
4. Teacher updates the class attributes (title, description, date, time, invited students, files, forum).
5. Teacher submits the form to save changes.

**Expected Output:**
- The class attributes are updated and displayed on the class page.

---

### Scenario 12: User Modifying Profile
**Objective:** Ensure users can modify their profiles.

**Steps:**
1. User logs into the application.
2. User navigates to the profile page.
3. User updates their username, email, password, and profile image.
4. User submits the form to save changes.

**Expected Output:**
- The user's profile is updated and displayed on the profile page.

---

### Scenario 13: Teacher Adding Course Description
**Objective:** Ensure the teacher can add a description to the courses.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the course page.
3. Teacher clicks on "Edit Course."
4. Teacher adds a description with text, images, or links.
5. Teacher submits the form to save changes.

**Expected Output:**
- The course description is updated and displayed on the course page.

---

### Scenario 14: CAPTCHA During Registration
**Objective:** Ensure all users pass a CAPTCHA during registration.

**Steps:**
1. User navigates to the registration page.
2. User enters their email, username, and password.
3. User completes the CAPTCHA.
4. User submits the registration form.

**Expected Output:**
- The user is registered only if the CAPTCHA is successfully completed.

---

### Scenario 15: Accessing Calendar
**Objective:** Ensure all users have access to a calendar displaying all their classes.

**Steps:**
1. User logs into the application.
2. User navigates to the calendar page.

**Expected Output:**
- The calendar displays all the user's classes.

---

### Scenario 16: Teacher Adding Students to a Course
**Objective:** Ensure the teacher can add students to a course using different methods.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the course page.
3. Teacher clicks on "Add Students."
4. Teacher adds students using their unique email address.
5. Teacher adds students using multiple email addresses.
6. Teacher uploads a file (txt, excel, Word, PDF) to add students.
7. Teacher submits the form to add students.

**Expected Output:**
- The students are added to the course and displayed on the course page.

```

### Execution 3

```markdown
### Scenario 1: Viewing Enrolled Courses
**Objective:** Ensure all users can see the courses they are enrolled in.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User clicks on the "My Courses" section.

**Expected Output:**
- The system displays a list of courses the user is enrolled in.

---

### Scenario 2: Accessing Course Details
**Objective:** Ensure all users can access any of the courses and see the classes ordered by date.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User clicks on a specific course.
4. User views the list of classes within the course, ordered by date.

**Expected Output:**
- The system displays the course details and a list of classes ordered by date.

---

### Scenario 3: Teacher Creating a Course
**Objective:** Ensure the teacher can create courses with a title and optional image.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the "Create Course" section.
3. Teacher enters the course title.
4. Teacher uploads an image (optional).
5. Teacher clicks the "Create Course" button.

**Expected Output:**
- The system creates the course and displays it in the teacher's course list.

---

### Scenario 4: Teacher Creating a Class
**Objective:** Ensure the teacher can create a new class within a course with all required attributes.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks on "Create Class."
4. Teacher enters the class title.
5. Teacher enters the class description.
6. Teacher sets the date and time.
7. Teacher adds invited students.
8. Teacher uploads related files (optional).
9. Teacher enables the forum for the class (optional).
10. Teacher clicks "Create Class" button.

**Expected Output:**
- The system creates the class and displays it in the course's class list.

---

### Scenario 5: Student Interacting with Courses
**Objective:** Ensure students can interact with courses on their dashboard.

**Steps:**
1. Student logs into the application.
2. Student navigates to the dashboard.
3. Student clicks on a specific course.
4. Student views the course information.
5. Student adds comments to the forum (if enabled).
6. Student downloads attached files (if available).

**Expected Output:**
- The system displays the course information.
- The student can add comments to the forum.
- The student can download attached files.

---

### Scenario 6: Teacher Conducting a Class
**Objective:** Ensure the teacher can conduct classes with all specified functionalities.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific class.
3. Teacher clicks "Start Class."
4. Teacher shares or blocks audio.
5. Teacher shares or blocks webcam.
6. Teacher views class information.
7. Teacher communicates via chat.
8. Teacher passes intervention turn to students.
9. Teacher revokes control of intervention turn from students.
10. Teacher ends the class.

**Expected Output:**
- The system starts the class.
- The teacher can share/block audio and webcam.
- The teacher can view class information.
- The teacher can communicate via chat.
- The teacher can manage intervention turns.
- The system ends the class.

---

### Scenario 7: Student Attending a Class
**Objective:** Ensure students can attend classes with all specified functionalities.

**Steps:**
1. Student logs into the application.
2. Student navigates to a specific class.
3. Student clicks "Enter Classroom" (when the teacher has started it).
4. Student receives the audio and video feed of the teacher.
5. Student mutes the audio and video feed of the teacher (optional).
6. Student views class information.
7. Student communicates via chat.
8. Student asks for an intervention turn.
9. Student cancels a previously requested turn (optional).
10. Student shares their audio and webcam (if speaking request is granted).

**Expected Output:**
- The student enters the classroom.
- The student receives the audio and video feed.
- The student can mute the feed.
- The student can view class information.
- The student can communicate via chat.
- The student can manage intervention turns.
- The student can share their audio and webcam.

---

### Scenario 8: User Registration
**Objective:** Ensure users can register using their email and pass a CAPTCHA.

**Steps:**
1. User navigates to the registration page.
2. User enters their email.
3. User completes the CAPTCHA.
4. User submits the registration form.

**Expected Output:**
- The system registers the user and sends a confirmation email.

---

### Scenario 9: User Login
**Objective:** Ensure users can log in to the application.

**Steps:**
1. User navigates to the login page.
2. User enters their email and password.
3. User clicks the "Login" button.

**Expected Output:**
- The system logs the user into the application and redirects to the dashboard.

---

### Scenario 10: Teacher Editing Course Attributes
**Objective:** Ensure the teacher can edit the attributes of a course at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Edit Course."
4. Teacher updates the course attributes.
5. Teacher clicks "Save Changes."

**Expected Output:**
- The system updates the course attributes and displays the updated information.

---

### Scenario 11: Teacher Editing Class Attributes
**Objective:** Ensure the teacher can edit the attributes of a class at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific class.
3. Teacher clicks "Edit Class."
4. Teacher updates the class attributes.
5. Teacher clicks "Save Changes."

**Expected Output:**
- The system updates the class attributes and displays the updated information.

---

### Scenario 12: User Modifying Profile
**Objective:** Ensure users can modify their profiles.

**Steps:**
1. User logs into the application.
2. User navigates to the profile settings.
3. User updates the username, email, password, and profile image.
4. User clicks "Save Changes."

**Expected Output:**
- The system updates the user's profile information.

---

### Scenario 13: Teacher Adding Course Description
**Objective:** Ensure the teacher can add a description to the courses.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Edit Course."
4. Teacher adds a description with text, images, or links.
5. Teacher clicks "Save Changes."

**Expected Output:**
- The system updates the course description and displays it.

---

### Scenario 14: CAPTCHA During Registration
**Objective:** Ensure all users pass a CAPTCHA during registration.

**Steps:**
1. User navigates to the registration page.
2. User enters their email.
3. User completes the CAPTCHA.
4. User submits the registration form.

**Expected Output:**
- The system verifies the CAPTCHA and registers the user.

---

### Scenario 15: Accessing Calendar
**Objective:** Ensure all users have access to a calendar displaying all their classes.

**Steps:**
1. User logs into the application.
2. User navigates to the calendar section.

**Expected Output:**
- The system displays a calendar with all the user's classes.

---

### Scenario 16: Teacher Adding Students to a Course
**Objective:** Ensure the teacher can add students to a course using different methods.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Add Students."
4. Teacher adds students using their unique email address.
5. Teacher adds students using multiple email addresses.
6. Teacher uploads a file (txt, excel, Word, PDF) to add students.
7. Teacher clicks "Add Students."

**Expected Output:**
- The system adds the students to the course and displays them in the student list.
```

### Execution 4

```markdown
### Scenario 1: Viewing Enrolled Courses
**Objective:** Ensure all users can see the courses they are enrolled in.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. System displays a list of courses the user is enrolled in.

**Expected Output:**
- The dashboard shows all the courses the user is enrolled in.

---

### Scenario 2: Accessing Course Details
**Objective:** Ensure all users can access any of the courses and see the classes ordered by date.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User clicks on a course.
4. System displays the course details, including a list of classes ordered by date.

**Expected Output:**
- The course page shows all the classes ordered by date.

---

### Scenario 3: Teacher Creating a Course
**Objective:** Ensure the teacher can create courses with a title and optional image.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the course creation page.
3. Teacher enters the course title.
4. Teacher uploads an image (optional).
5. Teacher clicks on the "Create Course" button.
6. System saves the course and displays it on the teacher's dashboard.

**Expected Output:**
- The new course appears on the teacher's dashboard with the entered title and optional image.

---

### Scenario 4: Teacher Creating a Class
**Objective:** Ensure the teacher can create a new class within a course with all required attributes.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks on "Create New Class."
4. Teacher enters the class title, description, date, and time.
5. Teacher selects invited students.
6. Teacher uploads related files (optional).
7. Teacher enables the forum for the class (optional).
8. Teacher clicks on the "Create Class" button.
9. System saves the class and displays it within the course.

**Expected Output:**
- The new class appears within the course with all the entered details.

---

### Scenario 5: Student Interacting with Courses
**Objective:** Ensure the student can interact with the courses on their dashboard.

**Steps:**
1. Student logs into the application.
2. Student navigates to the dashboard.
3. Student clicks on a course.
4. System displays the course details.
5. Student adds a comment to the forum (if enabled).
6. Student downloads attached files (if available).

**Expected Output:**
- The student can see course details, add comments to the forum, and download attached files.

---

### Scenario 6: Teacher Conducting a Class
**Objective:** Ensure the teacher can conduct classes with all specified functionalities.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific class.
3. Teacher clicks on "Start Class."
4. Teacher shares or blocks audio and webcam as needed.
5. Teacher views class information (title, duration, attendees, intervention times).
6. Teacher communicates via chat.
7. Teacher passes intervention turn to students.
8. Teacher revokes control of intervention turn from students.
9. Teacher ends the class.

**Expected Output:**
- The teacher can start, manage, and end the class with all specified functionalities.

---

### Scenario 7: Student Attending a Class
**Objective:** Ensure the student can attend classes with all specified functionalities.

**Steps:**
1. Student logs into the application.
2. Student navigates to a specific class.
3. Student clicks on "Enter Classroom" (when the teacher has started it).
4. Student receives the audio and video feed of the teacher.
5. Student mutes the audio and video feed of the teacher (if needed).
6. Student views class information (title, duration, attendees).
7. Student communicates via chat.
8. Student asks for an intervention turn.
9. Student cancels a previously requested turn (if needed).
10. Student shares their audio and webcam when their speaking request is granted.

**Expected Output:**
- The student can attend and interact in the class with all specified functionalities.

---

### Scenario 8: User Registration
**Objective:** Ensure users can register using their email and pass a CAPTCHA.

**Steps:**
1. User navigates to the registration page.
2. User enters their email, username, and password.
3. User completes the CAPTCHA.
4. User clicks on the "Register" button.
5. System verifies the CAPTCHA and saves the user details.
6. System sends a confirmation email to the user.

**Expected Output:**
- The user is registered successfully and receives a confirmation email.

---

### Scenario 9: User Login
**Objective:** Ensure users can log in to the application.

**Steps:**
1. User navigates to the login page.
2. User enters their email and password.
3. User clicks on the "Login" button.
4. System verifies the credentials and logs the user in.

**Expected Output:**
- The user is logged in and redirected to the dashboard.

---

### Scenario 10: Teacher Editing Course Attributes
**Objective:** Ensure the teacher can edit the attributes of a course at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks on "Edit Course."
4. Teacher updates the course attributes (title, description, image).
5. Teacher clicks on the "Save" button.
6. System saves the updated course attributes.

**Expected Output:**
- The course attributes are updated and displayed correctly.

---

### Scenario 11: User Profile Modification
**Objective:** Ensure users can modify their profiles.

**Steps:**
1. User logs into the application.
2. User navigates to the profile page.
3. User updates their username, email, password, and profile image.
4. User clicks on the "Save" button.
5. System saves the updated profile information.

**Expected Output:**
- The user's profile information is updated successfully.

---

### Scenario 12: Viewing Calendar
**Objective:** Ensure all users have access to a calendar displaying all their classes.

**Steps:**
1. User logs into the application.
2. User navigates to the calendar page.
3. System displays a calendar with all the user's classes.

**Expected Output:**
- The calendar shows all the classes the user is enrolled in.

---

### Scenario 13: Teacher Adding Students to a Course
**Objective:** Ensure the teacher can add students to a course using different methods.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks on "Add Students."
4. Teacher enters a unique email address or multiple email addresses or uploads a file (txt, excel, Word, PDF).
5. Teacher clicks on the "Add" button.
6. System adds the students to the course.

**Expected Output:**
- The students are added to the course successfully.

```

### Execution 5

```markdown

### Scenario 1: Viewing Enrolled Courses
**Objective:** Ensure all users can see the courses they are enrolled in.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User views the list of enrolled courses.

**Expected Output:**
- The dashboard displays a list of all courses the user is enrolled in.

---

### Scenario 2: Accessing Course Details
**Objective:** Ensure all users can access any of the courses and see the classes ordered by date.

**Steps:**
1. User logs into the application.
2. User navigates to the dashboard.
3. User clicks on a specific course.
4. User views the list of classes ordered by date.

**Expected Output:**
- The course page displays a list of classes ordered by date.

---

### Scenario 3: Teacher Creating a Course
**Objective:** Ensure the teacher can create courses with a title and optional image.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to the course creation page.
3. Teacher enters a course title.
4. Teacher optionally uploads an image.
5. Teacher clicks the "Create Course" button.

**Expected Output:**
- The system creates the course and displays it in the teacher's dashboard.

---

### Scenario 4: Teacher Creating a Class
**Objective:** Ensure the teacher can create a new class within a course with all required attributes.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks on "Create New Class."
4. Teacher enters the class title, description, date, and time.
5. Teacher invites students.
6. Teacher uploads related files.
7. Teacher enables the forum for the class.
8. Teacher clicks the "Create Class" button.

**Expected Output:**
- The system creates the class and displays it in the course's class list.

---

### Scenario 5: Student Interacting with Courses
**Objective:** Ensure students can interact with courses on their dashboard.

**Steps:**
1. Student logs into the application.
2. Student navigates to the dashboard.
3. Student views associated course information.
4. Student clicks on a course with an enabled forum.
5. Student adds a comment to the forum.
6. Student downloads attached files.

**Expected Output:**
- The dashboard displays course information.
- The forum accepts and displays the student's comment.
- The student successfully downloads attached files.

---

### Scenario 6: Teacher Conducting a Class
**Objective:** Ensure the teacher can conduct classes with all specified functionalities.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific class.
3. Teacher clicks "Start Class."
4. Teacher shares/blocks audio and webcam as needed.
5. Teacher views class information.
6. Teacher communicates via chat.
7. Teacher passes and revokes intervention turns.
8. Teacher ends the class.

**Expected Output:**
- The class starts and all functionalities work as expected.

---

### Scenario 7: Student Attending a Class
**Objective:** Ensure students can attend classes with all specified functionalities.

**Steps:**
1. Student logs into the application.
2. Student navigates to a specific class.
3. Student enters the classroom when the teacher starts it.
4. Student receives the audio and video feed.
5. Student mutes the audio and video feed.
6. Student views class information.
7. Student communicates via chat.
8. Student requests and cancels intervention turns.
9. Student shares audio and webcam when granted speaking request.

**Expected Output:**
- The student successfully attends and interacts in the class.

---

### Scenario 8: User Registration
**Objective:** Ensure users can register using their email and pass a CAPTCHA.

**Steps:**
1. User navigates to the registration page.
2. User enters email and other required details.
3. User completes the CAPTCHA.
4. User clicks "Register."

**Expected Output:**
- The system registers the user and sends a confirmation email.

---

### Scenario 9: User Login
**Objective:** Ensure users can log in to the application.

**Steps:**
1. User navigates to the login page.
2. User enters email and password.
3. User clicks "Login."

**Expected Output:**
- The system logs the user in and redirects to the dashboard.

---

### Scenario 10: Teacher Editing Course Attributes
**Objective:** Ensure the teacher can edit the attributes of a course at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Edit Course."
4. Teacher updates course attributes.
5. Teacher clicks "Save."

**Expected Output:**
- The system updates the course attributes and displays the updated information.

---

### Scenario 11: Teacher Editing Class Attributes
**Objective:** Ensure the teacher can edit the attributes of a class at any time.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific class.
3. Teacher clicks "Edit Class."
4. Teacher updates class attributes.
5. Teacher clicks "Save."

**Expected Output:**
- The system updates the class attributes and displays the updated information.

---

### Scenario 12: User Profile Modification
**Objective:** Ensure users can modify their profiles.

**Steps:**
1. User logs into the application.
2. User navigates to the profile settings page.
3. User updates username, email, password, and profile image.
4. User clicks "Save."

**Expected Output:**
- The system updates the user's profile with the new information.

---

### Scenario 13: Teacher Adding Course Description
**Objective:** Ensure the teacher can add a description to the courses.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Edit Course."
4. Teacher adds a description with text, images, or links.
5. Teacher clicks "Save."

**Expected Output:**
- The system updates the course with the new description.

---

### Scenario 14: CAPTCHA During Registration
**Objective:** Ensure all users pass a CAPTCHA during registration.

**Steps:**
1. User navigates to the registration page.
2. User enters registration details.
3. User completes the CAPTCHA.
4. User clicks "Register."

**Expected Output:**
- The system registers the user only if the CAPTCHA is successfully completed.

---

### Scenario 15: Accessing Calendar
**Objective:** Ensure all users have access to a calendar displaying all their classes.

**Steps:**
1. User logs into the application.
2. User navigates to the calendar page.

**Expected Output:**
- The calendar displays all classes the user is enrolled in.

---

### Scenario 16: Teacher Adding Students to a Course
**Objective:** Ensure the teacher can add students to a course in different ways.

**Steps:**
1. Teacher logs into the application.
2. Teacher navigates to a specific course.
3. Teacher clicks "Add Students."
4. Teacher adds students using their unique email address.
5. Teacher adds students using multiple email addresses.
6. Teacher uploads a file with student details.
7. Teacher clicks "Save."

**Expected Output:**
- The system adds the students to the course using the provided methods.
```