package seedu.progresschecker.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.progresschecker.model.ProgressChecker;
import seedu.progresschecker.model.ReadOnlyProgressChecker;
import seedu.progresschecker.model.exercise.Exercise;
import seedu.progresschecker.model.exercise.ModelAnswer;
import seedu.progresschecker.model.exercise.Question;
import seedu.progresschecker.model.exercise.QuestionIndex;
import seedu.progresschecker.model.exercise.QuestionType;
import seedu.progresschecker.model.exercise.StudentAnswer;
import seedu.progresschecker.model.exercise.exceptions.DuplicateExerciseException;
import seedu.progresschecker.model.person.Email;
import seedu.progresschecker.model.person.GithubUsername;
import seedu.progresschecker.model.person.Major;
import seedu.progresschecker.model.person.Name;
import seedu.progresschecker.model.person.Person;
import seedu.progresschecker.model.person.Phone;
import seedu.progresschecker.model.person.Year;
import seedu.progresschecker.model.person.exceptions.DuplicatePersonException;
import seedu.progresschecker.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ProgressChecker} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@gmail.com"),
                new GithubUsername("AlexGithub"), new Major("Computer Science"), new Year("2"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@gmail.com"),
                new GithubUsername("BerniceGithub"), new Major("Computer Engineering"), new Year("2"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@gmail.com"),
                new GithubUsername("CharlotteGithub"), new Major("Information Security"), new Year("2"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@gmail.com"),
                new GithubUsername("DavidGithub"), new Major("Computer Engineering"), new Year("2"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@gmail.com"),
                new GithubUsername("IrfanGithub"), new Major("Computer Science"), new Year("2"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@gmail.com"),
                new GithubUsername("RoyGithub"), new Major("Computer Science"), new Year("2"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyProgressChecker getSampleProgressChecker() {
        try {
            ProgressChecker sampleAb = new ProgressChecker();
            for (Person samplePerson : getSamplePersons()) {
                sampleAb.addPerson(samplePerson);
            }
            for (Exercise sampleExercise : getSampleExercises()) {
                sampleAb.addExercise(sampleExercise);
            }
            return sampleAb;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        } catch (DuplicateExerciseException e) {
            throw new AssertionError("sample data cannot contain duplicate exercises", e);
        }
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        HashSet<Tag> tags = new HashSet<>();
        for (String s : strings) {
            tags.add(new Tag(s));
        }

        return tags;
    }

    //@@author iNekox3
    public static Exercise[] getSampleExercises() {
        return new Exercise[] {
            // week 2
            new Exercise(new QuestionIndex("2.2.1"), new QuestionType("choice"),
                new Question("Which one of these is not a feature available in IDEs?\n"
                        + "\n"
                        + "a. Compiling.\n"
                        + "b. Syntax error highlighting.\n"
                        + "c. Debugging.\n"
                        + "d. Code navigation e.g., to navigate from a method call to the method implementation.\n"
                        + "e. Simulation e.g., run a mobile app in a simulator.\n"
                        + "f. Code analysis e.g. to find unreachable code.\n"
                        + "g. Reverse engineering design/documentation e.g. generate diagrams from code\n"
                        + "h. Visual programming e.g. Write programs using ‘drag and drop’ actions "
                        + "instead of typing code.\n"
                        + "i. Syntax assistance e.g., show hints as you type.\n"
                        + "j. Code generation e.g., to generate the code required "
                        + "by simply specifying which component/structure you want to implement.\n"
                        + "k. Extension. i.e. ability add more functionality to the IDE using plugins."),
                new StudentAnswer(""),
                new ModelAnswer("All. While all of these features may not be present in some IDEs, "
                        + "most do have these features in some form or other.")),
            new Exercise(new QuestionIndex("2.5.1"), new QuestionType("text"),
                new Question("Explain how the concepts of testing, test case, test failure, "
                        + "and defect are related to each other."),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("2.5.2"), new QuestionType("choice"),
                new Question("Regression testing is the automated re-testing of a software "
                        + "after it has been modified.\n"
                        + "\n"
                        + "a. True\n"
                        + "b. False\n"
                        + "c. Partially true"),
                new StudentAnswer(""),
                new ModelAnswer("c. Regression testing need not be automated but automation is highly recommended.")),
            new Exercise(new QuestionIndex("2.5.3"), new QuestionType("text"),
                new Question("Explain why and when you would do regression testing in a software project."),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("2.6.1"), new QuestionType("text"),
                new Question("What does RCS stand for?"),
                new StudentAnswer(""),
                new ModelAnswer("Revision Control Software.")),
            new Exercise(new QuestionIndex("2.6.2"), new QuestionType("text"),
                new Question("In the context of RCS, what is a Revision? Give an example."),
                new StudentAnswer(""),
                new ModelAnswer("Versions of a piece of information. For example, "
                        + "take a file containing program code. "
                        + "If you modify the code and save the file, "
                        + "you have a new version of that file.")),
            new Exercise(new QuestionIndex("2.6.3"), new QuestionType("choice"),
                new Question("Which of these is not considered a benefit of a typical RCS?\n"
                        + "a. Help a single user manage revisions of a single file\n"
                        + "b. Help a developer recover from a incorrect modification to a code file\n"
                        + "c. Makes it easier for a group of developers to collaborate on a project\n"
                        + "d. Manage the drift between multiple versions of your project\n"
                        + "e. Detect when multiple developers make incompatible changes to the same file\n"
                        + "f. All of them are benefits of RCS"),
                new StudentAnswer(""),
                new ModelAnswer("f.")),
            new Exercise(new QuestionIndex("2.6.4"), new QuestionType("text"),
                new Question("Suppose You are doing a team project with Tom, Dick, and Harry "
                        + "but those three have not even heard the term RCS. "
                        + "How do you explain RCS to them as briefly as possible, "
                        + "using the project as an example?"),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("2.6.5"), new QuestionType("text"),
                new Question("In the context of RCS, what is a repo?"),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),

            // week 3
            new Exercise(new QuestionIndex("3.1.1"), new QuestionType("choice"),
                new Question("Choose the correct statements\n"
                        + "\n"
                        + " a. Refactoring can improve understandability\n"
                        + " b. Refactoring can uncover bugs\n"
                        + " c. Refactoring can result in better performance\n"
                        + " d. Refactoring can change the number of methods/classes"),
                new StudentAnswer(""),
                new ModelAnswer("a b c d. (a, b, c) Although the primary aim of refactoring "
                        + "is to improve internal code structure, there are other secondary benefits. "
                        + "(d) Some refactorings result in adding/removing methods/classes.")),
            new Exercise(new QuestionIndex("3.1.2"), new QuestionType("text"),
                new Question("Do you agree with the following statement? Justify your answer.\n"
                        + "\n"
                        + "Statement: Whenever we refactor code to fix bugs, "
                        + "we need not do regression testing if the bug fix was minor."),
                new StudentAnswer(""),
                new ModelAnswer("DISAGREE. Even a minor change can have major repercussions on the system. "
                        + "We MUST do regression testing after each change, no matter how minor it is. "
                        + "Fixing bugs is technically not refactoring.")),
            new Exercise(new QuestionIndex("3.1.3"), new QuestionType("text"),
                new Question("Explain what is refactoring and why it is not the same as rewriting, "
                        + "bug fixing, or adding features."),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("3.1.4"), new QuestionType("choice"),
                new Question("‘Extract method’ and ‘Inline method’ refactorings\n"
                        + "\n"
                        + "a. are opposites of each other.\n"
                        + "b. sounds like opposites but they are not."),
                new StudentAnswer(""),
                new ModelAnswer("a.")),
            new Exercise(new QuestionIndex("3.2.1"), new QuestionType("choice"),
                new Question("What is the recommended approach regarding coding standards?\n"
                        + "\n"
                        + "a. Each developer should find a suitable coding standard and follow it in their coding.\n"
                        + "b. A developer should understand the importance of following a coding standard. "
                        + "However, there is no need to follow one.\n"
                        + "c. A developer should find out the coding standards currently used by the project "
                        + "and follow that closely.\n"
                        + "d. Coding standards are lame. Real programmers develop their own individual styles."),
                new StudentAnswer(""),
                new ModelAnswer("c.")),
            new Exercise(new QuestionIndex("3.2.2"), new QuestionType("text"),
                new Question("What is the aim of using a coding standard? How does it help?"),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("3.2.3"), new QuestionType("choice"),
                new Question("According to the given Java coding standard, which one of these is not a good name?\n"
                        + "\n"
                        + "a. integer variable name: totalPeople\n"
                        + "b. boolean variable name: checkWeight\n"
                        + "c. method name (returns integer): getPeopleCount\n"
                        + "d. method name (returns boolean): isValidAddress\n"
                        + "e. String variable name: description"),
                new StudentAnswer(""),
                new ModelAnswer("b. checkWeight is an action. "
                        + "Naming variables as actions makes the code harder to follow. "
                        + "isWeightValid may be a better name.")),
            new Exercise(new QuestionIndex("3.3.1"), new QuestionType("choice"),
                new Question("Putting all details in one place can create lengthy methods, "
                        + "but it is preferred over creating many small methods "
                        + "because it makes the code easier to understand.\n"
                        + "\n"
                        + "a. True\n"
                        + "b. False"),
                new StudentAnswer(""),
                new ModelAnswer("b. False. If you are using abstraction properly, "
                        + "you DON’T need to see all details to understand something. "
                        + "The whole point of using abstraction is to be able to understand things "
                        + "without knowing as little details as possible. "
                        + "This is why we recommend single level of abstraction per method and top-down coding.")),
            new Exercise(new QuestionIndex("3.3.2"), new QuestionType("choice"),
                new Question("What are the drawbacks of trying to optimize code too soon?\n"
                        + "\n"
                        + "a. We may not know which parts are the real performance bottleneck\n"
                        + "b. When we optimize code manually, it becomes harder for the compiler to optimize\n"
                        + "c. Optimizing can complicate code\n"
                        + "d. Optimizing can lead to more error-prone code"),
                new StudentAnswer(""),
                new ModelAnswer("All.")),
            new Exercise(new QuestionIndex("3.3.3"), new QuestionType("choice"),
                new Question("This is a common saying among programmers\n"
                        + "\n"
                        + "a. Make it fast, make it right, make it work\n"
                        + "b. Make it work, make it right, make it fast\n"
                        + "c. Make it fast, make it right, now make it faster"),
                new StudentAnswer(""),
                new ModelAnswer("b.")),
            new Exercise(new QuestionIndex("3.6.1"), new QuestionType("choice"),
                new Question("In general, comments should describe,\n"
                        + "\n"
                        + "a. WHAT the code does\n"
                        + "b. WHY the code does something\n"
                        + "c. HOW the code does something"),
                new StudentAnswer(""),
                new ModelAnswer("a b. How the code does something should be apparent from the code itself. "
                        + "However, comments can help the reader in describing WHAT and WHY aspects of the code.")),

            // week 4
            new Exercise(new QuestionIndex("4.1.1"), new QuestionType("choice"),
                new Question("Choose the correct statements about models.\n"
                        + "\n"
                        + "a. Models are abstractions.\n"
                        + "b. Models can be used for communication.\n"
                        + "c. Models can be used for analysis of a problem.\n"
                        + "d. Generating models from code is useless.\n"
                        + "e. Models can be used as blueprints for generating code."),
                new StudentAnswer(""),
                new ModelAnswer("a b c e. Models generated from code can be used for understanding, analysing, "
                        + "and communicating about the code.")),
            new Exercise(new QuestionIndex("4.1.2"), new QuestionType("text"),
                new Question("Explain how models (e.g. UML diagrams) can be used in a class project."),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("4.2.1"), new QuestionType("choice"),
                new Question("A) Choose the correct statements\n"
                        + "\n"
                        + "a. OO is a programming paradigm\n"
                        + "b. OO guides us in how to structure the solution\n"
                        + "c. OO is mainly an abstraction mechanism\n"
                        + "d. OO is a programming language\n"
                        + "e. OO is modeled after how the objects in real world work"),
                new StudentAnswer(""),
                new ModelAnswer("a b c e. While many languages support the OO paradigm, OO is not a language itself.")),
            new Exercise(new QuestionIndex("4.2.2"), new QuestionType("choice"),
                new Question("Choose the correct statements\n"
                        + "\n"
                        + "a. Java and C++ are OO languages\n"
                        + "b. C language follows the Functional Programming paradigm\n"
                        + "c. Java can be used to write procedural code\n"
                        + "d. Prolog follows the Logic Programming paradigm"),
                new StudentAnswer(""),
                new ModelAnswer("a c d. C follows the procedural paradigm. "
                        + "Yes, we can write procedural code using OO languages e.g., AddressBook-level1.")),
            new Exercise(new QuestionIndex("4.2.3"), new QuestionType("choice"),
                new Question("OO is a higher level mechanism than the procedural paradigm.\n"
                        + "\n"
                        + "a. True\n"
                        + "b. False"),
                new StudentAnswer(""),
                new ModelAnswer("a. True. Procedural languages work at simple data structures (e.g., integers, arrays) "
                        + "and functions level. Because an object is an abstraction over data+related functions, "
                        + "OO works at a higher level.")),
            new Exercise(new QuestionIndex("4.2.4"), new QuestionType("choice"),
                new Question("Choose the correct statement\n"
                        + "\n"
                        + "a. An object is an encapsulation because it packages data and behavior into one bundle.\n"
                        + "b. An object is an encapsulation because it lets us think in terms of higher level concepts "
                        + "such as Students rather than student-related functions and data separately."),
                new StudentAnswer(""),
                new ModelAnswer("a. The second statement should be: An object is an abstraction encapsulation "
                        + "because it lets ...")),
            new Exercise(new QuestionIndex("4.5.1"), new QuestionType("choice"),
                new Question("Which are benefits of exceptions?\n"
                        + "+\n"
                        + " a. Exceptions allow us to separate normal code from error handling code.\n"
                        + " b. Exceptions can prevent problems that happen in the environment.\n"
                        + " c. Exceptions allow us to handle in one location an error raised in another location."),
                new StudentAnswer(""),
                new ModelAnswer("a c. Exceptions cannot prevent problems in the environment. "
                        + "They can only be used to handle and recover from such problems.")),
            new Exercise(new QuestionIndex("4.6.1"), new QuestionType("text"),
                new Question("Show (in UML notation) an enumeration called WeekDay "
                        + "to use when the value can only be Monday ... Friday."),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("4.7.1"), new QuestionType("text"),
                new Question("In the context of RCS, what is the branching? What is the need for branching?"),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("4.7.2"), new QuestionType("text"),
                new Question("In the context of RCS, what is the merging branches? "
                        + "How can it lead to merge conflicts?"),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
                
            // week 5
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
                
            // week 6
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
                
            // week 7
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
                
            // week 8
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
                
            // week 9
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
                
            // week 10
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex(""), new QuestionType(""),
                new Question(""),
                new StudentAnswer(""),
                new ModelAnswer("")),
                
            // week 11
            new Exercise(new QuestionIndex("11.1.1"), new QuestionType("choice"),
                new Question("What is the main difference between a class diagram and and an OO domain model?\n"
                        + "\n"
                        + "a. One is about the problem domain while the other is about the solution domain.\n"
                        + "b. One has more classes than the other.\n"
                        + "c. One shows more details than the other.\n"
                        + "d. One is a UML diagram, while the other is not a UML diagram."),
                new StudentAnswer(""),
                new ModelAnswer("a. Both are UML diagrams, and use the class diagram notation. "
                        + "While it is true that often a class diagram may have more classes and more details, "
                        + "the main difference is that the OO domain model describes the problem domain "
                        + "while the class diagram describes the solution.")),
            new Exercise(new QuestionIndex("11.3.1"), new QuestionType("text"),
                new Question("Here are some common elements of a design pattern: "
                        + "Name, Context, Problem, Solution, Anti-patterns (optional), Consequences (optional), "
                        + "other useful information (optional).\n"
                        + "\n"
                        + "Using similar elements, describe a pattern that is not a design pattern. "
                        + "It must be a pattern you have noticed, not a pattern already documented by others. "
                        + "You may also give a pattern not related to software.\n"
                        + "\n"
                        + "Some examples:\n"
                        + "- A pattern for testing textual UIs.\n"
                        + "- A pattern for striking a good bargain at a mall such as Sim-Lim Square."),
                new StudentAnswer(""),
                new ModelAnswer("No suggested answer.")),
            new Exercise(new QuestionIndex("11.4.1"), new QuestionType("choice"),
                new Question("Applying the heuristics covered so far, we can determine the precise number of "
                        + "test cases required to test any given SUT effectively.\n"
                        + "\n"
                        + "a. True\n"
                        + "b. False"),
                new StudentAnswer(""),
                new ModelAnswer("b. False. These heuristics are, well, heuristics only. "
                        + "They will help you to make better decisions about test case design. "
                        + "However, they are speculative in nature (especially, when testing in black-box fashion) "
                        + "and cannot give you precise number of test cases.")),
            new Exercise(new QuestionIndex("11.4.2"), new QuestionType("choice"),
                new Question("Which of these contradict the heuristics recommended "
                        + "when creating test cases with multiple inputs?\n"
                        + "\n"
                        + "a. All invalid test inputs must be tested together.\n"
                        + "b. It is ok to combine valid values for different inputs.\n"
                        + "c. No more than one invalid test input should be in a given test case.\n"
                        + "d. Each valid test input should appear at least once "
                        + "in a test case that doesn’t have any invalid inputs."),
                new StudentAnswer(""),
                new ModelAnswer("a. If you test all invalid test inputs together, "
                        + "you will not know if each one of the invalid inputs are handled correctly by the SUT. "
                        + "This is because most SUTs return an error message "
                        + "upon encountering the first invalid input.")),
            new Exercise(new QuestionIndex("11.6.1"), new QuestionType("choice"),
                new Question("Choose the correct statements about agile processes.\n"
                        + "\n"
                        + "a. They value working software over comprehensive documentation.\n"
                        + "b. They value responding to change over following a plan.\n"
                        + "c. They may not be suitable for some type of projects.\n"
                        + "d. XP and Scrum are agile processes."),
                new StudentAnswer(""),
                new ModelAnswer("a b c d.")),
            new Exercise(new QuestionIndex("11.7.1"), new QuestionType("choice"),
                new Question("Choose the correct statements about the unified process.\n"
                        + "\n"
                        + "a. It was conceived by the three amigos who also created UML.\n"
                        + "b. The Unified process requires the use of UML.\n"
                        + "c. The Unified process is actually a process framework rather than a fixed process.\n"
                        + "d. The Unified process can be iterative and incremental"),
                new StudentAnswer(""),
                new ModelAnswer("a c d. Although UP was created by the same three amigos who created UML, "
                        + "the UP does not require UML.")),

                // week 12
        };
    }

}
