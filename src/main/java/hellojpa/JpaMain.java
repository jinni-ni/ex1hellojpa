package hellojpa;

import org.h2.util.json.JSONValidationTargetWithoutUniqueKeys;
import org.hibernate.Hibernate;
import org.w3c.dom.ls.LSOutput;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1","street","1000"));

            member.getFavoritFoods().add("치킨");
            member.getFavoritFoods().add("족발");
            member.getFavoritFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1","street","10000"));
            member.getAddressHistory().add(new AddressEntity("old2","street2","20000"));
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============== START =====================");
            Member findMember = em.find(Member.class,member.getId());

            //findMember.getHomeAddress().setCity("newCity");
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZip()));


            //치킨 -> 한식
            findMember.getFavoritFoods().remove("치킨");
            findMember.getFavoritFoods().add("한식");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
//
//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try{
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Team team2 = new Team();
//            team.setName("teamB");
//            em.persist(team2);
//
//            Member member1 = new Member();
//            member1.setUsername("Hello1");
//            member1.setTeam(team);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("Hello2");
//            member2.setTeam(team2);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team",Member.class)
//                    .getResultList();
//
////            Member m = em.find(Member.class,member1.getId());
////            System.out.println("m = " + m.getTeam().getClass());
////
////            System.out.println("==============================");
////            System.out.println("teamName : " + m.getTeam().getName());
////            System.out.println("==============================");
////            Member member2 = new Member();
////            member2.setUsername("Hello2");
////            em.persist(member2);
////
////            em.flush();
////            em.clear();
////            //영속성 컨텍스트 초기화
////            Member m1 = em.find(Member.class, member1.getId());
////            Member m2 = em.getReference(Member.class, member2.getId());
////
////            System.out.println("m1, m2 :" + (m1.getClass()==m2.getClass()));
////            System.out.println("m1 :" + (m1 instanceof Member));
////            System.out.println("m2 :" + (m2 instanceof Member));
//
////            m2.getUsername();
////            System.out.println("isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(m2));
////            m2.getUsername();
////            Hibernate.initialize(m2); //강제 초기화
//            //em.find(Member.class,member.getId());
////            Member findMember = em.getReference(Member.class,member.getId());
////            System.out.println("before findMember = " + findMember.getClass());
////            System.out.println("findMember.id = " + findMember.getId());
////            System.out.println("findMember.getuserName = " + findMember.getUsername());
////            System.out.println("After findMember = " + findMember.getClass());
//
//
//            tx.commit();
//        } catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//
//        emf.close();
//    }
//    private static void printMember(Member member) {
//        System.out.println("member = " + member.getUsername());
//    }
//
//    private static void printMemberAndTeam(Member member){
//        String username = member.getUsername();
//        System.out.println("username = "+ username);
//
//        Team team = member.getTeam();
//        System.out.println("team = "+ team.getName());
//    }
//}

//
//
//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try{
//            Member member = em.find(Member.class,150L);
//            member.setName("ZZZZ");
//
//
////            Member member1 = new Member(150L,"A");
////            Member member2 = new Member(160L,"B");
////
////
////            em.persist(member1);
////            em.persist(member2);
////            System.out.println("======================");
//
//            // commit 시에 sql 이 날아감
//            // flush : 영속성 컨텍스트의 변경사항과 database 를 맞춤
//            tx.commit();
//        } catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//
//        emf.close();
//    }
//}

//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try{
//            Member findMember1 = em.find(Member.class,101L);
//            Member findMember2 = em.find(Member.class,101L);
//
//            //jpa가 영속 엔티티의 동일성을 보장
//            //같은 트랜잭션 안에서 이루어 져야 함
//            System.out.println("result = " + (findMember1 == findMember2));
//
//
//            // 쿼리가 날아감
//            tx.commit();
//        } catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//
//        emf.close();
//    }
//
//}

//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try{
//            // 비영속 상태
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//
//            System.out.println("=====BEFORE=====");
//            // 영속 상태
//            // 객체를 저장한 상태
//            em.persist(member);
//            // 영속 상태 해제
//            //em.detach(member);
//
//            // db 삭제
//            // em.remove(member);
//            System.out.println("=====AFTER=====");
//
//            // 1차 캐시에 저장되어 select 쿼리를 날리지 않음
//            em.find(Member.class,101L);
//            System.out.println("findMember.id = " + member.getId());
//            System.out.println("findMember.Name = " + member.getName());
//
//            // 쿼리를 날림
//            Member findMember2 = em.find(Member.class,100L);
//            // 1차 캐시에서 데이터 가져옴
//            Member findMember3 = em.find(Member.class,100L);
//
//
//            // 쿼리가 날아감
//            tx.commit();
//        } catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//
//        emf.close();
//    }
//
//}
//    public static void main(String[] args) {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        try {
//
//            List<Member> result = em.createQuery("select m from Member as m",Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result){
//                System.out.println("member.name = " + member.getName());
//            }
////            Customer customer = new Customer();
//
////            customer.setId(3);
////            customer.setName("LEE");
////            customer.setTel("333-333");
////            em.persist(customer);
//
////            Customer findCustomer = em.find(Customer.class,3);
////            System.out.println("CustomerId = " + findCustomer.getId());
////            System.out.println("CustomerName = " + findCustomer.getName());
////            System.out.println("CustomerTel = " + findCustomer.getTel());
//
////            Customer findCustomer = em.find(Customer.class,2);
////            em.remove(findCustomer);
//
////            Customer findCustomer = em.find(Customer.class,3);
////            findCustomer.setTel("555-3333");
//            tx.commit();
//
//        } catch(Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//
//        emf.close();
//
//    }
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
////        emf.close();
////    }
//}
