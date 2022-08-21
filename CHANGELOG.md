# Change log

-FragmentViewBindingDelegate-KT 1.0.1 (2022-08-21)
--------------------------------

- FIX: use `viewLifecycleOwnerLiveData` instead of `viewLifecycleOwner.lifecycle` to allow accessing binding in `onDestroyView()` (fixes #12, thanks @rubensousa) 

- FIX: use `thisRef` instead of `fragment` in `getValue()` to allow usage in `FragmentScenario` (thanks @apramana)

-FragmentViewBindingDelegate-KT 1.0.0 (2021-02-10)
--------------------------------

- Initial Release.