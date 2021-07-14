# AWS SQS (Simple Queue Service) test 

  Technologies
  -----------

  - Java 11
  - Maven 
  - Spring Boot 2.5.2
  - Lombok
  
  
  Installation
  --------------
  
  ### Install JDK 11
  1. Download the [installation bundle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) for your OS.
  2. Install the Development Kit.
  3. Add an environment variable called `JAVA_HOME` pointing to the folder created by the installation (e.g. `C:\Program Files\Java\jdk1.8.0_91`).
  4. Add the `JAVA_HOME` variable to your PATH variable.
  5. Open a console and run `java -version` to verify the installation.
  
  ### Test it
  
  By default the service starts listening at port :9091
  
  Environments and deployment
  ----------------------------
  Variables:
      The following variables will need to be. Please check application.yml file to configure it.
  - cloud.aws
    - credentials:
        - access-key: YOUR_ACCESS_KEY_HERE
        - secret-key: SECRET_KEY_HERE
    - endpoint:
            uri: https://sqs.us-east-2.amazonaws.com/1234564789/YOUR-QUEUE-HERE
  - queue:
      name: YOUR-QUEUE-NAME-HERE