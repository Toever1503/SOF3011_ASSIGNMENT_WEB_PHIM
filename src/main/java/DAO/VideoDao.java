package DAO;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;

import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.internal.SessionFactoryImpl.SessionBuilderImpl;
import org.hibernate.internal.SessionImpl;

import model.Category;
import model.Chars;
import model.Staff;
import model.Studio;
import model.Tag;
import model.Video;
import utils.JpaUtils;

public class VideoDao {
	private EntityManager enManager;

	public VideoDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public Video findByID(Long id) {
		Video video = enManager.find(Video.class, id);
		return video;
	}

	public Video save(Video obj) {
		try {
			enManager.getTransaction().begin();

			Set<Category> cats = new HashSet<Category>(); // make list cat
			obj.getCategores().forEach(cat -> {
				if (cat.getId() != null)
					cats.add(enManager.getReference(Category.class, cat.getId()));// add exist cat
				else
					cats.add(cat); // add new cat
			});

			Set<Staff> staffs = new HashSet<Staff>(); // make list staff
			obj.getStaffs().forEach(staff -> {
				if (staff.getId() != null)
					staffs.add(enManager.getReference(Staff.class, staff.getId())); // add exist staff
				else
					staffs.add(staff); // add new staff
			});

			Set<Studio> studios = new HashSet<Studio>();
			obj.getStudios().forEach(studio -> {
				if (studio.getId() != null)
					studios.add(enManager.getReference(Studio.class, studio.getId())); // add exist staff
				else
					studios.add(studio); // add new studio
			});

			Set<Chars> chars = new HashSet<Chars>();
			obj.getChars().forEach(chars1 -> {
				if (chars1.getId() != null)
					chars.add(enManager.getReference(Chars.class, chars1.getId())); // add exist staff
				else
					chars.add(chars1); // add new char
			});

			Set<Tag> tags = new HashSet<Tag>();
			obj.getTags().forEach(tag -> {
				if (tag.getId() != null)
					tags.add(enManager.getReference(Tag.class, tag.getId())); // add exist staff
				else
					tags.add(tag); // add new tag
			});

			obj.setCategores(cats); // set cat again
			obj.setStaffs(staffs); // set staff again
			obj.setStudios(studios);
			obj.setChars(chars);
			obj.setTags(tags);

			obj = enManager.merge(obj);
			enManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			enManager.getTransaction().rollback();
			obj = null;
			// TODO: handle exception
		}
		return obj;
	}

	public Video delete(Video obj) {
		try {
			enManager.getTransaction().begin();
			enManager.remove(enManager.contains(obj) ? obj : enManager.merge(obj));
			enManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			enManager.getTransaction().rollback();
			obj = null;
			// TODO: handle exception
		}
		return obj;
	}

	public List<Video> findAllByInclude(Object... include) {
		String sql = "SELECT v FROM Video v where ";
		for (Object object : include) {

		}
		return null;
	}

	public List<Video> findAllByPage(Integer from, Integer to) {
		String sql = "SELECT distinct v FROM Video v ORDER BY dateUpdate DESC";
		TypedQuery<Video> query = enManager.createQuery(sql, Video.class);
		query.setFirstResult(from);
		query.setMaxResults(to);

		List<Video> result = query.getResultList();
		return result.size() == 0 ? null : result;
	}

	public Integer findAllPage(Integer perPage) {
		// TODO Auto-generated method stub
		String jpql = "SELECT COUNT(id)/:perPage as total FROM Video v";
		Query query = enManager.createQuery(jpql);
		query.setParameter("perPage", Long.valueOf(perPage));

		Long result = (Long) query.getSingleResult();
		return result.intValue();
	}

	public List<Video> search(String keyword) {
		String sql = "SELECT distinct v FROM Video v where v.title like :search or v.titleJp like :search1 ORDER BY dateUpdate DESC";
		TypedQuery<Video> query = enManager.createQuery(sql, Video.class);
		query.setParameter("search", keyword);
		query.setParameter("search1", keyword);

		List<Video> result = query.getResultList();
		return result.size() == 0 ? null : result;
	}

	public static void main(String[] args) {
		EntityManager en = JpaUtils.getManager();
		en.createNativeQuery(null);
		

	}
}
