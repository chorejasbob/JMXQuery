package com.outlyer.jmx.jmxquery.tests;

import com.outlyer.jmx.jmxquery.JMXMetric;
import com.outlyer.jmx.jmxquery.ParseError;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author dgildeh
 */

public class JMXParserTest {
    public JMXParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSlashesInsidePath() throws ParseError {
        String q = "foo.bar=Tomcat:type=DataSource,context=/,host=localhost,class=javax.sql.DataSource,name=\"jdbc/storage\"/numIdle";
        JMXMetric m = new JMXMetric(q);
        Assert.assertEquals("foo.bar", m.getMetric());
        Assert.assertEquals("Tomcat:type=DataSource,context=/,host=localhost,class=javax.sql.DataSource,name=\"jdbc/storage\"", m.getmBeanName());
        Assert.assertEquals("numIdle", m.getAttribute());
    }

    @Test
    public void testAttributeKey() throws ParseError {
        String q = "jvm.memory.heap.used=java.lang:type=Memory/HeapMemoryUsage/used";
        JMXMetric m = new JMXMetric(q);
        Assert.assertEquals("jvm.memory.heap.used", m.getMetric());
        Assert.assertEquals("java.lang:type=Memory", m.getmBeanName());
        Assert.assertEquals("HeapMemoryUsage", m.getAttribute());
        Assert.assertEquals("used", m.getAttributeKey());
    }
}