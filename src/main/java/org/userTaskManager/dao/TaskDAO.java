package org.userTaskManager.dao;

import org.userTaskManager.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TaskDAO {

    private final EntityManagerFactory emf;

    public TaskDAO() {
        emf = Persistence.createEntityManagerFactory("task-persistence-unit");
    }

    public void addTask(Task task) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(task);
        em.getTransaction().commit();
        em.close();
    }

    public void markAsComplete(Long taskId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Task task = em.find(Task.class, taskId);
        if (task != null) {
            task.setCompleted(true);
            em.merge(task);
        }

        em.getTransaction().commit();
        em.close();
    }

    public void removeTaskById(Long taskId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Task task = em.find(Task.class, taskId);
        if (task != null) {
            em.remove(task);
        }

        em.getTransaction().commit();
        em.close();
    }

    public Task getTaskById(Long taskId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Task task = em.find(Task.class, taskId);
        em.close();
        return task;
    }

    public List<Task> getTaskList() {
        EntityManager em = emf.createEntityManager();
        List<Task> tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        em.close();
        return tasks;
    }

}
