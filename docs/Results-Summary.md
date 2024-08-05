# Research Question 1: How LLMs perform generating scenarios

|         Model          | Prompt Technique | % Coverage    | Median | STD Desv. |
|:----------------------:|:----------------:|:--------------|-------:|----------:|
|        Baseline        |     Baseline     | 79.48 %       |        |           |
|   gpt-4o-2024-05-13    |     Few Shot     | 98.97 % (AVG) |    100 |      2.39 |
|   gpt-4o-2024-05-13    | Few Shot + CoT	  | 100 % (AVG)   |    100 |         0 |
| gpt-4o-mini-2024-07-18 |     Few Shot     | 94.36 % (AVG) |  94.87 |      3.34 |
| gpt-4o-mini-2024-07-18 | Few Shot + CoT	  | 91.79 % (AVG) |  92.31 |      4.58 |

# Research Question 2: How LLMs perform generating test cases

|         Model          | Prompt Technique | (A) View Courses | (B) View Classes | (C) Create Course | (D) View Calendar | Average | Average Lines Changed |
|:----------------------:|:----------------:|:----------------:|:----------------:|:-----------------:|:-----------------:|:--------|----------------------:|
|   gpt-4o-2024-05-13    |     Few Shot     |        2         |        5         |        14         |         3         | 6       |               22.64 % |
|   gpt-4o-2024-05-13    | Few Shot + CoT	  |        6         |        9         |        14         |         4         | 8.25    |               31.13 % |
| gpt-4o-mini-2024-07-18 |     Few Shot     |        5         |        7         |      18(14H)      |       6(1H)       | 9       |                31.30% |
| gpt-4o-mini-2024-07-18 | Few Shot + CoT	  |      8 (6H)      |        10        |      15(8H)       |         4         | 9.25    |                 34.9% |