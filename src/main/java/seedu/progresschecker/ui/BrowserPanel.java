package seedu.progresschecker.ui;

import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.progresschecker.MainApp;
import seedu.progresschecker.commons.core.LogsCenter;
import seedu.progresschecker.commons.events.ui.PageLoadChangedEvent;
import seedu.progresschecker.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.progresschecker.model.person.Person;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String SEARCH_PAGE_URL =
            "https://se-edu.github.io/addressbook-level4/DummySearchPage.html?name=";
    public static final String MODULE_PAGE_URL =
            "https://nus-cs2103-ay1718s2.github.io/";
    public static final String OUTCOMES_PAGE_PATH =
            "website/schedule/week";
    public static final String OUTCOMES_PAGE_DOCUMENT =
            "/outcomes.html";
    public static final int CURRENT_WEEK = 2;

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private WebView browser;

    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    // TODO: replace with a default page for each person when API has been implemented
    private void loadPersonPage(Person person) {
        loadOutcomesPage(CURRENT_WEEK);
    }

    // TODO: update loading to use API to pull data from Google Tasks
    // TODO: also check which person is currently selected to display the corresponding data
    public void loadOutcomesPage(int index) {
        loadPage(MODULE_PAGE_URL + OUTCOMES_PAGE_PATH + index + OUTCOMES_PAGE_DOCUMENT);
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection().person);
    }

    @Subscribe
    private void handlePageLoadChangedEvent(PageLoadChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadOutcomesPage(event.getPageIndex());
    }
}
