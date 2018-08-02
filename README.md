# junit-mockito-extension

This library provides a Mockito extenstion for injecting annotated mocks in JUnit 5 

### Setup ###
In your build.gradle, specify the repo and dependencies
```groovy
repositories {
    maven {
        url 'http://repo.anigenero.com/repository/maven-public'
    }
}

dependencies {

    testCompile group: 'com.anigenero.junit', name: 'junit-mockito-extension', version: '1.0.0'

    // remember to include the JUnit and mockito libraries
    testCompile group: 'org.junit.jupiter', name: 'junit-jupiter-api', version: junitVersion
    testRuntime group: 'org.junit.jupiter', name: 'junit-jupiter-engine', version: junitVersion
    testCompile group: 'org.mockito', name: 'mockito-core', version: mockitoVersion
    
}

```

For your tests, set the extension for your test suite, and annotate any mocks that need to be injected

```java
import com.anigenero.junit.mockito.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MyJunitTest {
    
     @Mock
     private MyMockSubject myMockSubject;
     
     // ...
       
}
```