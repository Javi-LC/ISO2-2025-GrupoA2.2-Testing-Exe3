## ISSUE-1: Fix test / package name mismatches

- Priority: High
- Assignee: @student
- Estimated effort: 1-2 hours

Description:

Some tests are failing to run due to a mismatch between directory casing and package declarations (example: `iso2/Exe3/AppTest` vs `ISO2/Exe3/AppTest`). Normalize package names to lowercase and ensure test source files have correct `package` declarations.

Acceptance criteria:
- `mvn -DskipTests=false test` completes and runs all tests.
