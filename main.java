
import java.util.Date;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
// import java.util.Scanner;
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

class Ator extends Individuo {

    Ator(String nome, Gender genero, Date nascimento, String paisDeOrigem) {
        super(nome, genero, nascimento, paisDeOrigem);
    }

}

class Diretor extends Individuo {

    List<Filme> filmesDirigidos;

    Diretor(String nome, Gender genero, Date nascimento, String paisDeOrigem) {
        super(nome, genero, nascimento, paisDeOrigem);
        this.filmesDirigidos = new ArrayList<Filme>();
    }

}

class Usuario {
    String nome;
    String senha;
    String email;
    int idade;
    Date registro;
    List<Comentario> comentarios;
    List<Avaliacao> avaliacoes;
    List<Review> reviews;

    Usuario(String nome, String senha, int idade, String email, Date registro)
    {
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.email = email;
        this.registro = registro;
        this.comentarios = new ArrayList<Comentario>();
        this.avaliacoes = new ArrayList<Avaliacao>();
        this.reviews = new ArrayList<Review>();
    }

    public String toString() {
        return String.format("nome: %s idade: %d email: %s", 
                this.nome, this.idade, this.email
                );
    }

}

class Edicao {
    Moderador moderador;
    Conteudo alvo;
    String diferenca;

    Edicao(Moderador moderador, Conteudo alvo, String diferenca) {
        this.moderador = moderador;
        this.alvo = alvo;
        this.diferenca = diferenca;
    }
}

class Moderador extends Usuario {
    List<Edicao> historicoEdicoes;

    Moderador(String nome, String senha, int idade, String email, Date registro)
    {
        super(nome, senha, idade, email, registro);
        this.historicoEdicoes = new ArrayList<Edicao>();
    }
}

// o que por aqui?
class Critico extends Usuario {

    Critico(String nome, String senha, int idade, String email, Date registro) 
    { 
        super(nome, senha, idade, email, registro);
    }
}

class Avaliacao {
    Filme filme;
    Usuario usuario;
    float nota;

    Avaliacao(Filme filme, Usuario usuario, float nota) {
        this.filme = filme;
        this.usuario = usuario;
        this.nota = nota;
    }
}

class Postagem {
    Usuario usuario;
    String texto;
    Date data;

    Postagem(Usuario usuario, String texto, Date data) {
        this.usuario = usuario;
        this.texto = texto;
        this.data = data;
    }
}

class Comentario extends Postagem {
    Review review;

    Comentario(Usuario usuario, String texto, Date data, Review review) {
        super(usuario, texto, data);
        this.review = review;
    }
}

class Review extends Postagem {
    float avaliacao;
    List<Comentario> comentarios;

    Review(Usuario usuario, String texto, Date data, float avaliacao) {
        super(usuario, texto, data);
        this.avaliacao = avaliacao;
        this.comentarios = new ArrayList<Comentario>();
    }
}

interface Rank {
    public void filtrarPorIdade(int idade);
}

class FilmeRank implements Rank {
    List<Filme> filmes;

    FilmeRank(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }
    public void mostrarTodosFilmes() {
        for (Filme i : this.filmes) {
            System.out.println(i);
        }
    }
    public void filtrarPorIdade(int idade) {
        for (Filme i : this.filmes) {
            if (i.faixaEtaria < idade) {
                System.out.println(i);
            }
        }
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
            System.out.println("ERRO: arquivo não encontrado!");
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


        var filmesRank = new FilmeRank(filmes);

        filmesRank.filtrarPorIdade(12);
    }
}
