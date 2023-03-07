package br.com.fiap.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;



public abstract class DAO<T> {
	private Class<T> clazz;

	private EntityManagerFactory entityManagerFactory;

	public DAO(Class<T> clazz) {
		this.clazz = clazz;
		this.entityManagerFactory = Persistence.createEntityManagerFactory("Fase01Capitulo05");
	}

	public T findById(Long id) {
		T entity = null;
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entity = entityManager.find(clazz, id);
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public void save(T entity) {
		EntityTransaction transaction = null;
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
			entityManager.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void update(T entity) {
		EntityTransaction transaction = null;
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(entity);
			transaction.commit();
			entityManager.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public boolean delete(T entity) {
		EntityTransaction transaction = null;
		boolean isDeleted = false;
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(entity);
			transaction.commit();
			isDeleted = true;
			entityManager.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return isDeleted;
}

	public boolean deleteById(Long id) {
	    T entity = findById(id);
	    if (entity != null) {
	       return delete(entity);
	       
	    }
	    return false;
	}

	public List<T> findAll() {
		List<T> entities = null;
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + clazz.getName() + " e", clazz);
			entities = query.getResultList();
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}
