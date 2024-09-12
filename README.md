[![Status](https://github.com/giis-uniovi/samples-giis-template/actions/workflows/test.yml/badge.svg)](https://github.com/giis-uniovi/samples-giis-template/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=my%3Asamples-giis-template&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=my%3Asamples-giis-template)

# Replication package for *'Exploratory study of the usefulness of LLMs in System testing'*

This repository contains the replication package of the paper *Software System Testing assisted by Large Language Models: An Exploratory Study*
published at [TO-DO]

The replication package comprises the test scripts used to generate the test scenarios and system test code as well
as the different inputs required: the user requirements, the system test cases provided as example, the test scenarios
used as input and the scenario used as example. The replication package also provides the different outputs of our
exploratory study in the `\docs` folder, the original raw data is available in the [ZENODO](TO-DO) repository

## Replication package structure and naming conventions:

The naming conventions are :

- **Test Scenarios given as output**  are named using the number of research question as well as the OpenAI model and
  prompting technique (e.g., RQ1-TestScenarios-GPT4oCOT).
- **System Test Cases given as output**  are named using the number of research question, followed by a traceability
  letter (
  e.g. A,B,C or D) and the test case requested (e.g., RQ2-B-AccessCourseViewClasses).
- **Prompt Inputs** are named with the word `input*` followed by the type of input (e.g. inputSystemTestCases.txt,
  inputTestScenarioExample.txt etc.)

The replication package is structured as follows:

1. `/docs`: contains the experimental outputs as well as the experimental baselines, namely as aforementioned .

2. `retorch-llm-rp/src/main`: contains all the necessary Java scripting code for execute the different prompts to the
   OpenAI API.

3. `retorch-llm-rp/src/main/resources`: contains all the necessary inputs for the prompts: scenarios, test cases and
   user requirements as well as the examples.

## Experimental Subject

The experimental subject is a real-world application
called [FullTeaching](https://github.com/codeurjc-students/2019-FullTeaching/tree/Angular-Refactor), used as a
demonstrator of the [ElasTest EU Project](https://elastest.eu/). FullTeaching provides an education platform composed
of several test resources, such as web servers, databases, and multimedia servers that allows to create online
classrooms, classes or publish and create class resources.

To the best of our knowledge, FullTeaching has two test suites available in different
repositories [[1]](https://github.com/elastest/full-teaching) [[2]](https://github.com/codeurjc-students/2019-FullTeaching/tree/Angular-Refactor).
The test suite used to generate the raw datasets provided in this replication package is a compilation of the available
test, cases in these repositories. The test suite is made available as the version TO-DO in
the [retorch-st-fullteaching](https://github.com/giis-uniovi/retorch-st-fullteaching) GitHub repository.

The user requirements are extracted of the FullTeaching documentation (Fuente Pérez, P. (2017). FullTeaching :
Aplicación Web de docencia con videoconferencia.) and translated to english. The spanish version can be
consulted [here](/retorch-llm-rp/src/main/resources/input/inputUserRequirements_spa.txt) and the english
version [here](/retorch-llm-rp/src/main/resources/input/inputUserRequirements_en.txt).

## Treatment Replication Overview

The process consists of two distinct parts: the generation of test scenarios performed through a single
script(`RQ1Experimentation.java`),
and the generation of the test system test cases (`RQ2Experimentation.java`) using the best test scenarios of the first
part.
These two parts are detailed below.

- **Test Scenarios Generation:** This process is accomplished through the execution of a single script that take the
  user requirements as input,
  The output is provided in the resources (`src/main/resources/outputs`), namely with the version of the model and the
  prompting strategy used.

- **System Test Cases Generation:** The process takes the best previously generated test scenarios and several system
  test cases as input. Automatically
  the script makes a cross validation leaving the most close test case in terms of levenshtein distance, and asking the
  model to generate its scenario.
  The output is provided in the resources (`src/main/resources/outputs`), namely with the version of the model and the
  prompting strategy used and the scenario requested.

In both cases the prompts used are stored in the target folder  (`src/main/resources/outputs`) for debugging purposes
The comparison baseline and how we selected the test cases from the original test suite is described in
the [Test Scenarios Baseline](docs/RQ1-TestScenarios-Baseline.md)
and [Experimental Set-up](docs/RQ2-ExperimentalSetup.md)

## Treatment Replication Procedure

To execute the different Java scripts, your system needs the following requirements:

1. Install java and maven, this experimentation was performed using the following versions:
    - Maven 3.9.7
    - Java SE 22.0.1
2. Create an environment variable `CHATGPT_API_KEY` with your OpenAI API token
3. Execute the two Java files.

### Replication procedure outputs

The outputs of the replication procedure are the following:

- Test Scenarios:
    - [OpenAI GPT-4o using Few-Shot](/docs/RQ1-TestScenarios-GPT4o-FShot.md)
    - [OpenAI GPT-4o using Few Shot + Chain-of-Though](/docs/RQ1-TestScenarios-GPT4o-CoT.md)
    - [OpenAI GPT-4o mini using Few-Shot](/docs/RQ1-TestScenarios-GPT4o-mini-FShot.md)
    - [OpenAI GPT-4o mini using Few Shot + Chain-of-Though](/docs/RQ1-TestScenarios-GPT4o-mini-CoT.md)
- System Test Cases (each file contains 4o/4o-mini and both prompting techniques):
    - [User view enrolled courses](/docs/RQ2-A-ViewingEnrolledCourses.md)
    - [User access a course and view its classes](/docs/RQ2-B-AccessCourseViewClasses.md)
    - [Teacher creates a course](/docs/RQ2-C-TeacherCreatesCourse.md)
    - [User access a calendar](/docs/RQ2-D-UserAccessCalendar.md)

## Contributing

See the general contribution policies and guidelines for *giis-uniovi* at
[CONTRIBUTING.md](https://github.com/giis-uniovi/.github/blob/main/profile/CONTRIBUTING.md).

## Contact

Contact any of the researchers who authored the paper; their affiliation and contact information are provided in the
paper itself.

## Citing this work

- Cristian Augusto, Jesús Morán, Antonia Bertolino, Claudio de la Riva, and Javier Tuya, “Exploratory study of the
  usefulness of LLMs in System testing,” in TO-DO, TBC, Ed., TO-DO: TO-DO, 2024, pp.
  1–13.   https://doi.org/TO-DO - [Full Paper available](TO-DO) - [Authors version](TO-DO) -
  [Download citation](TO-DO)

## Acknowledgments

This work was supported in part by the project PID2022-137646OB-C32 under Grant MCIN/ AEI/10.13039/501100011033/FEDER,
UE,
by the [Ministry of Science and Innovation (SPAIN)](https://www.ciencia.gob.es/) and in part by the project MASE
RDS-PTR_22_24_P2.1 Cybersecurity (Italy). 
