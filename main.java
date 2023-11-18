
import java.util.Date;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


class Conteudo{
    String titulo;
    String genero;
    String faixaEtaria;
    boolean avaliacaoMedia;
    //Considerar acrescentar uma variavel lista de atores//

    public Conteudo(String titulo, String genero, String faixaEtaria,
            boolean avaliacaoMedia)
    {
        this.titulo = titulo;
        this.genero = genero;    //Construtor de Conteudo//
        this.faixaEtaria = faixaEtaria;
        this.avaliacaoMedia = avaliacaoMedia;
    }
}

class Filme extends Conteudo{
    Date anoLancamento;
    boolean bilheteria;
    boolean duracaoEstimada;

    public Filme(String titulo, String genero, String faixaEtaria, boolean avaliacaoMedia,
            Date anoLancamento, boolean bilheteria, boolean duracaoEstimada)
    {
        super(titulo, genero, faixaEtaria, avaliacaoMedia);
        this.anoLancamento = anoLancamento;
        this.bilheteria = bilheteria;
        this.duracaoEstimada = duracaoEstimada;   
    }
}
class Serie extends Conteudo{
    Date anoInicio;
    Date anoTermino;
    int qtdTemporadas;

    public Serie(String titulo, String genero, String faixaEtaria, 
            boolean avaliacaoMedia, Date anoInicio, Date anoTermino, int qtdTemporadas)
    {
        super(titulo, genero, faixaEtaria, avaliacaoMedia);
        this.anoInicio = anoInicio;
        this.anoTermino = anoTermino;
        this.qtdTemporadas = qtdTemporadas;
    }
}
//-------------- Pessoas (ou Famosos) ------------------------------------//

enum Gender{M,F};

class Individuo{
    String nome;
    Gender genero;
    Date nascimento;
    String paisDeOrigem;

    public Individuo(String nome, Gender genero, Date nascimento, String paisDeOrigem)
    {
        this.nome = nome;
        this.genero = Gender.M;
        this.nascimento = nascimento;
        this.paisDeOrigem = paisDeOrigem;
    }
}

class Usuario {
    String nome;
    String senha;
    String email;
    int idade;
    Date registro;

    Usuario(String nome, String senha, int idade, String email, Date registro)
    {
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.email = email;
        this.registro = registro;
    }

    public String toString() {
        return String.format("nome: %s idade: %d email: %s", 
                this.nome, this.idade, this.email
                );
    }

}
class main {

    public static void main(String[] args) {
        System.out.println("hello world");

        // ler arquivo dataset.csv por linhas e salvar nesta array
        List<String> linhas = new ArrayList<String>();
        try {
            File file = new File("usuarios.csv");
            FileReader filereader = new FileReader(file);
            BufferedReader br = new BufferedReader(filereader);
            String line;

            while ((line = br.readLine()) != null) {
                linhas.add(line);
            }


        } catch (Exception e) {
            System.out.println("erro! arquivo não encontrado");
            e.printStackTrace();
        }

        List<Usuario> usuarios = new ArrayList<Usuario>();

        // separar cada linha por colunas e salvar todos os candidatos em uma lista
        for (int i = 1; i < linhas.size(); i++)
        {
            String linha = linhas.get(i);
            // separa linha pelas virgulas
            String[] colunas = linha.split(",");

            String nome = colunas[0];
            String senha = colunas[1];
            int idade = Integer.parseInt(colunas[2]);
            String email = colunas[3];
            Date registro = new Date((long) Integer.parseInt(colunas[4]) * 1000);

            var usuario = new Usuario(nome, senha, idade, email, registro);

            // salvar candidato
            usuarios.add(usuario);
        }

        for (Usuario i : usuarios) {
            System.out.println(i);
        }

        // Individuo foo = new Individuo();
    }
}
