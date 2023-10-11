package br.com.fiap.petshop.domain.entity.servico;

import br.com.fiap.petshop.domain.entity.animal.Animal;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SERVICO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TP_SERVICO")
public  class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SERVICO")
    @SequenceGenerator(name = "SQ_SERVICO", sequenceName = "SQ_SERVICO", allocationSize = 1)
    @Column(name = "ID_SERVICO")
    private Long id;

    private BigDecimal valor;

    @JsonbDateFormat
    @Column(name = "DT_ABERTURA")
    private LocalDateTime abertura = LocalDateTime.now();

    @JsonbDateFormat
    @Column(name = "DT_AUTORIZACAO")
    private LocalDateTime autorizacao;

    @JsonbDateFormat
    @Column(name = "DT_CONCLUSAO")
    private LocalDateTime conclusao;

    @Column(name = "DS_SERVICO")
    private String descricao;

    @Column(name = "OBS_SERVICO")
    private String observacao;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ANIMAL",
            referencedColumnName = "ID_ANIMAL",
            foreignKey = @ForeignKey(name = "TB_SERVICO_FK_ANIMAL")
    )
    private Animal animal;

    protected Servico() {
    }

    protected Servico(Long id, BigDecimal valor, LocalDateTime abertura, LocalDateTime autorizacao, LocalDateTime conclusao, String descricao, String observacao, Animal animal) {
        this.id = id;
        this.valor = valor;
        this.abertura = abertura;
        this.autorizacao = autorizacao;
        this.conclusao = conclusao;
        this.descricao = descricao;
        this.observacao = observacao;
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public Servico setId(Long id) {
        this.id = id;
        return this;
    }



    public BigDecimal getValor() {
        return valor;
    }

    public Servico setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public Servico setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
        return this;
    }

    public LocalDateTime getAutorizacao() {
        return autorizacao;
    }

    public Servico setAutorizacao(LocalDateTime autorizacao) {
        this.autorizacao = autorizacao;
        return this;
    }

    public LocalDateTime getConclusao() {
        return conclusao;
    }

    public Servico setConclusao(LocalDateTime conclusao) {
        this.conclusao = conclusao;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Servico setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getObservacao() {
        return observacao;
    }

    public Servico setObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }


    public Animal getAnimal() {
        return animal;
    }

    public Servico setAnimal(Animal animal) {
        this.animal = animal;
        return this;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", valor=" + valor +
                ", abertura=" + abertura +
                ", autorizacao=" + autorizacao +
                ", conclusao=" + conclusao +
                ", descricao='" + descricao + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}