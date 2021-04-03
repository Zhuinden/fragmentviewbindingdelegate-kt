/*
 * Copyright 2021 Gabor Varadi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhuinden.fragmentviewbindingdelegatekt

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
    private val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        binding ?: createBinding(thisRef)

    private fun createBinding(fragment: Fragment): T {
        val viewLifecycle = fragment.viewLifecycleOwner.lifecycle

        check(viewLifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            "Must not attempt to get bindings when Fragment views are destroyed."
        }

        val lifecycleCallback = object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                if (f === fragment) {
                    binding = null
                    fragment.parentFragmentManager.unregisterFragmentLifecycleCallbacks(this)
                }
            }
        }
        fragment.parentFragmentManager.registerFragmentLifecycleCallbacks(lifecycleCallback, false)
        return viewBindingFactory(fragment.requireView()).also { binding = it }
    }
}

@Suppress("unused")
fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
): FragmentViewBindingDelegate<T> = FragmentViewBindingDelegate(viewBindingFactory)
