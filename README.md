# Task Scheduler

A Java command-line application for managing and scheduling tasks.

## Objective

Build a system that allows users to create, manage, search, and execute tasks according to defined scheduling rules.

## Features

The application should allow the user to:

1. Add task
2. List tasks
3. Search task
4. Remove task
5. Complete task
6. Execute next task
0. Exit

Each task has:

- ID
- Title
- Description
- Priority
- Scheduled date
- Status

A task can be `PENDING` or `COMPLETED`.

## Rules

- Task IDs must be unique.
- Required task information cannot be empty.
- Invalid dates must be rejected.
- A task cannot be completed more than once.
- A task that does not exist cannot be removed or completed.
- The scheduler must determine which pending task should be executed next based on the defined scheduling rules.
- Tasks must be searchable and sortable.
- Invalid user input must not terminate the application.

## Dates

Scheduled dates must use an appropriate Java date/time type rather than `String`.

The application must be able to parse, validate, compare, and format dates.

## Error Handling

Domain errors must be handled appropriately.

Use custom exceptions when they provide a clear representation of a domain rule violation.

The application should separate business logic from user-interface messages.

## Constraints

Use only Java features studied so far.

Do not use:

- Maven
- Gradle
- Spring
- Database
- File persistence
- Streams
- Lambdas
- External libraries

The application must run entirely in memory from the command line.

## Definition of Done

The project is complete when the application can:

- Create tasks
- List tasks
- Search tasks
- Remove tasks
- Complete tasks
- Execute the next eligible task
- Sort tasks using different criteria
- Handle dates correctly
- Handle invalid operations
- Run without external dependencies

## Project Status

In development.
