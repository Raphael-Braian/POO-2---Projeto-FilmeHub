
import java.util.Date;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.stream.Collectors;


class Conteudo{
    String titulo;
    int faixaEtaria;
    float avaliacaoMedia;
    List<Ator> atores;
    int id;
    //Considerar acrescentar uma variavel lista de atores//

    public Conteudo(String titulo, int faixaEtaria,
            float avaliacaoMedia, List<Ator> atores, int id)
    {
        this.titulo = titulo;
        this.faixaEtaria = faixaEtaria;
        this.avaliacaoMedia = avaliacaoMedia;
        this.atores = atores;
    }

    public String toString() {
        return titulo;
    }
}

class Filme extends Conteudo{
    int anoLancamento;
    int duracaoEstimada;
    Diretor diretor;

    public Filme(String titulo, int faixaEtaria, float avaliacaoMedia,
            int anoLancamento, int duracaoEstimada, 
            List<Ator> atores, Diretor diretor, int id)
    {
        super(titulo, faixaEtaria, avaliacaoMedia, atores, id);
        this.anoLancamento = anoLancamento;
        this.duracaoEstimada = duracaoEstimada;   
        this.diretor = diretor;
        diretor.filmesDirigidos.add(this);
    }

    public String toString() {
        return String.format("%s direção: (%d) %s",this.titulo, 
                diretor.id, diretor.nome);
    }

    public void listarAtores() {
        for (Ator i : this.atores) {
            System.out.println(i);
        }
    }
}
class Serie extends Conteudo{
    Date anoInicio;
    Date anoTermino;
    int qtdTemporadas;

    public Serie(String titulo, int faixaEtaria, 
            float avaliacaoMedia, Date anoInicio, Date anoTermino, int qtdTemporadas,
            List<Ator> atores, int id)
    {
        super(titulo, faixaEtaria, avaliacaoMedia, atores, id);
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
    int idade;
    String paisDeOrigem;
    int id; 

    public Individuo(String nome, Gender genero, int idade, String paisDeOrigem, int id)
    {
        this.nome = nome;
        this.genero = Gender.M;
        this.idade = idade;
        this.paisDeOrigem = paisDeOrigem;
        this.id = id;
    }
}

class Ator extends Individuo {

    List<Filme> filmes;

    Ator(String nome, Gender genero, int idade, String paisDeOrigem, int id) {
        super(nome, genero, idade, paisDeOrigem, id);
    }

    public String toString() {
        return String.format("(%d) %s, %s, %d anos", this.id, this.nome, 
                this.genero == Gender.M ? "Homem" : "Mulher", this.idade);
    }

}

class Diretor extends Individuo {

    List<Filme> filmesDirigidos;

    Diretor(String nome, Gender genero, int idade, String paisDeOrigem, int id) {
        super(nome, genero, idade, paisDeOrigem, id);
        this.filmesDirigidos = new ArrayList<Filme>();
    }

