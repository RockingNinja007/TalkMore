# TalkMore
TalkMore is android based application that implements firebase back-end support to manage robust and real time data. TalkMore is an easy to use chat application with easy to use UI.

SET UP

	Add in projrct level build.gradle file(Mendatory)
 
 	 allprojects {
 	   repositories {
        google()
        jcenter()
        maven {
            url "https://maven.google.com" // Google's Maven repository
        }
		}
		}  
		and add dependencies as
		dependencies {
		classpath 'com.android.tools.build:gradle:3.0.1'
		classpath 'com.google.gms:google-services:3.1.1'
		}
		}		
	
....

	Add in app level build.gradle file(Mendatory)  
  	add dependencies 	
    // these are necessary plugins for accesing Firebase classes
    compile 'com.google.firebase:firebase-core:11.6.0'
    compile 'com.google.firebase:firebase-database:11.6.0'
    compile 'com.google.firebase:firebase-auth:11.6.0'
    compile 'com.google.firebase:firebase-messaging:11.6.0'
    compile 'com.google.firebase:firebase-crash:11.6.0'
    compile 'com.google.firebase:firebase-firestore:11.6.0'
    compile 'com.google.firebase:firebase-storage:11.6.0'
    
    //this is plugin for validation class AwesomeValidation used in the app
    compile 'com.basgeekball:awesome-validation:1.3'
    
  	//add in the end of the app level build.gradle
    apply plugin: 'com.google.gms.google-services'
	
FIREBASE configuration

	To set up firebase in your application
		Go to android Studio
			make new project name anything you want
		Go to firebase console
			make new project 
			follow the instruction
			make sure to put your SHA1 code while configuring Firebase this will help firebase to identify your app.

		
    
    
 
