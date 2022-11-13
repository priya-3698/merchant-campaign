# Phone Number Parser
## Problem Statement
https://drive.google.com/drive/folders/1G9m0Tu13d7pNYHc_RmnHQyS1OXc39hWY
## First-time Setup
1. Clone this repository to some directory on your machine.
2. Install IntelliJ IDEA Community (free - available via https://www.jetbrains.com/idea/download/)
3. For Mac, we will be using brew command to install most of the dependencies, so kindly install homebrew on your mac if not installed. Please refer the following document for installation: https://treehouse.github.io/installation-guides/mac/homebrew
4. For Java 8 installation run the following commands
```
brew tap adoptopenjdk/openjdk
brew install adoptopenjdk8
```
Once installed, set the Java Home path as follows
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
```
5. Install maven using the following command
```
brew install maven
```
6. Open IntelliJ, and open the `merchant-campaign` directory.
7. Install postgresql from `https://www.enterprisedb.com/downloads/postgres-postgresql-downloads`
8. Create database `merchant_campaign` 
9. Go to application.properties and update the database username and password under `spring.datasource.username` and `spring.datasource.password` respectively.
10. The database schemas will be created automatically on application initialization using flyway.
11. Install Docker Community Edition: https://docs.docker.com/get-docker/
12. Click Intellij IDEA | Preferences | Build, Execution, Deployment | Compiler | Annotation Processors and ensure Enable annotation processing is checked. Click OK to apply the changes.
13. Click Build -> Rebuild Project and wait for the build to complete successfully.
14. Open `MerchantCampaignApplication.java` and click `Run`
15. You should see a log message at the bottom of the console that looks like this:
   ```
   2022-11-12 18:28:38.738  INFO 4915 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9090 (http) with context path ''
   ```
16. In your browser, navigate to http://localhost:9090/merchant-campaign/health which should return `{"status": "UP"}`. This means the project is running successfully!