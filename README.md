# Placemark lab rebuild → five-a-side pitch finder

This repository currently contains the initial Kotlin placeholders and a verified Android Gradle project shell. The setup files were recovered from the Gradle-only checkpoint e5bcaf8 and committed anew after the reset to 7a6ef27. The later lab implementation is being rebuilt by Peter.

## Build the starter

Open /Users/peter/AndroidStudioProjects/Placemark in Android Studio and sync Gradle. Use the bundled JDK 25; the project uses AGP 9.4.1, Gradle 9.6.0 and Android API 37. Set your local SDK path through Android Studio/local.properties; that file is ignored.

From the project root, on this Mac:

```bash
JAVA_HOME='/Applications/Android Studio.app/Contents/jbr/Contents/Home' ./gradlew :app:assembleDebug
```

The setup build passed. The starter currently has no launcher Activity; this build is not evidence of a functioning Android user interface.

## Development

See [DEVELOPMENT_PLAN.md](DEVELOPMENT_PLAN.md) for issues, branches, commit checkpoints and planned annotated releases. Start with [Lab 1 issue #10](https://github.com/Peterk04/placemark-android/issues/10), then Activities, Adapters and Interfaces. The final app will use a curated in-memory pitch collection and Google Maps. Features, tests and later tags are planned and unimplemented.

## References and assistance

- [Module assignment](https://tutors.dev/talk/mobile-app-dev-1-2026-setu/topic-00-assignments/unit-1/talk-assignment-1)
- [Kotlin lab](https://tutors.dev/note/mobile-app-dev-1-2026-setu/topic-02-kotlin-recap/unit-01/note-01-placemarker)
- [Activities lab](https://tutors.dev/note/mobile-app-dev-1-2026-setu/topic-04-activities/unit-01/note-01-lab-activities)
- [Adapters lab](https://tutors.dev/note/mobile-app-dev-1-2026-setu/topic-05-adapters/unit-01/note-1-adapters)
- [Interfaces lab](https://tutors.dev/note/mobile-app-dev-1-2026-setu/topic-06-interfaces/unit-01/note-01-interfaces)

AI setup/planning assistance is recorded in [AI_USAGE_LOG.md](AI_USAGE_LOG.md). Peter's own understanding and verification must be recorded as they occur.

