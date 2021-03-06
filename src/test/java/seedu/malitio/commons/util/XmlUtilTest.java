package seedu.malitio.commons.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.malitio.testutil.MalitioBuilder;
import seedu.malitio.testutil.TestUtil;
import seedu.malitio.commons.util.FileUtil;
import seedu.malitio.commons.util.XmlUtil;
import seedu.malitio.model.Malitio;
import seedu.malitio.storage.XmlSerializableMalitio;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class XmlUtilTest {

    private static final String TEST_DATA_FOLDER = FileUtil.getPath("src/test/data/XmlUtilTest/");
    private static final File EMPTY_FILE = new File(TEST_DATA_FOLDER + "empty.xml");
    private static final File MISSING_FILE = new File(TEST_DATA_FOLDER + "missing.xml");
    private static final File VALID_FILE = new File(TEST_DATA_FOLDER + "validMalitio.xml");
    private static final File TEMP_FILE = new File(TestUtil.getFilePathInSandboxFolder("tempMalitio.xml"));

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getDataFromFile_nullFile_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.getDataFromFile(null, Malitio.class);
    }

    @Test
    public void getDataFromFile_nullClass_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.getDataFromFile(VALID_FILE, null);
    }

    @Test
    public void getDataFromFile_missingFile_FileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.getDataFromFile(MISSING_FILE, Malitio.class);
    }

    @Test
    public void getDataFromFile_emptyFile_DataFormatMismatchException() throws Exception {
        thrown.expect(JAXBException.class);
        XmlUtil.getDataFromFile(EMPTY_FILE, Malitio.class);
    }

    @Test
    public void getDataFromFile_validFile_validResult() throws Exception {
        XmlSerializableMalitio dataFromFile = XmlUtil.getDataFromFile(VALID_FILE, XmlSerializableMalitio.class);
        assertEquals(9, dataFromFile.getFloatingTaskList().size());
        assertEquals(0, dataFromFile.getTagList().size());
    }

    @Test
    public void saveDataToFile_nullFile_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.saveDataToFile(null, new Malitio());
    }

    @Test
    public void saveDataToFile_nullClass_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.saveDataToFile(VALID_FILE, null);
    }

    @Test
    public void saveDataToFile_missingFile_FileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.saveDataToFile(MISSING_FILE, new Malitio());
    }

    @Test
    public void saveDataToFile_validFile_dataSaved() throws Exception {
        TEMP_FILE.createNewFile();
        XmlSerializableMalitio dataToWrite = new XmlSerializableMalitio(new Malitio());
        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        XmlSerializableMalitio dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableMalitio.class);
        assertEquals((new Malitio(dataToWrite)).toString(),(new Malitio(dataFromFile)).toString());
        //TODO: use equality instead of string comparisons

        MalitioBuilder builder = new MalitioBuilder(new Malitio());
        dataToWrite = new XmlSerializableMalitio(builder.withTask(TestUtil.generateSampleTaskData().get(0)).withTag("Friends").build());

        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableMalitio.class);
        assertEquals((new Malitio(dataToWrite)).toString(),(new Malitio(dataFromFile)).toString());
    }
}
