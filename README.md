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


# Lab: 31 - Espresso and Polish
## Overview
## Feature Tasks
### Espresso Testing
Add Espresso to your application, and use it to test basic functionality of the main components of your application. For example:

. assert that important UI elements are displayed on the page
. tap on a task, and assert that the resulting activity displays the name of that task
. edit the user’s username, and assert that it says the correct thing on the homepage




# lab 32- Integrating AWS for Cloud Data Storage
## Overview
will gain a scalable backend by using AWS Amplify. We’ll continue to work with Amplify to add more cloud functionality for the rest of the course

## Feature Tasks

 ### Tasks Are Cloudy
Using the amplify add api command, create a Task resource that replicates our existing Task schema. Update all references to the Task data to instead use AWS Amplify to access your data in DynamoDB instead of in Room.

### Add Task Form
Modify your Add Task form to save the data entered in as a Task to DynamoDB.

### Homepage
Refactor your homepage’s RecyclerView to display all Task entities in DynamoDB.


## Lab: 33 - Related Data
### Overview
add a second resource on backend.

## Feature Tasks
### Tasks Are Owned By Teams
Create a second entity for a team, which has a name and a list of tasks. Update your tasks to be owned by a team.

Manually create three teams by running a mutation exactly three times in your code. (You do NOT need to allow the user to create new teams.)

### Add Task Form
Modify your Add Task form to include either a Spinner or Radio Buttons for which team that task belongs to.

### Settings Page
In addition to a username, allow the user to choose their team on the Settings page. Use that Team to display only that team’s tasks on the homepage.


# Adding Cognito - 36
## Overview
In this lap we  will allow users to sign up and log in using Cognito.

## Feature Tasks
### User Login
Add Cognito to Amplify setup. Add in user login and sign up flows to your application, using Cognito’s pre-built UI as appropriate. Display the logged in user’s username somewhere relevant in your app.

###User Logout
Allow users to log out of your application.


# S3- Uploads
## Overview
will allow users to upload files related to tasks, like screenshots or log files  

## Feature Tasks
### Uploads
On the “Add a Task” activity, allow users to optionally select a file to attach to that task. If a user attaches a file to a task, that file should be uploaded to S3, and associated with that task.

### Displaying Files
On the Task detail activity, if there is a file that is an image associated with a particular Task, that image should be displayed within that activity.

# Lab: 38 - Notifications
## Overview
add the ability for push notifications to be delivered to your app from the cloud.

## Feature Tasks
Notifications on Task Creation
When a new task is created within a team, alert all users who are a part of that team about that new task.

# Lab: 41 - Intent Filters
## Overview
 allow users to “share” a text from another app and open TaskMaster

## Feature Tasks
Adding a Task from Another Application

Also allow users to share text content from another application, and have that automatically populated on the Add a Task activity.


# Location 
## overview
add the user’s location to a task automatically when that task is created.

## Feature Tasks
### Location
When the user adds a task, their location should be retrieved and included as part of the saved Task.

### Displaying Location
On the Task Detail activity, the location of a Task should be displayed if it exists.