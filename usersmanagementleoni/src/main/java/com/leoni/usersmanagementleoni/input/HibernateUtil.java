package com.leoni.usersmanagementleoni.input;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static com.leoni.usersmanagementleoni.input.ExcelReader.readExcelFile;


public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(EmployeeWorkingData.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
/*
    public void insertEmployeeData(List<EmployeeWorkingData> employeeList) {
        Session session = getSession();
        session.beginTransaction();

        for (EmployeeWorkingData employee : employeeList) {
            String hql = "FROM EmployeeWorkingData WHERE userLogin = :userLogin AND day = :day";
            Query query = session.createQuery(hql);
            query.setParameter("userLogin", employee.getUserLogin());
            query.setParameter("day", employee.getDay());

            List results = query.getResultList();

            if (results.isEmpty()) {
                session.save(employee);
            } else {
                System.out.println("Employee data already exists: " + employee.getUserLogin() + " on " + employee.getDay());
            }
        }

        session.getTransaction().commit();
        session.close();
    }*/
    public void insertEmployeeData(List<EmployeeWorkingData> employeeList) {
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();

            for (EmployeeWorkingData employee : employeeList) {
                String hql = "FROM EmployeeWorkingData WHERE userLogin = :userLogin AND day = :day";
                Query query = session.createQuery(hql);
                query.setParameter("userLogin", employee.getUserLogin());
                query.setParameter("day", employee.getDay());

                List results = query.getResultList();

                if (results.isEmpty()) {
                    session.save(employee);
                } else {
                    System.out.println("Employee data already exists: " + employee.getUserLogin() + " on " + employee.getDay());
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }


    public static void main(String[] args) {
        HibernateUtil util = new HibernateUtil();

        //String excelFilePath = "C:\\Users\\noura\\Downloads\\Maven_first_project_stage\\input xlsx file\\input\\src\\main\\java\\leoni\\file.xlsx";

        //List<EmployeeWorkingData> employeeList = readExcelFile(excelFilePath);

        //util.insertEmployeeData(employeeList);
    }
}
