import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class LexicalAnalyzerTest {

    private static final String testResourcesDir = "./src/test/resources/";

    private LexicalAnalyzer lexicalAnalyzer;
    Constructor<LexicalAnalyzer> lexicalAnalyzerConstructor;

    {
        try {
            lexicalAnalyzerConstructor = LexicalAnalyzer.class.getDeclaredConstructor();
            lexicalAnalyzerConstructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckOperator1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckOperator", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, ">>=");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckOperator2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckOperator", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, ">>|");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckKeyword1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckKeyword", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, "string");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckKeyword2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckKeyword", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, "String");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckDelimiter1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckDelimiter", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, "\r\n");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckDelimiter2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckDelimiter", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, "{");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckDelimiter3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckDelimiter", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, "{{");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckDelimiter4() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckDelimiter", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, "\n\r");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckComments1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckComments", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, "/*");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckComments2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("CheckComments", String.class);
        method.setAccessible(true);
        boolean actual = (Boolean) method.invoke(lexicalAnalyzer, "///");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testParse1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("Parse", String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(lexicalAnalyzer, "PETR_AND_ANSAT_ARE_AWESOME_TEAM");
        String expected = "[Identifier -> \"PETR_AND_ANSAT_ARE_AWESOME_TEAM\"] ";
        assertEquals(expected, actual);
    }

    @Test
    public void testParse2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("Parse", String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(lexicalAnalyzer, "241441241");
        String expected = "[Numerical constant -> \"" + 241441241 + "\"] ";
        assertEquals(expected, actual);
    }

    @Test
    public void testParse3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("Parse", String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(lexicalAnalyzer, "using");
        String expected = "[Keyword -> \"using\"] ";
        assertEquals(expected, actual);
    }


    @Test
    public void testParse4() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("Parse", String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(lexicalAnalyzer, "=>");
        String expected = "[Operator -> \"=>\"] ";
        assertEquals(expected, actual);
    }

    @Test
    public void testParse5() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("Parse", String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(lexicalAnalyzer, ";");
        String expected = "[Delimiter -> \";\"] ";
        assertEquals(expected, actual);
    }

    @Test
    public void testParse6() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        lexicalAnalyzer = lexicalAnalyzerConstructor.newInstance();
        Method method = lexicalAnalyzer.getClass().getDeclaredMethod("Parse", String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(lexicalAnalyzer, "\r\n");
        String expected = "\r\n";
        assertEquals(expected, actual);
    }

    @Test
    public void testFullParse1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        File inputFile = new File(testResourcesDir + "test1.cs");
        File expectedFile = new File(testResourcesDir + "out1_expected.txt");

        String text = FileUtils.readFileToString(inputFile);

        String actual = "";
        String expected = FileUtils.readFileToString(expectedFile);

        LexicalAnalyzer analyzer = new LexicalAnalyzer(text);
        while (text != null) {
            text = StringUtils.strip(text, " \t");
            String nextToken = analyzer.GetNextLexicalAtom(text);
            text = analyzer.getInput();
            if (text != null) actual += nextToken;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testFullParse2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        File inputFile = new File(testResourcesDir + "test2.cs");
        File expectedFile = new File(testResourcesDir + "out2_expected.txt");

        String text = FileUtils.readFileToString(inputFile);

        String actual = "";
        String expected = FileUtils.readFileToString(expectedFile);

        LexicalAnalyzer analyzer = new LexicalAnalyzer(text);
        while (text != null) {
            text = StringUtils.strip(text, " \t");
            String nextToken = analyzer.GetNextLexicalAtom(text);
            text = analyzer.getInput();
            if (text != null) actual += nextToken;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testFullParse3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        File inputFile = new File(testResourcesDir + "test3.cs");
        File expectedFile = new File(testResourcesDir + "out3_expected.txt");

        String text = FileUtils.readFileToString(inputFile);

        String actual = "";
        String expected = FileUtils.readFileToString(expectedFile);

        LexicalAnalyzer analyzer = new LexicalAnalyzer(text);
        while (text != null) {
            text = StringUtils.strip(text, " \t");
            String nextToken = analyzer.GetNextLexicalAtom(text);
            text = analyzer.getInput();
            if (text != null) actual += nextToken;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testFullParse4() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        File inputFile = new File(testResourcesDir + "test4.cs");
        File expectedFile = new File(testResourcesDir + "out4_expected.txt");

        String text = FileUtils.readFileToString(inputFile);

        String actual = "";
        String expected = FileUtils.readFileToString(expectedFile);

        LexicalAnalyzer analyzer = new LexicalAnalyzer(text);
        while (text != null) {
            text = StringUtils.strip(text, " \t");
            String nextToken = analyzer.GetNextLexicalAtom(text);
            text = analyzer.getInput();
            if (text != null) actual += nextToken;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testFullParse5() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        File inputFile = new File(testResourcesDir + "test5.cs");
        File expectedFile = new File(testResourcesDir + "out5_expected.txt");

        String text = FileUtils.readFileToString(inputFile);

        String actual = "";
        String expected = FileUtils.readFileToString(expectedFile);

        LexicalAnalyzer analyzer = new LexicalAnalyzer(text);
        while (text != null) {
            text = StringUtils.strip(text, " \t");
            String nextToken = analyzer.GetNextLexicalAtom(text);
            text = analyzer.getInput();
            if (text != null) actual += nextToken;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testFullParse6() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        File inputFile = new File(testResourcesDir + "test6.cs");
        File expectedFile = new File(testResourcesDir + "out6_expected.txt");

        String text = FileUtils.readFileToString(inputFile);

        String actual = "";
        String expected = FileUtils.readFileToString(expectedFile);

        LexicalAnalyzer analyzer = new LexicalAnalyzer(text);
        while (text != null) {
            text = StringUtils.strip(text, " \t");
            String nextToken = analyzer.GetNextLexicalAtom(text);
            text = analyzer.getInput();
            if (text != null) actual += nextToken;
        }
        assertEquals(expected, actual);
    }

}