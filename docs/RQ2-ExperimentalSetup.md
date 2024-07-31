## How we select the test cases for the second research questions

The following sections explain how we select the system test cases subset to prompt the different models.

### How we calculate the levenshtein distance

To select the test cases to provide the LLM we employ the Levenshtein distance with their coverage matrix over the
user requirements.The formula used to calculate these distance is:

````vba
Function Levenshtein(s1 As String, s2 As String) As Integer
    Dim i As Integer, j As Integer
    Dim d() As Integer
    Dim cost As Integer
    Dim lenS1 As Integer, lenS2 As Integer
    
    lenS1 = Len(s1)
    lenS2 = Len(s2)
    ReDim d(0 To lenS1, 0 To lenS2)
    
    For i = 0 To lenS1
        d(i, 0) = i
    Next i
    
    For j = 0 To lenS2
        d(0, j) = j
    Next j
    
    For i = 1 To lenS1
        For j = 1 To lenS2
            If Mid(s1, i, 1) = Mid(s2, j, 1) Then
                cost = 0
            Else
                cost = 1
            End If
            
            d(i, j) = Application.Min(d(i - 1, j) + 1, _
                                      d(i, j - 1) + 1, _
                                      d(i - 1, j - 1) + cost)
        Next j
    Next i
    
    Levenshtein = d(lenS1, lenS2)
End Function
 
````

The matrix with the different Levenshtein distance is the following:

| System Test Case                         | **Scenario 1 (A)** | **Scenario 2(B)** | **Scenario 3 (C)** | Scenario 4 | Scenario 5 | Scenario 8 | Scenario 9 | Scenario 10 | Scenario 11 | Scenario 12 | Scenario 13 | **Scenario 14 (D)** | Scenario 15 |
|------------------------------------------|:------------------:|:-----------------:|:------------------:|:----------:|:----------:|:----------:|:----------:|:-----------:|:-----------:|:-----------:|:-----------:|:-------------------:|:-----------:|
| oneToOneChatInSessionChrome              |         7          |         8         |         9          |     14     |     11     |     10     |     9      |     10      |     10      |     10      |     10      |         10          |     13      |
| courseRestOperations                     |         2          |         3         |         3          |     10     |     7      |     5      |     4      |      3      |      5      |      5      |      5      |          5          |      8      |
| courseInfoRestOperations                 |         3          |         4         |         4          |     10     |     8      |     6      |     5      |      4      |      5      |      5      |      4      |          6          |      9      |
| sessionRestOperations                    |         4          |         5         |         5          |     6      |     9      |     7      |     6      |      7      |      7      |      7      |      7      |          7          |     10      |
| forumRestOperations                      |         4          |         5         |         5          |     8      |     5      |     7      |     6      |      7      |      7      |      7      |      7      |          7          |     10      |
| filesRestOperations                      |         3          |         4         |         4          |     8      |     6      |     6      |     5      |      6      |      6      |      6      |      6      |          6          |      9      |
| attendersRestOperations                  |         3          |         4         |         4          |     9      |     8      |     6      |     5      |      6      |      6      |      6      |      6      |          6          |      7      |
| sessionTest                              |         4          |         5         |         6          |     11     |     8      |     7      |     6      |      7      |      7      |      7      |      7      |          7          |     10      |
| oneToOneVideoAudioSessionChrome          |         15         |        16         |         17         |     16     |     17     |     18     |     17     |     18      |     18      |     18      |     18      |         18          |     21      |
| studentCourseMainTest                    |         1          |         2         |         4          |     9      |     6      |     3      |     3      |      4      |      4      |      4      |      4      |          2          |      7      |
| **teacherCourseMainTest (D)**            |         1          |         2         |         4          |     9      |     6      |     3      |     3      |      4      |      4      |      4      |      4      |      **2(D)**       |      7      |
| **teacherCreateAndDeleteCourseTest (C)** |         2          |         3         |      **3(C)**      |     10     |     7      |     5      |     4      |      3      |      5      |      5      |      5      |          5          |      8      |
| teacherEditCourseValues                  |         4          |         5         |         5          |     11     |     7      |     7      |     6      |      5      |      6      |      6      |      5      |          7          |     10      |
| teacherDeleteCourseTest                  |         1          |         2         |         2          |     9      |     6      |     4      |     3      |      4      |      4      |      4      |      4      |          4          |      7      |
| **forumLoadEntriesTest (A)**             |      **0(A)**      |         1         |         3          |     8      |     5      |     3      |     2      |      3      |      3      |      3      |      3      |          3          |      6      |
| **forumNewEntryTest (B)**                |         1          |     **2(B)**      |         3          |     9      |     4      |     4      |     3      |      4      |      4      |      4      |      4      |          4          |      7      |
| forumNewCommentTest                      |         1          |         2         |         3          |     9      |     4      |     4      |     3      |      4      |      4      |      4      |      4      |          4          |      7      |
| forumNewReply2CommentTest                |         1          |         2         |         3          |     9      |     4      |     4      |     3      |      4      |      4      |      4      |      4      |          4          |      7      |
| spiderLoggedTest                         |         0          |         1         |         3          |     8      |     5      |     3      |     2      |      3      |      3      |      3      |      3      |          3          |      6      |
| spiderUnLoggedTest                       |         3          |         2         |         2          |     7      |     4      |     2      |     1      |      2      |      2      |      2      |      2      |          2          |      3      |
| loginTest                                |         2          |         1         |         1          |     6      |     3      |     2      |     0      |      1      |      1      |      1      |      1      |          1          |      4      |

We select the test cases (A) forumLoadEntriesTest, (B) forumNewEntryTest, (C) teacherCreateAndDeleteCourseTest
and (D) teacherCourseMainTest to cover the scenarios (A) Viewing Enrolled Courses, (B) Accessing Courses and Viewing
Classes
(C) Teacher Creating a Course and (D) User Accessing Calendar