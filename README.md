# Setup
The following is required in order to be able to run this project.

**Appium**  
You can install Appium as follows:  
- **Install node**  
`brew install node`

- **Install Appium**  
`npm install -g appium`

- **Install the appium web driver client**  
`npm install wd`

If these options do not work for you then please visit the [Getting Started](http://appium.io/docs/en/about-appium/getting-started/?lang=en) Appium page. 

**Android SDK**  
It is possible to install the Android SDK without the use of the Android Studio IDE, however I'd strongly recommend downloading this IDE
in order to manage and maintain the Android SDK, as doing so manually can be cumbersome and error prone.

**xcode commandline tools**  
While this is not necessary for running the Appium tests, I'd recommend installing the xcode commandline tools for any future interactions with
the iOS ecosystem.
`xcode-select --install`


This should be everything needed to get the base tests up and running.


# Running The Tests
The test scripts can be run either manually or via an automated script. Running them manually allows you to target one specific 
device/platform at a time, while the automated script can be used to run the tests against multiple devices/platforms.
The script will run the tests in a synchronous manner. It is possible to run the tests in parallel but at the time of creating this project 
there exists a bug in Appium that prevents this from working on Android and has mixed results on iOS.

## Manually
The following command, run from the projects root directory, will run the tests.
`OS_TYPE=ios11 mvn clean test`

`OS_TYPE` is used to specify the os that the tests will run against.
`ios11` Targets the tests laid out in `tests-for-ios11.xml` which uses xml parameters to target iOS 11. NOTE that if this is not being run on the 
Jenkins build server (the slave box in Dublin) then the value for the `udid` parameter would need to be changed to match one of the simulator
udid's. This may also need to be done if the udid's for the simulator ever change.

The `mvn clean test` will use maven to clean the repo and build the test suite.

The same format can be used to run the tests on Android
`OS_TYPE=android8 mvn clean test`

This will target Android 8. The same thing goes here regarding the udid's. They are generally easier to work with than iOS as most emulators will get 
the same udid across different machines (usually starting with `emulator-5554`).

## Automated  
The following script will automate running the android and ios tests respectively. Each script will run/has the ability to run the tests against
multiple versions of each platform, e.g Android 6 & Android 8.  
`./run_android_tests.sh`  
`./run_ios_tests.sh`  
