package br.com.fiap.petshop.domain.entity.animal;

import br.com.fiap.petshop.domain.entity.Sexo;
import br.com.fiap.petshop.domain.entity.servico.Servico;
import br.com.fiap.petshop.infra.security.entity.Pessoa;
import br.com.fiap.petshop.infra.security.entity.PessoaFisica;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;


@Entity
@Table(name = "TB_ANIMAL")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ANIMAL")
    @SequenceGenerator(name = "SQ_ANIMAL", sequenceName = "SQ_ANIMAL", allocationSize = 1)
    @Column(name = "ID_ANIMAL")
    private Long id;

    @Column(name = "NM_ANIMAL")
    private String nome;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;

    private String raca;

    @Column(name = "DS_ANIMAL")
    private String descricao;

    private String observacao;

    @Column(name = "TP_ANIMAL")
    private String tipo;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "DONO",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "TB_ANIMAL_FK_DONO")
    )
    private Pessoa dono;


//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "TB_ANIMAL_SERVICO",
//            joinColumns = {
//                    @JoinColumn(
//                            name = "ANIMAL",
//                            referencedColumnName = "ID_ANIMAL",
//                            foreignKey = @ForeignKey(name = "TB_ANIMAL_SERVICO_FK_ANIMAL")
//                    )
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(
//                            name = "SERVICO",
//                            referencedColumnName = "ID_SERVICO",
//                            foreignKey = @ForeignKey(name = "TB_ANIMAL_SERVICO_FK_SERVICO")
//                    )
//            }
//    )
//    private Set<Servico> servicos = new LinkedHashSet<>();


    public Animal() {
    }


    public Animal(String tipo) {
        this.tipo = tipo;
    }


    public Animal(Long id, String nome, Sexo sexo, LocalDate nascimento, String raca, String descricao, String observacao, String tipo, Pessoa dono) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.raca = raca;
        this.descricao = descricao;
        this.observacao = observacao;
        this.tipo = tipo;
        this.dono = dono;
    }

    public Long getId() {
        return id;
    }

    public Animal setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Animal setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Animal setSexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Animal setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public String getRaca() {
        return raca;
    }

    public Animal setRaca(String raca) {
        this.raca = raca;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Animal setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getObservacao() {
        return observacao;
    }

    public Animal setObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Animal setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Pessoa getDono() {
        return dono;
    }

    public Animal setDono(Pessoa dono) {
        this.dono = dono;
        return this;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", nascimento=" + nascimento +
                ", raca='" + raca + '\'' +
                ", descricao='" + descricao + '\'' +
                ", observacao='" + observacao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dono=" + dono +
                '}';
    }
}
