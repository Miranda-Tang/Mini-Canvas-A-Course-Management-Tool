package persistence;

import model.Canvas;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Canvas canvas = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralCanvas() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCanvas.json");
        try {
            Canvas testCanvas = reader.read();
            checkCanvasInstructors(expectedCanvas, testCanvas);
            checkCanvasStudents(expectedCanvas, testCanvas);
            checkCanvasCourses(expectedCanvas, testCanvas);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
