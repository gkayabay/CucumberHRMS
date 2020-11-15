$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/Database.feature");
formatter.feature({
  "name": "SyntaxHrms database testing",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@dbTesting"
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I create connection with SyntaxHrms database",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I create a statement",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Job Title Validation for SyntaxHrms Database",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@dbTesting"
    },
    {
      "name": "@tag1"
    }
  ]
});
formatter.step({
  "name": "I retrieve all job tittles from database",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I validate results for Job Titles",
  "rows": [
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {}
  ],
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I close all connections",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});