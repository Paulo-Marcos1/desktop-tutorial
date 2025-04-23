import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONObject;

public class gpt {
    // Chave da API fornecida pelo serviço OpenRouter
    private static final String API_KEY = "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"; // Substitua com a sua chave API
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions"; // Endpoint da API

    // Método que envia uma pergunta para a API e retorna a resposta
    public static String enviarPergunta(String pergunta) throws IOException {
        // Cria a URL do endpoint da API
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configurações da requisição HTTP
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY); // Define a chave da API no cabeçalho
        connection.setRequestProperty("Content-Type", "application/json"); // Define que o conteúdo é JSON
        connection.setRequestProperty("HTTP-Referer", "https://seuapp.com"); // Referer (pode ser o URL do seu app)
        connection.setRequestProperty("X-Title", "MeuTesteJava"); // Título de identificação da requisição
        connection.setDoOutput(true); // Habilita o envio de dados no corpo da requisição

        // Formata o corpo da requisição em JSON com a pergunta do usuário
        String jsonInput = """
                    {
                      "model": "mistralai/mistral-7b-instruct",
                      "messages": [
                        { "role": "user", "content": "%s" }
                      ]
                    }
                """.formatted(pergunta);

        // Envia os dados JSON para a API
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8); // Converte o JSON em bytes
            os.write(input, 0, input.length); // Escreve os bytes no corpo da requisição
        }

        // Captura o código de resposta HTTP da API
        int code = connection.getResponseCode();
        // Se a resposta for OK, lê o conteúdo, caso contrário, lê o erro
        InputStream is = code < HttpURLConnection.HTTP_BAD_REQUEST
                ? connection.getInputStream()
                : connection.getErrorStream();

        // Lê a resposta da API linha por linha
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line.trim());
        }

        // Converte a resposta JSON para um objeto JSON
        JSONObject json = new JSONObject(response.toString());
        JSONArray choices = json.getJSONArray("choices");
        // Pega a primeira opção de resposta da API
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");

        // Retorna o conteúdo da resposta, substituindo "\\n" por quebras de linha reais
        return content.replace("\\n", "\n");
    }
}