# Project plan

Start with the labs, then turn the working Placemark app into the five-a-side pitch finder. The current project is a buildable starter; the Kotlin lab classes are still blank. Persistence is outside the current scope, so records can be lost when the app process closes. Reflect that honestly in the rubric.

## How we will use Git

- One issue and branch for each lab or feature. Each issue is assigned to Peter.
- Start the next branch from updated main. Main holds finished, checked work.
- Make small commits when a useful piece works. Push after each checkpoint.
- Use messages such as `feat: add console menu (#10)` so the issue and change are clear.
- Review the diff and check the behavior before committing. Keep keys and generated files out of Git.
- Open a PR, list the checks actually done and add `Closes #<number>`.
- Merge with a merge commit to keep the individual commits visible.
- Add the milestone tag after merging and checking the result. Give the GitHub release a simple name.
- The commit ideas below can change. There is no commit-count target: the history should show real work. Do not invent fixes or backdate commits.
- Record AI help and Peter's own verification in AI_USAGE_LOG.md as they happen.

## Issues, branches and tags

| Issue | Task | Branch | Tag when finished |
| --- | --- | --- | --- |
| [#9](https://github.com/Peterk04/placemark-android/issues/9) | Project setup | `codex/9-setup-baseline` | `v0.1.0-setup` |
| [#10](https://github.com/Peterk04/placemark-android/issues/10) | Lab 1: Kotlin basics | `codex/10-kotlin-foundation` | `v0.2.0-kotlin` |
| [#11](https://github.com/Peterk04/placemark-android/issues/11) | Lab 2: Activities | `codex/11-activities` | `v0.3.0-activities` |
| [#12](https://github.com/Peterk04/placemark-android/issues/12) | Lab 3: RecyclerView and layouts | `codex/12-adapters` | `v0.4.0-adapters` |
| [#13](https://github.com/Peterk04/placemark-android/issues/13) | Lab 4: Interfaces | `codex/13-interfaces` | `v0.5.0-labs` |
| [#14](https://github.com/Peterk04/placemark-android/issues/14) | Turn Placemark into a pitch finder | `codex/14-pitch-domain` | `v0.6.0-pitch-crud` |
| [#15](https://github.com/Peterk04/placemark-android/issues/15) | Add Google Maps | `codex/15-google-maps` | `v0.7.0-maps` |
| [#16](https://github.com/Peterk04/placemark-android/issues/16) | Search and filter pitches | `codex/16-search-filters` | `v0.8.0-feature-complete` |
| [#17](https://github.com/Peterk04/placemark-android/issues/17) | Final checks and submission | `codex/17-submission` | `v1.0.0-assignment1` |

Setup is finished. All other stages are planned. Check Maps/Google Cloud access early while working through the labs, then integrate Maps once the pitch model is ready.

## Commit ideas

### #9 — Project setup

- `build: set up Android Studio starter (#9)`
- `docs: plan issues, branches and release tags (#9)`

### #10 — Lab 1: Kotlin basics

- `docs: add Placemark model and store UML (#10)`
- `feat: define Placemark model and store interface (#10)`
- `feat: implement memory store CRUD and stable IDs (#10)`
- `feat: add console menu and input validation (#10)`
- `test: verify store updates, deletions and missing IDs (#10)`

### #11 — Lab 2: Activities

- `feat: add shared store and launcher Activity (#11)`
- `feat: implement add and edit Activity with validation (#11)`
- `feat: implement list and delete actions (#11)`
- `fix: refresh records after navigation and handle missing IDs (#11)`

### #12 — Lab 3: RecyclerView and layouts

- `feat: add menu and form XML layouts (#12)`
- `feat: add RecyclerView row and ViewHolder adapter (#12)`
- `feat: connect row actions and list refresh (#12)`
- `fix: handle empty lists and navigation consistently (#12)`

### #13 — Lab 4: Interfaces

- `refactor: route row actions through listener interface (#13)`
- `feat: confirm deletion from long press (#13)`
- `chore: add Timber logging for record actions (#13)`
- `fix: complete toolbar and Back navigation (#13)`

### #14 — Turn Placemark into a pitch finder

- `refactor: adapt Placemark model and UI to pitches (#14)`
- `feat: add pitch fields and form controls (#14)`
- `feat: validate pitch details and coordinates (#14)`
- `feat: add curated pitch examples and empty states (#14)`

### #15 — Add Google Maps

- `build: configure Google Maps SDK and local key setup (#15)`
- `feat: show map markers for curated pitches (#15)`
- `feat: open pitch details from markers (#15)`
- `feat: select pitch locations and refresh markers (#15)`

### #16 — Search and filter pitches

- `feat: search pitches by name and area (#16)`
- `feat: filter pitches by facilities and surface (#16)`
- `feat: share filtered results between list and map (#16)`
- `fix: polish navigation, accessibility and empty states (#16)`
- `test: cover search and combined filter behavior (#16)`

### #17 — Final checks and submission

- `test: record final build and emulator verification (#17)`
- `docs: document app setup, references and limitations (#17)`
- `docs: complete assessment and submission evidence (#17)`

## Release scheme

The table lists annotated tags for the main milestones. Publish a short GitHub release saying what works, what was checked and what is still missing. Lab tags make it easy to revisit a teaching checkpoint; v0.5.0-labs is the completed lab base. Only publish v1.0.0-assignment1 after the final checks and submission material are ready.

Never move a published tag. If a later fix changes the final submission, create a new patch tag such as v1.0.1-assignment1. Future tags stay uncreated until their work is complete.

## Start Lab 1

In Android Studio's Terminal:

```bash
cd /Users/peter/AndroidStudioProjects/Placemark
git switch main
git pull --ff-only
git switch -c codex/10-kotlin-foundation
```

After writing and checking the model/store contract, for example:

```bash
git diff
git add app/src/main/java/org/setu/placemark/models/PlacemarkModel.kt app/src/main/java/org/setu/placemark/models/PlacemarkStore.kt
git commit -m "feat: define Placemark model and store interface (#10)"
git push -u origin codex/10-kotlin-foundation
```

Run those commit commands only when the files are actually implemented. Later pushes on the branch use git push.

After the Lab 1 PR is merged and checked:

```bash
git switch main
git pull --ff-only
git tag -a v0.2.0-kotlin -m "Lab 1: Kotlin model, store and console CRUD checked"
git push origin v0.2.0-kotlin
```

## Checks so far

- Setup: :app:assembleDebug --offline --no-daemon passed on 14 October 2026 using Android Studio's bundled JDK 25; 34 tasks executed. The starter has no launcher Activity yet.
- No rebuilt console or emulator behavior has been checked. Add real results to each issue/PR as the work is done.
- Deadline confirmed by Peter: Friday 16 October 2026. Check Moodle for the exact cut-off.

