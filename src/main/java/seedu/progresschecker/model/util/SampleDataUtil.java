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
            new Exercise(new QuestionIndex("2.5.1"), new QuestionType("text"),
                new Question("Explain how the concepts of testing, test case, test failure"
                    + "and defect are related to each other."),
                new StudentAnswer(""),
                new ModelAnswer("")),
            new Exercise(new QuestionIndex("2.5.2"), new QuestionType("choice"),
                new Question("Regression testing is the automated re-testing"
                    + "of a software after it has been modified."),
                new StudentAnswer(""),
                new ModelAnswer("c. Regression testing need not be automated"
                    + "but automation is highly recommended.")),
            new Exercise(new QuestionIndex("2.5.3"), new QuestionType("text"),
                new Question("Explain why and when you would do regression testing in a software project."),
                new StudentAnswer(""),
                new ModelAnswer("")),

            // week 3
            new Exercise(new QuestionIndex("3.1.1"), new QuestionType("choice"),
                new Question("Choose the correct statements\n"
                    + "\n"
                    + "a. Refactoring can improve understandability\n"
                    + "b. Refactoring can uncover bugs\n"
                    + "c. Refactoring can result in better performance\n"
                    + "d. Refactoring can change the number of methods/classes"),
                new StudentAnswer(""),
                new ModelAnswer("a b c d. (a, b, c) Although the primary aim of refactoring is to improve"
                    + "internal code structure, there are other secondary benefits. (d) Some refactorings"
                    + "result in adding/removing methods/classes.")),
            new Exercise(new QuestionIndex("3.1.2"), new QuestionType("text"),
                new Question("Do you agree with the following statement? Justify your answer.\n"
                    + "\n"
                    + "Statement: Whenever we refactor code to fix bugs,"
                    + "we need not do regression testing if the bug fix was minor."),
                new StudentAnswer(""),
                new ModelAnswer("DISAGREE. Even a minor change can have major repercussions on the system."
                    + "We MUST do regression testing after each change, no matter how minor it is."
                    + "Fixing bugs is technically not refactoring.")),
            new Exercise(new QuestionIndex("3.1.3"), new QuestionType("text"),
                new Question("Explain what is refactoring and "
                    + "why it is not the same as rewriting, bug fixing, or adding features."),
                new StudentAnswer(""),
                new ModelAnswer("")),

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
                new ModelAnswer("a b c d. Models generated from code can be used for"
                    + "understanding, analysing, and communicating about the code.")),
            new Exercise(new QuestionIndex("4.1.2"), new QuestionType("text"),
                new Question("Explain how models (e.g. UML diagrams) can be used in a class project."),
                new StudentAnswer(""),
                new ModelAnswer("")),

            // week 5
            new Exercise(new QuestionIndex("5.6.1"), new QuestionType("choice"),
                new Question("Which one of these is recommended not to use in UML diagrams"
                    + "because it adds more confusion than clarity?\n"
                    + "\n"
                    + "a. Composition symbol\n"
                    + "b. Aggregation symbol"),
                new StudentAnswer(""),
                new ModelAnswer("b.")),

            // week 6
            new Exercise(new QuestionIndex("6.1.1"), new QuestionType("text"),
                new Question("Discuss pros and cons of developers testing their own code."),
                new StudentAnswer(""),
                new ModelAnswer("Pros:\n"
                    + "\n"
                    + "Can be done early (the earlier we find a bug, the cheaper it is to fix).\n"
                    + "Can be done at lower levels, for examples, at operation and class level"
                    + "(testers usually test the system at UI level).\n"
                    + "It is possible to do more thorough testing because developers know"
                    + "the expected external behavior as well as the internal structure of the component.\n"
                    + "It forces developers to take responsibility for their own work"
                    + "(they cannot claim that \"testing is the job of the testers\").\n"
                    + "Cons:\n"
                    + "\n"
                    + "A developer may unconsciously test only situations that he knows to work"
                    + "(i.e. test it too 'gently').\n"
                    + "A developer may be blind to his own mistakes (if he did not consider"
                    + "a certain combination of input while writing code,"
                    + "it is possible for him to miss it again during testing).\n"
                    + "A developer may have misunderstood what the SUT is supposed to do in the first place.\n"
                    + "A developer may lack the testing expertise.")),
            new Exercise(new QuestionIndex("6.1.2"), new QuestionType("choice"),
                new Question("The cost of fixing a bug goes down as we reach the product release.\n"
                    + "\n"
                    + "a. True\n"
                    + "b. False"),
                new StudentAnswer(""),
                new ModelAnswer("b. False. The cost goes up over time.")),
            new Exercise(new QuestionIndex("6.1.3"), new QuestionType("text"),
                new Question("Explain why early testing by developers is important."),
                new StudentAnswer(""),
                new ModelAnswer("")),

            // week 7
            new Exercise(new QuestionIndex("7.2.1"), new QuestionType("choice"),
                new Question("Choose the correct statement\n"
                    + "\n"
                    + "a. The architecture of a system should be simple enough for all team members to understand it.\n"
                    + "b. The architecture is primarily a high-level design of the system.\n"
                    + "c. The architecture is usually decided by the project manager.\n"
                    + "d. The architecture can contain details private to a component."),
                new StudentAnswer(""),
                new ModelAnswer("a b. Not (c) because Architecture is usually designed by the Architect."
                    + "Not (d) because \"... private details of elements—details having to do solely with"
                    + "internal implementation—are not architectural.\"")),

            // week 8
            new Exercise(new QuestionIndex("8.4.1"), new QuestionType("text"),
                new Question("Explain the link (if any) between regressions and coupling."),
                new StudentAnswer(""),
                new ModelAnswer("When the system is highly-coupled, the risk of regressions is higher too"
                    + "e.g. when component A is modified, all components ‘coupled’"
                    + "to component A risk ‘unintended behavioral changes’.")),

            // week 9
            new Exercise(new QuestionIndex("9.2.1"), new QuestionType("choice"),
                new Question("Which one of these is least related to how OO programs achieve polymorphism?\n"
                    + "\n"
                    + "a. substitutability\n"
                    + "b. dynamic binding\n"
                    + "c. operation overloading\n"
                    + "d. interfaces\n"
                    + "e. abstract classes"),
                new StudentAnswer(""),
                new ModelAnswer("c. Operation overriding is the one that is related, not operation overloading."
                    + "Interfaces and abstract classes, although not required,"
                    + "can be used in achieving polymorphism.")),

            // week 10
            new Exercise(new QuestionIndex("10.1.3"), new QuestionType("choice"),
                new Question("Pick the odd one out.\n"
                    + "\n"
                    + "a. Law of Demeter.\n"
                    + "b. Don’t add people to a late project.\n"
                    + "c. Don’t talk to strangers.\n"
                    + "d. Principle of least knowledge.\n"
                    + "e. Coupling."),
                new StudentAnswer(""),
                new ModelAnswer("b. Law of Demeter, which aims to reduce coupling,"
                    + "is also known as ‘Don’t talk to strangers’ and ‘Principle of least knowledge’.")),

            // week 11
            new Exercise(new QuestionIndex("11.1.2"), new QuestionType("choice"),
                new Question("What is the main difference between a class diagram and and an OO domain model?\n"
                    + "\n"
                    + "a. One is about the problem domain while the other is about the solution domain.\n"
                    + "b. One has more classes than the other.\n"
                    + "c. One shows more details than the other.\n"
                    + "d. One is a UML diagram, while the other is not a UML diagram."),
                new StudentAnswer(""),
                new ModelAnswer("a. Both are UML diagrams, and use the class diagram notation."
                    + "While it is true that often a class diagram may have more classes and more details,"
                    + "the main difference is that the OO domain model describes the problem domain"
                    + "while the class diagram describes the solution.")),

            // week 12
            new Exercise(new QuestionIndex("12.2.1"), new QuestionType("choice"),
                new Question("Google Calendar belongs to which category of cloud computing services?\n"
                    + "\n"
                    + " a. IaaS\n"
                    + " b. PaaS\n"
                    + " c. SaaS"),
                new StudentAnswer(""),
                new ModelAnswer("c. It is a software as a service."
                    + "Instead of installing a calendar software on your desktop,"
                    + "we can use the Google Calendar software that lives ‘on the cloud’."))
        };
    }

}
