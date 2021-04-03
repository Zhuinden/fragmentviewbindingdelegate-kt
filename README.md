# FragmentViewBindingDelegate-KT

FragmentViewBindingDelegate-KT contains a helper delegate for auto-clearing the binding variable when the Fragment view is destroyed.

``` kotlin
class MyFragment: Fragment(R.layout.my_fragment) {
    private val binding by viewBinding(MyFragmentBinding::bind)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val binding = binding
        
        // ...
    }
}
```

## Why?

Because automatic clearing in `onDestroyView` and the erasure of the backing property (without use of inheritance!) is a net benefit in cases where the `binding` field is unavoidable.

## How?

It works as outlined in [this article](https://medium.com/@Zhuinden/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c).

## Using FragmentViewBindingDelegate-KT

In order to use FragmentViewBindingDelegate-KT, you need to add `jitpack` to your project root `build.gradle.kts`
(or `build.gradle`):

``` kotlin
// build.gradle.kts
allprojects {
    repositories {
        // ...
        maven { setUrl("https://jitpack.io") }
    }
    // ...
}
```

or

``` groovy
// build.gradle
allprojects {
    repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
    // ...
}
```

and then, add the dependency to your module's `build.gradle.kts` (or `build.gradle`):

``` kotlin
// build.gradle.kts
implementation("com.github.Zhuinden:fragmentviewbindingdelegate-kt:1.0.0")
```

or

``` groovy
// build.gradle
implementation 'com.github.Zhuinden:fragmentviewbindingdelegate-kt:1.0.0'
```

Due to a transitive dependency on `lifecycle-common-java8`, the following block must also be added to the `android {` block in `build.gradle`:

``` groovy
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
```

And you also need to enable `viewBinding`.

``` groovy
    buildFeatures {
        viewBinding = true
    }
```

## License

    Copyright 2021 Gabor Varadi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
