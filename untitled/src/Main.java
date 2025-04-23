

public class Main {
    public static void main(String[] args) {
        try {
            // Faz uma pergunta à API sobre uma introdução para um RPG
            String intro = gpt.enviarPergunta(
                    "faça uma introdução a um rpg com no máximo 100 caracteres por linha em pt-br"
            );
            // Faz outra pergunta à API sobre o nome de uma espada lendária
            String espada = gpt.enviarPergunta(
                    "Você é o monstro e foi atacado pelo Rafeal que é um mago com 12 de dano, Como você é um boss de elite com 200 de vida fale de acordo com a sua altura e orgulho, e ataque ele com uma força que passe a impressão de que você é implacavel, humilhe-o e diga o nome e dano do ataque,seja curto e objetivo, não passar de 100 carcateres por linha quebre ela e a frase tem que ser em pt-br  CUIDADO COM ERROS DE PORTUGUES "
            );

            // Exibe a introdução de forma animada no terminal
            System.out.println("\n--- Introdução ---\n");
            TextoImerso.mostrarComoDigitando(intro);

            // Exibe o nome da espada de forma animada no terminal
            System.out.println("\n--- Espada Lendária ---\n");
            TextoImerso.mostrarComoDigitando(espada);

        } catch (Exception e) {
            e.printStackTrace(); // Caso ocorra algum erro, imprime a exceção
        }

    }
}