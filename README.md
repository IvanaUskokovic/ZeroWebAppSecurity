# ZeroBankApp

Zero Bank is a website for testing purposes that provides online banking

## Dependencies
* Run on macOS Sonoma version 14.3
* IDE for this project is IntelliJ Idea Community Edition 2023.3.3
* Browsers needed is Chrome as mandatory but also preferred are Firefox, Edge and Safari

## Installation

Open terminal in IDE and git clone the repository

```
git clone https://github.com/IvanaUskokovic/ZeroWebAppSecurity.git
```
* java version "21" 2023-09-19 LTS
* Apache Maven 3.9.5

## Executing program
Run all tests from terminal with: 
```
mvn test
```
Run specific class from terminal with: 
```
mvn test -Dtest="LoginTest"
```
Run specific xml file from terminal with:
```
mvn clean test -DsuiteXmlFile="testng.xml"
```
## Framework Walkthrough
Packages:
* Base - Contains classes used through the app
* Helpers - Contains class that could be useful for DDT
* Pages - Contains classes for each page
* Tests - Contains test classes

Files:
* pom.xml - Contains all dependencies used in the project (last updated: 23.01.2024.)
* TestData.xlsx - Excel file used to read some data for DDT as an alternative
* testng.xml - Basic xml file to run all test classes
* .gitignore - File used to store all items that are not pushed to github

## Naming convention
* Classes are written in camel case with first character in upper case
* Methods are written in camel case with first character in lower case
* Class objects are named the same as a class name with lower case for first character
* Test methods are named as test case names

## Other
* Test methods are kept clean
* Each test should have at least 2 assertions with few test exceptions
* Priorities are set with 10 increment, if higher priority occur later in testing, priority of such tests are placed between the two priorities
# ZeroWebAppSecurity
