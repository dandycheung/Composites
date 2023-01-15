﻿[![Maven Central](https://img.shields.io/maven-central/v/ru.ldralighieri.composites/composites.svg)](https://search.maven.org/search?q=g:ru.ldralighieri.composites)
[![Kotlin Version](https://img.shields.io/badge/Kotlin-v1.8.0-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose BOM Version](https://img.shields.io/badge/Compose-v2023.01.00-blue.svg?logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

# Composites (work in progress 🚧🔧️👷⛏🚧)

Composites are a group of tools and handy libraries that make it easier to use [Jetpack Compose][compose].  
Please consider giving this repository a star ⭐ if you like the project.


## Modules
* [composites-fiberglass] &mdash; A tool for building complex screens based on simple blocks.


## Using in your projects

Add one or more dependencies:

```kotlin
dependencies {
    implementation("ru.ldralighieri.composites:composites-fiberglass:0.1.0")
}
```

Make sure that you have `mavenCentral()` in the list of repositories:

```kotlin
repositories {
    mavenCentral()
}
```

Snapshot build:
```kotlin
repositories {
    maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots/") }
}

dependencies {
   implementation("ru.ldralighieri.composites:{module}:0.2.0-SNAPSHOT")
}
```


## If you're finding performance issues

Make sure to try running your app or sample app in [release mode][performance].


## Missed or forgot something?

If I forgot something or you have any ideas what can be added or corrected, please create an issue or contact me directly.


## License

```
Copyright 2023 Vladimir Raupov

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


[compose]: https://developer.android.com/jetpack/compose
[composites-fiberglass]: https://github.com/LDRAlighieri/Composites/tree/master/composites-fiberglass
[performance]: https://developer.android.com/jetpack/compose/performance#build-release