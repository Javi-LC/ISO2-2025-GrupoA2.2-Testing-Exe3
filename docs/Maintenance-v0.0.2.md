# Maintenance — Release v0.0.2

## Resumen

Release candidate produced after maintenance activities. Key goals: normalize package names, fix test/package mismatches, add Javadoc and package-info, remove duplicate legacy files, and generate reporting/site artifacts for comparison.

## Acciones realizadas

From `RELEASE_NOTES.md` — Changes:

- Normalized package names and moved tests/sources to lowercase packages.
- Added `package-info.java` and Javadoc for `ActivityRecommender`.
- Removed duplicate legacy files that caused compilation/package mismatches.
- Added reporting artifacts and comparison archives.

## Diagnóstico (resumen)

From `WIKI_MAINTENANCE.md` / `MAINTENANCE.md`:

- Checkstyle: baseline reported 35 errors → after maintenance **8** (mainly `PackageName` and `LineLength`).
- PMD: no problems reported.
- JaCoCo: coverage reports are unreliable under JDK 25 with JaCoCo 0.8.8 (instrumentation errors). Recommend running under JDK 17 or updating JaCoCo.
- SpotBugs: not enabled due to JDK incompatibility; consider re-enabling with a compatible plugin or different JDK.

## Comparativa (key metrics)

From `COMPARISON.md`:

- Checkstyle errors: **35** (baseline) → **8** (after)
- PMD warnings: **0** → **0**
- JaCoCo coverage: not reliable (instrumentation failed on JDK 25 system classes)

Artifacts:

- Baseline site (zipped): `site-comparison/before.zip`
- Final site (zipped): `site-comparison/after.zip`

## Commits / Timeline

- Maintenance commit: `4ca4777`
- Merge to main: `cdcf933`
- Version bump: `adcb772` (0.0.2-SNAPSHOT)

## Issues (created and closed during maintenance)

- ISSUE-1: Fix test / package name mismatches — created & closed
- ISSUE-2: Complete remaining Checkstyle fixes — created & closed
- ISSUE-3: Re-enable SpotBugs / FindBugs analysis — created & closed
- ISSUE-4: Obtain reliable coverage reports — created & closed

See the `issues/` folder for the original markdowns.

## Artefactos y enlaces

- Release: https://github.com/Javi-LC/ISO2-2025-GrupoA2.2-Testing-Exe3/releases/tag/v0.0.2
- Repository: https://github.com/Javi-LC/ISO2-2025-GrupoA2.2-Testing-Exe3
- Site comparisons: `site-comparison/before.zip`, `site-comparison/after.zip`

## Cómo reproducir

- `mvn clean site`
- `mvn test`

## Próximos pasos / backlog

1. Fix remaining Checkstyle errors (add missing `@param`/`@return` and fix line length).
2. Decide JaCoCo approach: update plugin or run under JDK 17 to get reliable coverage.
3. Re-enable SpotBugs / run static analysis under a compatible toolchain.
4. Verify `mvn test` on `main` (fix test-class/package casing mismatch reported by Surefire).

## Notas finales

This page was generated from repository artifacts and maintenance notes. If you prefer this as a GitHub Wiki page instead of a docs file, enable the repository wiki, then I can push the same content to the wiki repo.
