# Mobile lab catch-up

Checked against the current Tutors notes on 12 October 2026. Your local work is ahead of the GitHub branch and is the starting point for these changes.

## Where you need to be

The published schedule puts 12-18 October in teaching Week 6, Interfaces. Assignment 1 is due 16 October at 23:59 and asks for an original app comparable in complexity to the material through Topic 5, Adapters. The lab project is the learning foundation for that original assignment app.

| Checkpoint | Expected behaviour | Local position |
| --- | --- | --- |
| Topic 2: Kotlin Fundamentals / Lab 1 | Console CRUD and ID search, domain model, memory store, UML, Git workflow | Tested and published through merged PR #2. UML saved; your own code walkthrough remains. |
| Topic 3: Android introduction | Android project/environment and understanding the platform | Android debug APK builds and launches on the installed API 37 emulator. |
| Topic 4: Activities | Main list and add/edit screens, shared data, validated coordinates and Android CRUD | Implemented and emulator-tested: CRUD, title/coordinate validation and cancellation. Issue #3 checkpoint ready for publishing. |
| Topic 5: Adapters | Main menu, dedicated list screen, RecyclerView/adapter and XML layouts | Not implemented yet. This is the core lab target for Assignment 1 preparation. |
| Topic 6: Interfaces | Item listener, tap to edit, confirmed long-press delete, Timber logging | Not implemented yet. This is the current teaching-week target. |

## Run Lab 1 in VS Code

Open this project folder. Use **Terminal > Run Build Task** (Cmd+Shift+B on this Mac) to run `Placemark: run Lab 1 console`.

Or, in the project terminal:

```bash
bash scripts/run-console.sh
```

The runner compiles the four console/model files with the Kotlin compiler and Java runtime already bundled with Android Studio. It does not install tools or turn this console into an Android launcher. Generated output stays in `.lab-build/`, which is ignored by Git.

On another machine, set `PLACEMARK_JAVA` to the Java executable and `PLACEMARK_KOTLIN_HOME` to the installed compiler folder.

Verified on 12 October: compilation, the complete add/list/search/update/delete flow, invalid menu input, blank-title rejection, invalid/missing IDs, cancelled updates, unique IDs after deletion and clean exit on end-of-input. This verifies the console checkpoint; the Android build and emulator have not been verified in this step.

The store was also checked for updating coordinates, retaining the existing record reference, handling missing IDs and issuing a new ID after deletion.

## UML draft for Lab 1

See [the saved UML draft](lab-1-uml.md). The diagram reflects the actual three data-layer types, including coordinate fields.

## Your walkthrough

1. `main()` repeats the menu and delegates each operation. `menu()` converts input to an integer and exits cleanly when the input stream ends.
2. `PlacemarkModel` holds one record. `val` makes a reference read-only; `var` allows its value to change. A data class supplies operations such as `copy()`.
3. `PlacemarkStore` defines the operations a store must provide. `PlacemarkMemStore` implements them with an in-memory list and generated IDs.
4. Add rejects a blank title. List handles the empty-store case. Search uses the record ID.
5. The console copies the selected record with the new title/description, retaining its ID and coordinates. The memory store updates the existing object's fields, following the lab's store example. Invalid input or an unknown ID returns to the menu without changing data.
6. Delete removes the requested record. Data disappears when the program ends because this checkpoint has no file persistence.

Run add, list, search, update and delete yourself. Also try a blank title, a non-numeric menu choice and an ID that does not exist. Be ready to explain null safety, `toLongOrNull()`, `copy()`, the store interface and the generated ID.

## Source mismatches to understand

The Lab 1 UML/runner uses `PlacemarkModel` and `description`, but some snippets use `PlacedMark` and `desc`. This project consistently uses `PlacemarkModel` and `description` so the examples compile together. The model includes the lab snippet's `x`/`y` fields with defaults of zero. As in the lab runner, the console only asks for title/description; coordinate entry is part of the later Android form.

The menu and CRUD flow follow the lab example. Two small input-handling differences remain: end-of-input exits rather than looping indefinitely, and malformed update IDs get an explicit error. A console update retains existing coordinates instead of resetting them to their defaults. The later Activities notes use another store name, `PlacedMarkList`; those names will also need to stay consistent when adding the screens. The Adapters notes mix some layout/control IDs, so IDs must match the actual XML.

VS Code can edit the Kotlin/XML and run this console. The Adapters lab explicitly teaches Android Studio's visual Layout Editor, and Android device/emulator checks still need the Android tooling. Use Android Studio for those lab steps even when VS Code is your main code editor.

## Remaining handoffs

- Review and understand the Lab 1 code before moving to the Android screens.
- Lab 1 was committed on its Issue #1 branch, pushed and merged in [PR #2](https://github.com/Peterk04/placemark-android/pull/2). Each next checkpoint gets a new issue/branch and is committed/pushed only after its checks pass.
- Activities uses `codex/issue-3-activities`, linked to [Issue #3](https://github.com/Peterk04/placemark-android/issues/3). Its emulator report has four passing tests, including three behaviour checks and the generated app-context check.
- Build Topic 5 RecyclerView and layouts next, then Topic 6 listener/logging refactor.
- For Assignment 1, choose your own original app idea, rewrite written sections, review all code and record your demo. Finishing the Placemark lab does not by itself satisfy the original-app assignment.

Agent assistance in this checkpoint covered lab comparison, completing console functions, correcting mixed example names, VS Code run support and verification. Use this record when preparing the module's required AI-use disclosure.

## Activities walkthrough

- `AppData` owns a shared `PlacemarkMemStore` so both Android screens use the same records.
- `MainActivity` builds the first UI in Kotlin, as the Activities lab does, and refreshes its list in `onResume()` after returning from the form.
- `AddEditActivity` reads an optional mark ID from its Intent. Without one it creates a mark; with one it loads and updates that record.
- A blank title or invalid/non-finite coordinate keeps the form open without saving. Cancel returns without changing the store. Negative decimal coordinates are accepted.
- These Android screens still use memory only. They do not provide JSON/file persistence or the original Assignment 1 app.
- The project's generated Espresso/JUnit Android test versions were updated to 3.7.0/1.3.0 because the older Espresso version could not inject input on API 37. The app's test checks now execute on that emulator.

Be ready to explain `onCreate`, `onResume`, Intent extras, the singleton store, view IDs and why validation runs before create/update. Compare the programme-generated views with the XML views in the next lab.

## Tutors references

- [Course](https://tutors.dev/course/mobile-app-dev-1-2026-setu)
- [Lab 1: Kotlin Fundamentals](https://tutors.dev/note/mobile-app-dev-1-2026-setu/topic-02-kotlin-recap/unit-01/note-01-placemarker)
- [Activities CRUD lab](https://tutors.dev/note/mobile-app-dev-1-2026-setu/topic-04-activities/unit-01/note-01-lab-activities)
- [Adapters / RecyclerView lab](https://tutors.dev/note/mobile-app-dev-1-2026-setu/topic-05-adapters/unit-01/note-1-adapters)
- [Interfaces / logging lab](https://tutors.dev/note/mobile-app-dev-1-2026-setu/topic-06-interfaces/unit-01/note-01-interfaces)
- [Assignment 1 rubric and schedule](https://tutors.dev/talk/mobile-app-dev-1-2026-setu/topic-00-assignments/unit-1/talk-assignment-1)
