package br.com.fiap.petshop.domain.repository;

import br.com.fiap.petshop.domain.entity.animal.Animal;
import br.com.fiap.petshop.domain.entity.servico.Servico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ServicoRepository implements Repository<Servico, Long> {

    private static final AtomicReference<ServicoRepository> instance = new AtomicReference<>();
    private EntityManager manager;

    private ServicoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static ServicoRepository build(EntityManager manager) {
        ServicoRepository result = instance.get();
        if (Objects.isNull(result)) {
            ServicoRepository repo = new ServicoRepository(manager);
            if (instance.compareAndSet(null, repo)) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }


    @Override
    public List<Servico> findAll() {
        List<Servico> list = manager.createQuery("FROM Servico").getResultList();
        return list;
    }

    @Override
    public Servico findById(Long id) {
        Servico servico = manager.find(Servico.class, id);
        return servico;
    }

    @Override
    public List<Servico> findByTexto(String texto) {
        String jpql = "SELECT s FROM Servico s  where lower(s.descricao) LIKE CONCAT('%',lower(:nome),'%')";
        Query query = manager.createQuery(jpql);
        query.setParameter("nome", texto);
        List<Servico> list = query.getResultList();
        return list;
    }

    @Override
    public Servico persist(Servico servico) {
        manager.getTransaction().begin();
        manager.persist(servico);
        manager.getTransaction().commit();
        return servico;
    }


    @Override
    public Servico update(Servico servico) {
        manager.getTransaction().begin();
        servico = manager.merge(servico);
        manager.getTransaction().commit();

        return servico;
    }

    public boolean delete(Servico servico) {
        manager.getTransaction().begin();
        try {
            servico = manager.merge(servico);
            manager.remove(servico);
            manager.getTransaction().commit();
            return true; // Indica que a exclusão foi bem-sucedida
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false; // Indica que a exclusão falhou
        }
    }
}
