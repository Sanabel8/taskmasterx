#  Lab: 26  Beginning TaskMaster
# Overview
start building an Android app using android studio Work in  new taskmaster repo.
# Feature Tasks
### Homepage
 it should have a heading at the top of the page, an image to mock the “my tasks” view, and buttons at the bottom of the page to allow going to the “add tasks” and “all tasks” page.

### Add a Task
allow users to type in details about a new task, specifically a title and a body. When users click the “submit” button, show a “submitted!” label on the page.

### All Tasks
The all tasks page should just be an image with a back button; it needs no functionality.

![main](screenshote\main page.jpg)
![add](screenshote\add task.jpg)
![all](screenshote\all task.jpg)


#  Lab:  27 - Data in TaskMaster
# Overview
continue working in the first repo
# Feature Tasks
### Task Detail Page
Create a Task Detail page. It should have a title at the top of the page, and a Lorem Ipsum description.

### Settings Page
Create a Settings page. It should allow users to enter their username and hit save.

### Homepage
The main page should be modified to contain three different buttons with hardcoded task titles. When a user taps one of the titles, it should go to the Task Detail page, and the title at the top of the page should match the task title that was tapped on the previous page.

The homepage should also contain a button to visit the Settings page, and once the user has entered their username, it should display “{username}’s tasks” above the three task buttons.

![main27](screenshote\main page.jpg)
![details](screenshote\details.jpeg)
![setting](screenshote\setting page.jpg)

# RecyclerViews for Displaying Lists of Data /lab28
## Overview
refactor your homepage to look snazzy, with a RecyclerView full of Task data.

## Feature Tasks
### Task Model
Create a Task class. A Task should have a title, a body, and a state. The state should be one of “new”, “assigned”, “in progress”, or “complete”.

### Homepage
Refactor your homepage to use a RecyclerView for displaying Task data. This should have hardcoded Task data for now.

Some steps you will likely want to take to accomplish this:

Create a ViewAdapter class that displays data from a list of Tasks.
In your MainActivity, create at least three hardcoded Task instances and use those to populate your RecyclerView/ViewAdapter.
Ensure that you can tap on any one of the Tasks in the RecyclerView, and it will appropriately launch the detail page with the correct Task title displayed.

# screen shote
![main-page](screenshote\main page lab28.jpg)

# lab29 Room
## Overview
 refactor your model layer to store Task data in a local database by using room 

The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite

# Feature Tasks
### Task Model and Room
set up Room in  application, and modify Task class to be an Entity.

### Add Task Form
Modify Add Task form to save the data entered in as a Task in local database.

### Homepage
Refactor homepage’s RecyclerView to display all Task entities in database.

### Detail Page
Ensure that the description and status of a tapped task are also displayed on the detail page, in addition to the title.

## screenshots
![main-page](screenshote\main29.jpeg)
![addTask-page](screenshote\addTask29.jpeg)
![setting-page](screenshote\setting29.jpeg)
![detalies-page](screenshote\detailes29.jpeg)
