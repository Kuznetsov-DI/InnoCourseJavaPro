package spring.web.lesson.client.handlers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import spring.web.lesson.exception.IntegrationProductClientException;

import java.io.IOException;

@Component
public class ProductClientResponseHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatusCode status = response.getStatusCode();
        if (status.is5xxServerError() || status.is4xxClientError()) {
            throw new IntegrationProductClientException("Integration with product service have error with code - " + status.value());
        }
    }
}
