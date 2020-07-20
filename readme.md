Here is 2 tests, according to the task.
Before you run tests you must set properties in file

`src/test/resources/application.properties`
 
One test is about Amazon web site.

You need to specify 

`webdriver.hub.url` - your selenium hub address

Another one about Bazaraki web site via appium (tested only on android emulator). 

`appium.server.url` - appium server url

`android.version` - android OS version

`android.device.name` - change it, if required to your device name

`appium.version` - set your appium version


To run tests:
`./gradlew clean test -Dtest.browser=chrome`

You can specify property `test.browser` to one of accepted values:
`chrome`, `firefox`, `opera`, by default it is `local`

To generate test report:

`./gradlew allureReport`

You can also run everything in one command:
`./gradlew clean test allureReport -Dtest.browser=chrome`


HERE TEST FOR MANUAL TESTS TASK

`test 1, 2`

	select +357 from country code dropdown
	enter valid phone number
	accept policy
	click on continue
	enter a code from SMS
	check: 
	User is logged in to the web site
	(Repeat the same test with +30 in country code dropdown)

`test 3, 4`

	select +357 from country code dropdown
	leave phone number field blank
	accept policy
	click on continue
	check: 
	error message saying that phone number is empty or invalid is displayed
	(Repeat the same test with +30 in country code dropdown)

`test 5, 6`

	select +357 from country code dropdown
	enter only one digit into phone number field
	accept policy
	click on continue
	check: 
	error message saying that phone number is empty or invalid is displayed
	(Repeat the same test with +30 in country code dropdown)

`test 7, 8`

	select +357 from country code dropdown
	enter valid phone number + 1 digit into phone number field
	accept policy
	click on continue
	check: 
	error message saying that phone number is empty or invalid is displayed
	(Repeat the same test with +30 in country code dropdown)

`test 7, 8`

	select +357 from country code dropdown
	enter valid phone number + 1 digit into phone number field
	accept policy
	click on continue
	check: 
	error message saying that phone number is empty or invalid is displayed
	(Repeat the same test with +30 in country code dropdown)

`test 9, 10`

	select +357 from country code dropdown
	enter characters into phone number field
	accept policy
	click on continue
	check: 
	error message saying that phone number is empty or invalid is displayed
	(Repeat the same test with +30 in country code dropdown)
