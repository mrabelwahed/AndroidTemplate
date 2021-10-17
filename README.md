
## Challenge 1

The goal of this challenge is to implement a sample project, where you can see a restaurant list.
 You will able to sort the restaurant list based on it’s current openings state, you can favourite a restaurant and
 you can select a sort value to further sort the list. Finally you would like to see you add the option to filter the restaurant list, based on a restaurant’s name.
 In the assets you can find a JSON file (data.json), this file contains all the necessary data to complete this challenge. Parse the JSON file and use it for the visualization and sorting of the list.
 Use the following priority of the sorting (from the highest to the lowest priority):

1. Favourites​: Favourite restaurants are at the top of the list, your current favourite restaurants are stored locally on the phone.
2. Openings state​: Restaurant is either open (top), you can order ahead (middle) or a restaurant is currently closed (bottom). (Values available in data.json)
3. Sort options​: Always one sort option is chosen and this can be best match, newest, rating average, distance, popularity, average product price, delivery costs or the minimum cost. (Values available in data.json)
4. Filtering​: It’s up to you how you how you want to search by restaurant name.


## How to run the project

 1. Download the project
 2. Import the project to Android Studio Tool
 3. Run the project
 4. Check the Emulator or your real device

 ## How to run the unit and instrumentation tests

 1. Make sure your emulator is up running (for instrumentation test only)
 2. OPen the terminal from Android Studio
 3- type the following command in terminal

    ./gradlew test connectedAndroidTest

 ## Run Ktlint

 1. to check the formatting. Run the following command in terminal
     ./gradlew ktlintCheck
 2. to format the code. Run the following command in terminal
    ./gradlew ktlintFormat

## The App Architecture

 1. Clean Architecture based on  the MVVM Architecture pattern with Interactors

## The Template Extras

1. The App has two build types debug and release with two different app icons and app names
2. support dark theme

## Libs

1. Hilt for dependency Injection
2. RxJava for Handling Threading ,  powerful operators and Reactive Programming
3. Room for store data locally
4. Architecture Components like ViewModel and LiveData
5. Junit for Assertions
6. Mockito for mocking objects
7. Material Design components


