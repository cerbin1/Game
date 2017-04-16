package app;

import app.config.Configuration;
import app.config.ConfigurationLoader;
import app.view.Resolution;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    public void tearDown() {
        folder.delete();
    }

    @Test
    public void shouldGetDefaultConfiguration() {
        // given
        File createdFile = new File(folder.getRoot(), "test.properties");
        Configuration.use(new ConfigurationLoader(createdFile));

        // when
        boolean debug = Configuration.isDebug();
        Resolution resolution = Configuration.getResolution();
        boolean fullscreen = Configuration.isFullscreen();

        // then
        assertEquals(false, debug);
        assertEquals(1920, resolution.getWidth());
        assertEquals(1080, resolution.getHeight());
        assertEquals(true, fullscreen);
    }

    @Test
    public void shouldLoadConfigurationFromFile() {
        // when
        File createdFile = newFile("test.properties");
        ConfigurationLoader configurationLoader = new ConfigurationLoader(createdFile);

        // then
        Configuration.use(configurationLoader);

        assertEquals(false, Configuration.isDebug());
        assertEquals(1920, Configuration.getResolution().getWidth());
        assertEquals(1080, Configuration.getResolution().getHeight());
        assertEquals(true, Configuration.isFullscreen());
    }

    @Test
    public void shouldSetPropertiesFromArguments() {
        // given
        String[] args = {"debug=true", "resolution=1366x768", "fullscreen=true"};
        File file = newFile("test.properties");

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(file, args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(true, Configuration.isDebug());
        assertEquals(1366, Configuration.getResolution().getWidth());
        assertEquals(768, Configuration.getResolution().getHeight());
        assertEquals(true, Configuration.isFullscreen());
    }

    @Test
    public void shouldNotSetPropertiesFromArgumentsAndLetDefaultValues() {
        // given
        String[] args = {" ", " ", " ", " "};
        File file = newFile("test.properties");

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(file, args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(false, Configuration.isDebug());
        assertEquals(1920, Configuration.getResolution().getWidth());
        assertEquals(1080, Configuration.getResolution().getHeight());
        assertEquals(true, Configuration.isFullscreen());
    }

    private File newFile(String filename) {
        try {
            return folder.newFile(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
