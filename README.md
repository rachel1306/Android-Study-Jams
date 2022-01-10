# Android-Study-Jams
## Notes4You

### **Problem Statement:**
Taking down notes on important matters has been something practiced by people for a very long time. But as the world started modernizing and technology progressed, people have started looking for mobile applications to perform the same activity. It helps to efficiently get the work done as they are widely used and are easily accessible.
Proposed Solution :
We have developed a note-taking app using simple and basic UI, so that everyone can understand and use it easily. Its features include creating new notes along with a description of what is to be done to complete that particular note. Once you are done using it, it can be deleted to remove it from the application. All notes are shown on the main screen and we can scroll through to find our required note page.

### **Screenshots:** 
<img src="https://github.com/rachel1306/Android-Study-Jams/blob/master/Screenshots/main_screen.png" alt="" width="250px"/>

<img src="https://raw.githubusercontent.com/rachel1306/Android-Study-Jams/master/Screenshots/add_screen.png" alt="" width="250px"/>

<img src="https://raw.githubusercontent.com/rachel1306/Android-Study-Jams/master/Screenshots/main_screen_after_addition.png" alt="" width="250px"/>

<img src="https://raw.githubusercontent.com/rachel1306/Android-Study-Jams/master/Screenshots/edit_screen.png" alt="" width="250px"/>

<img src="https://raw.githubusercontent.com/rachel1306/Android-Study-Jams/master/Screenshots/main_screen_on_deletion.png" alt="" width="250px"/>

### **Functionality & Concepts used:**
1.	LiveData 
2.	ViewBinding 
3.	Room Database 
4.	Navigation Library 
5.	Android Jetpack Libraries 

The App has a very simple and interactive interface which helps the users efficiently create and manage their notes. Following are few android concepts used to achieve the functionalities in app :
*	Constraint Layout : Most of the activities in the app uses a flexible constraint layout, which is easy to handle for different screen sizes.
*	RecyclerView : RecyclerView is used to present all the notes
*	LiveData & Room Database : New notes can be added to the application an any instant. LiveData is used to update and observe any changes made in the note, and update it to local databases using Room. 

### **Application Link & Future Scope:**
The app is currently in the Alpha testing phase with limited number of users. Access to the app : https://github.com/rachel1306/Android-Study-Jams

Once the app is fully tested and functional  with the existing feature, we are planning to modify the application and add more features like: 
 
*	adding images from gallery as well as using camera to take pictures, 
*	scan the images and extracting text from the image , 
*	adding various types for fonts and other text features 
*	adding a pen like feature to take down notes.
