/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.remotedata.remotedatada.JPA;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.bhaduri.remotedata.remotedatada.JPA.exceptions.NonexistentEntityException;
import org.bhaduri.remotedata.remotedatada.JPA.exceptions.PreexistingEntityException;
import org.bhaduri.remotedata.remotedatada.entities.Remotedata;

/**
 *
 * @author sb
 */
public class RemotedataJpaController implements Serializable {

    public RemotedataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Remotedata remotedata) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(remotedata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRemotedata(remotedata.getId()) != null) {
                throw new PreexistingEntityException("Remotedata " + remotedata + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Remotedata remotedata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            remotedata = em.merge(remotedata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = remotedata.getId();
                if (findRemotedata(id) == null) {
                    throw new NonexistentEntityException("The remotedata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remotedata remotedata;
            try {
                remotedata = em.getReference(Remotedata.class, id);
                remotedata.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The remotedata with id " + id + " no longer exists.", enfe);
            }
            em.remove(remotedata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Remotedata> findRemotedataEntities() {
        return findRemotedataEntities(true, -1, -1);
    }

    public List<Remotedata> findRemotedataEntities(int maxResults, int firstResult) {
        return findRemotedataEntities(false, maxResults, firstResult);
    }

    private List<Remotedata> findRemotedataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Remotedata.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Remotedata findRemotedata(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Remotedata.class, id);
        } finally {
            em.close();
        }
    }

    public int getRemotedataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Remotedata> rt = cq.from(Remotedata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
