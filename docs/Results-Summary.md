# Research Question 1: How LLMs outperform generating scenarios

|         Model          | Prompt Technique | NºScenarios | % Coverage |
|:----------------------:|:----------------:|-------------|-----------:|
|        Baseline        |     Baseline     |             |    79.48 % |
|   gpt-4o-2024-05-13    |     Few Shot     | 15          |      100 % |
|   gpt-4o-2024-05-13    | Few Shot + CoT	  | 16          |      100 % |
| gpt-4o-mini-2024-07-18 |     Few Shot     | 12          |    92.30 % |
| gpt-4o-mini-2024-07-18 | Few Shot + CoT	  | 12          |    97.47 % |

# Research Question 2: How LLMs outperform generating scenarios

|         Model          | Prompt Technique | (A) View Courses | (B) View Classes | (C) Create Course | (D) View Calendar | Average | Average Lines Changed |
|:----------------------:|:----------------:|:----------------:|:----------------:|:-----------------:|:-----------------:|:--------|----------------------:|
|   gpt-4o-2024-05-13    |     Few Shot     |        2         |        5         |        14         |         3         | 6       |               22.64 % |
|   gpt-4o-2024-05-13    | Few Shot + CoT	  |        6         |        9         |        14         |         4         | 8.25    |               31.13 % |
| gpt-4o-mini-2024-07-18 |     Few Shot     |        5         |        7         |      18(14H)      |       6(1H)       | 9       |                31.30% |
| gpt-4o-mini-2024-07-18 | Few Shot + CoT	  |      8 (6H)      |        10        |      15(8H)       |         4         | 9.25    |                 34.9% |