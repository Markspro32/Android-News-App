# Android-News-App
A student who is studying Android crafted a news app using Android Studio
## Preview:
On Pixel Tablet:

<img width="498" height="312" alt="Screenshot 2026-04-03 at 11 15 45 AM" src="https://github.com/user-attachments/assets/bc82d435-a524-4cad-bcd5-7c2ac16d5cdf" />

On Pixel Phone:

<img width="358" height="793" alt="Screenshot 2026-04-03 at 11 16 31 AM" src="https://github.com/user-attachments/assets/7f62727b-25fd-4ffb-98c8-4c2bbdf0e593" />

## My effort:
<img width="1512" height="982" alt="updated_news app_likes_1" src="https://github.com/user-attachments/assets/807d70bc-9143-4495-b53e-d00d908a3e3e" />
<img width="1512" height="982" alt="updated_news app_share" src="https://github.com/user-attachments/assets/b2369b3a-c28d-49cb-8c5d-5debf4e9b3d2" />
<img width="1512" height="975" alt="News app_phone" src="https://github.com/user-attachments/assets/8a02ea0d-6a35-4008-b1a0-630457bd1b68" />
<img width="1512" height="945" alt="News app_tablet" src="https://github.com/user-attachments/assets/68f37071-f67c-49f8-9c41-f3b8fcc4498c" />



## Simple News App Development Guide
### 1. Prerequisites
Before starting, ensure you have the following:
* Development Environment: Android Studio installed with the latest SDKs.
* Basic Knowledge: Familiarity with Fragments, RecyclerView, and Android lifecycle methods.
* Emulators: Configured for both phone and tablet testing.

### 2. Setup
#### 2.1 Project Initialization
* Create a new Android project.
* Set up the necessary dependencies for Fragments and RecyclerView in your build.gradle file.

#### 2.2 Data Model Creation
* Define a News class with the following properties:
    * headline (String)
    * content (String)

### 3. Implementation
#### 3.1 Layout Design
* Structure:
    * Header: Displays the news headline.
    * Body: Displays the news content, hidden by default in dual-page mode.
* Separation: Use a thin horizontal line between the header and body.

#### 3.2 NewsContentFragment
* onCreate(): Load the pre-designed news interface layout.
* refresh(): Implement to update the UI when a headline is selected.

#### 3.3 Single-Page Mode
* Create a new Activity.
* Reuse code from the dual-page mode.
* Override onCreate(): Retrieve and display the relevant news headline and content.

#### 3.4 News List with RecyclerView
* Design RecyclerView layout with specified dimensions, text truncation, and margins.
* Implement a new fragment to display the news list:
    * In onViewCreated(), verify if the dual-page layout is active by checking for NewsContentLayout.
    * Apply layout qualifiers to ensure visibility only in dual-page mode.

#### 3.5 Data Binding
* Develop an Adapter to efficiently bind the News data to the RecyclerView.

#### 3.6 Feature Extensions (Without Altering Source Code)
* Enhancements:
    * Add news charts, publisher logos, like, and share functionalities.
* Operator Overloading:
    * Modify the random news content generator logic.
    * Overload the * operator to streamline the code.

### 4. Testing
#### 4.1 Debugging
* Perform thorough code reviews and debug to identify and fix issues.

#### 4.2 Emulator Testing
* Test the app on both phone and tablet emulators.
* Validate:
    * Proper display of headlines and content.
    * Responsive layout in dual and single-page modes.
    * Functionality of new features (charts, logos, like/share buttons).
 

