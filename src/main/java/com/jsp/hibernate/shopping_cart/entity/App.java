package com.jsp.hibernate.shopping_cart.entity;

import java.util.Scanner;

import com.jsp.hibernate.shopping_cart.repository.ShoppingCartRepository;

public class App 
{
	public static void main( String[] args )
	{
		ShoppingCartRepository shp = new ShoppingCartRepository();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the choices");
		int choice= scan.nextInt();
		switch(choice) {
		case 1:
			shp.addProducts();
			break;
		case 2:
			shp.addUserWithcart();
			break;
		case 3 :
			shp.addProductToCart();
			break;
		case 4 :
			shp.removeProductFromCart();
		case 5:
			shp. findAllProductsInCart();
		case 6:
			shp. findAllProductsInCart();
		case 7:
			shp.updateUserEmailAndMobileByUserId();
		case 8 :
			shp.updateProductPriceByProductId();

			break;
		default:
			System.out.println("Invalid choice!!");
		}
	}
}
