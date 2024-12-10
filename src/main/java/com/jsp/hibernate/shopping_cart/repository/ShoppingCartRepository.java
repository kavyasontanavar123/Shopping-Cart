package com.jsp.hibernate.shopping_cart.repository;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jsp.hibernate.shopping_cart.entity.Cart;
import com.jsp.hibernate.shopping_cart.entity.Product;
import com.jsp.hibernate.shopping_cart.entity.User;

public class ShoppingCartRepository {
	Scanner scan = new Scanner(System.in);
	Configuration cfg = new Configuration().configure().
			addAnnotatedClass(User.class ).addAnnotatedClass(Cart.class).addAnnotatedClass(Product.class);
	SessionFactory sf = cfg.buildSessionFactory();

	public void addProducts() {
		Product prod = new Product();
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the Product Id:");
		prod.setProductId(scan.nextInt());

		System.out.println("Enter the Product Name:");
		prod.setProductName(scan.next());

		System.out.println("Enter the Product Brand:");
		prod.setProductBrand(scan.next());

		System.out.println("Enter the Product Price:");
		prod.setProductPrice(scan.nextInt());
		Session session= sf.openSession();
		Transaction tran = session.beginTransaction();

		session.save(prod);

		tran.commit();
		session.close();

	}
	public  void addUserWithcart() {

		User user = new User();

		System.out.println("Enter the User Id:");
		user.setUserId(scan.nextInt());

		System.out.println("Enter the User Name:");
		user.setUserName(scan.next());

		System.out.println("Enter the User Email:");
		user.setUserEmail(scan.next());

		System.out.println("Enter the User Mobile:");
		user.setUserMobile(scan.nextLong());

		Cart cart= new Cart();

		System.out.println("Enter the Cart Id:");
		cart.setCartId(scan.nextInt());

		user.setCart(cart);

		Session session= sf.openSession();
		Transaction tran = session.beginTransaction();

		session.save(user);
		session.save(cart);


		tran.commit();
		session.close();

	}
	//	public void addProductToCart() {
	//		Session session= sf.openSession();
	//		Transaction tran = session.beginTransaction();
	//		
	//		Product product = session.get(Product.class, scan.nextInt()) ;
	//		if(product != null) {
	//			User user = session.get(User.class, scan.nextInt());
	//			if(user != null) {
	//			Cart cart =user.getCart();
	//			cart.getProducts().add(product);
	//			session.update(cart);
	//		}else {
	//			System.err.println("User not found!!");
	//		}
	//		}else {
	//			System.err.println("Product not found!!");
	//		}
	//		tran.commit();
	//		session.close();
	//		
	//		
	//	}
	public void addProductToCart() {
		Session session= sf.openSession();
		Transaction tran = session.beginTransaction();

		System.out.println("Enter cart ID:");
		int cartId = scan.nextInt();
		System.out.println("Enter product ID:");
		int productId = scan.nextInt();
		

		Cart cart = session.get(Cart.class, cartId);
		Product product = session.get(Product.class, productId);

		if (cart != null && product != null) {
			cart.getProducts().add(product);
			session.update(cart);
		} else {
			System.out.println("Cart or product not found.");
		}

		tran.commit();
		session.close();
	}
	public void removeProductFromCart() {
		Session session= sf.openSession();
		Transaction tran = session.beginTransaction();

		System.out.println("Enter cart ID:");
		int cartId = scan.nextInt();
		System.out.println("Enter product ID:");
		int productId = scan.nextInt();
		scan.nextLine(); 

		Cart cart = session.get(Cart.class, cartId);
		Product product = session.get(Product.class, productId);

		if (cart != null && product != null) {
			cart.getProducts().remove(product);
			session.update(cart);
		} else {
			System.out.println("Cart or product not found.");
		}

		tran.commit();
		session.close();
	}
	public void findAllProductsInCart() {
		Session session= sf.openSession();
		Transaction tran = session.beginTransaction();

		System.out.println("Enter cart ID:");
		int cartId = scan.nextInt();
		scan.nextLine(); 

		Cart cart = session.get(Cart.class, cartId);

		if (cart != null) {
			for (Product product : cart.getProducts()) {
				System.out.println("Product ID: " + product.getProductId());
				System.out.println("Product Name: " + product.getProductName());
				System.out.println("Product Brand: " + product.getProductBrand());
				System.out.println("Product Price: " + product.getProductPrice());
				System.out.println();
			}
		} else {
			System.out.println("Cart not found.");
		}

		tran.commit();
		session.close();
	}
	public void updateUserEmailAndMobileByUserId() {
		Session session= sf.openSession();
		Transaction tran = session.beginTransaction();

		System.out.println("Enter user ID:");
		int userId = scan.nextInt();
		scan.nextLine(); 

		System.out.println("Enter new email:");
		String newEmail = scan.nextLine();

		System.out.println("Enter new mobile number:");
		long newMobileNumber = scan.nextLong();

		User user = session.get(User.class, userId);


		user.setUserEmail(newEmail);
		user.setUserMobile(newMobileNumber);
		session.update(user);




		tran.commit();
		session.close();
	}
	public void updateProductPriceByProductId() {
		Session session= sf.openSession();
		Transaction tran = session.beginTransaction();

		System.out.println("Enter product ID:");
		int productId = scan.nextInt();

		System.out.println("Enter new price:");
		int newPrice = scan.nextInt();


		Product product = session.get(Product.class, productId);


		product.setProductPrice(newPrice);
		session.update(product);

		System.out.println("Product not found.");


		tran.commit();
		session.close();
	}


}
