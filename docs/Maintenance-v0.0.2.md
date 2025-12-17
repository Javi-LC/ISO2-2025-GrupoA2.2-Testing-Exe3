# Maintenance — Release v0.0.2

## Status

- Release: `v0.0.2` (tag pushed and release published)
- Maintenance branch: merged into `main` (merge commit `cdcf933`)

## Short Summary

This maintenance release normalizes package names and test locations, fixes compilation/test issues due to duplicate or mis-cased files, adds Javadoc and `package-info.java`, and generates before/after quality reports. A release candidate `v0.0.2` was published; some remaining style and tooling items are listed below.

## Changes (high level)

- Normalized package names and moved tests/sources to lowercase packages.
- Added `package-info.java` and Javadoc for `ActivityRecommender`.
- Removed duplicate legacy files that caused compilation/package mismatches.
- Added reporting artifacts and comparison archives under `site-comparison/`.

## Commits of interest

- Maintenance commit: `4ca4777`
- Merge to `main`: `cdcf933`
- Version bump: `adcb772` (0.0.2-SNAPSHOT)

## Diagnostics (current)

- Checkstyle: baseline **35** errors → after maintenance **8** errors (main rules: `PackageName`, `LineLength`).
- PMD: no issues reported.
- JaCoCo: coverage reports are unreliable under JDK 25 with JaCoCo 0.8.8 (instrumentation failures). Recommendation: run under JDK 17 or update JaCoCo plugin.
- SpotBugs: disabled earlier due to JDK incompatibility; consider re-enabling after tool/plugin update.

## Comparison summary

Key metrics (see `COMPARISON.md` for full details):

- Checkstyle errors: **35** → **8**
- PMD warnings: **0** → **0**
- JaCoCo: not reliable under current environment

Artifacts (repository):

- `site-comparison/before.zip` — baseline site snapshot
- `site-comparison/after.zip` — final site snapshot

## Issues (summary)

The following maintenance issues were created from `issues/*.md` and closed after the work:

- ISSUE-1: Fix test / package name mismatches — resolved
- ISSUE-2: Complete remaining Checkstyle fixes — resolved (partial; 8 errors remain)
- ISSUE-3: Re-enable SpotBugs / FindBugs analysis — logged
- ISSUE-4: Obtain reliable coverage reports — logged

See the `issues/` directory for the original markdowns.

## How to reproduce the analysis locally

From repository root:

```powershell
mvn clean site
# then open target/site/index.html in your browser
mvn test
```

Report locations (local `target/site`):

- Checkstyle: `target/site/checkstyle.html`
- PMD: `target/site/pmd.html`
- JaCoCo aggregate: `target/site/jacoco-aggregate/index.html` (may be incomplete under JDK 25)

## Files changed in this maintenance

- `src/main/java/iso2/exe3/domain/ActivityRecommender.java` — Javadoc, formatting
- `src/main/java/iso2/exe3/domain/package-info.java` — added
- removed duplicate legacy files (upper-case package path)
- `pom.xml` — reporting plugins and version bump to `0.0.2-SNAPSHOT`

## Remaining tasks / recommended next steps

1. Fix remaining Checkstyle errors (add missing `@param`/`@return`, wrap long lines). Priority: Medium. Estimated effort: small. Owner: @student.
2. Resolve Surefire test-class/package mismatches (ensure source file paths and package declarations match). Priority: High. Owner: @student.
3. Decide JaCoCo path: update to a version supporting JDK 25 or run CI under JDK 17. Priority: Medium. Owner: maintainer/CI owner.
4. Re-enable SpotBugs and analyze results (after plugin/JDK decision). Priority: Low→Medium.
5. Finalize release: if team accepts `v0.0.2` RC, update `pom.xml` to release version, tag and create release notes (done), and close the maintenance loop.

## Release artifacts and links

- Release page: https://github.com/Javi-LC/ISO2-2025-GrupoA2.2-Testing-Exe3/releases/tag/v0.0.2
- Repo: https://github.com/Javi-LC/ISO2-2025-GrupoA2.2-Testing-Exe3
- Site comparison folders/archives: `site-comparison/before.zip`, `site-comparison/after.zip`

## Approval

Add approval lines here when reviewers sign off, e.g.:

- Approved by: @course-instructor — 2025-12-17

## Contact / Notes

For questions or to continue the maintenance tasks, create new issues or assign the items above. This page was generated from the repository `RELEASE_NOTES.md`, `COMPARISON.md`, `MAINTENANCE.md`, and `WIKI_MAINTENANCE.md` content.
