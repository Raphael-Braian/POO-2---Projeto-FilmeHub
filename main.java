


// FilmesHub
// trabalho feito por Daniel Augusto e Raphael Braian
// para o curso de Ciências da Computação da Universidade Vila Velha.
// CC3N

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


// interface para mostrar atributos
interface atributos {
    public void atributos();
}


class Conteudo{
    String titulo;
    int faixaEtaria;
    float avaliacaoMedia;
    List<Ator> atores;
    int id;

    public Conteudo(String titulo, int faixaEtaria,
            float avaliacaoMedia, List<Ator> atores, int id)
    {
        this.titulo = titulo;
        this.faixaEtaria = faixaEtaria;
        this.avaliacaoMedia = avaliacaoMedia;
        this.atores = atores;
        this.id = id;
    }

    public String toString() {
        return titulo;
    }
}

class Filme extends Conteudo implements atributos{
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
        for (Ator ator : atores) {
            ator.filmes.add(this);
        }
    }

    public String toString() {
        return String.format("\"%s\" direção: (%d) %s",this.titulo, 
                diretor.id, diretor.nome);
    }

    public void listarAtores() {
        for (Ator i : this.atores) {
            System.out.println(i);
        }
    }

    public void atributos() {
        System.out.println(this.titulo);
        System.out.println("direção :" + this.diretor.nome);
        System.out.println("nota: " + this.avaliacaoMedia);
        System.out.println(String.format("faixa etária: %s", 
                this.faixaEtaria == 0 ? "livre" : this.faixaEtaria
                ));
        System.out.println("ano de lançamento: " + this.anoLancamento);
        System.out.println("duração em minutos: " + this.duracaoEstimada);
    }
}
class Serie extends Conteudo implements atributos{
    int anoInicio;
    int anoTermino;
    int qtdTemporadas;

    public Serie(String titulo, int faixaEtaria, 
            float avaliacaoMedia, int anoInicio, int anoTermino, int qtdTemporadas,
            List<Ator> atores, int id)
    {
        super(titulo, faixaEtaria, avaliacaoMedia, atores, id);
        this.anoInicio = anoInicio;
        this.anoTermino = anoTermino;
        this.qtdTemporadas = qtdTemporadas;
    }

