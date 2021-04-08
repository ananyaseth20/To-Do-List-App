# Term Project

## To-Do List Application

##### What will the application do? <br>
My project is a to-do list application/ task manager that allows you to:
 - *add* items to a to-do list
 - *view* to-do list
 - *view* urgent items (due in one day)
 - *remove* items from a to-do list
 - *sort* the items into categories
 - *arrange* items according to their due dates


##### Who will use it? Why is this project of interest to you? <br>
Everyone! This application is for anyone that wants to be more organized in their daily life- it is of special interest to me because I always feel like I have a hard time keeping track of deadlines and unfortunately, sometimes, my work can suffer because of it. I think if I have a task manager application, I will be able to focus on what's important.

##### USER STORIES <br>
- As a user, I want to be able to add items to my to-do list
- As a user, I want to be able to remove items from my to-do list
- As a user, I want to be able to categorise my tasks into one of three categories- 1. high priority (urgent); 2. mid priority (do soon); 3. low priority (do when free)
- As a user, I want to be able to see my tasks sorted by due date
- As a user, I want to be able to view my to-do list
- As a user, I want to be able to view the urgent items in my to-do list (due within a day)
<br> (Phase 2)
- As a user, I want to be able to be given the option to save my to-do list
- As a user, I want to be able to reload my to-do list from file and pick up where I left off
<br> (Phase 4: Task 2)
- Type hierarchy: Tab class is the superclass with subclasses AddTab, HomeTab, LoadTab, SaveTab, ViewTab, and ViewUrgentTab. Each subclass represents a different tab in the tabbed pane on the side bar. The parent class Tab has a method addFunctionality() which each subclass overrides and adds its own distinct functionality to the respective tab (eg.- in LoadTab, addFunctionality() calls the associated method to load the previously saved ToDoList every time the "Load" button is pressed by the user)
<br> (Phase 4: Task 3)
- If I had the chance to refactor my project, I would probably add a class in the model package called UrgentToDoList which would be a subclass of ToDoList and store a to-do list of the urgent tasks that are displayed (tasks due in one day) which would increase cohesion and follow the Single Responsibility principle
