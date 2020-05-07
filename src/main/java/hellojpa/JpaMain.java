package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result){
                System.out.println("member.name = " + member.getName());
            }
//            Customer customer = new Customer();

//            customer.setId(3);
//            customer.setName("LEE");
//            customer.setTel("333-333");
//            em.persist(customer);

//            Customer findCustomer = em.find(Customer.class,3);
//            System.out.println("CustomerId = " + findCustomer.getId());
//            System.out.println("CustomerName = " + findCustomer.getName());
//            System.out.println("CustomerTel = " + findCustomer.getTel());

//            Customer findCustomer = em.find(Customer.class,2);
//            em.remove(findCustomer);

//            Customer findCustomer = em.find(Customer.class,3);
//            findCustomer.setTel("555-3333");
            tx.commit();

        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//        EntityManager em = emf.createEntityManager();
//        //transaction 단위
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//
//        try {
//            // insert member
////            Member member = new Member();
////            member.setId(2L);
////            member.setName("HelloB");
////            em.persist(member);
//            // select member
////            Member findMember = em.find(Member.class,1L);
////            System.out.println("findMember.id = " + findMember.getId());
////            System.out.println("findMember.name = " + findMember.getName());
//            // delete member
////            Member findMember = em.find(Member.class,1L);
////            em.remove(findMember);
//            // modify member
//            Member findMember = em.find(Member.class,1L);
//            findMember.setName("HelloJPA");
//
//            tx.commit();
//        } catch(Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//
//        emf.close();
//    }
}
