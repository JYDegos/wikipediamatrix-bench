---
title: "Wikipedia HTML extractor to CSV"
author: "Jean-Yves Degos"
date: "20/12/2020"
output: html_document
---

### 1. Outline

This software aims to test a Wikipedia HTML Extractor on a list of 336 URLs, which are given in a .txt file, namely **/inputdata/wikiurls.txt**. For each extracted table, a record of statistics is done into a file **BenchTestStats.csv** in the directory **output/html/**. At the moment, there are non execution errors: whenever it is possible, a CSV is file in the right directory with the right name, corresponding to a 'right table'. But, for most of the these, we have not checked is the generated CSV file is correct, w. r. t. what would be produced by a human visual scanning.

### 2. License

CC-BY-SA 3.0

### 3. Usage

Maven is required to build the projet. Some JUnit tests can be performed, as they appear in the directory **src/test/java/** : *WikipediaHTMLExtractorTest*, and *BenchTest*. To enjoy the project:
1. Clone it into a desired location;
2. Open the folder **src/test/java/** of the resulting local repository;
3. Then right-click on *WikipediaHTMLExtractorTest* or *BenchTest* to run the tests.

### 4. Architecture

This is a Maven project. There are 4 classes in the directory **src/main/java/**:
- *App* is unused for the moment;
- *CSVReader* is unused for the moment;
- *WikipediaHTMLExtractor* contains the main part of the process;
- *Table* is usued to saved statistics about the extracted tables.

Here is the UML class diagram (*undefined* stands for *void*):

https://app.genmymodel.com/editor/edit/_9S9vUELmEeufTvxJgLajhw?locale=fr#

(Caution: an account is needed to access the above webpage).

Given an URL, the main method tries to connect to it. If the connection is successful, an HTML Document is created. Next, this document is scanned to know if it contains 'raw tables'. Raw tables are defined here to be any HTML code contained between <TABLE></TABLE> HTML tags. A 'right' table (namely simply 'table') are raw tables of class 'wikitable sortable'. In addition, right tables are currently supposed to contain nor rowspan, neither colspan attributes for the <td></td> and </tr></tr>, because handling these table in a first step of the projet would have been to complex.

#### 4.1 The method **WikipediaHTMLExtractor.getDocument(String):Document**

Tries to connect to an URL, given as an argument of type String. If the connection is successful, using *jsoup*, the method return a HTML Document. If not, an exception is catched, an error message is printed in the console, and an empty HTML document is returned.

#### 4.2 The method **WikipediaHTMLExtractor.extraction(String):Elements**

Given an URL as an argument of type String, this method calls the method *getDocument* (see below) on it, and converts the HTML Document to a list of type Elements, which are tables of class 'wikitable sortable'. These tables are the 'raw tables'.

#### 4.3 The method **WikipediaHTMLExtractor.writeToCSV(int, int, Element,String):Table**

This method is used to write a table, given as an Element, to a CSV file the name of which is given by a String (including the path).

#### 4.4 The method **Table.addToCSVstat():void**

This method is used to record statistics about the table which is currently treated.






