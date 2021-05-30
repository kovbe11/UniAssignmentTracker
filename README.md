# University Assignment Tracker app

This project implements an application to track the various requirements of a typical University.

Quick summary:

Entities:
- Subject
- Assignment
- Exam
- Project
- ProjectAssignment
- User

Relations:
- Subjects can have assignments, exams, projects
- Projects can have project assigments

Functionality:
- With user role, you can "subscribe" to subjects
- By subscribing to a subject, you can query for your relevant requirements
- ADMIN and USER roles, Admins can modify data, Users can subscribe to subjects
- non-authenticated users can query all data, but can't subscribe to subjects

Other:
- JWT for auth
- Angular Material for frontend

Screenshot of the application:
!["demo-pic"](/docs/app-demo.PNG)
