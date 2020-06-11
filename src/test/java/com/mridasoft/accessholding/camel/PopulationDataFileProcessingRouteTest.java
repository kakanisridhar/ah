package com.mridasoft.accessholding.camel;

import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

@CamelSpringBootTest
@SpringBootTest
class PopulationDataFileProcessingRouteTest {

    @Autowired
    protected CamelContext camelContext;

    @EndpointInject("mock:end")
    protected MockEndpoint mockEndpoint;

    @Produce("direct:start")
    protected ProducerTemplate start;

    @Test
    public void camelStarts() {
        assertEquals(ServiceStatus.Started, camelContext.getStatus());
        assertThat("Should have 2 routes", camelContext.getRoutes().size() == 2);
    }

    @Test
    public void testPopulationRecordProcessing() throws Exception {
        String ukXmlOut = loadFileContent("/Uk.xml");
        String germanyXmlOut = loadFileContent("/Germany.xml");
        mockEndpoint.expectedBodiesReceivedInAnyOrder(ukXmlOut,germanyXmlOut);
        
        start.sendBody(loadFileContent("/PopulationRecord1.csv"));

        MockEndpoint.assertIsSatisfied(camelContext);
    }

    private String loadFileContent(String fn) throws IOException {
        return IOUtils.toString(
                this.getClass().getResourceAsStream(fn),
                "UTF-8"
        );
    }
}