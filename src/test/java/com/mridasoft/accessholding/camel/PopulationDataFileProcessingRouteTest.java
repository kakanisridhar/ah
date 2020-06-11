package com.mridasoft.accessholding.camel;

import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@CamelSpringTest
@ContextConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PopulationDataFileProcessingRouteTest {

    @Autowired
    protected CamelContext camelContext;

    @EndpointInject("mock:end")
    protected MockEndpoint mockEndpoint;

    @Produce("direct:start")
    protected ProducerTemplate start;

    @Test
    public void testPopulationRecordProcessing() throws Exception {
        assertEquals(ServiceStatus.Started, camelContext.getStatus());
        mockEndpoint.expectedBodiesReceived("David");
        start.sendBody(loadFileContent("PopulationRecord1.csv"));

        MockEndpoint.assertIsSatisfied(camelContext);
    }

    private String loadFileContent(String fn) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fn).getFile());
        return FileUtils.readFileToString(file, "UTF-8");
    }
}