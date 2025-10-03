# week21

Execution Steps in IntelliJ

Open IntelliJ → Import as Maven project.

Ensure Maven dependencies download successfully (Reload All Maven Projects).

## Start Selenium Grid Hub + Nodes:

java -jar selenium-server-4.24.0.jar hub
java -jar selenium-server-4.24.0.jar node --detect-drivers true --publish-events tcp://localhost:4442 --subscribe-events tcp://localhost:4443


# Run:

mvn clean test


or directly right-click testng.xml → Run.

# Check results in TestNG HTML report under:

target/surefire-reports/index.html
