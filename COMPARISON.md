# Initial vs Final Reports Comparison

This file summarises the differences between the initial project state (baseline from `main` before maintenance) and the final state (after the changes applied in the `Maintenance` branch and merged to `main`). The full generated sites are available under `site-comparison/before` and `site-comparison/after` in this repository.

## Key metrics
- Checkstyle errors: **35** (baseline) → **8** (after)
- PMD warnings: **0** → **0**
- JaCoCo coverage: not reliable (JaCoCo 0.8.8 attempted instrumentation and failed on JDK 25 system classes) — coverage reports must be generated under a compatible JDK or with an updated JaCoCo.

## Checkstyle details (final)
- Files checked: 2
- Errors: 8 (breakdown: `ActivityRecommender.java` — 7 errors; `package-info.java` — 1 error)
- Main remaining rule violations: `PackageName` (naming) and `LineLength` (sizes).

## What changed (summary)
- Added `package-info.java` and Javadoc to reduce many javadoc-related errors.
- Fixed duplicate/legacy source files and normalized packages to lowercase for tests and sources.
- Updated `pom.xml` to include reporting plugins and bumped version to `0.0.2-SNAPSHOT` after merge.

## Artifacts
- Baseline site: `site-comparison/before` (zipped at `site-comparison/before.zip`)
- Final site: `site-comparison/after` (zipped at `site-comparison/after.zip`)

## Remaining tasks to satisfy the lab enunciado
1. Finish fixing Checkstyle errors (the remaining 8): add missing `@param`/`@return` tags where appropriate and wrap/break long lines to comply with `LineLength` rule; fix `PackageName` rule by ensuring package naming conventions match the checks (or adjust rules if required by course).
2. Run FindBugs/SpotBugs analysis and analyze results (plugin was removed earlier due to compatibility issues). Decide whether to re-enable SpotBugs by updating the plugin version or by running under a compatible JDK.
3. Produce a full maintenance plan document on the project wiki with:
   - Summary of system status (PMD/SpotBugs/Checkstyle results)
   - List of changes to be made, priority, feasibility, impact analysis and assignment of responsibilities (open issues/tickets)
   - Execution report with links to before/after sites and screenshots (optional) — `MAINTENANCE.md` contains an execution summary you can adapt for the wiki.
4. Ensure tests run cleanly on `main` after changes: there is still a test-class/package name mismatch reported by Surefire (`iso2/Exe3/AppTest (wrong name: ISO2/Exe3/AppTest)`) — reconcile source file locations and package declarations so Surefire runs tests.
5. Obtain reliable coverage reports: either run the build/tests under a JDK supported by JaCoCo 0.8.8 (e.g., JDK 17) or update JaCoCo to a version that supports JDK 25.
6. Close the maintenance loop: open PRs, assign and close issues, and when satisfied merge and tag a release (update any component patch numbers as required by the enunciado).

## How I produced the comparison
1. Created a temporary worktree from the pre-merge commit and ran `mvn checkstyle:checkstyle` to produce the baseline Checkstyle report.
2. Ran `mvn clean site` on the current branch to generate the final site.
3. Copied the two `target/site` outputs into `site-comparison/before` and `site-comparison/after` and produced this summary.

---
Files: `COMPARISON.md`, `MAINTENANCE.md`, `site-comparison/before/`, `site-comparison/after/`.
