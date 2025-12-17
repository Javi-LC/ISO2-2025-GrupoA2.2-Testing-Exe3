# Maintenance Plan and Execution — Lab Session 5

## Summary
This document summarizes the maintenance actions performed as part of the lab: add quality checkers/plugins, run reports, define small maintenance tasks and implement a subset of fixes required by the Checkstyle report.

## What I changed
- Added `package-info.java` for `iso2.exe3.domain` to satisfy the `JavadocPackage` rule.
- Added Javadoc comments for constants and the `getRecommendation(...)` method in `ActivityRecommender` and wrapped the method signature to reduce line length.
- Removed a duplicate source file that caused compilation/package mismatches.
- Created branch `Maintenance` and small task branches for the fixes (history in git).

## How to reproduce
Run from repository root:

```bash
mvn -f "pom.xml" clean site -DskipTests=false
```

After running, open the generated site in `target/site/index.html`. Notable reports:
- Checkstyle: `target/site/checkstyle.html`
- PMD: `target/site/pmd.html`
- JaCoCo: `target/site/jacoco-aggregate/index.html` (may be incomplete)

## Results (before → after)
- Checkstyle errors on `ActivityRecommender.java`: 24 → 8 (reduced by adding package-info + Javadoc + line-wrap)
- PMD: no issues reported.
- JaCoCo: agent 0.8.8 attempted instrumentation and failed on some JDK 25 system classes (Unsupported class file major version 69). Coverage report generation proceeded but execution data may not match runtime classes; coverage is therefore unreliable under JDK 25 with this JaCoCo version.

## Known remaining issues
- 8 Checkstyle errors remain (mainly Javadoc tags/line length and minor style items). These can be fixed by completing the Javadoc param tags and small formatting changes.
- JaCoCo/SpotBugs compatibility: to obtain reliable coverage/FindBugs results either:
  - run the build under an older JDK supported by the tools (e.g., JDK 17), or
  - update JaCoCo/SpotBugs plugins to versions compatible with JDK 25 (may require investigation).

## Proposed minimal next steps (you can ask me to do these)
1. Fix remaining Checkstyle errors (add missing `@param`/`@return` tags and break long lines). — I can apply these fixes now.
2. Decide JaCoCo approach: update plugin versions or run under JDK 17 to get accurate coverage reports.
3. When satisfied, open a PR from `Maintenance` → `main` and bump version.

## Files changed by me
- `src/main/java/iso2/exe3/domain/package-info.java` (added)
- `src/main/java/iso2/exe3/domain/ActivityRecommender.java` (Javadoc and formatting edits)
- removed legacy duplicate `ActivityRecommender` in uppercase path

## Commands and notes for the wiki
- To show the initial vs final reports, keep a copy of `target/site` from the `main` branch before applying fixes, then compare with `target/site` on `Maintenance`. You can zip both `target/site` folders and host them in the wiki or include screenshots.

---
Generated: 2025-12-17
