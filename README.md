# Unit Test Java
The most actual and most used unit testing tools and usage for Java projects

**To clone the project;**
```git clone https://github.com/ErtanSidar/Unit-Test-Java.git```

A first test case
```
class MyFirstJUnitJupiterTests {

    private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        // Returns true if the first parameter is equal to the second parameter, and the test runs
        assertEquals(2, calculator.add(1, 1)); 
    }

}

class Calculator {
    public int add(int num1, int num2) {
        return num1+num2;    
    }
}
```
We can create our own test annotation, like @Tag("fast")
```
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
public @interface Fast {
}
```
The following @Test method demonstrates usage of the @Fast annotation.
```
@Fast
@Test
void myFastTest() {
    // ...
}
```
## Assertions 
* assertTrue(boolean contition) => Asserts that a condition is true.
* assertFalse(boolean contition) => Asserts that a condition is false.
* assertNull(Object object) => Asserts that an object is null.
* assertNotNull(Object object) => Asserts that an object isn’t null.
* assertEquals(Object object1, Object object2) => Asserts that two objects are equal.
* assertSame(Object object1, Object object2) => Asserts that two objects refer to the same object.
* assertNotSame(Object object1, Object object2) => Asserts that two objects dont refer to the same object.
* assertArrayEquals(Array array1, Array array2) => Asserts that two arrays are equal.
```
        Student ertan = new Student("1","Ertan","Sidar");
        
        // assertEquals
        // checking if two objects are equal
        assertEquals("Ertan",ertan.getName());
        
        // assertEquals
        // checking if two objects are equal with message
        assertEquals("Ertan",ertan.getName(),"Student's name");
        
        // assertNotEquals
        // checking if two objects are not equal
        assertNotEquals("Ertann",ertan.getName(),"Student's name");
        
        // assertTrue
        // checking if a condition is true
        assertTrue(ertan.getName().startsWith("E"));
        
        // assertTrue
        assertTrue(ertan.getName().startsWith("E"), ()->"Student's name "+" starts with E");
        
        // assertFalse
        // checking if a condition is false
        assertFalse(()-> {
            Student mehmet = new Student("id1","Mehmet","Can");
            return mehmet.getName().endsWith("M");
        },()->"Student's name"+"ends with M");
        
        final Student ahmet = new Student("2","Ahmet","Yılmaz");
        
        // assertArrayEquals
        // checking if two arrays are the equal
        assertArrayEquals(new String []{"Ertan","Ahmet"}, Stream.of(ertan,ahmet).map(Student::getName).toArray());
        
        Student student=ertan;
        
        // assertSame
        // checking if two objects references point the same object
        assertSame(ertan,student);
        
        // assertNotSame
        // checking if two objects references don't point the same object
        assertNotSame(ahmet,student);
        
        Student ertan = new Student("1", "Ertan", "Sidar");

        // In a grouped assertion all assertions are executed,
        // and any failures will be reported together.
        assertAll("Student's name check",
                () -> assertEquals("Ertan", ertan.getName()),
                () -> assertEquals("Ertan", ertan.getName(), "Student's name"),
                () -> assertNotEquals("Ertann", ertan.getName(), "Student's name")
        );

```
## A standard test class
* @BeforeAll => Run before all tests and run once
* @AfterAll => Run after all tests and run once
* @BeforeEach => Run before each test
* @AfterEach => Run after each test
* @Disabled() => The method is disabled
```
public class StandartTestClass {

    private static String oneInstancePerClass;
    private Integer onInstancePerMethod;

    @BeforeAll
    static void initAll() {
        oneInstancePerClass=String.valueOf(new Random().nextInt());
        System.out.println("Init Before All Test Method");
    }
    @AfterAll
    static void tearDownAll() {
        oneInstancePerClass=null;
        System.out.println("Tear Down After All Test Method");
    }
    @BeforeEach
    void init() {
        onInstancePerMethod=new Random().nextInt();
        System.out.println("Init Before Each Test Method");
    }
    @AfterEach
    void tearDown() {
        oneInstancePerClass=null;
        System.out.println("Tear Down After Each Test Method");
    }
    @Test
    void testSomething1() {
        System.out.println("Test: testSomething1 :"+onInstancePerMethod+":"+oneInstancePerClass);
    }
    @Test
    void testSomething2() {
        System.out.println("Test: testSomething2 :"+onInstancePerMethod+":"+oneInstancePerClass);
    }
    @Test
    @Disabled("This test is not in scope for now")
    void testSomething3() {
        System.out.println("Test: testSomething3");
    }
    @Test
    void testSomethingToFail() {
        Assertions.fail("A failing test...");
    }
}
```
## Display Names
Test classes and test methods can declare custom display names via @DisplayName — with spaces, special characters, and even emojis — that will be displayed in test reports and by test runners and IDEs.
```
@DisplayName("A special test case")
class DisplayNameDemo {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }

}
```
## Assumptions
JUnit Jupiter comes with a subset of the assumption methods that JUnit 4 provides and adds a few that lend themselves well to being used
with Java 8 lambda expressions and method references.
```
    @Test
    @DisplayName("Test student creation at only development machine")
    @Tag("createStudent")
    void shouldCreateStudentWithNameAndSurnameAtDevelopmentMachine() {

        assumeTrue(System.getProperty("ENV") != null, "Aborting Test: System property ENV doesn't exist!");
        assumeTrue(System.getProperty("ENV").equals("dev"), "Aborting Test: Not on a developer machine!");

        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
        
        final String env = System.getProperty("ENV");
        assumingThat(env != null && env.equals("dev"), () -> {
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(null, null);
            ahmet.addCourse(lecturerCourseRecord);
            assertEquals(1, ahmet.getStudentCourseRecords().size());
        });
    }
```
## Conditional Test Execution
JUnit Jupiter allows developers to either enable or disable a container or test based on certain conditions programmatically
* @EnabledOnOs({OS.WINDOWS}) => Enable on Windows OS
* @DisabledOnOs({OS.MAC}) => Disable on Mac OS
* @EnabledOnJre(JRE.JAVA_10) => Enable for Java 10
* @EnabledOnJre({JRE.JAVA_9, JRE.JAVA_10}) => Disable for Java 9, Java 10
* @EnabledIf("2 * 3 == 6") // Static JavaScript expression.
* @DisabledIf("Math.random() < 0.314159") // Dynamic JavaScript expression.
```
public class ConditionalStudentTest {

    @EnabledOnOs({OS.WINDOWS}) 
    @Test
    void shouldCreateStudentOnlyOnWindow() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @DisabledOnOs({OS.MAC})
    @Test
    void shouldCreateStudentOnlyOnNonMac() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @EnabledOnJre(JRE.JAVA_10)
    @Test
    void shouldCreateStudentOnlyOnJRE10() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @EnabledOnJre({JRE.JAVA_9, JRE.JAVA_10})
    @Test
    void shouldCreateStudentOnlyOnJRE9orJRE10() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @DisabledOnJre(JRE.JAVA_10)
    @Test
    void shouldCreateStudentOnlyOnNonJRE10() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @Test
    void shouldCreateStudentOnlyOn64Architectures() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @DisabledIfSystemProperty(named = "ENV", matches = "dev")
    @Test
    void shouldCreateStudentOnlyOnDevMachine() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }


    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    @Test
    void shouldCreateStudentOnlyOnStagingEnv() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @DisabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    @Test
    void shouldCreateStudentOnlyOnNonCIEnv() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @EnabledIf("2 * 3 == 6") // Static JavaScript expression.
    @Test
    void shouldCreateStudentIfStaticJSExpressionIsEvaluatedToTrue() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @DisabledIf("Math.random() < 0.314159") // Dynamic JavaScript expression.
    @Test
    void shouldCreateStudentIfDynamicJSExpressionIsEvaluatedToTrue() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @DisabledIf("/64/.test(systemProperty.get('os.arch'))") // Regular expression testing bound system property.
    @Test
    void shouldCreateStudentOnlyOn32BitArchitectures() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @EnabledIf("'staging-server' == systemEnvironment.get('ENV')")
    @Test
    void shouldCreateStudentOnlyOnStagingEnvEvaluatedWithJS() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @TestOnWindowsWithJRE10
    void shouldCreateStudentOnlyOnMacWithJRE10() {
        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(OS.WINDOWS)
    @EnabledOnJre(JRE.JAVA_9)
    @interface TestOnWindowsWithJRE10 {
    }
}
```
## Tagging and Filtering
Test classes and methods can be tagged via the @Tag annotation. Those tags can later be used to filter test discovery and execution.
```
@Tag("fast")
@Tag("model")
class TaggingDemo {

    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

}

```
## Nested Tests
@Nested tests give the test writer more capabilities to express the relationship among several groups of tests. 
Such nested tests make use of Java’s nested classes and facilitate hierarchical thinking about the test structure.
```
@DisplayName("A stack")
class TestingAStackDemo {

    Stack<Object> stack;

    @Test
    @DisplayName("is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        new Stack<>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
```
## Dependency Injection for Constructors and Methods
In all prior JUnit versions, test constructors or methods were not allowed to have parameters (at least not with the standard Runner implementations). As one of the major changes in JUnit Jupiter, both test constructors and methods are now permitted to have parameters. This allows for greater flexibility and enables Dependency Injection for constructors and methods.
```
@DisplayName("TestInfo Demo")
class TestInfoDemo {

    TestInfoDemo(TestInfo testInfo) {
        assertEquals("TestInfo Demo", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        assertEquals("TEST 1", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    void test2() {
    }

}

class TestReporterDemo {

    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a status message");
    }

    @Test
    void reportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("a key", "a value");
    }

    @Test
    void reportMultipleKeyValuePairs(TestReporter testReporter) {
        Map<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");

        testReporter.publishEntry(values);
    }

}
```







