1. To run you need to import the jfoenix library(the jar file) in intelij/eclipse
* for intelij - file - project structure - libraries - the plus sign - select the jar file 
* I also use java 8 and sdk 1.8, if it bugs for you

2. If you want to edit the views, I suggest downloading scene builder 2.0 https://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-1x-archive-2199384.html
* you have to import the library there too (next to libraries there is an wheel button - select import jar/fxml)
* if you find it hard to find the wheel https://www.youtube.com/watch?v=Di9f_eP_x9I

3. I have made few changes:
* the views are in separate package
* the controllers and the main are in sample (NOT IN CONTROLLERS(need some time to fix the packages if you move them))
* the resources for now includes only the images
* back button on create an account view