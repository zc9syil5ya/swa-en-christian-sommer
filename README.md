# SWA Module, Graded Exercise

## Project

* Matriculation Number: 16-665-176
* Evento-ID:	10003529
* E-Mail: christian.sommer@students.fhnw.ch

You might choose between following **Java** applications:
* Contact list
* Library

The delivered application should be composed of multiple **modules** resp. should generate multiple **jars**.

The **frontend** (or **view** - probably in its own **module**/**component**) might be a **REST** API or **Server Pages** based. If you implement a **REST** API, then you will have to provide me a client (i.e., **React**) or you will have to tell how I can realise the minimum requirements (see below).

To start the application, you might use one of the following mechanisms:
* Using **Java** [modules](https://github.com/ribeaud/ch.fhnw.swa.modserv.cli/blob/master/Commands.txt).
* [Repacking](https://ribeaud.github.io/SWA/lectures/5/#10) a multi-**modules** **Spring Boot** application into a single **jar**.
* Using `-classpath` **Java** option.

#### Minimum requirements

The application should be able to, at least, do the following:
* List the entities
* Create/edit one entity

You will have to use a repository (or database), where you will _persist_ the entities. This repository could be a _real_ database (deployed via **Docker** for instance), an _in-memory_ one or a simple file.

#### Suggested course of action

1. Think about the components you will need
1. Draw a component/class diagram
1. Start to code

#### Possible extensions

* Delete
* Entity:
  * `Magazine` (as extension of `Book`)
  * `Company` (as extension of `Person`)
* Image upload (in its own *module*)

#### Reference applications

* [ch.fhnw.swa.modserv.cli](https://github.com/ribeaud/ch.fhnw.swa.modserv.cli)
* [myshop](https://github.com/ribeaud/blog-code-samples/tree/master/myshop)
* [PetClinic](https://github.com/spring-projects/spring-petclinic)

## Project delivery

### License
    Copyright 2020 Christian Sommer <christian.sommer@students.fhnw.ch>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

### Requirements
* JDK 14
* Node.Js >15
* Maven
* IntelliJ IDE (recommended)

### How to start the project:

####BACKEND
To start the <b>backend</b> run `AddressBookApplication.java` in the `web` module (`package ch.fhnw.swa.web`).

![abb0.png](abb0.png)

The server can reached over:
`http://localhost:8080/entries` or `http://localhost:8080/entries/1` etc...

The following HTTP methods are available on the server side:

* [GET]  `/entries` - returns all entries in the address book.
* [GET]  `/entries/{id}` - returns an entry by ID.
* [DELETE] `/entries/{id}` - deletes an entry by ID.
* [PUT] `/entries/{id}` - updates an entry by ID.
* [POST] `/upload/{id}`- post a avatar for the entry by ID.

so possible actions are, create an entry, edit an entry, delete entry, add an avatar to an entry, edit avatar on the entry
over curl or the frontend-app UI. 

####Frontend

To start the <b>frontend</b> run `npm install` and then `npm start` module (`frontend-app` folder).

1) `C:\Users\example\Documents\GitHub\swa-en...\frontend-app> npm install`
2) `C:\Users\example\Documents\GitHub\swa-en...\frontend-app> npm start`

the frontend can be reached via the url `http://localhost:3000/`. Navigation in the frontend should be self-explanatory.

#### Test the project

All tests can be started with a right mouse click `Run 'All Tests'` on the `java` folder in the test directory of the web module.

![abb1.png](abb1.png)

#### External contributions: 
<i>none</i>

#### Other comments:
1) I generated the basic structure of the frontend app with npx. `npx create-react-app frontend-app`

2) UML diagrams and description can be found in the pdf `UML diagrams and description for SWA assignment 20HS 3Ib from 
   Christian Sommer.pdf` in the main project folder.

#### proud of
I'm particular proud of: <i>that images can be uploaded and are validated by size and type</i>

## Project grading

(_to be filled by lecturer_)
