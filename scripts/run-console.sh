#!/usr/bin/env bash
set -euo pipefail

case "${1:-}" in
    ""|--compile-only) ;;
    *) printf 'Usage: bash scripts/run-console.sh [--compile-only]\n' >&2; exit 2 ;;
esac

task_project_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/.." && pwd)"
task_java="${PLACEMARK_JAVA:-/Applications/Android Studio.app/Contents/jbr/Contents/Home/bin/java}"
task_kotlin_home="${PLACEMARK_KOTLIN_HOME:-/Applications/Android Studio.app/Contents/plugins/Kotlin/kotlinc}"
task_output_dir="$task_project_dir/.lab-build"
task_jar="$task_output_dir/placemark-console.jar"

if [[ ! -x "$task_java" || ! -f "$task_kotlin_home/lib/kotlin-compiler.jar" ]]; then
    printf 'Java or Kotlin compiler not found. Set PLACEMARK_JAVA and PLACEMARK_KOTLIN_HOME to your installed tools.\n' >&2
    exit 1
fi

task_sources=(
    "$task_project_dir/app/src/main/java/org/setu/placemark/main/Main.kt"
    "$task_project_dir/app/src/main/java/org/setu/placemark/models/PlacemarkModel.kt"
    "$task_project_dir/app/src/main/java/org/setu/placemark/models/PlacemarkStore.kt"
    "$task_project_dir/app/src/main/java/org/setu/placemark/models/PlacemarkMemStore.kt"
)

mkdir -p "$task_output_dir"
"$task_java" -cp "$task_kotlin_home/lib/*" org.jetbrains.kotlin.cli.jvm.K2JVMCompiler \
    -kotlin-home "$task_kotlin_home" -jvm-target 11 \
    "${task_sources[@]}" -include-runtime -d "$task_jar"

if [[ "${1:-}" == --compile-only ]]; then
    printf 'Console compiled successfully.\n'
else
    exec "$task_java" -jar "$task_jar"
fi