    public void mediaFilmes() {
        float media = 0;
        for (Filme filme : this.filmesDirigidos) {
            media += filme.avaliacaoMedia;
        }
        System.out.println(String.format("media da avaliação de filmes: %f", media));
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
// class Critico extends Usuario {
//
//     Critico(String nome, String senha, int idade, String email, Date registro) 
//     { 
//         super(nome, senha, idade, email, registro);
//     }
// }

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
    public void mostrarTodosFilmesAtores() {
        for (Filme filme : this.filmes) {
            System.out.println(filme);
            for (Ator ator : filme.atores) {
                System.out.println("\t" + ator);
                
            }
        }
    }
    public void filtrarPorIdade(int idade) {
        for (Filme filme : this.filmes) {
            if (filme.faixaEtaria < idade) {
                System.out.println(String.format("%s idade: %s",
                            filme.titulo, 
                            filme.faixaEtaria == 0 ? "livre" : filme.faixaEtaria
                            ));
            }
        }
    }

    public void diretorAtorFilmes(int diretor, int atorAlvo) {
        for (Filme filme : this.filmes) {
            if (filme.diretor.id != diretor) {
                continue;
            }
            for (Ator ator : filme.atores) {
                if (ator.id == atorAlvo) {
                    System.out.println(filme);
                }
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
        // System.out.println("hello world");

        var rand = new Random(1);

        // ler datasets
        var filmesCSV = lerCsv("filmes.csv");
        var atoresCSV = lerCsv("atores.csv");
        var diretoresCSV = lerCsv("diretores.csv");

        var atores = new ArrayList<Ator>();

        // salvar todos os filmes em uma lista
        for (int i = 1; i < atoresCSV.size(); i++)
        {
            String linha = atoresCSV.get(i);
            // separa linha pelas virgulas
            String[] colunas = linha.split(",");

            // formato do csv:
            // nome,genero,idade,pais
            String nome = colunas[0];
            Gender genero = colunas[1].equals("M") ? Gender.M : Gender.F;
            int idade = Integer.parseInt(colunas[2]);
            String pais = colunas[3];

    
            atores.add(new Ator(nome, genero, idade, pais, i));
        }

        var diretores = new ArrayList<Diretor>();

        for (int i = 1; i < diretoresCSV.size(); i++)
        {
            String linha = diretoresCSV.get(i);
            String[] colunas = linha.split(",");

            String nome = colunas[0];
            Gender genero = colunas[1].equals("M") ? Gender.M : Gender.F;
            int idade = Integer.parseInt(colunas[2]);
            String pais = colunas[3];
    
            diretores.add(new Diretor(nome, genero, idade, pais, i));
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
            int faixaEtaria = Integer.parseInt(colunas[3]);
            float avaliacao = Float.parseFloat(colunas[4]);

            var diretor = diretores.get(rand.nextInt(diretores.size() -1 ));

            Collections.shuffle(atores, rand);
            List<Ator> filmeAtores = atores.stream()
                .limit(rand.nextInt(atores.size()))
                .collect(Collectors.toList());
    
            filmes.add(new Filme(titulo, faixaEtaria, avaliacao, ano, duracao,
                        filmeAtores, diretor, i));
        }

        var rank = new FilmeRank(filmes);

        Scanner scan = new Scanner(System.in);
        int input;

        System.out.println("1 para mostrar atributos de classes");
        System.out.println("2 para selecionar metodos operacionas");

        System.out.print("input: ");
        input = scan.nextInt();
        System.out.println();
        switch (input) {
            case 1: // mostrar atributos de classes
                    System.out.println("selecionar classe (1 até 10): ");
                    System.out.print("input: ");
                    input = scan.nextInt();
                    System.out.println();
                    switch (input) {
                    }
                break;
            case 2: // selecionar métodos operacionais
                    System.out.println("selecionar metodo operacional:");
                    System.out.println("1 para mostrar todos os filmes junto com atores");
                    System.out.println("2 para filtrar filmes por idade");
                    System.out.println("3 para procurar quais filmes diretor fez com ator");
                    System.out.print("input: ");
                    input = scan.nextInt();
                    System.out.println();
                    switch (input) {
                        case 1:
                            System.out.println("todos os filmes e seus atores:");
                            rank.mostrarTodosFilmesAtores();
                            break;
                        case 2:
                            System.out.print("idade: ");
                            input = scan.nextInt();
                            System.out.println();
                            System.out.println("filmes para menores de " 
                                    + input + " anos:");
                            rank.filtrarPorIdade(input);
                            System.out.println("os filmes acima são para menores de " 
                                    + input + " anos");
                            break;
                        case 3:
                            System.out.print("id do diretor (1-249): ");
                            var diretor = scan.nextInt();
                            System.out.print(String.format("id do ator (1-%d): ", 
                                        atores.size()));
                            var ator = scan.nextInt();
                            System.out.println();
                            System.out.println("filmes que diretor fez com ator");
                            rank.diretorAtorFilmes(diretor, ator);
                            break;
                        default:
                            System.out.println("metodo não reconhecido: " + input);
                            System.exit(1);
                            break;
                    }
                break;
            default:
                System.out.println("metodo não reconhecido: " + input);
                System.exit(1);
        }

    }
}
