# Development and Git plan

Prepared 14 October 2026. Assignment deadline: Friday 16 October 2026; verify the exact Moodle cut-off. This plan records intended work, not completed features.

## Current scope and starting point

The clean main baseline was 7a6ef27. The new setup commit copies only the ten generated Gradle files from e5bcaf8. Kotlin lab classes remain blank. The setup build passed using Android Studio's bundled JDK 25 and Gradle 9.6.0. There is no launcher Activity or working CRUD app yet.

First rebuild the Kotlin, Activities, Adapters and Interfaces labs with Peter implementing and explaining each checkpoint. Then adapt the baseline to a curated five-a-side pitch finder with Google Maps, search and filters. No Google Places discovery, bookings, payments or authentication. Persistence is excluded from the current scope at Peter's request: records will be in memory and can be lost on process termination. Assess the persistence rubric column honestly; high performance elsewhere does not demonstrate JSON storage.

Check Google Cloud access, billing, Maps SDK and restricted key configuration early to uncover setup blockers. Integrate Maps after the pitch model is ready. Keep credentials and local SDK paths out of commits.

## Branching scheme

Use a small issue-based workflow: main contains completed checkpoints; one short-lived branch per issue. Start each branch from current main. Branch names are listed below. New tasks use codex/<issue-number>-<short-topic>; focused defects can use codex/<issue-number>-fix-<topic>.

1. Open or select an issue and assign it to Peterk04.
2. Update main with git pull --ff-only, then create the issue branch.
3. Implement one coherent change, run the relevant check, review git diff and commit with the issue number.
4. Push each useful checkpoint. A draft PR can collect progress while work remains.
5. Finish the issue's acceptance checklist and record the actual checks in the PR.
6. Use Closes #<issue> in the PR body. Merge with a merge commit so individual development commits remain visible. Do not squash this project workflow.
7. Pull merged main, create its annotated milestone tag, then start the next branch. Never rewrite a published tag.

No develop branch is needed for this single-developer assignment. Do not merge historical feature branches from before the reset into new main. Keep historical closed issues intact; the new rebuild issues describe current work.

## Commit scheme

