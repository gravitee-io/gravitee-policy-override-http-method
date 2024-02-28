## [2.2.1](https://github.com/gravitee-io/gravitee-policy-override-http-method/compare/2.2.0...2.2.1) (2024-02-28)


### Bug Fixes

* **deps:** update dependency io.gravitee:gravitee-bom to v4.0.3 ([a2b7257](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/a2b7257e8700de1f7cda620e64d70913391daa23))

# [2.2.0](https://github.com/gravitee-io/gravitee-policy-override-http-method/compare/2.1.1...2.2.0) (2023-12-19)


### Features

* enable policy on REQUEST phase for message APIs ([1e186c4](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/1e186c4ed06fd9e57c4a20f6c78e9a581f2737f4)), closes [gravitee-io/issues#9430](https://github.com/gravitee-io/issues/issues/9430)

## [2.1.1](https://github.com/gravitee-io/gravitee-policy-override-http-method/compare/2.1.0...2.1.1) (2023-07-20)


### Bug Fixes

* update policy description ([31641e7](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/31641e791d14f5fb410c79d230ee6165c12aa68f))

# [2.1.0](https://github.com/gravitee-io/gravitee-policy-override-http-method/compare/2.0.0...2.1.0) (2023-06-27)


### Features

* add proxy phase properties ([1b06703](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/1b06703a0cb1569be7f4912a54efbcb31cfe73af))

# [2.0.0](https://github.com/gravitee-io/gravitee-policy-override-http-method/compare/1.3.0...2.0.0) (2023-03-17)


### Bug Fixes

* **deps:** bump dependencies ([5b26345](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/5b26345a0e988a39e6d8df4fd50ec3d1d452992b))
* rename 'jupiter' package in 'reactive' ([2a613bf](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/2a613bfd2ed05249ff033e5a3d9fddec51051f06))


### Features

* modify the request method in v4 ([c1388a4](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/c1388a41e51b6de6afc5e01c661097423550d3f4))


### BREAKING CHANGES

* Requires APIM 3.20 minimum because it requires RxJava3.
With V4 Api definition, the request's method is directly overridden.

# [2.0.0-alpha.2](https://github.com/gravitee-io/gravitee-policy-override-http-method/compare/2.0.0-alpha.1...2.0.0-alpha.2) (2023-03-13)


### Bug Fixes

* rename 'jupiter' package in 'reactive' ([0ac0567](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/0ac0567e32e00045af719ce15ef8a9e18ba74054))

# [2.0.0-alpha.1](https://github.com/gravitee-io/gravitee-policy-override-http-method/compare/1.3.0...2.0.0-alpha.1) (2023-01-13)


### Features

* modify the request method in v4 ([e80114a](https://github.com/gravitee-io/gravitee-policy-override-http-method/commit/e80114a0932da163fe0484c97227ec0abb8f4ea8))


### BREAKING CHANGES

* Requires APIM 3.20 minimum because it requires RxJava3.
With V4 Api definition, the request's method is directly overridden.
