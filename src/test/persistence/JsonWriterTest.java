package persistence;

import model.Canvas;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Canvas canvas = new Canvas();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // pass

        }
    }

    @Test
    void testWriterGeneralCanvas() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCanvas.json");
            writer.open();
            writer.write(expectedCanvas);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCanvas.json");
            Canvas testCanvas = reader.read();

            checkCanvasInstructors(expectedCanvas, testCanvas);
            checkCanvasStudents(expectedCanvas, testCanvas);
            checkCanvasCourses(expectedCanvas, testCanvas);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
