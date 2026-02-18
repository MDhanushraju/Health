# Install Maven on Windows

Choose **one** of these options.

---

## Option 1: Install Maven globally (recommended)

1. **Download Maven**  
   - https://maven.apache.org/download.cgi  
   - Under "Files" click **apache-maven-3.9.6-bin.zip** (or latest 3.9.x).

2. **Extract**  
   - Unzip to a folder, e.g. `C:\Program Files\Apache\maven` (no spaces in path is best).

3. **Set environment variables**  
   - **JAVA_HOME** – must already point to your JDK (e.g. `C:\Program Files\Java\jdk-17`).  
   - **MAVEN_HOME** – Maven install folder (e.g. `C:\Program Files\Apache\maven`).  
   - **Path** – add `%MAVEN_HOME%\bin`.

   To set in PowerShell (run as Administrator if needed):
   ```powershell
   [Environment]::SetEnvironmentVariable("MAVEN_HOME", "C:\Program Files\Apache\maven", "Machine")
   $path = [Environment]::GetEnvironmentVariable("Path", "Machine")
   [Environment]::SetEnvironmentVariable("Path", "$path;%MAVEN_HOME%\bin", "Machine")
   ```
   Then **close and reopen** your terminal/IDE.

4. **Verify**
   ```powershell
   mvn -v
   ```

---

## Option 2: Use Maven Wrapper (no global install)

If the file `.mvn/wrapper/maven-wrapper.jar` is already in this project, you can use:

```powershell
.\mvnw.cmd clean package -DskipTests
.\mvnw.cmd spring-boot:run
```

If the wrapper JAR is missing, download it once:

1. Download:  
   https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar  

2. Save it as:  
   `d:\Front-end\health\.mvn\wrapper\maven-wrapper.jar`  

3. Then run `.\mvnw.cmd` as above.

---

## Option 3: Chocolatey

If you use Chocolatey:

```powershell
choco install maven
```

Then close and reopen the terminal and run `mvn -v`.
