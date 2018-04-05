package seedu.progresschecker.logic.parser;

import static seedu.progresschecker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.progresschecker.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.progresschecker.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.progresschecker.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.progresschecker.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.progresschecker.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.progresschecker.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.progresschecker.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static seedu.progresschecker.logic.commands.CommandTestUtil.MAJOR_DESC_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.MAJOR_DESC_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.progresschecker.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.progresschecker.logic.commands.CommandTestUtil.USERNAME_DESC_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.USERNAME_DESC_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_USERNAME_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.YEAR_DESC_BOB;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.progresschecker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.progresschecker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.progresschecker.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.progresschecker.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.progresschecker.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.Test;

import seedu.progresschecker.commons.core.index.Index;
import seedu.progresschecker.logic.commands.EditCommand;
import seedu.progresschecker.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.progresschecker.model.person.Email;
import seedu.progresschecker.model.person.GithubUsername;
import seedu.progresschecker.model.person.Major;
import seedu.progresschecker.model.person.Name;
import seedu.progresschecker.model.person.Phone;
import seedu.progresschecker.model.person.Year;
import seedu.progresschecker.model.tag.Tag;
import seedu.progresschecker.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_NAME_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_PHONE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_EMAIL_CONSTRAINTS); // invalid email
        //@@author EdwardKSG
        assertParseFailure(parser, "1" + INVALID_USERNAME_DESC,
                GithubUsername.MESSAGE_USERNAME_CONSTRAINTS); // invalid username
        assertParseFailure(parser, "1" + INVALID_MAJOR_DESC, Major.MESSAGE_MAJOR_CONSTRAINTS); // invalid major
        assertParseFailure(parser, "1" + INVALID_YEAR_DESC, Year.MESSAGE_YEAR_CONSTRAINTS); // invalid year
        //@@author
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_TAG_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                Phone.MESSAGE_PHONE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC,
                Phone.MESSAGE_PHONE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_TAG_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_TAG_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_TAG_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + INVALID_USERNAME_DESC
                 + VALID_MAJOR_AMY + VALID_PHONE_AMY + VALID_YEAR_AMY, Name.MESSAGE_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND + USERNAME_DESC_AMY
                + EMAIL_DESC_AMY + MAJOR_DESC_AMY + YEAR_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withUsername(VALID_USERNAME_AMY)
                .withMajor(VALID_MAJOR_AMY).withYear(VALID_YEAR_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //@@author EdwardKSG
        // username
        userInput = targetIndex.getOneBased() + USERNAME_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withUsername(VALID_USERNAME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // major
        userInput = targetIndex.getOneBased() + MAJOR_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withMajor(VALID_MAJOR_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
        //@@author

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased()  + PHONE_DESC_AMY + MAJOR_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + MAJOR_DESC_AMY + EMAIL_DESC_AMY + YEAR_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND
                + USERNAME_DESC_AMY + USERNAME_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withUsername(VALID_USERNAME_BOB)
                .withMajor(VALID_MAJOR_BOB).withYear(VALID_YEAR_BOB).withTags(
                VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + USERNAME_DESC_BOB
                + MAJOR_DESC_BOB + YEAR_DESC_BOB + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withUsername(VALID_USERNAME_BOB).withMajor(VALID_MAJOR_BOB).withYear(VALID_YEAR_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}