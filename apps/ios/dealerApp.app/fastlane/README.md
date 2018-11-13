fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

# Available Actions
## iOS
### ios fabric_debug
```
fastlane ios fabric_debug
```
Send Debug to Fabric
### ios fabric_beta
```
fastlane ios fabric_beta
```
Send Beta to Fabric
### ios release_on_ci
```
fastlane ios release_on_ci
```
Deploy a new version to the App Store
### ios refresh_dsyms
```
fastlane ios refresh_dsyms
```
Upload latest dSYMS to Fabric

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
