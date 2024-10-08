# Project Overview

This project is a console application for managing departments and lectors, featuring:

## Commands:

- Who is head of department {department_name}.
- Show {department_name} statistics.
- Show the average salary for the department {department_name}.
- Show count of employee for {department_name}.
- Global search by {template}.

## Profiles:

Application supports `development` and `test` profiles.

## How it works:

- The application reads console input using the `Scanner` class.
- Input is passed to the `CommandProcessor`, which checks if any command can process it via the `supports(String)`
  method.
- The appropriate command then processes the line and produces the result.

## Database Management:

Utilizes Flyway for migrations, with the following scripts:

- `V1__init_tables.sql`
- `V2__insert_defaults.sql`

## Dockerization :

Application is containerized using Docker for easy deployment.

- To use the application, retrieve the running image ID with `docker ps` and attach to its execution terminal
  using `docker attach {image_id}`.

## Deprecated? Aren't in use, but are present in codebase:

- Services
- DTOs
- Mappers

## Extensibility:

- `CommandResult` for encapsulating results.
- Any new command should extend the `AbstractCommand` class and to be added in Configuration bean of `CommandProcessor`.
