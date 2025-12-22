# Maintenance Wiki Page

## Diagnostic Summary

- Checkstyle: baseline reported 35 errors; after maintenance this decreased to 8 (mainly `PackageName` and `LineLength`). See `site-comparison/before` and `site-comparison/after`.
- PMD: no problems reported.
- JaCoCo: coverage reports are unreliable under JDK 25 with JaCoCo 0.8.8 (instrumentation errors). Recommend running under JDK 17 or updating JaCoCo.
- SpotBugs: not enabled (removed due to JDK incompatibility); consider re-enabling with a compatible plugin version or different JDK.

## Prioritization of Changes

Priority criteria used: severity (compilation/test blockers > style > documentation), impact (runtime/test stability), and effort.

Top prioritized tasks:

1. Fix test/package name mismatches (High) — tests failing under Surefire due to path/package casing.
2. Fix remaining Checkstyle errors (Medium) — Javadoc tags and line length; low risk, low effort.
3. Restore reliable coverage (Medium) — update JaCoCo or run under JDK 17.
4. Re-enable SpotBugs/FindBugs and analyze results (Low → Medium) — may reveal deeper defects.

## Impact Analysis

- Fixing package/name mismatches will allow CI to run tests reliably and produce accurate test results.
- Completing Checkstyle fixes improves code readability and meets course requirements with minimal functional impact.
- Updating JaCoCo or JDK affects CI environment; needs coordination (CI matrix update or plugin bump).

## Assigned Tasks (issues)

- ISSUE-1: Fix test/package name mismatches 
- ISSUE-2: Complete remaining Checkstyle 
- ISSUE-3: Re-enable SpotBugs or update 
- ISSUE-4: Update JaCoCo or run under JDK 17 for reliable coverage


## Execution record

- Created branch `Maintenance`, implemented: package normalization, `package-info.java`, Javadoc for `ActivityRecommender`, removed duplicate files, merged fixes into `main`.
- Merge commit: `cdcf933` (merge Maintenance into main)
- Maintenance commit (fixes/doc): `4ca4777`
- Version bumped to `0.0.2-SNAPSHOT` and pushed to `main` (commit `adcb772`)
- Generated sites and added comparison archives under `site-comparison/`