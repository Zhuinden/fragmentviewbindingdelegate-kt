# Change log

-FragmentViewBindingDelegate-KT 1.0.2 (2022-10-29)
--------------------------------

- CHANGE: use `view != null` instead of `lifecycle.isAtLeast(INITIALIZED)` to allow people to create and destroy a view in onDestroyView. (thanks @kendrickkwok and @dptsolutions)

- CHANGE: recreate binding if the view doesn't match the one in the fragment (should fix the binding in retained fragments)

-FragmentViewBindingDelegate-KT 1.0.1 (2022-08-21)
--------------------------------

- FIX: use `viewLifecycleOwnerLiveData` instead of `viewLifecycleOwner.lifecycle` to allow accessing binding in `onDestroyView()` (fixes #12, thanks @rubensousa) 

- FIX: use `thisRef` instead of `fragment` in `getValue()` to allow usage in `FragmentScenario` (thanks @apramana)

-FragmentViewBindingDelegate-KT 1.0.0 (2021-02-10)
--------------------------------

- Initial Release.