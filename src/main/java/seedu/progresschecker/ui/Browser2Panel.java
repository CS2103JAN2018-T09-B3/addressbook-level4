package seedu.progresschecker.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.progresschecker.commons.core.LogsCenter;
import seedu.progresschecker.commons.events.ui.LoadBarEvent;

/**
 * The second Browser Panel of the App.
 */
public class Browser2Panel extends UiPart<Region> {

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private WebView browser;

    public Browser2Panel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);
        registerAsAnEventHandler(this);
    }

    //@@author EdwardKSG
    /**
     * Loads the HTML file which contains task information.
     */
    public void loadBarPage(String content) {
        loadPageViaString(content);
    }

    public void loadPageViaString(String content) {
        Platform.runLater(() -> browser.getEngine().loadContent(content));
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    private void handleLoadBarEvent(LoadBarEvent event)  {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadBarPage(event.getContent());
    }
    //@@author
}

