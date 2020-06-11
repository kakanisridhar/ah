package com.mridasoft.accessholding.camel;

import com.mridasoft.accessholding.dto.CountryCityPopulation;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileNameProcessorTest {

    private final FileNameProcessor processor = new FileNameProcessor();

    @Test
    public void shouldSetFileNameAsCountryName() throws Exception {
        Exchange exchange = Mockito.mock(Exchange.class);
        Message message = Mockito.mock(Message.class);
        CountryCityPopulation popRecord = new CountryCityPopulation();
        popRecord.setCountry("UK");

        when(exchange.getIn()).thenReturn(message);
        when(message.getBody(CountryCityPopulation.class)).thenReturn(popRecord);

        processor.process(exchange);
        verify(message).setHeader(eq(Exchange.FILE_NAME), eq("UK.xml"));

    }

}