    public void atributos() {
        System.out.println(this.titulo);
        System.out.println("nota: " + this.avaliacaoMedia);
        System.out.println(String.format("faixa etária: %s", 
                this.faixaEtaria == 0 ? "livre" : this.faixaEtaria
                ));
        System.out.println("temporadas :" + this.qtdTemporadas);
        System.out.println("ano de inicio: " + this.anoInicio);
        System.out.println("ano de termino" + this.anoTermino);
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

class Ator extends Individuo implements atributos {

    List<Filme> filmes;

    Ator(String nome, Gender genero, int idade, String paisDeOrigem, int id) {
        super(nome, genero, idade, paisDeOrigem, id);
        this.filmes = new ArrayList<Filme>();
    }

    public String toString() {
        return String.format("(%d) %s, %s, %d anos", this.id, this.nome, 
                this.genero == Gender.M ? "Homem" : "Mulher", this.idade);
    }

    public void atributos() {
        System.out.println(this.nome);
        System.out.println(this.genero == Gender.M ? "Homem" : "Mulher");
        System.out.println(this.idade + " anos");
        System.out.println("pais de origem: " + this.paisDeOrigem);
        System.out.println("id: " + this.id);
        System.out.println("filmes estreiados: ");
        for (Filme i : this.filmes) {
            System.out.println(i);
        }
    }

}

class Diretor extends Individuo implements atributos{

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

    public void atributos() {
        System.out.println(this.nome);
        System.out.println(this.genero == Gender.M ? "Homem" : "Mulher");
        System.out.println(this.idade + " anos");
        System.out.println("pais de origem: " + this.paisDeOrigem);
        System.out.println("id: " + this.id);
        System.out.println("filmes dirigidos: ");
        for (Filme i : this.filmesDirigidos) {
            System.out.println(i);
        }
    }

}

class Usuario implements atributos {
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

    public void atributos() {
        System.out.println(this.nome);
        System.out.println("senha: " + this.senha);
        System.out.println("idade: " + this.idade);
        System.out.println("data de registro: " + this.registro);
        for (Comentario comentario : this.comentarios) {
            System.out.println(comentario);
        }
        for (Avaliacao avaliacao : this.avaliacoes) {
            System.out.println(avaliacao);
        }
        for (Review review : this.reviews) {
            System.out.println(review);
        }
    }

}

// moderadores fazem edições em filmes e series
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

class Moderador extends Usuario implements atributos {
    List<Edicao> historicoEdicoes;

    Moderador(String nome, String senha, int idade, String email, Date registro)
    {
        super(nome, senha, idade, email, registro);
        this.historicoEdicoes = new ArrayList<Edicao>();
    }
    public void atributos() {
        System.out.println(this.nome);
        System.out.println("senha: " + this.senha);
        System.out.println("idade: " + this.idade);
        System.out.println("data de registro: " + this.registro);
        for (Edicao edicao : this.historicoEdicoes) {
            System.out.println(edicao);
        }
    }
}

// usuarios avaliam filmes e series
class Avaliacao implements atributos {
    Filme filme;
    Usuario usuario;
    float nota;

    Avaliacao(Filme filme, Usuario usuario, float nota) {
        this.filme = filme;
        this.usuario = usuario;
        this.nota = nota;
    }

    public void atributos() {
        System.out.println("filme: " + this.filme.titulo);
        System.out.println("usuario: " + this.usuario.nome);
        System.out.println("nota: " + this.nota);
    }

}

// classe abstrata que abranje alguns atributos de review e comentario
abstract class Postagem {
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

class Review extends Postagem implements atributos {
    float avaliacao;
    List<Comentario> comentarios;

    Review(Usuario usuario, String texto, Date data, float avaliacao) {
        super(usuario, texto, data);
        this.avaliacao = avaliacao;
        this.comentarios = new ArrayList<Comentario>();
    }

    public void atributos() {
        System.out.println(this.texto);
        System.out.println(this.usuario.nome);
        System.out.println(this.data);
        System.out.println("nota: " + this.avaliacao);
    }
}

// pensamos em criar um rank para series também mas acabou que ficamos sem tempo :)
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

    // ler csv
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

        // isso faz com que os resultados aleatorios sempre sejam os mesmos
        var rand = new Random(1);

        // ler todos os dados necessarios
        var filmesCSV = lerCsv("filmes.csv");
        var atoresCSV = lerCsv("atores.csv");
        var diretoresCSV = lerCsv("diretores.csv");

        // salvar todos os atores em uma lista
        var atores = new ArrayList<Ator>();

        for (int i = 1; i < atoresCSV.size(); i++)
        {
            String linha = atoresCSV.get(i);
            // separa linha pelas virgulas
            String[] colunas = linha.split(",");

            // formato do csv:
            // nome,genero,idade,pais
            String nome = colunas[0];
            // traduzir para enum
            Gender genero = colunas[1].equals("M") ? Gender.M : Gender.F;
            int idade = Integer.parseInt(colunas[2]);
            String pais = colunas[3];

    
            atores.add(new Ator(nome, genero, idade, pais, i));
        }

        var diretores = new ArrayList<Diretor>();

        // salvar todos os diretores em uma lista
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
            // csv dos filmes é separados por barras
            String[] colunas = linha.split("\\|");

            // formato do csv:
            // titulo|ano|duracao|idade|avaliacao
            String titulo = colunas[0];
            int ano = Integer.parseInt(colunas[1]);
            int duracao = Integer.parseInt(colunas[2]); // duração em minutos
            int faixaEtaria = Integer.parseInt(colunas[3]);
            float avaliacao = Float.parseFloat(colunas[4]);

            // inventar um diretor pro filme escolhendo algum diretor qualquer
            // da lista de diretores
            var diretor = diretores.get(rand.nextInt(diretores.size() -1 ));

            // inventar lista de atores 
            // isso embaralha a lista de atores e pega os N primeiros
            Collections.shuffle(atores, rand);
            List<Ator> filmeAtores = atores.stream()
                .limit(rand.nextInt(atores.size()))
                .collect(Collectors.toList());

            filmes.add(new Filme(titulo, faixaEtaria, avaliacao, ano, duracao,
                        filmeAtores, diretor, i));
        }

        // salvar todos os filmes nesse rank
        var rank = new FilmeRank(filmes);

        Scanner scan = new Scanner(System.in);
        int input;

        // objetos de demonstração
        var usuario = new Usuario("joãozinho123", "123456789", 12, 
                "jaobomzao@gmail.com", new Date((long) 1600745024 * 1000));
        var moderador = new Moderador("alberto_rosa32", "8dn28dj0", 31, 
                "alberto_rosa@hotmail.com", new Date((long) 1505744902 * 1000));
        var avaliacao = new Avaliacao(filmes.get(0), usuario, 10);
        var serie = new Serie("Breaking Bad", 16, 9.1f, 2010, 2014, 5, null, 1);
        var review = new Review(usuario, "achei o filme muito bom! nota 10!", 
                                new Date((long) 1600755257 * 1000), 10);


        System.out.println("1 para mostrar atributos de classes");
        System.out.println("2 para selecionar metodos operacionas");

        System.out.print("input: ");
        input = scan.nextInt();
        System.out.println();
        switch (input) {
            case 1: // mostrar atributos de classes
                    System.out.println("selecionar classe: ");
                    System.out.println("(1) filmes");
                    System.out.println("(2) series");
                    System.out.println("(3) ator");
                    System.out.println("(4) diretor");
                    System.out.println("(5) usuarios");
                    System.out.println("(6) moderador");
                    System.out.println("(7) avaliação");
                    System.out.println("(8) review");
                    System.out.print("input: ");
                    input = scan.nextInt();
                    System.out.println();
                    // esse é um exemplo bem abstrato (e gambiarra) de polimorfismo.
                    // todas os objetos nessa lista são diferentes e implementam
                    // a interface "atributos" que tem apenas um método que
                    // mostra os atributos de cada classe.
                    var classes = new atributos[]{
                        filmes.get(0),
                        serie,
                        atores.get(0),
                        diretores.get(0),
                        usuario,
                        moderador,
                        avaliacao,
                        review,
                    };
                    // caso input esteja alem do limite
                    if (input > classes.length) {
                        System.out.println("metodo não reconhecido: " + input);
                        System.exit(1);
                        break;
                    }
                    classes[input-1].atributos();
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
