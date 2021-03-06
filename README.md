# junit-mockito-extension

[![Build Status](https://travis-ci.org/anigenero/junit-mockito-extension.svg?branch=master)](https://travis-ci.org/anigenero/junit-mockito-extension)
 [ ![Download](https://api.bintray.com/packages/anigenero/maven/junit-mockito-extension/images/download.svg) ](https://bintray.com/anigenero/maven/junit-mockito-extension/_latestVersion)

This library provides a Mockito extenstion for injecting annotated mocks in JUnit 5 

### Setup ###
In your build.gradle, specify the repo and dependencies
```groovy
repositories {
    jcenter()
}

dependencies {

    testCompile group: 'com.anigenero.junit', name: 'junit-mockito-extension', version: '1.0.1'

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
