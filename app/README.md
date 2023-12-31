Overview

This GitHub App is a simple Android application that fetches repository data from the GitHub API, 
saves it locally using Room database for offline access, and provides a multi-screen experience. 

The app consists of three main screens:

- Repositories List Screen: This is the first screen where the app loads the repository data from the 
GitHub API and saves it in the local Room database. It also supports pagination for a smoother user experience.

- Repository Details Screen: After selecting a repository from the list, users can navigate to this 
screen to view detailed information about the selected repository.

- Issues Screen: Users can further explore issues related to the selected repository on this screen.

How to Test

To test the GitHub App, follow these steps:

Prerequisites

- Android Studio installed on your machine.
- A physical Android device or an emulator with a minimum API level of 21 (Android 5.0 Lollipop).
Installation
- Clone the Repository:git clone https://github.com/Alaaelfeshawy/GithHubApp.git

Open in Android Studio:

- Open Android Studio.
- Click on "Open an existing Android Studio project."
- Navigate to the directory where you cloned the repository and select it.

Run the App:

- Connect your Android device or start an emulator.
- Click on the "Run" button in Android Studio.

Testing Scenarios

- Loading Repositories:

1.Open the app and verify that the list of repositories is loaded from the GitHub API on the first screen.
2.Scroll through the list and ensure that pagination works as expected.
-Repository Details:
1.select a repository from the list and verify that the details are displayed correctly on the second screen.
-Issues Screen:
1.From the Repository Details screen, navigate to the Issues screen and verify that it shows issues related to the selected repository.
