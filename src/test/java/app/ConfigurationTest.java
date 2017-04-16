package app;

import app.config.Configuration;
import app.config.ConfigurationLoader;
import app.view.Resolution;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import util.RuntimeFileWriter;

import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;
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
        File emptyFile = new File(folder.getRoot(), "test.properties");
        Configuration.use(new ConfigurationLoader(emptyFile));

        // when
        boolean debug = Configuration.isDebug();
        Resolution resolution = Configuration.getResolution();
        boolean fullscreen = Configuration.isFullscreen();

        // then
        assertEquals(false, debug); // from default
        assertEquals(1920, resolution.getWidth()); // from default
        assertEquals(1080, resolution.getHeight()); // from default
        assertEquals(true, fullscreen); // from default
    }

    @Test
    public void shouldLoadConfigurationFromFile() {
        // when
        File fullscreenFile = newFile("test.properties", "fullscreen=false");
        ConfigurationLoader configurationLoader = new ConfigurationLoader(fullscreenFile);

        // then
        Configuration.use(configurationLoader);

        assertEquals(false, Configuration.isDebug()); // from default
        assertEquals(1920, Configuration.getResolution().getWidth()); // from default
        assertEquals(1080, Configuration.getResolution().getHeight()); // from default
        assertEquals(false, Configuration.isFullscreen()); // from file
    }

    @Test
    public void shouldSetPropertiesFromArguments() {
        // given
        String[] args = {"debug=true", "resolution=1366x768", "fullscreen=true"};
        File file = newFile("test.properties", "fullscreen=false");

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(file, args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(true, Configuration.isDebug()); // from args
        assertEquals(1366, Configuration.getResolution().getWidth()); // from args
        assertEquals(768, Configuration.getResolution().getHeight()); // from args
        assertEquals(true, Configuration.isFullscreen()); // from args
    }

    @Test
    public void shouldNotSetPropertiesFromArgumentsAndLetDefaultValues() {
        // given
        String[] args = {" "};
        File file = newFile("test.properties", "fullscreen=false");

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(file, args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(false, Configuration.isDebug()); //from default
        assertEquals(1920, Configuration.getResolution().getWidth()); //from default
        assertEquals(1080, Configuration.getResolution().getHeight()); //from default
        assertEquals(false, Configuration.isFullscreen()); //from file
    }

    @Test
    public void shouldTakePartialValuesFileArgsDefault() {
        // given
        String[] args = {"debug=true"};
        File file = newFile("test.properties", "resolution=1050x750");

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(file, args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(true, Configuration.isDebug()); // from args
        assertEquals(1050, Configuration.getResolution().getWidth()); //from file
        assertEquals(750, Configuration.getResolution().getHeight()); //from file
        assertEquals(true, Configuration.isFullscreen()); // from default
    }

    private File newFile(String filename, String... content) {
        try {
            File file = folder.newFile(filename);
            RuntimeFileWriter writer = new RuntimeFileWriter(file);
            asList(content).forEach(writer::write);
            writer.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
