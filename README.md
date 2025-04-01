# Shipping Sample Java Project

tbc

# Architecture Decision Record

_(draft)_ Using the same database for the event store and a read model.
The variant _could_ be used for independent event store and other cases than updating read model -
if we don't need at-least-once delivery.

```
             +---------+                  +-----------------+                                           +------------+     
    command  |         |  state mutation  |                 | persist                                   |            |     
    ---------> Handler -------------------> EventRepository --------------------------------------------> EventStore |     
             |         |                  |                 |                                           |            |     
             +---------+                  +----|------------+                                           +------------+     
                                               |                                                                           
                                               |                                                                           
                                           +---v------------+    +-----------------------------+        +-----------------+
                                           |                |    |                             | update |                 |
                                           | EventPublisher -----> @TransactionalEventListener |--------> QueryRepository |
                                           |                |    | ProjectionSynchronizer      |        |                 |
                                           +----------------+    |                             |        +-----------------+
                                                                 +-----------------------------+                           
```

_(draft)_ Using dedicated, independent event store with Change Data Capture.

```
             +---------+                  +-----------------+         +------------+                                 
    command  |         |  state mutation  |                 | persist |            |                                 
    ---------> Handler -------------------> EventRepository ----------> EventStore |                                 
             |         |                  |                 |         |            |                                 
             +---------+                  +-----------------+         +-----|------+                                 
                                                                            | CDC                                    
                                                                            |                                        
                                                                 +----------v-------------+       +-----------------+
                                                                 |                        |update |                 |
                                                                 | ProjectionSynchronizer --------> QueryRepository |
                                                                 |                        |       |                 |
                                                                 +------------------------+       +-----------------+
```

# Project

The project uses pre-compiled convention plugins. Sometimes Gradle does not detect the changes
in the plugin. When you make any modifications to the gradle scripts, make sure to run `./gradlew clean`

_Note: if you're having issues with IntelliJ, try generating IDE metadata:_

```shell
./gradlew cleanIdea idea
```

_and then, reimport the project into IntelliJ._

## Conventions

### Pre-compiled Gradle scripts

- use double quotes (`"`) instead of single quotes (`'`)

### Java/Groovy or any other JVM language, Spring

- do not use wildcard imports, to customize Intellij go to: `Settings > Editor > Code Style > Java/Groovy > Imports`,
  set `Class count to use import with '*'` to `999` and `Names count to use import with '*'` to `999`
- use `.properties` format when defining application properties - do not use `.yml` - it requires a custom handling,
  which gets complicated when you also count the Spring profiles
- prefix spring application libraries' properties file with `application-` - allows supporting project properties
  expansion
- use fluent naming convention for the getters if possible (see: `lombok.config`) to align with
  java `record` (allows seamlessly converting between plain immutable java classes and records)
- use a snake case format to define Gradle tasks

### SQL

- all liquibase change sets should be located in `src/db/liquibase/changelog.yml` (TODO: document migration task)
- column names should be lower-cased
- naming conventions:

| Index/Constraint Type  | Suffix | Naming Convention     | Meaning                     |
|------------------------|--------|-----------------------|-----------------------------|
| Primary key            | pk     | *<table-name>*_pk     | ***P***rimary ***K***ey     |
| Foreign key constraint | fk     | *<table-name>*_fk{xx} | ***F***oreign ***K***ey     |
| Unique constraint      | ak     | *<table-name>*_ak{xx} | ***A***lternative ***K***ey |
| Index                  | ix     | *<table-name>*_ix{xx} | ***I***nde***X***           |
| Check constraint       | ck     | *<table-name>*_ck{xx} | ***C***hec***K***           |
| Default constraint     | df     | *<table-name>*_df{xx} | ***D***e***F***ault         |

- aggregate versioning:

| Column name | Meaning       |
|-------------|---------------|
| ver         | ***Ver***sion |

## Notable tasks

tbc

# Contribution

Before you commit copy all hooks into `.git` directory:

```shell
cp ./.hooks/* .git/hooks/
```

# Dev notes (junk)

Incoming vs outgoing events:

1. Allocate

Revisiting Allocate scenario:

| Step                    | Action                                               | Transaction?                       | Event(s) Emitted                                                            |
|-------------------------|------------------------------------------------------|------------------------------------|-----------------------------------------------------------------------------|
| Allocate Shipment       | Client calls POST /shipments/{id}/allocate           | Yes (DB commit)                    | ShipmentAllocationRequested (incoming)                                      |
| Validate Allocation     | System checks business rules                         | No (part of the same TX)           | -                                                                           |
| Call Carrier API        | System requests shipping label                       | No (external call, not part of TX) | -                                                                           |
| Handle Carrier Response | System updates shipment status based on API response | Yes (DB commit)                    | ShipmentAllocated (Success) / ShipmentAllocationFailed (Failure) (outgoing) |
