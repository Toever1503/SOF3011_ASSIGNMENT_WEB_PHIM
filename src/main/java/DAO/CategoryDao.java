package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Category;
import utils.JpaUtils;

public class CategoryDao {
	private EntityManager enManager;

	public CategoryDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public List<Category> getAll() {
		String jpql = "SELECT c FROM Category c";
		TypedQuery<Category> query = enManager.createQuery(jpql, Category.class);

		List<Category> list = query.getResultList();
		return list.size() == 0 ? null : list;
	}

	public Category findById(Long id) {
		Category cat = enManager.find(Category.class, id);
		return cat;
	}

	public Category save(Category obj) {
		try {
			enManager.getTransaction().begin();
			System.out.println("category->" + enManager.getTransaction().hashCode());

			obj = enManager.merge(obj);
			enManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			enManager.getTransaction().rollback();
		}
		return obj;
	}

	public static void main(String[] args) {
//		findById(4l).getVideos().forEach(v->System.out.println(v.getId()+"-"+v.getTitle()));
	}
}
