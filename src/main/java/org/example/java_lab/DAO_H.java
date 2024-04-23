package org.example.java_lab;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DAO_H extends DAO {
    @Override
    public void add(Point x) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(x);
        tx1.commit();
        session.close();
    }

    @Override
    public ArrayList<Point> getPoints() {
        allPoints.clear();
        List<Point> res = (List<Point>) HibernateSessionFactoryUtil.
                getSessionFactory().openSession().createQuery("From Point").list();
        allPoints.addAll(res);
        return allPoints;
    }
}
