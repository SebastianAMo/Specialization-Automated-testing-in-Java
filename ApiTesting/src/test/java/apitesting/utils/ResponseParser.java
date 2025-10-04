package apitesting.utils;

import apitesting.models.apiresponse.GeneralResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseParser {

    public static String extractJsonFromHtml(String htmlResponse) {
        return htmlResponse
                .replaceAll("<html>", "")
                .replaceAll("</html>", "")
                .replaceAll("<body>", "")
                .replaceAll("</body>", "")
                .trim();
    }

    public static GeneralResponse parseResponse(String htmlResponse) {
        String jsonContent = ResponseParser.extractJsonFromHtml(htmlResponse);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonContent);
            int responseCode = jsonNode.get("responseCode").asInt();
            String message = jsonNode.get("message").asText();
            return new GeneralResponse(responseCode, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON from response body", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while parsing response", e);
        }
    }


    public static <T> T parseResponse(String htmlResponse, Class<T> responseType) {
        String jsonContent = ResponseParser.extractJsonFromHtml(htmlResponse);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonContent, responseType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON from response body to " + responseType.getSimpleName(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while parsing response to " + responseType.getSimpleName(), e);
        }
    }
}
