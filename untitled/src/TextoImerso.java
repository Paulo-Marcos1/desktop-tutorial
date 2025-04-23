public class TextoImerso {
    // Método para mostrar o texto "digitado" lentamente no terminal
    public static void mostrarComoDigitando(String texto) throws InterruptedException {
        // Percorre cada caractere do texto
        for (char c : texto.toCharArray()) {
            System.out.print(c); // Exibe o caractere
            System.out.flush(); // Garante que o caractere é mostrado imediatamente
            Thread.sleep(30); // Aguarda 25 milissegundos entre cada caractere
        }
        System.out.println(); // Adiciona uma linha em branco após o texto
    }
}
