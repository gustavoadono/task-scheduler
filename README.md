# Task Scheduler

A Java command-line application for creating, managing, searching, and scheduling tasks.

## Objective

Build a task management system that allows users to create, view, search, remove, complete, and execute tasks according to their priority and scheduled date.

The application runs entirely in memory and is operated through the command line.

## Main Menu

When the application starts, it must display a menu similar to:

```text
1. Add task
2. List tasks
3. Search task
4. Remove task
5. Complete task
6. Execute next task
0. Exit
```

The user selects an operation by entering its corresponding option.

After completing an operation, the application should return to the main menu.

Invalid menu options must be handled without terminating the application.

## Task

Each task contains:

* ID
* Title
* Description
* Priority
* Scheduled date
* Status

### ID

The ID uniquely identifies a task.

The ID must be generated automatically by the system when a task is created.

The user does not provide the ID when creating a task.

Each task must have a unique ID.

### Priority

Each task has one of three priority levels:

* `LOW`
* `MEDIUM`
* `HIGH`

Priority order:

```text
HIGH > MEDIUM > LOW
```

### Status

Each task has one of two statuses:

* `PENDING`
* `COMPLETED`

Every newly created task starts with status `PENDING`.

A `PENDING` task can become `COMPLETED`.

A `COMPLETED` task cannot be completed again.

## Terminal Interaction

When an operation requires multiple pieces of information, the application must request each piece of information separately.

For example, creating a task should follow a flow similar to:

```text
Title: Study Java Collections
Description: Review List, Set and Map
Priority: HIGH
Scheduled date: 2026/09/10
```

The user should not be required to enter the entire task in a single line.

The exact wording of prompts and messages is left to the developer.

## 1. Add Task

The user must be able to create a new task.

The application must request:

* Title
* Description
* Priority
* Scheduled date

The system generates the task ID automatically.

A newly created task must have status `PENDING`.

Title, priority, and scheduled date are required.

Description is optional.

The application must validate the information before creating the task.

A task must not be created when:

* Required information is empty.
* The priority is invalid.
* The scheduled date is invalid.

After successful creation, the application must inform the user that the task was created and display its generated ID.

Example:

```text
Task created successfully.
ID: 1
```

## 2. List Tasks

The user must be able to view all tasks currently registered in the system.

Each displayed task should contain enough information for the user to identify it and understand its current state.

For example:

```text
ID: 1
Title: Study Java Collections
Description: Review List, Set and Map
Priority: HIGH
Scheduled date: 2026/09/10
Status: PENDING
```

The application must support ordering tasks by different criteria.

At minimum, the user must be able to order tasks by:

* Priority
* Scheduled date

The exact interaction used to choose the ordering criterion is left to the developer.

If no tasks are registered, the application must inform the user.

An empty task list is not considered an error.

## 3. Search Task

The user must be able to search for registered tasks.

The application must request a search value separately.

For example:

```text
Search: Collections
```

The search must consider:

* ID
* Title
* Description

ID searches must be exact matches.

Title and description searches must support partial text matching.

Text searches must be case-insensitive.

The search must return all tasks that match the provided value.

If no task matches the search, the application must inform the user.

## 4. Remove Task

The user must be able to remove a task using its ID.

The application must request the task ID separately.

If the task exists, it must be removed from the system.

Both `PENDING` and `COMPLETED` tasks can be removed.

If the task does not exist, the application must inform the user.

Removing one task must not affect the other registered tasks.

## 5. Complete Task

The user must be able to complete a task using its ID.

The application must request the task ID separately.

If the task exists and has status `PENDING`, its status must change to `COMPLETED`.

A task that is already `COMPLETED` cannot be completed again.

If the task does not exist, the application must inform the user.

If the task is already completed, the application must inform the user that the operation cannot be performed.

## 6. Execute Next Task

The user can request the execution of the next task without providing a task ID.

The application must automatically select the next eligible task.

Only tasks with status `PENDING` are eligible for execution.

The next task must be selected according to the following rules:

1. Higher priority comes first.
2. If two tasks have the same priority, the task with the earlier scheduled date comes first.
3. If two tasks have the same priority and scheduled date, the task with the lower ID comes first.

A task whose scheduled date has already passed is still eligible for execution.

The scheduled date determines the order of execution, but does not prevent a task from being executed.

When a task is executed:

* The selected task must be displayed to the user.
* Its status must change from `PENDING` to `COMPLETED`.

Execution in this project means selecting the next eligible task and changing its status to `COMPLETED`.

The application does not need to perform any real-world action associated with the task.

If there are no pending tasks, the application must inform the user that there are no tasks available for execution.

## Dates

The scheduled date must be represented using an appropriate Java date/time type rather than a `String`.

The required date format is:

```text
yyyy-MM-dd
```

For example:

```text
2026-09-10
```

The application must:

* Parse dates entered by the user.
* Reject invalid dates.
* Compare scheduled dates.
* Determine which scheduled date comes first.
* Display dates consistently.

Invalid calendar dates must be rejected.

A scheduled date may be in the past.

## Error Handling

Invalid user input must not terminate the application.

Expected problems must be handled appropriately and allow the user to continue using the application.

Examples include:

* Invalid menu option.
* Empty required information.
* Invalid priority.
* Invalid date.
* Task not found.
* Attempt to complete an already completed task.
* No pending tasks available for execution.

At least one business/domain rule must be represented using a custom exception.

User-interface messages must remain separate from business logic.

## Data Storage

Tasks are stored only in memory.

No persistence is required.

The application does not need to preserve tasks between executions.

When the application terminates, all registered tasks may be lost.

## Constraints

Use only Java features studied so far.

Do not use:

* Maven
* Gradle
* Spring
* Database
* File persistence
* Streams
* Lambdas
* External libraries

The application must run directly from the command line using the Java compiler and runtime.

## Implementation Decisions

The following decisions are intentionally left to the developer:

* Class structure
* Package structure
* Responsibilities of each class
* How task IDs are generated
* How tasks are stored
* How tasks are searched
* How tasks are ordered
* How the next task is selected
* How validation is organized
* Which operations require exceptions
* Which custom exception(s) to create
* How user input is processed
* How the menu and terminal interaction are organized

Choose the simplest design that satisfies the requirements.

Do not introduce abstractions or technologies solely for the purpose of demonstrating a Java feature.

## Definition of Done

The project is complete when the application can:

* Start from the terminal.
* Display an interactive menu.
* Create tasks through individual terminal inputs.
* Generate a unique ID for each task.
* Validate task information.
* Accept optional descriptions.
* Reject invalid priorities and dates.
* List all registered tasks.
* Order tasks by priority.
* Order tasks by scheduled date.
* Search tasks by ID, title, and description.
* Perform exact ID searches.
* Perform partial, case-insensitive title and description searches.
* Return all matching search results.
* Remove tasks by ID.
* Remove both pending and completed tasks.
* Complete pending tasks.
* Prevent completing an already completed task.
* Select the next pending task according to priority, scheduled date, and ID.
* Execute the selected task.
* Execute tasks whose scheduled dates are in the past.
* Handle the absence of pending tasks.
* Handle invalid user input without terminating.
* Represent at least one domain rule with a custom exception.
* Keep business logic separate from user-interface messages.
* Store all data in memory.
* Run without external dependencies.

