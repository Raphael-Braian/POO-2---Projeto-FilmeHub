
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
    int faixaEtaria;
    float avaliacaoMedia;
    //Considerar acrescentar uma variavel lista de atores//

    public Conteudo(String titulo, String genero, int faixaEtaria,
            float avaliacaoMedia)
    {
        this.titulo = titulo;
        this.genero = genero;
        this.faixaEtaria = faixaEtaria;
        this.avaliacaoMedia = avaliacaoMedia;
    }

    public String toString() {
        return titulo;
    }
}

class Filme extends Conteudo{
    int anoLancamento;
    boolean bilheteria;
    int duracaoEstimada;

    public Filme(String titulo, String genero, int faixaEtaria, float avaliacaoMedia,
            int anoLancamento, boolean bilheteria, int duracaoEstimada)
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

    public Serie(String titulo, String genero, int faixaEtaria, 
            float avaliacaoMedia, Date anoInicio, Date anoTermino, int qtdTemporadas)
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

    // le qualquer csv
    public static List<String> lerCsv(String arquivo) {
        List<String> linhas = new ArrayList<String>();
        try {
            File file = new File(arquivo);
            FileReader filereader = new FileReader(file);
            BufferedReader br = new BufferedReader(filereader);
            String line;

            while ((line = br.readLine()) != null) {
                linhas.add(line);
            }
        } catch (Exception e) {
            System.out.println("Erro: arquivo não encontrado!");
            e.printStackTrace();
        }
        return linhas;
    }

    public static void main(String[] args) {
        System.out.println("hello world");

        // ler datasets
        var usuariosCSV = lerCsv("usuarios.csv");
        var filmesCSV = lerCsv("filmes.csv");

        var usuarios = new ArrayList<Usuario>();

        // salvar todos os usuarios em uma lista
        for (int i = 1; i < usuariosCSV.size(); i++)
        {
            String linha = usuariosCSV.get(i);
            // separa linha pelas virgulas
            String[] colunas = linha.split(",");

            // formato do csv:
            // nome,idade,senha,email,registro
            String nome = colunas[0];
            String senha = colunas[1];
            int idade = Integer.parseInt(colunas[2]);
            String email = colunas[3];

            Date registro = new Date((long) Integer.parseInt(colunas[4]) * 1000);
            // O construtor de Date aceita unix timestamp em milisegundos para 
            // definir uma data.
            // unix timestamp é definido pelo numero de segundos desde primeiro 
            // de janeiro de 1970
            // https://www.unixtimestamp.com/index.php

            usuarios.add(new Usuario(nome, senha, idade, email, registro));
        }


        var filmes = new ArrayList<Filme>();

        // salvar todos os filmes em uma lista
        for (int i = 1; i < filmesCSV.size(); i++)
        {
            String linha = filmesCSV.get(i);
            // separa linha pelas virgulas
            String[] colunas = linha.split("\\|");

            // formato do csv:
            // titulo|ano|duracao|idade|avaliacao
            String titulo = colunas[0];
            int ano = Integer.parseInt(colunas[1]);
            int duracao = Integer.parseInt(colunas[2]); // duração em minutos
            int idade = Integer.parseInt(colunas[3]);
            float avaliacao = Float.parseFloat(colunas[4]);

    
            filmes.add(new Filme(titulo, "", idade, avaliacao, ano, false, duracao));
        }

        for (Filme i : filmes) {
            System.out.println(i);
        }
    }
}
