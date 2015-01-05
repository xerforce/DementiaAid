/**
 * 
 */
package com.example.androidhive;

/**
 * @author Jerry
 *
 */
public class Const {
	
	private static String PHPWebserviceIp="http://210.115.182.145";
	
	private static String PHPFolderPath="/php_projects/test/";
	
	private static final String url_all_products = PHPWebserviceIp+PHPFolderPath+"get_all_products.php";
	
	// single product url
	private static final String url_product_detials = PHPWebserviceIp+PHPFolderPath+"get_product_details.php";

	// url to update product
	private static final String url_update_product = PHPWebserviceIp+PHPFolderPath+"update_product.php";
	
	// url to delete product
	private static final String url_delete_product = PHPWebserviceIp+PHPFolderPath+"delete_product.php";
	
	// url to create new product
	private static final String url_create_product = PHPWebserviceIp+PHPFolderPath+"create_product.php";


	public static String getUrlAllProducts() {
		return url_all_products;
	}


	public static String getUrlProductDetials() {
		return url_product_detials;
	}


	public static String getUrlUpdateProduct() {
		return url_update_product;
	}


	public static String getUrlDeleteProduct() {
		return url_delete_product;
	}


	public static String getUrlCreateProduct() {
		return url_create_product;
	}


	/**
	 * 
	 */
	public Const() {
		// TODO Auto-generated constructor stub
	}
}
