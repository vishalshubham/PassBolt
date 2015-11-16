# PassBolt
A smart password manager android application

Important Note:
-----------
This project is kept private because we are planning to file for a patent. A description of the project has been provided below.

Inspiration: 
-----------
We all have a large number of online accounts ranging from social networking to banking to utilities. It is really difficult to track the passwords for these accounts and keep them safe. We came up with a solution, which not only makes it easier to manage passwords, it also allows the launching of applications remotely on a computer using an Android based mobile device. This application is protected by an NFC card (for example: expired or active credit card), which makes it an inexpensive but effective password vault.

What it does: 
-----------
It safely keeps different passwords at one place and provides a single click launch from the mobile app. First, the user need to register on the application by providing a username, pin and a tap of any NFC card. Once registered, the user can login using the combination of these three. As a one time step the user needs to save the credentials of various accounts. So, after logging in, the user will select an application and it will remotely connect to the computer, open the web page and login into it.

How We built it:
-----------
The mobile app was created using Android. We made use of NFC technology to control the access. We also wrote a Java based daemon runs on the computer and listens for requests from the android app.

Challenges We ran into: 
-----------
We needed a way to remotely control a desktop computer from a mobile device. We needed to make the login screen more secure, so we used NFC in a really cool way to make a secure password entry to the vault.
Accomplishments that We are proud of: An inexpensive way to provide a rock solid security for the password vault. Remotely controlling a computer using a mobile device.

What We learned: 
-----------
Automation frameworks such as Selenium and HttpUnit NFC communication using Android REST using Jersey Deployment on Microsoft Azure

What's next for PassBolt: 
-----------
A "share password feature" which will allow a user to share his/her password for a limited time without revealing it.