Format: type: concrete change (#issue). Use feat for behavior, fix for an actual defect, refactor for a structural change with the same behavior, test for meaningful test additions, docs for documentation, build for build configuration, and chore for maintenance.

The checkpoints below are suggestions, not a quota. A rich history should show real decisions and working progression. Do not backdate, create empty commits, invent defects, or split one trivial edit just to increase the count. Combine closely coupled changes when that makes the commit easier to review. Change the proposed message to describe what was actually implemented. It is fine to commit a partial feature on its branch if it compiles and the commit clearly describes its limits; main should remain a verified checkpoint.

Before committing: check git status; review git diff; stage specific files; run checks appropriate to the change; include honest results. Verify no build output, local.properties, API keys or signing secrets are staged. Document AI assistance as it occurs and record Peter's own explanation/verification separately.

## Issue and release roadmap

| Issue | Work | Branch | Annotated tag after completion |
| --- | --- | --- | --- |
| [#9](https://github.com/Peterk04/placemark-android/issues/9) | Setup: prepare a buildable starter and Git workflow | `codex/9-setup-baseline` | `v0.1.0-setup` |
| [#10](https://github.com/Peterk04/placemark-android/issues/10) | Rebuild Lab 1: Kotlin model, memory store and console CRUD | `codex/10-kotlin-foundation` | `v0.2.0-kotlin` |
| [#11](https://github.com/Peterk04/placemark-android/issues/11) | Rebuild Activities lab: Android CRUD screens | `codex/11-activities` | `v0.3.0-activities` |
| [#12](https://github.com/Peterk04/placemark-android/issues/12) | Rebuild Adapters lab: XML layouts and RecyclerView | `codex/12-adapters` | `v0.4.0-adapters` |
| [#13](https://github.com/Peterk04/placemark-android/issues/13) | Rebuild Interfaces lab: row listeners and logging | `codex/13-interfaces` | `v0.5.0-labs` |
| [#14](https://github.com/Peterk04/placemark-android/issues/14) | Pitch finder: adapt the lab baseline to curated pitches | `codex/14-pitch-domain` | `v0.6.0-pitch-crud` |
| [#15](https://github.com/Peterk04/placemark-android/issues/15) | Pitch finder: integrate Google Maps and pitch locations | `codex/15-google-maps` | `v0.7.0-maps` |
| [#16](https://github.com/Peterk04/placemark-android/issues/16) | Pitch finder: search, filters and navigation polish | `codex/16-search-filters` | `v0.8.0-feature-complete` |
| [#17](https://github.com/Peterk04/placemark-android/issues/17) | Assignment 1: verify the app and prepare submission evidence | `codex/17-submission` | `v1.0.0-assignment1` |

Tags describe tested milestones on main; they do not imply every assignment requirement has been met. v0.5.0-labs is the practice baseline; v1.0.0-assignment1 is created only when the final deliverable passes its actual checks. For a later submission defect, use a new patch tag such as v1.0.1-assignment1; do not move v1.0.0.

Create a GitHub release from each milestone tag with a short description of what now works, the validation performed and known limits. For example, the setup release is a buildable shell with blank lab classes, not a usable app. Tag only the milestone just completed; future tags stay planned.

## Concrete checkpoints per issue

### #9: Setup: prepare a buildable starter and Git workflow

Branch: `codex/9-setup-baseline`. Finish the previous stage before integrating this stage.

- `build: set up Android Studio starter (#9)`
- `docs: plan issues, branches and release tags (#9)`

Completion evidence:

- [ ] Bring the Gradle-only setup from e5bcaf8 into a new commit based on 7a6ef27.
- [ ] Verify assembleDebug succeeds using the Android Studio JDK.
- [ ] Document issue branches, meaningful commit checkpoints, PR merges and annotated tags.
- [ ] Record the setup assistance in an AI usage log.

### #10: Rebuild Lab 1: Kotlin model, memory store and console CRUD

Branch: `codex/10-kotlin-foundation`. Finish the previous stage before integrating this stage.

- `docs: add Placemark model and store UML (#10)`
- `feat: define Placemark model and store interface (#10)`
- `feat: implement memory store CRUD and stable IDs (#10)`
- `feat: add console menu and input validation (#10)`
- `test: verify store updates, deletions and missing IDs (#10)`

Completion evidence:

- [ ] Draw and save the model/store UML diagram.
- [ ] Use consistent PlacemarkModel and description names across the lab examples.
- [ ] Implement the model and store contract, ArrayList storage and AtomicLong IDs.
- [ ] Implement console add, list, update, delete, find by ID and safe input handling.
- [ ] Run all console operations, including blank titles, malformed IDs and missing records; explain val/var, nullable results and ID generation.

### #11: Rebuild Activities lab: Android CRUD screens

Branch: `codex/11-activities`. Finish the previous stage before integrating this stage.

- `feat: add shared store and launcher Activity (#11)`
- `feat: implement add and edit Activity with validation (#11)`
- `feat: implement list and delete actions (#11)`
- `fix: refresh records after navigation and handle missing IDs (#11)`

Completion evidence:

- [ ] Use a shared AppData memory store.
- [ ] Create and register launcher/menu, list and add/edit Activities following Topic 4.
- [ ] Pass record IDs through Intents and refresh current data onResume.
- [ ] Validate input and preserve cancellation behavior.
- [ ] Run add/edit/delete/cancel and Back on an emulator; explain Intents and the shared store.

### #12: Rebuild Adapters lab: XML layouts and RecyclerView

Branch: `codex/12-adapters`. Finish the previous stage before integrating this stage.

- `feat: add menu and form XML layouts (#12)`
- `feat: add RecyclerView row and ViewHolder adapter (#12)`
- `feat: connect row actions and list refresh (#12)`
- `fix: handle empty lists and navigation consistently (#12)`

Completion evidence:

- [ ] Create XML for menu, list, add/edit and the reusable row.
- [ ] Add RecyclerView and its ViewHolder/adapter.
- [ ] Connect row callbacks to the correct record IDs.
- [ ] Refresh the adapter after add/edit/delete and show a useful empty list.
- [ ] Demonstrate a long list and correct row actions; explain inflation, binding and callbacks.

### #13: Rebuild Interfaces lab: row listeners and logging

Branch: `codex/13-interfaces`. Finish the previous stage before integrating this stage.

- `refactor: route row actions through listener interface (#13)`
- `feat: confirm deletion from long press (#13)`
- `chore: add Timber logging for record actions (#13)`
- `fix: complete toolbar and Back navigation (#13)`

Completion evidence:

- [ ] Introduce the course row-listener interface.
- [ ] Use tap to edit and long press to request deletion.
- [ ] Confirm deletion with a cancel option.
- [ ] Add Timber logging following Topic 6.
- [ ] Check correct IDs, confirmation/cancellation and toolbar navigation; explain the interface callback.

### #14: Pitch finder: adapt the lab baseline to curated pitches

Branch: `codex/14-pitch-domain`. Finish the previous stage before integrating this stage.

- `refactor: adapt Placemark model and UI to pitches (#14)`
- `feat: add pitch fields and form controls (#14)`
- `feat: validate pitch details and coordinates (#14)`
- `feat: add curated pitch examples and empty states (#14)`

Completion evidence:

- [ ] Replace Placemark wording and model usage with the pitch domain.
- [ ] Add name, area/address, location and appropriate facility choices.
- [ ] Validate required text and finite latitude/longitude ranges.
- [ ] Add clearly sourced curated examples or explicitly labelled demo examples; preserve unknown facilities.
- [ ] Show full in-memory pitch CRUD with list/detail/form screens; explain model decisions.

### #15: Pitch finder: integrate Google Maps and pitch locations

Branch: `codex/15-google-maps`. Finish the previous stage before integrating this stage.

- `build: configure Google Maps SDK and local key setup (#15)`
- `feat: show map markers for curated pitches (#15)`
- `feat: open pitch details from markers (#15)`
- `feat: select pitch locations and refresh markers (#15)`

Completion evidence:

- [ ] Configure Maps SDK, ignored local API-key configuration and appropriate key restrictions.
- [ ] Verify actual map tiles and one marker before expanding the UI.
- [ ] Plot curated pitches with stable IDs in marker tags and open the correct record.
- [ ] Add location selection with cancel/save behavior and refresh markers after edits/deletions.
- [ ] Test multiple pitches and invalid/missing locations; document rebuild configuration without publishing secrets.

### #16: Pitch finder: search, filters and navigation polish

Branch: `codex/16-search-filters`. Finish the previous stage before integrating this stage.

- `feat: search pitches by name and area (#16)`
- `feat: filter pitches by facilities and surface (#16)`
- `feat: share filtered results between list and map (#16)`
- `fix: polish navigation, accessibility and empty states (#16)`
- `test: cover search and combined filter behavior (#16)`

Completion evidence:

- [ ] Search pitch names/areas without changing stored records.
- [ ] Add useful facility/surface filtering and a clear/reset action.
- [ ] Use the same visible results in list and map.
- [ ] Finish Back/Up navigation, clear empty states and accessible controls.
- [ ] Test combined filters, no results and clearing filters; add meaningful model/query tests.

### #17: Assignment 1: verify the app and prepare submission evidence

Branch: `codex/17-submission`. Finish the previous stage before integrating this stage.

- `test: record final build and emulator verification (#17)`
- `docs: document app setup, references and limitations (#17)`
- `docs: complete assessment and submission evidence (#17)`

Completion evidence:

- [ ] Run required unit checks, lint/build and emulator acceptance scenarios; fix genuine failures in focused commits.
- [ ] Write README setup/features/limitations, source references and honest AI disclosure.
- [ ] Complete the self-assessment only with demonstrated evidence.
- [ ] Record and check the demo video, links and submission archive.
- [ ] Tag the final tested commit and verify Moodle's exact cut-off; persistence is excluded from the current scope and its rubric tradeoff must be assessed honestly.

## Commands for the next lab

After setup is merged, Peter starts Lab 1 on its own branch:

```bash
cd /Users/peter/AndroidStudioProjects/Placemark
git switch main
git pull --ff-only
git switch -c codex/10-kotlin-foundation
```

After the model/store checkpoint is implemented and checked:

```bash
git diff
git add app/src/main/java/org/setu/placemark/models/PlacemarkModel.kt app/src/main/java/org/setu/placemark/models/PlacemarkStore.kt
git commit -m "feat: define Placemark model and store interface (#10)"
git push -u origin codex/10-kotlin-foundation
```

Stage only files actually completed. Do not execute the example commit before making the changes. Later pushes on the same branch use git push. PR body: describe behavior, list actual checks and add Closes #10.

Example milestone commands after the Lab 1 PR is merged and verified:

```bash
git switch main
git pull --ff-only
git tag -a v0.2.0-kotlin -m "Kotlin model, memory store and console CRUD verified"
git push origin v0.2.0-kotlin
```

## Verification record

- Setup: :app:assembleDebug --offline --no-daemon passed with 34 executed tasks on 14 October 2026, using Android Studio's bundled JDK. This checks compilation and packaging only. No emulator UI or CRUD verification has been performed for the rebuilt project.
- All lab and app evidence remains pending. Record results in issue checklists and PRs as work is completed.

