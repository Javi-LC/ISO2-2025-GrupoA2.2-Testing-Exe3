## ISSUE-4: Obtain reliable coverage reports

- Priority: Medium
- Assignee: @maintainer
- Estimated effort: 1-2 hours

Description:

JaCoCo 0.8.8 fails to instrument some JDK 25 system classes. Two options:

- Update JaCoCo to a version supporting JDK 25 (investigate compatibility).
- Run tests under JDK 17 on CI to produce reliable coverage with current JaCoCo.

Acceptance criteria:
- Coverage reports generated and validated; JaCoCo errors no longer observed.
